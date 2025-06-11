package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributionRecompense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    private Recompense recompense;

    @Column(nullable = false)
    private String nom;

    private String description;

    private LocalDate dateAttribution;

    private int pointsBonus;
}
