package com.miage.miagiquespring.dao;

import com.miage.miagiquespring.entities.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO pour les participants
 */
public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    /**
     * Recherche des participants par prénom et nom
     *
     * @param prenom le prénom
     * @param nom    le nom
     * @return la liste des participants qui correspondent
     */
    List<Participant> findByPrenomAndNom(String prenom, String nom);
}
