package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Billet;
import org.springframework.data.repository.CrudRepository;


/**
 * DAO pour les billets
 */
public interface BilletRepository extends CrudRepository<Billet, Long> {
}
