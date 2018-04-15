package katas.dart;

import katas.dart.beat_the_power.DartsPlayer;

public class Playground {
    DartsPlayer opponent;
    DartsPlayer player;

    public Playground(DartsPlayer player) {
        this.player = player;
        this.opponent = new DartsPlayer();
    }

    public void nextLeg() {
        int dartsToThrow = 3;
        for (int i = 1; i <= dartsToThrow; i++) {
            String nextScore = getNextScore(); // Calculate next score
            // Throw
            // Update statistics
            // Check victory
        }
    }

    public PlayerStatistics getPlayerStatistics() {
        return this.player.getStatistics();
    }

    public PlayerStatistics getOpponentStatistics() {
        return this.opponent.getStatistics();
    }

    private String getNextScore() {
        return "";
    }
}
