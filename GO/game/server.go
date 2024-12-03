package game

import (
	"fmt"
	"log"
	"net"
)

func StartServer() {
	listener, err := net.Listen("tcp", "localhost:8080")
	if err != nil {
		log.Fatalf("Error starting server: %v", err)
	}
	defer listener.Close()

	fmt.Println("Server started, waiting for a player...")

	// Accept only one player connection for now.
	conn, err := listener.Accept()
	if err != nil {
		log.Fatalf("Error accepting connection: %v", err)
	}
	defer conn.Close()

	fmt.Println("Player has connected.")

	// Simple game logic to check the player's guess
	for {
		buffer := make([]byte, 1024)
		n, err := conn.Read(buffer)
		if err != nil {
			log.Printf("Error reading from client: %v", err)
			return
		}

		// Process the guess sent by the client (assuming it's a number)
		guess := string(buffer[:n])
		fmt.Printf("Received guess: %s\n", guess)

		// Define the correct answer
		correctAnswer := "42"

		// Check if the guess matches the correct answer
		var response string
		if guess == correctAnswer {
			response = "Congratulations! You guessed the correct number!"
		} else {
			response = "Try again!"
		}

		// Send the response back to the client
		_, err = conn.Write([]byte(response))
		if err != nil {
			log.Printf("Error writing to client: %v", err)
			return
		}
	}
}
