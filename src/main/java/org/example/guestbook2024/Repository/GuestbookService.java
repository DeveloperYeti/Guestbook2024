package org.example.guestbook2024.Repository;

import com.querydsl.core.BooleanBuilder;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.dto.PageRequestDTO;
import org.example.guestbook2024.dto.PageResultDTO;
import org.example.guestbook2024.entity.GuestBook;

public interface GuestbookService {
    //글 등록 기능
    Long register(GuestbookDTO dto);
    // 한 페이지에 보여질 글 목록(GuestbookDTO 객체)이 저장된 List를 가지고 있는  PageResultDTO객체 참조값을 반환하는 기능
    PageResultDTO<GuestbookDTO , GuestBook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);
    default GuestBook dtoToEntity(GuestbookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content((dto.getContent()))
                .writer(dto.getWriter())
                .build();
        return entity;
    }
    //글 제목과 내용 수정하는 기능

    // 글 삭제 기능
    void modify(GuestbookDTO dto );
    void remove(Long gno);

    default GuestbookDTO entityToDto(GuestBook entity){
        GuestbookDTO dto =GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
    // 검색 기능
    BooleanBuilder getSearch(PageRequestDTO requestDTO);

}
