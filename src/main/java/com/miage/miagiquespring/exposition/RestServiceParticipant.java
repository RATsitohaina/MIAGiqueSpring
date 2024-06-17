package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Participant;
import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.metier.ServiceEpreuve;
import com.miage.miagiquespring.metier.ServiceParticipant;
import com.miage.miagiquespring.metier.ServiceResultat;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource participant
 */
@RestController
@RequestMapping("/api/participant")
public class RestServiceParticipant {

    private final ServiceParticipant serviceParticipant;
    private final ServiceEpreuve serviceEpreuve;
    private final ServiceResultat serviceResultat;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceParticipant le bean métier client injecté
     */
    public RestServiceParticipant(ServiceParticipant serviceParticipant, ServiceEpreuve serviceEpreuve, ServiceResultat serviceResultat) {
        this.serviceParticipant = serviceParticipant;
        this.serviceEpreuve = serviceEpreuve;
        this.serviceResultat = serviceResultat;
    }

    /**
     *    ********* EPREUVE **********************************************************************
     */

    /**
     * CONSULTER LES EPREUVES DISPONIBLES
     * Permet de récupérer toute les epreuve pour une requête GET
     */
    @GetMapping("epreuves")
    public Iterable<Epreuve> getAllEpreuve() throws Exception {
        return serviceEpreuve.recupererAllEpreuve();
    }

    /** INSCRIPTION EPREUVE
     * Permet de s'inscrire a une epreuve
     */
    @PostMapping("inscrire/{prenom}/{nom}/{epreuve}")
    public String inscrireEpreuve(@PathVariable("nom") String nomParticipant,
                                    @PathVariable("prenom") String prenomParticipant,
                                    @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceParticipant.participerEpreuve(nomParticipant,prenomParticipant,nomEpreuve);
    }

    /** DESENGAGER EPREUVE
     * Permet de se désengager d'une epreuve
     */
    @PostMapping("desengager/{prenom}/{nom}/{epreuve}")
    public String desengagerEpreuve(@PathVariable("nom") String nomParticipant,
                                  @PathVariable("prenom") String prenomParticipant,
                                  @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceParticipant.desengagerEpreuve(nomParticipant,prenomParticipant,nomEpreuve);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* RESULTAT **********************************************************************
     */

    /**
     * CONSULTER LES RESULTATS
     * Permet de récupérer tous les resultats
     */
    @GetMapping("resultats")
    public Iterable<Resultat> getAllResultat() throws Exception {
        return serviceResultat.recupererAllResultat();
    }

    /**
     *    *******************************************************************************************
     */
}
