package io.github.alexeychurchill.rsatools.rsa;

public class MathUtils {
    public static int powByMod(int number, int pow, int mod) {
        long d = 1;
        int k = valuableBitCount(pow);
        for (int i = k; i >= 0; i--) {
            d = (d * d) % mod;
            if (bitValue(pow, i) == 1) {
                d = (d * number) % mod;
            }
        }
        return (int) d;
    }

    public static int powOf2(int pow) {
        return (1 << pow);
    }

    public static int valuableBitCount(int number) {
        int valuableBitCount = 32;
        while (valuableBitCount > 0) {
            int tester = powOf2(valuableBitCount - 1);
            if ((tester & number) == tester) {
                break;
            }
            valuableBitCount--;
        }
        return valuableBitCount;
    }

    public static int bitValue(int number, int bitNumber) {
        int pow2 = powOf2(bitNumber);
        return ((number & pow2) == pow2) ? 1 : 0;
    }
}
