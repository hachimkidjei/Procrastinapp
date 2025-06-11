package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipationDefiDTO {
    private Long id;
    private Long utilisateurId;
    private Long defiId;
    private LocalDate dateInscription;
    private String statut; // inscrit, en cours, terminé
    private int pointsGagnes;
}
