package pl.com.rbo.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    private String name;
    private Double price;
}
