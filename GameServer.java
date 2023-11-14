import java.net.*;
import java.util.Arrays;

public class GameServer {
    public static void main(String[] args) {
        try {
            int broadcastPort = 7500;
            int receivePort = 7501;

            // Create a DatagramSocket for broadcasting
            DatagramSocket broadcastSocket = new DatagramSocket();
            broadcastSocket.setBroadcast(true);

            // Create a DatagramSocket for receiving
            DatagramSocket receiveSocket = new DatagramSocket(receivePort);

            // Broadcast the start code (202) after a countdown timer finishes
            int startCode = 202;
            byte[] startCodeData = Integer.toString(startCode).getBytes();
            DatagramPacket startPacket = new DatagramPacket(startCodeData, startCodeData.length, InetAddress.getByName("255.255.255.255"), broadcastPort);
            broadcastSocket.send(startPacket);

            while (true) {
                // Create a buffer to hold received data
                byte[] receiveData = new byte[1024];

                // Create a DatagramPacket to store the received data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive data
                receiveSocket.receive(receivePacket);

                // Convert the received data to a string
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Split the received message by ":" to separate player IDs
                String[] parts = receivedMessage.split(":");
                if (parts.length == 2) {
                    int transmitterID = Integer.parseInt(parts[0]);
                    int hitPlayerID = Integer.parseInt(parts[1]);

                    System.out.println(transmitterID);
                    System.out.println(hitPlayerID);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
