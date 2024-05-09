package org.example.guestbook2024.Service;

import org.example.guestbook2024.Repository.GuestbookService;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestServiceTests {
    @Autowired
    private GuestbookService service;
    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("등록 title 1")
                .content("등록 연습 content 1")
                .writer("등록 연습 writer1")
                .build();

        service.register(guestbookDTO);
    }
}
