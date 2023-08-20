package jpabook.jpashop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private Long oderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long oderId, String itemName, int orderPrice, int count) {
        this.oderId = oderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
