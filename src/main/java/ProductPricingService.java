public interface ProductPricingService {
    double price(String exchange, String ticker);
    double price(String exchange, String contactCode, int month, int year);
}
