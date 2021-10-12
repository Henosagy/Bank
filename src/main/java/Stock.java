public class Stock extends Product {
    private String exchange;
    private String ticker;
    private ProductPricingService productPricingService;

    public Stock(String id, String exchange, String ticker, ProductPricingService productPricingService) {
        super(id);
        this.exchange = exchange;
        this.ticker = ticker;
        this.productPricingService = productPricingService;
    }

    @Override
    public double getPrice() {
        return this.productPricingService.price(this.exchange,this.ticker);
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "exchange='" + exchange + '\'' +
                ", ticker='" + ticker + '\'' +
                '}';
    }
}
