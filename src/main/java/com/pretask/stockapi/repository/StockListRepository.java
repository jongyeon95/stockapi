package com.pretask.stockapi.repository;

import com.pretask.stockapi.entity.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockListRepository extends JpaRepository<StockList,Long> {
    Optional<StockList> findByUserId(Long id);
    Optional<StockList> findByUserIdAndAndStockName(Long id,String name);
}
