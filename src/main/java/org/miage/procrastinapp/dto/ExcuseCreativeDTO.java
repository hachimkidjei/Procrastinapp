package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExcuseCreativeDTO {
    private Long id;
    private String texte;
    private String situation;
    private String categorie;
    private String statut;
    private int votes;
    private LocalDate dateSoumission;
    private Long auteurId;
}
