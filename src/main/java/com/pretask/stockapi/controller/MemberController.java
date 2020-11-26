package com.pretask.stockapi.controller;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.repository.MemberRepository;
import com.pretask.stockapi.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

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

    @PostMapping("/fastStock")
    @ResponseBody
    public void addFastStock(@RequestBody String req) throws IOException {
        JSONObject jObject=new JSONObject(req);
        JSONObject userInfo=jObject.getJSONObject("username");
       String code=jObject.getString("code");
       String username=userInfo.getString("username");
        memberService.addFastStockList(username,code);
        return;
    }
}
