import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        BigInteger doubleFact = BigInteger.ONE;
        while (n > 0) {
            doubleFact = doubleFact.multiply(BigInteger.valueOf(n));
            n -= 2;
        }
        return doubleFact;
    }
}