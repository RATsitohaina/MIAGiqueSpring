package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Participant;
import com.miage.miagiquespring.metier.ServiceBillet;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource Billet
 */
@RestController
@RequestMapping("/api/billet")
public class RestServiceBillet {

    private final ServiceBillet serviceBillet;

    public RestServiceBillet(ServiceBillet serviceBillet) {
        this.serviceBillet = serviceBillet;
    }

    /**
     *Permet d'ajouter une épreuve
     * @param billet
     * @return billet
     */
    @PostMapping
    public Billet creerBillet(@RequestBody Billet billet) {
        return serviceBillet.creerBillet(billet.getIdEpreuve(),billet.getIdSpectateur(),billet.getPrix(), billet.getEtat());
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
}
