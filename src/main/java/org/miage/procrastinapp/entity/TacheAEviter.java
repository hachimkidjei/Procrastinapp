package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class TacheAEviter {

    public enum Statut {
        EN_ATTENTE,
        EVITEE_AVEC_SUCCES,
        REALISEE_IN_EXTREMIS,
        CATASTROPHE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int degreUrgence;

    @Column(nullable = false)
    private LocalDate dateLimite;

    private String consequencesPotentielles;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;

    private LocalDate dateCreation = LocalDate.now();
}
