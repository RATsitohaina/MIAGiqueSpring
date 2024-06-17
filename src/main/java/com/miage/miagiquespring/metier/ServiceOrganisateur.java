package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.*;
import com.miage.miagiquespring.entities.*;
import com.miage.miagiquespring.utilities.OrganisateurInexistant;
import com.miage.miagiquespring.utilities.RoleOrganisateurNonConforme;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Bean métier pour la gestion des organisateurs {
 */
@Service
public class ServiceOrganisateur {

    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final OrganisateurRepository organisateurRepository;
    private final EpreuveRepository epreuveRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param organisateurRepository
     * @param epreuveRepository
     */
    public ServiceOrganisateur(OrganisateurRepository organisateurRepository, EpreuveRepository epreuveRepository) {
        this.organisateurRepository = organisateurRepository;
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Créer un organisateur
     *
     * @param nom
     * @param prenom
     * @param email
     * @param roleOrganisateur
     * @return
     */
    public Organisateur creerOrganisateur(String nom, String prenom,
                                          String email, boolean roleOrganisateur) {
        //Opération métier
        //On cherche si le client est déjà présent
        List<Organisateur> organisateurs = organisateurRepository.findByPrenomAndNom(prenom, nom);
        Organisateur organisateur;
        // s'il n'est pas présent
        if (organisateurs.isEmpty()) {
            // on le crée
            organisateur = new Organisateur();
            organisateur.setPrenom(prenom);
            organisateur.setNom(nom);
            organisateur.setEmail(email);
            organisateur.setRoleOrganisateur(roleOrganisateur);

            // on l'ajoute à la BD
            organisateur = organisateurRepository.save(organisateur);
        } else {
            // sinon, on retournera l'ancien
            organisateur = organisateurs.get(0);
        }
        // retourne le client
        return organisateur;
    }

    /**
     * Récuperer un organisateur
     *
     * @param idOrganisateur
     * @return l'organisateur qui correspond
     */
    public Organisateur recupererOrganisateur(long idOrganisateur) {
        // on cherche le organisateur
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty())
            throw new OrganisateurInexistant("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get();
    }

    /**
     * Récuperer un organisateur
     *
     * @param nom
     * @param prenom
     * @return l'organisateur qui correspond
     */
    public Organisateur recupererOrganisateur(String nom, String prenom) {
        // on cherche le organisateur
        final List<Organisateur> optionalOrganisateur = organisateurRepository.findByPrenomAndNom(prenom, nom);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty())
            throw new OrganisateurInexistant("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get(0);
    }

    /**
     * Supprimer un organisateur
     *
     * @param idOrganisateur
     * @return la confirmation de suppression
     */
    public String supprimerOrganisateur(long idOrganisateur) {
        // on cherche le client
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty()) {
            throw new OrganisateurInexistant("Organisateur inexistant");
        }
        organisateurRepository.delete(optionalOrganisateur.get());
        return "Organisateur :" + idOrganisateur + " removed";
    }

    /**
     * Récuperer tous les organisateurs
     *
     * @return liste des organisateurs
     */
    public Iterable<Organisateur> recupererAllOrganisateur() {
        return organisateurRepository.findAll();
    }

    /**
     * GENERER LES STATISTIQUES
     * Calcule le pourcentage des billets vendus par épreuves
     *
     * @param nomOrganisateur
     * @param prenomOrganisateur
     * @return une hashmap avec comme clé le nom de l'épreuve et comme valeur la statistique des ventes
     */
    //Processus supervision epreuve
    public HashMap<String, Float> calculerStatDeVentes(String nomOrganisateur, String prenomOrganisateur) {
        Organisateur organisateur = recupererOrganisateur(nomOrganisateur, prenomOrganisateur);
        if (!organisateur.getRoleOrganisateur()) {
            throw new RoleOrganisateurNonConforme("Cet organisateur est un controlleur!");
        }
        //calcul des res
        Iterable<Epreuve> epreuves = epreuveRepository.findAll();
        HashMap<String, Float> statistiques = new HashMap<String, Float>();
        //boucler sur les epreuves (ratio: pourcentage des ventes)
        for (Epreuve epreuve : epreuves) {
            statistiques.put(epreuve.getNomEpreuve(),
                    (float) ((epreuve.getNbPlacesInit() - epreuve.getNbPlacesDispo()) / epreuve.getNbPlacesInit()) * 100);
        }
        return statistiques;
    }
}
