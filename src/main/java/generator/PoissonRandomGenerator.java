package generator;
import org.apache.commons.math3.distribution.PoissonDistribution;


public class PoissonRandomGenerator implements IRandomGenerator{
    @Override
    public int generate() {
        int lambda = 5000; // mean of the Poisson distribution
        PoissonDistribution poisson = new PoissonDistribution(lambda);
        int randomNum = poisson.sample();
        return randomNum;
    }
}
