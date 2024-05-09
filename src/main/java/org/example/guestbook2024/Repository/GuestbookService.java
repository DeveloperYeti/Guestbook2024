package org.example.guestbook2024.Repository;

import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.entity.GuestBook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    default GuestBook dtoToEntity(GuestbookDTO dto){
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content((dto.getContent()))
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}
