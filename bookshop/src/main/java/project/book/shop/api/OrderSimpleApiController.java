package project.book.shop.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.book.shop.entity.*;
import project.book.shop.entity.item.Book;
import project.book.shop.repository.ItemRepository;
import project.book.shop.repository.MemberRepository;
import project.book.shop.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
* xToOne
* order
* order -> member
* order -> delivery
*
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;


    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){

        List<Order> all = orderRepository.findAll();
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order>orders = orderRepository.findAll();

        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @Data
    @AllArgsConstructor
    private static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;


        public SimpleOrderDto(Order o) {
            orderId = o.getId();
            name = o.getMember().getName(); //lazy
            orderDate = o.getOrderDate();
            orderStatus = o.getOrderStatus();
            address = o.getDelivery().getAddress(); //lazy
        }
    }

}
