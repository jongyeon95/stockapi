package com.pretask.stockapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YahooApiTest {

    @Autowired
    private YahooApi yahooApi;

    @Test
    public void getSingleStock() throws IOException {
//        String s ="AAPL";
//        Stock stock=yahooApi.getSingleStock(s);
//        assertNotNull(stock);
    }

    @Test
    public void getSingleStockFail() throws IOException {
//        String s ="AAAAAAAAAAA";
//        Stock stock=yahooApi.getSingleStock(s);
//        assertNull(stock);
    }


    @Test
    public void getPeriodStock() throws IOException{
        String s ="AAPL";
        Stock stock=yahooApi.getPeriodStock(s);
        assertNotNull(stock);

        List<HistoricalQuote> list =stock.getHistory();
        BigDecimal highPrice= BigDecimal.valueOf(0.0);
        BigDecimal lowPrice= BigDecimal.valueOf(Long.MAX_VALUE);
        Calendar highDate=Calendar.getInstance();
        Calendar lowDate=Calendar.getInstance();
        for(HistoricalQuote quote : list){
            if(quote.getHigh().compareTo(highPrice)>0){
                highPrice=quote.getHigh();
                highDate=quote.getDate();
            }
            if(quote.getLow().compareTo(lowPrice)<0){
                lowPrice=quote.getLow();
                lowDate=quote.getDate();
            }
        }
        System.out.println(highPrice);
        System.out.println(highDate.getTime());
        System.out.println(lowPrice);
        System.out.println(lowDate.getTime());


        String s2 ="!@#@!#";
        Stock stock2=yahooApi.getPeriodStock(s2);
        assertNull(stock2);
    }

}