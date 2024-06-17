package com.miage.miagiquespring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
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
    private Date dateEpreuve;

    /**
     * Nombre de places mises en vente
     */
    private int nbPlacesDispo;

    /**
     * Nombre de places initiales de l'epreuve
     */
    private int nbPlacesInit;

    /**
     * Prix des billets de l'épreuve
     */
    private int prixBillet;

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
