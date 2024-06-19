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
public class Participant {
    /**
     * Id du participant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idParticipant;

    /**
     * Nom du participant
     */
    protected String nom;

    /**
     * Prenom du participant
     */
    protected String prenom;

    /**
     * email du participant
     */
    protected String email;

    /**
     * Delegation du participant
     */
    @ManyToOne
    private Delegation delegation;


    /**
     * Epreuve du participant
     */
    @OneToMany
    private List<Epreuve> epreuveList;

    /**
     * Pour s'il est encore actif
     * true : actif
     * false : nonactif
     * @return
     */
    private boolean isActif = true;

    @Override
    public String toString() {
        return "Participant{" +
                "idParticipant=" + idParticipant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", delegation=" + delegation +
                '}';
    }
}