package checker;

import java.util.Arrays;

public class UniformityChecker implements IChecker {

    public boolean check(double[] data) {
        Arrays.sort(data);
        double n = data.length;
        double D = 0.0;
        for (int i = 0; i < n; i++) {
            double diff1 = (i + 1) / n - data[i];
            double diff2 = data[i] - i / n;
            D = Math.max(D, Math.max(diff1, diff2));
        }
        double ks = Math.sqrt(n) * D;
        double criticalValue = 1.36 / Math.sqrt(n);
        return ks < criticalValue;
    }
}
