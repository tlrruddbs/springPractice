package project.test.traffic;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import project.test.entity.Book;

import javax.transaction.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void purchase(final Long bookId, final long quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(IllegalArgumentException::new);
        book.purchase(quantity);
    }
}
