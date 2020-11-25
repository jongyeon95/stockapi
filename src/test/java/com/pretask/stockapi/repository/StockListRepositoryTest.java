package com.pretask.stockapi.repository;

import com.pretask.stockapi.entity.StockList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockListRepositoryTest {
    @Autowired
    StockListRepository stockListRepository;

    @Test
    public void delete(){
        Optional<StockList> stockList;

        stockList=stockListRepository.findById(4L);
        stockListRepository.delete(stockList.get());

    }
}