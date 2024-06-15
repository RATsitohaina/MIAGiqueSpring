package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Organisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrganisateurRepository extends CrudRepository<Organisateur, Long> {
    /**
     * Recherche des Utilisateurs par prénom et nom
     * @param prenom le prénom
     * @param nom le nom
     * @return la liste des clients qui correspondent
     */
    List<Organisateur> findByPrenomAndNom(String prenom, String nom);
}
