package com.testeEstrutura.demo.config;


import com.testeEstrutura.demo.constantes.RabbitMQConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    private static final String  NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConfig(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //quando quisermos que algo rode quando a aplicação subir, coloca essa anottation e boa.
    @PostConstruct
    private void adiciona(){
        Queue filaTeste = this.fila(RabbitMQConstantes.FILA_VENDA);

        DirectExchange troca = this.trocaDireta();

        Binding ligacao = this.relacionamento(filaTeste, troca);

        //Criando as filas no rabbitMQ
        this.amqpAdmin.declareQueue(filaTeste);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacao);
    }
}