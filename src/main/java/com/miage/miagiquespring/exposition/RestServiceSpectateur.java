package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Spectateur;
import com.miage.miagiquespring.metier.ServiceSpectateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la ressource utilisateur
 */
@RestController
@RequestMapping("/api/spectateur")
public class RestServiceSpectateur {

    private final ServiceSpectateur serviceSpectateur;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceSpectateur le bean métier client injecté
     */
    public RestServiceSpectateur(ServiceSpectateur serviceSpectateur) {
        this.serviceSpectateur = serviceSpectateur;
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param spectateur les détails du client envoyés par le front
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Spectateur creerSpectateur(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(),spectateur.getEpreuves());
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idSpectateur id d'un Utilisateur
     */
    @GetMapping("{id}")
    public Spectateur getSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(idSpectateur);
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idSpectateur id d'un Utilisateur
     */
    @GetMapping("delete/{id}")
    public void deleteSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        serviceSpectateur.supprimerSpectateur(idSpectateur);
    }

    @GetMapping("{id}/epreuves")
    public List<Epreuve> listerEpreuves(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.recupererSpectateur(idSpectateur).getEpreuves();
    }
}
