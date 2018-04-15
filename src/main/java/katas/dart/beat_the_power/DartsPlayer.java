package katas.dart.beat_the_power;

import katas.dart.PlayerStatistics;
import katas.dart.Segments;

public class DartsPlayer {
    private PlayerStatistics statistics;

    public DartsPlayer() {
        this.statistics = new PlayerStatistics();
    }

    public double[] getCoordinates(int score) {
        return new Segments().getCoordinates(score).toDoubleArray();
    }

    public PlayerStatistics getStatistics() {
        return this.statistics;
    }
}
