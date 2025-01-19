using System;
using Xunit;
using GameLogic; // Ensure the namespace matches your project

public class GameLogicTests
{
    [Fact]
    public void ValidateGuess_ValidNumber_ReturnsParsedValue()
    {
        // Arrange
        string input = "42";

        // Act
        int result = GameLogic.ValidateGuess(input).Item1;

        // Assert
        Assert.Equal(42, result);
    }

    [Fact]
    public void ValidateGuess_InvalidNumber_ThrowsError()
    {
        // Arrange
        string input = "abc";

        // Act & Assert
        var exception = Assert.Throws<FormatException>(() => GameLogic.ValidateGuess(input));
        Assert.Contains("invalid input", exception.Message);
    }

    [Fact]
    public void CheckGuessCorrectness_CorrectGuess_ReturnsTrue()
    {
        // Act
        bool result = GameLogic.CheckGuessCorrectness(42);

        // Assert
        Assert.True(result);
    }

    [Fact]
    public void CheckGuessCorrectness_WrongGuess_ReturnsFalse()
    {
        // Act
        bool result = GameLogic.CheckGuessCorrectness(50);

        // Assert
        Assert.False(result);
    }
}
