package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.OrganisateurRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Organisateur;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private final OrganisateurRepository organisateurRepository;
    private final SpectateurRepository spectateurRepository;
    private final EpreuveRepository epreuveRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param billetRepository
     * @param organisateurRepository
     * @param spectateurRepository
     * @param epreuveRepository
     */
    public ServiceBillet(BilletRepository billetRepository, OrganisateurRepository organisateurRepository, SpectateurRepository spectateurRepository, EpreuveRepository epreuveRepository) {
        this.billetRepository = billetRepository;
        this.organisateurRepository = organisateurRepository;
        this.spectateurRepository = spectateurRepository;
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Créer un billet
     *
     * @param idEpreuve
     * @param prix
     * @param idSpectateur
     * @param dateBillet
     * @return le billet créée
     */
    public Billet creerBillet(Long idEpreuve, Long idSpectateur, int prix, Date dateBillet) {
        Billet billet = new Billet();
        billet.setIdSpectateur(idSpectateur);
        billet.setIdEpreuve(idEpreuve);
        billet.setPrix(prix);
        billet.setDateBillet(dateBillet);

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

    /**
     * PROCESSUS DE VALIDATION
     * Vérifie la validité d'un billet, verifie le role de l'organisateur concerné
     *
     * @param nomOrganisateur
     * @param prenomOrganisateur
     * @param idBillet
     * @param idSpectateur
     * @return Billet validé
     * @throws Exception
     */
    public String validerBillet(String nomOrganisateur, String prenomOrganisateur, Long idBillet, Long idSpectateur) throws Exception {
        //verification des roles de l'organisateur
        List<Organisateur> optionalOrganisateur = organisateurRepository.findByPrenomAndNom(prenomOrganisateur, nomOrganisateur);
        if (optionalOrganisateur.isEmpty()) {
            throw new Exception("Organisateur inexistante");
        }
        Organisateur organisateur = optionalOrganisateur.get(0);

        if (organisateur.getRoleOrganisateur()) {
            throw new Exception("Cet organisateur n'est pas un controlleur");
        }

        //Verification billet
        Optional<Billet> optionalBillet = billetRepository.findById(idBillet);
        if (optionalBillet.isEmpty()) {
            throw new Exception("Billet inexistant");
        }
        Billet billet = optionalBillet.get();

        //Verification existance billet et spactateur dans la base
        //Verification spectateur
        Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        if (optionalSpectateur.isEmpty()) {
            throw new Exception("Spectateur inexistant");
        }
        Spectateur spectateur = optionalSpectateur.get();


        //Verification correspondance spectateur-billet
        if (billet.getIdBillet() != spectateur.getIdSpectateur()) {
            throw new Exception("Billet :" + idBillet + " déjà utilisé");
        }

        //Billet valide ou non
        return "Billet : " + idBillet + " valide";
    }

    /**
     * PROCESSUS D'ANNULATION
     * Permet a un spectateur d'annuler son billet sous 7 - 3 jours
     * avec remboursement
     *
     * @param prenomSpectateur
     * @param nomSpectateur
     * @param idBillet
     * @return
     * @throws Exception
     */
    public String annulationBillet(String prenomSpectateur, String nomSpectateur, Long idBillet) throws Exception {
        //Verification et récuperation si le spectateur existe
        List<Spectateur> optionalSpectateur = spectateurRepository.findByPrenomAndNom(prenomSpectateur, nomSpectateur);
        if (optionalSpectateur.isEmpty()) {
            throw new Exception("Spectateur inexistante");
        }
        Spectateur spectateur = optionalSpectateur.get(0);

        //Verifcation et recuperer si le billet existe

        // Vérifier et récuperer l'billet
        Optional<Billet> optionalBillet = billetRepository.findById(idBillet);

        // s'il n'existe pas on lance une exception
        if (optionalBillet.isEmpty()) {
            throw new Exception("Billet inexistant");
        }

        Billet billet = optionalBillet.get();

        // Vérification si le billet appartient au spectateur
        List<Billet> spectateurBillets = spectateur.getBillets();
        if (!spectateurBillets.contains(billet)) {
            throw new Exception("Erreur : Le billet n'appartient pas au spectateur");
        }

        // Récupération de la date de l'épreuve
        Long idEpreuve = billet.getIdEpreuve();
        Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idEpreuve);

        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty()) {
            throw new Exception("Epreuve inexistant");
        }

        Epreuve epreuve = optionalEpreuve.get();

        Calendar recuperateurDeDate = Calendar.getInstance();
        Date dateCourante = recuperateurDeDate.getTime();

        recuperateurDeDate.setTime(epreuve.getDateEpreuve());
        Date dateEpreuve = recuperateurDeDate.getTime();

        // Calcul du nombre de jours restants avant l'épreuve
        long diffInMillies = dateEpreuve.getTime() - dateCourante.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);

        // Appliquer les règles de remboursement
        double remboursement = 0;
        if (diffInDays >= 7) {
            // Annulation sans frais jusqu'à 7 jours avant l'épreuve
            remboursement = billet.getPrix();
        } else if (diffInDays >= 3) {
            // Remboursement de 50% de 7 à 3 jours avant l'épreuve
            remboursement = billet.getPrix() * 0.5;
        } else {
            // Annulation impossible
            throw new Exception("Annulation impossible : Les annulations doivent être faites au moins 3 jours avant l'épreuve.");
        }

        // Annuler le billet
        spectateurBillets.remove(billet);
        spectateur.setBillets(spectateurBillets);
        spectateurRepository.save(spectateur);
        billetRepository.delete(billet);

        return "Billet remboursé : " + billet.getIdBillet() + " annulé par " + nomSpectateur + ". Montant remboursé : " + remboursement + " euros.";
    }

    /**
     * PROCESSUS DE RESERVATION DES BILLETS
     * Réserver un billet
     *
     * @param prenomSpectateur
     * @param nomSpectateur
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    public String reservationBillet(String prenomSpectateur, String nomSpectateur, String nomEpreuve) throws Exception {

        // Vérifier et récuperer le spectateur
        List<Spectateur> optionalSpectateur = spectateurRepository.findByPrenomAndNom(prenomSpectateur, nomSpectateur);
        if (optionalSpectateur.isEmpty()) {
            throw new Exception("Spectateur inexistante");
        }
        Spectateur spectateur = optionalSpectateur.get(0);


        // Vérifier et récuperer l'epreuve
        final List<Epreuve> optionalEpreuve = epreuveRepository.findByNomEpreuve(nomEpreuve);

        // s'il n'existe pas on lance une exception
        if (optionalEpreuve.isEmpty()) {
            throw new Exception("Epreuve inexistant");
        }

        Epreuve epreuve = optionalEpreuve.get(0);

        // Compter le nombre de billet pour cette epreuve appartenant au spectateur
        int nb_billet = 0;
        for (Billet billet : spectateur.getBillets()) {
            if ((billet.getIdSpectateur() == spectateur.getIdSpectateur())
                    && (billet.getIdEpreuve() == epreuve.getIdEpreuve())) {
                nb_billet += 1;
            }
        }

        // Limiter a 4 billet par spectateur
        if (nb_billet == 4) {
            throw new Exception("Spectateur possède déjà 4 billets pour cette epreuve");
        }

        // Vérification si place encore disponible
        if (epreuve.getNbPlacesDispo() == 0) {
            throw new Exception("Epreuve complete : Billet non disponible");
        }

        // Prendre un billet dans epreuve
        List<Billet> billetList = epreuve.getBillets();
        for (Billet billet : billetList) {

            //récuperer un billet disponible
            if (billet.getDisponible()) {
                List<Billet> billetsSpectateur = spectateur.getBillets();

                //reservation du billet
                billet.setDisponible(false);
                billet.setIdSpectateur(spectateur.getIdSpectateur());
                billetRepository.save(billet);

                billetsSpectateur.add(billet);
                spectateur.setBillets(billetsSpectateur);
                spectateurRepository.save(spectateur);
                return nomEpreuve + "| Billet : " + billet.getIdBillet() + " reserver par " + nomSpectateur;
            }
        }
        //Modifier nombre de place
        int nbPlace = epreuve.getNbPlacesDispo();
        if (nbPlace <= 0) {
            throw new Exception("Erreur nombre de place : nombre de place négatif");
        }
        epreuve.setNbPlacesDispo(nbPlace - 1);
        epreuveRepository.save(epreuve);

        return "Billet : Non disponible";
    }
}
