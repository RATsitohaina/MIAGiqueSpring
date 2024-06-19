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

    }
}
