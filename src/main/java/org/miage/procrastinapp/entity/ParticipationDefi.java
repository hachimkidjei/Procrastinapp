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
public class ParticipationDefi {

    public enum StatutParticipation {
        INSCRIT,
        EN_COURS,
        TERMINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    private DefiProcrastination defi;

    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private StatutParticipation statut;

    private int pointsGagnes;
}
