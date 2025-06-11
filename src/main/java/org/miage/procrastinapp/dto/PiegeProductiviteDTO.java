package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PiegeProductiviteDTO {
    private Long id;
    private String titre;
    private String description;
    private String type; // JEU, DEFI, MEDITATION
    private Long createurId;
    private int niveauDifficulte;
    private String recompenseSiSucces;
    private String consequenceSiEchec;
    private LocalDateTime dateCreation;
    private String statut; // ACTIF, INACTIF
}
