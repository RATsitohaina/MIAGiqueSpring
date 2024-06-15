package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les entités l'InfrastructureSportiveRepository
 */
public interface InfrastructureSportiveRepository extends CrudRepository<InfrastructureSportive, Long> {
    /**
     * Recherche des InfrastructureSportiveRepository par nom
     * @param nom le prénom
     * @return la liste des clients qui correspondent
     */
    List<InfrastructureSportive> findByNom(String nom);
}
