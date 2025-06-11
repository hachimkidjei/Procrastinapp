package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DefiProcrastinationDTO {
    private Long id;
    private String titre;
    private String description;
    private int duree;
    private String difficulte;
    private int pointsAGagner;
    private Long createurId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
}
