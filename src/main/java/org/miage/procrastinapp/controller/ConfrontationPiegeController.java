package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.ConfrontationPiegeDTO;
import org.miage.procrastinapp.entity.ConfrontationPiege;
import org.miage.procrastinapp.service.ConfrontationPiegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/confrontations")
@RequiredArgsConstructor
public class ConfrontationPiegeController {

    private final ConfrontationPiegeService confrontationService;

    // 🔹 Créer une nouvelle confrontation
    @PostMapping
    public ResponseEntity<ConfrontationPiegeDTO> enregistrer(@RequestBody ConfrontationPiegeDTO dto) {
        ConfrontationPiege confrontation = confrontationService.enregistrerConfrontation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(confrontation));
    }

    // 🔹 Obtenir les confrontations d’un utilisateur
    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<ConfrontationPiegeDTO>> getByUtilisateur(@PathVariable Long id) {
        List<ConfrontationPiegeDTO> result = confrontationService.getConfrontationsParUtilisateur(id)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // 🔹 Obtenir les confrontations liées à un piège
    @GetMapping("/piege/{id}")
    public ResponseEntity<List<ConfrontationPiegeDTO>> getByPiege(@PathVariable Long id) {
        List<ConfrontationPiegeDTO> result = confrontationService.getConfrontationsParPiege(id)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // 🔁 Conversion Entité → DTO
    private ConfrontationPiegeDTO toDTO(ConfrontationPiege cp) {
        ConfrontationPiegeDTO dto = new ConfrontationPiegeDTO();
        dto.setId(cp.getId());
        dto.setUtilisateurId(cp.getUtilisateur().getId());
        dto.setPiegeId(cp.getPiege().getId());
        dto.setDateConfrontation(cp.getDateConfrontation());
        dto.setResultat(cp.getResultat().name());
        dto.setPoints(cp.getPoints());
        dto.setCommentaire(cp.getCommentaire());
        return dto;
    }
}
