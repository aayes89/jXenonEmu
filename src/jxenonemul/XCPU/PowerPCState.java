/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class PowerPCState {

    private long CIA;
    long NIA;
    private PowerPCInstr CI = new PowerPCInstr();
    private GPR GPR = new GPR();
    private FPR FPR = new FPR();
    private CR CR = new CR();
    private FPSCR FPSCR = new FPSCR();
    private LR LR = new LR();
    MSR MSR = new MSR();
    private CTR CTR = new CTR();
    private XER XER = new XER();
    private TB TB = new TB();
    private SDR1 SDR1 = new SDR1();
    PVR PVR = new PVR();
    private SLBEntry[] SLB = new SLBEntry[16];
    private int trapEA = 0;
    long[] SPR = new long[1024];
    private boolean translationInProgress = false;
    private byte exceptionFlag = 0;
    private boolean iFetch = false;
    long[] CPUFuses = new long[12];
    private final MMUContext mmuContext = new MMUContext();
    boolean coreRunning;
    byte[] L1;

    // Initialize PowerPC State with default values
    public PowerPCState() {
        // Initialize SLB Entries
        for (int i = 0; i < SLB.length; i++) {
            SLB[i] = new SLBEntry();
        }
    }

    public long getCIA() {
        return CIA;
    }

    public void setCIA(long CIA) {
        this.CIA = CIA;
    }

    public long getNIA() {
        return NIA;
    }

    public void setNIA(long NIA) {
        this.NIA = NIA;
    }

    public PowerPCInstr getCI() {
        return CI;
    }

    public void setCI(PowerPCInstr CI) {
        this.CI = CI;
    }

    public GPR getGPR() {
        return GPR;
    }

    public FPR getFPR() {
        return FPR;
    }

    public CR getCR() {
        return CR;
    }

    public FPSCR getFPSCR() {
        return FPSCR;
    }

    public LR getLR() {
        return LR;
    }

    public MSR getMSR() {
        return MSR;
    }

    public CTR getCTR() {
        return CTR;
    }

    public XER getXER() {
        return XER;
    }

    public TB getTB() {
        return TB;
    }

    public SDR1 getSDR1() {
        return SDR1;
    }

    public PVR getPVR() {
        return PVR;
    }

    public SLBEntry[] getSLB() {
        return SLB;
    }

    public int getTrapEA() {
        return trapEA;
    }

    public void setTrapEA(int trapEA) {
        this.trapEA = trapEA;
    }

    public long[] getSPR() {
        return SPR;
    }

    public boolean isTranslationInProgress() {
        return translationInProgress;
    }

    public void setTranslationInProgress(boolean translationInProgress) {
        this.translationInProgress = translationInProgress;
    }

    public byte getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(byte exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public boolean isiFetch() {
        return iFetch;
    }

    public void setiFetch(boolean iFetch) {
        this.iFetch = iFetch;
    }

    public long[] getCPUFuses() {
        return CPUFuses;
    }

    public MMUContext getMmuContext() {
        return mmuContext;
    }
}
