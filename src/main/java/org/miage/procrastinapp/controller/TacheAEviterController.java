package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.TacheAEviterDTO;
import org.miage.procrastinapp.entity.TacheAEviter;
import org.miage.procrastinapp.service.TacheAEviterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TacheAEviterController {

    private final TacheAEviterService tacheService;

    @PostMapping("/{utilisateurId}")
    public ResponseEntity<TacheAEviterDTO> creer(@PathVariable Long utilisateurId, @RequestBody TacheAEviterDTO dto) {
        TacheAEviter tache = tacheService.creerTache(utilisateurId, toEntity(dto));
        return ResponseEntity.status(201).body(toDTO(tache));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<TacheAEviterDTO>> getTachesParUtilisateur(@PathVariable Long utilisateurId) {
        List<TacheAEviterDTO> dtos = tacheService.listerTachesParUtilisateur(utilisateurId)
                .stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{tacheId}")
    public ResponseEntity<Void> supprimerTache(@PathVariable Long tacheId) {
        tacheService.supprimerTache(tacheId);
        return ResponseEntity.noContent().build();
    }

    // 🔁 Mapping

    private TacheAEviterDTO toDTO(TacheAEviter t) {
        TacheAEviterDTO dto = new TacheAEviterDTO();
        dto.setId(t.getId());
        dto.setUtilisateurId(t.getUtilisateur().getId());
        dto.setDescription(t.getDescription());
        dto.setDegreUrgence(t.getDegreUrgence());
        dto.setDateLimite(t.getDateLimite());
        dto.setConsequencesPotentielles(t.getConsequencesPotentielles());
        dto.setStatut(t.getStatut().name());
        dto.setDateCreation(t.getDateCreation());
        return dto;
    }

    private TacheAEviter toEntity(TacheAEviterDTO dto) {
        TacheAEviter t = new TacheAEviter();
        t.setDescription(dto.getDescription());
        t.setDegreUrgence(dto.getDegreUrgence());
        t.setDateLimite(dto.getDateLimite());
        t.setConsequencesPotentielles(dto.getConsequencesPotentielles());
        return t;
    }
}
