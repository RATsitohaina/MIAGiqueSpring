package com.miage.miagiquespring.entities;

import jakarta.persistence.*;
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
