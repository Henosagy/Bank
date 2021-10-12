
public interface MontrealTradedProducts {
    void addNewProduct(Product product) throws ProductAlreadyRegisteredException;

    void trade(Product product, int quantity);

    int totalTradeQuantityForDay();

    double totalValueOfDaysTradedProducts();
}
