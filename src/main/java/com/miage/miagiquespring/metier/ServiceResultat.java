package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.ResultatRepository;
import com.miage.miagiquespring.entities.Resultat;
import org.springframework.stereotype.Service;

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

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param resultatRepository
     */
    public ServiceResultat(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
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
    public Resultat creerResultat(Long idEpreuve, Long idParticipant, float temps, int position) {
        //Opération métier
        //On cherche si le client est déjà présent
        Resultat resultat = new Resultat();
        resultat.setIdEpreuve(idEpreuve);
        resultat.setIdParticipant(idParticipant);
        resultat.setTemps(temps);
        resultat.setPosition(position);
        //Ajout à la BD
        resultatRepository.save(resultat);
        return resultat;
    }

    /**
     * Récuperer un résultat
     *
     * @param idResultat
     * @return le résultat qui correspond
     * @throws Exception
     */
    public Resultat recupererResultat(long idResultat) throws Exception {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if (optionalResultat.isEmpty())
            throw new Exception("Resultat inexistant");
        // sinon, on renvoie les infos
        return optionalResultat.get();
    }

    /**
     * Récuperer tous les résultats
     *
     * @return
     * @throws Exception
     */
    public Iterable<Resultat> recupererAllResultat() throws Exception {
        // on cherche le Resultat
        return resultatRepository.findAll();
    }

    /**
     * Supprimer un résultat
     *
     * @param idResultat
     * @return confirmation de suppression
     * @throws Exception
     */
    public String supprimerResultat(long idResultat) throws Exception {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if (optionalResultat.isEmpty()) {
            throw new Exception("idResultat inexistant");
        }
        resultatRepository.delete(optionalResultat.get());
        return "Spectateur :" + idResultat + " removed";
    }
}
