package tests

import (
	"ccs_interview/game"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestValidateGuess(t *testing.T) {
	guess, err := game.ValidateGuess("20")
	assert.NoError(t, err)
	assert.Equal(t, 50, guess)
}
