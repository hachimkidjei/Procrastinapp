package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TacheAEviterDTO {
    private Long id;
    private Long utilisateurId;
    private String description;
    private int degreUrgence;
    private LocalDate dateLimite;
    private String consequencesPotentielles;
    private String statut;
    private LocalDate dateCreation;
}
