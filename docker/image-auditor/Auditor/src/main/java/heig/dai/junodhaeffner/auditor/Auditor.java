package heig.dai.junodhaeffner.auditor;

import java.util.HashMap;

/**
 * This class is the main class of the auditor. It starts the TCPTextServer and
 * the UDPMulticastRec threads.
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
public class Auditor {

    public static HashMap<String, Musician> musicians = new HashMap<>();
    public static void main(String[] args) {
        // Create an instance of TCPTextServer
        TCPTextServer tcpTextServer = new TCPTextServer();
        // Start a new thread with the TCPTextServer instance
        new Thread(tcpTextServer).start();

        UDPMulticastRec udpMulticastRec = new UDPMulticastRec();
        new Thread(udpMulticastRec).start();

    }

}