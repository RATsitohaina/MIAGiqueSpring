package com.miage.miagiquespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Spectateur{
    /**
     * Id de l'entité
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idSpectateur;

    /**
     * Nom de l'Utilisateur
     */
    protected String nom;

    /**
     * Prenom de l'Utilisateur
     */
    protected String prenom;

    /**
     * email de l'Utilisateur
     */
    protected String email;

    /**
     * Liste des billets du specatateur
     */
    @OneToMany
    private List<Billet> billets;
}
