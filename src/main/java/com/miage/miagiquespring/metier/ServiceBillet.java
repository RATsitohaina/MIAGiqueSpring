package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.entities.Billet;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Bean métier pour la gestion des billets {
 */
@Service
public class ServiceBillet {

    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final BilletRepository billetRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param billetRepository
     */
    public ServiceBillet(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    /**
     * Créer un billet
     *
     * @param prix
     * @param etat
     * @param idSpectateur
     * @param idSpectateur
     * @return le billet créée
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

    /**
     * Récupérer un billet
     *
     * @param idBillet
     * @return le billet récupéré
     * @throws Exception
     */
    public Billet recupererBillet(Long idBillet) throws Exception {
        // on cherche le Billet
        final Optional<Billet> optionalBillet = billetRepository.findById(idBillet);
        // s'il n'existe pas on lance une exception
        if (optionalBillet.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalBillet.get();
    }

    /**
     * Récupérer tous les billets
     *
     * @return la liste de tous les billets crées
     * @throws Exception
     */
    public Iterable<Billet> recupererAllBillet() throws Exception {
        return billetRepository.findAll();
    }

    /**
     * Supprimer un billet
     *
     * @param idBillet
     * @return la confirmations de suppression du billet
     * @throws Exception
     */
    public String supprimerBillet(Long idBillet) throws Exception {
        // on cherche le Billet
        final Optional<Billet> optionalBillet = billetRepository.findById(idBillet);
        // s'il n'existe pas on lance une exception
        if (optionalBillet.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        billetRepository.delete(optionalBillet.get());
        return "Billet :" + idBillet + " removed";
    }
}
