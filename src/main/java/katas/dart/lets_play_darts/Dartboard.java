package katas.dart.lets_play_darts;

import katas.dart.Coordinates;
import katas.dart.Hit;

class Dartboard {
    String getScore(double x, double y) {
        return new Hit(new Coordinates(x, y)).getScore();
    }
}
