package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.DefiProcrastination;
import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefiProcrastinationRepository extends JpaRepository<DefiProcrastination, Long> {
    List<DefiProcrastination> findByCreateur(Utilisateur createur);
}
