package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpectateurRepository extends CrudRepository<Spectateur, Long> {
    /**
     * Recherche des Utilisateurs par prénom et nom
     * @param prenom le prénom
     * @param nom le nom
     * @return la liste des clients qui correspondent
     */
    List<Spectateur> findByPrenomAndNom(String prenom, String nom);
}
