package project.test.traffic;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.test.entity.Book;
import project.test.entity.Stock;

@RestController
@RequiredArgsConstructor
public class Test {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;


    private final String KEY="keyword";


    @GetMapping("test1")
    public String test1(){

        String key ="banana";
        String value = "yellow";

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key, value);

        System.out.println("Redis key : " + key);
        System.out.println("Redis value : " + stringValueOperations.get(key));

        return "1";
    }
    @GetMapping("test2")
    public Long test2(){
        Long bookId;

        bookId = bookRepository.save(new Book("이펙티브 자바", 36_000, new Stock(100)))
                .getId();
        return bookId;
    }
}
