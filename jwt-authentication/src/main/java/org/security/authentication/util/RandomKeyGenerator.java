package org.security.authentication.util;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomKeyGenerator {
    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[64]; // 64 bytes = 512 bits, because 512 / 8 = 64
        random.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}