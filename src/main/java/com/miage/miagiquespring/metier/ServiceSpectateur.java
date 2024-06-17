package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Spectateur;
import com.miage.miagiquespring.utilities.SpectateurInexistant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bean métier pour la gestion des spectateurs {
 */
@Service
public class ServiceSpectateur {
    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final SpectateurRepository spectateurRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param spectateurRepository
     */
    public ServiceSpectateur(SpectateurRepository spectateurRepository) {
        this.spectateurRepository = spectateurRepository;
    }

    /**
     * Créer un spectateur
     *
     * @param nom
     * @param prenom
     * @param email
     * @param billetList
     * @return
     */
    public Spectateur creerSpectateur(String nom,
                                      String prenom,
                                      String email,
                                      List<Billet> billetList) {
        //Opération métier
        //On cherche si le client est déjà présent
        List<Spectateur> spectateurs = spectateurRepository.findByPrenomAndNom(prenom, nom);
        Spectateur spectateur;
        // s'il n'est pas présent
        if (spectateurs.isEmpty()) {
            // on le crée
            spectateur = new Spectateur();
            spectateur.setPrenom(prenom);
            spectateur.setNom(nom);
            spectateur.setEmail(email);
            spectateur.setBillets(billetList);
            // on l'ajoute à la BD
            spectateur = spectateurRepository.save(spectateur);
        } else {
            // sinon, on retournera l'ancien
            spectateur = spectateurs.get(0);
        }
        // retourne le client
        return spectateur;
    }

    /**
     * Récupérer un spectateur
     *
     * @param idSpectateur
     * @return le spectateur qui correspond
     */
    public Spectateur recupererSpectateur(long idSpectateur) {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty())
            throw new SpectateurInexistant("Spectateur inexistant");
        // sinon, on renvoie les infos
        return optionalSpectateur.get();
    }

    /**
     * Récupérer un spectateur
     *
     * @param prenom
     * @param nom
     * @return le spectateur qui correspond
     */
    public Spectateur recupererSpectateur(String prenom, String nom) {
        // on cherche le Spectateur
        final List<Spectateur> optionalSpectateur = spectateurRepository.findByPrenomAndNom(prenom, nom);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty())
            throw new SpectateurInexistant("Spectateur inexistant");
        // sinon, on renvoie les infos
        return optionalSpectateur.get(0);
    }


    /**
     * Récupérer tous les spectateurs
     *
     * @return la liste des spéctateurs
     */
    public Iterable<Spectateur> recupererAllSpectateur() {
        return spectateurRepository.findAll();
    }

    /**
     * Supprimer un spectateur
     *
     * @param idSpectateur
     * @return confirmation de suppression
     */
    public String supprimerSpectateur(long idSpectateur) {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty()) {
            throw new SpectateurInexistant("Spectateur inexistant");
        }
        spectateurRepository.delete(optionalSpectateur.get());
        return "Spectateur :" + idSpectateur + " removed";
    }

    /**
     * Consulter liste de billets du spectateur
     *
     * @param idSpectateur
     * @return la liste des billets
     */
    public List<Billet> consulterBilletSpectateur(long idSpectateur) {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty()) {
            throw new SpectateurInexistant("Spectateur inexistant");
        }

        Spectateur spectateur = optionalSpectateur.get();
        return spectateur.getBillets();
    }
}
