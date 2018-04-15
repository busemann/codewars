package katas.dart;

public class Hit {
    private Coordinates coords;
    private Segments segments;

    public Hit(Coordinates coords) {
        this.coords = coords;
        this.segments = new Segments();
    }

    public String getScore() {
        Score score = new Score();
        if (isMiss()) {
            score.setMiss();
        } else if (isBullsEye()) {
            score.setBullsEye();
        } else if (isBull()) {
            score.setBull();
        } else {
            int index = (int) Math.floor(getAngle() / ((BoardSize.FULL_ANGLE / this.segments.size()) / 2));
            if (index == (this.segments.size() * 2) - 1) {
                index = 0;
            } else {
                index = index % 2 == 0 ? index / 2 : (int) (index - Math.floor(index / 2));
            }
            score.set(this.segments.getAll().get(index));
            if (isTriple()) {
                score.isTriple();
            } else if (isDouble()) {
                score.isDouble();
            }
        }
        return score.get();
    }

    private double getDistanceFromCenter() {
        return Math.sqrt(Math.pow(Math.abs(coords.x), 2) + Math.pow(Math.abs(coords.y), 2));
    }

    private double getAngle() {
        double angle = Math.toDegrees(Math.atan2(coords.y, coords.x));
        return angle < 0 ? angle + BoardSize.FULL_ANGLE : angle;
    }

    private boolean isMiss() {
        return getDistanceFromCenter() > BoardSize.getRadius(BoardSize.DOUBLE_OUTER);
    }

    private boolean isBull() {
        return getDistanceFromCenter() > BoardSize.getRadius(BoardSize.BULLS_EYE) &&
                getDistanceFromCenter() < BoardSize.getRadius(BoardSize.BULL);
    }

    private boolean isBullsEye() {
        return getDistanceFromCenter() < BoardSize.getRadius(BoardSize.BULLS_EYE);
    }

    private boolean isTriple() {
        return getDistanceFromCenter() > BoardSize.getRadius(BoardSize.TRIPLE_INNER) &&
                getDistanceFromCenter() < BoardSize.getRadius(BoardSize.TRIPLE_OUTER);
    }

    private boolean isDouble() {
        return getDistanceFromCenter() > BoardSize.getRadius(BoardSize.DOUBLE_INNER) &&
                getDistanceFromCenter() < BoardSize.getRadius(BoardSize.DOUBLE_OUTER);
    }
}
