import java.net.*;

public class LaserTagGameServer 
{
    public static void main(String[] args) 
    {
        try 
        {
            DatagramSocket receiveSocket = new DatagramSocket(7501);
            DatagramSocket sendSocket = new DatagramSocket();

            // Counter to track the number of times code 221 is received
            int code221Count = 0;

            // Game loop
            while (true) 
            {
                //Indicate that the server has started
                System.out.println("Server Start...");

                // Display current thread information
                Thread currentThread = Thread.currentThread();
                long threadId = currentThread.getId();
                String threadName = currentThread.getName();
                System.out.println("Current thread ID: " + threadId);
                System.out.println("Current thread name: " + threadName);

                // Game start countdown timer (assuming 10 seconds for demonstration)
                Thread.sleep(10000);

                // Transmit code 202 after countdown
                System.out.println("Game Start...");
                transmitCode(sendSocket, "localhost", 7500, 202);

                // Game logic goes here

                // Game end logic
                for (int i = 0; i < 3; i++) 
                {
                    transmitCode(sendSocket, "localhost", 7500, 221);
                    code221Count++;

                    // Check if code 221 has been received three times

                }
                if (code221Count == 3) 
                {
                    System.out.println("Server Closed. Game End.");
                    break; // Exit the loop if code 221 is received three times
                }

                // Add a delay or sleep between game rounds if needed

                // Clear the console or perform any necessary cleanup for the next round
            }

            receiveSocket.close();
            sendSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transmitCode(DatagramSocket socket, String ipAddress, int port, int code) throws Exception 
    {
        String data = String.valueOf(code);
        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(),
                InetAddress.getByName(ipAddress), port);
        socket.send(packet);
    }
}
