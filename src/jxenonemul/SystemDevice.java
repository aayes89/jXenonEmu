/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
public interface SystemDevice {
    void initialize(String deviceName, long startAddress, long endAddress);
    void read(long readAddress, long[] data, byte byteCount);
    void write(long writeAddress, long data, byte byteCount);

    String getDeviceName();
    long getStartAddress();
    long getEndAddress();
}
