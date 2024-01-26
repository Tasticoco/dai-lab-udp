package heig.dai.junodhaeffner.auditor;

import java.sql.Timestamp;

/**
 * Musician class, contains the uuid, the instrument and the last activity of a musician
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
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

    /**
     * Update the last activity of the musician to the current time
     */
    public void updateLastActivity() {
        lastActivity = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastActivity(){
        return lastActivity;
    }

    public String getUuid(){
        return uuid;
    }

    // Used for the json serialization
    public Instrument getInstrument(){
        return instrument;
    }

}