package com.example.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jachat
 * @ClassName: JachatEncode
 * @Description: 用于协议加密的工具类加密解密
 * @date 2018年5月18日
 */

public class JachatEncode {

    private static final int[][] JACHAT_WORD = {
            {62, 82, 43, 3, 23, 7, 1, 71, 46, 60, 47, 84, 17, 38, 19, 37, 53, 93, 15, 12, 64, 76, 73, 29, 5, 55, 59,
                    66, 48, 16, 83, 42, 11, 94, 13, 27, 57, 65, 14, 86, 21, 92, 79, 8, 10, 69, 44, 77, 51, 6, 91, 95,
                    54, 4, 80, 78, 34, 72, 28, 36, 2, 74, 88, 75, 81, 41, 52, 18, 63, 70, 67, 49, 31, 25, 33, 58, 89,
                    50, 35, 22, 20, 68, 26, 61, 9, 85, 56, 87, 32, 45, 90, 24, 39, 40, 30, 85},
            {46, 84, 52, 91, 15, 57, 70, 43, 27, 76, 89, 62, 54, 56, 41, 71, 58, 74, 19, 79, 69, 59, 12, 8, 11, 28, 88,
                    34, 32, 10, 51, 45, 23, 42, 30, 63, 44, 48, 94, 75, 5, 61, 4, 16, 22, 68, 24, 93, 67, 20, 47, 49,
                    72, 92, 78, 13, 35, 3, 73, 90, 7, 65, 39, 64, 9, 83, 80, 14, 77, 86, 38, 1, 85, 25, 82, 6, 37, 87,
                    81, 55, 50, 40, 53, 26, 33, 95, 21, 36, 31, 2, 66, 60, 29, 17, 18, 86},
            {10, 47, 16, 27, 77, 21, 75, 45, 74, 82, 56, 72, 22, 3, 80, 63, 46, 20, 51, 55, 40, 19, 85, 92, 18, 8, 13,
                    11, 95, 70, 58, 61, 89, 28, 49, 38, 7, 53, 48, 29, 62, 1, 88, 43, 44, 59, 94, 39, 78, 91, 9, 34, 2,
                    76, 42, 93, 33, 5, 90, 14, 35, 87, 6, 60, 86, 15, 71, 32, 41, 66, 64, 30, 4, 81, 26, 25, 68, 84, 24,
                    79, 50, 65, 17, 67, 31, 23, 69, 52, 37, 36, 54, 57, 12, 83, 73, 87},
            {41, 62, 73, 72, 79, 64, 28, 44, 4, 8, 5, 6, 93, 17, 34, 85, 74, 19, 7, 15, 67, 14, 2, 24, 43, 35, 86, 33,
                    26, 40, 71, 68, 18, 50, 84, 10, 9, 13, 21, 27, 89, 1, 56, 49, 32, 42, 75, 88, 95, 54, 55, 16, 29,
                    36, 94, 69, 58, 12, 65, 59, 87, 63, 37, 76, 46, 83, 92, 30, 57, 82, 48, 91, 22, 80, 51, 25, 77, 47,
                    23, 53, 66, 60, 31, 81, 90, 52, 38, 20, 39, 45, 11, 78, 70, 61, 3, 88},
            {62, 85, 68, 47, 35, 12, 8, 76, 60, 43, 11, 57, 16, 87, 46, 44, 63, 25, 15, 7, 42, 32, 70, 90, 26, 45, 2,
                    65, 30, 38, 40, 37, 48, 77, 5, 39, 36, 86, 10, 56, 1, 67, 64, 88, 9, 22, 75, 53, 18, 69, 20, 50, 94,
                    24, 71, 83, 34, 73, 21, 81, 79, 66, 3, 72, 61, 84, 49, 6, 19, 4, 55, 78, 41, 58, 52, 28, 23, 33, 14,
                    59, 82, 54, 29, 17, 92, 51, 93, 89, 95, 31, 80, 13, 27, 74, 91, 89},
            {28, 72, 91, 94, 70, 2, 79, 64, 80, 48, 84, 10, 34, 19, 62, 26, 86, 32, 78, 37, 58, 56, 31, 50, 74, 54, 5,
                    1, 71, 40, 76, 7, 63, 14, 85, 4, 42, 24, 77, 35, 45, 88, 93, 9, 33, 87, 29, 67, 66, 41, 47, 83, 57,
                    6, 25, 44, 18, 53, 11, 49, 17, 90, 27, 36, 15, 82, 92, 61, 68, 51, 16, 38, 75, 60, 20, 81, 69, 65,
                    21, 39, 95, 23, 43, 8, 12, 89, 73, 59, 3, 55, 22, 46, 52, 13, 30, 90}};

    private static Map<Integer, int[]> scrDecMap = new HashMap<>();

    static {
        int jaCount = JACHAT_WORD.length;
        int jaKey[] = new int[jaCount];
        for (int i = 0; i < JACHAT_WORD.length; i++) {
            int wleng = JACHAT_WORD[0].length;
            jaKey[i] = JACHAT_WORD[i][wleng - 1];
            scrDecMap.put(jaKey[i], JACHAT_WORD[i]);
        }
        scrDecMap.put(301, jaKey);
        //System.out.println("encPwd::    初始化 Jachat Decoder  ***** ");
    }


    public static String getEncodeStr(String str) {
        int jaKey[] = scrDecMap.get(301);
        int jaleng = jaKey.length;
        int randNum[] = getRandomNums(jaKey[0], jaKey[jaleng - 1], jaleng);

        int frontWord = randNum[1];
        int afterWord = randNum[0];
        int afterRandAry[] = scrDecMap.get(afterWord);
        int frontRandAry[] = scrDecMap.get(frontWord);

        int len = str.length();

        int encLeng = len + 4;
        char[] encChar = new char[encLeng];

        int flagsInt = len - len / 2;

        int frontRandom[] = getRandomNums(0, 1, 2);
        int afterRandom[] = getRandomNums(0, 1, 2);
        int frontAfter_dx[] = getRandomNums(65, 90, 25);
        int frontAfter_xx[] = getRandomNums(97, 122, 25);
        int frontIndex = 0;
        int afterIndex = 0;

        if (afterRandom[0] == 0) {
            encChar[encLeng - 1] = (char) frontAfter_dx[0];
            afterIndex = encLeng - flagsInt - 2;
        } else {
            encChar[encLeng - 1] = (char) frontAfter_xx[0];
            afterIndex = encLeng - 3;
        }
        encChar[encLeng - 2] = (char) afterWord;

        if (frontRandom[0] == 0) {
            encChar[0] = (char) frontAfter_dx[1];
            frontIndex = 2;
        } else {
            encChar[0] = (char) frontAfter_xx[1];
            frontIndex = len - flagsInt + 1;
        }
        encChar[1] = (char) frontWord;

        int randConn[] = new int[50];
        for (int i = 0; i < frontAfter_dx.length; i++) {
            randConn[i] = frontAfter_dx[i];
        }
        for (int i = 0; i < frontAfter_xx.length; i++) {
            randConn[i + 25] = frontAfter_xx[i];
        }
        int randTmp[] = getRandomNumAry(randConn, 50, 50);
        int tempint[] = getRandomNums(3, 9, 6);
        int firtLeng = tempint[0];
        int hleng = 10 - firtLeng;
        char encrandChar0[] = new char[firtLeng];
        char encrandChar1[] = new char[hleng];
        encrandChar0[0] = (char) randTmp[0];
        encrandChar0[1] = (char) randTmp[2];
        encrandChar0[2] = (char) (firtLeng + 65);
        for (int i = 3; i < firtLeng; i++) {
            encrandChar0[i] = (char) randTmp[i];
        }
        for (int i = 0; i < hleng; i++) {
            encrandChar1[i] = (char) randTmp[i + 10];
        }

        int getRand = 0;
        for (int i = 0; i < len; i++) {
            int ascInt = ((int) str.charAt(i));
            int newAsc = 0;
            for (int j = 0; j < afterRandAry.length - 1; j++) {
                if (i >= flagsInt) {
                    getRand = afterRandAry[j] + 31;
                } else {
                    getRand = frontRandAry[j] + 31;
                }
                if (ascInt == getRand) {
                    newAsc = j + 32;
                    break;
                }
            }
            if (newAsc > 0) {
                if (i >= flagsInt) {
                    encChar[frontIndex] = (char) newAsc;
                    if (frontRandom[0] == 0) {
                        frontIndex++;
                    } else {
                        frontIndex--;
                    }
                } else {
                    encChar[afterIndex] = (char) newAsc;
                    if (afterRandom[0] == 0) {
                        afterIndex++;
                    } else {
                        afterIndex--;
                    }
                }
            }
        }
        return new StringBuffer().append(encrandChar0).append(encChar).append(encrandChar1).toString();
    }

    public static String getDecodeStr(String decstr) {
        int fristl = decstr.charAt(2) - 65;
        int hl = 10 - fristl;

        String str = decstr.substring(fristl, decstr.length() - hl);
        int decleng = str.length();
        int decFront_zf = str.charAt(0);
        int decafter_zf = str.charAt(decleng - 1);

        int decFrontRand = str.charAt(1);
        int decAfterRand = str.charAt(decleng - 2);

        int afterRandAry[] = scrDecMap.get(decAfterRand);
        int frontRandAry[] = scrDecMap.get(decFrontRand);

        String decStr = str.substring(2, decleng - 2);
        int scrDecLeng = decStr.length();

        int decFlagsInt = scrDecLeng - scrDecLeng / 2;
        decFlagsInt = scrDecLeng - decFlagsInt;

        int decFrontIndex = 0;
        int decAfterIndex = 0;

        if (decFront_zf <= 90) {
            decFrontIndex = scrDecLeng - decFlagsInt;
        } else {
            decFrontIndex = scrDecLeng - 1;
        }

        if (decafter_zf <= 90) {
            decAfterIndex = 0;
        } else {
            decAfterIndex = scrDecLeng - decFlagsInt - 1;
        }

        char decChar[] = new char[scrDecLeng];

        for (int i = 0; i < scrDecLeng; i++) {
            int decInt = (int) decStr.charAt(i) - 32;
            if (i >= decFlagsInt) {
                decChar[decAfterIndex] = (char) (frontRandAry[decInt] + 31);
                if (decafter_zf <= 90) {
                    decAfterIndex++;
                } else {
                    decAfterIndex--;
                }
            } else {
                decChar[decFrontIndex] = (char) (afterRandAry[decInt] + 31);
                if (decFront_zf <= 90) {
                    decFrontIndex++;
                } else {
                    decFrontIndex--;
                }
            }
        }
        return new StringBuffer().append(decChar).toString();
    }

    public static int[] getRandomNumAry(int random[], int intleng, int length) {
        if (length > intleng || length <= 0) {
            return null;
        }
        int result[] = new int[length];
        int datleng = intleng;
        int randLeng = length;
        datleng--;
        while (randLeng > 0) {
            randLeng--;
            if (datleng <= 0) {
                randLeng--;
                result[0] = random[0];
            } else {
                int num = (int) (Math.random() * datleng);
                result[randLeng] = random[num];
                if (num != datleng) {
                    random[num] = random[datleng];
                }
            }
            datleng--;
        }
        return result;
    }

    public static int[] getRandomNums(int min, int max, int length) {
        int datleng = max - min + 1;
        if (length > datleng || max < min) {
            return null;
        }
        int tmpAry[] = new int[datleng];
        for (int i = 0; i < datleng; i++) {
            tmpAry[i] = i + min;
        }
        return getRandomNumAry(tmpAry, datleng, length);
    }

}
