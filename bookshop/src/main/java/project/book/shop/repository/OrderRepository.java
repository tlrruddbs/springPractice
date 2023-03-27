package project.book.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.book.shop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
