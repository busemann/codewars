package katas.dart;

class Score {
    private String score;

    String get() {
        return this.score;
    }

    void set(String score) {
        this.score = score;
    }

    void setMiss() {
        this.score = "X";
    }

    void setBullsEye() {
        this.score = "DB";
    }

    void setBull() {
        this.score = "SB";
    }

    void isTriple() {
        this.score = "T" + this.score;
    }

    void isDouble() {
        this.score = "D" + this.score;
    }
}
