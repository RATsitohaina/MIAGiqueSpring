package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les infrastructures sportives
 */
public interface InfrastructureSportiveRepository extends CrudRepository<InfrastructureSportive, Long> {
    /**
     * Recherche des InfrastructureSportiveRepository par nom
     *
     * @param nom de l'infrastructure sportive
     * @return la liste des infrastructures qui correspondent
     */
    List<InfrastructureSportive> findByNom(String nom);
}
