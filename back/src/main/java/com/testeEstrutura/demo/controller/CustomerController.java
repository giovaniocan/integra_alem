package com.testeEstrutura.demo.controller;

import com.testeEstrutura.demo.domain.Sale.dto.DataListSales;
import com.testeEstrutura.demo.domain.User.dto.DataAddCustomer;
import com.testeEstrutura.demo.domain.User.dto.DataDetailCustomer;
import com.testeEstrutura.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<DataDetailCustomer> add (@RequestBody @Valid DataAddCustomer dataAddCustomer){
        var customer = customerService.createCustomer(dataAddCustomer);

        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailCustomer>> listAll(
            @PageableDefault(size = 20, sort = {"customerName"}, direction = Sort.Direction.ASC) Pageable pageable) {
        var customer = customerService.listAll(pageable);
        return ResponseEntity.ok(customer);
    }


}
