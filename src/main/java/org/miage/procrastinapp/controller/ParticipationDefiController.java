package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.ParticipationDefiDTO;
import org.miage.procrastinapp.entity.ParticipationDefi;
import org.miage.procrastinapp.service.ParticipationDefiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participations-defis")
@RequiredArgsConstructor
public class ParticipationDefiController {

    private final ParticipationDefiService participationService;

    @PostMapping
    public ResponseEntity<ParticipationDefiDTO> inscrire(@RequestBody ParticipationDefiDTO dto) {
        ParticipationDefi p = participationService.inscrireUtilisateurADefi(dto.getUtilisateurId(), dto.getDefiId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(p));
    }

    @GetMapping
    public ResponseEntity<List<ParticipationDefiDTO>> getAll() {
        return ResponseEntity.ok(
                participationService.findAll().stream().map(this::toDTO).collect(Collectors.toList())
        );
    }

    @GetMapping("/utilisateur/{idUtilisateur}")
    public ResponseEntity<List<ParticipationDefiDTO>> getByUtilisateur(@PathVariable Long idUtilisateur) {
        return ResponseEntity.ok(
                participationService.getParticipationsParUtilisateur(idUtilisateur).stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/defi/{idDefi}")
    public ResponseEntity<List<ParticipationDefiDTO>> getByDefi(@PathVariable Long idDefi) {
        return ResponseEntity.ok(
                participationService.getParticipationsParDefi(idDefi).stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    private ParticipationDefiDTO toDTO(ParticipationDefi p) {
        ParticipationDefiDTO dto = new ParticipationDefiDTO();
        dto.setId(p.getId());
        dto.setUtilisateurId(p.getUtilisateur().getId());
        dto.setDefiId(p.getDefi().getId());
        dto.setDateInscription(p.getDateInscription());
        dto.setStatut(p.getStatut().name());
        dto.setPointsGagnes(p.getPointsGagnes());
        return dto;
    }
}
