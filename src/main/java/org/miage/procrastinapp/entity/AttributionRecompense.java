package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class AttributionRecompense {

    public enum Statut {
        ACTIF,
        EXPIRE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recompense_id")
    private Recompense recompense;

    @NotNull
    private LocalDate dateObtention;

    private LocalDate dateExpiration;

    private String contexteAttribution;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Statut statut;
}
