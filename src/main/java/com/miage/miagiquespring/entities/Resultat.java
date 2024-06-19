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
public class Resultat {
    /**
     * Id du resultat
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResultat;

    /**
     * Id de l'epreuve
     */
    private Long idEpreuve;

    /**
     * Id du participant
     */
    private Long idParticipant;

    /**
     * Temps enregistrer pour le resultat
     */
    private float temps;

    /**
     * Position du participant
     */
    private int position;

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */

    private boolean isActif = true;

    @Override
    public String toString() {
        return "Resultat{" +
                "idResultat=" + idResultat +
                ", idEpreuve=" + idEpreuve +
                ", idParticipant=" + idParticipant +
                ", temps=" + temps +
                ", position=" + position +
                '}';
    }
}
