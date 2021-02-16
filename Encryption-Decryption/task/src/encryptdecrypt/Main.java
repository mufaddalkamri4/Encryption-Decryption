package encryptdecrypt;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

// Applying strategy pattern

// Strategy
interface EncryptionStrategy {
    String encrypt(String input, int key);
    String decrypt(String input, int key);
}

// Concrete Strategy - Shift
class ShiftEncryptionStrategy implements EncryptionStrategy {
    @Override
    public String encrypt(String input, int key) {
        StringBuilder out = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                int tmp = ((input.charAt(i) - 96 + key) % 26) + 96;
                out.setCharAt(i, (char) tmp);
            } else if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                int tmp = ((input.charAt(i) - 40 + key) % 26) + 40;
                out.setCharAt(i, (char) tmp);
            }
        }
        return out.toString();
    }

    @Override
    public String decrypt(String input, int key) {
        StringBuilder out = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++) { 
            if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                int tmp = (input.charAt(i) - 96 - key);
                tmp = tmp < 1 ? 26 + tmp : tmp;
                tmp = (tmp % 26) + 96;
                out.setCharAt(i, (char) tmp);
            } else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                int tmp = (input.charAt(i) - 40 - key);
                tmp = tmp < 1 ? 26 + tmp : tmp;
                tmp = (tmp % 26) + 40;
                out.setCharAt(i, (char) tmp);
            }
        }
        return out.toString();
    }
}

// Concrete Strategy - Unicode
class UnicodeEncryptionStrategy implements EncryptionStrategy {
    @Override
    public String encrypt(String input, int key) {
        StringBuilder out = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) >= ' ' && input.charAt(i) <= '~') {
                int tmp = ((input.charAt(i) - 32 + key) % 95) + 32;
                out.setCharAt(i, (char) tmp);
            }
        }
        return out.toString();
    }

    @Override
    public String decrypt(String input, int key) {
        StringBuilder out = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) >= ' ' && input.charAt(i) <= '~') {
                int tmp = (input.charAt(i) - 32 - key);
                tmp = tmp < 1 ? 95 + tmp : tmp;
                tmp = (tmp % 95) + 32;
                out.setCharAt(i, (char) tmp);
            }
        }
        return out.toString();
    }
}

//Context
class Encryptor {
    private EncryptionStrategy strategy;

    public void setStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }
    public String encrypt(String input, int key) {
        return strategy.encrypt(input, key);
    }
    public String decrypt(String input, int key) {
        return strategy.decrypt(input, key);
    }
}

public class Main {
    public static void main(String[] args) {
        
        Encryptor ec = new Encryptor();

        String action = "enc";
        String inputString = "";
        String inputFilePath = "";
        String outputFilePath = "";
        int key = 0;
        String output = "";

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
                case "-in":
                    inputFilePath = args[i + 1];
                    break;
                case "-out":
                    outputFilePath = args[i + 1];
                    break;
                case "-alg":
                    switch (args[i + 1]) {
                        case "unicode":
                            ec.setStrategy(new UnicodeEncryptionStrategy());
                            break;
                        default:
                            ec.setStrategy(new ShiftEncryptionStrategy());
                    }
                    break;
                default:
                    break;
            }
        }

        if(!inputString.equals("")){
            if(action.equals("enc")) {
                output = ec.encrypt(inputString, key);
            } else {
                output = ec.decrypt(inputString, key);
            }
        } else if(!inputFilePath.equals("")){
            File file = new File("./" + inputFilePath);

            try (Scanner scanner = new Scanner(file)) {
                StringBuilder in = new StringBuilder();
                while (scanner.hasNext()) {
                    in.append(scanner.nextLine());
                }
                inputString = in.toString();
                if(action.equals("enc")) {
                    output = ec.encrypt(inputString, key);
                } else {
                    output = ec.decrypt(inputString, key);
                }
            } catch (Exception e) {
                System.out.println("Error: cant read in file");
            }
        }

        if (!outputFilePath.equals("")){
            File file = new File("./" + outputFilePath);

            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.println(output);
            } catch (Exception e) {
                System.out.println("Error: cant write to out file");
            }
        } else {
            System.out.println(output);
        }
    }
}
