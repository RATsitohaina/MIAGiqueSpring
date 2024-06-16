package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Organisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * DAO pour les organisateurs
 */
public interface OrganisateurRepository extends CrudRepository<Organisateur, Long> {
    /**
     * Recherche des organisateurs par prénom et nom
     *
     * @param prenom le prénom
     * @param nom    le nom
     * @return la liste des organisateurs qui correspondent
     */
    List<Organisateur> findByPrenomAndNom(String prenom, String nom);
}
