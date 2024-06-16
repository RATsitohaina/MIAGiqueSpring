package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.ResultatRepository;
import com.miage.miagiquespring.entities.Resultat;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceResultat {

    private final ResultatRepository resultatRepository;

    public ServiceResultat(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    /**
     * Demande la création d'un nouveau Resultat / Publication d'un nouveau resultat
     * @param idEpreuve
     * @param idParticipant
     * @param temps
     * @param position
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
     * Permet de récupérer les infos d'un Spectateur
     * @param idResultat du Spectateur
     * @return infos du Spectateur
     */
    public Resultat recupererResultat(long idResultat) throws Exception {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if(optionalResultat.isEmpty())
            throw new Exception("Resultat inexistant");
        // sinon, on renvoie les infos
        return optionalResultat.get();
    }

    /**
     * Permet de récupérer les infos d'un Resultat
     * @return infos du Resultat
     */
    public Iterable<Resultat> recupererAllResultat() throws Exception {
        // on cherche le Resultat
        return resultatRepository.findAll();
    }


    /**
     * Permet de récupérer les infos d'un Spectateur
     * @param idResultat
     */
    public String supprimerResultat(long idResultat) throws Exception {
        // on cherche le client
        final Optional<Resultat> optionalResultat = resultatRepository.findById(idResultat);
        // s'il n'existe pas on lance une exception
        if(optionalResultat.isEmpty()){
            throw new Exception("idResultat inexistant");
        }
        resultatRepository.delete(optionalResultat.get());
        return "Spectateur :"+idResultat+" removed";
    }
}
