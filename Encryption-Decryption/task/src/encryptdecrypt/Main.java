package encryptdecrypt;

import java.util.Scanner;

class Encryptor {


    String encrypt3(String in, int key) {
        StringBuilder out = new StringBuilder(in);

        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(i) >= ' ' && in.charAt(i) <= '~') {
                int tmp = ((in.charAt(i) - 32 + key) % 95) + 32;
                out.setCharAt(i, (char) tmp);
            }
        }

        return out.toString();
    }

    String decrypt3(String in, int key) {
        StringBuilder out = new StringBuilder(in);

        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(i) >= ' ' && in.charAt(i) <= '~') {
                int tmp = (in.charAt(i) - 32 - key);
                tmp = tmp < 1 ? 95 + tmp : tmp;
                tmp = (tmp % 95) + 32;
                out.setCharAt(i, (char) tmp);
            }
        }

        return out.toString();
    }

    String encrypt2(String in, int key) {
        StringBuilder out = new StringBuilder(in);

        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(i) >= 'a' && in.charAt(i) <= 'z') {
                int tmp = ((in.charAt(i) - 96 + key) % 26) + 96;
                out.setCharAt(i, (char) tmp);
            }
        }

        return out.toString();
    }

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

        String action = "enc";
        String inputString = "";
        int key = 0;
        String output;

        for (int i = 0; i < args.length; i += 2){
            switch (args[i]) {
                case "-mode":
                    action = args[i+1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    inputString = args[i + 1];
                    break;
                default:
                    break;
            }
        }

        if(action.equals("enc")) {
            output = ec.encrypt3(inputString, key);
        } else {
            output = ec.decrypt3(inputString, key);
        }

        System.out.println(output);
    }
}
