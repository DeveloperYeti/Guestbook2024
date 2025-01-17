package org.example.guestbook2024.Service;

import org.example.guestbook2024.Repository.GuestbookService;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.dto.PageRequestDTO;
import org.example.guestbook2024.dto.PageResultDTO;
import org.example.guestbook2024.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @Test
    public void testSearchList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(25)
                .size(10)
                .type("tc")
                .keyword("7")
                .build();
        service.getList(pageRequestDTO);
        PageResultDTO<GuestbookDTO , GuestBook> resultDTO = service.getList(pageRequestDTO);
        List<GuestbookDTO> list = resultDTO.getDtoList();

        System.out.println("start:" + resultDTO.getStart());
        System.out.println("end:" + resultDTO.getEnd());
        System.out.println("prev:" + resultDTO.isPrev());
        System.out.println("next:" + resultDTO.isNext());

        for (GuestbookDTO guestbookDTO : list) System.out.println(guestbookDTO);

        for (Integer pageNum : resultDTO.getPageList()){
            System.out.println(pageNum.intValue());
        }
    }
}
