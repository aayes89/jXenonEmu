/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
import java.util.Arrays;

public class State {

    private byte A = 0x00;
    private byte B = 0x00;
    private byte C = 0x00;
    private byte D = 0x00;
    private byte E = 0x00;
    private byte F = 0x00;
    private byte[] memory = new byte[256];
    private byte position = 0x00;
    private byte step = 0x00;
    private byte register1 = 0x00;
    private byte register2 = 0x00;
    private byte address1 = 0x00;
    private byte address2 = 0x00;
    private byte conditional = 0x00;
    private byte carry = 0x00;
    private byte data = 0x00;

    public State() {
        Arrays.fill(memory, (byte) 0xff);
        memory[0] = 0x01; // Example initial memory value
        memory[1] = 0x48; // Example initial memory value
        memory[2] = (byte) 0xD6; // Example initial memory value
        memory[255] = (byte) 0xff; // Fault flag
    }

    public byte getA() {
        return A;
    }

    public byte getB() {
        return B;
    }

    public byte getC() {
        return C;
    }

    public byte getD() {
        return D;
    }

    public byte getE() {
        return E;
    }

    public byte getF() {
        return F;
    }

    public byte[] getMemory() {
        return memory;
    }

    public byte getPosition() {
        return position;
    }

    public byte getStep() {
        return step;
    }

    public byte getRegister1() {
        return register1;
    }

    public byte getRegister2() {
        return register2;
    }

    public byte getAddress1() {
        return address1;
    }

    public byte getAddress2() {
        return address2;
    }

    public byte getConditional() {
        return conditional;
    }

    public byte getCarry() {
        return carry;
    }

    public byte getData() {
        return data;
    }

    public void setA(byte A) {
        this.A = A;
    }

    public void setB(byte B) {
        this.B = B;
    }

    public void setC(byte C) {
        this.C = C;
    }

    public void setD(byte D) {
        this.D = D;
    }

    public void setE(byte E) {
        this.E = E;
    }

    public void setF(byte F) {
        this.F = F;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    public void setStep(byte step) {
        this.step = step;
    }

    public void setRegister1(byte register1) {
        this.register1 = register1;
    }

    public void setRegister2(byte register2) {
        this.register2 = register2;
    }

    public void setAddress1(byte address1) {
        this.address1 = address1;
    }

    public void setAddress2(byte address2) {
        this.address2 = address2;
    }

    public void setConditional(byte conditional) {
        this.conditional = conditional;
    }

    public void setCarry(byte carry) {
        this.carry = carry;
    }

    public void setData(byte data) {
        this.data = data;
    }
}
