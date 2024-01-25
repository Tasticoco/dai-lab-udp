package heig.dai.junodhaeffner.auditor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;

public class Musician {
    private final Instrument instrument;
    private final String uuid;
    private Timestamp lastActivity;

    public Musician() {
        this.uuid = null;
        this.instrument = null;
        lastActivity = null;
    }

    public Musician(String uuid, String sound) {
        this.uuid = uuid;
        this.instrument = Instrument.getFromSound(sound);
        lastActivity = new Timestamp(System.currentTimeMillis());
    }

    public void updateLastActivity() {
        lastActivity = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastActivity(){
        return lastActivity;
    }

    public String getUuid(){
        return uuid;
    }

}