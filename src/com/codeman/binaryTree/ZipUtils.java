package com.codeman.binaryTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 压缩解压工具
 */
public class ZipUtils {
    private Map<Byte, String> huffmanCode = new HashMap<>();
    private Map<String, Byte> huffmanEncode = new HashMap<>();

    /**
     * 解压
     * @param srcPath 源文件路径
     *      * @param tarPath 目标文件路径
     */
    public void unzip(String srcPath, String tarPath) {
        // 通过数据流，获取源文件字节数据
        File srcFile = new File(srcPath);
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(srcFile));
            byte[] zipB = (byte[]) ois.readObject();
            huffmanCode = (Map<Byte, String>) ois.readObject();
            byte[] b = unZioBytes(zipB);
            fos = new FileOutputStream(new File(tarPath));
            fos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压 byte to byte
     * @param zipB
     * @return
     */
    private byte[] unZioBytes(byte[] zipB) {
        // 转换huffmanCode
        for (Byte aByte : huffmanCode.keySet()) {
            huffmanEncode.put(huffmanCode.get(aByte), aByte);
        }
        // 遍历zipB解压
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zipB.length; i ++) {
            sb.append(byte2String(i == zipB.length - 1 ,zipB[i]));
        }

        // 转换为原byte[]
        List<Byte> bytes = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < sb.toString().length(); i ++) {
            Byte aByte = huffmanEncode.get(sb.substring(temp, i));
            if (aByte != null) {
                bytes.add(aByte);
                temp = i;
            }
        }
        byte[] resultBs = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            resultBs[i] = bytes.get(i);
        }
        return resultBs;
    }

    /**
     * 转换byte为8位字符串
     * @param b 为true保留原样，不补0
     * @param b1
     * @return
     */
    private String byte2String(boolean b, byte b1) {
        String result = "";
        int temp = b1; // 转换为int ，是为了下面的256，因为256超过byte
        if (!b) {
            temp |= 256; // 256为1 0000 0000 ，或上则肯定会保留9位
        }
        result = Integer.toBinaryString(temp);
        if (!b) {
            result = result.substring(result.length() - 8);
        }
        return result;
    }

    /**
     * 压缩
     *
     * @param srcPath 源文件路径
     * @param tarPath 目标文件路径
     */
    public void zip(String srcPath, String tarPath) {
        // 通过数据流，获取源文件字节数据
        File srcFile = new File(srcPath);
        FileInputStream dis = null;
        ObjectOutputStream oos = null;
        try {
            dis = new FileInputStream(srcFile);
            byte[] b = new byte[dis.available()];
            dis.read(b);
            HuffmanTree huffmanTree = new HuffmanTree(changeBytes2Nodes(b));
            huffmanCode = huffmanTree.getHuffmanCode();
            byte[] zipB = zipBytes(b);
            oos = new ObjectOutputStream(new FileOutputStream(new File(tarPath)));
            oos.writeObject(zipB);
            oos.writeObject(huffmanCode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 用huffman编码，压缩原字节数据
     * @param b
     * @return
     */
    private byte[] zipBytes(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte b1 : b) {
            sb.append(huffmanCode.get(b1));
        }
        int length = sb.length() % 8 == 0 ? sb.length() / 8 : sb.length() / 8 + 1;
        byte[] results = new byte[length];
        int sbIndex = 0;
        for (int i = 0; i < results.length; i ++) {
            if (i == results.length - 1) {
                results[i] = (byte) Integer.parseInt((sb.substring(sbIndex)), 2);
            } else {
                results[i] = (byte) Integer.parseInt((sb.substring(sbIndex, sbIndex + 8)), 2);
                sbIndex += 8;
            }
        }
        return results;
    }

    /**
     * 将字节码转换为TreeNode数组
     * @param b
     */
    private HufTreeNode[] changeBytes2Nodes(byte[] b) {
        Map<Byte, Integer> tempCount = new HashMap<>();
        for (byte b1 : b) {
            if (tempCount.get(b1) == null) {
                tempCount.put(b1, 1);
            } else {
                tempCount.put(b1, tempCount.get(b1) + 1);
            }
        }
        HufTreeNode[] results = new HufTreeNode[tempCount.size()];
        int tempIndex = 0;
        for (Byte b0 : tempCount.keySet()) {
            results[tempIndex ++] = new HufTreeNode(tempCount.get(b0), b0);
        }
        return results;
    }

    public Map<Byte, String> getHuffmanCode() {
        return huffmanCode;
    }

    public void setHuffmanCode(Map<Byte, String> huffmanCode) {
        this.huffmanCode = huffmanCode;
    }

    public Map<String, Byte> getHuffmanEncode() {
        return huffmanEncode;
    }

    public void setHuffmanEncode(Map<String, Byte> huffmanEncode) {
        this.huffmanEncode = huffmanEncode;
    }
}
