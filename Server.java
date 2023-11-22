import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server extends Thread
{
    DatagramSocket datagramSocket;
    DatagramSocket server;
    byte[] buffer = new byte[256];
    byte[] bufferClient = new byte[256];
    Model model;
    boolean runThread = true;
    public static volatile int threadInt;
    int transmitterID = 0;
    int hitPlayerID = 0;
    final ConcurrentLinkedQueue<String> serverToMain;
    final ConcurrentLinkedQueue<String> mainToServer;
    String messageFromMain;
	String messageToMain;

    public Server(DatagramSocket datagramSocket, ConcurrentLinkedQueue<String> mainToServer, ConcurrentLinkedQueue<String> serverToMain) throws SocketException
    {
        this.mainToServer = mainToServer;
        this.serverToMain = serverToMain;
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

                /* 
                String[] parts = messageFromClient.split(":");
                if (parts.length == 2) {
                    transmitterID = Integer.parseInt(parts[0]);
                    hitPlayerID = Integer.parseInt(parts[1]);
                    System.out.println("Transmit ID: " + transmitterID);
                    System.out.println("Hit ID: " + hitPlayerID);
                }
                */

                /* 
                if (hitPlayerID == 53 && transmitterID % 2 == 0)
                {
                    System.out.println("Red Base Score");
                }

                if (hitPlayerID == 43 && transmitterID % 2 != 0)
                {
                    System.out.println("Green Base Score");
                }
                */

                // Send Transmit ID to Main Thread/ Model
                serverToMain.add(messageFromClient);

                messageFromMain = mainToServer.poll();

                if (messageFromMain != null)
                {
                bufferClient = messageFromMain.getBytes();
                }

                datagramPacket = new DatagramPacket(bufferClient, bufferClient.length, inetAddress, port);
                datagramSocket.send(datagramPacket);            
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Program Error, Exit Now");
            }
        }
    }
}