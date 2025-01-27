package com.testeEstrutura.demo.domain.Sale.dto;

import com.testeEstrutura.demo.domain.Sale.Sale;

import java.time.LocalDateTime;

public record DataListSales(String id, Double totalValue, String clientName, String address, LocalDateTime date, Boolean isDelivered) {
    public DataListSales(Sale sale) {
        this(sale.getId(), sale.getTotalValue(), sale.getClientName(), sale.getAddress(), sale.getDate(), sale.getIsDelivered());
    }

    public DataListSales(SaleDto sale) {
        this(sale.idCustomer, sale.totalValue, sale.clientName, sale.address, sale.date, false);
    }
}
