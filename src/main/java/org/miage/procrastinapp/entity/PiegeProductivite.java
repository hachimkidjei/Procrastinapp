package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PiegeProductivite {

    public enum TypePiege {
        JEU, DEFI, MEDITATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String titre;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TypePiege type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "createur_id")
    private Utilisateur createur; // doit être de rôle ANTI_PROCRASTINATEUR_REPENTI

    @NotNull
    private int niveauDifficulte;

    @NotNull
    private String recompenseSiSucces;

    @NotNull
    private String consequenceSiEchec;

    @NotNull
    private LocalDateTime dateCreation;

    @NotNull
    private boolean actif;
}
