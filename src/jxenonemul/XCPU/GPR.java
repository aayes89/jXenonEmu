/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class GPR {

    private long[] registers = new long[32];

    public long getRegister(int index) {
        return registers[index];
    }

    public void setRegister(int index, long value) {
        registers[index] = value;
    }
}
