package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Resultat;
import com.miage.miagiquespring.metier.ServiceResultat;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource resultat
 */
@RestController
@RequestMapping("/api/resultat")
public class RestServiceResultat {


    private final ServiceResultat serviceResultat;
    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceResultat le bean métier client injecté
     */
    public RestServiceResultat(ServiceResultat serviceResultat) {
        this.serviceResultat = serviceResultat;
    }

    /**
     * Permet de créer un nouveau client
     * @param resultat les détails d'un resultat envoyés par le front
     */
    @PostMapping
    public Resultat creerResultat(@RequestBody Resultat resultat) {
        return serviceResultat.creerResultat(resultat.getIdEpreuve(),resultat.getIdParticipant(),resultat.getTemps(), resultat.getPosition());
    }

    /**
     * Permet de récupérer les détails d'une Resultat
     * @param idResultat id d'une Resultat
     */
    @GetMapping("{id}")
    public Resultat getResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.recupererResultat(idResultat);
    }

    /**
     * Permet de récupérer les détails d'une Resultat
     */
    @GetMapping("all")
    public Iterable<Resultat> getAllResultat() throws Exception {
        return serviceResultat.recupererAllResultat();
    }

    /**
     * Permet de supprimer les détails d'une Resultat
     * @param idResultat d'une Resultat
     */
    @DeleteMapping("{id}")
    public String supprimerResultat(@PathVariable("id") Long idResultat) throws Exception {
        return serviceResultat.supprimerResultat(idResultat);
    }
}
