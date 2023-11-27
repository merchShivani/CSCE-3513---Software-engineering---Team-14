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
    String sendToClients;

    boolean serverListen = false;
    int endCount = 0;

    public Server(DatagramSocket datagramSocket, ConcurrentLinkedQueue<String> mainToServer, ConcurrentLinkedQueue<String> serverToMain) throws SocketException
    {
        this.mainToServer = mainToServer;
        this.serverToMain = serverToMain;
        this.datagramSocket = datagramSocket;
        datagramSocket.setBroadcast(true);
        start();
    }


 public void run()
    {
        while (runThread)
        {
            messageFromMain = mainToServer.poll();
            if (messageFromMain == "202")
            {
                sendToClients = messageFromMain;
                bufferClient = sendToClients.getBytes();
                try {
                DatagramPacket datagramPacket = new DatagramPacket(bufferClient, bufferClient.length, InetAddress.getByName("127.0.0.1"), 7501);
                datagramSocket.send(datagramPacket);
                } catch (Exception e) {
                   System.out.println("Send Failed");
                }
                serverListen = true;
            }
            
            if (serverListen)
            {
            try{

                messageFromMain = mainToServer.poll();
                if (messageFromMain == "221")
                {
                endCount += 1;
                sendToClients = messageFromMain;
                bufferClient = sendToClients.getBytes();
                try {
                DatagramPacket datagramPacket = new DatagramPacket(bufferClient, bufferClient.length, InetAddress.getByName("127.0.0.1"), 7501);
                datagramSocket.send(datagramPacket);
                } catch (Exception e) {
                   System.out.println("Send Failed");
                }
                if (endCount == 3)
                {
                serverListen = false;
                endCount = 0;
                }
                }
                
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String messageFromClient = new String(datagramPacket.getData(), 0,datagramPacket.getLength());
                String[] parts = messageFromClient.split(":");
                if (parts.length == 2) 
                {
                    transmitterID = Integer.parseInt(parts[0]);
                    hitPlayerID = Integer.parseInt(parts[1]);
                }

                sendToClients = String.valueOf(hitPlayerID);
                bufferClient = sendToClients.getBytes();

                // Send Transmit ID to Main Thread/ Model
                serverToMain.add(messageFromClient);
                datagramPacket = new DatagramPacket(bufferClient, bufferClient.length, InetAddress.getByName("127.0.0.1"), 7501);
                datagramSocket.send(datagramPacket);

            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Program Error, Exit Now");
            }
            }
        }
    }
}
