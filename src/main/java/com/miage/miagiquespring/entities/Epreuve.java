package com.miage.miagiquespring.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

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
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEpreuve;

    /**
     * Nom de l'épreuve
     */
    private String nomEpreuve;

    /**
     * Date de dernière interrogation du compte
     */
   private String dateEpreuve;

    /**
     * Nombre de places mises en vente
     */
    private int nbPlacesDispo;

    /**
     * Nombre de places initiales (pour
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
