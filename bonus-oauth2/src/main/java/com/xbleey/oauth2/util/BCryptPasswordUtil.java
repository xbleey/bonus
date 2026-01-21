package com.xbleey.oauth2.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class BCryptPasswordUtil {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private BCryptPasswordUtil() {
    }

    public static String encode(String rawPassword) {
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    public static void main(String[] args) {
        System.out.println(encode("admin"));
    }
}
