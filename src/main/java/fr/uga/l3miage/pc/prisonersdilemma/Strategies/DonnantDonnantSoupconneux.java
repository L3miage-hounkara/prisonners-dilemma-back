package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import java.util.ArrayList;

import fr.uga.l3miage.pc.prisonersdilemma.enums.Decision;
import fr.uga.l3miage.pc.prisonersdilemma.interfaces.Strategy;

public class DonnantDonnantSoupconneux implements Strategy
{
    @Override
    public Decision execute(ArrayList<Decision> historiqueJoueur1, ArrayList<Decision> historiqueJoueur2)
    {
        if (historiqueJoueur2.isEmpty())
        {
            return Decision.TRAHIR;
        }
        return historiqueJoueur2.get(historiqueJoueur2.size() - 1);
    }

}
