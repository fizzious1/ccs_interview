package game

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestValidateGuess(t *testing.T) {
	/* Example inputs:
	valid: "007", "81", " 10  "
	invalid: "$", "-15", " "
	*/

	guess, err := ValidateGuess("20")
	assert.NoError(t, err)
	assert.Equal(t, 20, guess)

}

func TestValidateGuessCorrectness(t *testing.T) {
	/* In order to test TestValidateGuessCorrectness you must override the 'random generation'
	Use the following static values and validate the result.
	Example inputs:
	input: "007", "81", " 10  "
	invalid: "$", "-15", " "
	*/

	isCorrect := ValidateGuessCorrectness(10)
	assert.Equal(t, isCorrect, false)
}
