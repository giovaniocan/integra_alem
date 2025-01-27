package com.testeEstrutura.demo.webSocket;

import com.testeEstrutura.demo.domain.Sale.Sale;
import com.testeEstrutura.demo.domain.Sale.dto.DataListSales;
import com.testeEstrutura.demo.domain.Sale.dto.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate; // Import correto
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketSalesController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendSaleToCustomer(String customerId, DataListSales sale) {
        messagingTemplate.convertAndSend("/topic/sales/"+ customerId, sale);
    }

    public void sendSaleofDayToCustomer(String idCustomer, List<DataListSales> sales){
        messagingTemplate.convertAndSend("/topic/salesOfDay/"+ idCustomer, sales);
    }

    public void sendSalesDeliveredToCustomer(String idCustomer, DataListSales sale){
        messagingTemplate.convertAndSend("/topic/salesDelivered/"+ idCustomer, sale);
    }
}
