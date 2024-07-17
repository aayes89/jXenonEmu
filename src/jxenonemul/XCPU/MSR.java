/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class MSR {

    private long MSR_Hex;
    private int[] MSR_fields = new int[64];
    boolean HV;
    boolean SF;

    MSR() {
        this.HV = false;
        this.SF = false;
    }

    public long getMSR_Hex() {
        return MSR_Hex;
    }

    public void setMSR_Hex(long MSR_Hex) {
        this.MSR_Hex = MSR_Hex;
    }

    public int getMSRField(int index) {
        return MSR_fields[index];
    }

    public void setMSRField(int index, int value) {
        MSR_fields[index] = value;
    }
}
