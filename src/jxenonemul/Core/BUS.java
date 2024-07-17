/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.Core;

/**
 *
 * @author localadmin
 */
import java.util.ArrayList;
import java.util.List;
import jxenonemul.SystemDevice;

public class BUS {
    private PCIBus pciBus;
    private int deviceCount;
    private List<SystemDevice> connectedDevices;

    private static final long PCI_CONFIG_SPACE_BEGIN = 0x200D0000000L;
    private static final long PCI_CONFIG_SPACE_END = 0x200D1000000L;
    private static final long PCI_BUS_START_ADDR = 0x200EA000000L;
    private static final long PCI_BUS_END_ADDR = 0x200EA010000L;

    public BUS() {
        deviceCount = 0;
        connectedDevices = new ArrayList<>();
    }

    public void addPCIBus(PCIBus newPCIBus) {
        pciBus = newPCIBus;
    }

    public void addDevice(SystemDevice device) {
        deviceCount++;
        System.out.println("BUS-> New device attached: " + device.getDeviceName() + " 0x" + Long.toHexString(device.getStartAddress()) + " - 0x" + Long.toHexString(device.getEndAddress()));
        connectedDevices.add(device);
    }

    public void read(long readAddress, long[] data, byte byteCount) {
        for (SystemDevice device : connectedDevices) {
            if (readAddress >= device.getStartAddress() && readAddress <= device.getEndAddress()) {
                device.read(readAddress, data, byteCount);
                return;
            }
        }

        if (readAddress >= PCI_CONFIG_SPACE_BEGIN && readAddress <= PCI_CONFIG_SPACE_END) {
            pciBus.configRead(readAddress, data, byteCount);
            return;
        }

        if (readAddress >= PCI_BUS_START_ADDR && readAddress <= PCI_BUS_END_ADDR) {
            pciBus.read(readAddress, data, byteCount);
            return;
        }

        System.out.println("BUS: Read failed at address 0x" + Long.toHexString(readAddress));
        data[0] = 0x0L;
    }

    public void write(long writeAddress, long data, byte byteCount) {
        for (SystemDevice device : connectedDevices) {
            if (writeAddress >= device.getStartAddress() && writeAddress <= device.getEndAddress()) {
                device.write(writeAddress, data, byteCount);
                return;
            }
        }

        if (writeAddress >= PCI_CONFIG_SPACE_BEGIN && writeAddress <= PCI_CONFIG_SPACE_END) {
            pciBus.configWrite(writeAddress, data, byteCount);
            return;
        }

        if (writeAddress >= PCI_BUS_START_ADDR && writeAddress <= PCI_BUS_END_ADDR) {
            pciBus.write(writeAddress, data, byteCount);
            return;
        }

        if (data != 0) {
            System.out.println("BUS: Data write failed > (" + writeAddress + ") data = 0x" + Long.toHexString(data) + " data LE: 0x" + Long.toHexString(Long.reverseBytes(data)));
        } else {
            System.out.println("BUS: Write failed:\n(0x" + writeAddress + ") data: 0x" + Long.toHexString(data) + " LE: 0x" + Long.toHexString(Long.reverseBytes(data)));
        }
    }

    
}
