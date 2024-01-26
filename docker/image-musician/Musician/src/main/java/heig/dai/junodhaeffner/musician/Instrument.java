package heig.dai.junodhaeffner.musician;

/**
 * enum Instrument, contains the instruments and their sounds
 *
 * @author Arthur Junod, Edwin Haeffner
 * @date 26/01/2024
 */
public enum Instrument {
    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");
    private final String sound;

    Instrument(String s) {
        sound = s;
    }

    public String getSound() {
        return sound;
    }
    static public int count() {
        return Instrument.values().length;
    }
}
