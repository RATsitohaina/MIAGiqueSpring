package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Organisateur {
    /**
     * Id de l'organisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idOrganisateur;

    /**
     * Nom de l'organisateur
     */
    protected String nom;

    /**
     * Prenom de l'organisateur
     */
    protected String prenom;

    /**
     * email de l'organisateur
     */
    protected String email;

    /**
     * Rôle de l'organisateur
     * True si organisateur, false si controlleur
     */
    private Boolean roleOrganisateur = true;
}
