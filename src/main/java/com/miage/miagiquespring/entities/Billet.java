package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Billet {
    /**
     * Id du billet
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
     * Disponibiliter du billet
     * True : disponible
     * False : reserver
     */
    private Boolean disponible = true;

    /**
     * Date de réservation
     */
    private Date dateBillet;

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */
    private boolean isActif = true;

    @Override
    public String toString() {
        return "Billet{" +
                "idBillet=" + idBillet +
                ", idEpreuve=" + idEpreuve +
                ", idSpectateur=" + idSpectateur +
                ", prix=" + prix +
                ", disponible=" + disponible +
                ", dateBillet=" + dateBillet +
                '}';
    }
}
