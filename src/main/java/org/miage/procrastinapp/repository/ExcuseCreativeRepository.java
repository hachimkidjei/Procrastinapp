package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.ExcuseCreative;
import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcuseCreativeRepository extends JpaRepository<ExcuseCreative, Long> {

    List<ExcuseCreative> findByAuteur(Utilisateur utilisateur);

    List<ExcuseCreative> findByStatut(ExcuseCreative.Statut statut);
}
