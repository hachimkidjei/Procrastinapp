package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.UtilisateurDTO;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    // 🔹 Inscription (création d'un nouvel utilisateur)
    @PostMapping("/inscription")
    public ResponseEntity<UtilisateurDTO> inscrire(@RequestBody UtilisateurDTO dto) {
        Utilisateur created = utilisateurService.inscrireUtilisateur(dto.getPseudo(), dto.getEmail());
        return ResponseEntity.status(201).body(toDTO(created));
    }

    // 🔹 Obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getById(id);
        return ResponseEntity.ok(toDTO(utilisateur));
    }

    // 🔄 Conversion Entité -> DTO
    private UtilisateurDTO toDTO(Utilisateur u) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(u.getId());
        dto.setPseudo(u.getPseudo());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole().name());
        dto.setNiveau(u.getNiveau().name());
        dto.setExcusePreferee(u.getExcusePreferee());
        dto.setDateInscription(u.getDateInscription());
        dto.setPoints(u.getPoints());
        return dto;
    }
}
