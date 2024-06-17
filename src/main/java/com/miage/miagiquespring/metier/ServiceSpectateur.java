package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.entities.Spectateur;
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
     * @param resultatList
     * @return
     */
    public Spectateur creerSpectateur(String nom, String prenom, String email, List<Billet> billetList, List<Resultat> resultatList) {
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
            spectateur.setResultats(resultatList);
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
     * @throws Exception
     */
    public Spectateur recupererSpectateur(long idSpectateur) throws Exception {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty())
            throw new Exception("Spectateur inexistant");
        // sinon, on renvoie les infos
        return optionalSpectateur.get();
    }

    /**
     * Récupérer un spectateur
     *
     * @param prenom
     * @param nom
     * @return le spectateur qui correspond
     * @throws Exception
     */
    public Spectateur recupererSpectateur(String prenom, String nom) throws Exception {
        // on cherche le Spectateur
        final List<Spectateur> optionalSpectateur = spectateurRepository.findByPrenomAndNom(prenom, nom);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty())
            throw new Exception("Spectateur inexistant");
        // sinon, on renvoie les infos
        return optionalSpectateur.get(0);
    }


    /**
     * Récupérer tous les spectateurs
     *
     * @return la liste des spéctateurs
     * @throws Exception
     */
    public Iterable<Spectateur> recupererAllSpectateur() throws Exception {
        return spectateurRepository.findAll();
    }

    /**
     * Supprimer un spectateur
     *
     * @param idSpectateur
     * @return confirmation de suppression
     * @throws Exception
     */
    public String supprimerSpectateur(long idSpectateur) throws Exception {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty()) {
            throw new Exception("Spectateur inexistant");
        }
        spectateurRepository.delete(optionalSpectateur.get());
        return "Spectateur :" + idSpectateur + " removed";
    }

    /**
     * Consulter liste de billets du spectateur
     * @param idSpectateur
     * @return la liste des billets
     * @throws Exception
     */
    public List<Billet> consulterBilletSpectateur(long idSpectateur) throws Exception{
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if (optionalSpectateur.isEmpty()) {
            throw new Exception("Spectateur inexistant");
        }

        Spectateur spectateur = optionalSpectateur.get();
        return spectateur.getBillets();
    }
}
