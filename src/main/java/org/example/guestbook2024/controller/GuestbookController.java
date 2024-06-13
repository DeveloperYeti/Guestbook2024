package org.example.guestbook2024.controller;

import lombok.RequiredArgsConstructor;
import org.example.guestbook2024.Repository.GuestbookRepository;
import org.example.guestbook2024.Repository.GuestbookService;
import org.example.guestbook2024.dto.GuestbookDTO;
import org.example.guestbook2024.dto.PageRequestDTO;
import org.example.guestbook2024.dto.PageResultDTO;
import org.example.guestbook2024.entity.QGuestBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
    private  final GuestbookService service;

    @GetMapping("/")
    public String index(){
        return  "redirect:/guestbook/list";
    }




    @GetMapping({ "/list" })
//    슬래쉬로도 리스트.html 실행 되고 두가지 다 파일이 실행되게 하기 위해서
    public void list(PageRequestDTO pageResultDTO, Model model){
            //list.html(Viewr계층)에 방명록 목록과 화면에 보여질 때 필요한 페이지 번호를 동의 정보를 전달
        model.addAttribute("result" , service.getList(pageResultDTO));

    }
    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        Long gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long gno , @ModelAttribute("requestDTO") PageRequestDTO requestDTO , Model model){
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto",dto);
    }
    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg" , gno);

        return "redirect:/guestbook/list";
    }
}

