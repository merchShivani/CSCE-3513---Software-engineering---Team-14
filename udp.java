import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class udp {
    private DatagramSocket socketReceive;
    private DatagramSocket socketBroadcast;

    private static final String URL_LOCALHOST = "localhost";
    private static final int PORT_SOCKET_RECEIVE = 7501;
    private static final int PORT_SOCKET_BROADCAST = 7500;
    private static final int SOCKET_BUFFER_SIZE = 1024;

    public udp() {
        try {
            // Create UDP sockets
            socketReceive = new DatagramSocket(PORT_SOCKET_RECEIVE);
            socketBroadcast = new DatagramSocket();

            // Set timeout for receiving socket
            socketReceive.setSoTimeout(100); // Stop trying after 0.1 seconds to run other code

            // Bind receive UDP to localhost:7501
            socketReceive
                    .bind(new java.net.InetSocketAddress(InetAddress.getByName(URL_LOCALHOST), PORT_SOCKET_RECEIVE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] tryReceive() {
        try {
            // Receive any data that might have come in
            byte[] receiveData = new byte[SOCKET_BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socketReceive.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] parts = receivedMessage.split(":");

            if (parts.length == 2) {
                int idTransmit = Integer.parseInt(parts[0]);
                int idHit = Integer.parseInt(parts[1]);

                return new int[] { idTransmit, idHit };
            }
        } catch (SocketTimeoutException e) {
            // No data has come in, try again
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void broadcast(int message) {
        try {
            // Broadcast a message to all devices on the broadcast UDP channel
            String messageStr = String.valueOf(message);
            byte[] sendData = messageStr.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData,
                    sendData.length,
                    InetAddress.getByName(URL_LOCALHOST),
                    PORT_SOCKET_BROADCAST);

            socketBroadcast.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
