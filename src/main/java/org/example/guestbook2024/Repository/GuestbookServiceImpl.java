package org.example.guestbook2024.Repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.dto.PageRequestDTO;
import org.example.guestbook2024.dto.PageResultDTO;
import org.example.guestbook2024.entity.GuestBook;
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
        Page<GuestBook> result = repository.findAll(pageable);
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
}
