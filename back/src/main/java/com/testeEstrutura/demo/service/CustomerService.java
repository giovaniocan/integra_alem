package com.testeEstrutura.demo.service;

import com.testeEstrutura.demo.domain.Sale.dto.DataListSales;
import com.testeEstrutura.demo.domain.User.User;
import com.testeEstrutura.demo.domain.User.dto.DataAddCustomer;
import com.testeEstrutura.demo.domain.User.dto.DataDetailCustomer;
import com.testeEstrutura.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    public DataDetailCustomer createCustomer(DataAddCustomer dataAddCustomer) {
        var user = new User(dataAddCustomer);
        var newUser = userRepository.save(user);

        var userDetail = new DataDetailCustomer(newUser);

        return userDetail;
    }

    public Boolean validateCustomer(String idCustomer) {
        var exists = userRepository.existsById(idCustomer);
        return exists;
    }

    public Page<DataDetailCustomer> listAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(DataDetailCustomer::new);
    }
}
