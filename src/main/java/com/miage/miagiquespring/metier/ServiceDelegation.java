package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.entities.Delegation;
import com.miage.miagiquespring.entities.InfrastructureSportive;

import java.util.List;
import java.util.Optional;

public class ServiceDelegation {

    private final DelegationRepository delegationRepository;

    public ServiceDelegation(DelegationRepository serviceDelegation) {
        this.delegationRepository = serviceDelegation;
    }

    /**
     * Crée une délégation
     * @param nom nom de la délégation
     * @param nbMedailleOr nombre de medailles d'or
     * @param nbMedailleArgent nombre de medailles d'argent
     * @param nbMedailleBronze nombre de medailles de  bronze
     */
    public Delegation creerDelegation(String nom,  int nbMedailleOr, int nbMedailleArgent, int nbMedailleBronze){
        List<Delegation> listeDelegation= delegationRepository.findByNomDelegation(nom);
        Delegation delegation;

        if (listeDelegation.isEmpty()){
            delegation=new Delegation();
            delegation.setNom(nom);
            delegation.setNbMedailleOr(nbMedailleOr);
            delegation.setNbMedailleArgent(nbMedailleArgent);
            delegation.setNbMedailleBronze(nbMedailleBronze);

            // Ajout à la base de donnée
            delegation=delegationRepository.save(delegation);
        }else{
            delegation = listeDelegation.get(0);
        }
        return delegation;
    }

    /**
     *Supprime une délégation
     * @param IdDelegation
     */
    public String supprimerDelegation(Long IdDelegation) throws Exception {
        final Optional<Delegation> optionalDelegation = delegationRepository.findById(IdDelegation);

        if (optionalDelegation.isEmpty()){
            throw new Exception("Délégation inexistante");
        }
        delegationRepository.delete(optionalDelegation.get());

        return "InfrastructureSportive :"+IdDelegation+" removed";
    }

}
