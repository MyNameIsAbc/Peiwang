package com.example.utils;

public class CommonUtils {
    public static int byteToInt(byte b) {
//Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }
}
