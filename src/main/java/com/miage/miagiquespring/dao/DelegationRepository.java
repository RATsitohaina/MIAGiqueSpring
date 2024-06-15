package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Delegation;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DelegationRepository extends CrudRepository<Delegation, Long> {

    /**
     * DAO pour les délégations
     */
    /**
     * Recherche de délégations par nom
     * @param nom
     * @return Delegation
     */
    List<Delegation> findByNom(String nom);
}
