package checker;

public class IndependenceChecker implements IChecker{

    public boolean check(double[] data) {
        int n = data.length;
        int k = (int) Math.sqrt(n);
        int[] frequencies = new int[k];
        for (int i = 0; i < n; i++) {
            int category = (int) (data[i] * k);
            frequencies[category]++;
        }
        double[] expectedValues = new double[k];
        double chiSquared = 0.0;
        for (int i = 0; i < k; i++) {
            expectedValues[i] = (double) n / k;
            double deviation = frequencies[i] - expectedValues[i];
            chiSquared += deviation * deviation / expectedValues[i];
        }
        int degreesOfFreedom = k - 1;
        double criticalValue = 14.07;
        return chiSquared < criticalValue;
    }
}