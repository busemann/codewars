package katas.sortable_poker_hands;

public class Card {
    String faceValue;
    String suite;
    boolean isAceLow = false;

    Card(String card) {
        String[] c = card.split("");
        this.faceValue = c[0];
        this.suite = c[1];
    }

    public void setAceLow(boolean isLow) {
        this.isAceLow = isLow;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public String getSuite() {
        return suite;
    }

    public int getRank() {
        switch (this.faceValue) {
            case "T": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return this.isAceLow ? 1 : 14;
            default: return Integer.parseInt(faceValue);
        }
    }

//    @Override
//    public String toString() {
//        return faceValue + suite + " - isAceLow ? " + this.isAceLow;
//    }
}
