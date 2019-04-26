package pl.com.rbo.order.discount;

import pl.com.rbo.order.Product;

import java.util.List;

public interface DiscountPolicy {
    boolean applies(List<Product> products);
    Double calculate(List<Product> products);
}
