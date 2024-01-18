package heig.dai.junodhaeffner.auditor;

import java.io.*;
import java.net.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static class MulticastReceiver {
        final static String IPADDRESS = "239.1.2.3";
        final static int PORT = 44444;
        public static void main(String[] args) {
            try (MulticastSocket socket = new MulticastSocket(PORT)) {
                var group_address = new InetSocketAddress(IPADDRESS, PORT);
                NetworkInterface netif = NetworkInterface.getByName("eth0");
                socket.joinGroup(group_address, netif);
                byte[] buffer = new byte[1024];
                var packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0,
                        packet.getLength(), UTF_8);
                System.out.println("Received message: " + message +
                        " from " + packet.getAddress() +
                        ", port " + packet.getPort());
                socket.leaveGroup(group_address, netif);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } } }

    static class TextualTCPServer {
        public static void main(String args[]) {
            try (ServerSocket serverSocket = new ServerSocket(1234)) {
                while (true) {
                    try (Socket socket = serverSocket.accept();
                         var in = new BufferedReader(
                                 new InputStreamReader(
                                         socket.getInputStream(), UTF_8));
                         var out = new BufferedWriter(
                                 new OutputStreamWriter(
                                         socket.getOutputStream(), UTF_8))){
                        String line;
                        while ((line = in.readLine()) != null) {
                            out.write(line + "\n");
                            out.flush();
                        }
                    } catch (IOException e) {
                        System.out.println("Server: socket ex.: " + e);
                    } }
            } catch (IOException e) {
                System.out.println("Server: server socket ex.: " + e);
            } } }
}