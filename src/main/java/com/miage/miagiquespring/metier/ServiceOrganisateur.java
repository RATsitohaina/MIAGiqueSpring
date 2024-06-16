package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.dao.OrganisateurRepository;
import com.miage.miagiquespring.dao.ParticipantRepository;
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

    private final ParticipantRepository participantRepository;
    private final DelegationRepository delegationRepository;

    public ServiceOrganisateur(OrganisateurRepository organisateurRepository, ParticipantRepository participantRepository, DelegationRepository delegationRepository) {
        this.organisateurRepository = organisateurRepository;
        this.participantRepository = participantRepository;
        this.delegationRepository = delegationRepository;
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
     * Permet de récupérer les infos d'un Spectateur
     * @return infos du Spectateur
     */
    public Organisateur recupererOrganisateur(String nom, String prenom) throws Exception {
        // on cherche le organisateur
        final List<Organisateur> optionalOrganisateur = organisateurRepository.findByPrenomAndNom(prenom,nom);
        // s'il n'existe pas on lance une exception
        if(optionalOrganisateur.isEmpty())
            throw new Exception("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get(0);
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

    /**
     * Permet de récupérer les infos des organisateurs
     * @return infos des organisateurs
     */
    public Iterable<Organisateur> recupererAllOrganisateur() throws Exception {
        // on cherche le organisateur
        return organisateurRepository.findAll();
    }

    /**
     * ACTION POSSIBLE POUR L'ORGANISATEUR
     */


    /** PROCESSUS DE GESTION DES DELEGATIONS
     * Ajout de la delegation dans participant
     * @param nomDelegation
     * @param prenomParticipant
     * @param nomParticipant
     * @return
     * @throws Exception
     */
    public String ajouterParticipant(String nomDelegation, String prenomParticipant, String nomParticipant) throws Exception {
        //Vérification existance et recupreation delegation
        List<Delegation> optionalDelegation = delegationRepository.findByNom(nomDelegation);
        if (optionalDelegation.isEmpty()){
            throw new Exception("Délégation inexistante");
        }
        Delegation delegation = optionalDelegation.get(0);

        //Vérification existance et recuperation participant
        List<Participant> optionalParticipant = participantRepository.findByPrenomAndNom(prenomParticipant,nomParticipant);
        if (optionalDelegation.isEmpty()){
            throw new Exception("Participant inexistante");
        }
        Participant participant = optionalParticipant.get(0);

        //Ajout du participant dans la delegation
        participant.setDelegation(delegation);
        participantRepository.save(participant);

        return "Delegation :"+nomDelegation+" added in "+nomParticipant;
    }

}
