package generator;

import org.apache.commons.math3.distribution.WeibullDistribution;

public class WeibullRandomGenerator implements IRandomGenerator{
    @Override
    public double generate() {
        double shape = 15; // shape parameter of the Weibull distribution
        double scale = 5000; // scale parameter of the Weibull distribution
        WeibullDistribution weibull = new WeibullDistribution(shape, scale);
        double randomNum = weibull.sample();
        return randomNum;
    }
}
