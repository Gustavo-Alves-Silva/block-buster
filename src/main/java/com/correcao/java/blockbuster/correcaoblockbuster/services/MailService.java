package com.correcao.java.blockbuster.correcaoblockbuster.services;

import com.correcao.java.blockbuster.correcaoblockbuster.models.MailModel;
import com.correcao.java.blockbuster.correcaoblockbuster.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MailService {

    @Autowired
    MailRepository mailRepository;


    public List<MailModel> findAll() {
        return mailRepository.findAll();
    }

    public MailModel save(MailModel mailModel) {
       return  mailRepository.save(mailModel);
    }
}
