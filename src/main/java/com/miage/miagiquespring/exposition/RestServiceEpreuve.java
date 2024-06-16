package com.miage.miagiquespring.exposition;


import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.entities.Spectateur;
import com.miage.miagiquespring.metier.ServiceEpreuve;
import com.miage.miagiquespring.metier.ServiceInfrastructureSportive;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;


/**
 * Contrôleur REST pour la ressource infrastructuresportive
 */
@RestController
@RequestMapping("/api/epreuve")
public class RestServiceEpreuve {

    private final ServiceEpreuve serviceEpreuve;

    public RestServiceEpreuve(ServiceEpreuve serviceEpreuve) {
        this.serviceEpreuve = serviceEpreuve;
    }

    /**
     *Permet d'ajouter une épreuve
     * @param epreuve
     * @return Epreuve
     */
    @PostMapping
    public Epreuve creerEpreuve(@RequestBody Epreuve epreuve) {
        return serviceEpreuve.creerEpreuve(epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), epreuve.getInfrastructureAccueil(),epreuve.getNbPlacesDispo(), epreuve.getBillets());
    }

    /**
     * Permet de modifier une épreuve
     * @param epreuve L'épreuve à modifier
     * @param nom Le nom de l'épreuve à modifier
     * @return L'épreuve modifiée
     * @throws Exception En cas d'erreur lors de la modification
     */
    @PutMapping("modifier/{nom}")
    public Epreuve modifierEpreuve(@RequestBody Epreuve epreuve, @PathVariable("nom") String nom) throws Exception {
        return serviceEpreuve.modifierEpreuve(nom, epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), epreuve.getNbPlacesDispo());
    }


    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param idEpreuve
     */
    @GetMapping("id/{id}")
    public Epreuve getEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(idEpreuve);
    }

    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param nomEpreuve
     */
    @GetMapping("nom/{nom}")
    public Epreuve getEpreuve(@PathVariable("nom") String nomEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(nomEpreuve);
    }

    /**
     * Permet de récupérer toute les epreuve pour une requête GET
     */
    @GetMapping()
    public Iterable<Epreuve> getAllEpreuve() throws Exception {
        return serviceEpreuve.recupererAllEpreuve();
    }

    /**
     * Permet de supprimer une epreuve pour une requête DELETE
     * @param idEpreuve
     */
    @DeleteMapping("{id}")
    public String supprimerEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.supprimerEpreuve(idEpreuve);
    }

    /**
     *Permet d'ajouter une épreuve
     * @param epreuve
     * @return Epreuve
     */
    @PostMapping("/null")
    public Epreuve creerEpreuveNull(@RequestBody Epreuve epreuve) {
        return serviceEpreuve.creerEpreuve(epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), null,epreuve.getNbPlacesDispo(),null);
    }
}
