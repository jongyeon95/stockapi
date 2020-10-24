package com.pretask.stockapi.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockInfo {

    @NonNull
    private String code;

    @NonNull
    private BigDecimal price;


}
