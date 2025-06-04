package org.miage.procrastinapp.controller;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.Recompense;
import org.miage.procrastinapp.service.RecompenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recompenses/catalogue")
@RequiredArgsConstructor
public class RecompenseController {

    private final RecompenseService service;

    @GetMapping
    public ResponseEntity<List<Recompense>> lister() {
        return ResponseEntity.ok(service.listerRecompenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompense> chercher(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Recompense> creer(@RequestBody Recompense recompense) {
        return ResponseEntity.status(201).body(service.creerRecompense(recompense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompense> modifier(@PathVariable Long id, @RequestBody Recompense recompense) {
        return ResponseEntity.ok(service.modifierRecompense(id, recompense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimerRecompense(id);
        return ResponseEntity.noContent().build();
    }
}
