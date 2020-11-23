package com.pretask.stockapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StockList {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column
    private String stockName;

    @Column
    private Long userId;

}
