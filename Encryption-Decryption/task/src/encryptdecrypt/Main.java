package encryptdecrypt;

import java.util.Scanner;

class Encryptor {

    String encrypt(String in) {
        StringBuilder out = new StringBuilder(in);
        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(i) >= 'a' && in.charAt(i) <= 'z') {
                out.setCharAt(i, (char) ('{' - in.charAt(i) + '`'));
            }
        }
        return out.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Encryptor ec = new Encryptor();

        String input = "we found a treasure!";
        String output = ec.encrypt(input);

        System.out.println(output);
    }
}
