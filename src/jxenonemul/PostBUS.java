package jxenonemul;

/**
 *
 * @author localadmin
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

class PostBUS {

    private static final long POST_BUS_ADDR = 0x20000061010L;
    private final long lastBusData = 0;
    private final Map<Integer, String> postCodes;

    public PostBUS() {
        this.postCodes = initializePostCodes();
    }

    public void read(long readAddress, long[] data, int byteCount) {
        System.out.println("POST Bus: Warning, read attempt to post bus. Returning last bus output.");
        data[0] = lastBusData;
    }

    public void write(long writeAddress, long data, int byteCount) {
        long postCode = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(data).getLong(0);
        POST(postCode);
    }

    private void POST(long postCode) {
        System.out.printf("POST: ");
        if (postCodes.containsKey((int) postCode)) {
            System.out.printf(postCodes.get((int) postCode) + "\n");
        } else {
            System.out.printf("Unrecognized POST code 0x%X\n", postCode);
        }
    }

    private Map<Integer, String> initializePostCodes() {
        Map<Integer, String> codes = new HashMap<>();
        // 1BL Codes
        codes.put(0x10, "1BL started");
        codes.put(0x11, "FSB_CONFIG_PHY_CONTROL - Execute FSB function1");
        codes.put(0x12, "FSB_CONFIG_RX_STATE - Execute FSB function2");
        codes.put(0x13, "FSB_CONFIG_TX_STATE - Execute FSB function3");
        codes.put(0x14, "FSB_CONFIG_TX_CREDITS - Execute FSB function4");
        codes.put(0x15, "FETCH_OFFSET - Verify CB offset");
        codes.put(0x16, "FETCH_HEADER - Copy CB header from NAND");
        codes.put(0x17, "VERIFY_HEADER - Verify CB header");
        codes.put(0x18, "FETCH_CONTENTS - Copy CB into protected SRAM");
        codes.put(0x19, "HMACSHA_COMPUTE - Generate CB HMAC key");
        codes.put(0x1a, "RC4_INITIALIZE - Initialize CB RC4 decryption key");
        codes.put(0x1b, "RC4_DECRYPT - RC4 decrypt CB");
        codes.put(0x1c, "SHA_COMPUTE - Generate hash of CB for verification");
        codes.put(0x1d, "SIG_VERIFY - RSA signature check of CB hash");
        codes.put(0x1e, "BRANCH - Jump to CB");

        // 1BL Panic Codes
        codes.put(0x81, "PANIC - MACHINE_CHECK");
        codes.put(0x82, "PANIC - DATA_STORAGE");
        codes.put(0x83, "PANIC - DATA_SEGMENT");
        codes.put(0x84, "PANIC - INSTRUCTION_STORAGE");
        codes.put(0x85, "PANIC - INSTRUCTION_SEGMENT");
        codes.put(0x86, "PANIC - EXTERNAL");
        codes.put(0x87, "PANIC - ALIGNMENT");
        codes.put(0x88, "PANIC - PROGRAM");
        codes.put(0x89, "PANIC - FPU_UNAVAILABLE");
        codes.put(0x8a, "PANIC - DECREMENTER");
        codes.put(0x8b, "PANIC - HYPERVISOR_DECREMENTER");
        codes.put(0x8c, "PANIC - SYSTEM_CALL");
        codes.put(0x8d, "PANIC - TRACE");
        codes.put(0x8e, "PANIC - VPU_UNAVAILABLE");
        codes.put(0x8f, "PANIC - MAINTENANCE");
        codes.put(0x90, "PANIC - VMX_ASSIST");
        codes.put(0x91, "PANIC - THERMAL_MANAGEMENT");
        codes.put(0x92, "PANIC - 1BL");
        codes.put(0x92, "PANIC - 1BL_UNRECOVERABLE");
        codes.put(0x93, "PANIC - TOO_MANY_CORES - 1BL is executed on wrong CPU core (panic)");
        codes.put(0x94, "PANIC - VERIFY_OFFSET - CB offset verification failed");
        codes.put(0x95, "PANIC - VERIFY_HEADER - CB header verification failed");
        codes.put(0x96, "PANIC - SIG_VERIFY - CB RSA signature verification failed");
        codes.put(0x97, "PANIC - NONHOST_RESUME_STATUS");
        codes.put(0x98, "PANIC - NEXT_STAGE_SIZE - Size of next stage is out-of-bounds");

        /* CB */
        codes.put(0x20, "CB entry point. initialize SoC");
        codes.put(0x21, "INIT_SECOTP - Initialize secotp, verify lockdown fuses");
        codes.put(0x22, "INIT_SECENG - Iitialize security engine");
        codes.put(0x23, "INIT_SYSRAM - Initialize EDRAM");
        codes.put(0x24, "VERIFY_OFFSET_3BL_CC");
        codes.put(0x25, "LOCATE_3BL_CC");
        codes.put(0x26, "FETCH_HEADER_3BL_CC");
        codes.put(0x27, "VERIFY_HEADER_3BL_CC");
        codes.put(0x28, "FETCH_CONTENTS_3BL_CC");
        codes.put(0x29, "HMACSHA_COMPUTE_3BL_CC");
        codes.put(0x2a, "RC4_INITIALIZE_3BL_CC");
        codes.put(0x2b, "RC4_DECRYPT_3BL_CC");
        codes.put(0x2c, "SHA_COMPUTE_3BL_CC");
        codes.put(0x2d, "SIG_VERIFY_3BL_CC");
        codes.put(0x2e, "HWINIT - Hardware initialization");
        codes.put(0x2f, "RELOCATE - Setup TLB entries, relocate to RAM");
        codes.put(0x30, "VERIFY_OFFSET_4BL_CD - Verify CD offset");
        codes.put(0x31, "FETCH_HEADER_4BL_CD - Verify CD header");
        codes.put(0x32, "VERIFY_HEADER_4BL_CD - Verify CD header");
        codes.put(0x33, "FETCH_CONTENTS_4BL_CD - Copy CD from NAND");
        codes.put(0x34, "HMACSHA_COMPUTE_4BL_CD - Create HMAC key for CD decryption");
        codes.put(0x35, "RC4_INITIALIZE_4BL_CD - Initialize CD RC4 key using HMAC key");
        codes.put(0x36, "RC4_DECRYPT_4BL_CD - RC4 decrypt CD with key");
        codes.put(0x37, "SHA_COMPUTE_4BL_CD - Compute hash of CD for verification");
        codes.put(0x38, "SIG_VERIFY_4BL_CD - RSA signature check of CD hash");
        codes.put(0x39, "SHA_VERIFY_4BL_CD - MemCmp cumputed hash with expected one");
        codes.put(0x3a, "BRANCH - Setup memory encryption and jump to CD");
        codes.put(0x3b, "PCI_INIT - Initialize PCI");

        /* CB PANICS */
        codes.put(0x9b, "PANIC - VERIFY_SECOTP_1 - Secopt fuse verification fail");
        codes.put(0x9c, "PANIC - VERIFY_SECOTP_2 - Secopt fuse verification fail2");
        codes.put(0x9d, "PANIC - VERIFY_SECOTP_3 - Secopt fuse verification console type? fail");
        codes.put(0x9e, "PANIC - VERIFY_SECOTP_4 - Secopt fuse verification console type? fail");
        codes.put(0x9f, "PANIC - VERIFY_SECOTP_5 - Secopt fuse verification console type? fail");
        codes.put(0xa0, "PANIC - VERIFY_SECOTP_6 - CB revocation check failed");
        codes.put(0xa1, "PANIC - VERIFY_SECOTP_7 - Panic after 0x21");
        codes.put(0xa2, "PANIC - VERIFY_SECOTP_8 - Panic after 0x21");
        codes.put(0xa3, "PANIC - VERIFY_SECOTP_9 - Panic after 0x21");
        codes.put(0xa4, "PANIC - VERIFY_SECOTP_10 - Failed SMC HMAC");
        codes.put(0xa5, "PANIC - VERIFY_OFFSET_3BL_CC");
        codes.put(0xa6, "PANIC - LOCATE_3BL_CC");
        codes.put(0xa7, "PANIC - VERIFY_HEADER_3BL_CC");
        codes.put(0xa8, "PANIC - SIG_VERIFY_3BL_CC");
        codes.put(0xa9, "PANIC - HWINIT - HArdware Initialization failed");
        codes.put(0xaa, "PANIC - VERIFY_OFFSET_4BL_CC");
        codes.put(0xab, "PANIC - VERIFY_HEADER_4BL_CC");
        codes.put(0xac, "PANIC - SIG_VERIFY_4BL_CC");
        codes.put(0xad, "PANIC - SHA_VERIFY_4BL_CC");
        codes.put(0xae, "PANIC - UNEXPECTED_INTERRUPT");
        codes.put(0xaf, "PANIC - UNSUPPORTED_RAM_SIZE");

        /* CB_A */
        codes.put(0xD0, "CB_A_ENTRY - CB_A entry point, copy self to 0x8000.0200.0001.C000 and continue from there.");
        codes.put(0xD1, "READ_FUSES - Copy fuses from SoC for CB_B decryption.");
        codes.put(0xD2, "VERIFY_OFFSET_CB_B - Verify CB_B offset.");
        codes.put(0xD3, "FETCH_HEADER_CB_B - Copy CB_B header from NAND for verification.");
        codes.put(0xD4, "VERIFY_HEADER_CB_B - Verify CB_B header.");
        codes.put(0xD5, "FETCH_CONTENTS_CB_B - Copy CBB into memory at 0x8000.0200.0001.0000 (old location of CB_A).");
        codes.put(0xD6, "HMACSHA_COMPUTE_CB_B - Create HMAC key for CD decryption.");
        codes.put(0xD7, "RC4_INITIALIZE_CB_B - Initialize CD RC4 key using HMAC key.");
        codes.put(0xD8, "RC4_DECRYPT_CB_B - RC4 decrypt CD.");
        codes.put(0xD9, "SHA_COMPUTE_CB_B - Compute hash of CD for verification.");
        codes.put(0xDa, "SHA_VERIFY_CB_B - MemCmp computed hash with expected one (where rgh2 glitches).");
        codes.put(0xDb, "BRANCH_CB_B - Verify CB_B offset.");

        /* CB_A PANICS */
        codes.put(0xF0, "PANIC - VERIFY_OFFSET_CB_B - CB_B offset verification fail.");
        codes.put(0xF1, "PANIC - VERIFY_HEADER_CB_B - CB_B header verification fail");
        codes.put(0xF2, "PANIC - SHA_VERIFY_CB_B - CB_B security hash comparison fail.");
        codes.put(0xF3, "PANIC - ENTRY_SIZE_INVALID_CB_B - CB_B size check fail (must be less than 0xC000).");

        /* CD */
        codes.put(0x40, "Entrypoint of CD, setup memory paging.");
        codes.put(0x41, "VERIFY_OFFSET - Verify offset to CE");
        codes.put(0x42, "FETCH_HEADER - Copy CE header from NAND for verification");
        codes.put(0x43, "VERIFY_HEADER - Verify CE header");
        codes.put(0x44, "FETCH_CONTENTS - Read CE from NAND into memory");
        codes.put(0x45, "HMACSHA_COMPUTE - Create HMAC key for CE decryption");
        codes.put(0x46, "RC4_INITIALIZE - Initialize CE RC4 key using HMAC key");
        codes.put(0x47, "RC4_DECRYPT - RC4 decrypt CE");
        codes.put(0x48, "SHA_COMPUTE - Compute hash of CE for verification");
        codes.put(0x49, "SHA_VERIFY - MemCmp computed hash with expected one. (RGH1 Glitches here)");
        codes.put(0x4a, "LOAD_6BL_CF");
        codes.put(0x4b, "LZX_EXPAND - LZX Decompress CE");
        codes.put(0x4c, "SWEEP_CACHES");
        codes.put(0x4d, "DECODE_FUSES");
        codes.put(0x4e, "FETCH_OFFSET_6BL_CF - Load CD (kernel patches) offset");
        codes.put(0x4f, "VERIFY_OFFSET_6BL_CF - Verify CF offset");
        codes.put(0x50, "LOAD_UPDATE_1 - Load CF1/CG1 (patch slot 1) if version & header check pass");
        codes.put(0x51, "LOAD_UPDATE_2 - Load CF2/CG2 (patch slot 2) if version & header check pass");
        codes.put(0x52, "BRANCH - Startup kernel/hypervisor");
        codes.put(0x53, "DECRYPT_VERIFY_HV_CERT - Decrypt and verify hypervisor certificate");

        /* CD PANICS */
        codes.put(0xB1, "PANIC - VERIFY_OFFSET - CE decryption failed");
        codes.put(0xB2, "PANIC - VERIFY_HEADER - Failed to verify CE header");
        codes.put(0xB3, "PANIC - SHA_VERIFY - CE hash comparison fail");
        codes.put(0xB4, "PANIC - LZX_EXPAND - CE LZX decompression failed");
        codes.put(0xB5, "PANIC - VERIFY_OFFSET_6BL - CF verification failed");
        codes.put(0xB6, "PANIC - DECODE_FUSES - Fuse decryption/check failed");
        codes.put(0xB7, "PANIC - UPDATE_MISSING - CF decryption failed, patches missing");
        codes.put(0xB8, "PANIC - CF_HASH_AUTH - CF hash auth failed");

        /* CE/CF PANICS */
        codes.put(0xC1, "PANIC - LZX_EXPAND_1 - Panic - LDICreateDecompression");
        codes.put(0xC2, "PANIC - LZX_EXPAND_2 - 7BL Size Verification");
        codes.put(0xC3, "PANIC - LZX_EXPAND_3 - Header/Patch Fragment Info");
        codes.put(0xC4, "PANIC - LZX_EXPAND_4 - Unexpected LDI Fragment");
        codes.put(0xC5, "PANIC - LZX_EXPAND_5 - LDISetWindowData");
        codes.put(0xC6, "PANIC - LZX_EXPAND_6 - LDIDecompress");
        codes.put(0xC7, "PANIC - LZX_EXPAND_7 - LDIResetDecompression");
        codes.put(0xC8, "PANIC - SHA_VERIFY - 7BL Signature Verify");

        /* HYPERVISOR */
        codes.put(0x58, "INIT_HYPERVISOR - Hypervisor Initialization begin");
        codes.put(0x59, "INIT_SOC_MMIO - Initialize SoC MMIO");
        codes.put(0x5a, "INIT_XEX_TRAINING - Initialize XEX training");
        codes.put(0x5b, "INIT_KEYRING - Initialize key ring");
        codes.put(0x5c, "INIT_KEYS - Initialize keys");
        codes.put(0x5d, "INIT_SOC_INT - Initialize SoC Interrupts");
        codes.put(0x5e, "INIT_SOC_INT_COMPLETE - Initialization complete");

        /* HYPERVISOR PANICS */
        codes.put(0xFF, "HV > PANIC - FATAL!");

        /* KERNEL */
        codes.put(0x60, "INIT_KERNEL - Initialize kernel");
        codes.put(0x61, "INITIAL_HAL_PHASE_0 - Initialize HAL phase 0");
        codes.put(0x62, "INIT_PROCESS_OBJECTS - Initialize process objects");
        codes.put(0x63, "INIT_KERNEL_DEBUGGER - Initialize kernel debugger");
        codes.put(0x64, "INIT_MEMORY_MANAGER - Initialize memory manager");
        codes.put(0x65, "INIT_STACKS - Initialize stacks");
        codes.put(0x66, "INIT_OBJECT_SYSTEM - Initialize object system");
        codes.put(0x67, "INIT_PHASE1_THREAD - Initialize phase 1 thread");
        codes.put(0x68, "INIT_PROCESSORS - Initialize processors");
        codes.put(0x69, "INIT_KEYVAULT - Initialize keyvault");
        codes.put(0x6A, "INIT_HAL_PHASE_1 - Initialize HAL phase 1");
        codes.put(0x6B, "INIT_SFC_DRIVER - Initialize flash controller");
        codes.put(0x6C, "INIT_SECURITY - Initialize security");
        codes.put(0x6D, "INIT_KEY_EX_VAULT - Initialize extended keyvault");
        codes.put(0x6E, "INIT_SETTINGS - Initialize settings");
        codes.put(0x6F, "INIT_POWER_MODE - Initialize power mode");
        codes.put(0x70, "INIT_VIDEO_DRIVER - Initialize video driver");
        codes.put(0x71, "INIT_AUDIO_DRIVER - Initialize audio driver");
        codes.put(0x72, "INIT_BOOT_ANIMATION - Initialize bootanim.xex, XMADecoder, XAudioRender");
        codes.put(0x73, "INIT_SATA_DRIVER - Initialize SATA driver");
        codes.put(0x74, "INIT_SHADOWBOOT - Initialize shadowboot");
        codes.put(0x75, "INIT_DUMP_SYSTEM - Initialize dump system");
        codes.put(0x76, "INIT_SYSTEM_ROOT - Initialize system root");
        codes.put(0x77, "INIT_OTHER_DRIVERS - Initialize other drivers");
        codes.put(0x78, "INIT_STFS_DRIVER - Initialize STFS driver");
        codes.put(0x79, "LOAD_XAM - Initialize xam.xex");

        return codes;
    }

}
