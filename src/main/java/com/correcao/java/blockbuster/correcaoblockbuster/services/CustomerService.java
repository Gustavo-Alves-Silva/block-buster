package com.correcao.java.blockbuster.correcaoblockbuster.services;

import com.correcao.java.blockbuster.correcaoblockbuster.models.CustomerModel;
import com.correcao.java.blockbuster.correcaoblockbuster.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerModel save(CustomerModel customerModel) {
        return customerRepository.save(customerModel);
    }

    public Optional<CustomerModel> findById(UUID id) {
        return customerRepository.findById(id);
    }

    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }
}
