package com.miage.miagiquespring.metier;


import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bean métier pour la gestion des InfrastructureSportive {
 */
@Service
public class ServiceInfrastructureSportive {

    private final InfrastructureSportiveRepository sportiveRepository;
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;

    public ServiceInfrastructureSportive(InfrastructureSportiveRepository sportiveRepository, InfrastructureSportiveRepository infrastructureSportiveRepository) {
        this.sportiveRepository = sportiveRepository;
        this.infrastructureSportiveRepository = infrastructureSportiveRepository;
    }

    public InfrastructureSportive creerInfrastructureSportive(String nom, String adresse, int capacite) {
        List<InfrastructureSportive> infrastructureSportiveList = sportiveRepository.findByNom(nom);
        InfrastructureSportive infrastructureSportive;

        if(infrastructureSportiveList.isEmpty()) {
            infrastructureSportive = new InfrastructureSportive();
            infrastructureSportive.setNom(nom);
            infrastructureSportive.setAdresse(adresse);
            infrastructureSportive.setCapacite(capacite);

            // Ajout à la base de donnée
            infrastructureSportive = sportiveRepository.save(infrastructureSportive);
        }else {
            infrastructureSportive = infrastructureSportiveList.get(0);
        }

        return infrastructureSportive;
    }

    public InfrastructureSportive recupererInfrastructureSportive(Long infraId) throws Exception {
        // on cherche le infrastucture
        final Optional<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findById(infraId);
        // s'il n'existe pas on lance une exception
        if(optionalInfrastructureSportive.isEmpty())
            throw new Exception("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        return optionalInfrastructureSportive.get();
    }

    public InfrastructureSportive recupererInfrastructureSportive(String nom) throws Exception {
        // on cherche le infrastucture
        final List<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findByNom(nom);
        // s'il n'existe pas on lance une exception
        if(optionalInfrastructureSportive.isEmpty())
          throw new Exception("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        return optionalInfrastructureSportive.get(0);
    }

    public Iterable<InfrastructureSportive> recupererAllInfrastructureSportive() throws Exception {
        // on cherche le infrastucture
        return infrastructureSportiveRepository.findAll();
    }

    public String supprimerInfrastructureSportive(Long idInfrastructureSportive) throws Exception {
        // on cherche le infrastucture
        final Optional<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findById(idInfrastructureSportive);
        // s'il n'existe pas on lance une exception
        if(optionalInfrastructureSportive.isEmpty())
            throw new Exception("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        sportiveRepository.delete(optionalInfrastructureSportive.get());
        return "InfrastructureSportive :"+idInfrastructureSportive+" removed";
    }
}
