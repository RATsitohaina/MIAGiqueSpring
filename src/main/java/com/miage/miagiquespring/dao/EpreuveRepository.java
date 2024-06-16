package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Epreuve;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les epreuves
 */
public interface EpreuveRepository extends CrudRepository<Epreuve, Long> {
    /**
     * Recherche des epreuves par nom
     *
     * @param nomEpreuve le prénom
     * @return la liste des epreuves qui correspondent
     */
    List<Epreuve> findByNomEpreuve(String nomEpreuve);
}
