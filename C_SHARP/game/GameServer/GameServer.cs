using System;
using System.IO;
using System.Net.Sockets;
using System.Text;

class GameClient
{
    private const string ServerAddress = "127.0.0.1";
    private const int Port = 8080;

    static void Main()
    {
        StartClient();
    }

    public static void StartClient()
    {
        try
        {
            using (TcpClient client = new TcpClient(ServerAddress, Port))
            using (NetworkStream stream = client.GetStream())
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            using (StreamWriter writer = new StreamWriter(stream, Encoding.UTF8) { AutoFlush = true })
            {
                Console.WriteLine("Connected to the game server as a single player.");

                while (true)
                {
                    Console.Write("Enter your guess (number) or 'exit' to quit: ");
                    string guess = Console.ReadLine();

                    if (guess.ToLower() == "exit")
                    {
                        Console.WriteLine("Exiting the game.");
                        break;
                    }

                    writer.WriteLine(guess);
                    string response = reader.ReadLine();
                    Console.WriteLine($"Server response: {response}");

                    if (response.Contains("Congratulations"))
                    {
                        Console.WriteLine("You won the game! Exiting...");
                        break;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Connection error: {ex.Message}");
        }
    }
}
