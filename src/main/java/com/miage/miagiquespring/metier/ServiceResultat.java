package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.DelegationRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.ParticipantRepository;
import com.miage.miagiquespring.dao.ResultatRepository;
import com.miage.miagiquespring.entities.*;
import com.miage.miagiquespring.utilities.EpreuveInexistant;
import com.miage.miagiquespring.utilities.ParticipantInexistant;
import com.miage.miagiquespring.utilities.ResultatInexistant;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Bean métier pour la gestion des resultats {
 */
@Service
public class ServiceResultat {
    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final ResultatRepository resultatRepository;
    private final ParticipantRepository participantRepository;
    private final EpreuveRepository epreuveRepository;
    private final DelegationRepository delegationRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param resultatRepository
     * @param epreuveRepository
     * @param participantRepository
     * @param delegationRepository
     */
    public ServiceResultat(ResultatRepository resultatRepository, ParticipantRepository participantRepository, EpreuveRepository epreuveRepository, DelegationRepository delegationRepository) {
        this.resultatRepository = resultatRepository;
        this.participantRepository = participantRepository;
        this.epreuveRepository = epreuveRepository;
        this.delegationRepository = delegationRepository;
    }


    /**
     * Créer un résultat
     *
     * @param idEpreuve
     * @param idParticipant
     * @param temps
     * @param position
     * @return
     */
    public Resultat creerResultat(Long idEpreuve, Long idParticipant, float temps, int position) throws Exception {
        //Opération métier
        //On cherche si le client est déjà présent
        Resultat resultat = new Resultat();
        resultat.setIdEpreuve(idEpreuve);
        resultat.setIdParticipant(idParticipant);
        resultat.setTemps(temps);
        resultat.setPosition(position);
        MajNbMedaille(resultat);
        //Ajout à la BD
        resultatRepository.save(resultat);
        return resultat;
    }

    /**
     * Récuperer un résultat
     *
     * @param idResultat
     * @return le résultat qui correspond
     */
    public Resultat recupererResultat(long idResultat) {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if (optionalResultat.isEmpty())
            throw new ResultatInexistant("Resultat inexistant");
        // sinon, on renvoie les infos
        return optionalResultat.get();
    }

    /**
     * Récuperer tous les résultats
     *
     * @return
     */
    public Iterable<Resultat> recupererAllResultat() {
        // on cherche le Resultat
        return resultatRepository.findAll();
    }

    /**
     * Supprimer un résultat
     *
     * @param idResultat
     * @return confirmation de suppression
     */
    public String supprimerResultat(long idResultat) {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if (optionalResultat.isEmpty()) {
            throw new ResultatInexistant("Resultat inexistant");
        }
        resultatRepository.delete(optionalResultat.get());
        return "Resultat :" + idResultat + " removed";
    }

    /**
     * AFFICHER CLASSEMENT
     * Permet d'afficher un classement des résultats
     * <nomDelegation, nomEpreuve, nomParticipant, nbMedaille>
     */
    public Map<String, Map<String, Map<String, Integer>>> afficherClassement() {

        // Récupération de tous les résultats
        Iterable<Resultat> resultats = resultatRepository.findAll();

        // Classement
        Map<String, Map<String, Map<String, Integer>>> classement = new HashMap<>();

        for (Resultat resultat : resultats) {
            // Récupération de l'épreuve
            Long idEpreuve = resultat.getIdEpreuve();
            Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
            if (optionalEpreuve.isEmpty()) {
                throw new EpreuveInexistant("Epreuve inexistant");
            }
            Epreuve epreuve = optionalEpreuve.get();

            // Récupération participant
            Long idParticipant = resultat.getIdParticipant();
            Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
            if (optionalParticipant.isEmpty()) {
                throw new ParticipantInexistant("Participant inexistant");
            }
            Participant participant = optionalParticipant.get();

            // Récupération délégation du participant
            Delegation participantDelegation = participant.getDelegation();

            // Initialisation de la structure de classement
            classement.putIfAbsent(participantDelegation.getNom(), new HashMap<>());
            classement.get(participantDelegation.getNom()).putIfAbsent(epreuve.getNomEpreuve(), new HashMap<>());
            classement.get(participantDelegation.getNom()).get(epreuve.getNomEpreuve()).putIfAbsent(participant.getNom(), 0);

            // MAJ du nombre de médailles
            if (resultat.getPosition() == 1) {
                int nbMedaille = classement.get(participantDelegation.getNom()).get(epreuve.getNomEpreuve()).get(participant.getNom());
                classement.get(participantDelegation.getNom()).get(epreuve.getNomEpreuve()).put(participant.getNom(), nbMedaille + 1);
            }
        }

        return classement;
    }


    /**
     * MAJ des nombres de medaille
     *
     * @param resultat
     * @return
     * @throws Exception
     */
    public String MajNbMedaille(Resultat resultat) {
        // Récupération participant
        Long idParticipant = resultat.getIdParticipant();
        Optional<Participant> optionalParticipant = participantRepository.findById(idParticipant);
        if (optionalParticipant.isEmpty()) {
            throw new ParticipantInexistant("Participant inexistant");
        }
        Participant participant = optionalParticipant.get();

        // Récupération délégation du participant
        Delegation participantDelegation = participant.getDelegation();

        //MISE A JOUR NB MEDAILLE
        switch (resultat.getPosition()) {
            case 1: {
                participantDelegation.setNbMedailleOr(participantDelegation.getNbMedailleOr() + 1);
                break;
            }
            case 2: {
                participantDelegation.setNbMedailleArgent(participantDelegation.getNbMedailleArgent() + 1);
                break;
            }
            case 3: {
                participantDelegation.setNbMedailleBronze(participantDelegation.getNbMedailleBronze() + 1);
                break;
            }
            default:
                return participantDelegation.getNom() + " Pas de MAJ des medailles";
        }

        delegationRepository.save(participantDelegation);
        return participantDelegation.getNom() + " | Nombre de medaille MAJ";
    }
}
