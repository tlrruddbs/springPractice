package project.book.shop.entity;

import jakarta.persistence.*;

public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery")//, fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
