/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul;

/**
 *
 * @author localadmin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class NAND implements SystemDevice {

    private String deviceName;
    private long NAND_START_ADDR = 0x200C8000000L;
    private long NAND_END_ADDR = 0x200C9000000L; // 16 Mb region

    private File inputFile;
    private byte[] rawNANDData = new byte[0x1000000];
    private long rawFileSize = 0;
    private boolean hasSpare = false;
    private MetaType imageMetaType = MetaType.metaTypeNone;

    @Override
    public void read(long readAddress, long[] data, byte byteCount) {
        int offset = (int) (readAddress & 0xffffff);
        offset = 1 != 0 ? ((offset / 0x200) * 0x210) + offset % 0x200 : offset;
        System.arraycopy(rawNANDData, offset, data, 0, byteCount);
    }

    @Override
    public void write(long writeAddress, long data, byte byteCount) {
        int offset = (int) (writeAddress & 0xffffff);
        offset = 1 != 0 ? ((offset / 0x200) * 0x210) + offset % 0x200 : offset;
        byte[] dataBytes = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            dataBytes[i] = (byte) (data >> (i * 8));
        }
        System.arraycopy(dataBytes, 0, rawNANDData, offset, byteCount);
    }

    public boolean Load(String filePath) {
        System.out.println("NAND: Loading file " + filePath);

        inputFile = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            rawFileSize = inputFile.length();

            System.out.println("NAND: File size = 0x" + Long.toHexString(rawFileSize) + " bytes.");

            if (!CheckMagic(inputStream)) {
                System.out.println("NAND: wrong magic found, Xbox 360 Retail NAND magic is 0xFF4F and Devkit NAND magic 0x0F4F.");
                return false;
            }

            inputStream.read(rawNANDData);
            inputStream.getChannel().position(0);

            CheckSpare(inputStream);

            if (hasSpare) {
                System.out.println("NAND: Image has spare.");

                // Check Meta Type
                imageMetaType = DetectSpareType(inputStream);
            }

        } catch (IOException e) {
            System.out.println("NAND: Unable to load file!");
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    private boolean CheckMagic(FileInputStream inputStream) throws IOException {
        byte[] magic = new byte[2];
        inputStream.read(magic);
        inputStream.getChannel().position(0);

        return (magic[0] == (byte) 0xff || magic[0] == (byte) 0x0f) && (magic[1] == (byte) 0x3f || magic[1] == (byte) 0x4f);
    }

    private void CheckSpare(FileInputStream inputStream) throws IOException {
        byte[] data = new byte[0x630];
        inputStream.read(data);

        hasSpare = true;
        int dataSize = data.length;
        for (int idx = 0; idx < dataSize; idx += 0x210) {
            if (!CheckPageECD(data, idx, inputStream)) {
                hasSpare = false;
                break; // Si se encuentra un error en el ECC, se sale del bucle
            }
        }
    }

    private boolean CheckPageECD(byte[] data, int offset, FileInputStream inputStream) throws IOException {
        byte[] actualData = new byte[4];
        inputStream.getChannel().position(offset + 524);
        inputStream.read(actualData);
        inputStream.getChannel().position(0);

        byte[] calculatedECD = CalculateECD(data, offset);
        //return actualData[0] == calculatedECD[0] && actualData[1] == calculatedECD[1] && actualData[2] == calculatedECD[2] && actualData[3] == calculatedECD[3];
        return Arrays.equals(actualData, calculatedECD); // Utiliza Arrays.equals para comparar los arrays de manera más eficiente
    }

    private byte[] CalculateECD(byte[] data, int offset) {
        int val = 0, v = 0;
        int count = 0;
        for (int i = 0; i < 0x1066; i++) {
            if ((i & 31) == 0) {
                int value = ((data[count + offset] & 0xFF) << 24)
                        | ((data[count + offset + 1] & 0xFF) << 16)
                        | ((data[count + offset + 2] & 0xFF) << 8)
                        | (data[count + offset + 3] & 0xFF);
                value = Integer.reverseBytes(value);
                v = ~value;
                count += 4;
            }
            val ^= v & 1;
            v >>= 1;
            if ((val & 1) != 0) {
                val ^= 0x6954559;
            }
            val >>= 1;
        }
        val = ~val;
        byte[] ret = new byte[4];
        ret[0] = (byte) (val << 6);
        ret[1] = (byte) ((val >> 2) & 0xFF);
        ret[2] = (byte) ((val >> 10) & 0xFF);
        ret[3] = (byte) ((val >> 18) & 0xFF);
        return ret;
    }

    private MetaType DetectSpareType(FileInputStream inputStream) throws IOException {
        if (!hasSpare) {
            return MetaType.metaTypeNone;
        }
        ByteBuffer bb = ByteBuffer.allocate(0x10);
        inputStream.getChannel().position(0x4400).read(bb);
        System.out.println(new String(bb.array()));
        byte[] tmp = new byte[0x10];
        inputStream.read(tmp);

        for (byte b : tmp) {
            System.out.print((char) b);//"0x%X", b);
        }

        System.out.println("\nReaded!");

        return MetaType.metaTypeUninitialized;
    }

    private MetaType DetectSpareType1(FileInputStream inputStream) throws IOException {
        if (!hasSpare) {
            return MetaType.metaTypeNone;
        }

        byte[] tmp = new byte[0x10];
        inputStream.getChannel().position(0x4400);
        inputStream.read(tmp);

        for (byte b : tmp) {
            System.out.print(b);
        }
        System.out.println("");

        inputStream.getChannel().position(0);

        return MetaType.metaTypeNone; // Implementar la lógica de detección de tipo adecuada aquí
    }

    @Override
    public void initialize(String deviceName, long startAddress, long endAddress) {
        this.deviceName = deviceName;
        this.NAND_START_ADDR = startAddress;
        this.NAND_END_ADDR = endAddress;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public long getStartAddress() {
        return NAND_START_ADDR;
    }

    @Override
    public long getEndAddress() {
        return NAND_END_ADDR;
    }
}
