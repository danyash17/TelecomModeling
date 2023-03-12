package generator;

import java.util.Random;

public class ExponentialRandomGenerator implements IRandomGenerator {
    @Override
    public double generate() {
        double temp = 0.000000001; // bigger chance
        double lambda = 0.000000003; // rate parameter of the exponential distribution
        Random rand = new Random();
        double randomNum = -Math.log(1 - rand.nextDouble()) / lambda;
        randomNum /= Integer.MAX_VALUE;
        randomNum %= 1.0;
        return randomNum;
    }
}
