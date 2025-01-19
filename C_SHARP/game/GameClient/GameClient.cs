using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using System.Threading;

class GameClient
{
    public static void StartClient(string address, int port)
    {
        try
        {
            // Connect to the server
            using (TcpClient client = new TcpClient(address, port))
            using (NetworkStream stream = client.GetStream())
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            using (StreamWriter writer = new StreamWriter(stream, Encoding.UTF8) { AutoFlush = true })
            {
                Console.WriteLine("Connected to the game server as a single player.");

                while (true)
                {
                    // Prompt the user for input
                    Console.Write("Enter your guess (number) or 'exit' to quit: ");
                    string guess = Console.ReadLine();

                    if (string.IsNullOrEmpty(guess))
                        continue;

                    // Allow user to exit
                    if (guess.ToLower() == "exit")
                    {
                        Console.WriteLine("Exiting the game.");
                        break;
                    }

                    // Send guess to the server
                    writer.WriteLine(guess);

                    // Read response from the server
                    string serverResponse = reader.ReadLine();
                    if (serverResponse == null)
                    {
                        Console.WriteLine("Disconnected from the server.");
                        break;
                    }

                    Console.WriteLine("Server response: " + serverResponse);

                    // If correct guess, end the game
                    if (serverResponse == "Congratulations! You guessed the correct number!")
                    {
                        Console.WriteLine("You won the game! Exiting...");
                        break;
                    }

                    Thread.Sleep(1000); // Simulate delay before next round
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
        }
    }

    static void Main()
    {
        string address = "127.0.0.1"; // Replace with actual server address
        int port = 12345; // Replace with actual port number
        StartClient(address, port);
    }
}
