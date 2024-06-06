package com.miage.miagiquespring.metier;


import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.entities.Epreuve;
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


    public ServiceInfrastructureSportive(InfrastructureSportiveRepository sportiveRepository) {
        this.sportiveRepository = sportiveRepository;
    }

    public InfrastructureSportive creerInfrastructureSportive(String nom, String adresse, int capacite, List<Epreuve> epreuveList) {
        InfrastructureSportive infrastructureSportive = new InfrastructureSportive();

        infrastructureSportive.setNom(nom);
        infrastructureSportive.setAdresse(adresse);
        infrastructureSportive.setCapacite(capacite);
        infrastructureSportive.setEpreuveList(epreuveList);

        // Ajout à la base de donnée
        infrastructureSportive = sportiveRepository.save(infrastructureSportive);
        return infrastructureSportive;
    }

    public InfrastructureSportive recupererInfrastructureSportive(Long idInfrastructureSportive) throws Exception {
        // on cherche le client
        final Optional<InfrastructureSportive> optionalInfrastructureSportive = sportiveRepository.findById(idInfrastructureSportive);
        // s'il n'existe pas on lance une exception
        if(optionalInfrastructureSportive.isEmpty())
          throw new Exception("Erreur infrastucture inexistant");
        // sinon, on renvoie les infos
        return optionalInfrastructureSportive.get();
    }
}
