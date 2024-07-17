/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class FPSCR {

    private int FPSCR_Hex;
    private int[] FPSCR_fields = new int[32];

    public int getFPSCRField(int index) {
        return FPSCR_fields[index];
    }

    public void setFPSCRField(int index, int value) {
        FPSCR_fields[index] = value;
    }

    public int getFPSCR_Hex() {
        return FPSCR_Hex;
    }

    public void setFPSCR_Hex(int FPSCR_Hex) {
        this.FPSCR_Hex = FPSCR_Hex;
    }
}
