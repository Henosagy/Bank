import java.util.*;

public class MontrealTradedProductsImpl implements MontrealTradedProducts{
    private List<Product> productList = new ArrayList<>();
    private Map<Product,Integer> tradedProductMap = new HashMap<>();


    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        Optional<Product> optionalProduct = productList
                .stream().
                filter(p -> p.getId().equals(product.getId()))
                .findFirst();

        if(optionalProduct.isPresent()){
            throw new ProductAlreadyRegisteredException("Product with ID " +product.getId() +" already exists!");
        }
        else{
            this.productList.add(product);
        }

    }

    @Override
    public void trade(Product product, int quantity) {
        if(!productList.contains(product)){
            return;
        }

        if(tradedProductMap.containsKey(product)){
            int previousQuantity = tradedProductMap.get(product);
            tradedProductMap.put(product, previousQuantity + quantity);
        }
        else {
            tradedProductMap.put(product, quantity);
        }

    }

    @Override
    public int totalTradeQuantityForDay() {
        return this.tradedProductMap.values().stream()
                .mapToInt(q -> q)
                .reduce((a,b) -> a+b).orElse(0);
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        return this.tradedProductMap.keySet().stream()
                .mapToDouble(q -> q.getPrice()*tradedProductMap.get(q))
                .reduce((a,b) -> a+b).orElse(0);
    }
}
