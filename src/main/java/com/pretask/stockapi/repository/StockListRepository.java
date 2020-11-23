package com.pretask.stockapi.repository;

import com.pretask.stockapi.entity.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockListRepository extends JpaRepository<StockList,Long> {

}
