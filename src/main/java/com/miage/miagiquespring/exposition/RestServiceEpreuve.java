package com.miage.miagiquespring.exposition;


import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.metier.ServiceEpreuve;
import com.miage.miagiquespring.metier.ServiceInfrastructureSportive;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


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
     * @return
     */
    @PostMapping
    public Epreuve ajouterEpreuve(@RequestBody Epreuve epreuve) {
        return serviceEpreuve.ajouterEpreuve(epreuve.getNomEpreuve(), epreuve.getDateEpreuve(), epreuve.getInfrastructureAccueil(),epreuve.getNbPlacesDispo());
    }

    /**
     * Permet de récupérer une epreuve pour une requête GET
     * @param idEpreuve
     * @return
     * @throws Exception
     */
    @GetMapping("{id}")
    public Epreuve getEpreuve(@PathVariable("id") long idEpreuve) throws Exception {
        return serviceEpreuve.recupererEpreuve(idEpreuve);
    }
    /**
     * AJouter la méthode modifier
     */


}
