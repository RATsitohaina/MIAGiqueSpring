package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.OrganisateurRepository;
import com.miage.miagiquespring.dao.ParticipantRepository;
import com.miage.miagiquespring.entities.*;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Bean métier pour la gestion des participants {
 */
@Service
public class ServiceParticipant {

    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final ParticipantRepository participantRepository;
    private final EpreuveRepository epreuveRepository;
    private final DelegationRepository delegationRepository;
    private final OrganisateurRepository organisateurRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param participantRepository
     * @param epreuveRepository
     * @param delegationRepository
     */
    public ServiceParticipant(ParticipantRepository participantRepository, EpreuveRepository epreuveRepository, DelegationRepository delegationRepository, OrganisateurRepository organisateurRepository) {
        this.participantRepository = participantRepository;
        this.epreuveRepository = epreuveRepository;
        this.delegationRepository = delegationRepository;
        this.organisateurRepository = organisateurRepository;
    }

    /**
     * Créer un participant
     *
     * @param prenom     prénom du Participant
     * @param nom        nom du Participant
     * @param email      du Participant
     * @param delegation du Participant
     * @return le nouveau participant ou l'ancien Spectateur
     */
    public Participant creerParticipant(String nom, String prenom, String email, Delegation delegation, List<Epreuve> epreuveList) {
        //Opération métier
        //On cherche si le client est déjà présent
        List<Participant> participants = participantRepository.findByPrenomAndNom(prenom, nom);
        Participant participant;
        // s'il n'est pas présent
        if (participants.isEmpty()) {
            // on le crée
            participant = new Participant();
            participant.setPrenom(prenom);
            participant.setNom(nom);
            participant.setEmail(email);
            participant.setDelegation(delegation);
            participant.setEpreuveList(epreuveList);

            // on l'ajoute à la BD
            participant = participantRepository.save(participant);
        } else {
            // sinon, on retournera l'ancien
            participant = participants.get(0);
        }
        // retourne le client
        return participant;
    }

    /**
     * Récuperer un participant
     *
     * @param idParticipant
     * @return le participant qui correspond
     * @throws Exception
     */
    public Participant recupererParticipant(long idParticipant) throws Exception {
        // on cherche le participant
        final Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
        // s'il n'existe pas on lance une exception
        if (optionalParticipant.isEmpty())
            throw new Exception("Participant inexistant");
        // sinon, on renvoie les infos
        return optionalParticipant.get();
    }

    /**
     * Récuperer un participant
     *
     * @param nom
     * @param prenom
     * @return le participant qui correspond
     * @throws Exception
     */
    public Participant recupererParticipant(String nom, String prenom) throws Exception {
        // on cherche le participant
        final List<Participant> optionalParticipant = participantRepository.findByPrenomAndNom(prenom, nom);
        // s'il n'existe pas on lance une exception
        if (optionalParticipant.isEmpty())
            throw new Exception("Participant inexistant");
        // sinon, on renvoie les infos
        return optionalParticipant.get(0);
    }

    /**
     * Récupérer tous les participants
     *
     * @return la liste des participants
     * @throws Exception
     */
    public Iterable<Participant> recupererAllParticipant() throws Exception {
        // on cherche le participant
        return participantRepository.findAll();
    }

    /**
     * Supprimer un participant
     *
     * @param idParticipant
     * @return la confirmation de suppression
     * @throws Exception
     */
    public String supprimerParticipant(long idParticipant) throws Exception {
        // on cherche le client
        final Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
        // s'il n'existe pas on lance une exception
        if (optionalParticipant.isEmpty()) {
            throw new Exception("Participant inexistant");
        }
        participantRepository.delete(optionalParticipant.get());
        return "Participant :" + idParticipant + " removed";
    }

    /**
     * ACTION POSSIBLE POUR LE PARTICIPANT
     */

    /**
     * PROCESSUS D'INSCRIPTION AUX EPREUVES
     * Participer a une epreuve
     * Et avoir une confirmation
     *
     * @param nomParticipant
     * @param prenomParticipant
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    public String participerEpreuve(String nomParticipant,
                                    String prenomParticipant,
                                    String nomEpreuve) throws Exception {

        //Vérification existance et recupreation participant
        Participant participant = recupererParticipant(prenomParticipant, nomParticipant);

        //Vérification existance et recuperation epreuve
        List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        if (optionalEpreuve.isEmpty()) {
            throw new Exception("Epreuve inexistante");
        }
        Epreuve epreuve = optionalEpreuve.get(0);

        //Verification si 10 Jours ou plus SINON EXCEPTION
        Calendar recuperateurDeDate = Calendar.getInstance();
        Date dateCourante = recuperateurDeDate.getTime();
        recuperateurDeDate.setTime(epreuve.getDateEpreuve());
        recuperateurDeDate.add(Calendar.DAY_OF_YEAR, -10);
        Date dateFinInscription = recuperateurDeDate.getTime();

        if (dateCourante.after(dateFinInscription)) {
            throw new Exception("Inscription cloturé : 10 jours avant la date prévue.");
        }
        //Ajout du l'epreuve chez le participant
        List<Epreuve> epreuveList = participant.getEpreuveList();
        epreuveList.add(epreuve);


        participant.setEpreuveList(epreuveList);
        participantRepository.save(participant);

        return "/** CONFIRMATION **/ Participant :" + nomParticipant +
                " : " + prenomParticipant + " inscrit à : " + nomEpreuve;
    }

    /**
     * Se désengager d'un épreuve
     *
     * @param nomParticipant
     * @param prenomParticipant
     * @param nomEpreuve
     * @return la confirmation de désengagement de l'épreuve
     * @throws Exception
     */
    public String desengagerEpreuve(String nomParticipant,
                                    String prenomParticipant,
                                    String nomEpreuve) throws Exception {

        //Vérification existance et recupreation participant
        Participant participant = recupererParticipant(prenomParticipant, nomParticipant);

        //Vérification existance et recuperation epreuve
        List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        if (optionalEpreuve.isEmpty()) {
            throw new Exception("Epreuve inexistante");
        }
        Epreuve epreuve = optionalEpreuve.get(0);

        //Vérification si participant inscrit
        List<Epreuve> epreuveList = participant.getEpreuveList();
        if (epreuveList.contains(epreuve)) {
            epreuveList.remove(epreuve);
            participant.setEpreuveList(epreuveList);
            participantRepository.save(participant);
        } else {
            throw new Exception("Participant non inscrit");
        }

        //Verification si 10 Jours avant l'épreuve
        Calendar recuperateurDeDate = Calendar.getInstance();
        Date dateCourante = recuperateurDeDate.getTime();
        recuperateurDeDate.setTime(epreuve.getDateEpreuve());
        recuperateurDeDate.add(Calendar.DAY_OF_YEAR, -10);
        Date dateFinInscription = recuperateurDeDate.getTime();

        if (dateCourante.after(dateFinInscription)) {
            return "/** FORFAIT **/ Participant :" + nomParticipant +
                    " : " + prenomParticipant + " déinscrit à : " + nomEpreuve;
        }

        return "/** DESENGAGEMENT - réinscription possible **/ Participant :" + nomParticipant +
                " : " + prenomParticipant + " déinscrit à : " + nomEpreuve;
    }

    /**
     * PROCESSUS DE GESTION DES DELEGATIONS
     * Ajout de la delegation dans participant
     *
     * @param nomDelegation
     * @param prenomParticipant
     * @param nomParticipant
     * @return
     * @throws Exception
     */
    public String ajouterParticipant(String nomOrganisateur, String prenomOrganisateur, String nomDelegation, String prenomParticipant, String nomParticipant) throws Exception {
        //verification des roles de l'organisateur
        List<Organisateur> optionalOrganisateur = organisateurRepository.findByPrenomAndNom(prenomOrganisateur, nomOrganisateur);
        if (optionalOrganisateur.isEmpty()) {
            throw new Exception("Organisateur inexistante");
        }
        Organisateur organisateur = optionalOrganisateur.get(0);

        if (!organisateur.getRoleOrganisateur()) {
            throw new Exception("Cet organisateur est un controlleur");
        }

        //Vérification existance et récupération delegation
        List<Delegation> optionalDelegation = delegationRepository.findByNom(nomDelegation);
        if (optionalDelegation.isEmpty()) {
            throw new Exception("Délégation inexistante");
        }
        Delegation delegation = optionalDelegation.get(0);

        //Vérification existance et recuperation participant
        List<Participant> optionalParticipant = participantRepository.findByPrenomAndNom(prenomParticipant, nomParticipant);
        if (optionalDelegation.isEmpty()) {
            throw new Exception("Participant inexistante");
        }
        Participant participant = optionalParticipant.get(0);

        //Ajout du participant dans la delegation
        participant.setDelegation(delegation);
        participantRepository.save(participant);

        return "Delegation :" + nomDelegation + " added in " + nomParticipant;
    }
}
