package game

import (
	"fmt"
	"strconv"
)

func ValidateGuess(input string) (int, error) {
	guess, err := strconv.Atoi(input)
	if err != nil {
		return 0, fmt.Errorf("invalid input, please enter a number")
	}
	if guess < 1 || guess > 100 {
		return 0, fmt.Errorf("number out of range, please guess between 1 and 100")
	}
	return guess, nil
}
