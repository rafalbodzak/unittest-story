package pl.com.rbo.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pl.com.rbo.order.ProductKind.*;
import static org.junit.Assert.*;

public class OrderTestStory {

    private Order order;
    private List<Product> products = new ArrayList<Product>();
    private Product cheapestProduct;
    private Double expectedRegularPrice;
    private LocalDate currentDate = LocalDate.now();
    private Boolean returnAccepted = false;

    private OrderTestStory(){}

    public static OrderTestStory Go_shopping(){
        return new OrderTestStory();
    }

    public OrderTestStory get_one_item(){
        Product tshirt = new Product(TSHIRT, 40d);
        products.add(tshirt);
        cheapestProduct = tshirt;
        expectedRegularPrice = tshirt.getPrice();
        return this;
    }

    public OrderTestStory get_anything(){
        return get_one_item();
    }

    public OrderTestStory get_two_items(){
        Product tshirt = new Product(TSHIRT, 40d);
        Product jeans = new Product(JEANS, 100d);
        products.add(tshirt);
        products.add(jeans);
        expectedRegularPrice = tshirt.getPrice() + jeans.getPrice();
        cheapestProduct = tshirt;
        return this;
    }

    public OrderTestStory get_three_items(){
        Product tshirt = new Product(TSHIRT, 40d);
        Product jeans = new Product(JEANS, 100d);
        Product socks = new Product(SOCKS, 10d);
        products.add(tshirt);
        products.add(jeans);
        products.add(socks);
        expectedRegularPrice = tshirt.getPrice() + jeans.getPrice() + socks.getPrice();
        cheapestProduct = socks;
        return this;
    }

    public OrderTestStory buy(){
        order = new Order();
        order.addItems(products);
        order.finalize(currentDate);
        return this;
    }

    public OrderTestStory wait_more_than_return_period(){
        currentDate = currentDate.plusDays(Order.RETURN_PERIOD + 1);
        return this;
    }

    public OrderTestStory wait_less_than_return_period(){
        currentDate = currentDate.plusDays(Order.RETURN_PERIOD - 1);
        return this;
    }

    public OrderTestStory returnProducts(){
        try{
            order.rollback(currentDate);
            returnAccepted = true;
        }catch (OrderRollbackException e){
            returnAccepted = false;
        }
        return this;
    }

    public void expect_cheapest_item_half_off(){
        Double expectedPrice = expectedRegularPrice - (cheapestProduct.getPrice() / 2);
        assertEquals(order.getTotalPrice(), expectedPrice);
    }

    public void expect_cheapest_item_free(){
        Double expectedPrice = expectedRegularPrice - cheapestProduct.getPrice();
        assertEquals(order.getTotalPrice(), expectedPrice);
    }

    public void expect_no_discount() {
        assertEquals(order.getTotalPrice(), expectedRegularPrice);
    }

    public void expect_return_rejected(){
        assertFalse(returnAccepted);
    }

    public void expect_return_accepted(){
        assertTrue(returnAccepted);
    }
}
