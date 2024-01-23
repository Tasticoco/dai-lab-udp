package heig.dai.junodhaeffner.auditor;

import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TCPTextServer implements Runnable{

    static final int PORT = 2205;
    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                     var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {
                    while (in.readLine() != null) {
                        for(Musician m : Auditor.musicians.values()){
                            Timestamp now = new Timestamp(System.currentTimeMillis());
                            if((now.getTime() - m.getLastActivity().getTime()) > 5000){
                                Auditor.musicians.remove(m.getUuid());
                            }

                        }
                        out.write(objectMapper.writeValueAsString(Auditor.musicians.values()));
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Server: socket ex.: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }

}
