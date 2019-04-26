package pl.com.rbo.order.discount;

import pl.com.rbo.order.Item;

import java.util.List;

public class Buy3ItemsGetCheapestFreeDiscountPolicy implements DiscountPolicy{

    public boolean applies(List<Item> items) {
        return items.size() >= 3;
    }

    public Double calculate(List<Item> items) {
        Double regularPrice = new Double(0);
        Double cheapestItemPrice = Double.MAX_VALUE;
        for (Item item : items){
            regularPrice += item.getPrice();
            if (Double.compare(item.getPrice(), cheapestItemPrice) < 0){
                cheapestItemPrice = item.getPrice();
            }
        }
        return regularPrice - cheapestItemPrice;
    }
}
