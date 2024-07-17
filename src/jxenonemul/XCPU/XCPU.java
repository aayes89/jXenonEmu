/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
import java.io.*;
import java.util.Arrays;
import jxenonemul.Core.BUS;
import static jxenonemul.XCPU.SPRConstants.SPR_PIR;
import static jxenonemul.XCPU.SPRConstants.SPR_PpeTlbIndexHint;
import static jxenonemul.XCPU.SPRConstants.SPR_TTR;

class XCPU {

    private XCPUContext cpuContext;
    // Constants
    private static final long SROM_ADDR = 0x20000000000L;
    private static final int SROM_SIZE = 0x8000;

    private static final long SRAM_ADDR = 0x20000010000L;
    private static final int SRAM_SIZE = 0xffff;

    private static final long FUSESET_LOC = 0x20000020000L;
    private static final int FUSESET_SIZE = 0x17ff;

    private static final long XCPU_START_ADDR = 0x20000000100L;

    public XCPU(String blPath, BUS busPointer) {
        cpuContext = new XCPUContext();
        cpuContext.bus = busPointer;
        cpuContext.SROM = new byte[SROM_SIZE];
        cpuContext.SRAM = new byte[SRAM_SIZE];
        cpuContext.cpuCores = new PowerPCState[6];

        for (int core = 0; core < 6; core++) {
            cpuContext.cpuCores[core] = new PowerPCState();
            // Set core ID via PIR Reg
            cpuContext.cpuCores[core].SPR[SPR_PIR] = core;

            // TLB Index Hint register preferred SET for replacement.
            cpuContext.cpuCores[core].SPR[SPR_PpeTlbIndexHint] = 0x1; // TLB Set 3, Index 0.

            // Memset L1 DCache
            cpuContext.cpuCores[core].L1 = new byte[0x8000];
            Arrays.fill(cpuContext.cpuCores[core].L1, (byte) 0);
        }

        // Thread Timeout Register on CPU0
        cpuContext.cpuCores[0].SPR[SPR_TTR] = 0x1000;

        // MSR on reset
        cpuContext.cpuCores[0].MSR.SF = true;
        cpuContext.cpuCores[0].MSR.HV = true;

        cpuContext.cpuCores[0].PVR.PVR_Hex = 0x007105000L;

        // Set Fuses
        cpuContext.cpuCores[0].CPUFuses[0] = 0xc0ffffffffffffffL; // Determines console type
        cpuContext.cpuCores[0].CPUFuses[1] = 0x0f0f0f0f0f0f0ff0L; // Retail console or Devkit console
        cpuContext.cpuCores[0].CPUFuses[2] = 0x0000000000000000L; // Lockdown counter for 2BL/CB
        cpuContext.cpuCores[0].CPUFuses[3] = 0x8CBA33C6B70BF641L; // CPU Key - First 32 bits
        cpuContext.cpuCores[0].CPUFuses[4] = 0x8CBA33C6B70BF641L; // CPU Key - First 32 bits copy
        cpuContext.cpuCores[0].CPUFuses[5] = 0x2AC5A81E6B41BFE6L; // CPU Key - Last 32 bits
        cpuContext.cpuCores[0].CPUFuses[6] = 0x2AC5A81E6B41BFE6L; // CPU Key - Last 32 bits copy
        cpuContext.cpuCores[0].CPUFuses[7] = 0x0000000000000000L; // LDV, prevents downgrading console
        cpuContext.cpuCores[0].CPUFuses[8] = 0x0000000000000000L;
        cpuContext.cpuCores[0].CPUFuses[9] = 0x0000000000000000L;
        cpuContext.cpuCores[0].CPUFuses[10] = 0x0000000000000000L;
        cpuContext.cpuCores[0].CPUFuses[11] = 0x0000000000000000L;

        if (!load1BL(blPath)) {
            return;
        }
    }

    private boolean load1BL(String filePath) {
        try (RandomAccessFile inputFile = new RandomAccessFile(filePath, "r")) {
            long fileSize = inputFile.length();

            if (fileSize == SROM_SIZE) {
                inputFile.readFully(cpuContext.SROM);
                System.out.println("XCPU: 1BL loaded successfully. Entry point at 0x8000020000000100");
            }
            return true;
        } catch (IOException e) {
            System.out.println("XCPU: Unable to open file: " + filePath + ".");
            return false;
        }
    }

    public void start(long startAddress) {
        cpuContext.cpuCores[0].NIA = startAddress;
        cpuContext.cpuCores[0].coreRunning = true;
        cpuContext.executionRunning = true;
        PPCInterpreter.ppcInterpreterExecute(cpuContext);
    }

}
