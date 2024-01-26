package heig.dai.junodhaeffner.musician;

/**
 * Musician class, contains the uuid and the sound that the musician makes
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
public class Musician {
    private final String uuid;
    private final String sound;

    public Musician(Instrument instrument) {
        uuid = java.util.UUID.randomUUID().toString();
        sound = instrument.getSound();
    }

    public String getUuid() {
        return uuid;
    }

    public String getSound() {
        return sound;
    }

}
