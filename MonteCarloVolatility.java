import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarloVolatility implements VolatilityCalculator {

    @Override
    public double computeVolatility(List<Double> prices) {

        if (prices == null || prices.size() < 2) {
            return 0.0;
        }

        ArrayList<Double> returns = new ArrayList<Double>();
        for (int index = 1; index < prices.size(); index++) {

            double previousPrice = prices.get(index - 1);
            double currentPrice = prices.get(index);
            double dailyReturn =(currentPrice - previousPrice) / previousPrice;

            returns.add(dailyReturn);
        }

        Random randomGenerator = new Random();
        ArrayList<Double> simulatedReturns = new ArrayList<Double>();

        for (int simulation = 0; simulation < 1000; simulation++) {

            int randomIndex = randomGenerator.nextInt(returns.size());

            simulatedReturns.add(returns.get(randomIndex));
        }

        double total = 0.0;

        for (int index = 0;
             index < simulatedReturns.size();
             index++) {

            total += simulatedReturns.get(index);
        }

        double mean = total / simulatedReturns.size();
        double squaredDifferenceTotal = 0.0;

        for (int index = 0;
             index < simulatedReturns.size();
             index++) {

            double difference =
                    simulatedReturns.get(index) - mean;

            squaredDifferenceTotal += difference * difference;
        }

        double variance = squaredDifferenceTotal /simulatedReturns.size();
        return Math.sqrt(variance);
    }
}
