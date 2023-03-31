package project.book.shop.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.book.shop.entity.*;
import project.book.shop.entity.item.Book;
import project.book.shop.repository.ItemRepository;
import project.book.shop.repository.MemberRepository;
import project.book.shop.repository.OrderRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
//        System.out.println("postconstructer");
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final MemberRepository memberRepository;
        private final ItemRepository itemRepository;
        private final OrderRepository orderRepository;

        public void dbInit1(){
            Member createMember = createMember("userA", "서울","1", "1111");
            memberRepository.save(createMember);



            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            itemRepository.save(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            itemRepository.save(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Member member = memberRepository.findById(createMember.getId()).get();

            //배송정보 설정
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);


            //주문 생성
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            orderRepository.save(order);
        }
        public void dbInit2(){
            Member member = createMember("userB","진주", "2", "2222");
            memberRepository.save(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            itemRepository.save(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            itemRepository.save(book2);



            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            orderRepository.save(order);
        }

        private Member createMember(String name, String city, String street,
                                    String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }
        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);
            return delivery;
        }
    }
}
