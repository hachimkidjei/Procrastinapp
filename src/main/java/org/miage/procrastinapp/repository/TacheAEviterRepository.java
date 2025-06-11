package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.TacheAEviter;
import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheAEviterRepository extends JpaRepository<TacheAEviter, Long> {

    List<TacheAEviter> findByUtilisateur(Utilisateur utilisateur);
}
