package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.PiegeProductiviteDTO;
import org.miage.procrastinapp.entity.PiegeProductivite;
import org.miage.procrastinapp.service.PiegeProductiviteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pieges")
@RequiredArgsConstructor
public class PiegeProductiviteController {

    private final PiegeProductiviteService service;

    @PostMapping("/createur/{createurId}")
    public ResponseEntity<PiegeProductiviteDTO> creer(@PathVariable Long createurId, @RequestBody PiegeProductiviteDTO dto) {
        PiegeProductivite entity = service.creerPiege(createurId, fromDTO(dto));
        return ResponseEntity.status(201).body(toDTO(entity));
    }

    @GetMapping
    public ResponseEntity<List<PiegeProductiviteDTO>> listerTous() {
        return ResponseEntity.ok(service.getAll().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<PiegeProductiviteDTO>> listerActifs() {
        return ResponseEntity.ok(service.getPiegesActifs().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/createur/{id}")
    public ResponseEntity<List<PiegeProductiviteDTO>> listerParCreateur(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPiegesParCreateur(id).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimerPiege(id);
        return ResponseEntity.noContent().build();
    }

    // 🔁 Mapping Entité -> DTO
    private PiegeProductiviteDTO toDTO(PiegeProductivite p) {
        PiegeProductiviteDTO dto = new PiegeProductiviteDTO();
        dto.setId(p.getId());
        dto.setTitre(p.getTitre());
        dto.setDescription(p.getDescription());
        dto.setType(p.getType().name());
        dto.setCreateurId(p.getCreateur().getId());
        dto.setNiveauDifficulte(p.getNiveauDifficulte());
        dto.setRecompenseSiSucces(p.getRecompenseSiSucces());
        dto.setConsequenceSiEchec(p.getConsequenceSiEchec());
        dto.setDateCreation(p.getDateCreation());
        dto.setStatut(p.getStatut().name());
        return dto;
    }

    // 🔁 Mapping DTO -> Entité (partiel)
    private PiegeProductivite fromDTO(PiegeProductiviteDTO dto) {
        PiegeProductivite p = new PiegeProductivite();
        p.setTitre(dto.getTitre());
        p.setDescription(dto.getDescription());
        p.setType(PiegeProductivite.TypePiege.valueOf(dto.getType()));
        p.setNiveauDifficulte(dto.getNiveauDifficulte());
        p.setRecompenseSiSucces(dto.getRecompenseSiSucces());
        p.setConsequenceSiEchec(dto.getConsequenceSiEchec());
        p.setStatut(PiegeProductivite.StatutPiege.valueOf(dto.getStatut()));
        return p;
    }
}
