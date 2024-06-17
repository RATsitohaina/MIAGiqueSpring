package com.miage.miagiquespring.metier;


import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.utilities.InfrastructureSportiveInexistante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bean métier pour la gestion des infrastructures sportive {
 */
@Service
public class ServiceInfrastructureSportive {

    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final InfrastructureSportiveRepository sportiveRepository;
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param sportiveRepository
     * @param infrastructureSportiveRepository
     */
    public ServiceInfrastructureSportive(InfrastructureSportiveRepository sportiveRepository, InfrastructureSportiveRepository infrastructureSportiveRepository) {
        this.sportiveRepository = sportiveRepository;
        this.infrastructureSportiveRepository = infrastructureSportiveRepository;
    }

    /**
     * Créer un infrastructure spotive
     *
     * @param nom
     * @param adresse
     * @param capacite
     * @return
     */
    public InfrastructureSportive creerInfrastructureSportive(String nom, String adresse, int capacite) {
        List<InfrastructureSportive> infrastructureSportiveList = sportiveRepository.findByNom(nom);
        InfrastructureSportive infrastructureSportive;

        if (infrastructureSportiveList.isEmpty()) {
            infrastructureSportive = new InfrastructureSportive();
            infrastructureSportive.setNom(nom);
            infrastructureSportive.setAdresse(adresse);
            infrastructureSportive.setCapacite(capacite);

            // Ajout à la base de donnée
            infrastructureSportive = sportiveRepository.save(infrastructureSportive);
        } else {
            infrastructureSportive = infrastructureSportiveList.get(0);
        }

        return infrastructureSportive;
    }

    /**
     * Récupérer une infrastructure sportive
     *
     * @param infraId
     * @return l'infrastructure qui correspond
     */
    public InfrastructureSportive recupererInfrastructureSportive(Long infraId) {
        // on cherche le infrastucture
        final Optional<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findById(infraId);
        // s'il n'existe pas on lance une exception
        if (optionalInfrastructureSportive.isEmpty())
            throw new InfrastructureSportiveInexistante("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        return optionalInfrastructureSportive.get();
    }

    /**
     * Récupérer une infrastructure sportive
     *
     * @param nom
     * @return l'infrastructure qui correspond
     */
    public InfrastructureSportive recupererInfrastructureSportive(String nom) {
        // on cherche le infrastucture
        final List<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findByNom(nom);
        // s'il n'existe pas on lance une exception
        if (optionalInfrastructureSportive.isEmpty())
            throw new InfrastructureSportiveInexistante("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        return optionalInfrastructureSportive.get(0);
    }

    /**
     * Récupérer toutes les infrastructures sportives
     *
     * @return la liste des infrastructures sportives
     */
    public Iterable<InfrastructureSportive> recupererAllInfrastructureSportive() {
        return infrastructureSportiveRepository.findAll();
    }

    /**
     * Supprimer une infrastructure sportive
     *
     * @param idInfrastructureSportive
     * @return la confirmation de suppression
     */
    public String supprimerInfrastructureSportive(Long idInfrastructureSportive) {
        // on cherche le infrastucture
        final Optional<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findById(idInfrastructureSportive);
        // s'il n'existe pas on lance une exception
        if (optionalInfrastructureSportive.isEmpty())
            throw new InfrastructureSportiveInexistante("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        sportiveRepository.delete(optionalInfrastructureSportive.get());
        return "InfrastructureSportive :" + idInfrastructureSportive + " removed";
    }
}
