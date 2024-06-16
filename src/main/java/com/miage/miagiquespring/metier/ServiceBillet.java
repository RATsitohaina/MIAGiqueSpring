package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBillet {

    private final BilletRepository billetRepository;

    /**
     *
     * @param billetRepository : Repository sur laquelle on va travailler
     */
    public ServiceBillet(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    /**
     * Crée une épreuve à la base de donnée
     * @param prix
     * @param etat
     * @param idSpectateur
     * @param idSpectateur
     * @return l'épreuve créée
     */
    public Billet creerBillet(Long idEpreuve, Long idSpectateur, int prix, Boolean etat) {
        Billet billet = new Billet();
        billet.setIdSpectateur(idSpectateur);
        billet.setIdEpreuve(idEpreuve);
        billet.setPrix(prix);
        billet.setEtat(etat);

        // Ajout à la base de donnée
        billet = billetRepository.save(billet);
        return billet;
    }

    public Billet recupererBillet(Long idBillet) throws Exception {
        // on cherche le Billet
        final Optional<Billet> optionalBillet = billetRepository.findById(idBillet);
        // s'il n'existe pas on lance une exception
        if(optionalBillet.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalBillet.get();
    }

    public Iterable<Billet> recupererAllBillet() throws Exception {
        // on cherche le Billet
        return billetRepository.findAll();
    }

    public String supprimerBillet(Long idBillet) throws Exception {
        // on cherche le Billet
        final Optional<Billet> optionalBillet = billetRepository.findById(idBillet);
        // s'il n'existe pas on lance une exception
        if(optionalBillet.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        billetRepository.delete(optionalBillet.get());
        return "Billet :"+idBillet+" removed";
    }
}
