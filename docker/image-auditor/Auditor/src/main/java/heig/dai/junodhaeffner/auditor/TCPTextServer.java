package heig.dai.junodhaeffner.auditor;

import java.io.*;
import java.net.*;

import static java.nio.charset.StandardCharsets.*;

import java.sql.Timestamp;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TCPTextServer class is a thread that will listen to the TCP port 2205,
 * send the list of active musicians to the client and then close the connection
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
public class TCPTextServer implements Runnable {

    static final int PORT = 2205;

    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                try (Socket socket = serverSocket.accept();

                     // Create a writer to send the list of musicians to the client
                     var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {

                    // Remove the musicians that have not played for more than 5 seconds
                    Iterator<Musician> iterator = Auditor.musicians.values().iterator();
                    while (iterator.hasNext()) {
                        Musician m = iterator.next();
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        if ((now.getTime() - m.getLastActivity().getTime()) > 5000) {
                            iterator.remove();
                        }
                    }

                    out.write(objectMapper.writeValueAsString(Auditor.musicians.values()));
                    out.flush();

                } catch (IOException e) {
                    System.out.println("Server: socket ex.: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }

}
