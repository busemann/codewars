package katas.dart;

import java.util.Arrays;
import java.util.List;

public class Segments {
    private static List<String> segments = Arrays.asList("6", "13", "4", "18", "1", "20", "5", "12", "9", "14", "11", "8", "16", "7", "19", "3", "17", "2", "15", "10");

    public List<String> getAll() {
        return segments;
    }

    public int size() {
        return segments.size();
    }

    public Coordinates getCoordinates(int score) {
        double distance = 50;
        double x = distance * Math.cos(Math.toRadians(getMiddleDegree(score)));
        double y = distance * Math.sin(Math.toRadians(getMiddleDegree(score)));

        return new Coordinates(x, y);
    }

    private int getIndexOfScore(int score) {
        return this.getAll().indexOf(String.valueOf(score));
    }

    private double getMaxDegree(int score) {
        double minDegree = getMaxDegree(score);
        if (minDegree == 0) {
            return BoardSize.FULL_ANGLE - 9;  // TODO Calculate based on boardsize
        } else {
            return getMinDegree(score) + (9 * 2) - 1; // TODO Calculate based on boardsize
        }
    }

    private double getMinDegree(int score) {
        int scoreIndex = this.getIndexOfScore(score);
        if (scoreIndex == 0) {
            return 0;
        }
        return ((scoreIndex * 2) - 1) * 9; // TODO Calculate based on boardsize
    }

    private double getMiddleDegree(int score) {
        return (getMaxDegree(score) - getMinDegree(score)) / 2;
    }
}
