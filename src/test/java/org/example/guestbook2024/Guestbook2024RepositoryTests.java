package org.example.guestbook2024;

import org.example.guestbook2024.Repository.GuestbookRepository;
import org.example.guestbook2024.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        Optional<GuestBook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            GuestBook guestBook = result.get();

            guestBook.chageTitle("Changed Title...");
            guestBook.changeContent("Changed Content...");

            guestbookRepository.save(guestBook);
        }

    }

    @Test
    public void testQuery1(){


    }
}
