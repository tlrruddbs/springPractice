package project.test.traffic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.test.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
