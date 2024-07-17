/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class PVR {

    long PVR_Hex;
    private int Revision, Version;

    public long getPVR_Hex() {
        return PVR_Hex;
    }

    public void setPVR_Hex(long PVR_Hex) {
        this.PVR_Hex = PVR_Hex;
    }

    public int getRevision() {
        return Revision;
    }

    public void setRevision(int revision) {
        Revision = revision;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }
}
