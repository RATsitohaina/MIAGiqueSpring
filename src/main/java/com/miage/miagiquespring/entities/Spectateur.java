package com.miage.miagiquespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
    @NotNull
    protected String nom;

    /**
     * Prenom de l'Utilisateur
     */
    @NotNull
    protected String prenom;

    /**
     * email de l'Utilisateur
     */
    @NotNull
    protected String email;

    /**
     * Liste des épreuves auxquelles le spectateur assiste
     */

    @ManyToMany
    @JoinTable(
            name = "spectateur_epreuve",
            joinColumns = @JoinColumn(name = "idSpectateur"),
            inverseJoinColumns = @JoinColumn(name = "idEpreuve")
    )
    @JsonIgnore
    private List<Epreuve> epreuves;

    /**
     * Méthode pour afficher un spéctateur
     * @return une représentation textuelle du spéctateur
     */
    @Override
    public String toString() {
        return "Spectateur{" +
                "id=" + idSpectateur +
                ", nom=" + nom + '\'' +
                ", prenom=" + prenom + '\'' +
                ", email=" + email + '\'' +
                ", epreuves=" + epreuves +
                '}';
    }
}
