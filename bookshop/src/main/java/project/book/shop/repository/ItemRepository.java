package project.book.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.book.shop.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findAll();

}
