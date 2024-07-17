/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
import java.util.Arrays;
import jxenonemul.Core.BUS;

public class XCPUContext {

    // Constants
    private static final int SROM_SIZE = 0x8000;

    private static final int SRAM_SIZE = 0xffff;

    // PowerPC CPU Cores

    PowerPCState[] cpuCores = new PowerPCState[6];

    // Current CPU ID
    byte currentCoreID;

    // Execution Status
    boolean executionRunning;

    // Bus pointer
    BUS bus;

    byte[] SROM = new byte[SROM_SIZE];
    byte[] SRAM = new byte[SRAM_SIZE];

    // 0x2000 cache blocks * 0x80 bytes CACHELINE_SIZE = 1Mb Cache. 
    L2CacheBlock[] l2Cache = new L2CacheBlock[0x2000];

    void XCPUContext() {
        for (int i = 0; i < cpuCores.length; i++) {
            cpuCores[i] = new PowerPCState();
        }

        for (int i = 0; i < l2Cache.length; i++) {
            l2Cache[i] = new L2CacheBlock();
        }
    }

    public PowerPCState getCoreState(int coreIndex) {
        return cpuCores[coreIndex];
    }
    public PowerPCState[] getPPCCores(){
        return cpuCores;
    }

}
