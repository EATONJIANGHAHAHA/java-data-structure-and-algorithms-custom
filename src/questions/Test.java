package questions;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Random rnd = new Random();

        List<String> interned = new ArrayList<String>();

        for (; ; ) {

            int length = rnd.nextInt(100);

            StringBuilder builder = new StringBuilder();

            String chars = "abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i < length; i++) {

                builder.append(chars.charAt(rnd.nextInt(chars.length())));

            }

            interned.add(builder.toString().intern());
        }

    }
}
