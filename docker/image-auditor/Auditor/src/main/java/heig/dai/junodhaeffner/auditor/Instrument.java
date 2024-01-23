package heig.dai.junodhaeffner.auditor;

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

    static public int count() {
        return Instrument.values().length;
    }
}
