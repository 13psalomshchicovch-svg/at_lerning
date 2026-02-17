package less14.utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String randomString(int len) {
//        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append((AB.charAt(rnd.nextInt(AB.length()))));
        }
        return sb.toString();
    }

    public static String randomStringNumber(int len) {
        String AB = "0123456789";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append((AB.charAt(rnd.nextInt(AB.length()))));
        }
        return sb.toString();
    }

    public static String randomEmail() {
        return randomString(5) + "@mail.ru";
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    static void main(String[] args) {
        System.out.println(randomEmail());
        System.out.println(randomString(6));
        System.out.println(randomStringNumber(10));
        System.out.println(randomInt(5, 150));

        String[] names = {"a","b","c","d","e"};
        System.out.println(randomItem(names));
    }

    private static String randomItem(String[] values) {
        int index = randomInt(0, values.length - 1);
        return values[index];
    }
}


