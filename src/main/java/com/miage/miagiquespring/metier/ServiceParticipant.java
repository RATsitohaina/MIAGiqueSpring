package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.ParticipantRepository;
import com.miage.miagiquespring.entities.*;
import org.springframework.stereotype.Service;

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

    /**
     * Constructeur pour l'injection du bean repository
     * @param participantRepository
     * @param epreuveRepository
     */
    public ServiceParticipant(ParticipantRepository participantRepository, EpreuveRepository epreuveRepository) {
        this.participantRepository = participantRepository;
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Créer un participant
     * @param prenom prénom du Participant
     * @param nom nom du Participant
     * @param email du Participant
     * @param delegation du Participant
     * @return le nouveau participant ou l'ancien Spectateur
     */
    public Participant creerParticipant(String nom, String prenom, String email, Delegation delegation, List<Resultat> resultatList, List<Epreuve> epreuveList) {
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
            participant.setResultatList(resultatList);
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
     * @param idParticipant
     * @return le participant qui correspond
     * @throws Exception
     */
    public Participant recupererParticipant(long idParticipant) throws Exception {
        // on cherche le participant
        final Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
        // s'il n'existe pas on lance une exception
        if(optionalParticipant.isEmpty())
            throw new Exception("Participant inexistant");
        // sinon, on renvoie les infos
        return optionalParticipant.get();
    }

    /**
     * Récuperer un participant
     * @param nom
     * @param prenom
     * @return le participant qui correspond
     * @throws Exception
     */
    public Participant recupererParticipant(String nom, String prenom) throws Exception {
        // on cherche le participant
        final List<Participant> optionalParticipant = participantRepository.findByPrenomAndNom(prenom,nom);
        // s'il n'existe pas on lance une exception
        if(optionalParticipant.isEmpty())
            throw new Exception("Participant inexistant");
        // sinon, on renvoie les infos
        return optionalParticipant.get(0);
    }

    /**
     * Récupérer tous les participants
     * @return la liste des participants
     * @throws Exception
     */
    public Iterable<Participant> recupererAllParticipant() throws Exception {
        // on cherche le participant
        return participantRepository.findAll();
    }

    /**
     * Supprimer un participant
     * @param idParticipant
     * @return la confirmation de suppression
     * @throws Exception
     */
    public String supprimerParticipant(long idParticipant) throws Exception {
        // on cherche le client
        final Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
        // s'il n'existe pas on lance une exception
        if(optionalParticipant.isEmpty()){
            throw new Exception("Participant inexistant");
        }
        participantRepository.delete(optionalParticipant.get());
        return "Participant :"+idParticipant+" removed";
    }

    /**
     * ACTION POSSIBLE POUR LE PARTICIPANT
     */

    /** PROCESSUS D'INSCRIPTION AUX EPREUVES
     * Participer a une epreuve
     * Et avoir une confirmation
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
        Participant participant = recupererParticipant(prenomParticipant,nomParticipant);

        //Vérification existance et recuperation epreuve
        List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        if (optionalEpreuve.isEmpty()){
            throw new Exception("Epreuve inexistante");
        }
        Epreuve epreuve = optionalEpreuve.get(0);

        //Ajout du l'epreuve chez le participant
        List<Epreuve> epreuveList = participant.getEpreuveList();
        epreuveList.add(epreuve);

        //Verification si 10 Jours

        participant.setEpreuveList(epreuveList);
        participantRepository.save(participant);

        return "/** CONFIRMATION **/ Participant :"+nomParticipant+
                " : "+prenomParticipant+" inscrit à : "+nomEpreuve;
    }

    /**
     * Se désengager d'un épreuve
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
        Participant participant = recupererParticipant(prenomParticipant,nomParticipant);

        //Vérification existance et recuperation epreuve
        List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        if (optionalEpreuve.isEmpty()){
            throw new Exception("Epreuve inexistante");
        }
        Epreuve epreuve = optionalEpreuve.get(0);

        //Vérification si participant inscrit
        List<Epreuve> epreuveList = participant.getEpreuveList();
        if(epreuveList.contains(epreuve)){
            epreuveList.remove(epreuve);
            participant.setEpreuveList(epreuveList);
            participantRepository.save(participant);
        }else{
            throw new Exception("Participant non inscrit");
        }

        return "/** FORFAIT **/ Participant :"+nomParticipant+
                " : "+prenomParticipant+" déinscrit à : "+nomEpreuve;
    }
}
