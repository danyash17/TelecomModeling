import generator.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    private static final int LENGTH = 10000;

    public static void main(String[] args) {

        BasicRandomGenerator basicRandomGenerator = new BasicRandomGenerator();
        PoissonRandomGenerator poissonRandomGenerator = new PoissonRandomGenerator();
        ExponentialRandomGenerator exponentialRandomGenerator = new ExponentialRandomGenerator();
        ErlangRandomGenerator erlangRandomGenerator = new ErlangRandomGenerator();
        NormalRandomGenerator normalRandomGenerator = new NormalRandomGenerator();
        WeibullRandomGenerator weibullRandomGenerator = new WeibullRandomGenerator();
        LognormalRandomGenerator lognormalRandomGenerator = new LognormalRandomGenerator();
        ParetoRandomGenerator paretoRandomGenerator = new ParetoRandomGenerator();

        showBar(normalRandomGenerator);
    }

    private static void showBar(IRandomGenerator generator) {
        int[] arr = new int[LENGTH];
        for (int i = 0; i< arr.length; i++){
            arr[i] = generator.generate();
        }
        int[] histogram = new int[arr.length]; // the input array contains values from 1 to 5
        // count occurrences of each value in the input array
        for (int i = 1; i < arr.length; i++) {
            histogram[arr[i]]++;
        }
        // create a bar chart to display the histogram
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < histogram.length; i++) {
            dataset.setValue(histogram[i], "Value", Integer.toString(i+1));
        }
        JFreeChart chart = ChartFactory.createBarChart("Histogram", "Value", "Count", dataset);
        ChartFrame frame = new ChartFrame("Histogram", chart);
        frame.setVisible(true);
        frame.setSize(600, 700);
    }
}
