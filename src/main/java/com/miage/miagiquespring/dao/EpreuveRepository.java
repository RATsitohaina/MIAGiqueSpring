package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO pour les entités l'IEpreuveRepository
 */
public interface EpreuveRepository  extends CrudRepository<Epreuve, Long> {
}
