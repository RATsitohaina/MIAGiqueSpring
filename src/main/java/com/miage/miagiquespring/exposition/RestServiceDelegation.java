package com.miage.miagiquespring.exposition;


import com.miage.miagiquespring.entities.Delegation;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.metier.ServiceDelegation;
import com.miage.miagiquespring.metier.ServiceInfrastructureSportive;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource Delegation
 */
@RestController
@RequestMapping("/api/delegation")
public class RestServiceDelegation {

    private final ServiceDelegation serviceDelegation;
    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceDelegation le bean métier client injecté
     */
    public RestServiceDelegation(ServiceDelegation serviceDelegation) {
        this.serviceDelegation = serviceDelegation;
    }

    /**
     * Permet de créer un nouveau client
     * @param delegation les détails du client envoyés par le front
     */
    @PostMapping
    public Delegation creerDelegation(@RequestBody Delegation delegation) {
        return serviceDelegation.creerDelegation(delegation.getNom(), delegation.getNbMedailleOr(), delegation.getNbMedailleArgent(), delegation.getNbMedailleBronze());
    }

    /**
     * Permet de récupérer les détails d'une Delegation
     * @param idDelegation id d'une Delegation
     */
    @GetMapping("{id}")
    public Delegation getDelegation(@PathVariable("id") Long idDelegation) throws Exception {
        return serviceDelegation.recupererDelegation(idDelegation);
    }

    /**
     * Permet de supprimer les détails d'une InfrastructureSportive
     * @param idDelegation id d'une InfrastructureSportive
     */
    @DeleteMapping("{id}")
    public String supprimerDelegation(@PathVariable("id") Long idDelegation) throws Exception {
        return serviceDelegation.supprimerDelegation(idDelegation);
    }
}
