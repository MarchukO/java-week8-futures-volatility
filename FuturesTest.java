public class FuturesTest {

    public static void main(String[] args) {

        GoldFuture gold = new GoldFuture(2200.50, 130.15, 0.5);

        CopperFuture copper = new CopperFuture(4.25, 0.45, 0.25);

        CoffeeFuture coffee = new CoffeeFuture(200.50, 13.45, 0.45);
        gold.loadPricesFromFile("gold_prices.txt");
        copper.loadPricesFromFile("copper_prices.txt");
        coffee.loadPricesFromFile("coffee_prices.txt");

        System.out.println();
        System.out.println("FUTURES CONTRACTS");
        System.out.println("-----------------");

        FutureContract[] contracts = new FutureContract[3];

        contracts[0] = gold;
        contracts[1] = copper;
        contracts[2] = coffee;

        for (int index = 0;
             index < contracts.length;
             index++) {

            contracts[index].displayContract();
        }

        GoldFuture goldCopy = new GoldFuture(gold);

        System.out.println("COPIED GOLD CONTRACT");
        System.out.println("--------------------");

        goldCopy.displayContract();
    }
}
