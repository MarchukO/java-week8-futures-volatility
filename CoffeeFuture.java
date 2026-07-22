public class CoffeeFuture extends FutureContract {

    public CoffeeFuture(double underlyingPrice,
                        double optionPrice,
                        double timeToExpiration) {

        super("Coffee", underlyingPrice, optionPrice, timeToExpiration);

        this.volatilityCalculator = new MonteCarloVolatility();
    }

    public CoffeeFuture(CoffeeFuture other) {

        super(other);

        this.volatilityCalculator = new MonteCarloVolatility();
    }

    @Override
    public double computeImpliedVolatility() {

        System.out.println("Computing volatility for Coffee.");

        return computeVolatility(this.prices);
    }
}
