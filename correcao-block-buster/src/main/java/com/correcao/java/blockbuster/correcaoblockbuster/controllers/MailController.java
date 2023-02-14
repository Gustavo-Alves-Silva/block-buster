package com.correcao.java.blockbuster.correcaoblockbuster.controllers;

import com.correcao.java.blockbuster.correcaoblockbuster.models.MailModel;
import com.correcao.java.blockbuster.correcaoblockbuster.services.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class MailController {


    MailService mailService;

    @GetMapping
    public ResponseEntity<List<MailModel>> findAllMails(){
        return ResponseEntity.status(HttpStatus.FOUND).body(mailService.findAll());
    }

}
