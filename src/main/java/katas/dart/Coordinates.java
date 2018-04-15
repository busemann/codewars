package katas.dart;

public class Coordinates {
    double x;
    double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] toDoubleArray() {
        return new double[]{x, y};
    }
}
