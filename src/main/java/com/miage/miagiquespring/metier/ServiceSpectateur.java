package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSpectateur {

    private final SpectateurRepository spectateurRepository;

    private final EpreuveRepository epreuveRepository;
    private final BilletRepository billetRepository;

    public ServiceSpectateur(SpectateurRepository spectateurRepository, EpreuveRepository epreuveRepository, BilletRepository billetRepository) {
        this.spectateurRepository = spectateurRepository;
        this.billetRepository = billetRepository;
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Demande la création d'un nouveau Spectateur
     *
     * @param prenom prénom du Spectateur
     * @param nom    nom du Spectateur
     * @return le nouveau Spectateur ou l'ancien Spectateur
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
     * Permet de récupérer les infos d'un Spectateur
     *
     * @param idSpectateur du Spectateur
     * @return infos du Spectateur
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
     * Permet de récupérer les infos d'un Spectateur
     *
     * @return infos du Spectateur
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
     * Permet de récupérer toutes les infos d'un Spectateur
     *
     * @return infos du Spectateur
     */
    public Iterable<Spectateur> recupererAllSpectateur() throws Exception {
        // on cherche le Spectateur
        return spectateurRepository.findAll();
    }

    /**
     * Permet de récupérer les infos d'un Spectateur
     *
     * @param idSpectateur id du Spectateur
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
     * ACTION POSSIBLE POUR LE SPECTATEUR
     */

    /** PROCESSUS DE RESERVATION DES BILLETS **/
    /**
     * Permet de reserver un billet
     *
     * @param prenomSpectateur
     * @param nomSpectateur
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    public String reservationBillet(String prenomSpectateur, String nomSpectateur, String nomEpreuve) throws Exception {

        // Vérifier et récuperer le spectateur
        Spectateur spectateur = recupererSpectateur(prenomSpectateur, nomSpectateur);


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
            if (!billet.getEtat()) {
                List<Billet> billetsSpectateur = spectateur.getBillets();

                //reservation du billet
                billet.setEtat(true);
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


    /**
     * PROCESSUS D'ANNULATION
     **/

    /** Permet a un spectateur d'annuler son billet sous 7 - 3 jours
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
        Spectateur spectateur = recupererSpectateur(prenomSpectateur, nomSpectateur);

        //Verifcation et recuperer si le billet existe

        // Vérifier et récuperer l'billet
        Optional<Billet> optionalBillet = billetRepository.findById(idBillet);

        // s'il n'existe pas on lance une exception
        if (optionalBillet.isEmpty()) {
            throw new Exception("Billet inexistant");
        }

        Billet billet = optionalBillet.get();

        //Annuler le billet
        List<Billet> spectateurBillet = spectateur.getBillets();

        //Verifier si 7-3 Jours avant pour le remboursement
        // Sinon Annulation impossible


        if(spectateurBillet.contains(billet)){
            spectateurBillet.remove(billet);
            spectateur.setBillets(spectateurBillet);
            spectateurRepository.save(spectateur);
        }else{
            throw new Exception("Erreur : Le billet n'appartient pas au spectateur");
        }

        return " Billet rembourser : " + billet.getIdBillet() + " annuler par " + nomSpectateur;
    }
}
