package org.delphy.tokenherocms.util;

import java.util.Random;

/**
 * @author mutouji
 */
public class TimeUtil {
    public static Long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static String generateId() {
        Random random = new Random();
        Integer s = random.nextInt(10000);
        String  index = String.format("%04d", s);
        Long time = System.currentTimeMillis();
        return  "" + time + index;
    }

    public static String generatePhone() {
        Random random = new Random();
        Integer s = random.nextInt(100000000);
        return String.format("133%09d", s);
    }
    public static Long generateGender() {
        Random random = new Random();
        Integer s = random.nextInt(2);
        return s.longValue();
    }
    public static double generateTotalDpy() {
        double dpy = new Random().nextDouble() * 10000;
        return Double.parseDouble(String.format("%.2f", dpy - 0.005));
    }

}
