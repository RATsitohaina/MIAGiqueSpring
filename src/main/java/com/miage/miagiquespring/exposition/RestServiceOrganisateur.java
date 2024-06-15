package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Organisateur;
import com.miage.miagiquespring.metier.ServiceOrganisateur;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource organisateur
 */
@RestController
@RequestMapping("/api/organisateur")
public class RestServiceOrganisateur {

    private final ServiceOrganisateur serviceOrganisateur;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceOrganisateur le bean métier client injecté
     */
    public RestServiceOrganisateur(ServiceOrganisateur serviceOrganisateur) {
        this.serviceOrganisateur = serviceOrganisateur;
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
                organisateur.getInfrastructureSportiveList());
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
                , null);
    }
}
