package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.*;
import com.miage.miagiquespring.metier.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
     * @param serviceOrganisateur le bean métier client injecté
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
     * Permet de créer un nouveau spectateur
     * @param organisateur les détails du client envoyés par le front
     */
    @PostMapping
    public Organisateur creerOrganisateur(@RequestBody Organisateur organisateur) {
        return serviceOrganisateur.creerOrganisateur(organisateur.getNom(), organisateur.getPrenom(),
                organisateur.getEmail(), organisateur.getRoleOrganisateur());
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idOrganisateur id d'un Utilisateur
     */
    @GetMapping("{id}")
    public Organisateur getOrganisateur(@PathVariable("id") long idOrganisateur) throws Exception {
        return serviceOrganisateur.recupererOrganisateur(idOrganisateur);
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     */
    @GetMapping("{prenom}/{nom}")
    public Organisateur getOrganisateurNomPrenom(@PathVariable("nom") String nomOrganisateur, @PathVariable("prenom") String prenomOrganisateur) throws Exception {
        return serviceOrganisateur.recupererOrganisateur(prenomOrganisateur, nomOrganisateur);
    }

    /**
     * Permet de récupérer tout les détails d'un Utilisateur
     */
    @GetMapping("all")
    public Iterable<Organisateur> getAllOrganisateur() throws Exception {
        return serviceOrganisateur.recupererAllOrganisateur();
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idOrganisateur id d'un Utilisateur
     */
    @DeleteMapping("{id}")
    public String supprimerOrganisateur(@PathVariable("id") long idOrganisateur) throws Exception {
        return serviceOrganisateur.supprimerOrganisateur(idOrganisateur);
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param organisateur les détails du client envoyés par le front
     */
    @PostMapping("/null")
    public Organisateur creerOrganisateurNull(@RequestBody Organisateur organisateur) {
        return serviceOrganisateur.creerOrganisateur(organisateur.getNom(), organisateur.getPrenom()
                , organisateur.getEmail(),true);
    }

    /**
     * STATISTIQUE DE VENTE
     * Permet de récupérer les statistiques de vente
     */
    @GetMapping("statistique/{prenom}/{nom}")
    public HashMap<String, Float> getStatistique(@PathVariable("prenom") String prenomOrganisateur,
                                                 @PathVariable("nom") String nomOrganisateur) throws Exception {
        return serviceOrganisateur.calculerStatDeVentes(prenomOrganisateur,nomOrganisateur);
    }

    /**
     *    ********* RESULTAT **********************************************************************
     */

    /**
     * Permet de créer un nouveau resultat
     * @param resultat les détails d'un resultat envoyés par le front
     */
    @PostMapping("resultat")
    public Resultat creerResultat(@RequestBody Resultat resultat) {
        return serviceResultat.creerResultat(resultat.getIdEpreuve(),resultat.getIdParticipant(),resultat.getTemps(), resultat.getPosition());
    }

    /**
     * CONSULTER LES RESULTATS
     * Permet de récupérer tous les resultats
     */
    @GetMapping("resultat/all")
    public Iterable<Resultat> getAllResultat() throws Exception {
        return serviceResultat.recupererAllResultat();
    }

    /**
     * Permet de supprimer les détails d'une Resultat
     * @param idResultat d'une Resultat
     */
    @DeleteMapping("resultat/delete/{id}")
    public String supprimerResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.supprimerResultat(idResultat);
    }

    /**
     * Permet de récupérer les détails d'une Resultat
     * @param idResultat id d'une Resultat
     */
    @GetMapping("resultat/id/{id}")
    public Resultat getResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.recupererResultat(idResultat);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* BILLET **********************************************************************
     */

    /** SCANNER BILLET
     * Permet au controller de scanner un billet
     */
    @PostMapping("scanner/{prenom}/{nom}/{idBillet}/{idSpectateur}")
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

    /**
     *    ********* DELEGATION **********************************************************************
     */

    /**
     * Permet de créer un nouveau client
     * @param delegation les détails du client envoyés par le front
     */
    @PostMapping("delegation")
    public Delegation creerDelegation(@RequestBody Delegation delegation) {
        return serviceDelegation.creerDelegation(delegation.getNom(), delegation.getNbMedailleOr(), delegation.getNbMedailleArgent(), delegation.getNbMedailleBronze());
    }

    /**
     * Permet de récupérer les détails d'une Delegation
     * @param idDelegation id d'une Delegation
     */
    @GetMapping("delegation/id/{id}")
    public Delegation getDelegation(@PathVariable("id") Long idDelegation) throws Exception {
        return serviceDelegation.recupererDelegation(idDelegation);
    }

    /**
     * Permet de récupérer les détails d'un Delegation
     */
    @GetMapping("delegation/nom/{nom}")
    public Delegation getDelegation(@PathVariable("nom") String nomDelegation) throws Exception {
        return serviceDelegation.recupererDelegation(nomDelegation);
    }

    /**
     * Permet de récupérer tout les détails d'un Delegation
     */
    @GetMapping("delegation/all")
    public Iterable<Delegation> getAllDelegation() throws Exception {
        return serviceDelegation.recupererAllDelegation();
    }

    /**
     * Permet de supprimer les détails d'une InfrastructureSportive
     * @param idDelegation id d'une InfrastructureSportive
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
     * Permet de créer un nouveau spectateur
     * @param participant les détails du client envoyés par le front
     */
    @PostMapping("participant")
    public Participant creerParticipant(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(),
                participant.getPrenom(),
                participant.getEmail(),
                participant.getDelegation(),
                participant.getResultatList(),
                participant.getEpreuveList());
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idParticipant id d'un Utilisateur
     */
    @GetMapping("participant/{id}")
    public Participant getParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(idParticipant);
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     */
    @GetMapping("participant/{prenom}/{nom}")
    public Participant getParticipantNomPrenom(@PathVariable("nom") String nomParticipant, @PathVariable("prenom") String prenomParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(prenomParticipant, nomParticipant);
    }

    /**
     * Permet de récupérer tout les détails d'un Utilisateur
     */
    @GetMapping("participant/all")
    public Iterable<Participant> getAllParticipant() throws Exception {
        return serviceParticipant.recupererAllParticipant();
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idParticipant id d'un Utilisateur
     */
    @DeleteMapping("participant/delete/{id}")
    public String supprimerParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.supprimerParticipant(idParticipant);
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param participant les détails du client envoyés par le front
     */
    @PostMapping("participant/null")
    public Participant creerParticipantNull(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(), participant.getPrenom(), participant.getEmail(), null, null, null);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* EPREUVE **********************************************************************
     */

    /**
     *Permet d'ajouter une épreuve
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
     * @param epreuve L'épreuve à modifier
     * @param nom Le nom de l'épreuve à modifier
     * @return L'épreuve modifiée
     * @throws Exception En cas d'erreur lors de la modification
     */
    @PutMapping("epreuve/modifier/{nom}")
    public Epreuve modifierEpreuve(@RequestBody Epreuve epreuve, @PathVariable("nom") String nom) throws Exception {
        return serviceEpreuve.modifierEpreuve(nom, epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), epreuve.getNbPlacesDispo());
    }

    /**
     * Permet de supprimer une epreuve pour une requête DELETE
     * @param idEpreuve
     */
    @DeleteMapping("epreuve/delete/{id}")
    public String supprimerEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.supprimerEpreuve(idEpreuve);
    }

    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param idEpreuve
     */
    @GetMapping("epreuve/id/{id}")
    public Epreuve getEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(idEpreuve);
    }

    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param nomEpreuve
     */
    @GetMapping("epreuve/nom/{nom}")
    public Epreuve getEpreuve(@PathVariable("nom") String nomEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(nomEpreuve);
    }

    /**
     *Permet d'ajouter une épreuve
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
     * Permet de créer un nouveau infrastructuresportive
     * @param infrastructureSportive les détails du client envoyés par le front
     */
    @PostMapping
    public InfrastructureSportive creerInfrastructureSportive(@RequestBody InfrastructureSportive infrastructureSportive) {
        return serviceInfrastructureSportive.creerInfrastructureSportive(infrastructureSportive.getNom(), infrastructureSportive.getAdresse(), infrastructureSportive.getCapacite());
    }

    /**
     * Permet de récupérer les détails d'une InfrastructureSportive
     * @param idInfrastructureSportive id d'une InfrastructureSportive
     */
    @GetMapping("id/{id}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(idInfrastructureSportive);
    }

    /**
     * Permet de récupérer les détails d'une InfrastructureSportive
     * @param nomInfrastructureSportive d'une InfrastructureSportive
     */
    @GetMapping("nom/{nom}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("nom") String nomInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(nomInfrastructureSportive);
    }

    /**
     * Permet de récupérer tout les détails d'une InfrastructureSportive
     */
    @GetMapping("all")
    public Iterable<InfrastructureSportive> getAllInfrastructureSportive() throws Exception {
        return serviceInfrastructureSportive.recupererAllInfrastructureSportive();
    }

    /**
     * Permet de supprimer les détails d'une InfrastructureSportive
     * @param idInfrastructureSportive id d'une InfrastructureSportive
     */
    @DeleteMapping("{id}")
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
     *Permet d'ajouter une épreuve
     * @param billet
     * @return billet
     */
    @PostMapping
    public Billet creerBillet(@RequestBody Billet billet) {
        return serviceBillet.creerBillet(billet.getIdEpreuve()
                ,billet.getIdSpectateur(),
                billet.getPrix(),
                billet.getDateBillet());
    }

    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param idBillet
     */
    @GetMapping("{id}")
    public Billet getBillet(@PathVariable("id") long idBillet) throws Exception {
        return serviceBillet.recupererBillet(idBillet);
    }

    /**
     * Permet de supprimer une epreuve pour une requête DELETE
     * @param idBillet
     */
    @DeleteMapping("{id}")
    public String supprimerBillet(@PathVariable("id") long idBillet) throws Exception {
        return serviceBillet.supprimerBillet(idBillet);
    }

    /**
     * Permet de récupérer tout les détails d'un Billet
     */
    @GetMapping("all")
    public Iterable<Billet> getAllBillet() throws Exception {
        return serviceBillet.recupererAllBillet();
    }
    /**
     *    *******************************************************************************************
     */
}
