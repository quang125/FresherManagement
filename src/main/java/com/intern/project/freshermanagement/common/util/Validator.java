package com.intern.project.freshermanagement.common.util;

import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

    public static boolean isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        final String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(regex);
    }

    public static boolean isValidPhone(String phone) {
        final String regex = "^$|\\d{10}";
        return phone.matches(regex);
    }

}