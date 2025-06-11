package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Recherche d’un utilisateur par email.
     * Permet de vérifier l’unicité à l’inscription ou simuler une connexion.
     */
    Optional<Utilisateur> findByEmail(String email);

    /**
     * Vérifie l’existence d’un utilisateur par pseudo (optionnel pour futurs usages).
     */
    boolean existsByPseudo(String pseudo);
}
