class Problem {
    public static void main(String[] args) {
        int operand1 = Integer.parseInt(args[1]);
        int operand2 = Integer.parseInt(args[2]);

        switch (args[0]) {
            case "+":
                System.out.println(operand1 + operand2);
                break;
            case "-":
                System.out.println(operand1 - operand2);
                break;
            case "*":
                System.out.println(operand1 * operand2);
                break;
            default:
                System.out.println("Unknown operator");
        }
    }
}