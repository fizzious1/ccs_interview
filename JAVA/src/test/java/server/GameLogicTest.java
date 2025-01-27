package server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class GameLogicTest {
    private final GameLogic gameLogic = new GameLogic();
    private final int[] primes = {2, 3, 5, 7, 11, 13};

    static class MockRandom extends Random {
        private final int baseNumber;
        private final int primeIndex;
        private int callCount = 0;

        public MockRandom(int baseNumber, int primeIndex) {
            this.baseNumber = baseNumber;
            this.primeIndex = primeIndex;
        }
    }
    @Test
    public void testValidateGuess() {
        // Test boundary values
        assertEquals(1, gameLogic.validateGuess("1"));
        assertEquals(42, gameLogic.validateGuess("42"));
        assertEquals(100, gameLogic.validateGuess("100"));

        // Test out-of-range values
        assertThrows(IllegalArgumentException.class, () -> {gameLogic.validateGuess("0");});
        assertThrows(IllegalArgumentException.class, () -> {gameLogic.validateGuess("-5");});
        assertThrows(IllegalArgumentException.class, () -> {gameLogic.validateGuess("101");});
    }
    
    @Test
    public void testadjustOdd() { 
        for (int prime : primes) {
            int result = gameLogic.adjustOdd(99, prime);
            assertEquals(99 + prime, result);
        }
    }

    @Test
    public void testreverseNumber() { 
        assertEquals(8, gameLogic.adjustEven(8));
        assertEquals(62, gameLogic.adjustEven(26));
        assertEquals(99, gameLogic.adjustEven(99));
        assertEquals(1, gameLogic.adjustEven(100));   
    }

    @Test
    public void testTransformRange() {

        // 120 >= 100 -> 120 / 2 = 60
        assertEquals(60, gameLogic.transformRange(120));

        // 99 < 100 & >= 50 -> remains 99
        assertEquals(99, gameLogic.transformRange(99));

        // 49 < 50 -> 49 * 2 = 98
        assertEquals(98, gameLogic.transformRange(49));

        // Exactly 100 -> 100 >= 100 -> 50
        assertEquals(50, gameLogic.transformRange(100));

        // Exactly 50 -> not < 50, not >= 100 -> remains 50
        assertEquals(50, gameLogic.transformRange(50));

        // 1 -> < 50 -> 2
        assertEquals(2, gameLogic.transformRange(1));
    }

    public void testValidateGuessCorrectnessWithMockRandom() {
        //random == 39, prime == 13 => 39+13=52
        int guessOdd = 52;
        GameLogic logicOdd = new GameLogic(new MockRandom(39, 5));
        boolean oddResult = logicOdd.checkGuessCorrectness(guessOdd);
        assertTrue(oddResult);

        //random == 62, prime = 2 => 62 => 26 => 26*2 = 52
        int guessEven = 52;
        GameLogic logicEven = new GameLogic(new MockRandom(62, 0));
        boolean evenResult = logicEven.checkGuessCorrectness(guessEven);
        assertTrue(evenResult);

        //random == 95, prime == 7 => 95+7=102 => 102/2=51
        int guessOver100 = 51;
        GameLogic logicOver100 = new GameLogic(new MockRandom(95, 3));
        boolean over100Result = logicOver100.checkGuessCorrectness(guessOver100);
        assertTrue(over100Result);

        //random == 23, prime == 2 => 23+2=25 => 25*2=50
        int guessUnder50 = 50;
        GameLogic logicUnder50 = new GameLogic(new MockRandom(23, 0));
        boolean under50Result = logicUnder50.checkGuessCorrectness(guessUnder50);
        assertTrue(under50Result);

        //random == 8, prime == 2 => 8*2=16
        int guessSingleDigitEven = 16;
        GameLogic logicSingleDigitEven = new GameLogic(new MockRandom(8, 0));
        boolean singleDigitEvenResult = logicSingleDigitEven.checkGuessCorrectness(guessSingleDigitEven);
        assertTrue(singleDigitEvenResult);
    }
}
