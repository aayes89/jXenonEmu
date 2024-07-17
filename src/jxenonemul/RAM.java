/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
public class RAM {

    private static final long RAM_START_ADDR = 0x0;
    private static final int RAM_SIZE = 0x20000000; // 512MB
    private byte[] RAMData;

    public RAM() {
        RAMData = new byte[RAM_SIZE];
    }

    public void read(long readAddress, byte[] data, int byteCount) {
        int offset = (int) (readAddress - RAM_START_ADDR);
        System.arraycopy(RAMData, offset, data, 0, byteCount);
    }

    public void write(long writeAddress, byte[] data, int byteCount) {
        int offset = (int) (writeAddress - RAM_START_ADDR);
        System.arraycopy(data, 0, RAMData, offset, byteCount);
    }
}
