/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
public class Util {

    public static int hex2int(byte hexByte) {
        return hexByte & 0xFF;
    }

    public static byte int2hex(int intValue) {
        if (intValue < 0 || intValue > 255) {
            throw new IllegalArgumentException("El valor entero debe estar en el rango de 0 a 255.");
        }
        return (byte) intValue;
    }

    public static long BITMASK(int width, int index) {
        return ((1L << (width - index - 1)) & 0xFFFFFFFFFFFFFFFFL);
    }

    public static int BITGET(long data, int width, int index) {
        return ((data & BITMASK(width, index)) != 0) ? 1 : 0;
    }

    public static long BITSET(long data, int width, int index) {
        return data | BITMASK(width, index);
    }

    public static long DMASK(int b, int e) {
        return ((0xFFFFFFFF << (31 + b - e)) >> b);
    }

    public static long QMASK(int begin, int end) {
        return ((0xFFFFFFFFFFFFFFFFL << (63 + begin - end)) >> begin);
    }

    public static long EXTS(long data, int input) {
        return ((data & (1L << (input - 1))) != 0) ? (data | QMASK(0, 63 - input)) : data;
    }

    public static long QGET(long data, int begin, int end) {
        return ((data & QMASK(begin, end)) >> (63 - end));
    }
}
