package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.AttributionRecompenseDTO;
import org.miage.procrastinapp.entity.AttributionRecompense;
import org.miage.procrastinapp.service.AttributionRecompenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributions")
@RequiredArgsConstructor
public class AttributionRecompenseController {

    private final AttributionRecompenseService service;

    @PostMapping
    public ResponseEntity<AttributionRecompense> creer(@RequestBody AttributionRecompenseDTO dto) {
        return ResponseEntity.status(201).body(service.creerAttribution(dto));
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<AttributionRecompense>> getParUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByUtilisateur(id));
    }
}
