/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class CR {

    private int CR_Hex;
    private int[] CR_fields = new int[8];

    public int getCRField(int index) {
        return CR_fields[index];
    }

    public void setCRField(int index, int value) {
        CR_fields[index] = value;
    }

    public int getCR_Hex() {
        return CR_Hex;
    }

    public void setCR_Hex(int CR_Hex) {
        this.CR_Hex = CR_Hex;
    }
}
