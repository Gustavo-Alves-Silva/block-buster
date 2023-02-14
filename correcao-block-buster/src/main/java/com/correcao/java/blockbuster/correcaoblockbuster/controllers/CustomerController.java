package com.correcao.java.blockbuster.correcaoblockbuster.controllers;


import com.correcao.java.blockbuster.correcaoblockbuster.dtos.CustomerDto;
import com.correcao.java.blockbuster.correcaoblockbuster.models.CustomerModel;
import com.correcao.java.blockbuster.correcaoblockbuster.repositories.CustomerRepository;
import com.correcao.java.blockbuster.correcaoblockbuster.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/costumer")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid CustomerDto customerDto){
        CustomerModel customerModel =  new CustomerModel();

        BeanUtils.copyProperties(customerDto, customerModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerModel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable(value = "id")UUID id, @RequestBody CustomerDto customerDto){
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);

        if(customerModelOptional.isPresent()){
            CustomerModel customerModel =  customerModelOptional.get();

            BeanUtils.copyProperties(customerDto,customerModel);

            customerModel.setId(customerModelOptional.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerModel));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Olha só, não encontramos nenhum cliente com esse ID");
        }

    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable(value = "id") UUID id){
        Optional<CustomerModel> customerModelOptional =  customerService.findById(id);

        if(!customerModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID nao foi encontrado!!!!!");
        }else{
            return  ResponseEntity.status(HttpStatus.OK).body(customerModelOptional.get());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteByCustomerId(@PathVariable(value = "id") UUID id){
        Optional<CustomerModel> customerModelOptional =  customerService.findById(id);

        if(!customerModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID nao foi encontrado!!!!!");
        }else{
            customerService.delete(customerModelOptional.get());
            return  ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
        }
    }

}
