package generator;

import java.util.Random;

public class ParetoRandomGenerator implements IRandomGenerator{
    @Override
    public double generate() {
        double shape = 10; // shape parameter of the Pareto distribution
        double scale = 3000; // scale parameter of the Pareto distribution
        Random rand = new Random();
        double randomNum = scale * Math.pow(rand.nextDouble(), -1.0/shape);
        return randomNum;
    }
}
