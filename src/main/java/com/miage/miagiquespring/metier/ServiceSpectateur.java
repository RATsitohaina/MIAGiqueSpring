package com.miage.miagiquespring.metier;

import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.Spectateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSpectateur {

    private final SpectateurRepository spectateurRepository;

    public ServiceSpectateur(SpectateurRepository spectateurRepository) {
        this.spectateurRepository = spectateurRepository;
    }

    /**
     * Demande la création d'un nouveau Spectateur
     * @param prenom prénom du Spectateur
     * @param nom nom du Spectateur
     * @return le nouveau Spectateur ou l'ancien Spectateur
     */
    public Spectateur creerSpectateur(String nom, String prenom, String email, List<Billet> billetList) {
        //Opération métier
        //On cherche si le client est déjà présent
        List<Spectateur> spectateurs = spectateurRepository.findByPrenomAndNom(prenom, nom);
        Spectateur spectateur;
        // s'il n'est pas présent
        if (spectateurs.isEmpty()) {
            // on le crée
            spectateur = new Spectateur();
            spectateur.setPrenom(prenom);
            spectateur.setNom(nom);
            spectateur.setEmail(email);
            spectateur.setBillets(billetList);

            // on l'ajoute à la BD
            spectateur = spectateurRepository.save(spectateur);
        } else {
            // sinon, on retournera l'ancien
            spectateur = spectateurs.get(0);
        }
        // retourne le client
        return spectateur;
    }

    /**
     * Permet de récupérer les infos d'un Spectateur
     * @param idSpectateur du Spectateur
     * @return infos du Spectateur
     */
    public Spectateur recupererSpectateur(long idSpectateur) throws Exception {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if(optionalSpectateur.isEmpty())
            throw new Exception("Spectateur inexistant");
        // sinon, on renvoie les infos
        return optionalSpectateur.get();
    }

    /**
     * Permet de récupérer les infos d'un Spectateur
     * @param idSpectateur id du Spectateur
     */
    public String supprimerSpectateur(long idSpectateur) throws Exception {
        // on cherche le client
        final Optional<Spectateur> optionalSpectateur = spectateurRepository.findById(idSpectateur);
        // s'il n'existe pas on lance une exception
        if(optionalSpectateur.isEmpty()){
            throw new Exception("Spectateur inexistant");
        }
        spectateurRepository.delete(optionalSpectateur.get());
        return "Spectateur :"+idSpectateur+" removed";
    }
}
