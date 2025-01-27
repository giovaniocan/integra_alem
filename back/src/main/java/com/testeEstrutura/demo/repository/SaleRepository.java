package com.testeEstrutura.demo.repository;

import com.testeEstrutura.demo.domain.Sale.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, String> {
    @Query("SELECT s FROM Sale s WHERE s.idCustomer = :customerId")
    Page<Sale> findByCustomerId(String customerId, Pageable pageable);

    @Query("SELECT s FROM Sale s WHERE s.idCustomer = :customerId AND s.date >= :startOfDay AND s.date < :endOfDay")
    List<Sale> findSalesByCustomerAndDay(String customerId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT s FROM Sale s WHERE s.idCustomer = :customerId AND s.isDelivered = :isDelivered")
    Page<Sale> findByCustomerIdAndIfIsDelivered(String customerId, boolean isDelivered, Pageable pageable);
}
