package server;

import java.util.Random;

public class GameLogic {
    private static final int SECRET_NUMBER = 42;
    private Random random = new Random();
    private final int[] primes = {2, 3, 5, 7, 11, 13};

    public GameLogic(Random random) {
        this.random = random;
    }
    public GameLogic() {
        
    }

    public int validateGuess(String input) throws IllegalArgumentException {
        try {
            int guess = Integer.parseInt(input);
            if (guess < 1 || guess > 100) {
                throw new IllegalArgumentException("Number out of range, please guess between 1 and 100.");
            }
            return guess;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input, please enter a number.");
        }
    }

    public boolean checkGuessCorrectness(int guess) {
        int number = selectBaseNumber();
        if (number % 2 == 1) {
            number = adjustOdd(number, selectRandomPrime());
        } else {
            number = adjustEven(number);
        }
        number = transformRange(number);

        return guess == number;
    }

    public String generatePrefix(int guess) {
        int formatChoice = random.nextInt(3);
        String prefix;

        switch (formatChoice) {
            case 0:
                prefix = (guess % 2 == 0)
                        ? "The number you selected is " + guess + " and it is even!"
                        : "The number you selected is " + guess + " and it is odd!";
                break;
            case 1:
                prefix = (guess > 100)
                        ? "You selected " + guess + ", a number greater than 100! Great choice!"
                        : "You selected " + guess + ", which is a small number!";
                break;
            case 2:
                int randomFact = random.nextInt(100);
                prefix = "The number " + guess + " has a special fact: " + randomFact + " is a random number generated.";
                break;
            default:
                prefix = "You selected " + guess + ".";
        }

        if (guess >= 0 && guess <= 50) {
            prefix += " Your guess is within the safe zone!";
        } else if (guess > 50 && guess <= 150) {
            prefix += " Be careful! Your guess is in the uncertain range.";
        } else {
            prefix += " Your guess is in the high-risk zone!";
        }

        return prefix;  
    }


    //Helpers
    protected int selectBaseNumber() {
        return random.nextInt(100) + 1;
    }

    protected int adjustOdd(int number, int primeToAdd) {
        return number + primeToAdd;
    }

    protected int adjustEven(int number) {
        return reverseNumber(number);
    }

    protected int selectRandomPrime() {
        return primes[random.nextInt(primes.length)];
    }

    protected int transformRange(int number) {
        if (number >= 100) {
            return number / 2;
        } else if (number < 50) {
            return number * 2;
        }
        return number;
    }

    protected int reverseNumber(int number) {
        String reversed = new StringBuilder(String.valueOf(number)).reverse().toString();
        return Integer.parseInt(reversed);
    }
}
