package pl.com.rbo.order.discount;

import pl.com.rbo.order.Product;

import java.util.List;

public class Buy2ItemsGetCheapestHalfOffDiscountPolicy implements DiscountPolicy{

    public boolean applies(List<Product> products) {
        return products.size() >= 2;
    }

    public Double calculate(List<Product> products) {
        Double regularPrice = new Double(0);
        Double cheapestItemPrice = Double.MAX_VALUE;
        for (Product product : products){
            regularPrice += product.getPrice();
            if (Double.compare(product.getPrice(), cheapestItemPrice) < 0){
                cheapestItemPrice = product.getPrice();
            }
        }
        return regularPrice - (cheapestItemPrice / 2);
    }
}
