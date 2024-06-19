package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.*;
import com.miage.miagiquespring.metier.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur REST pour la ressource organisateur
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/organisateur")
public class RestServiceOrganisateur {

    private final ServiceOrganisateur serviceOrganisateur;
    private final ServiceResultat serviceResultat;
    private final ServiceBillet serviceBillet;
    private final ServiceDelegation serviceDelegation;
    private final ServiceParticipant serviceParticipant;
    private final ServiceEpreuve serviceEpreuve;
    private final ServiceInfrastructureSportive serviceInfrastructureSportive;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     *
     * @param serviceOrganisateur
     * @param serviceResultat
     * @param serviceBillet
     * @param serviceDelegation
     * @param serviceParticipant
     * @param serviceEpreuve
     * @param serviceInfrastructureSportive
     */
    public RestServiceOrganisateur(ServiceOrganisateur serviceOrganisateur, ServiceResultat serviceResultat, ServiceBillet serviceBillet, ServiceDelegation serviceDelegation, ServiceParticipant serviceParticipant, ServiceEpreuve serviceEpreuve, ServiceInfrastructureSportive serviceInfrastructureSportive) {
        this.serviceOrganisateur = serviceOrganisateur;
        this.serviceResultat = serviceResultat;
        this.serviceBillet = serviceBillet;
        this.serviceDelegation = serviceDelegation;
        this.serviceParticipant = serviceParticipant;
        this.serviceEpreuve = serviceEpreuve;
        this.serviceInfrastructureSportive = serviceInfrastructureSportive;
    }

    /**
     * Permet de créer un nouveau organisateur
     *
     * @param organisateur
     * @return
     */
    @PostMapping
    public Organisateur creerOrganisateur(@RequestBody Organisateur organisateur) {
        return serviceOrganisateur.creerOrganisateur(organisateur.getNom(), organisateur.getPrenom(),
                organisateur.getEmail(), organisateur.getRoleOrganisateur());
    }

    /**
     * Permet de récupérer les détails d'un organisateur
     *
     * @param idOrganisateur
     * @return
     * @throws Exception
     */
    @GetMapping("id/{id}")
    public Organisateur getOrganisateur(@PathVariable("id") long idOrganisateur) throws Exception {
        return serviceOrganisateur.recupererOrganisateur(idOrganisateur);
    }

    /**
     * Permet de récupérer les détails d'un organisateur
     *
     * @param nomOrganisateur
     * @param prenomOrganisateur
     * @return
     * @throws Exception
     */
    @GetMapping("prenomNom/{prenom}/{nom}")
    public Organisateur getOrganisateurNomPrenom(@PathVariable("nom") String nomOrganisateur, @PathVariable("prenom") String prenomOrganisateur) throws Exception {
        return serviceOrganisateur.recupererOrganisateur(prenomOrganisateur, nomOrganisateur);
    }

    /**
     * Permet de récupérer tous les organisteurs
     *
     * @return
     * @throws Exception
     */
    @GetMapping("all")
    public Iterable<Organisateur> getAllOrganisateur() throws Exception {
        return serviceOrganisateur.recupererAllOrganisateur();
    }

    /**
     * Supprimer un organisateur
     *
     * @param idOrganisateur
     * @return
     * @throws Exception
     */
    @DeleteMapping("delete/id/{id}")
    public String supprimerOrganisateur(@PathVariable("id") long idOrganisateur) throws Exception {
        return serviceOrganisateur.supprimerOrganisateur(idOrganisateur);
    }

    /**
     * Permet de créer un organisateur
     *
     * @param organisateur
     * @return
     */
    @PostMapping("/null")
    public Organisateur creerOrganisateurNull(@RequestBody Organisateur organisateur) {
        return serviceOrganisateur.creerOrganisateur(organisateur.getNom(), organisateur.getPrenom()
                , organisateur.getEmail(), true);
    }


    /**
     * STATISTIQUE DE VENTE
     * <p>
     * Permet de récupérer les statistiques de vente
     *
     * @param prenomOrganisateur
     * @param nomOrganisateur
     * @return
     * @throws Exception
     */
    @GetMapping("statistique/{prenom}/{nom}")
    public String getStatistique(@PathVariable("prenom") String prenomOrganisateur,
                                                 @PathVariable("nom") String nomOrganisateur) throws Exception {
        return serviceOrganisateur.calculerStatDeVentes(prenomOrganisateur, nomOrganisateur);
    }

    /**
     *    ********* RESULTAT **********************************************************************
     */

    /**
     * Permet de créer un nouveau resultat
     *
     * @param resultat les détails d'un resultat envoyés par le front
     */
    @PostMapping("resultat")
    public Resultat creerResultat(@RequestBody Resultat resultat) throws Exception {
        return serviceResultat.creerResultat(resultat.getIdEpreuve(), resultat.getIdParticipant(), resultat.getTemps(), resultat.getPosition());
    }

    /**
     * CONSULTER LES RESULTATS
     * <p>
     * Permet de récupérer tous les resultats
     *
     * @return
     * @throws Exception
     */
    @GetMapping("resultat/all")
    public Iterable<Resultat> getAllResultat() throws Exception {
        return serviceResultat.recupererAllResultat();
    }

    /**
     * Permet de supprimer les détails d'une résultat
     *
     * @param idResultat
     * @return
     * @throws Exception
     */
    @DeleteMapping("resultat/delete/{id}")
    public String supprimerResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.supprimerResultat(idResultat);
    }

    /**
     * Permet de récupérer les détails d'un résultat
     *
     * @param idResultat
     * @return
     * @throws Exception
     */
    @GetMapping("resultat/id/{id}")
    public Resultat getResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.recupererResultat(idResultat);
    }


    /**
     * CONSULTER LE CLASSEMENT
     *
     * @return
     * @throws Exception
     */
    @GetMapping("classement")
    public Map<String, Map<String, Map<String, Integer>>> getClassement() throws Exception {
        return serviceResultat.afficherClassement();
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* DELEGATION **********************************************************************
     */

    /**
     * Permet de créer une nouvelle délégation
     *
     * @param delegation
     * @return
     */
    @PostMapping("delegation")
    public Delegation creerDelegation(@RequestBody Delegation delegation) {
        return serviceDelegation.creerDelegation(delegation.getNom(), delegation.getNbMedailleOr(), delegation.getNbMedailleArgent(), delegation.getNbMedailleBronze());
    }

    /**
     * Permet de récupérer les détails d'une Delegation
     *
     * @param idDelegation
     * @return
     * @throws Exception
     */
    @GetMapping("delegation/id/{id}")
    public Delegation getDelegation(@PathVariable("id") Long idDelegation) throws Exception {
        return serviceDelegation.recupererDelegation(idDelegation);
    }

    /**
     * Permet de récupérer les détails d'une délégation
     *
     * @param nomDelegation
     * @return
     * @throws Exception
     */
    @GetMapping("delegation/nom/{nom}")
    public Delegation getDelegation(@PathVariable("nom") String nomDelegation) throws Exception {
        return serviceDelegation.recupererDelegation(nomDelegation);
    }

    /**
     * Récupérer toutes les délégations
     *
     * @return
     * @throws Exception
     */
    @GetMapping("delegation/all")
    public Iterable<Delegation> getAllDelegation() throws Exception {
        return serviceDelegation.recupererAllDelegation();
    }

    /**
     * Supprimer une délégation
     *
     * @param idDelegation
     * @return
     * @throws Exception
     */
    @DeleteMapping("delegation/delete/{id}")
    public String supprimerDelegation(@PathVariable("id") Long idDelegation) throws Exception {
        return serviceDelegation.supprimerDelegation(idDelegation);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* PARTRICIPANT **********************************************************************
     */

    /**
     * Créer un participant
     *
     * @param participant
     * @return
     */
    @PostMapping("participant")
    public Participant creerParticipant(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(),
                participant.getPrenom(),
                participant.getEmail(),
                participant.getDelegation(),
                participant.getEpreuveList());
    }

    /**
     * Récupérer les détails d'un participant
     *
     * @param idParticipant
     * @return
     * @throws Exception
     */
    @GetMapping("participant/id/{id}")
    public Participant getParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(idParticipant);
    }

    /**
     * Récupérer les détails d'un participant
     *
     * @param nomParticipant
     * @param prenomParticipant
     * @return
     * @throws Exception
     */
    @GetMapping("participant/prenomNom/{prenom}/{nom}")
    public Participant getParticipantNomPrenom(@PathVariable("nom") String nomParticipant, @PathVariable("prenom") String prenomParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(prenomParticipant, nomParticipant);
    }

    /**
     * Récupérer la liste de tous les participants
     *
     * @return
     * @throws Exception
     */
    @GetMapping("participant/all")
    public Iterable<Participant> getAllParticipant() throws Exception {
        return serviceParticipant.recupererAllParticipant();
    }

    /**
     * Supprimer un participant
     *
     * @param idParticipant
     * @return
     * @throws Exception
     */
    @DeleteMapping("participant/delete/{id}")
    public String supprimerParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.supprimerParticipant(idParticipant);
    }

    /**
     * Créer un participant
     *
     * @param participant
     * @return
     */
    @PostMapping("participant/null")
    public Participant creerParticipantNull(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(),
                participant.getPrenom(),
                participant.getEmail(),
                null,
                null);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* EPREUVE **********************************************************************
     */

    /**
     * Permet d'ajouter une épreuve
     *
     * @param epreuve
     * @return Epreuve
     */
    @PostMapping("epreuve")
    public Epreuve creerEpreuve(@RequestBody Epreuve epreuve) throws Exception {
        return serviceEpreuve.creerEpreuve(epreuve.getNomEpreuve(),
                epreuve.getDateEpreuve(),
                epreuve.getInfrastructureAccueil(),
                epreuve.getNbPlacesDispo(),
                epreuve.getNbPlacesInit(),
                epreuve.getPrixBillet());
    }

    /**
     * Permet de modifier une épreuve
     *
     * @param epreuve L'épreuve à modifier
     * @param nom     Le nom de l'épreuve à modifier
     * @return L'épreuve modifiée
     * @throws Exception En cas d'erreur lors de la modification
     */
    @PutMapping("epreuve/modifier/{nom}")
    public Epreuve modifierEpreuve(@RequestBody Epreuve epreuve, @PathVariable("nom") String nom) throws Exception {
        return serviceEpreuve.modifierEpreuve(nom, epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), epreuve.getNbPlacesDispo());
    }

    /**
     * Supprimer une épreuve
     *
     * @param idEpreuve
     * @return
     * @throws Exception
     */
    @DeleteMapping("epreuve/delete/{id}")
    public String supprimerEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.supprimerEpreuve(idEpreuve);
    }

    /**
     * CONSULTER LES EPREUVES DISPONIBLES
     * Récupérer la liste de toutes les épreuves
     *
     * @return
     * @throws Exception
     */
    @GetMapping("epreuve/all")
    public Iterable<Epreuve> getAllEpreuve() throws Exception {
        return serviceEpreuve.recupererAllEpreuve();
    }

    /**
     * Récupérer les détails d'une épreuve
     *
     * @param idEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("epreuve/id/{id}")
    public Epreuve getEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(idEpreuve);
    }

    /**
     * Récupérer les détails d'une épreuve
     *
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("epreuve/nom/{nom}")
    public Epreuve getEpreuve(@PathVariable("nom") String nomEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(nomEpreuve);
    }

    /**
     * Permet d'ajouter une épreuve
     *
     * @param epreuve
     * @return Epreuve
     */
    @PostMapping("epreuve/null")
    public Epreuve creerEpreuveNull(@RequestBody Epreuve epreuve) throws Exception {
        return serviceEpreuve.creerEpreuve(epreuve.getNomEpreuve(),
                epreuve.getDateEpreuve(),
                null,
                epreuve.getNbPlacesDispo(),
                epreuve.getNbPlacesInit(),
                epreuve.getPrixBillet());
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* INFRASTRUCTURE SPORTIVE **********************************************************************
     */

    /**
     * Créer une infrastructure sportive
     *
     * @param infrastructureSportive
     * @return
     */
    @PostMapping("infrastructure")
    public InfrastructureSportive creerInfrastructureSportive(@RequestBody InfrastructureSportive infrastructureSportive) {
        return serviceInfrastructureSportive.creerInfrastructureSportive(infrastructureSportive.getNom(), infrastructureSportive.getAdresse(), infrastructureSportive.getCapacite());
    }

    /**
     * Récupérer les détails d'une infrastructure sportive
     *
     * @param idInfrastructureSportive
     * @return
     * @throws Exception
     */
    @GetMapping("infrastructure/id/{id}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(idInfrastructureSportive);
    }

    /**
     * Récupérer les détails d'une infrastructure sportive
     *
     * @param nomInfrastructureSportive
     * @return
     * @throws Exception
     */
    @GetMapping("infrastructure/nom/{nom}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("nom") String nomInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(nomInfrastructureSportive);
    }

    /**
     * Récupérer toutes les infrastructures sportives
     *
     * @return
     * @throws Exception
     */
    @GetMapping("infrastructure/all")
    public Iterable<InfrastructureSportive> getAllInfrastructureSportive() throws Exception {
        return serviceInfrastructureSportive.recupererAllInfrastructureSportive();
    }

    /**
     * Supprimer une infrastructure sportive
     *
     * @param idInfrastructureSportive
     * @return
     * @throws Exception
     */
    @DeleteMapping("infrastructure/delete/{id}")
    public String supprimerInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.supprimerInfrastructureSportive(idInfrastructureSportive);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* BILLET **********************************************************************
     */

    /**
     * Créer un billet
     *
     * @param billet
     * @return
     */
    @PostMapping("billet")
    public Billet creerBillet(@RequestBody Billet billet) {
        return serviceBillet.creerBillet(billet.getIdEpreuve()
                , billet.getIdSpectateur(),
                billet.getPrix(),
                billet.getDateBillet());
    }

    /**
     * Récupérer un billet
     *
     * @param idBillet
     * @return
     * @throws Exception
     */
    @GetMapping("billet/id/{id}")
    public Billet getBillet(@PathVariable("id") long idBillet) throws Exception {
        return serviceBillet.recupererBillet(idBillet);
    }

    /**
     * Supprimer un billet
     *
     * @param idBillet
     * @return
     * @throws Exception
     */
    @DeleteMapping("billet/delete/{id}")
    public String supprimerBillet(@PathVariable("id") long idBillet) throws Exception {
        return serviceBillet.supprimerBillet(idBillet);
    }

    /**
     * Récupérer tous les billets
     *
     * @return
     * @throws Exception
     */
    @GetMapping("billet/all")
    public Iterable<Billet> getAllBillet() throws Exception {
        return serviceBillet.recupererAllBillet();
    }

    /**
     * SCANNER BILLET
     * Permet au controller de scanner un billet
     *
     * @param nomOrganisateur
     * @param prenomOrganisateur
     * @param idBillet
     * @param idSpectateur
     * @return
     * @throws Exception
     */
    @GetMapping("scanner/{prenom}/{nom}/{idBillet}/{idSpectateur}")
    public String scannerBillet(@PathVariable("nom") String nomOrganisateur,
                                @PathVariable("prenom") String prenomOrganisateur,
                                @PathVariable("idBillet") Long idBillet,
                                @PathVariable("idSpectateur") Long idSpectateur) throws Exception {
        return serviceBillet.validerBillet(nomOrganisateur,
                prenomOrganisateur,
                idBillet,
                idSpectateur);
    }
    /**
     *    *******************************************************************************************
     */
}
