package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Utilisateur {

    public enum Role {
        PROCRASTINATEUR,
        REPENTI,
        GESTIONNAIRE
    }

    public enum Niveau {
        DEBUTANT,
        INTERMEDIAIRE,
        EXPERT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pseudo;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Niveau niveau = Niveau.DEBUTANT;

    private String excusePreferee;

    private LocalDate dateInscription = LocalDate.now();

    private int points = 0;

    // Relations avec les autres entités
    @OneToMany(mappedBy = "utilisateur")
    private List<TacheAEviter> taches;

    @OneToMany(mappedBy = "utilisateur")
    private List<ConfrontationPiege> confrontations;

    @OneToMany(mappedBy = "auteur")
    private List<ExcuseCreative> excuses;

    @OneToMany(mappedBy = "utilisateur")
    private List<AttributionRecompense> recompenses;

    @OneToMany(mappedBy = "utilisateur")
    private List<ParticipationDefi> participations;

    @OneToMany(mappedBy = "createur")
    private List<DefiProcrastination> defisCrees;

    @OneToMany(mappedBy = "createur")
    private List<PiegeProductivite> piegesCrees;
}
