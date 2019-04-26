package pl.com.rbo.order.discount;

import pl.com.rbo.order.Item;

import java.util.List;

public interface DiscountPolicy {
    boolean applies(List<Item> items);
    Double calculate(List<Item> items);
}
