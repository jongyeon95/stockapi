package com.pretask.stockapi.service;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

@Service
public class YahooApi {
    /*
    * 주식정보 API 주소
    * https://financequotes-api.com/
    *
    */


//    Stock getSingleStock(String s) throws IOException {
//        Stock stock= YahooFinance.get(s);
//        return stock;
//    }
    Map<String, Stock> getStocks(String[] s) throws IOException {
        Map<String, Stock> stocks = YahooFinance.get(s);
        return stocks;
    }

    Stock getPeriodStock(String s) throws  IOException{
        if(s.equals(""))
            return null;
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, -180); // from 5 years ago
        Stock stock=YahooFinance.get(s,from, to, Interval.DAILY);
        return stock;
    }

}
