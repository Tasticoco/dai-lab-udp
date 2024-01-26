package heig.dai.junodhaeffner.auditor;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.DatagramPacket;

import static java.nio.charset.StandardCharsets.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * UDPTextServer class is a thread that will listen to the UDP port 9904 and add
 * the musicians it hears to the list of musicians.
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
public class UDPMulticastRec implements Runnable {
    final static String IPADDRESS = "239.255.22.5";
    final static int PORT = 9904;
    private static final String NETWORK_INTERFACE = "eth0";

    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();

        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetSocketAddress group_address = new InetSocketAddress(IPADDRESS, PORT);
            NetworkInterface netif = NetworkInterface.getByName(NETWORK_INTERFACE);
            socket.joinGroup(group_address, netif);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {

                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength(), UTF_8);
                try {
                    JsonNode jsonNode = objectMapper.readTree(message);
                    Musician musician = new Musician(jsonNode.get("uuid").asText(), jsonNode.get("sound").asText());

                    if (Auditor.musicians.containsKey(musician.getUuid())) {
                        Auditor.musicians.get(musician.getUuid()).updateLastActivity();
                    } else {
                        Auditor.musicians.put(musician.getUuid(), musician);
                    }

                } catch (Exception e) {
                    System.out.println("Error while parsing json");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
