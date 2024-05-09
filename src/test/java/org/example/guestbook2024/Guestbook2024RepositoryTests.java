package org.example.guestbook2024;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.example.guestbook2024.Repository.GuestbookRepository;
import org.example.guestbook2024.entity.GuestBook;
import org.example.guestbook2024.entity.QGuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class Guestbook2024RepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i ->{
            GuestBook guestbook = GuestBook.builder()
                    .title("Title====" + i)
                    .content("Title====" + i)
                    .writer("Writer==="  +(i % 10+1))
                    .build();
            guestbookRepository.save(guestbook);

        });
    }
    @Test
    public void updateTest(){
        Optional<GuestBook> result = guestbookRepository.findById(200L);

        if(result.isPresent()){
            GuestBook guestBook = result.get();

            guestBook.chageTitle("Changed Title...");
            guestBook.changeContent("Changed Content...");

            guestbookRepository.save(guestBook);
        }

    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
        QGuestBook qGuestBook = QGuestBook.guestBook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exp1 = qGuestBook.title.contains(keyword);
        builder.and(exp1);
        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }
    //다중 항목 검색
    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
        QGuestBook qGuestBook = QGuestBook.guestBook;
        String keyword = "2";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exp1 = qGuestBook.title.contains(keyword);
        BooleanExpression exp2 = qGuestBook.content.contains(keyword);
        BooleanExpression exp = exp1.or(exp2);
        builder.and(exp);
        builder.and(qGuestBook.gno.gt(100));
        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }
}
