package heig.dai.junodhaeffner.auditor;

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

    /**
     * Get the instrument from the sound it makes
     * @param s the sound
     * @return the instrument
     */
    static public Instrument getFromSound(String s) {
        for (Instrument i : Instrument.values()) {
            if (i.getSound().equals(s)) {
                return i;
            }
        }
        return null;
    }

    public String getSound() {
        return sound;
    }
}
