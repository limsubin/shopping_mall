package com.lsb.portfolio.shopping_mall.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

//TODO 해싱에 대해 알아보기
public class CryptoUtil {
    public static class SHA1 {
        private SHA1() {}

        public static String hash(String input) {
            return SHA1.hash(input, null);
        }

        public static String hash(String input, String fallback) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.reset();
                messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
                return String.format("%040x", new BigInteger(1, messageDigest.digest()));
            } catch (Exception ignored) {
                return fallback;
            }
        }
    }

    public static class SHA512 {
        private SHA512() {}

        public static String hash(String input) {
            return SHA512.hash(input, null);
        }

        public static String hash(String input, String fallback) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.reset();
                messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
                return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
            } catch (Exception ignored) {
                return fallback;
            }
        }
    }

    private CryptoUtil() {}
}
