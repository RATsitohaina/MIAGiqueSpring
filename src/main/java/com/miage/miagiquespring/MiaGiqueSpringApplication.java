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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
public class MiaGiqueSpringApplication implements CommandLineRunner {

    private final OrganisateurRepository organisateurRepository;

    /**
     * Main de l'application
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
     * @param args arguments non utilisés
     * @throws Exception en cas de problème avec la BD
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED, noRollbackFor=Exception.class)
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
        b_1.setEtat(true);
        b_1 = billetRepository.save(b_1);
        logger.info("Billet " + b_1);

        Billet b_2 = new Billet();
        /**
         * Argument mis a null de base
         * b_1.setIdEpreuve(null);
         * b_1.setIdSpectateur(null);
         */
        b_2.setPrix(500);
        b_2.setEtat(true);
        b_2 = billetRepository.save(b_2);
        logger.info("Billet " + b_2);

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

        /**
         * Epreuves
         */
        Epreuve e_1 = new Epreuve();
        e_1.setNomEpreuve("Petanque");
        e_1.setNbPlacesDispo(100);
        e_1.setDateEpreuve("Lundi");
        e_1.setInfrastructureAccueil(infra_1);
        e_1.setBillets(billets_1);
        e_1 = epreuveRepository.save(e_1);
        logger.info("Epreuve " + e_1);

        Epreuve e_2 = new Epreuve();
        e_2.setNomEpreuve("Course à pied");
        e_2.setNbPlacesDispo(100);
        e_2.setDateEpreuve("Mardi");
        e_2.setInfrastructureAccueil(infra_1);
        e_2.setBillets(billets_2);
        e_2 = epreuveRepository.save(e_2);
        logger.info("Epreuve " + e_2);

        List<Epreuve> epreuveList = new ArrayList<>();
        epreuveList.add(e_1);
        epreuveList.add(e_2);


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

        List<Billet> billets = new ArrayList<>();
        billets.add(b_1);
        billets.add(b_2);

        /**
         * Organisateur
         */
        Organisateur organisateur = new Organisateur();
        organisateur.setNom("Patron");
        organisateur.setPrenom("Be");
        organisateur.setEmail("@hotmail.com");
        organisateur.setDelegationList(delegations);
        organisateur.setParticipantList(participants);
        organisateur.setResultatList(resultats);
        organisateur.setEpreuveList(epreuveList);
        organisateur.setBilletList(billets);
        organisateur.setInfrastructureSportiveList(infra_sportives);

        organisateur = organisateurRepository.save(organisateur);
        logger.info("Organisateur " + organisateur);
    }
}
