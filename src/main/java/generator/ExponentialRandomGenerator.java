package generator;

import java.util.Random;

public class ExponentialRandomGenerator implements IRandomGenerator{
    @Override
    public int generate() {
        double lambda = 0.001; // rate parameter of the exponential distribution
        Random rand = new Random();
        double randomNum = -Math.log(1 - rand.nextDouble()) / lambda;
        return (int) randomNum;
    }
}
