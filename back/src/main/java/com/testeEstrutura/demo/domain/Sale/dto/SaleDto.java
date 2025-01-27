package com.testeEstrutura.demo.domain.Sale.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class SaleDto {
    public String idCustomer;
    public Double totalValue;
    public String clientName;
    public String address;
    public LocalDateTime date;

    // Construtor padrão
    public SaleDto() {
    }

    public SaleDto( UUID idCustomer, Double totalValue, String clientName, String address, LocalDateTime date) {
        this.idCustomer = idCustomer.toString();
        this.totalValue = totalValue;
        this.clientName = clientName;
        this.address = address;
        this.date = date;
    }
}
