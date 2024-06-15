package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Billet {
    /**
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBillet;

    /**
     * Id de l'épreuve
     * Qui va premettre de vérifier l'existance de l'epreuve
     */
    private Long idEpreuve;

    /**
     * Id du spectateur
     * Qui va permettre de vérifier le spectateur auquel le billet est assigné
     */
    private Long idSpectateur;

    /**
     * Prix du Billet
     */
    private int prix;

    /**
     * Etat du Billet
     */
    private Boolean etat;
}
