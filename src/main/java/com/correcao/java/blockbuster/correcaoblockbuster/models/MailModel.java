package com.correcao.java.blockbuster.correcaoblockbuster.models;

import com.correcao.java.blockbuster.correcaoblockbuster.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_MAIL")
public class MailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID emailId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}
