package heig.dai.junodhaeffner.auditor;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.DatagramPacket;
import static java.nio.charset.StandardCharsets.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UDPMulticastRec implements Runnable{
    final static String IPADDRESS = "239.255.22.5";
    final static int PORT = 9904;

    public void run() {
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetSocketAddress group_address =  new InetSocketAddress(IPADDRESS, PORT);
            NetworkInterface netif = NetworkInterface.getByName("eth0");
            socket.joinGroup(group_address, netif);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while(true){
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength(), UTF_8);

                ObjectMapper objectMapper = new ObjectMapper();
                Musician musician = objectMapper.readValue(message, Musician.class);

                if(Auditor.musicians.containsKey(musician.getUuid())){
                    Auditor.musicians.get(musician.getUuid()).updateLastActivity();
                }else{
                    Auditor.musicians.put(musician.getUuid(), musician);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
