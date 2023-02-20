package generator;

import org.apache.commons.math3.distribution.GammaDistribution;

public class ErlangRandomGenerator implements IRandomGenerator{

    @Override
    public int generate() {
        int k = 3; // shape parameter of the gamma distribution
        double lambda = 200; // scale parameter of the gamma distribution
        GammaDistribution gamma = new GammaDistribution(k, lambda);
        double randomNum = gamma.sample();
        return (int) randomNum;
    }
}
