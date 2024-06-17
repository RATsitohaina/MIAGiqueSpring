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
public class Spectateur {
    /**
     * Id du spectateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idSpectateur;

    /**
     * Nom du spectateur
     */
    protected String nom;

    /**
     * Prenom du spectateur
     */
    protected String prenom;

    /**
     * email du spectateur
     */
    protected String email;

    /**
     * Liste des billets du spectateur
     */
    @OneToMany
    private List<Billet> billets;
}
