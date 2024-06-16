package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Resultat;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO pour les resultats
 */
public interface ResultatRepository extends CrudRepository<Resultat, Long> {
}
