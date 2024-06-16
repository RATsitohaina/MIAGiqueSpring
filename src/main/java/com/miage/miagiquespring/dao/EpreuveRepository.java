package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les entités l'IEpreuveRepository
 */
public interface EpreuveRepository  extends CrudRepository<Epreuve, Long> {
    /**
     * Recherche des epreuves nom
     * @param nomEpreuve le prénom
     * @return la liste des clients qui correspondent
     */
    List<Epreuve> findByNomEpreuve(String nomEpreuve);
}
