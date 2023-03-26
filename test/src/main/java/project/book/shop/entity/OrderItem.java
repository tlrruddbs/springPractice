package project.book.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue @Column(name = "order_item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item Items;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



    private int orderPrice;
    private int count;

}
