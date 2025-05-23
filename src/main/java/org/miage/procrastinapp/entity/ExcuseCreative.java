package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ExcuseCreative {

    public enum Statut {
        EN_ATTENTE,
        APPROUVEE,
        REJETEE
    }

    public enum Categorie {
        TRAVAIL,
        ETUDES,
        VIE_SOCIALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Lob
    private String texte;

    private String situation;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Statut statut = Statut.EN_ATTENTE;

    private int votes = 0;

    @NotNull
    private LocalDate dateSoumission;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;
}
