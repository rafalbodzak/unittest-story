package pl.com.rbo.order;

import org.junit.Test;

import static pl.com.rbo.order.OrderTestStory.*;

public class OrderTest {


    @Test
    public void discount_policy_stories(){

        Go_shopping()
                .get_one_product()
                .buy()
                .expect_no_discount();

        Go_shopping()
                .get_two_products()
                .buy()
                .expect_cheapest_product_half_off();

        Go_shopping()
                .get_three_products()
                .buy()
                .expect_cheapest_product_free();
    }

    @Test
    public void return_policy_stories(){

        Go_shopping()
                .get_any_product()
                .buy()
                .wait_less_than_return_period()
                .returnProducts()
                .expect_return_accepted();

        Go_shopping()
                .get_any_product()
                .buy()
                .wait_more_than_return_period()
                .returnProducts()
                .expect_return_rejected();
    }
}