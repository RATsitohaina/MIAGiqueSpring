package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Spectateur;
import com.miage.miagiquespring.metier.ServiceSpectateur;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public Spectateur creerSpectateur(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), spectateur.getBillets(), spectateur.getResultats());
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
    @DeleteMapping("{id}")
    public String supprimerSpectateur(@PathVariable("id") long idSpectateur) throws Exception {
        return serviceSpectateur.supprimerSpectateur(idSpectateur);
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param spectateur les détails du client envoyés par le front
     */
    @PostMapping("/null")
    public Spectateur creerSpectateurNull(@RequestBody Spectateur spectateur) {
        return serviceSpectateur.creerSpectateur(spectateur.getNom(), spectateur.getPrenom(), spectateur.getEmail(), null, null);
    }
}
