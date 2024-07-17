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

public class PCIBus {
    private List<PCIDevice> connectedPCIDevices;

    private static final int XMA_DEV_NUM = 0x0;
    private static final int CDROM_DEV_NUM = 0x1;
    private static final int HDD_DEV_NUM = 0x2;
    private static final int OHCI0_DEV_NUM = 0x4;
    private static final int EHCI0_DEV_NUM = 0x4;
    private static final int OHCI1_DEV_NUM = 0x5;
    private static final int EHCI1_DEV_NUM = 0x5;
    private static final int FAST_ETH_DEV_NUM = 0x7;
    private static final int SFC_DEV_NUM = 0x8;
    private static final int _580C_DEV_NUM = 0x9;
    private static final int SMC_DEV_NUM = 0xa;
    private static final int _5841_DEV_NUM = 0xF;

    public PCIBus() {
        connectedPCIDevices = new ArrayList<>();
    }

    public void addPCIDevice(PCIDevice device) {
        System.out.println("PCI Bus > New device attached: " + device.getDeviceName() + " 0x" + Long.toHexString(device.getStartAddress()) + " - 0x" + Long.toHexString(device.getEndAddress()));
        connectedPCIDevices.add(device);
    }

    public void read(long readAddress, long[] data, byte byteCount) {
        for (PCIDevice device : connectedPCIDevices) {
            if (readAddress >= device.getStartAddress() && readAddress <= device.getEndAddress()) {
                device.read(readAddress, data, byteCount);
                return;
            }
        }

        System.out.println("PCI Bus: Read failed at address 0x" + Long.toHexString(readAddress));
        data[0] = 0xffL;
    }

    public void write(long writeAddress, long data, byte byteCount) {
        if (connectedPCIDevices.isEmpty()) return;

        for (PCIDevice device : connectedPCIDevices) {
            if (writeAddress >= device.getStartAddress() && writeAddress <= device.getEndAddress()) {
                device.write(writeAddress, data, byteCount);
                return;
            }
        }

        System.out.println("PCI Bus: Write failed at address 0x" + Long.toHexString(writeAddress) + " data = 0x" + Long.toHexString(data));
    }

    public void configRead(long readAddress, long[] data, byte byteCount) {
        int bus = (int) ((readAddress >> 20) & 0xF);
        int device = (int) ((readAddress >> 15) & 0x1F);
        int reg = (int) (readAddress & 0xFFF);

        String currentDevName = getDeviceName(device);

        if (currentDevName.isEmpty()) {
            System.out.println("PCI Config Space Read: Unknown device accessed: Dev 0x" + Integer.toHexString(device) + " Reg 0x" + Integer.toHexString(reg));
            return;
        }

        for (PCIDevice pciDevice : connectedPCIDevices) {
            if (pciDevice.getDeviceName().equals(currentDevName)) {
                pciDevice.configRead(readAddress, data, byteCount);
                return;
            }
        }

        System.out.println("PCI Read to unimplemented device: " + currentDevName);
    }

    public void configWrite(long writeAddress, long data, byte byteCount) {
        int bus = (int) ((writeAddress >> 20) & 0xF);
        int device = (int) ((writeAddress >> 15) & 0x1F);
        int reg = (int) (writeAddress & 0xFFF);

        String currentDevName = getDeviceName(device);

        if (currentDevName.isEmpty()) {
            System.out.println("PCI Config Space Write: Unknown device accessed: Dev 0x" + Integer.toHexString(device) + " Func 0x" + Integer.toHexString(reg) + " data = 0x" + Long.toHexString(data));
            return;
        }

        for (PCIDevice pciDevice : connectedPCIDevices) {
            if (pciDevice.getDeviceName().equals(currentDevName)) {
                pciDevice.configWrite(writeAddress, data, byteCount);
                return;
            }
        }

        System.out.println("PCI Write to unimplemented device: " + currentDevName + " data = 0x" + Long.toHexString(data));
    }

    private String getDeviceName(int device) {
        switch (device) {
            case XMA_DEV_NUM:
                return "XMA";
            case CDROM_DEV_NUM:
                return "CDROM";
            case HDD_DEV_NUM:
                return "HDD";
            case OHCI0_DEV_NUM:
                return "OHCI0";
            case OHCI1_DEV_NUM:
                return "OHCI1";
            case FAST_ETH_DEV_NUM:
                return "ETHERNET";
            case SFC_DEV_NUM:
                return "SFC";
            case _580C_DEV_NUM:
                return "580C";
            case SMC_DEV_NUM:
                return "SMC";
            case _5841_DEV_NUM:
                return "5841";
            default:
                return "";
        }
    }
}
