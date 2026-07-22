import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FutureContract
        implements VolatilityCalculator {

    private String name;
    private double underlyingPrice;
    private double optionPrice;
    private double timeToExpiration;

    protected ArrayList<Double> prices;
    protected VolatilityCalculator volatilityCalculator;
    public FutureContract(String name,
                          double underlyingPrice,
                          double optionPrice,
                          double timeToExpiration) {

        this.name = name;
        this.underlyingPrice = underlyingPrice;
        this.optionPrice = optionPrice;
        this.timeToExpiration = timeToExpiration;

        this.prices = new ArrayList<Double>();
    }

    public FutureContract(FutureContract other) {

        this.name = other.name;
        this.underlyingPrice = other.underlyingPrice;
        this.optionPrice = other.optionPrice;
        this.timeToExpiration = other.timeToExpiration;

        this.prices = new ArrayList<Double>(other.prices);
    }

    public void loadPricesFromFile(String filename) {

        prices.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.equals("")) {
                    double price = Double.parseDouble(line);
                    prices.add(price);
                }
            }

            reader.close();

            System.out.println("Prices loaded from " + filename);
        }
        catch (IOException exception) {

            System.out.println("Error reading file: " + filename);
        }
        catch (NumberFormatException exception) {

            System.out.println("The file contains an invalid price.");
        }
    }

    @Override
    public double computeVolatility(
            List<Double> priceList) {

        return volatilityCalculator
                .computeVolatility(priceList);
    }
    public abstract double computeImpliedVolatility();
    public void displayContract() {

        System.out.println("Contract name: " + this.name);

        System.out.println("Underlying price: $"+ this.underlyingPrice);

        System.out.println("Option price: $"+ this.optionPrice);

        System.out.println("Time to expiration: "+ this.timeToExpiration+ " years");

        System.out.println("Historical prices loaded: " + this.prices.size());

        System.out.println("Volatility: "+ computeImpliedVolatility());

        System.out.println();
    }
}
