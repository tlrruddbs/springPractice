package project.book.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.book.shop.entity.*;
import project.book.shop.entity.item.Book;
import project.book.shop.exception.NotEnoughStockException;
import project.book.shop.repository.ItemRepository;
import project.book.shop.repository.MemberRepository;
import project.book.shop.repository.OrderRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderService orderService;


    @Test
    public void 상품주문() throws Exception{
        Member member = new Member();
        member.setUserName("회원1");
        member.setAddress(new Address("서울","강가","12312"));
        memberRepository.save(member);

        Book book = new Book();
        book.setName("시골 jpa");
        book.setPrice(10000);
        book.setStockQuantity(10);
        int orderCount = 2;
        itemRepository.save(book);

        Book findBook = (Book) itemRepository.findById(book.getId()).get();
        System.out.println(findBook.getId());

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        assertEquals(book.getStockQuantity(), 8, "주문 수량만큼 재고가 줄어야한다.");
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = new Member();
        member.setUserName("회원1");
        member.setAddress(new Address("서울","강가","12312"));
        memberRepository.save(member);

        Item book = new Book();
        book.setName("시골 jpa");
        book.setPrice(10000);
        book.setStockQuantity(10);
        int orderCount = 11;
        itemRepository.save(book);

        Book findBook = (Book) itemRepository.findById(book.getId()).get();
        System.out.println(findBook.getId());

        orderService.order(member.getId(), 1L, orderCount);

        org.junit.jupiter.api.Assertions.fail("실패해야한다.");
    }



    @Test
    public void 주문취소() throws Exception{
        Member member = new Member();
        member.setUserName("회원1");
        member.setAddress(new Address("서울","강가","12312"));
        memberRepository.save(member);

        Book book = new Book();
        book.setName("시골 jpa");
        book.setPrice(10000);
        book.setStockQuantity(10);
        int orderCount = 2;
        itemRepository.save(book);


        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId).get();
        assertEquals(getOrder.getStatus(), OrderStatus.CANCEL, "주문 취소시 상태는 cancel");
        assertEquals(book.getStockQuantity(), 10 ,"주문이 취소된 상품은 그만큼 재고가 충전되야한다.");


    }
}