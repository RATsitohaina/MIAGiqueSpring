package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;


/**
 * Bean métier pour la gestion des épreuves {
 */
@Service
public class ServiceEpreuve {

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
     * @param billets
     * @return l'épreuve créée
     */
    public Epreuve creerEpreuve(String nomEpreuve, Calendar dateEpreuve,
                                InfrastructureSportive infrastructureAccueil,
                                int nbPlacesDispo, int nbPlacesInit,
                                List<Billet> billets) {
        List<Epreuve> epreuveList = epreuveRepository.findByNomEpreuve(nomEpreuve);
        Epreuve epreuve;
        if (epreuveList.isEmpty()) {
            epreuve = new Epreuve();
            epreuve.setNomEpreuve(nomEpreuve);
            epreuve.setDateEpreuve(dateEpreuve);
            epreuve.setInfrastructureAccueil(infrastructureAccueil);
            epreuve.setNbPlacesDispo(nbPlacesDispo);
            epreuve.setNbPlacesInit(nbPlacesInit);
            epreuve.setBillets(billets);

            // Ajout à la base de donnée
            epreuve = epreuveRepository.save(epreuve);
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
     * @throws Exception
     */
    public Epreuve modifierEpreuve(String nom, String nomEpreuve, Calendar dateEpreuve, int nbPlacesDispo) throws Exception {
        List<Epreuve> epreuveList = epreuveRepository.findByNomEpreuve(nom);
        Epreuve epreuve;
        if (epreuveList.isEmpty()) {
            throw new Exception("Epreuve inexistante");
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
     * @throws Exception
     */
    public Epreuve recupererEpreuve(Long idEpreuve) throws Exception {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get();
    }

    /**
     * Récupérer une épreuve
     *
     * @param nomEpreuve
     * @return l'épreuve qui correspond
     * @throws Exception
     */
    public Epreuve recupererEpreuve(String nomEpreuve) throws Exception {
        // on cherche le client
        final List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        return optionalEpreuve.get(0);
    }

    /**
     * Récupérer toutes les épreuves
     *
     * @return liste de toutes les épreuves
     * @throws Exception
     */
    public Iterable<Epreuve> recupererAllEpreuve() throws Exception {
        return epreuveRepository.findAll();
    }

    /**
     * Supprimer une épreuve
     *
     * @param idEpreuve
     * @return la confirmation de suppression
     * @throws Exception
     */
    public String supprimerEpreuve(Long idEpreuve) throws Exception {
        // on cherche le client
        final Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);
        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty())
            throw new Exception("Erreur epreuve inexistant");
        // sinon, on renvoie les infos
        epreuveRepository.delete(optionalEpreuve.get());
        return "Epreuve :" + idEpreuve + " removed";
    }

    /**
     * Générer les billets de l'épreuve
     *
     * @param idEpreuve
     * @param prix
     * @return
     * @throws Exception
     */
    public List<Billet> genererBilletEpreuve(Long idEpreuve, int prix) throws Exception {
        //Vérificaton et récupération epreuve
        Epreuve epreuve = recupererEpreuve(idEpreuve);
        List<Billet> epreuveBillets = epreuve.getBillets();
        //Mise en place de la jauge des ventes, gestion des erreurs
        if (!(epreuve.getNbPlacesInit() <= epreuve.getInfrastructureAccueil().getCapacite())) {
            throw new Exception("Nombre de places initiales de l'epreuve supérieure à la capacité de l'infrastructure");
        }

        //pour ne pas générer plus de places que l'infra ne puisse contenir

        for (int i = 0; i < epreuve.getNbPlacesInit(); i++) {
            Billet billet = new Billet();
            billet.setIdEpreuve(epreuve.getIdEpreuve());
            billet.setEtat(false);
            billet.setPrix(prix);
            billetRepository.save(billet);

            epreuveBillets.add(billet);
        }

        epreuve.setBillets(epreuveBillets);
        epreuveRepository.save(epreuve);
        return epreuve.getBillets();
    }
}
