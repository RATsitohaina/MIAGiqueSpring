package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Organisateur {
    /**
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idOrganisateur;

    /**
     * Nom de l'Organisateur
     */
    protected String nom;

    /**
     * Prenom de l'Organisateur
     */
    protected String prenom;

    /**
     * email de l'Organisateur
     */
    protected String email;

    /**
     * Liste délégation
     */
    @OneToMany
    private List<Delegation> delegationList;

    /**
     * Liste des participants
     */
    @OneToMany
    private List<Participant> participantList;

    /**
     * Liste des resultats
     */
    @OneToMany
    private List<Resultat> resultatList;

    /**
     * Liste des épreuves
     */
    @OneToMany
    private List<Epreuve> epreuveList;

    /**
     * Liste des billets
     */
    @OneToMany
    private List<Billet> billetList;

    /**
     * Liste des infrastructure sportives
     */
    @OneToMany
    private List<InfrastructureSportive> infrastructureSportiveList;
}
