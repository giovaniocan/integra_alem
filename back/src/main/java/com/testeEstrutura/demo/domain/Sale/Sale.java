package com.testeEstrutura.demo.domain.Sale;

import com.testeEstrutura.demo.domain.Sale.dto.SaleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "Sale")
@Table(name = "sales")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sale {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 36)
    private String id;

    @Column(nullable = false, length = 36)
    private String idCustomer;

    @Column(nullable = false)
    private Double totalValue;

    @Column(nullable = false, length = 255)
    private String clientName;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Boolean isDelivered = false;

    // Construtor usando SaleDto
    public Sale(SaleDto sale) {
        this.id = java.util.UUID.randomUUID().toString(); // Gera UUID em formato String
        this.idCustomer = sale.idCustomer.toString();     // Converte UUID para String
        this.totalValue = sale.totalValue;
        this.clientName = sale.clientName;
        this.address = sale.address;
        this.date = sale.date;
        this.isDelivered = false;
    }

    public void sendToDelivery() {
        this.isDelivered = true;
    }

    // Construtor padrão
    public Sale() {
    }

    public String getId() {
        return id;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getIsDelivered() {
        return isDelivered;
    }
}
