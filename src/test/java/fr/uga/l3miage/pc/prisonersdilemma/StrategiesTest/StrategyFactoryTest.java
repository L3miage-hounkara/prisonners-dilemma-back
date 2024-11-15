package fr.uga.l3miage.pc.prisonersdilemma.StrategiesTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.uga.l3miage.pc.prisonersdilemma.enums.TypeStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.interfaces.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.Aleatoire;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.DonnantDonnant;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.DonnantDonnantAleatoire;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.DonnantPourDeuxDonnants;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.DonnantPourDeuxDonnantsEtAleatoire;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.PacificateurNaif;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.Rancunier;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.RancunierDoux;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.SondeurNaif;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.SondeurRepentant;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.StrategyFactory;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.ToujoursCooperer;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.ToujoursTrahir;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.VraiPacificateur;

class StrategyFactoryTest {

    private StrategyFactory strategyFactory = new StrategyFactory();

    @Test
    void testCreateAleatoireStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof Aleatoire);
    }

    @Test
    void testCreateToujoursCoopererStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.TOUJOURS_COOPERER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof ToujoursCooperer);
    }

    @Test
    void testCreateToujoursTrahirStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.TOUJOURS_TRAHIR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof ToujoursTrahir);
    }

    @Test
    void testCreateDonnantDonnantStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.DONNANT_DONNANT);
        assertNotNull(strategy);
        assertTrue(strategy instanceof DonnantDonnant);
    }

    @Test
    void testCreateDonnantDonnantAleatoireStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.DONNANT_DONNANT_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof DonnantDonnantAleatoire);
    }

    @Test
    void testCreateDonnantPourDeuxDonnantsStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.DONNANT_POUR_DEUX_DONNANTS);
        assertNotNull(strategy);
        assertTrue(strategy instanceof DonnantPourDeuxDonnants);
    }

    @Test
    void testCreateDonnantPourDeuxDonnantsAleatoireStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE);
        assertNotNull(strategy);
        assertTrue(strategy instanceof DonnantPourDeuxDonnantsEtAleatoire);
    }

    @Test
    void testCreateSondeurNaifStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.SONDEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof SondeurNaif);
    }

    @Test
    void testCreateSondeurRepentantStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.SONDEUR_REPENTANT);
        assertNotNull(strategy);
        assertTrue(strategy instanceof SondeurRepentant);
    }

    @Test
    void testCreatePacificateurNaifStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.PACIFICATEUR_NAIF);
        assertNotNull(strategy);
        assertTrue(strategy instanceof PacificateurNaif);
    }

    @Test
    void testCreateVraiPacificateurStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.VRAI_PACIFICATEUR);
        assertNotNull(strategy);
        assertTrue(strategy instanceof VraiPacificateur);
    }

    @Test
    void testCreateRancunierStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.RANCUNIER);
        assertNotNull(strategy);
        assertTrue(strategy instanceof Rancunier);
    }

    @Test
    void testCreateRancunierDouxStrategy() {
        Strategy strategy = strategyFactory.create(TypeStrategy.RANCUNIER_DOUX);
        assertNotNull(strategy);
        assertTrue(strategy instanceof RancunierDoux);
    }
}
