import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Server extends Thread
{
    DatagramSocket datagramSocket;
    DatagramSocket server;
    byte[] buffer = new byte[256];
    Model model;

    int value = 0;

    boolean runThread = true;

    String currentMessage = "No";

    public Server(DatagramSocket datagramSocket, Model model) throws SocketException
    {
        this.datagramSocket = datagramSocket;
        start();
    }


 public void run()
    {
        while (runThread)
        {
            try{
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0,datagramPacket.getLength());

                System.out.println("message from client: " + messageFromClient);

                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
                currentMessage = messageFromClient;

                value = Integer.valueOf(messageFromClient);
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Program Error, Exit Now");
            }
        }
    }

    public void printValue()
    {
        model.serverInt = value;
    }

}