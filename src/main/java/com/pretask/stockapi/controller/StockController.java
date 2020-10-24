package com.pretask.stockapi.controller;

import com.pretask.stockapi.model.StockInfo;
import com.pretask.stockapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class StockController {

    @Autowired
    StockService stockService;


    @GetMapping("/stock/{value}")
    public String highPrice(@PathVariable String value, Model model) throws IOException {

        List<StockInfo> stocks=stockService.getStockPrice(value);
        if(stocks==null)
            return "stockInfo";
        model.addAttribute("infos",stocks);
        return "stockInfo";
    }
}
