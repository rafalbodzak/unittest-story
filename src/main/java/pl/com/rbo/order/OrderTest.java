package pl.com.rbo.order;

import org.junit.Test;

import static pl.com.rbo.order.OrderTestStory.*;

public class OrderTest {


    @Test
    public void discount_policy_stories(){

        Go_shopping()
                .get_one_item()
                .buy()
                .expect_no_discount();

        Go_shopping()
                .get_two_items()
                .buy()
                .expect_cheapest_item_half_off();

        Go_shopping()
                .get_three_items()
                .buy()
                .expect_cheapest_item_free();
    }

    @Test
    public void return_policy_stories(){

        Go_shopping()
                .get_anything()
                .buy()
                .return_before_return_period()
                .expect_return_accepted();

        Go_shopping()
                .get_anything()
                .buy()
                .return_after_return_period()
                .expect_return_rejected();
    }
}