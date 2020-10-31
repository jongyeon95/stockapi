package com.pretask.stockapi.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

}
