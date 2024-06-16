package com.miage.miagiquespring.exposition;

import com.miage.miagiquespring.entities.Organisateur;
import com.miage.miagiquespring.entities.Participant;
import com.miage.miagiquespring.metier.ServiceParticipant;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la ressource participant
 */
@RestController
@RequestMapping("/api/participant")
public class RestServiceParticipant {

    private final ServiceParticipant serviceParticipant;

    /**
     * Constructeur pour l'injection (remplace les @Autowired)
     * @param serviceParticipant le bean métier client injecté
     */
    public RestServiceParticipant(ServiceParticipant serviceParticipant) {
        this.serviceParticipant = serviceParticipant;
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param participant les détails du client envoyés par le front
     */
    @PostMapping
    public Participant creerParticipant(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(), participant.getPrenom(), participant.getEmail(), participant.getDelegation(), participant.getResultatList(), participant.getEpreuveList());
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idParticipant id d'un Utilisateur
     */
    @GetMapping("{id}")
    public Participant getParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(idParticipant);
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     */
    @GetMapping("{prenom}/{nom}")
    public Participant getParticipantNomPrenom(@PathVariable("nom") String nomParticipant, @PathVariable("prenom") String prenomParticipant) throws Exception {
        return serviceParticipant.recupererParticipant(prenomParticipant, nomParticipant);
    }

    /**
     * Permet de récupérer tout les détails d'un Utilisateur
     */
    @GetMapping()
    public Iterable<Participant> getAllParticipant() throws Exception {
        return serviceParticipant.recupererAllParticipant();
    }

    /**
     * Permet de récupérer les détails d'un Utilisateur
     * @param idParticipant id d'un Utilisateur
     */
    @DeleteMapping("{id}")
    public String supprimerParticipant(@PathVariable("id") long idParticipant) throws Exception {
        return serviceParticipant.supprimerParticipant(idParticipant);
    }

    /**
     * Permet de créer un nouveau spectateur
     * @param participant les détails du client envoyés par le front
     */
    @PostMapping("/null")
    public Participant creerParticipantNull(@RequestBody Participant participant) {
        return serviceParticipant.creerParticipant(participant.getNom(), participant.getPrenom(), participant.getEmail(), null, null, null);
    }
}
