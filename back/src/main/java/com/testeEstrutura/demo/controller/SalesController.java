package com.testeEstrutura.demo.controller;

import com.testeEstrutura.demo.domain.Sale.dto.DataListSales;
import com.testeEstrutura.demo.domain.Sale.dto.IdSalesToDelivery;
import com.testeEstrutura.demo.service.SalesService;
import com.testeEstrutura.demo.webSocket.WebSocketSalesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalesController {

    @Autowired
    private SalesService service;

    @Autowired
    private WebSocketSalesController webSocketController;

    @GetMapping("/{customerId}/today")
    public ResponseEntity<List<DataListSales>> getSalesByCustomerAndDay(@PathVariable UUID customerId){
        var sales = service.getSalesOfDayByCustomer(customerId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Page<DataListSales>> list(
            @PageableDefault(size = 20, sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable UUID customerId,
            @RequestParam(value = "isDelivered", required = false) Boolean isDelivered) {

        Page<DataListSales> sales;

        if (isDelivered == null) {
            // Caso o parâmetro isDelivered não seja passado, retornar todas as vendas do cliente
            sales = service.listarAllByCostumer(customerId, pageable);
        } else if (isDelivered) {
            // Caso o parâmetro isDelivered seja true, retornar apenas as entregues
            sales = service.listarAllByCustomerAndIsDelivered(customerId, true, pageable);
        } else {
            // Caso o parâmetro isDelivered seja false, retornar apenas as não entregues
            sales = service.listarAllByCustomerAndIsDelivered(customerId, false, pageable);
        }

        return ResponseEntity.ok(sales);
    }


    @GetMapping
    public ResponseEntity<Page<DataListSales>> listAll(
            @PageableDefault(size = 20, sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        var sales = service.listar(pageable);
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/mark-delivered")
    public ResponseEntity<Void> updateToDelivered(
            @RequestBody IdSalesToDelivery ids) {

        // Chama o serviço para atualizar os status de entrega
        service.updateStatusSales(ids);

        // Retorna 204 No Content como resposta padrão para atualizações em lote
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
