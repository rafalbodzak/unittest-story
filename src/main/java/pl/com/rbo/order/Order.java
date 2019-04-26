package pl.com.rbo.order;

import pl.com.rbo.order.discount.DiscountCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static pl.com.rbo.order.OrderStatus.*;


public class Order {

    public static final Integer RETURN_PERIOD = 30;

    private DiscountCalculator discountCalculator = new DiscountCalculator();

    private List<Product> products = new ArrayList<Product>();
    private OrderStatus status = IN_PROGRESS;
    private LocalDate finalizeDate;
    private Double totalPrice;

    public void addItem(Product product){
        products.add(product);
    }
    public void addItems(List<Product> products){
        this.products.addAll(products);
    }

    public void finalize(LocalDate date){
        finalizeDate = date;
        totalPrice = discountCalculator.calculatePrice(products);
        status = FINALIZED;
    }

    public void rollback(LocalDate date) throws OrderRollbackException{
        if (finalizeDate.plusDays(RETURN_PERIOD).isBefore(date)){
            throw new OrderRollbackException();
        }
        status = ROLLED_BACK;
    }

    public Double getTotalPrice(){
        return totalPrice;
    }

    public LocalDate getFinalizeDate(){
        return finalizeDate;
    }

}
