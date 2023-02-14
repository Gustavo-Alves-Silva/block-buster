package com.correcao.java.blockbuster.correcaoblockbuster.controllers;

import com.correcao.java.blockbuster.correcaoblockbuster.dtos.MovieRentDto;
import com.correcao.java.blockbuster.correcaoblockbuster.enums.StatusEmail;
import com.correcao.java.blockbuster.correcaoblockbuster.models.CustomerModel;
import com.correcao.java.blockbuster.correcaoblockbuster.models.MailModel;
import com.correcao.java.blockbuster.correcaoblockbuster.models.MovieRentModel;
import com.correcao.java.blockbuster.correcaoblockbuster.repositories.MovieRentRepository;
import com.correcao.java.blockbuster.correcaoblockbuster.services.CustomerService;
import com.correcao.java.blockbuster.correcaoblockbuster.services.MailService;
import com.correcao.java.blockbuster.correcaoblockbuster.services.MovieRentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/movieRent")
public class MovieRentController {

    @Autowired
    private MovieRentService movieRentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MailService mailService;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<Object> saveMovieRent(@RequestBody @Valid MovieRentDto movieRentDto){
        var mailModel = new MailModel();
        var movieRentModel = new MovieRentModel();
        BeanUtils.copyProperties(movieRentDto, movieRentModel);

        Optional<CustomerModel> customerModel = customerService.findById(movieRentDto.getCustomerId());

       if(customerModel.isPresent()){
           try{
                movieRentModel.setOrderDay(LocalDateTime.now());
                movieRentModel.setCustomerModel(customerModel.get());

                mailModel.setEmailFrom("rdesenhos@gmail.com");
                mailModel.setEmailTo(customerModel.get().getEmail());
                mailModel.setSendDateEmail(movieRentModel.getOrderDay());
                mailModel.setSubject("Locação do filme: " +movieRentModel.getMovieTitle());
                mailModel.setText("Saudações, Sr(a) "+customerModel.get().getName()+"! A locação do filme "+ movieRentModel.getMovieTitle()+" " +
                        "foi feita com sucesso!\n Data da Locação: "+ movieRentModel.getOrderDay());

               SimpleMailMessage message = new SimpleMailMessage();
               message.setFrom(mailModel.getEmailFrom());
               message.setTo(mailModel.getEmailTo());
               message.setSubject(mailModel.getSubject());
               message.setText(mailModel.getText());
               mailSender.send(message);

               mailModel.setStatusEmail(StatusEmail.SENT);
           }catch (MailException e){
                mailModel.setStatusEmail(StatusEmail.ERROR);
           }finally {
               mailService.save(mailModel);
               return ResponseEntity.status(HttpStatus.CREATED).body(movieRentService.save(movieRentModel));
           }
       }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O id do cliente não foi encontrado");
       }



        
    }





}
