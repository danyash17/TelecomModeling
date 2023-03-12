package generator;

import org.apache.commons.math3.distribution.WeibullDistribution;

public class WeibullRandomGenerator implements IRandomGenerator{
    @Override
    public double generate() {
        double shape = 3; // shape parameter of the Weibull distribution
        double scale = 0.5; // scale parameter of the Weibull distribution
        WeibullDistribution weibull = new WeibullDistribution(shape, scale);
        double randomNum = weibull.sample();
        randomNum %= 1.0;
        return randomNum;
    }
}
