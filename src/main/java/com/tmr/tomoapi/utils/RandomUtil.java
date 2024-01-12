package com.tmr.tomoapi.utils;

import java.text.DecimalFormat;
import java.util.*;

public class RandomUtil {

    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

    /**
     * 给定数组，抽取n个数据
     * @param list
     * @param n
     * @return
     */
    public static ArrayList getRandom(List list, int n) {

        Random random = new Random();

        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

        // 生成随机数字并存入HashMap
        for (int i = 0; i < list.size(); i++) {

            int number = random.nextInt(100) + 1;

            hashMap.put(number, i);
        }

        // 从HashMap导入数组
        Object[] robjs = hashMap.values().toArray();

        ArrayList r = new ArrayList();

        // 遍历数组并打印数据
        for (int i = 0; i < n; i++) {
            r.add(list.get((int) robjs[i]));
            System.out.print(list.get((int) robjs[i]) + "\t");
        }
        System.out.print("\n");
        return r;
    }

    /**
     * 生成大写、小写字母、数字的随机字符串
     * @return
     */
    public static String getRandomString(int length) {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%&";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        // 确保至少包含一个大小写字母、一个数字和一个特殊字符
        char randomChar = getRandomChar(random, "abcdefghijklmnopqrstuvwxyz");
        sb.append(randomChar);
        randomChar = getRandomChar(random, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        sb.append(randomChar);
        randomChar = getRandomChar(random, "0123456789");
        sb.append(randomChar);
        randomChar = getRandomChar(random, "@#$%&");
        sb.append(randomChar);

        // 生成剩余的随机字符
        for (int i = 4; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private static char getRandomChar(Random random, String characterSet) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }
}
