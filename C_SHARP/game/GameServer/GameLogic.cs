using System;
using System.Text;

public static class GameLogic
{
    private static readonly int SecretNumber = 42; // The correct number to guess
    private static readonly Random RandomGenerator = new Random();

    public static (int, string) ValidateGuess(string input)
    {
        if (!int.TryParse(input, out int guess))
        {
            return (0, "Invalid input! Please enter a number.");
        }

        if (guess < 1 || guess > 100)
        {
            return (0, "Number out of range! Please guess between 1 and 100.");
        }

        return (guess, null);
    }

    public static bool CheckGuessCorrectness(int guess)
    {
        return guess == SecretNumber;
    }

    public static string GeneratePrefix(int guess)
    {
        StringBuilder prefix = new StringBuilder();

        int formatChoice = RandomGenerator.Next(0, 3);

        switch (formatChoice)
        {
            case 0:
                prefix.Append(guess % 2 == 0
                    ? $"The number you selected is {guess}, and it is even!"
                    : $"The number you selected is {guess}, and it is odd!");
                break;
            case 1:
                prefix.Append(guess > 100
                    ? $"You selected {guess}, a number greater than 100! Great choice!"
                    : $"You selected {guess}, which is a small number!");
                break;
            case 2:
                int randomFact = RandomGenerator.Next(0, 100);
                prefix.Append($"The number {guess} has a special fact: {randomFact} is a random number generated.");
                break;
        }

        if (guess >= 0 && guess <= 50)
            prefix.Append(" Your guess is within the safe zone!");
        else if (guess > 50 && guess <= 150)
            prefix.Append(" Be careful! Your guess is in the uncertain range.");
        else
            prefix.Append(" Your guess is in the high-risk zone!");

        return prefix.ToString();
    }
}
