package pl.com.rbo.order.discount;

import pl.com.rbo.order.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DiscountCalculator {

    private List<DiscountPolicy> discountPolicies = new ArrayList<DiscountPolicy>();

    public DiscountCalculator(){
        discountPolicies.add(new NoDiscountPolicy());
        discountPolicies.add(new Buy2ItemsGetCheapestHalfOffDiscountPolicy());
        discountPolicies.add(new Buy3ItemsGetCheapestFreeDiscountPolicy());
    }

    public Double calculatePrice(List<Product> products){
        return discountPolicies.stream()
                .filter(policy -> policy.applies(products))
                .map(policy -> policy.calculate(products))
                .min(Comparator.comparingDouble(Double::doubleValue))
                .get();
    }

}
