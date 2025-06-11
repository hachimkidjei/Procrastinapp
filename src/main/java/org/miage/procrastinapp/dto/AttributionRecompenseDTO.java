package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttributionRecompenseDTO {
    private Long utilisateurId;
    private Long recompenseId;
    private String nom;
    private String description;
    private int pointsBonus;
    private LocalDate dateAttribution;
}
