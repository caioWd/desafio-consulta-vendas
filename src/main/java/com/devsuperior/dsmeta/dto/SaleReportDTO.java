package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleReportDTO extends SaleMinDTO{

    private String sellerName;

    public SaleReportDTO(Long id, Double amount, LocalDate date, String sellerName) {
        super(id, amount, date);
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
