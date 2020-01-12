package com.codeman.binaryTree;

import java.util.Arrays;

public class HuffmanTreeTest {
    public static void main(String[] args) {
//        HufTreeNode[] hufTreeNodes = {
//                new HufTreeNode(2,new Byte("00000010")),
//                new HufTreeNode(3,new Byte("00000011")),
//                new HufTreeNode(1,new Byte("00000001"))};
//        HuffmanTree huffmanTree = new HuffmanTree(hufTreeNodes);
//        int[] b = new int[0];
//        System.out.println(b[0]);

        ZipUtils zipUtils = new ZipUtils();
        zipUtils.zip("C:\\Users\\ASUS\\Desktop\\pic.png", "C:\\Users\\ASUS\\Desktop\\pic.zip");
        zipUtils.unzip("C:\\Users\\ASUS\\Desktop\\pic.zip", "C:\\Users\\ASUS\\Desktop\\pic2.png");


    }

    private static String byteToBitString(boolean flag, byte b) {
        //?????????? b
        int temp = b; //?? b ??? int
        //??????????????????????
        if(flag) {
            temp |= 256; //????? 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //???????temp??????????????
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }
}
