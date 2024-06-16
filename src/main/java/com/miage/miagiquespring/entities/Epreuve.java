package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Epreuve {
    /**
     * Id de l'epreuve
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEpreuve;

    /**
     * Nom de l'épreuve
     */
    private String nomEpreuve;

    /**
     * Date de l'epreuve
     */

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEpreuve;


    /**
     * Nombre de places mises en vente
     */
    private int nbPlacesDispo;

    /**
     * Nombre de places initiales de l'epreuve
     */
    private int nbPlacesInit;

    /**
     * Liste des billets disponibles pour l'epreuve
     */
    @OneToMany
    private List<Billet> billets;

    /**
     * Référence vers l'infrastructureSportive d'accueil
     */
    @ManyToOne
    private InfrastructureSportive infrastructureAccueil;
}
