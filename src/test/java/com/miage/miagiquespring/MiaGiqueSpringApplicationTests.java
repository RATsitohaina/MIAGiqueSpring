package com.miage.miagiquespring;

import com.miage.miagiquespring.dao.*;
import com.miage.miagiquespring.entities.*;
import com.miage.miagiquespring.metier.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MiaGiqueSpringApplicationTests {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(MiaGiqueSpringApplication.class);

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BilletRepository billetRepository;

    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private InfrastructureSportiveRepository infrastructureSportiveRepository;

    @Autowired
    private OrganisateurRepository organisateurRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private SpectateurRepository spectateurRepository;

    @Autowired
    private ServiceBillet svcBillet;

    @Autowired
    private ServiceDelegation svcDelegation;

    @Autowired
    private ServiceEpreuve svcEpreuve;

    @Autowired
    private ServiceInfrastructureSportive svcInfrastructureSportive;

    @Autowired
    private ServiceOrganisateur svcOrganisateur;

    @Autowired
    private ServiceParticipant svcParticipant;

    @Autowired
    private ServiceResultat svcResultat;

    @Autowired
    private ServiceSpectateur svcSpectateur;

    private Organisateur organisateur_1;
    private Organisateur organisateur_2;
    private Organisateur controleur_1;
    private Organisateur controleur_2;
    private Delegation delegation_1;
    private Delegation delegation_2;
    private Participant participant_1;
    private Participant participant_2;
    private InfrastructureSportive infra_1;
    private InfrastructureSportive infra_2;
    private Epreuve e_1;
    private Epreuve e_2;
    private Resultat resultat_1;
    private Resultat resultat_2;
    private Spectateur sp_1;
    private Spectateur sp_2;
    private Billet b_1_1;
    private Billet b_1_2;
    private Billet b_2_1;
    private Billet b_2_2;


    @BeforeEach
    public void setUp(){

        /**
         * InfrastructureSportive
         */
        infra_1 = new InfrastructureSportive();
        infra_1.setNom("Infrastructure 1");
        infra_1.setAdresse("Adresse infrastructure 1");
        infra_1.setCapacite(2);
        infra_1 = infrastructureSportiveRepository.save(infra_1);
        logger.info("Infrastructure Sportive " + infra_1);

        infra_2 = new InfrastructureSportive();
        infra_2.setNom("Infrastructure 2");
        infra_2.setAdresse("Adresse infrastructure 2");
        infra_2.setCapacite(2);
        infra_2 = infrastructureSportiveRepository.save(infra_2);
        logger.info("Infrastructure Sportive " + infra_2);

        /**
         * Epreuves
         */
        e_1 = new Epreuve();
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

        e_2 = new Epreuve();
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

        b_1_1 = new Billet();
        b_1_1.setIdEpreuve(e_1.getIdEpreuve());
        b_1_1.setPrix(50);
        b_1_1.setDateBillet(date_b_1);
        b_1_1 = billetRepository.save(b_1_1);
        logger.info("Billet " + b_1_1);

        b_1_2 = new Billet();
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

        b_2_1 = new Billet();
        b_2_1.setIdEpreuve(e_2.getIdEpreuve());
        b_2_1.setPrix(20);
        b_2_1.setDateBillet(date_b_1);
        b_2_1 = billetRepository.save(b_2_1);
        logger.info("Billet " + b_2_1);

        b_2_2 = new Billet();
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
        delegation_1 = new Delegation();
        delegation_1.setNom("Delegation 1");
        delegation_1.setNbMedailleOr(1);
        delegation_1.setNbMedailleArgent(0);
        delegation_1.setNbMedailleBronze(0);
        delegation_1 = delegationRepository.save(delegation_1);
        logger.info("Delegation " + delegation_1);

        delegation_2 = new Delegation();
        delegation_2.setNom("Delegation 2");
        delegation_2.setNbMedailleOr(0);
        delegation_2.setNbMedailleArgent(1);
        delegation_2.setNbMedailleBronze(0);
        delegation_2 = delegationRepository.save(delegation_2);
        logger.info("Delegation " + delegation_2);

        /**
         * Spectateur
         */
        sp_1 = new Spectateur();
        sp_1.setNom("Spectateur_1");
        sp_1.setPrenom("Spectateur_1");
        sp_1.setEmail("Spectateur_1@mail.com");
        sp_1.setBillets(listBillet);
        sp_1 = spectateurRepository.save(sp_1);
        logger.info("Spectateur " + sp_1);

        sp_2 = new Spectateur();
        sp_2.setNom("Spectateur_2");
        sp_2.setPrenom("Spectateur_2");
        sp_2.setEmail("Spectateur_2@mail.com");
        sp_2.setBillets(listBillet_2);
        sp_2 = spectateurRepository.save(sp_2);
        logger.info("Spectateur " + sp_2);


        /**
         * Participant
         */
        participant_1 = new Participant();
        participant_1.setNom("Participant_1");
        participant_1.setPrenom("Participant_1");
        participant_1.setEmail("Participant_1@mail.com");
        participant_1.setDelegation(delegation_1);
        participant_1.setEpreuveList(epreuveList_1);
        participant_1 = participantRepository.save(participant_1);
        logger.info("Participant " + participant_1);

        participant_2 = new Participant();
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
        resultat_1 = new Resultat();
        resultat_1.setIdEpreuve(e_1.getIdEpreuve());
        resultat_1.setIdParticipant(participant_1.getIdParticipant());
        resultat_1.setTemps(0);
        resultat_1.setPosition(1);
        resultat_1 = resultatRepository.save(resultat_1);
        logger.info("Resultat " + resultat_1);


        resultat_2 = new Resultat();
        resultat_2.setIdEpreuve(e_2.getIdEpreuve());
        resultat_2.setIdParticipant(participant_2.getIdParticipant());
        resultat_2.setTemps(0);
        resultat_2.setPosition(2);
        resultat_2 = resultatRepository.save(resultat_2);
        logger.info("Resultat " + resultat_2);

        /**
         * Organisateur
         */
        organisateur_1 = new Organisateur();
        organisateur_1.setNom("Organisateur_1");
        organisateur_1.setPrenom("Organisateur_1");
        organisateur_1.setEmail("Organisateur_1@mail.com");
        organisateur_1.setRoleOrganisateur(true);
        organisateur_1 = organisateurRepository.save(organisateur_1);
        logger.info("Organisateur " + organisateur_1);

        organisateur_2 = new Organisateur();
        organisateur_2.setNom("Organisateur_2");
        organisateur_2.setPrenom("Organisateur_2");
        organisateur_2.setEmail("Organisateur_2@mail.com");
        organisateur_2.setRoleOrganisateur(true);
        organisateur_2 = organisateurRepository.save(organisateur_2);
        logger.info("Organisateur " + organisateur_2);


        /**
         * Contrôlleur
         */
        controleur_1 = new Organisateur();
        controleur_1.setNom("Controleur_1");
        controleur_1.setPrenom("Controleur_1");
        controleur_1.setEmail("Controleur_1@mail.com");
        controleur_1.setRoleOrganisateur(false);
        controleur_1 = organisateurRepository.save(controleur_1);
        logger.info("Organisateur " + controleur_2);

        controleur_2 = new Organisateur();
        controleur_2.setNom("Controleur_2");
        controleur_2.setPrenom("Controleur_2");
        controleur_2.setEmail("Controleur_2@mail.com");
        controleur_2.setRoleOrganisateur(false);
        controleur_2 = organisateurRepository.save(controleur_2);
        logger.info("Organisateur " + controleur_2);
    }

    /** REQUETES SERVICE ORGANISATEUR **/
    @Test
    public void testCreerOrganisateurControleur() throws Exception {
        mvc.perform(post("/api/organisateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"RAZANAJATOVO\", " +
                                "\"prenom\": \"Tsitohaina\", " +
                                "\"email\": \"exemple@exemple.com\", " +
                                "\"roleOrganisateur\": true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("RAZANAJATOVO")))
                .andExpect(jsonPath("$.prenom", is("Tsitohaina")))
                .andExpect(jsonPath("$.email", is("exemple@exemple.com")))
                .andExpect(jsonPath("$.roleOrganisateur", is(true)));

        mvc.perform(post("/api/organisateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"RAMBELO\", " +
                                "\"prenom\": \"Iaro\", " +
                                "\"email\": \"exemple@exemple.com\", " +
                                "\"roleOrganisateur\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("RAMBELO")))
                .andExpect(jsonPath("$.prenom", is("Iaro")))
                .andExpect(jsonPath("$.email", is("exemple@exemple.com")))
                .andExpect(jsonPath("$.roleOrganisateur", is(false)));
    }


    @Test
    void testGetOrganisateurControleur() throws Exception {
        mvc.perform(get("/api/organisateur/id/{id}", organisateur_1.getIdOrganisateur())
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is(organisateur_1.getNom())))
                .andExpect(jsonPath("$.roleOrganisateur", is(true)));

        mvc.perform(get("/api/organisateur/id/{id}", controleur_1.getIdOrganisateur())
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is(controleur_1.getNom())))
                .andExpect(jsonPath("$.roleOrganisateur", is(false)));

        mvc.perform(get("/api/organisateur/prenomNom/{prenom}/{nom}",organisateur_1.getPrenom(),organisateur_1.getNom()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is(organisateur_1.getNom())))
                .andExpect(jsonPath("$.prenom", is(organisateur_1.getPrenom())))
                .andExpect(jsonPath("$.email", is(organisateur_1.getEmail())))
                .andExpect(jsonPath("$.roleOrganisateur", is(organisateur_1.getRoleOrganisateur())));

        mvc.perform(get("/api/organisateur/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerOrganisateur() throws Exception {
        mvc.perform(delete("/api/organisateur/delete/id/{id}",organisateur_1.getIdOrganisateur()))
                .andExpect(status().isOk())
                .andExpect(content().string("Organisateur :"+ organisateur_1.getIdOrganisateur() +" removed"));

    }

    @Test
    public void testGetStatistique() throws Exception {
        mvc.perform(get("/api/organisateur/statistique/{prenom}/{nom}",organisateur_1.getPrenom(),organisateur_1.getNom()))
                .andExpect(status().isOk());
    }


    /**
     * MODIFIER CREATION POUR AVOIR NULL SUR LES OBJETS
     * @throws Exception
     */
    @Test
    public void testCreerResultat() throws Exception {
        mvc.perform(post("/api/organisateur/resultat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"idEpreuve\": 1,\n" +
                                "\"idParticipant\": 1,\n" +
                                "\"temps\": 0,\n" +
                                "\"position\": 3\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetResultat() throws Exception {
        mvc.perform(get("/api/organisateur/resultat/id/{id}", resultat_1.getIdResultat())
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/resultat/all"))
                .andExpect(status().isOk());
    }


    @Test
    public void testSupprimerResultat() throws Exception {

        mvc.perform(delete("/api/organisateur/resultat/delete/{id}",resultat_1.getIdResultat()))
                .andExpect(status().isOk())
                .andExpect(content().string("Resultat :"+ resultat_1.getIdResultat() +" removed"));

    }

    @Test
    public void testGetClassement() throws Exception {
        mvc.perform(get("/api/organisateur/classement"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreerDelegation() throws Exception {
        mvc.perform(post("/api/organisateur/delegation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"DelegationTEST\", " +
                                "\"nbMedailleOr\": \"0\", " +
                                "\"nbMedailleArgent\": \"0\", " +
                                "\"nbMedailleBronze\": \"0\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("DelegationTEST")))
                .andExpect(jsonPath("$.nbMedailleOr", is(0)))
                .andExpect(jsonPath("$.nbMedailleArgent", is(0)))
                .andExpect(jsonPath("$.nbMedailleBronze", is(0)));
    }

    @Test
    public void testGetDelegation() throws Exception {
        mvc.perform(get("/api/organisateur/delegation/id/{id}", delegation_1.getIdDelegation()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/delegation/nom/{nom}", delegation_1.getNom()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/delegation/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerDelegation() throws Exception {
        mvc.perform(delete("/api/organisateur/delegation/delete/{id}",delegation_1.getIdDelegation()))
                .andExpect(status().isOk())
                .andExpect(content().string("Delegation :"+ delegation_1.getIdDelegation() +" removed"));

    }

    @Test
    public void testCreerParticipant() throws Exception {
        mvc.perform(post("/api/organisateur/participant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"nom\": \"Participant 3\",\n" +
                                "\"prenom\": \"Participant 3\",\n" +
                                "\"email\": \"Participant 3\",\n" +
                                "\"epreuveList\": []\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetParticipant() throws Exception {
        mvc.perform(get("/api/organisateur/participant/id/{id}", participant_1.getIdParticipant()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/participant/prenomNom/{prenom}/{nom}", participant_1.getPrenom(),participant_1.getNom()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/participant/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerParticipant() throws Exception {

        mvc.perform(delete("/api/organisateur/participant/delete/{id}",participant_1.getIdParticipant()))
                .andExpect(status().isOk())
                .andExpect(content().string("Participant :"+ participant_1.getIdParticipant() +" removed"));

    }

    @Test
    public void testCreerEpreuve() throws Exception {
        mvc.perform(post("/api/organisateur/epreuve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"EpreuveTest\", " +
                                "\"dateEpreuve\": \"1970-01-01T00:00:00.012+00:00\", " +
                                "\"nbPlacesDispo\": \"10\", " +
                                "\"nbPlacesInit\": \"10\", " +
                                "\"prixBillet\": \"200\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifierEpreuve() throws Exception {
        mvc.perform(put("/api/organisateur/epreuve/modifier/{nom}",e_1.getNomEpreuve())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"EpreuveTestModifier\", " +
                                "\"dateEpreuve\": \"1970-01-01T00:00:00.012+00:00\", " +
                                "\"nbPlacesDispo\": \"9\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerEpreuve() throws Exception {

        mvc.perform(delete("/api/organisateur/epreuve/delete/{id}",e_1.getIdEpreuve()))
                .andExpect(status().isOk())
                .andExpect(content().string("Epreuve :"+ e_1.getIdEpreuve() +" removed"));

    }

    @Test
    public void testGetEpreuve() throws Exception {
        mvc.perform(get("/api/organisateur/epreuve/id/{id}", e_1.getIdEpreuve()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/epreuve/nom/{nom}", e_1.getNomEpreuve()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/epreuve/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreerInfrastructure() throws Exception {
        mvc.perform(post("/api/organisateur/infrastructure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"InfraTest\", " +
                                "\"adresse\": \"InfraTest\", " +
                                "\"capacite\": \"200\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInfrastructure() throws Exception {
        mvc.perform(get("/api/organisateur/infrastructure/id/{id}", infra_1.getIdInfrastructureSportive()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/infrastructure/nom/{nom}", infra_1.getNom()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/infrastructure/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerInfrastructure() throws Exception {
        mvc.perform(delete("/api/organisateur/infrastructure/delete/{id}",infra_1.getIdInfrastructureSportive()))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreerBillet() throws Exception {
        mvc.perform(post("/api/organisateur/billet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"epreuveBillet\": \"{}\", " +
                                "\"spectateurBillet\": \"{}\", " +
                                "\"prix\": \"10\", " +
                                "\"dateBillet\": \"1970-01-01T00:00:00.010+00:00\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBillet() throws Exception {
        mvc.perform(get("/api/organisateur/billet/id/{id}", b_1_1.getIdBillet()))
                .andExpect(status().isOk());

        mvc.perform(get("/api/organisateur/billet/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSupprimerBillet() throws Exception {
        mvc.perform(delete("/api/organisateur/billet/delete/{id}",b_1_1.getIdBillet()))
                .andExpect(status().isOk());

    }


    /** LE BILLET DE CE CONTROLEUR_1 EST DEJA UTILISER **/
    public void testScannerBillet() throws Exception {
        mvc.perform(get("/api/organisateur/scanner/{prenom}/{nom}/{idBillet}/{idSpectateur}",controleur_1.getPrenom(),controleur_1.getNom(),b_1_1.getIdBillet(),sp_1.getIdSpectateur()))
                .andExpect(status().isOk());

    }
}

