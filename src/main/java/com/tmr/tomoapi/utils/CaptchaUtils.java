package com.tmr.tomoapi.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class CaptchaUtils {

    public static final String INT_VERIFY_CODES = "123456789";
    public static final String CHAR_VERIFY_CODES = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    public static String getRandomIntCode()
    {
        int codesLen = INT_VERIFY_CODES.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(4);
        for (int i = 0; i < 4; i++)
        {
            verifyCode.append(INT_VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static String getRandomCharCode()
    {
        int codesLen = CHAR_VERIFY_CODES.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(4);
        for (int i = 0; i < 4; i++)
        {
            verifyCode.append(CHAR_VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static BufferedImage createImg(String code){
        int w = 160;
        int h = 60;
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++)
        {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        // 设置边框色
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);

        Random random = new SecureRandom();
        Color c = getRandColor(200, 250, random);
        // 设置背景色
        g2.setColor(c);
        g2.fillRect(0, 2, w, h - 4);

        // 绘制干扰线
        // 设置线条的颜色
        g2.setColor(getRandColor(160, 200, random));
        for (int i = 0; i < 20; i++)
        {
            int x = rand.nextInt(w - 1);
            int y = rand.nextInt(h - 1);
            int xl = rand.nextInt(6) + 1;
            int yl = rand.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        // 噪声率
        float yawpRate = 0.05f;
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++)
        {
            int x = rand.nextInt(w);
            int y = rand.nextInt(h);
            int rgb = getRandomIntColor(random);
            image.setRGB(x, y, rgb);
        }

        // 使图片扭曲
        shear(g2, w, h, c, random);

        g2.setColor(getRandColor(100, 160, random));
        int fontSize = h - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++)
        {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (w / verifySize) * i + fontSize / 2, h / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
        }
        g2.dispose();
        return image;
    }

    public static Color getRandColor(int fc, int bc, Random random)
    {
        // 分辨率的最大值 255 255
        int max = 255;
        if (fc > max) {
            fc = max;
        }
        if (bc > max) {
            bc = max;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static int getRandomIntColor(Random random)
    {
        int[] rgb = getRandomRgb(random);
        int color = 0;
        for (int c : rgb)
        {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    public static int[] getRandomRgb(Random random)
    {
        int[] rgb = new int[3];
        for (int i = 0; i < rgb.length; i++)
        {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    public static void shear(Graphics g, int w1, int h1, Color color, Random random)
    {
        shearX(g, w1, h1, color, random);
        shearY(g, w1, h1, color, random);
    }

    public static void shearX(Graphics g, int w1, int h1, Color color, Random random)
    {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++)
        {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    public static void shearY(Graphics g, int w1, int h1, Color color, Random random)
    {

        // 50;
        int period = random.nextInt(40) + 10;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++)
        {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }
}
