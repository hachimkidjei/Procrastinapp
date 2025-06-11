package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DefiProcrastination {

    public enum Difficulte {
        FACILE, MOYEN, DIFFICILE
    }

    public enum Statut {
        ACTIF, TERMINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titre;

    private String description;

    private int duree; // en jours

    @Enumerated(EnumType.STRING)
    @NotNull
    private Difficulte difficulte;

    private int pointsAGagner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "createur_id")
    private Utilisateur createur;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ACTIF;

    @OneToMany(mappedBy = "defi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipationDefi> participations = new ArrayList<>();
}
