package org.miage.procrastinapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.entity.Utilisateur.Niveau;
import org.miage.procrastinapp.entity.Utilisateur.Role;
import org.miage.procrastinapp.exception.EmailDejaUtiliseException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    /**
     * Inscription d’un utilisateur avec rôle par défaut (PROCRASTINATEUR) et niveau DEBUTANT.
     */
    @Transactional
    public Utilisateur inscrireUtilisateur(String pseudo, String email) {
        Optional<Utilisateur> existant = utilisateurRepository.findByEmail(email);
        if (existant.isPresent()) {
            throw new EmailDejaUtiliseException("L'adresse email est déjà utilisée : " + email);
        }

        Utilisateur u = new Utilisateur();
        u.setPseudo(pseudo);
        u.setEmail(email);
        u.setRole(Role.PROCRASTINATEUR);
        u.setNiveau(Niveau.DEBUTANT);
        u.setPoints(0);
        u.setDateInscription(LocalDate.now());
        return utilisateurRepository.save(u);
    }

    /**
     * Récupération par ID avec levée d’exception si inexistant.
     */
    public Utilisateur getById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable avec l'id : " + id));
    }

    /**
     * Récupération par email (utile pour la logique de connexion).
     */
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable avec l'email : " + email));
    }

    /**
     * Mise à jour du niveau ou du rôle (ex: en fonction des points ou d’une montée en grade).
     */
    @Transactional
    public Utilisateur modifierProfil(Long id, Niveau nouveauNiveau, Role nouveauRole) {
        Utilisateur utilisateur = getById(id);
        utilisateur.setNiveau(nouveauNiveau);
        utilisateur.setRole(nouveauRole);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Mise à jour des points (ex: suite à une tâche évitée ou un défi réussi).
     */
    @Transactional
    public Utilisateur ajouterPoints(Long id, int pointsGagnes) {
        Utilisateur utilisateur = getById(id);
        utilisateur.setPoints(utilisateur.getPoints() + pointsGagnes);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Suppression d’un utilisateur (peut être réservé aux gestionnaires).
     */
    @Transactional
    public void supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new UtilisateurNotFoundException("Impossible de supprimer : utilisateur inexistant.");
        }
        utilisateurRepository.deleteById(id);
    }
}
