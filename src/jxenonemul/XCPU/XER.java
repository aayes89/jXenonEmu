/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class XER {

    private long XER_Hex;
    private int ByteCount;
    private int CA, OV, SO;

    public long getXER_Hex() {
        return XER_Hex;
    }

    public void setXER_Hex(long XER_Hex) {
        this.XER_Hex = XER_Hex;
    }

    public int getByteCount() {
        return ByteCount;
    }

    public void setByteCount(int byteCount) {
        ByteCount = byteCount;
    }

    public int getCA() {
        return CA;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    public int getOV() {
        return OV;
    }

    public void setOV(int OV) {
        this.OV = OV;
    }

    public int getSO() {
        return SO;
    }

    public void setSO(int SO) {
        this.SO = SO;
    }
}
