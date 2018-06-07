package org.delphy.tokenherocms.util;

import java.util.Date;

/**
 * @author mutouji
 */
public class TimeUtil {
    public static Long getCurrentSeconds() {
        Date now = new Date();
        return now.getTime() / 1000;
    }
}
