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
     * Permet de créer un nouveau infrastructuresportive
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
    @GetMapping("id/{id}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("id") Long idInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(idInfrastructureSportive);
    }

    /**
     * Permet de récupérer les détails d'une InfrastructureSportive
     * @param nomInfrastructureSportive d'une InfrastructureSportive
     */
    @GetMapping("nom/{nom}")
    public InfrastructureSportive getInfrastructureSportive(@PathVariable("nom") String nomInfrastructureSportive) throws Exception {
        return serviceInfrastructureSportive.recupererInfrastructureSportive(nomInfrastructureSportive);
    }

    /**
     * Permet de récupérer tout les détails d'une InfrastructureSportive
     */
    @GetMapping("all")
    public Iterable<InfrastructureSportive> getAllInfrastructureSportive() throws Exception {
        return serviceInfrastructureSportive.recupererAllInfrastructureSportive();
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
