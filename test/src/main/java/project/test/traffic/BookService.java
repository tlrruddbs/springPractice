package project.test.traffic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.test.entity.Book;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void purchase(final Long bookId, final long quantity) {
        Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        book.purchase(quantity);
    }


}
