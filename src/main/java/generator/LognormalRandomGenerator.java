package generator;

import org.apache.commons.math3.distribution.LogNormalDistribution;

public class LognormalRandomGenerator implements IRandomGenerator{
    @Override
    public int generate() {
        double mu = 5; // mean of the underlying normal distribution in log space
        double sigma = 1; // standard deviation of the underlying normal distribution in log space
        LogNormalDistribution lognormal = new LogNormalDistribution(mu, sigma);
        double randomNum = lognormal.sample();
        return (int) randomNum;
    }
}
