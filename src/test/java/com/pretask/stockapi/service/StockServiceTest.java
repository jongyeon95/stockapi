package com.pretask.stockapi.service;

import com.pretask.stockapi.model.StockInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yahoofinance.Stock;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void highPriceStock() throws IOException {
        String s="AA";
        BigDecimal output=stockService.HighPriceStock(s);
        assertNotNull(output);

        String s2="123!@#@!#";
        output=stockService.HighPriceStock(s2);
        assertNull(output);

        String s3="  ";
        output=stockService.HighPriceStock(s3);
        assertNull(output);
    }

    @Test
    public void bestProfitDate() throws IOException {

        String s="AAPL";
        String s2="AAPL@!#!@#@";
        StockInfo stockInfo=stockService.BestProfitDate(s);
        StockInfo stockInfo2=stockService.BestProfitDate(s2);

        assertNotNull(stockInfo);
        assertNull(stockInfo2);

        //계산된 결과의 구입날짜가 판매날짜보다 느린지 아닌지
        assertTrue(stockInfo.getBuyDate().compareTo(stockInfo.getSellDate())<0);

        System.out.println(stockInfo.toString());

    }

    @Test
    public void getStockInfo() throws IOException {
        String s="AA";
        assertThat(stockService.getStockInfo(s).getName(),is("Alcoa Corporation"));
        System.out.println(stockService.getStockInfo("AA").getName());
    }

    @Test
    public void getStockPeriodInfo() throws IOException {
        //공백,소문자도 같은 결과 나오는지
        String s="AA";
        String s2="";
        String s3="Aa";
        String s4="A    A";
        Stock stock1=stockService.getStockPeriodInfo(s);
        Stock stock2=stockService.getStockPeriodInfo(s2);
        Stock stock3=stockService.getStockPeriodInfo(s3);
        Stock stock4=stockService.getStockPeriodInfo(s4);

        assertNotNull(stock1);
        assertNull(stock2);


        assertThat(stock1.getName(),equalTo(stock3.getName()));
        assertThat(stock1.getName(),equalTo(stock4.getName()));

    }
}