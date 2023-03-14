package generator;

import org.apache.commons.math3.distribution.LogNormalDistribution;

public class LognormalRandomGenerator implements IRandomGenerator {
    @Override
    public double generate() {
        double mu = 6.2146; // mean of the underlying normal distribution in original space
        double sigma = 0.2219; // standard deviation of the underlying normal distribution in original space
        double logLowerBound = Math.log(500); // lower bound of the desired range in log space
        double logUpperBound = Math.log(1000); // upper bound of the desired range in log space
        double logMu = Math.log(mu) - 0.5 * Math.log((sigma / mu) * (sigma / mu) + 1); // adjusted mean in log space
        double logSigma = Math.sqrt(Math.log((sigma / mu) * (sigma / mu) + 1)); // adjusted standard deviation in log space
        LogNormalDistribution lognormal = new LogNormalDistribution(logMu, logSigma);
        double randomNum = lognormal.sample();
        return Math.max(500, Math.min(1000, Math.exp(randomNum))); // convert back to original space and clamp to desired range
    }
}
