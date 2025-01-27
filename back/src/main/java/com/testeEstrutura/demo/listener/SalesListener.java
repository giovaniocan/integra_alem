package com.testeEstrutura.demo.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.testeEstrutura.demo.domain.Sale.dto.SaleDto;
import com.testeEstrutura.demo.service.SalesService;
import com.testeEstrutura.demo.webSocket.WebSocketSalesController;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SalesListener {

    private final ObjectMapper objectMapper;  // Criação do ObjectMapper


    public SalesListener() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // para suportar localDateTime em vez de apenas Date
    }
    @Autowired
    private SalesService service;

    @RabbitListener(queues = "SALES")
    public void consumerMessage(@Payload Message saleMessage) {
        try {
            String payload = (String) saleMessage.getPayload();
            SaleDto sale = objectMapper.readValue(payload, SaleDto.class);

            service.saveSale(sale);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Erro permanente ao processar mensagem.");
        }
    }
}
