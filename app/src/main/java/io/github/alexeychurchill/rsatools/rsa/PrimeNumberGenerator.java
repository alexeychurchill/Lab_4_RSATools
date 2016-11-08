package io.github.alexeychurchill.rsatools.rsa;

/*
* Random number generator
* Generates prime numbers
* */

import android.content.Context;

import java.util.Random;

import io.github.alexeychurchill.rsatools.R;

public class PrimeNumberGenerator {
    private static int[] PRIMES;
    private int deltaIfNotPrime = 2;
    private Random randomGenerator = new Random();
    private boolean checkBy2000Primes = true;
    private boolean checkByMillerRabin = true;
    private int millerRabinRounds = 5;

    public PrimeNumberGenerator(Context context) {
        if (PRIMES == null) {
            PRIMES = context.getResources().getIntArray(R.array.primes_to_2000);
        }
    }

    public int generate16Bit() {
        int source = randomGenerator.nextInt(0xFFFF);
        source = ensureSizeAndNotOdd16Bit(source);
        source = correctToPrime(source);
        return source;
    }

    public int generate8Bit() {
        int source = randomGenerator.nextInt(0xFF);
        source = ensureSizeAndNotOdd8Bit(source);
        source = correctToPrime(source);
        return source;
    }

    public void setCheckBy2000Primes(boolean checkBy2000Primes) {
        this.checkBy2000Primes = checkBy2000Primes;
    }

    public void setCheckByMillerRabin(boolean checkByMillerRabin) {
        this.checkByMillerRabin = checkByMillerRabin;
    }

    public void setMillerRabinRounds(int millerRabinRounds) {
        this.millerRabinRounds = millerRabinRounds;
    }

    private int correctToPrime(int source) {
        boolean isPrime = checkIfPrime(source);
        while (!isPrime) {
            source += deltaIfNotPrime;
            isPrime = checkIfPrime(source);
        }
        return source;
    }

    private boolean checkIfPrime(int source) {
        boolean good = true;
        if (checkBy2000Primes) {
            good = checkIfPrimeBy2000Primes(source);
        }
        if (checkByMillerRabin) {
            good = good && checkIfPrimeByRabinMiller(source, millerRabinRounds);
        }
        return good;
    }

    private boolean checkIfPrimeByRabinMiller(int source, int rounds) {
//        Log.d("MILLER", "source = " + source);
        int t = (source - 1); //Initial t
        int s = 0; //Initial s

        while ((t % 2 == 0) && (t > 0)) {
            t = t / 2;
            s++;
        }

//        Log.d("MILLER", "s = " + s + " t = " + t);

        for (int roundI = 0; roundI < rounds; roundI++) { //Rounds (times to repeat)
            int a = randomGenerator.nextInt(source - 3) + 2;
            int x = MathUtils.powByMod(a, t, source);

//            Log.d("MILLER", "round = " + roundI + " a = " + a + " x = " + x);

            if ((x == 1) || (x == source - 1)) {
                continue;
            }

            for (int i = 0; i < s - 1; i++) {
                x = MathUtils.powByMod(x, 2, source);
//                Log.d("MILLER", "si = " + i + " x = " + x);
                if (x == 1) {
                    return false;
                }
                if (x == source - 1) { //To the next round
                    break;
                }
            }

            if (x != source - 1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkIfPrimeBy2000Primes(int source) {
        if (PRIMES == null) {
            return false;
        }
        for (int prime : PRIMES) {
            if (prime >= source) { //if we checking 8-bit numbers
                break;
            }
            if (source % prime == 0) {
                return false;
            }
        }
        return true;
    }

    private int ensureSizeAndNotOdd(int source, int parameter) {
        return source | parameter;
    }

    private int ensureSizeAndNotOdd8Bit(int source) {
        return ensureSizeAndNotOdd(source, (1 << 7) | 1);
    }

    private int ensureSizeAndNotOdd16Bit(int source) {
        return ensureSizeAndNotOdd(source, (1 << 15) | 1);
    }
}
