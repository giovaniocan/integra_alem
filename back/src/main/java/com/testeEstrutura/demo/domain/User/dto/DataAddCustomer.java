package com.testeEstrutura.demo.domain.User.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataAddCustomer(
        @NotBlank(message = "O nome do cliente não pode estar vazio.")
        String customerName,

        @NotBlank(message = "O login não pode estar vazio.")
        String login,

        @NotBlank(message = "A senha não pode estar vazia.")
        String password,

        @NotBlank(message = "O login de entrega além não pode estar vazio.")
        String loginEntregaAlem,

        @NotBlank(message = "A senha de entrega além não pode estar vazia.")
        String passwordEntregaAlem
) {
}
