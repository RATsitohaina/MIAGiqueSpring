package com.miage.miagiquespring.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor

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
    @NotNull
    private String nomEpreuve;

    /**
     * Date de dernière interrogation du compte
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEpreuve;

    /**
     * Référence vers l'infrastructureSportive d'accueil
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference // pour éviter les cycles lors de la transformation en JSON
    private InfrastructureSportive infrastructureAccueil;

    /**
     * Nombre de places mises en vente
     */
    private int nbPlacesDispo;

    /**
     * Méthode pour afficher l'épreuve
     * @return une représentation textuelle
     */
    @Override
    public String toString() {
        // attention aux cycles
        // Ici on choisit de ne pas afficher ni le client ni la liste d'opérations
        return "Epreuve{" +
                "id=" + idEpreuve +
                ", nom=" + nomEpreuve +
                ", infrastructure d'accueil=" + infrastructureAccueil +
                ", date=" + dateEpreuve +
                ", nombre de places=" + nbPlacesDispo +
                '}';
    }
}