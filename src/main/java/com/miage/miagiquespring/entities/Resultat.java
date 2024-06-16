package com.miage.miagiquespring.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Null;
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
}
