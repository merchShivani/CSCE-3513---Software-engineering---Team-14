import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public DatagramSocket datagramSocket;
    public InetAddress inetAddress;
    public byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try{
                String messageToSend = scanner.nextLine();
                buffer = messageToSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 7500);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("The server says you said: " + messageFromServer);    
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Program Error, Ending Now");
                break;
            }            
        }
    }

    public static void main (String[] args) throws SocketException, UnknownHostException
    {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client clinet = new Client(datagramSocket, inetAddress);
        System.out.println("Send datagram packets to a server");
        clinet.sendThenReceive();
    }
}
