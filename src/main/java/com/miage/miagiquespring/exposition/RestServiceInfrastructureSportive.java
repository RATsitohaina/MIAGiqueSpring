package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.metier.ServiceInfrastructureSportive;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource infrastructuresportive
 */
@RestController
@RequestMapping("/api/infrastructuresportive")
public class RestServiceInfrastructureSportive {

    private final ServiceInfrastructureSportive serviceInfrastructureSportive;
    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceInfrastructureSportive le bean métier client injecté
     */
    public RestServiceInfrastructureSportive(ServiceInfrastructureSportive serviceInfrastructureSportive) {
        this.serviceInfrastructureSportive = serviceInfrastructureSportive;
    }

    /**
     * Permet de créer un nouveau client
     * @param infrastructureSportive les détails du client envoyés par le front
     */
    @PostMapping
    public InfrastructureSportive creerInfrastructureSportive(@RequestBody InfrastructureSportive infrastructureSportive) {
        return serviceInfrastructureSportive.creerInfrastructureSportive(infrastructureSportive.getNom(), infrastructureSportive.getAdresse(), infrastructureSportive.getCapacite());
    }

    /**
     * Permet de récupérer les détails d'une InfrastructureSportive
     * @param idInfrastructureSportive id d'une InfrastructureSportive
     */
    @GetMapping("{id}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(idInfrastructureSportive);
    }

    /**
     * Permet de supprimer les détails d'une InfrastructureSportive
     * @param idInfrastructureSportive id d'une InfrastructureSportive
     */
    @DeleteMapping("{id}")
    public String supprimerInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.supprimerInfrastructureSportive(idInfrastructureSportive);
    }
}
