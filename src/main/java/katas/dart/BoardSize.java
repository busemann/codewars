package katas.dart;

class BoardSize {
    static int FULL_ANGLE = 360;
    static double BULLS_EYE = 12.70;
    static double BULL = 31.80;
    static double TRIPLE_INNER = 198;
    static double TRIPLE_OUTER = 214;
    static double DOUBLE_INNER = 324;
    static double DOUBLE_OUTER = 340;

    static double getRadius(double diameter) {
        return diameter / 2;
    }
}
