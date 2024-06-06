package com.miage.miagiquespring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class InfrastructureSportive {
    /**
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nom de l'infrastructure sportive
     */
    @NotNull
    private String nom;

    /**
     * Adresse de l'infrastructure sportive
     */
    @NotNull
    private String adresse;

    /**
     * Capacité de l'infrastructure sportive
     */
    @NotNull
    private int capacite;

    /**
     * Méthode pour afficher une Infrastructure_sportive
     * @return une représentation textuelle de l'Infrastructure_sportive
     */
    @Override
    public String toString() {
        return "InfrastructureSportive{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", capacite=" + capacite + '\'' +
                '}';
    }
}
