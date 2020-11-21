package com.pretask.stockapi.controller;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.repository.MemberRepository;
import com.pretask.stockapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;



    @GetMapping("/signUp")
    public String returnSignUpPage(){
        return "signUp";
    }
    @ResponseBody
    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto userDto){
        return memberService.signUp(userDto);
    }
}
