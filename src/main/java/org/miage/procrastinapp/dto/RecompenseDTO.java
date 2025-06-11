package org.miage.procrastinapp.dto;

import lombok.Data;

@Data
public class RecompenseDTO {
    private Long id;
    private String titre;
    private String description;
    private String conditionsObtention;
    private String niveauPrestige;
    private String type;
}
