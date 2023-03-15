package project.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.test.entity.Book;
import project.test.entity.Stock;
import project.test.traffic.BookLockFacade;
import project.test.traffic.BookRepository;
import project.test.traffic.BookService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest

public class SprinngBootTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLockFacade bookLockFacade;

    @Autowired
    BookService bookService;

    @Test
    @DisplayName("테스트")
    void test(){
        System.out.println("okj");
    }

    @Test
    void 동시에_100명이_책을_구매한다() throws InterruptedException {
        Long bookId;

        bookId = bookRepository.save(new Book("이펙티브 자바", 36_000, new Stock(100)))
                .getId();
        System.out.println("res: "+bookId);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    bookService.purchase(bookId, 1);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        Book actual = bookRepository.findById(bookId)
                .orElseThrow();

        assertThat(actual.getStock().getRemain()).isZero();
    }

    @Test
    void 동시에_100명이_책을_구매한다2() throws InterruptedException{
        Long bookId = bookRepository.save(new Book("이펙티브 자바", 36_000, new Stock(100)))
                .getId();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i= 0;i<100;i++){
            executorService.submit(()->{
                try {
                    bookLockFacade.purchase(bookId, 1);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Book actual = bookRepository.findById(bookId).orElseThrow();
        assertThat(actual.getStock().getRemain()).isZero();
    }
}
