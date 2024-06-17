package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.entities.Delegation;
import com.miage.miagiquespring.utilities.DelegationInexistante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Bean métier pour la gestion des délégations {
 */
@Service
public class ServiceDelegation {

    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final DelegationRepository delegationRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param serviceDelegation
     */
    public ServiceDelegation(DelegationRepository serviceDelegation) {
        this.delegationRepository = serviceDelegation;
    }

    /**
     * Créer un délégations
     *
     * @param nom
     * @param nbMedailleOr
     * @param nbMedailleArgent
     * @param nbMedailleBronze
     * @return
     */
    public Delegation creerDelegation(String nom, int nbMedailleOr, int nbMedailleArgent, int nbMedailleBronze) {
        List<Delegation> delegationList = delegationRepository.findByNom(nom);
        Delegation delegation;

        if (delegationList.isEmpty()) {
            delegation = new Delegation();
            delegation.setNom(nom);
            delegation.setNbMedailleOr(nbMedailleOr);
            delegation.setNbMedailleArgent(nbMedailleArgent);
            delegation.setNbMedailleBronze(nbMedailleBronze);

            // Ajout à la base de donnée
            delegation = delegationRepository.save(delegation);
        } else {
            delegation = delegationList.get(0);
        }
        return delegation;
    }

    /**
     * Récupérer une délégation
     *
     * @param IdDelegation
     * @return la délégation qui correspond
     */
    public Delegation recupererDelegation(Long IdDelegation) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(IdDelegation);

        if (optionalDelegation.isEmpty()) {
            throw new DelegationInexistante("Délégation inexistante");
        }
        return optionalDelegation.get();
    }

    /**
     * Récupérer une délégations
     *
     * @param nomDelegation
     * @return la délégation qui correspond
     */
    public Delegation recupererDelegation(String nomDelegation) {
        List<Delegation> optionalDelegation = delegationRepository.findByNom(nomDelegation);

        if (optionalDelegation.isEmpty()) {
            throw new DelegationInexistante("Délégation inexistante");
        }
        return optionalDelegation.get(0);
    }

    /**
     * Récupérer toutes les délégations
     *
     * @return la liste des délégations
     */
    public Iterable<Delegation> recupererAllDelegation() {
        return delegationRepository.findAll();
    }

    /**
     * Supprimer une délégation
     *
     * @param IdDelegation
     * @return une confirtmation de suppression
     * @throws Exception
     */
    public String supprimerDelegation(Long IdDelegation) throws Exception {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(IdDelegation);
        if (optionalDelegation.isEmpty()) {
            throw new DelegationInexistante("Délégation inexistante");
        }
        delegationRepository.delete(optionalDelegation.get());
        return "Delegation :" + IdDelegation + " removed";
    }
}
