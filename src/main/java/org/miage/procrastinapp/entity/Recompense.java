package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Recompense {
    public enum TypeRecompenseEnum {
        BADGE,
        TITRE,
        POUVOIR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    private String description;

    private String conditionsObtention;

    private String niveauPrestige;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRecompenseEnum type;
}
