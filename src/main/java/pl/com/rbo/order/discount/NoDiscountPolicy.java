package pl.com.rbo.order.discount;

import pl.com.rbo.order.Item;

import java.util.List;

public class NoDiscountPolicy implements DiscountPolicy{

    public boolean applies(List<Item> items) {
        return true;
    }

    public Double calculate(List<Item> items) {
        Double regularPrice = new Double(0);
        for (Item item : items){
            regularPrice += item.getPrice();
        }
        return regularPrice;
    }
}
