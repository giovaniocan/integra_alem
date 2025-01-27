package com.testeEstrutura.demo.domain.Sale.dto;

import java.util.List;
import java.util.UUID;

public record IdSalesToDelivery(
        String idCustomer,
        List<UUID> ids
) {
}
