package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.entities.Delegation;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        List<Delegation> delegationList = delegationRepository.findByNom(nom);
        Delegation delegation;

        if (delegationList.isEmpty()){
            delegation=new Delegation();
            delegation.setNom(nom);
            delegation.setNbMedailleOr(nbMedailleOr);
            delegation.setNbMedailleArgent(nbMedailleArgent);
            delegation.setNbMedailleBronze(nbMedailleBronze);

            // Ajout à la base de donnée
            delegation = delegationRepository.save(delegation);
        }else{
            delegation = delegationList.get(0);
        }
        return delegation;
    }

    /**
     *  Récuperer une délégation
     * @param IdDelegation
     */
    public Delegation recupererDelegation(Long IdDelegation) throws Exception {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(IdDelegation);

        if (optionalDelegation.isEmpty()){
            throw new Exception("Délégation inexistante");
        }
       return optionalDelegation.get();
    }

    /**
     *Supprime une délégation
     * @param IdDelegation
     */
    public String supprimerDelegation(Long IdDelegation) throws Exception {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(IdDelegation);
        if (optionalDelegation.isEmpty()){
            throw new Exception("Délégation inexistante");
        }
        delegationRepository.delete(optionalDelegation.get());
        return "Delegation :"+IdDelegation+" removed";
    }
}
