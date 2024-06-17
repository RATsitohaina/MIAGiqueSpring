package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.metier.ServiceEpreuve;
import com.miage.miagiquespring.metier.ServiceParticipant;
import com.miage.miagiquespring.metier.ServiceResultat;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     *
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
     * INSCRIPTION EPREUVE
     * Inscrire à une épreuve
     *
     * @param nomParticipant
     * @param prenomParticipant
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("inscrire/prenomNomNomEpreuve/{prenom}/{nom}/{epreuve}")
    public String inscrireEpreuve(@PathVariable("nom") String nomParticipant,
                                  @PathVariable("prenom") String prenomParticipant,
                                  @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceParticipant.participerEpreuve(nomParticipant, prenomParticipant, nomEpreuve);
    }


    /**
     * DESENGAGER EPREUVE
     * Se désengager d'un épreuve
     *
     * @param nomParticipant
     * @param prenomParticipant
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("desengager/prenomNomNomEpreuve/{prenom}/{nom}/{epreuve}")
    public String desengagerEpreuve(@PathVariable("nom") String nomParticipant,
                                    @PathVariable("prenom") String prenomParticipant,
                                    @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceParticipant.desengagerEpreuve(nomParticipant, prenomParticipant, nomEpreuve);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* RESULTAT **********************************************************************
     */

    /**
     * CONSULTER RESULTATS
     * Récupérer tous les résultats
     *
     * @return
     * @throws Exception
     */
    @GetMapping("resultat/all")
    public Iterable<Resultat> getAllResultat() throws Exception {
        return serviceResultat.recupererAllResultat();
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
}
