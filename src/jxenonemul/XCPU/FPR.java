/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class FPR {

    private double[] registers = new double[32];

    public double getRegister(int index) {
        return registers[index];
    }

    public void setRegister(int index, double value) {
        registers[index] = value;
    }
}
