package heig.dai.junodhaeffner.musician;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.nio.charset.StandardCharsets.*;
class MulticastSender {
    final static String IPADDR = "239.255.22.5";
    final static int PORT = 9904;

    public static void main(String[] args) throws JsonProcessingException {

        if(args == null || args.length == 0) {
            System.out.println("Please provide an instrument");
            System.exit(1);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        Instrument instrument = Instrument.valueOf(args[0]);
        Musician musician = new Musician(instrument);

        var json = objectMapper.writeValueAsString(musician);

        //Printing what we send
        System.out.println(json);

        try (DatagramSocket socket = new DatagramSocket()) {

            byte[] payload = json.getBytes(UTF_8);
            var dest_address = new InetSocketAddress(IPADDR, PORT);
            var packet = new DatagramPacket(payload,
                    payload.length,
                    dest_address);

            // Sending a packet every second
            while (true){
                System.out.println(json);
                socket.send(packet);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (
                IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}