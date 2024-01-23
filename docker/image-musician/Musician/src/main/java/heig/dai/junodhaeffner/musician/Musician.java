package heig.dai.junodhaeffner.musician;


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
