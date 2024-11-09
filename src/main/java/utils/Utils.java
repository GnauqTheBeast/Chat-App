package utils;

import java.security.SecureRandom;

public class Utils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int lengthID = 6;

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(lengthID);
        for (int i = 0; i < lengthID; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
