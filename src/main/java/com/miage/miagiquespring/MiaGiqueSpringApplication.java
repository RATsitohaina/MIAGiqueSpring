package com.miage.miagiquespring;

import com.miage.miagiquespring.dao.*;
import com.miage.miagiquespring.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication
public class MiaGiqueSpringApplication implements CommandLineRunner {

    private final OrganisateurRepository organisateurRepository;

    /**
     * Main de l'application
     *
     * @param args arguments pour Spring
     */
    public static void main(String[] args) {
        SpringApplication.run(MiaGiqueSpringApplication.class, args);
    }

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(MiaGiqueSpringApplication.class);

    /**
     * repo pour tests
     */
    private final BilletRepository billetRepository;
    private final EpreuveRepository epreuveRepository;
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;
    private final SpectateurRepository spectateurRepository;
    private final DelegationRepository delegationRepository;
    private final ResultatRepository resultatRepository;
    private final ParticipantRepository participantRepository;

    public MiaGiqueSpringApplication(BilletRepository billetRepository,
                                     EpreuveRepository epreuveRepository,
                                     InfrastructureSportiveRepository infrastructureSportiveRepository,
                                     SpectateurRepository spectateurRepository, DelegationRepository delegationRepository, ResultatRepository resultatRepository, ParticipantRepository participantRepository, OrganisateurRepository organisateurRepository) {
        this.billetRepository = billetRepository;
        this.epreuveRepository = epreuveRepository;
        this.infrastructureSportiveRepository = infrastructureSportiveRepository;
        this.spectateurRepository = spectateurRepository;
        this.delegationRepository = delegationRepository;
        this.resultatRepository = resultatRepository;
        this.participantRepository = participantRepository;
        this.organisateurRepository = organisateurRepository;
    }

    /**
     * Méthode pour tester les répositories
     * On a besoin du Transactionnal pour que les listes de comptes remontent avec les clients
     *
     * @param args arguments non utilisés
     * @throws Exception en cas de problème avec la BD
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void run(String... args) throws Exception {

        /**
         * InfrastructureSportive
         */
        InfrastructureSportive infra_1 = new InfrastructureSportive();
        infra_1.setNom("Complexe Mahamasina");
        infra_1.setAdresse("Analakely");
        infra_1.setCapacite(200);
        infra_1 = infrastructureSportiveRepository.save(infra_1);
        logger.info("InfrastructureSportive " + infra_1);

        InfrastructureSportive infra_2 = new InfrastructureSportive();
        infra_2.setNom("Palais des sports");
        infra_2.setAdresse("Ankorondrano");
        infra_2.setCapacite(400);
        infra_2 = infrastructureSportiveRepository.save(infra_2);
        logger.info("InfrastructureSportive " + infra_2);

        /**
         * Billets
         */
        Billet b_1 = new Billet();
        /**
         * Argument mis a null de base
         * b_1.setIdEpreuve(null);
         * b_1.setIdSpectateur(null);
         */
        b_1.setPrix(300);
        Date date_30 = new Date();
        b_1.setDateBillet(date_30);
        b_1 = billetRepository.save(b_1);
        logger.info("Billet " + b_1);

        Billet b_2 = new Billet();
        /**
         * Argument mis a null de base
         * b_1.setIdEpreuve(null);
         * b_1.setIdSpectateur(null);
         */
        b_2.setPrix(500);
        b_2.setDateBillet(date_30);
        b_2 = billetRepository.save(b_2);
        logger.info("Billet " + b_2);

        Billet b_3 = new Billet();
        /**
         * Argument mis a null de base
         * b_1.setIdEpreuve(null);
         * b_1.setIdSpectateur(null);
         */
        b_3.setPrix(800);
        b_3.setDisponible(true);
        b_3 = billetRepository.save(b_3);
        logger.info("Billet " + b_3);


        /**
         * Listes des billets
         */
        List<Billet> billets_1 = new ArrayList<>();
        billets_1.add(b_1);

        List<Billet> billets_2 = new ArrayList<>();
        billets_2.add(b_2);

        List<Billet> billets_3 = new ArrayList<>();
        billets_3.add(b_1);
        billets_3.add(b_2);

        List<Billet> billets_4 = new ArrayList<>();
        billets_4.add(b_3);

        /**
         * Epreuves
         */
        Epreuve e = new Epreuve();
        Date date_12 = new Date();
        date_12.setTime(12);

        e.setNomEpreuve("Course à pied");
        e.setNbPlacesDispo(100);
        e.setNbPlacesInit(100);
        e.setDateEpreuve(date_12);
        e.setInfrastructureAccueil(infra_1);
        e.setBillets(billets_1);
        e.setPrixBillet(20);
        e = epreuveRepository.save(e);
        logger.info("Epreuve " + e);

        Epreuve e_2 = new Epreuve();
        e_2.setNomEpreuve("Petanque");
        e_2.setNbPlacesDispo(50);
        e_2.setNbPlacesInit(50);
        e_2.setDateEpreuve(date_12);
        e_2.setInfrastructureAccueil(infra_2);
        e_2.setBillets(billets_2);
        e_2.setPrixBillet(60);
        e_2 = epreuveRepository.save(e_2);
        logger.info("Epreuve " + e_2);

        List<Epreuve> epreuveList = new ArrayList<>();
        epreuveList.add(e_2);
        epreuveList.add(e);

        /**
         * Delegation
         */
        Delegation delegation = new Delegation();
        delegation.setNom("Gaspaname");
        delegation.setNbMedailleOr(0);
        delegation.setNbMedailleArgent(0);
        delegation.setNbMedailleBronze(0);
        delegation = delegationRepository.save(delegation);
        logger.info("Delegation " + delegation);

        /**
         * Resultat
         */
        Resultat resultat = new Resultat();
        resultat.setIdEpreuve(null);
        resultat.setIdParticipant(null);
        resultat.setTemps(0);
        resultat.setPosition(0);
        resultat = resultatRepository.save(resultat);
        logger.info("Resultat " + resultat);

        List<Resultat> resultats = new ArrayList<>();
        resultats.add(resultat);

        /**
         * Spectateur
         */
        Spectateur sp = new Spectateur();
        sp.setNom("Razanajatovo");
        sp.setPrenom("Andrianaivo Tsitohaina");
        sp.setEmail("@hotmail.com");
        sp.setBillets(billets_3);
        sp.setResultats(resultats);
        sp = spectateurRepository.save(sp);
        logger.info("Spectateur " + sp);

        /**
         * Participant
         */
        Participant participant = new Participant();
        participant.setNom("Zandry");
        participant.setPrenom("kely");
        participant.setEmail("@hotmail.com");
        participant.setDelegation(delegation);
        participant.setResultatList(resultats);
        participant.setEpreuveList(epreuveList);
        participant = participantRepository.save(participant);
        logger.info("Participant " + participant);

        List<Delegation> delegations = new ArrayList<>();
        delegations.add(delegation);

        List<Participant> participants = new ArrayList<>();
        participants.add(participant);

        List<InfrastructureSportive> infra_sportives = new ArrayList<>();
        infra_sportives.add(infra_1);
        infra_sportives.add(infra_2);


        /**
         * Organisateur
         */
        Organisateur organisateur = new Organisateur();
        organisateur.setNom("RAZANAJATOVO");
        organisateur.setPrenom("Tsitohaina");
        organisateur.setEmail("@hotmail.com");
        organisateur.setRoleOrganisateur(true);
        organisateur = organisateurRepository.save(organisateur);
        logger.info("Organisateur " + organisateur);

        /**
         * Contrôlleur
         */
        Organisateur controlleur = new Organisateur();
        controlleur.setNom("RAMBELO");
        controlleur.setPrenom("Iaro");
        controlleur.setEmail("@gmail.com");
        controlleur.setRoleOrganisateur(false);
        controlleur = organisateurRepository.save(controlleur);
        logger.info("Controlleur " + controlleur);
    }
}
