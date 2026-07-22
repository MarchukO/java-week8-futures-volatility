public class CopperFuture extends FutureContract {

    public CopperFuture(double underlyingPrice,
                        double optionPrice,
                        double timeToExpiration) {

        super("Copper", underlyingPrice, optionPrice, timeToExpiration);

        this.volatilityCalculator = new MonteCarloVolatility();
    }

    public CopperFuture(CopperFuture other) {

        super(other);

        this.volatilityCalculator = new MonteCarloVolatility();
    }
    @Override
    public double computeImpliedVolatility() {

        System.out.println("Computing volatility for Copper.");

        return computeVolatility(this.prices);
    }
}
