package utils;

import java.util.List;

public class BloatingUtils {

    public static double mean(List<Double> values) {
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.size();
        //return 17;
    }

    public static double variance(List<Double> values) {
        double mean = mean(values);
        double sum = 0.0;
        for (double v : values) {
            sum += Math.pow(v - mean, 2);
        }
        return sum / values.size();
    }

    public static double covariance(List<Double> x, List<Double> y) {
        double meanX = mean(x);
        double meanY = mean(y);
        double sum = 0.0;

        for (int i = 0; i < x.size(); i++) {
            sum += (x.get(i) - meanX) * (y.get(i) - meanY);
        }

        return sum / x.size();
    }

    public static double calculateK(List<Double> lengths, List<Double> fitnesses) {
        double cov = covariance(lengths, fitnesses);
        double var = variance(lengths);
        double k = var == 0 ? 0 : cov / var;
        //System.out.println("K: " + k);
        return k;
    }
}
