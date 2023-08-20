package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSimpleQueryDto;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.OrderSearch;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2() {
        // ORDER 2개(N=2)
        // N+1 문제가 발생한다 -> 1 + 회원N + 배송 N ( 1+2+2 총 5개의 쿼리가 나간다)
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
//        List<SimpleOrderDto> result = orders.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(Collectors.toList());
//        return result;

        List<SimpleOrderDto> result = new ArrayList<>();
        for (Order o : orders) {
            result.add(new SimpleOrderDto(o));
        }
        return result;
    }

@Data
static class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName(); //LAZY 초기화
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress(); // LAZY 초기화
    }

}

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = new ArrayList<>();
//        List<SimpleOrderDto> result = orders.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(Collectors.toList());
//        return result;
        for (Order o : orders) {
            result.add(new SimpleOrderDto(o));
        }
        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }
}
