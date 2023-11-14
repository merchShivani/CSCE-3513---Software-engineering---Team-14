import java.net.*;

public class LaserTagPlayerClient {
    public static void main(String[] args) 
    {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(7500);
            DatagramSocket sendSocket = new DatagramSocket();

            // Counter to track the number of times code 221 is received
            int code221Count = 0;
            
            // Indicate that the client has started.
            System.out.println("Client started...");

            // Display current thread information
            Thread curretnThread = Thread.currentThread();
            long threadId = curretnThread.getId();
            String threadName = curretnThread.getName();
            System.out.println("Current thread ID: " + threadId);
            System.out.println("Current thread name: " + threadName);
            // Receive and process data loop
            while (true) 
            {
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                receiveSocket.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                processReceivedData(receivedData);

                // Check if code 221 is received
                if (receivedData.equals("221")) 
                {
                    code221Count++;

                    // Check if code 221 has been received three times
                    if (code221Count == 3) 
                    {
                        System.out.println("CLient Closed. Game End.");
                        break; // Exit the loop if code 221 is received three times
                    }
                }
            }

            receiveSocket.close();
            sendSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processReceivedData(String receivedData) 
    {
        // Implement game logic here based on the received data format
        // For demonstration, simply print the received data
        System.out.println("Received: " + receivedData);
    }
}
