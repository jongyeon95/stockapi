package com.pretask.stockapi.controller;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class homeController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest req, Model model){
        UserDto userDto=new UserDto();
        if(req.isUserInRole("ROLE_USER"))
            userDto=memberService.returnMemberInfo(req.getRemoteUser());
        model.addAttribute("member",userDto);
        return "home";
    }
}
