package org.miage.procrastinapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Recompense {

    public enum Type {
        BADGE,
        TITRE_HONORIFIQUE,
        POUVOIR_SPECIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String titre;

    @Lob
    private String description;

    @NotNull
    private String conditionsObtention;

    @NotNull
    private String niveauPrestige;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
}
