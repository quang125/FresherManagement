package com.intern.project.freshermanagement.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author TruongNH
 */
public class IpAddressUtils {
    public static final String PROXY_NGINX_IP = "X-Real-IP";
    public static final String PROXY_FORWARD_IP = "X-Forwarded-For";

    /**
     * Get IP of the client
     * @param request rest
     * @return IP
     */
    public static String resolveIp(HttpServletRequest request) {
        String ipFor = request.getHeader(PROXY_FORWARD_IP);

        if (StringUtils.isNotBlank(ipFor)) {
            return ipFor.split(",")[0].trim();
        }

        String ipReal = request.getHeader(PROXY_NGINX_IP);
        if (StringUtils.isNotBlank(ipReal)) {
            return ipReal;
        }

        return request.getRemoteAddr();
    }

}
