/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

import java.util.Arrays;

/**
 *
 * @author localadmin
 */
class L2CacheBlock {

    boolean valid = false;
    long address = 0;
    byte[] data = new byte[128];

    L2CacheBlock() {
        Arrays.fill(data, (byte) 0);
    }
}
