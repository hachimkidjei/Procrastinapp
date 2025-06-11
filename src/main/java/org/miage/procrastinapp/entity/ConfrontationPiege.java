package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ConfrontationPiege {

    public enum Resultat {
        SUCCES, // Résistance à la productivité
        ECHEC   // Productivité accidentelle
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou AUTO
    private Long id;


    @NotNull
    private LocalDate dateConfrontation;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Resultat resultat;

    @NotNull
    private int points;

    private String commentaire;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "piege_id")
    private PiegeProductivite piege;
}
