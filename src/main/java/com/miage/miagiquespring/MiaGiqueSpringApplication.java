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
        infra_1.setNom("Infrastructure 1");
        infra_1.setAdresse("Adresse infrastructure 1");
        infra_1.setCapacite(2);
        infra_1 = infrastructureSportiveRepository.save(infra_1);
        logger.info("Infrastructure Sportive " + infra_1);

        InfrastructureSportive infra_2 = new InfrastructureSportive();
        infra_2.setNom("Infrastructure 2");
        infra_2.setAdresse("Adresse infrastructure 2");
        infra_2.setCapacite(2);
        infra_2 = infrastructureSportiveRepository.save(infra_2);
        logger.info("Infrastructure Sportive " + infra_2);

        /**
         * Epreuves
         */
        Epreuve e_1 = new Epreuve();
        Date date_e_1 = new Date();
        date_e_1.setTime(12);

        e_1.setNomEpreuve("Epreuve 1");
        e_1.setNbPlacesDispo(2);
        e_1.setNbPlacesInit(2);
        e_1.setDateEpreuve(date_e_1);
        e_1.setInfrastructureAccueil(infra_1);
        e_1.setBillets(null);
        e_1.setPrixBillet(50);
        e_1 = epreuveRepository.save(e_1);
        logger.info("Epreuve " + e_1);

        Epreuve e_2 = new Epreuve();
        Date date_e_2 = new Date();
        date_e_2.setTime(11);

        e_2.setNomEpreuve("Epreuve 2");
        e_2.setNbPlacesDispo(2);
        e_2.setNbPlacesInit(2);
        e_2.setDateEpreuve(date_e_2);
        e_2.setInfrastructureAccueil(infra_2);
        e_2.setBillets(null);
        e_2.setPrixBillet(20);
        e_2 = epreuveRepository.save(e_2);
        logger.info("Epreuve " + e_2);

        List<Epreuve> epreuveList_1 = new ArrayList<>();
        epreuveList_1.add(e_1);

        List<Epreuve> epreuveList_2 = new ArrayList<>();
        epreuveList_2.add(e_2);

        /**
         * Billets EPREUVE 1 : nbBillet 2
         */

        Date date_b_1 = new Date();
        date_b_1.setTime(10);

        Billet b_1_1 = new Billet();
        b_1_1.setIdEpreuve(e_1.getIdEpreuve());
        b_1_1.setPrix(50);
        b_1_1.setDateBillet(date_b_1);
        b_1_1 = billetRepository.save(b_1_1);
        logger.info("Billet " + b_1_1);

        Billet b_1_2 = new Billet();
        b_1_2.setIdEpreuve(e_1.getIdEpreuve());
        b_1_2.setPrix(50);
        b_1_2.setDateBillet(date_b_1);
        b_1_2 = billetRepository.save(b_1_2);
        logger.info("Billet " + b_1_2);

        /**
         * Listes des billets EPREUVE 1
         */
        List<Billet> listBillet = new ArrayList<>();
        listBillet.add(b_1_1);
        listBillet.add(b_1_2);

        e_1.setBillets(listBillet);
        epreuveRepository.save(e_1);

        /**
         * Billets EPREUVE 2 : nbBillet 2
         */

        Date date_b_2 = new Date();
        date_b_2.setTime(9);

        Billet b_2_1 = new Billet();
        b_2_1.setIdEpreuve(e_2.getIdEpreuve());
        b_2_1.setPrix(20);
        b_2_1.setDateBillet(date_b_1);
        b_2_1 = billetRepository.save(b_2_1);
        logger.info("Billet " + b_2_1);

        Billet b_2_2 = new Billet();
        b_2_2.setIdEpreuve(e_2.getIdEpreuve());
        b_2_2.setPrix(20);
        b_2_2.setDateBillet(date_b_2);
        b_2_2 = billetRepository.save(b_2_2);
        logger.info("Billet " + b_2_2);

        /**
         * Listes des billets EPREUVE 2
         */
        List<Billet> listBillet_2 = new ArrayList<>();
        listBillet.add(b_2_1);
        listBillet.add(b_2_2);

        e_2.setBillets(listBillet_2);
        epreuveRepository.save(e_2);

        /**
         * Delegation
         */
        Delegation delegation_1 = new Delegation();
        delegation_1.setNom("Delegation 1");
        delegation_1.setNbMedailleOr(1);
        delegation_1.setNbMedailleArgent(0);
        delegation_1.setNbMedailleBronze(0);
        delegation_1 = delegationRepository.save(delegation_1);
        logger.info("Delegation " + delegation_1);

        Delegation delegation_2 = new Delegation();
        delegation_2.setNom("Delegation 2");
        delegation_2.setNbMedailleOr(0);
        delegation_2.setNbMedailleArgent(1);
        delegation_2.setNbMedailleBronze(0);
        delegation_2 = delegationRepository.save(delegation_2);
        logger.info("Delegation " + delegation_2);

        /**
         * Spectateur
         */
        Spectateur sp_1 = new Spectateur();
        sp_1.setNom("Spectateur_1");
        sp_1.setPrenom("Spectateur_1");
        sp_1.setEmail("Spectateur_1@mail.com");
        sp_1.setBillets(listBillet);
        sp_1 = spectateurRepository.save(sp_1);
        logger.info("Spectateur " + sp_1);

        Spectateur sp_2 = new Spectateur();
        sp_2.setNom("Spectateur_2");
        sp_2.setPrenom("Spectateur_2");
        sp_2.setEmail("Spectateur_2@mail.com");
        sp_2.setBillets(listBillet_2);
        sp_2 = spectateurRepository.save(sp_2);
        logger.info("Spectateur " + sp_2);


        /**
         * Participant
         */
        Participant participant_1 = new Participant();
        participant_1.setNom("Participant_1");
        participant_1.setPrenom("Participant_1");
        participant_1.setEmail("Participant_1@mail.com");
        participant_1.setDelegation(delegation_1);
        participant_1.setEpreuveList(epreuveList_1);
        participant_1 = participantRepository.save(participant_1);
        logger.info("Participant " + participant_1);

        Participant participant_2 = new Participant();
        participant_2.setNom("Participant_2");
        participant_2.setPrenom("Participant_2");
        participant_2.setEmail("Participant_2@mail.com");
        participant_2.setDelegation(delegation_2);
        participant_2.setEpreuveList(epreuveList_2);
        participant_2 = participantRepository.save(participant_2);
        logger.info("Participant " + participant_2);

        /**
         * Resultat
         */
        Resultat resultat_1 = new Resultat();
        resultat_1.setIdEpreuve(e_1.getIdEpreuve());
        resultat_1.setIdParticipant(participant_1.getIdParticipant());
        resultat_1.setTemps(0);
        resultat_1.setPosition(1);
        resultat_1 = resultatRepository.save(resultat_1);
        logger.info("Resultat " + resultat_1);


        Resultat resultat_2 = new Resultat();
        resultat_2.setIdEpreuve(e_2.getIdEpreuve());
        resultat_2.setIdParticipant(participant_2.getIdParticipant());
        resultat_2.setTemps(0);
        resultat_2.setPosition(2);
        resultat_2 = resultatRepository.save(resultat_2);
        logger.info("Resultat " + resultat_2);

        /**
         * Organisateur
         */
        Organisateur organisateur_1 = new Organisateur();
        organisateur_1.setNom("Organisateur_1");
        organisateur_1.setPrenom("Organisateur_1");
        organisateur_1.setEmail("Organisateur_1@mail.com");
        organisateur_1.setRoleOrganisateur(true);
        organisateur_1 = organisateurRepository.save(organisateur_1);
        logger.info("Organisateur " + organisateur_1);

        Organisateur organisateur_2 = new Organisateur();
        organisateur_2.setNom("Organisateur_2");
        organisateur_2.setPrenom("Organisateur_2");
        organisateur_2.setEmail("Organisateur_2@mail.com");
        organisateur_2.setRoleOrganisateur(true);
        organisateur_2 = organisateurRepository.save(organisateur_2);
        logger.info("Organisateur " + organisateur_2);


        /**
         * Contrôlleur
         */
        Organisateur controlleur_1 = new Organisateur();
        controlleur_1.setNom("Controlleur_1");
        controlleur_1.setPrenom("Controlleur_1");
        controlleur_1.setEmail("Controlleur_1@mail.com");
        controlleur_1.setRoleOrganisateur(false);
        controlleur_1 = organisateurRepository.save(controlleur_1);
        logger.info("Organisateur " + controlleur_1);

        Organisateur controlleur_2 = new Organisateur();
        controlleur_2.setNom("Controlleur_2");
        controlleur_2.setPrenom("Controlleur_2");
        controlleur_2.setEmail("Controlleur_2@mail.com");
        controlleur_2.setRoleOrganisateur(false);
        controlleur_2 = organisateurRepository.save(controlleur_2);
        logger.info("Organisateur " + controlleur_2);
    }
}
