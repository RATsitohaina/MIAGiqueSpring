package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Organisateur;
import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.metier.ServiceOrganisateur;
import com.miage.miagiquespring.metier.ServiceResultat;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource organisateur
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/organisateur")
public class RestServiceOrganisateur {

    private final ServiceOrganisateur serviceOrganisateur;
    private final ServiceResultat serviceResultat;
    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceOrganisateur le bean métier client injecté
     */
    public RestServiceOrganisateur(ServiceOrganisateur serviceOrganisateur, ServiceResultat serviceResultat) {
        this.serviceOrganisateur = serviceOrganisateur;
        this.serviceResultat = serviceResultat;
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param organisateur les détails du client envoyés par le front
     */
    @PostMapping
    public Organisateur creerOrganisateur(@RequestBody Organisateur organisateur) {
        return serviceOrganisateur.creerOrganisateur(organisateur.getNom(), organisateur.getPrenom(),
                organisateur.getEmail(),organisateur.getDelegationList(),
                organisateur.getParticipantList(),organisateur.getResultatList(),
                organisateur.getEpreuveList(), organisateur.getBilletList(),
                organisateur.getInfrastructureSportiveList(),
                organisateur.getRoleOrganisateur());
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
                , organisateur.getEmail(), null
                , null, null
                , null, null
                , null
                ,true);
    }

    /**
     * Permet de créer un nouveau resultat
     * @param resultat les détails d'un resultat envoyés par le front
     */
    @PostMapping("resultat")
    public Resultat creerResultat(@RequestBody Resultat resultat) {
        return serviceResultat.creerResultat(resultat.getIdEpreuve(),resultat.getIdParticipant(),resultat.getTemps(), resultat.getPosition());
    }
}
