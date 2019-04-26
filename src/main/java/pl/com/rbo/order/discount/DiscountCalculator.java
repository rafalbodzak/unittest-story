package pl.com.rbo.order.discount;

import pl.com.rbo.order.Item;

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

    public Double calculatePrice(List<Item> items){
        return discountPolicies.stream()
                .filter(policy -> policy.applies(items))
                .map(policy -> policy.calculate(items))
                .min(Comparator.comparingDouble(Double::doubleValue))
                .get();
    }

}
