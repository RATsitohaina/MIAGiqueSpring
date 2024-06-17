package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.utilities.CapaciteDacceuilRempli;
import com.miage.miagiquespring.utilities.EpreuveInexistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Bean métier pour la gestion des épreuves {
 */
@Service
public class ServiceEpreuve {

    private static final Logger log = LoggerFactory.getLogger(ServiceEpreuve.class);
    /**
     * Bean repository qui sera injecté par le constructeur
     */
    private final EpreuveRepository epreuveRepository;
    private final BilletRepository billetRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param epreuveRepository
     * @param billetRepository
     */
    public ServiceEpreuve(EpreuveRepository epreuveRepository, BilletRepository billetRepository) {
        this.epreuveRepository = epreuveRepository;
        this.billetRepository = billetRepository;
    }

    /**
     * Créer une épreuve
     *
     * @param nomEpreuve
     * @param dateEpreuve
     * @param infrastructureAccueil
     * @param nbPlacesDispo
     * @param nbPlacesInit
     * @param prixBillet
     * @return l'épreuve créée
     */
    public Epreuve creerEpreuve(String nomEpreuve, Date dateEpreuve,
                                InfrastructureSportive infrastructureAccueil,
                                int nbPlacesDispo, int nbPlacesInit, int prixBillet) throws Exception {
        List<Epreuve> epreuveList = epreuveRepository.findByNomEpreuve(nomEpreuve);
        Epreuve epreuve;
        if (epreuveList.isEmpty()) {
            epreuve = new Epreuve();
            epreuve.setNomEpreuve(nomEpreuve);
            epreuve.setDateEpreuve(dateEpreuve);
            epreuve.setInfrastructureAccueil(infrastructureAccueil);
            epreuve.setNbPlacesDispo(nbPlacesDispo);
            epreuve.setNbPlacesInit(nbPlacesInit);
            epreuve.setPrixBillet(prixBillet);

            // Ajout à la base de donnée
            epreuve = epreuveRepository.save(epreuve);

            //Génération des billets
            List<Epreuve> epreuves = epreuveRepository.findByNomEpreuve(nomEpreuve);
            Long idEpreuve = epreuves.get(0).getIdEpreuve();
            genererBilletEpreuve(idEpreuve, prixBillet);

        } else {
            epreuve = epreuveList.get(0);
        }
        return epreuve;
    }

    /**
     * Modifier les détails d'un épreuve
     *
     * @param nom
     * @param nomEpreuve
     * @param dateEpreuve
     * @param nbPlacesDispo
     * @return l'épreuve modifier
     */
    public Epreuve modifierEpreuve(String nom, String nomEpreuve, Date dateEpreuve, int nbPlacesDispo) {
        List<Epreuve> epreuveList = epreuveRepository.findByNomEpreuve(nom);
        Epreuve epreuve;
        if (epreuveList.isEmpty()) {
            throw new EpreuveInexistant("Epreuve inexistante");
        } else {
            epreuve = epreuveList.get(0);
            epreuve.setNomEpreuve(nomEpreuve);
            epreuve.setDateEpreuve(dateEpreuve);
            epreuve.setNbPlacesDispo(nbPlacesDispo);
            epreuve = epreuveRepository.save(epreuve);
        }
        return epreuve;
    }

    /**
     * Récupérer une épreuve
     *
     * @param idEpreuve
     * @return l'épreuve qui correspond
     */
    public Epreuve recupererEpreuve(Long idEpreuve) {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new EpreuveInexistant("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get();
    }

    /**
     * Récupérer une épreuve
     *
     * @param nomEpreuve
     * @return l'épreuve qui correspond
     */
    public Epreuve recupererEpreuve(String nomEpreuve) {
        // on cherche le client
        final List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new EpreuveInexistant("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get(0);
    }

    /**
     * Récupérer toutes les épreuves
     *
     * @return liste de toutes les épreuves
     */
    public Iterable<Epreuve> recupererAllEpreuve() {
        return epreuveRepository.findAll();
    }

    /**
     * Supprimer une épreuve
     *
     * @param idEpreuve
     * @return la confirmation de suppression
     */
    public String supprimerEpreuve(Long idEpreuve) {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new EpreuveInexistant("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        epreuveRepository.delete(optionalEpreuve.get());
        return "Epreuve :" + idEpreuve + " removed";
    }

    /**
     * Générer les billets de l'épreuve
     *
     * @param idEpreuve
     * @param prix
     */
    public void genererBilletEpreuve(Long idEpreuve, int prix) {
        //Vérificaton et récupération epreuve
        Epreuve epreuve = recupererEpreuve(idEpreuve);
        List<Billet> epreuveBillets = new ArrayList<>();

        //Mise en place de la jauge des ventes, gestion des erreurs
        if (!(epreuve.getNbPlacesInit() <= epreuve.getInfrastructureAccueil().getCapacite())) {
            throw new CapaciteDacceuilRempli("Capacité d'acceuil rempli");
        }

        //pour ne pas générer plus de places que l'infra ne puisse contenir
        for (int i = 0; i < epreuve.getNbPlacesInit(); i++) {
            Billet billet = new Billet();
            billet.setIdEpreuve(epreuve.getIdEpreuve());
            billet.setDisponible(true);
            billet.setPrix(prix);
            billetRepository.save(billet);

            epreuveBillets.add(billet);
            log.info(billet.toString());
        }

        //Ajout à la base de donnée
        epreuve.setBillets(epreuveBillets);
        epreuveRepository.save(epreuve);
    }
}
