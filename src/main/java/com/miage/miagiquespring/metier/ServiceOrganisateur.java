package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.*;
import com.miage.miagiquespring.entities.*;
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
    private final ParticipantRepository participantRepository;
    private final DelegationRepository delegationRepository;
    private final BilletRepository billetRepository;
    private final SpectateurRepository spectateurRepository;
    private final EpreuveRepository epreuveRepository;

    /**
     * Constructeur pour l'injection du bean repository
     *
     * @param organisateurRepository
     * @param participantRepository
     * @param delegationRepository
     * @param billetRepository
     * @param spectateurRepository
     * @param epreuveRepository
     */
    public ServiceOrganisateur(OrganisateurRepository organisateurRepository, ParticipantRepository participantRepository, DelegationRepository delegationRepository, BilletRepository billetRepository, SpectateurRepository spectateurRepository, EpreuveRepository epreuveRepository) {
        this.organisateurRepository = organisateurRepository;
        this.participantRepository = participantRepository;
        this.delegationRepository = delegationRepository;
        this.billetRepository = billetRepository;
        this.spectateurRepository = spectateurRepository;
        this.epreuveRepository = epreuveRepository;
    }

    /**
     * Créer un organisateur
     *
     * @param nom
     * @param prenom
     * @param email
     * @param delegationList
     * @param participantList
     * @param resultatList
     * @param epreuveList
     * @param billetList
     * @param infrastructureSportiveList
     * @param roleOrganisateur
     * @return
     */
    public Organisateur creerOrganisateur(String nom, String prenom, String email,
                                          List<Delegation> delegationList, List<Participant> participantList,
                                          List<Resultat> resultatList, List<Epreuve> epreuveList,
                                          List<Billet> billetList, List<InfrastructureSportive> infrastructureSportiveList,
                                          boolean roleOrganisateur) {
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
            organisateur.setDelegationList(delegationList);
            organisateur.setParticipantList(participantList);
            organisateur.setResultatList(resultatList);
            organisateur.setEpreuveList(epreuveList);
            organisateur.setBilletList(billetList);
            organisateur.setInfrastructureSportiveList(infrastructureSportiveList);
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
     * @throws Exception
     */
    public Organisateur recupererOrganisateur(long idOrganisateur) throws Exception {
        // on cherche le organisateur
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty())
            throw new Exception("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get();
    }

    /**
     * Récuperer un organisateur
     *
     * @param nom
     * @param prenom
     * @return l'organisateur qui correspond
     * @throws Exception
     */
    public Organisateur recupererOrganisateur(String nom, String prenom) throws Exception {
        // on cherche le organisateur
        final List<Organisateur> optionalOrganisateur = organisateurRepository.findByPrenomAndNom(prenom, nom);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty())
            throw new Exception("Organisateur inexistant");
        // sinon, on renvoie les infos
        return optionalOrganisateur.get(0);
    }

    /**
     * Supprimer un organisateur
     *
     * @param idOrganisateur
     * @return la confirmation de suppression
     * @throws Exception
     */
    public String supprimerOrganisateur(long idOrganisateur) throws Exception {
        // on cherche le client
        final Optional<Organisateur> optionalOrganisateur = organisateurRepository.findById(idOrganisateur);
        // s'il n'existe pas on lance une exception
        if (optionalOrganisateur.isEmpty()) {
            throw new Exception("Organisateur inexistant");
        }
        organisateurRepository.delete(optionalOrganisateur.get());
        return "Organisateur :" + idOrganisateur + " removed";
    }

    /**
     * Récuperer tous les organisateurs
     *
     * @return liste des organisateurs
     * @throws Exception
     */
    public Iterable<Organisateur> recupererAllOrganisateur() throws Exception {
        return organisateurRepository.findAll();
    }

    /**
     * ACTION POSSIBLE POUR L'ORGANISATEUR
     */


    /**
     * PROCESSUS DE GESTION DES DELEGATIONS
     * Ajout de la delegation dans participant
     *
     * @param nomDelegation
     * @param prenomParticipant
     * @param nomParticipant
     * @return
     * @throws Exception
     */
    public String ajouterParticipant(String nomDelegation, String prenomParticipant, String nomParticipant) throws Exception {
        //Vérification existance et recupreation delegation
        List<Delegation> optionalDelegation = delegationRepository.findByNom(nomDelegation);
        if (optionalDelegation.isEmpty()) {
            throw new Exception("Délégation inexistante");
        }
        Delegation delegation = optionalDelegation.get(0);

        //Vérification existance et recuperation participant
        List<Participant> optionalParticipant = participantRepository.findByPrenomAndNom(prenomParticipant, nomParticipant);
        if (optionalDelegation.isEmpty()) {
            throw new Exception("Participant inexistante");
        }
        Participant participant = optionalParticipant.get(0);

        //Ajout du participant dans la delegation
        participant.setDelegation(delegation);
        participantRepository.save(participant);

        return "Delegation :" + nomDelegation + " added in " + nomParticipant;
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
        Organisateur organisateur = recupererOrganisateur(nomOrganisateur, prenomOrganisateur);
        if (organisateur.getRoleOrganisateur()) {
            throw new Exception("Cet organisateur n'est pas un controlleur!");
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
            throw new Exception("Le billet ne correspond pas au spectateur.");
        }

        //Billet valide ou non
        return "Billet validé";
    }

    /**
     * GENERER LES STATISTIQUES
     * Calcule le pourcentage des billets vendus par épreuves
     *
     * @param nomOrganisateur
     * @param prenomOrganisateur
     * @return une hashmap avec comme clé le nom de l'épreuve et comme valeur la statistique des ventes
     * @throws Exception
     */
    //Processus supervision epreuve
    public HashMap<String, Float> calculerStatDeVentes(String nomOrganisateur, String prenomOrganisateur) throws Exception {
        Organisateur organisateur = recupererOrganisateur(nomOrganisateur, prenomOrganisateur);
        if (!organisateur.getRoleOrganisateur()) {
            throw new Exception("Cet organisateur est un controlleur!");
        }
        //calcul des res
        Iterable<Epreuve> epreuves = epreuveRepository.findAll();
        HashMap<String, Float> statistiques = new HashMap<String, Float>();
        //boucler sur les epreuves (ratio: pourcentage des ventes)
        for (Epreuve epreuve : epreuves) {
            statistiques.put(epreuve.getNomEpreuve(), (float) ((epreuve.getNbPlacesInit() - epreuve.getNbPlacesDispo()) / epreuve.getNbPlacesInit()) * 100);
        }
        return statistiques;
    }
}
