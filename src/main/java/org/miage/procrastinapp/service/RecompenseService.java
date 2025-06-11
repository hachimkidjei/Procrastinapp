
package org.miage.procrastinapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.Recompense;
import org.miage.procrastinapp.exception.RessourceIntrouvableException;
import org.miage.procrastinapp.repository.RecompenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecompenseService {

    private final RecompenseRepository recompenseRepository;

    public List<Recompense> listerRecompenses() {
        return recompenseRepository.findAll();
    }

    public Optional<Recompense> chercherParId(Long id) {
        return recompenseRepository.findById(id);
    }

    public Recompense creerRecompense(Recompense recompense) {
        return recompenseRepository.save(recompense);
    }

    public void supprimerRecompense(Long id) {
        recompenseRepository.deleteById(id);
    }

    public Recompense modifierRecompense(Long id, Recompense recompenseModifiee) {
        return recompenseRepository.findById(id).map(r -> {
            r.setTitre(recompenseModifiee.getTitre());
            r.setDescription(recompenseModifiee.getDescription());
            r.setConditionsObtention(recompenseModifiee.getConditionsObtention());
            r.setNiveauPrestige(recompenseModifiee.getNiveauPrestige());
            r.setType(recompenseModifiee.getType());
            return recompenseRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Récompense non trouvée"));
    }
    public Recompense getById(Long id) {
        return recompenseRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Récompense introuvable avec l'id : " + id));
    }

}
