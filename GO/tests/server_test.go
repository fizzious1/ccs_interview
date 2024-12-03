package tests

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestValidateGuess(t *testing.T) {
	guess, err := ValidateGuess("50")
	assert.NoError(t, err)
	assert.Equal(t, 50, guess)

	_, err = ValidateGuess("abc")
	assert.Error(t, err)

	_, err = ValidateGuess("150")
	assert.Error(t, err)
}
