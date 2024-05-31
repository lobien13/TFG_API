package intra.intranet2copia.Entidades;

public class Range {
    private final double lowerBound;
    private final double upperBound;

    public Range(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }


    public boolean isWithinRange(double value) {

        return value >= lowerBound && value <= upperBound;
    }
}