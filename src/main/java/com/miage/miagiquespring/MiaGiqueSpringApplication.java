package com.miage.miagiquespring;

import com.miage.miagiquespring.dao.BilletRepository;
import com.miage.miagiquespring.dao.EpreuveRepository;
import com.miage.miagiquespring.dao.InfrastructureSportiveRepository;
import com.miage.miagiquespring.dao.SpectateurRepository;
import com.miage.miagiquespring.entities.Billet;
import com.miage.miagiquespring.entities.Epreuve;
import com.miage.miagiquespring.entities.InfrastructureSportive;
import com.miage.miagiquespring.entities.Spectateur;
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
    /**
     * repo pour tests
     */
    private final EpreuveRepository epreuveRepository;
    /**
     * repo pour tests
     */
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;
    /**
     * repo pour tests
     */
    private final SpectateurRepository spectateurRepository;

    public MiaGiqueSpringApplication(BilletRepository billetRepository,
                                     EpreuveRepository epreuveRepository,
                                     InfrastructureSportiveRepository infrastructureSportiveRepository,
                                     SpectateurRepository spectateurRepository) {
        this.billetRepository = billetRepository;
        this.epreuveRepository = epreuveRepository;
        this.infrastructureSportiveRepository = infrastructureSportiveRepository;
        this.spectateurRepository = spectateurRepository;
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
        List<Epreuve> epreuves = epreuveRepository.findByNomEpreuve("Petanque");

        if (epreuves.isEmpty()) {
            /**
             * InfrastructureSportive
             */
            InfrastructureSportive infra_1 = new InfrastructureSportive();
            infra_1.setNom("Mahamasina");
            infra_1.setAdresse("Analakely");
            infra_1.setCapacite(200);
            infra_1 = infrastructureSportiveRepository.save(infra_1);
            logger.info("InfrastructureSportive " + infra_1);

            InfrastructureSportive infra_2 = new InfrastructureSportive();
            infra_2.setNom("Mahamasina");
            infra_2.setAdresse("Analakely");
            infra_2.setCapacite(200);
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

            /**
             * Spectateur
             */
            Spectateur sp = new Spectateur();
            sp.setNom("Razanajatovo");
            sp.setPrenom("Andrianaivo Tsitohaina");
            sp.setEmail("@hotmail.com");
            sp.setBillets(billets_3);
            sp = spectateurRepository.save(sp);
            logger.info("Spectateur " + sp);

        }
    }
}
