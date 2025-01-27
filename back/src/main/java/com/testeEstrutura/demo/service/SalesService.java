package com.testeEstrutura.demo.service;

import com.testeEstrutura.demo.domain.Sale.dto.DataListSales;
import com.testeEstrutura.demo.domain.Sale.dto.IdSalesToDelivery;
import com.testeEstrutura.demo.domain.Sale.dto.SaleDto;
import com.testeEstrutura.demo.domain.Sale.Sale;
import com.testeEstrutura.demo.repository.SaleRepository;
import com.testeEstrutura.demo.webSocket.WebSocketSalesController;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SalesService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private WebSocketSalesController webSocketSalesController;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public void saveSale(SaleDto sale) {

        // verifying if the customer exists
        var customerExists = customerService.validateCustomer(sale.idCustomer);

        if(customerExists){
            var newSaleToWebSocket = repository.save(new Sale(sale));
            // Envia a nova venda para o front-end
            webSocketSalesController.sendSaleToCustomer(newSaleToWebSocket.getIdCustomer(), new DataListSales(newSaleToWebSocket));
        }
    }

    public Page<DataListSales> listar(Pageable pageable) {
        return repository.findAll(pageable).map(DataListSales::new);
    }

    public Page<DataListSales> listarAllByCostumer(UUID costumerId, Pageable pageable) {
        var idString = costumerId.toString();
        return repository.findByCustomerId(idString, pageable).map(DataListSales::new);
    }

    public Page<DataListSales> listarAllByCustomerAndIsDelivered(UUID customerId, boolean isDelivered, Pageable pageable) {
        var idString = customerId.toString();
        return repository.findByCustomerIdAndIfIsDelivered(idString, isDelivered, pageable).map(DataListSales::new);
    }

    @Transactional
    public void updateStatusSales(IdSalesToDelivery idSalesToDelivery) {
        List<String> saleIds = idSalesToDelivery.ids()
                .stream()
                .map(UUID::toString) // Converte UUID para String
                .toList();


        // verifica se o cliente existe
        var customerExists = customerService.validateCustomer(idSalesToDelivery.idCustomer());

        if(customerExists){
            // Busca todas as vendas que correspondem aos IDs fornecidos
            List<Sale> sales = repository.findAllById(saleIds);

            // Atualiza o status de cada venda
            sales.forEach(Sale::sendToDelivery);

            // Salva as vendas atualizadas no banco
            repository.saveAll(sales);

            //mandar para o websocket essas vendas aqui que já foram entregues.
            var salesDelivered = sales.stream().map(DataListSales::new).toList();
            salesDelivered.forEach(sale -> {
                webSocketSalesController.sendSalesDeliveredToCustomer(idSalesToDelivery.idCustomer(), sale);
            });
        }else{
            System.out.println("o customer nao existe bb");
        }


    }

    public List<DataListSales> getSalesOfDayByCustomer(UUID customerId) {

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.now().toLocalDate().atTime(23, 59, 59);

        var idString = customerId.toString();
        var sales = repository.findSalesByCustomerAndDay(idString, startOfDay, endOfDay).stream().map(DataListSales::new).toList();

        //enviando para o websocket controller
        webSocketSalesController.sendSaleofDayToCustomer(idString, sales);

        return sales;
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }
}
