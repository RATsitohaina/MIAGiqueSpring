package com.miage.miagiquespring.metier;

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

    private final ParticipantRepository participantRepository;

    public ServiceParticipant(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    /**
     * Demande la création d'un nouveau Spectateur
     * @param prenom prénom du Participant
     * @param nom nom du Participant
     * @param email du Participant
     * @param delegation du Participant
     * @return le nouveau Participant ou l'ancien Spectateur
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
     * Permet de récupérer les infos d'un Spectateur
     * @param idParticipant du Spectateur
     * @return infos du Spectateur
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
     * Permet de récupérer les infos d'un Spectateur
     * @return infos du Spectateur
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
     * Permet de récupérer les toutes les infos des participants
     * @return infos du participant
     */
    public Iterable<Participant> recupererAllParticipant() throws Exception {
        // on cherche le participant
        return participantRepository.findAll();
    }

    /**
     * Permet de récupérer les infos d'un Spectateur
     * @param idParticipant id du Spectateur
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
}
