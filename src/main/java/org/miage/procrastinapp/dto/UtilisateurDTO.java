package org.miage.procrastinapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UtilisateurDTO {
    private Long id;
    private String pseudo;
    private String email;
    private String role;
    private String niveau;
    private String excusePreferee;
    private LocalDate dateInscription;
    private int points;
}
