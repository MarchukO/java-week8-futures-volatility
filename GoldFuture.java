public class GoldFuture extends FutureContract {

    public GoldFuture(double underlyingPrice,
                      double optionPrice,
                      double timeToExpiration) {

        super("Gold", underlyingPrice, optionPrice, timeToExpiration);

        this.volatilityCalculator = new MonteCarloVolatility();
    }

    public GoldFuture(GoldFuture other) {

        super(other);

        this.volatilityCalculator = new MonteCarloVolatility();
    }

    @Override
    public double computeImpliedVolatility() {

        System.out.println("Computing volatility for Gold.");

        return computeVolatility(this.prices);
    }
}
