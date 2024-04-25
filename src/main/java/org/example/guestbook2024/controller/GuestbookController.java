package org.example.guestbook2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")

public class GuestbookController {
    @GetMapping({"/", "/list" })
//    슬래쉬로도 리스트.html 실행 되고 두가지 다 파일이 실행되게 하기 위해서
    public String list(){
        return "/guestbook/list";
    }

}

