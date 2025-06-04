package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.DefiProcrastinationDTO;
import org.miage.procrastinapp.entity.DefiProcrastination;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.service.DefiProcrastinationService;
import org.miage.procrastinapp.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/defis")
@RequiredArgsConstructor
public class DefiProcrastinationController {

    private final DefiProcrastinationService defiService;
    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<DefiProcrastinationDTO> creer(@RequestBody DefiProcrastinationDTO dto) {
        Utilisateur createur = utilisateurService.getById(dto.getCreateurId());

        DefiProcrastination defi = new DefiProcrastination();
        defi.setTitre(dto.getTitre());
        defi.setDescription(dto.getDescription());
        defi.setDuree(dto.getDuree());
        defi.setDifficulte(DefiProcrastination.Difficulte.valueOf(dto.getDifficulte()));
        defi.setPointsAGagner(dto.getPointsAGagner());
        defi.setCreateur(createur);
        defi.setDateDebut(dto.getDateDebut());
        defi.setDateFin(dto.getDateFin());
        defi.setStatut(DefiProcrastination.Statut.valueOf(dto.getStatut()));

        DefiProcrastination created = defiService.creerDefi(defi);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<DefiProcrastinationDTO>> getAll() {
        return ResponseEntity.ok(
                defiService.getAll().stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/createur/{utilisateurId}")
    public ResponseEntity<List<DefiProcrastinationDTO>> getParCreateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(
                defiService.getDefisParCreateur(utilisateurId).stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @DeleteMapping("/{defiId}")
    public ResponseEntity<Void> delete(@PathVariable Long defiId) {
        defiService.supprimerDefi(defiId);
        return ResponseEntity.noContent().build();
    }

    // 🔁 Conversion Entité → DTO
    private DefiProcrastinationDTO toDTO(DefiProcrastination defi) {
        DefiProcrastinationDTO dto = new DefiProcrastinationDTO();
        dto.setId(defi.getId());
        dto.setTitre(defi.getTitre());
        dto.setDescription(defi.getDescription());
        dto.setDuree(defi.getDuree());
        dto.setDifficulte(defi.getDifficulte().name());
        dto.setPointsAGagner(defi.getPointsAGagner());
        dto.setCreateurId(defi.getCreateur().getId());
        dto.setDateDebut(defi.getDateDebut());
        dto.setDateFin(defi.getDateFin());
        dto.setStatut(defi.getStatut().name());
        return dto;
    }
}
