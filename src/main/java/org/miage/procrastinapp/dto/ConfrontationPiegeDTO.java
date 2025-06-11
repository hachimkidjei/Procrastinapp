package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConfrontationPiegeDTO {
    private Long id;
    private Long utilisateurId;
    private Long piegeId;
    private LocalDate dateConfrontation;
    private String resultat;
    private int points;
    private String commentaire;
}
