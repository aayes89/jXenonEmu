/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
public class Emulator {
    private State state;
    private String instruction = "OFF";

    public Emulator(State state) {
        this.state = state;
    }

    public byte fetchInstruction() {
        int position = Util.hex2int(state.getPosition());
        return state.getMemory()[position];
    }

    public void executeInstruction(byte current) {
        int position = Util.hex2int(state.getPosition());
        int carry = Util.hex2int(state.getCarry());
        int step = Util.hex2int(state.getStep());

        int newCarry = carry;
        int newStep = step;
        String newInstruction = instruction;

        if (step == 0) {
            if (current == 0x00) {  // NOP
                newInstruction = "NOP";
            } else if (current == 0x01) {  // MDM
                newInstruction = "MDM";
                newCarry = 1;
                newStep = 1;
            } else if (current == (byte) 0xFF) {  // FLT
                newInstruction = "FLT";
                state.getMemory()[255] = (byte) 0xFE;
            }
        } else if (step == 1 && carry == 1) {  // MDM Step 1
            state.setData(current);
            newStep = 2;
        } else if (step == 2 && carry == 1) {  // MDM Step 2
            state.setAddress1(current);
            newCarry = 0;
            newStep = 0;
            int address = Util.hex2int(state.getAddress1());
            state.getMemory()[address + 1] = state.getData();
        }

        int newPosition = position + 1;
        state.setPosition(Util.int2hex(newPosition));
        state.setCarry(Util.int2hex(newCarry));
        state.setStep(Util.int2hex(newStep));
        instruction = newInstruction;
    }

    public void updateDisplay() {
        String vidmem = new String(state.getMemory(), 215, 40);
        System.out.printf("A: 0x%X\n", Util.hex2int(state.getA()));
        System.out.printf("B: 0x%X\n", Util.hex2int(state.getB()));
        System.out.printf("C: 0x%X\n", Util.hex2int(state.getC()));
        System.out.printf("D: 0x%X\n", Util.hex2int(state.getD()));
        System.out.printf("E: 0x%X\n", Util.hex2int(state.getE()));
        System.out.printf("F: 0x%X\n", Util.hex2int(state.getF()));
        System.out.printf("position: 0x%X\n", Util.hex2int(state.getPosition()));
        System.out.printf("step: 0x%X\n", Util.hex2int(state.getStep()));
        System.out.printf("register1: 0x%X\n", Util.hex2int(state.getRegister1()));
        System.out.printf("register2: 0x%X\n", Util.hex2int(state.getRegister2()));
        System.out.printf("address1: 0x%X\n", Util.hex2int(state.getAddress1()));
        System.out.printf("address2: 0x%X\n", Util.hex2int(state.getAddress2()));
        System.out.printf("conditional: 0x%X\n", Util.hex2int(state.getConditional()));
        System.out.printf("fault: 0x%X\n", Util.hex2int(state.getMemory()[255]));
        System.out.printf("carry: 0x%X\n", Util.hex2int(state.getCarry()));
        System.out.printf("data: 0x%X\n", Util.hex2int(state.getData()));
        System.out.printf("current: 0x%X\n", Util.hex2int(fetchInstruction()));
        System.out.printf("opcode: %s\n", instruction);
        System.out.printf("|%s|\n", vidmem);
        System.out.println(repr(state.getMemory()));
    }

    private String repr(byte[] memory) {
        StringBuilder repr = new StringBuilder("[");
        for (byte b : memory) {
            repr.append(String.format("0x%02X, ", b));
        }
        repr.append("]");
        return repr.toString();
    }
}
