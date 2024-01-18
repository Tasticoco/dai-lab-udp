package heig.dai.junodhaeffner;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;
class MulticastSender {
    final static String IPADDR = "239.255.22.5";
    final static int PORT = 9904;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {

        String message = "Hello everybody!";
        byte[] payload = message.getBytes(UTF_8);
        var dest_address = new InetSocketAddress(IPADDR, 44444);
        var packet = new DatagramPacket(payload,
                payload.length,
                dest_address);
        socket.send(packet);
    } catch(
    IOException ex)

    {
        System.out.println(ex.getMessage());
    }
}