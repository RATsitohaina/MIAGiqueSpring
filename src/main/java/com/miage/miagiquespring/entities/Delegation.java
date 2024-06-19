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
     * Id de la delegation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDelegation;

    /**
     * Nom de la delegation
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

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */
    private boolean isActif = true;

    @Override
    public String toString() {
        return "Delegation{" +
                "idDelegation=" + idDelegation +
                ", nom='" + nom + '\'' +
                ", nbMedailleOr=" + nbMedailleOr +
                ", nbMedailleArgent=" + nbMedailleArgent +
                ", nbMedailleBronze=" + nbMedailleBronze +
                '}';
    }
}
