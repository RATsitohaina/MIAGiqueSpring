package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Spectateur;
import com.miage.miagiquespring.metier.ServiceBillet;
import com.miage.miagiquespring.metier.ServiceEpreuve;
import com.miage.miagiquespring.metier.ServiceResultat;
import com.miage.miagiquespring.metier.ServiceSpectateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST pour la ressource utilisateur
 */
@RestController
@RequestMapping("/api/spectateur")
public class RestServiceSpectateur {

    private final ServiceSpectateur serviceSpectateur;
    private final ServiceEpreuve serviceEpreuve;
    private final ServiceBillet serviceBillet;
    private final ServiceResultat serviceResultat;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceSpectateur le bean métier client injecté
     */
    public RestServiceSpectateur(ServiceSpectateur serviceSpectateur, ServiceEpreuve serviceEpreuve, ServiceBillet serviceBillet, ServiceResultat serviceResultat) {
        this.serviceSpectateur = serviceSpectateur;
        this.serviceEpreuve = serviceEpreuve;
        this.serviceBillet = serviceBillet;
        this.serviceResultat = serviceResultat;
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param spectateur les détails du client envoyés par le front
     */
    @PostMapping
    public Spectateur creerSpectateur(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), spectateur.getBillets());
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idSpectateur id d'un Utilisateur
     */
    @GetMapping("id/{id}")
    public Spectateur getSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(idSpectateur);
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     */
    @GetMapping("prenomNomNom/{prenom}/{nom}")
    public Spectateur getSpectateur(@PathVariable("nom") String nomSpectateur, @PathVariable("prenom") String prenomSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(prenomSpectateur,nomSpectateur);
    }

    /**
     * Permet de récupérer tous les détails d'un Utilisateur
     */
    @GetMapping("all")
    public Iterable<Spectateur> getAllSpectateur() throws Exception {
        return serviceSpectateur.recupererAllSpectateur();
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idSpectateur id d'un Utilisateur
     */
    @DeleteMapping("delete/{id}")
    public String supprimerSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.supprimerSpectateur(idSpectateur);
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param spectateur les détails du client envoyés par le front
     */
    @PostMapping("/null")
    public Spectateur creerSpectateurNull(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), null);
    }

    /**
     *    ********* EPREUVE **********************************************************************
     */

    /**
     * CONSULTER LES EPREUVES DISPONIBLES
     * Permet de récupérer toute les epreuve pour une requête GET
     */
    @GetMapping("epreuve/all")
    public Iterable<Epreuve> getAllEpreuve() throws Exception {
        return serviceEpreuve.recupererAllEpreuve();
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* BILLET **********************************************************************
     */

    /** RESRERVER BILLET
     * Permet de reserver un billet
     */
    @GetMapping("reservationBillet/prenomNomNomEpreuve/{prenom}/{nom}/{epreuve}")
    public String reservationBillet(@PathVariable("nom") String nomSpectateur,
                                        @PathVariable("prenom") String prenomSpectateur,
                                        @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceBillet.reservationBillet(prenomSpectateur,nomSpectateur,nomEpreuve);
    }

    /** ANNULER RESERVATION
     * Permet d'annuler la reservation un billet
     */
    @GetMapping("annulationBillet/prenomNomNomIdBillet/{prenom}/{nom}/{idBillet}")
    public String annulerBillet(@PathVariable("nom") String nomSpectateur,
                                    @PathVariable("prenom") String prenomSpectateur,
                                    @PathVariable("idBillet") Long idBillet) throws Exception {
        return serviceBillet.annulationBillet(prenomSpectateur,nomSpectateur,idBillet);
    }

    /** CONSULTER SES BILLET
     * Permet de consulter les billets
     */
    @GetMapping("billet/id/{id}")
    public List<Billet> consulterBillet(@PathVariable("id") Long idSpectateur) throws Exception {
        return serviceSpectateur.consulterBilletSpectateur(idSpectateur);
    }

    /**
     *    *******************************************************************************************
     */

    /**
     *    ********* RESULTAT **********************************************************************
     */

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
