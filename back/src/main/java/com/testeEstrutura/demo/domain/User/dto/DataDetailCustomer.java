package com.testeEstrutura.demo.domain.User.dto;

import com.testeEstrutura.demo.domain.User.User;

public record DataDetailCustomer(
        String id,
        String customerName,
        String login,
        String password,
        String loginEntregaAlem,
        String passwordEntregaAlem
) {
    public DataDetailCustomer(User newUser) {
        this(newUser.getId(), newUser.getCustomerName(), newUser.getLogin(), newUser.getPassword(), newUser.getLoginEntregaAlem(), newUser.getPasswordEntregaAlem());
    }
}
