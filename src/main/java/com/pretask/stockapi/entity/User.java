package com.pretask.stockapi.entity;


import lombok.Data;
import yahoofinance.Stock;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
public class User {

    @Id
    @Column
    @GeneratedValue
    private  Long id;

    @Column(nullable = false)
    private String username;

    private  String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private  String role;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private LocalDateTime updatedTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Collection<StockList> stockList;

}
