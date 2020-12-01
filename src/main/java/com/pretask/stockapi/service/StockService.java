package com.pretask.stockapi.service;

import com.pretask.stockapi.model.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Autowired
    YahooApi api;



    //현재 주식 가격
    public ArrayList<StockInfo> getStockPrice(String s) throws IOException {
        Map<String ,Stock> stocks=getStockInfo(s);
        if(stocks==null){
            return null;
        }
        ArrayList<StockInfo> list= new ArrayList<>();
        for(Stock value : stocks.values()){
            Stock stock=value;
            list.add(StockInfo.builder()
                    .code(stock.getSymbol())
                    .price(stock.getQuote().getPrice())
                    .build());
        }
        return list;
    }

    //기간 중 최고가
    public BigDecimal HighPriceStock(String s) throws IOException {
        Stock stock=getStockPeriodInfo(s);
        BigDecimal highPrice=BigDecimal.valueOf(0);
        if(stock==null){
            return null;
        }
        for(HistoricalQuote quote: stock.getHistory()){
            if(quote.getHigh().compareTo(highPrice)>0)
                highPrice=quote.getHigh();
        }

        return highPrice;
    }


    public Map<String, Stock> getStockInfo(String s) throws IOException {
        s=s.toUpperCase();
        s=s.replaceAll(" " , "");
        String[] stocks=s.split(",");
        return api.getStocks(stocks);
    }

    public StockInfo getSingleStockInfo(String s) throws  IOException{
       Stock stock = api.getSingleStock(s);
       StockInfo stockInfo=new StockInfo().builder()
               .code(stock.getSymbol())
               .price(stock.getQuote().getPrice())
               .build();
       return stockInfo;
    }

    //180일 기간의 주식정보 가져오기
    public Stock getStockPeriodInfo(String s) throws IOException {
        s=s.replaceAll(" " , "");
        return api.getPeriodStock(s);
    }


}
