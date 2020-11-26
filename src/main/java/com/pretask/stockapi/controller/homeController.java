package com.pretask.stockapi.controller;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.entity.StockList;
import com.pretask.stockapi.model.StockInfo;
import com.pretask.stockapi.service.MemberService;
import com.pretask.stockapi.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Controller
public class homeController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public String home(HttpServletRequest req, Model model) throws IOException {
        Collection<StockList> stockList=null;
        UserDto userDto=null;
        ArrayList<StockInfo> list=new ArrayList<>();
        if(req.isUserInRole("ROLE_USER")) {
            stockList = memberService.returnStockList(req.getRemoteUser());
            userDto = memberService.returnMemberInfo(req.getRemoteUser());
            System.out.println(userDto);
            for (StockList temp : stockList) {
                list.add((StockInfo) stockService.getSingleStockInfo(temp.getStockName()));
            }
        }
        model.addAttribute("stockList",list);
        model.addAttribute("user",userDto);
        return "home";
    }
}
