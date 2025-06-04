package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.ExcuseCreativeDTO;
import org.miage.procrastinapp.entity.ExcuseCreative;
import org.miage.procrastinapp.service.ExcuseCreativeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/excuses")
@RequiredArgsConstructor
public class ExcuseCreativeController {

    private final ExcuseCreativeService excuseService;

    @PostMapping("/{auteurId}")
    public ResponseEntity<ExcuseCreativeDTO> soumettre(@PathVariable Long auteurId, @RequestBody ExcuseCreativeDTO dto) {
        ExcuseCreative excuse = excuseService.soumettreExcuse(auteurId, toEntity(dto));
        return ResponseEntity.status(201).body(toDTO(excuse));
    }

    @PatchMapping("/{id}/approuver")
    public ResponseEntity<ExcuseCreativeDTO> approuver(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(excuseService.approuverExcuse(id)));
    }

    @PatchMapping("/{id}/rejeter")
    public ResponseEntity<ExcuseCreativeDTO> rejeter(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(excuseService.rejeterExcuse(id)));
    }

    @PatchMapping("/{id}/voter")
    public ResponseEntity<ExcuseCreativeDTO> voter(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(excuseService.voterPourExcuse(id)));
    }

    @GetMapping("/approuvees")
    public ResponseEntity<List<ExcuseCreativeDTO>> getApprouvees() {
        List<ExcuseCreativeDTO> dtos = excuseService.getExcusesApprouvees()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private ExcuseCreativeDTO toDTO(ExcuseCreative e) {
        ExcuseCreativeDTO dto = new ExcuseCreativeDTO();
        dto.setId(e.getId());
        dto.setTexte(e.getTexte());
        dto.setSituation(e.getSituation());
        dto.setCategorie(e.getCategorie().name());
        dto.setStatut(e.getStatut().name());
        dto.setVotes(e.getVotes());
        dto.setDateSoumission(e.getDateSoumission());
        dto.setAuteurId(e.getAuteur().getId());
        return dto;
    }

    private ExcuseCreative toEntity(ExcuseCreativeDTO dto) {
        ExcuseCreative e = new ExcuseCreative();
        e.setTexte(dto.getTexte());
        e.setSituation(dto.getSituation());
        e.setCategorie(ExcuseCreative.Categorie.valueOf(dto.getCategorie().toUpperCase()));
        return e;
    }
}
