/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.Core;

/**
 *
 * @author localadmin
 */
public class PCIDevice {

    private PCIDeviceInfo deviceInfo;

    public PCIDevice() {
        this.deviceInfo = new PCIDeviceInfo();
    }

    public void initialize(String deviceName, long startAddress, long endAddress) {
        deviceInfo.deviceName = deviceName;
        deviceInfo.startAddr = startAddress;
        deviceInfo.endAddr = endAddress;
    }

    public void read(long readAddress, long[] data, byte byteCount) {
        // Implementación de lectura del dispositivo
    }

    public void write(long writeAddress, long data, byte byteCount) {
        // Implementación de escritura del dispositivo
    }

    public void configRead(long readAddress, long[] data, byte byteCount) {
        // Implementación de lectura de configuración del dispositivo
    }

    public void configWrite(long writeAddress, long data, byte byteCount) {
        // Implementación de escritura de configuración del dispositivo
    }

    public String getDeviceName() {
        return deviceInfo.deviceName;
    }

    public long getStartAddress() {
        return deviceInfo.startAddr;
    }

    public long getEndAddress() {
        return deviceInfo.endAddr;
    }

    private class PCIDeviceInfo {
        private String deviceName;
        private long startAddr;
        private long endAddr;
    }
}
