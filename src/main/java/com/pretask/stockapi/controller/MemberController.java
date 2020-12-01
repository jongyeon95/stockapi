package com.pretask.stockapi.controller;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.repository.MemberRepository;
import com.pretask.stockapi.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/stock/delete")
    @ResponseBody
    public void deleteStock(@RequestParam String stock, @RequestParam String user){
        memberService.deleteFastStock(stock,user);
        return;
    }
}
