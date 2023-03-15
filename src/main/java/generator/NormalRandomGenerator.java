package generator;

import java.util.Random;

public class NormalRandomGenerator implements IRandomGenerator{
    @Override
    public double generate() {
        double mean = 100; // mean of the normal distribution
        double stdDev = 50; // standard deviation of the normal distribution
        Random rand = new Random();
        double randomNum = mean + stdDev * rand.nextGaussian();
        return randomNum;
    }
}
