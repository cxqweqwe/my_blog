package com.fang.backgroundapi.utils;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Bernie_fang
 * @Description: 生成1个验证码图片
 * @create 2021/8/15 23:55
 **/
public class ValidateCode {

    private static int height;
    private static int width;
    private static int num;
    private String code = "";

    private BufferedImage buffimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    private Graphics2D gd = buffimg.createGraphics();

    private Random random = new Random();

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static int charNum = codeSequence.length;

    static {
        height = 40;
        width = 164;
        num = 5;
    }

    public ValidateCode(int height, int width, int num) {
        super();
        this.height = height;
        this.width = width;
        this.num = num;
    }

    public ValidateCode() {
        super();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void drawBackgroundAndLine() {
        gd.setColor(Color.LIGHT_GRAY);
        gd.fillRect(0, 0, width, height);
        gd.setColor(Color.black);
        gd.drawRect(0, 0, width - 1, height - 1);

        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            gd.drawLine(x, y, x + x1, y + y1);
        }
    }

    /**
     * 生成随机验证码
     *
     * @return
     */
    public String createCode() {
        String codeString = "";
        for (int i = 0; i < num; i++) {
            codeString = String.valueOf(codeSequence[random.nextInt(charNum)]);
            code += codeString;
        }
        return code;
    }

    /**
     * 生成验证码图片
     *
     * @return
     */
    public BufferedImage getCodeImg(String codeString) {
        if (!StringUtils.isEmpty(codeString)){
            this.code = codeString;
        }
        drawBackgroundAndLine();

        int fontHeight;
        int codeX;
        int CodeY;
        codeX = (width - 4) / (num + 1);
        fontHeight = height - 10;
        CodeY = height - 8;

        Font font = new Font("Fixdsys", Font.PLAIN, fontHeight);
        gd.setFont(font);

        for (int i = 0; i < num; i++) {
            String strRand = String.valueOf(code.charAt(i));
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            gd.setColor(new Color(red, green, blue));
            gd.drawString(strRand, (i + 1) * codeX, CodeY);
        }
        return buffimg;
    }


}
