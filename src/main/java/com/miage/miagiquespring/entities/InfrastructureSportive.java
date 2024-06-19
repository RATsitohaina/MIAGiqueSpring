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
public class InfrastructureSportive {
    /**
     * Id de l'infrastructure sportive
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInfrastructureSportive;

    /**
     * Nom de l'infrastructure sportive
     */
    private String nom;

    /**
     * Adresse de l'infrastructure sportive
     */
    private String adresse;

    /**
     * Capacité de l'infrastructure sportive
     */
    private int capacite;

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */
    private boolean isActif = true;

    @Override
    public String toString() {
        return "InfrastructureSportive{" +
                "idInfrastructureSportive=" + idInfrastructureSportive +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", capacite=" + capacite +
                '}';
    }
}
