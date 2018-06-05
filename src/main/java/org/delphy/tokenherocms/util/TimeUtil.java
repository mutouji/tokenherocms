package org.delphy.tokenherocms.util;

import java.util.Date;

public class TimeUtil {
    public static Long getCurrentSeconds() {
        Date now = new Date();
        Long time = now.getTime() / 1000;
        return time;
    }
}
