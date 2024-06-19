package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
import lombok.*;
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

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */
    private boolean isActif = true;

    @Override
    public String toString() {
        return "Epreuve{" +
                "idEpreuve=" + idEpreuve +
                ", nomEpreuve='" + nomEpreuve + '\'' +
                ", dateEpreuve=" + dateEpreuve +
                ", nbPlacesDispo=" + nbPlacesDispo +
                ", nbPlacesInit=" + nbPlacesInit +
                ", prixBillet=" + prixBillet +
                ", infrastructureAccueil=" + infrastructureAccueil +
                '}';
    }
}
