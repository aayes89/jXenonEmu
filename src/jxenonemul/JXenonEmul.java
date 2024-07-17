/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jxenonemul;

import javax.swing.JFileChooser;

/**
 *
 * @author localadmin
 */
public class JXenonEmul {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PostBUS post = new PostBUS();
        
        JFileChooser jfc = new JFileChooser("/Users/localadmin/Downloads/1 - XENON/");
        jfc.showOpenDialog(null);
        NAND nandUtil = new NAND();

        if (nandUtil.Load(jfc.getSelectedFile().getAbsolutePath())) {
            State state = new State();
            Emulator emulator = new Emulator(state);

            while (state.getMemory()[255] == (byte) 0xff) {
                byte current = emulator.fetchInstruction();
                emulator.executeInstruction(current);
                emulator.updateDisplay();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println("NAND not available");
        }
    }

}
