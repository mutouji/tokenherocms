package org.delphy.tokenherocms.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mutouji
 */
@Slf4j
public class RequestUtil {
    public static void logRequestIpAndUrl(HttpServletRequest request) {
        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        log.info("request, ip = " + ip + ", url = " + url);
    }
}
