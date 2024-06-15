package com.miage.miagiquespring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delegation {
    /**
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInfrastructureSportive;

    /**
     * Nom de l'infrastructure sportive
     */
    private String nom;

    /**
     * Nombre de medailles d'or
     */
    private int nbMedailleOr;

    /**
     * Nombre de medailles d'argrent
     */
    private int nbMedailleArgent;

    /**
     * Nombre de medailles de bronze
     */
    private int nbMedailleBronze;
}