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
     *
     * @param serviceSpectateur le bean métier client injecté
     */
    public RestServiceSpectateur(ServiceSpectateur serviceSpectateur, ServiceEpreuve serviceEpreuve, ServiceBillet serviceBillet, ServiceResultat serviceResultat) {
        this.serviceSpectateur = serviceSpectateur;
        this.serviceEpreuve = serviceEpreuve;
        this.serviceBillet = serviceBillet;
        this.serviceResultat = serviceResultat;
    }

    /**
     * Créer un spectateur
     *
     * @param spectateur
     * @return
     */
    @PostMapping
    public Spectateur creerSpectateur(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), spectateur.getBillets());
    }

    /**
     * Récupérer les détails d'un spectateur
     *
     * @param idSpectateur
     * @return
     * @throws Exception
     */
    @GetMapping("id/{id}")
    public Spectateur getSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(idSpectateur);
    }

    /**
     * Récupérer les détails d'un spectateur
     *
     * @param nomSpectateur
     * @param prenomSpectateur
     * @return
     * @throws Exception
     */
    @GetMapping("prenomNomNom/{prenom}/{nom}")
    public Spectateur getSpectateur(@PathVariable("nom") String nomSpectateur, @PathVariable("prenom") String prenomSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(prenomSpectateur, nomSpectateur);
    }

    /**
     * Récupérer tous les spectateurs
     *
     * @return
     * @throws Exception
     */
    @GetMapping("all")
    public Iterable<Spectateur> getAllSpectateur() throws Exception {
        return serviceSpectateur.recupererAllSpectateur();
    }

    /**
     * Supprimer un spectateur
     *
     * @param idSpectateur
     * @return
     * @throws Exception
     */
    @DeleteMapping("delete/{id}")
    public String supprimerSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.supprimerSpectateur(idSpectateur);
    }

    /**
     * Créer un spectateur
     *
     * @param spectateur
     * @return
     */
    @PostMapping("/null")
    public Spectateur creerSpectateurNull(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), null);
    }

    /**
     *    ********* EPREUVE **********************************************************************
     */

    /**
     * CONSULTER EPREUVES
     * Récupérer toutes les épreuves disponibles
     *
     * @return
     * @throws Exception
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


    /**
     * RESERVER BILLET
     *
     * @param nomSpectateur
     * @param prenomSpectateur
     * @param nomEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("reservationBillet/prenomNomNomEpreuve/{prenom}/{nom}/{epreuve}")
    public String reservationBillet(@PathVariable("nom") String nomSpectateur,
                                    @PathVariable("prenom") String prenomSpectateur,
                                    @PathVariable("epreuve") String nomEpreuve) throws Exception {
        return serviceBillet.reservationBillet(prenomSpectateur, nomSpectateur, nomEpreuve);
    }

    /**
     * ANNULER RESERVATION D'UN BILLET
     *
     * @param nomSpectateur
     * @param prenomSpectateur
     * @param idBillet
     * @return
     * @throws Exception
     */
    @GetMapping("annulationBillet/prenomNomNomIdBillet/{prenom}/{nom}/{idBillet}")
    public String annulerBillet(@PathVariable("nom") String nomSpectateur,
                                @PathVariable("prenom") String prenomSpectateur,
                                @PathVariable("idBillet") Long idBillet) throws Exception {
        return serviceBillet.annulationBillet(prenomSpectateur, nomSpectateur, idBillet);
    }

    /**
     * CONSULTER SES BILLETS
     *
     * @param idSpectateur
     * @return
     * @throws Exception
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
