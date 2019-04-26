package pl.com.rbo.order;

import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static pl.com.rbo.order.ItemKind.*;

public class OrderTestStory {

    private static final Integer GREATER_THAN_RETURN_PERIOD = Order.RETURN_PERIOD + 1;
    private static final Integer LESS_THAN_RETURN_PERIOD = Order.RETURN_PERIOD - 1;

    private Order order;
    private List<Item> items = new ArrayList<Item>();
    private Item cheapestItem;
    private Double expectedRegularPrice;
    private Integer returnPeriod;

    private OrderTestStory(){}

    public static OrderTestStory Go_shopping(){
        return new OrderTestStory();
    }

    public OrderTestStory get_one_item(){
        Item tshirt = new Item(TSHIRT, 40d);
        items.add(tshirt);
        cheapestItem = tshirt;
        expectedRegularPrice = tshirt.getPrice();
        return this;
    }

    public OrderTestStory get_anything(){
        return get_one_item();
    }

    public OrderTestStory get_two_items(){
        Item tshirt = new Item(TSHIRT, 40d);
        Item jeans = new Item(JEANS, 100d);
        items.add(tshirt);
        items.add(jeans);
        expectedRegularPrice = tshirt.getPrice() + jeans.getPrice();
        cheapestItem = tshirt;
        return this;
    }

    public OrderTestStory get_three_items(){
        Item tshirt = new Item(TSHIRT, 40d);
        Item jeans = new Item(JEANS, 100d);
        Item socks = new Item(SOCKS, 10d);
        items.add(tshirt);
        items.add(jeans);
        items.add(socks);
        expectedRegularPrice = tshirt.getPrice() + jeans.getPrice() + socks.getPrice();
        cheapestItem = socks;
        return this;
    }

    public OrderTestStory buy(){
        order = new Order();
        order.addItems(items);
        order.finalize(LocalDate.now());
        return this;
    }

    public OrderTestStory return_after_return_period(){
        returnPeriod = GREATER_THAN_RETURN_PERIOD;
        return this;
    }

    public OrderTestStory return_before_return_period(){
        returnPeriod = LESS_THAN_RETURN_PERIOD;
        return this;
    }

    public void expect_cheapest_item_half_off(){
        Assert.assertTrue(order.getTotalPrice() == expectedRegularPrice - (cheapestItem.getPrice() / 2));
    }

    public void expect_cheapest_item_free(){
        Assert.assertTrue(order.getTotalPrice() == expectedRegularPrice - (cheapestItem.getPrice()));
    }

    public void expect_no_discount(){
        Assert.assertTrue(order.getTotalPrice() == expectedRegularPrice);
    }

    public void expect_return_rejected(){
        Order order = new Order();
        order.addItems(items);
        LocalDate finalizeDay = LocalDate.now();
        order.finalize(finalizeDay);
        try{
            order.rollback(finalizeDay.minusDays(returnPeriod));
        }catch(OrderRollbackException e){
            Assert.assertTrue(true);
        }
        Assert.fail();
    }

    public void expect_return_accepted(){
        Order order = new Order();
        order.addItems(items);
        LocalDate finalizeDay = LocalDate.now();
        order.finalize(finalizeDay);
        try{
            order.rollback(finalizeDay.minusDays(returnPeriod));
        }catch(OrderRollbackException e){
            Assert.fail();
        }
        Assert.assertTrue(true);
    }
}
