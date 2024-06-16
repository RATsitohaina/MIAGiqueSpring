package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEpreuve {

    private final EpreuveRepository epreuveRepository;

    /**
     *
     * @param epreuveRepository : Repository sur laquelle on va travailler
     */
    public ServiceEpreuve(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Crée une épreuve à la base de donnée
     * @param nomEpreuve
     * @param dateEpreuve
     * @param infrastructureAccueil
     * @param nbPlacesDispo
     * @return l'épreuve créée
     */
    public Epreuve creerEpreuve(String nomEpreuve, String dateEpreuve, InfrastructureSportive infrastructureAccueil , int nbPlacesDispo, List<Billet> billets) {
        List<Epreuve> epreuveList = epreuveRepository.findByNom(nomEpreuve);
        Epreuve epreuve;
        if(epreuveList.isEmpty()){
            epreuve = new Epreuve();
            epreuve.setNomEpreuve(nomEpreuve);
            epreuve.setDateEpreuve(dateEpreuve);
            epreuve.setInfrastructureAccueil(infrastructureAccueil);
            epreuve.setNbPlacesDispo(nbPlacesDispo);
            epreuve.setBillets(billets);

            // Ajout à la base de donnée
            epreuve = epreuveRepository.save(epreuve);
        }else {
            epreuve = epreuveList.get(0);
        }
        return epreuve;
    }

    public Epreuve recupererEpreuve(Long idEpreuve) throws Exception {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if(optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get();
    }

    public Epreuve recupererEpreuve(String nomEpreuve) throws Exception {
        // on cherche le client
        final List<Epreuve> optionalEpreuve = epreuveRepository.findByNom(nomEpreuve);
        // s'il n'existe pas on lance une exception
        if(optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get(0);
    }

    public Iterable<Epreuve> recupererAllEpreuve() throws Exception {
        return epreuveRepository.findAll();
    }

    public String supprimerEpreuve(Long idEpreuve) throws Exception {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if(optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        epreuveRepository.delete(optionalEpreuve.get());
        return "Epreuve :"+idEpreuve+" removed";
    }
}
