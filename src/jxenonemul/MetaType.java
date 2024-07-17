/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
public enum MetaType {
    metaType0(0),             // Pre Jasper (0x01198010)
    metaType1(1),             // Jasper, Trinity & Corona (0x00023010 [Jasper 
                              // & Trinity] and 0x00043000 [Corona])
    metaType2(2),             // BigBlock Jasper (0x008A3020 and 0x00AA3020)
    metaTypeUninitialized(3), // Really old JTAG XeLL images
    metaTypeNone(4);          // No spare type or unknown

    private final int value;

    MetaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
