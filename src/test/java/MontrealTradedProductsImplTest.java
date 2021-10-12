import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MontrealTradedProductsImplTest {
    Product product;
    MontrealTradedProductsImpl montrealTradedProductsImpl;
    ProductPricingService productPricingService;

    @BeforeEach
    void setup(){
        montrealTradedProductsImpl = new MontrealTradedProductsImpl();

        product = mock(Product.class);
        when(product.getId()).thenReturn("12");

        productPricingService = mock(ProductPricingService.class);
        when(productPricingService.price(anyString(),anyString())).thenReturn(1.0);
        when(productPricingService.price(anyString(),anyString(),anyInt(),anyInt())).thenReturn(2.0);

    }

    @Test
    void addNewProduct() throws ProductAlreadyRegisteredException {
        montrealTradedProductsImpl.addNewProduct(product);
        assertThrows(ProductAlreadyRegisteredException.class, ()-> montrealTradedProductsImpl.addNewProduct(product),
                "Duplicate products throw exception");
    }

    @Test
    void trade() throws ProductAlreadyRegisteredException{
        montrealTradedProductsImpl.addNewProduct(product);
        montrealTradedProductsImpl.trade(product,4);
    }

    @Test
    void totalTradeQuantityForDay() throws ProductAlreadyRegisteredException{
        montrealTradedProductsImpl.addNewProduct(product);
        montrealTradedProductsImpl.trade(product,4);

        Product product1 = mock(Product.class);
        when(product1.getId()).thenReturn("13");
        montrealTradedProductsImpl.addNewProduct(product1);
        montrealTradedProductsImpl.trade(product1, 5);

        assertEquals(9, montrealTradedProductsImpl.totalTradeQuantityForDay(),"Product traded is not valid.");
    }

    @Test
    void totalValueOfDaysTradedProducts() throws ProductAlreadyRegisteredException{
        Product stock = new Stock("14", "Dollars", "APPL", productPricingService);
        montrealTradedProductsImpl.addNewProduct(stock);
        montrealTradedProductsImpl.trade(stock,4);

        Product future = new Future("13", "Pounds", "GOOG", 5, 2021,productPricingService);
        montrealTradedProductsImpl.addNewProduct(future);
        montrealTradedProductsImpl.trade(future,5);

        assertEquals(14, montrealTradedProductsImpl.totalValueOfDaysTradedProducts(), "Total value of days traded product is not valid.");
    }
}