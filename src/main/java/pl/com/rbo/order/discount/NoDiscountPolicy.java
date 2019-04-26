package pl.com.rbo.order.discount;

import pl.com.rbo.order.Product;

import java.util.List;

public class NoDiscountPolicy implements DiscountPolicy{

    public boolean applies(List<Product> products) {
        return true;
    }

    public Double calculate(List<Product> products) {
        Double regularPrice = new Double(0);
        for (Product product : products){
            regularPrice += product.getPrice();
        }
        return regularPrice;
    }
}
