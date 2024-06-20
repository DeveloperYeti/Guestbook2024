package org.example.guestbook2024.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.dto.PageRequestDTO;
import org.example.guestbook2024.dto.PageResultDTO;
import org.example.guestbook2024.entity.GuestBook;
import org.example.guestbook2024.entity.QGuestBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
    private final GuestbookRepository repository;
    @Override
    public Long register(GuestbookDTO dto){
        GuestBook entity = dtoToEntity(dto);
        repository.save(entity);

         return entity.getGno();


    }

    @Override
    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO); //where 정의 조건식
        Page<GuestBook> result = repository.findAll(booleanBuilder , pageable); /*조건식이 포함된 select 문 생성*/
        Function<GuestBook, GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<GuestBook> result = repository.findById(gno);

        return result.isPresent()?entityToDto(result.get()) : null;
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<GuestBook> result = repository.findById(dto.getGno());

        if(result.isPresent()){
            GuestBook entity = result.get();
            entity.chageTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            repository.save(entity);// 글의 제목과 내용을 update문 실행

        }
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);

    }

    @Override
    public BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestBook qGuestBook = QGuestBook.guestBook;
        BooleanExpression booleanExpression = qGuestBook.gno.gt(0L);

        booleanBuilder.and(booleanExpression);

        //  화면에서 검색 조건을 선택하지 않은 경우 검색 타입 = null 및 검색어 는 입력이 안된 경우
        if(type == null || keyword.trim().length() == 0 || type.trim().length() == 0 ){
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("c")){
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if(type.contains("t")){
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

       return booleanBuilder;
    }

}
