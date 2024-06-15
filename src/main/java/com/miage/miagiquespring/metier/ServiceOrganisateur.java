package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.OrganisateurRepository;
import com.miage.miagiquespring.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bean métier pour la gestion des Organisateur {
 */
@Service
public class ServiceOrganisateur {

    private final OrganisateurRepository organisateurRepository;

    public ServiceOrganisateur(OrganisateurRepository organisateurRepository) {
        this.organisateurRepository = organisateurRepository;
    }

    /**
     * Demande la création d'un nouveau Organisateur
     * @return le nouveau Participant ou l'ancien Spectateur
     */
    public Organisateur creerOrganisateur(String nom, String prenom, String email,
                                          List<Delegation> delegationList, List<Participant> participantList,
                                          List<Resultat> resultatList, List<Epreuve> epreuveList,
                                          List<Billet> billetList, List<InfrastructureSportive> infrastructureSportiveList) {
        //Opération métier
        //On cherche si le client est déjà présent
        List<Organisateur> organisateurs = organisateurRepository.findByPrenomAndNom(prenom, nom);
        Organisateur organisateur;
        // s'il n'est pas présent
        if (organisateurs.isEmpty()) {
            // on le crée
            organisateur = new Organisateur();
            organisateur.setPrenom(prenom);
            organisateur.setNom(nom);
            organisateur.setEmail(email);
            organisateur.setDelegationList(delegationList);
            organisateur.setParticipantList(participantList);
            organisateur.setResultatList(resultatList);
            organisateur.setEpreuveList(epreuveList);
            organisateur.setBilletList(billetList);
            organisateur.setInfrastructureSportiveList(infrastructureSportiveList);

            // on l'ajoute à la BD
            organisateur = organisateurRepository.save(organisateur);
        } else {
            // sinon, on retournera l'ancien
            organisateur = organisateurs.get(0);
        }
        // retourne le client
        return organisateur;
    }

    /**
     * Permet de récupérer les infos d'un Spectateur
     * @param idOrganisateur du Spectateur
     * @return infos du Spectateur
     */
    public Organisateur recupererOrganisateur(long idOrganisateur) throws Exception {
        // on cherche le organisateur
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if(optionalOrganisateur.isEmpty())
            throw new Exception("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get();
    }

    /**
     * Permet de récupérer les infos d'un organisateur
     * @param idOrganisateur
     */
    public String supprimerOrganisateur(long idOrganisateur) throws Exception {
        // on cherche le client
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if(optionalOrganisateur.isEmpty()){
            throw new Exception("Organisateur inexistant");
        }
        organisateurRepository.delete(optionalOrganisateur.get());
        return "Organisateur :"+idOrganisateur+" removed";
    }
}
