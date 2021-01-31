import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String inputString = sc.next();
        int i = 0, j = inputString.length() - 1;
        boolean isPalindrome = true;

        while (i < j) {
            if (inputString.charAt(i) != inputString.charAt(j)) {
                isPalindrome = false;
                break;
            }
            i++; j--;
        }

        System.out.println(isPalindrome ? "yes" : "no");

    }
}