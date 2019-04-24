package pl.com.rbo.order;

import java.util.ArrayList;
import java.util.List;

public class OrderTestStory {

    private Order order = new Order();
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private OrderTestStory(){}

    public static OrderTestStory Go_shopping(){
        return new OrderTestStory();
    }

    public OrderTestStory get_tshirt(){
        items.add(new OrderItem("tshirt", 40d));
        return this;
    }

    public OrderTestStory get_jeans(){
        items.add(new OrderItem("jeans", 100d));
        return this;
    }

    public OrderTestStory buy(){
        return this;
    }

    public OrderTestStory return_after_return_period(){
        return this;
    }

    public OrderTestStory return_before_return_period(){
        return this;
    }

    public void got_cheapest_item_half_off(){
    }

    public void got_cheapest_item_free(){

    }

    public void got_no_discount(){

    }

    public void got_return_rejected(){

    }

    public void got_return_accepted(){

    }
}
