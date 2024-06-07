package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

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
    public Epreuve creerEpreuve(String nomEpreuve, Calendar dateEpreuve, InfrastructureSportive infrastructureAccueil , int nbPlacesDispo, List<Spectateur> spectateurs) {
        Epreuve epreuve = new Epreuve();
        epreuve.setNomEpreuve(nomEpreuve);
        epreuve.setDateEpreuve(dateEpreuve);
        epreuve.setInfrastructureAccueil(infrastructureAccueil);
        epreuve.setNbPlacesDispo(nbPlacesDispo);
        epreuve.setSpectateurs(spectateurs);

        // Ajout à la base de donnée
        epreuve = epreuveRepository.save(epreuve);
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
}
