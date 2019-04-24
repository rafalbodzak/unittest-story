package pl.com.rbo.order;

import org.junit.Test;

import static pl.com.rbo.order.OrderTestStory.*;

public class OrderTest {


    @Test
    public void discount_policy_stories(){

        Go_shopping()
                .get_jeans()
                .buy()
                .got_no_discount();

        Go_shopping()
                .get_jeans()
                .get_tshirt()
                .buy()
                .got_cheapest_item_half_off();

        Go_shopping()
                .get_tshirt()
                .get_jeans()
                .get_tshirt()
                .buy()
                .got_cheapest_item_free();
    }

    @Test
    public void return_policy_stories(){

        Go_shopping()
                .get_jeans()
                .buy()
                .return_before_return_period()
                .got_return_accepted();

        Go_shopping()
                .get_jeans()
                .buy()
                .return_after_return_period()
                .got_return_rejected();
    }
}