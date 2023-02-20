package generator;

import java.util.Random;

public class NormalRandomGenerator implements IRandomGenerator{
    @Override
    public int generate() {
        double mean = 5000; // mean of the normal distribution
        double stdDev = 800; // standard deviation of the normal distribution
        Random rand = new Random();
        double randomNum = mean + stdDev * rand.nextGaussian();
        return (int) randomNum;
    }
}
