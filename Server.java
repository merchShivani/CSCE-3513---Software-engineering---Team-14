import java.io.IOException;
import java.io.PipedOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.PipedInputStream;



public class Server extends Thread
{
    DatagramSocket datagramSocket;
    DatagramSocket server;
    byte[] buffer = new byte[256];
    Model model;
    boolean runThread = true;
    public static volatile int threadInt;
    int transmitterID = 0;
    int hitPlayerID = 0;
    final PipedOutputStream senderOut = new PipedOutputStream();
    final PipedOutputStream recieverOut = new PipedOutputStream();

    boolean wakeUp = false;


    public Server(DatagramSocket datagramSocket, PipedInputStream senderIn, PipedInputStream recieverIn) throws SocketException
    {
        try {
			senderOut.connect(senderIn);
            recieverOut.connect(recieverIn);
		} catch (IOException e) {
			e.printStackTrace();
        }

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

                String[] parts = messageFromClient.split(":");
                if (parts.length == 2) {
                    transmitterID = Integer.parseInt(parts[0]);
                    hitPlayerID = Integer.parseInt(parts[1]);
                    System.out.println("Transmit ID: " + transmitterID);
                    System.out.println("Hit ID: " + hitPlayerID);
                }

                if (hitPlayerID == 53 && transmitterID % 2 == 0)
                {
                    System.out.println("Red Base Score");
                }

                if (hitPlayerID == 43 && transmitterID % 2 != 0)
                {
                    System.out.println("Green Base Score");
                }

                // Send Transmit ID to Main Thread/ Model
                senderOut.write(transmitterID);
                recieverOut.write(hitPlayerID);
                
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
            
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Program Error, Exit Now");
            }
        }
    }
}