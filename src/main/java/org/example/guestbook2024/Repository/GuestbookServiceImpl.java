package org.example.guestbook2024.Repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.entity.GuestBook;
import org.springframework.stereotype.Service;

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
}
