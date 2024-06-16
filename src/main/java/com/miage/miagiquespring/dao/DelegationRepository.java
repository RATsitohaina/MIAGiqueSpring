package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Delegation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les délégations
 */
public interface DelegationRepository extends CrudRepository<Delegation, Long> {
    /**
     * Recherche de délégations par nom
     *
     * @param nom
     * @return Delegation
     */
    List<Delegation> findByNom(String nom);
}
