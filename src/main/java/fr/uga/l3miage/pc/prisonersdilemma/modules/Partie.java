package fr.uga.l3miage.pc.prisonersdilemma.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.uga.l3miage.pc.prisonersdilemma.enums.Decision;
import fr.uga.l3miage.pc.prisonersdilemma.enums.TypeStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.strategies.StrategyFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Partie {
    private final Map<String, ArrayList<Decision>> historiqueDecisionsMap = new HashMap<>();
    private StrategyFactory strategyFactory;
    private int nbTours;
    private int tourActuel = 0;

    private final List<Joueur> joueurs = new ArrayList<>();

    public Partie(int nbTours) {
        this.strategyFactory = new StrategyFactory();
        this.nbTours = nbTours;
    }

    public void addJoueur(String pseudo, boolean isConnected, TypeStrategy strategy) throws IllegalStateException {
        Joueur joueur = new Joueur();
        if (isConnected) {
            if (pseudo == null || pseudo.isEmpty()) {
                throw new IllegalStateException("Le nom du joueur ne peut pas être vide.");
            } else if (joueurs.stream().anyMatch(j -> j.getName().equals(pseudo))) {
                throw new IllegalStateException("Le nom du joueur est déjà utilisé.");
            }
            joueur.setName(pseudo);
        } else {
            joueur.setName("Blip-Boup-Bap");
            joueur.setConnected(false);
            joueur.setStrategy(strategyFactory.create(strategy));
        }
        joueurs.add(joueur);
        historiqueDecisionsMap.put(joueur.getName(), new ArrayList<>());
    }

    public int getNbJoueurs() {
        return joueurs.size();
    }

    public void abandonner(Joueur joueur, TypeStrategy typeStrategy) {
        joueur.setConnected(false);
        joueur.setStrategy(strategyFactory.create(typeStrategy));
    }

    @SuppressWarnings("null")
    public boolean soumettreDecision(String pseudo, Decision decision) {
        Joueur joueur1 = joueurs.get(0);
        Joueur joueur2 = joueurs.size() > 1 ? joueurs.get(1) : null;
        if (joueur1.getName().equals(pseudo)) {
            if (joueur1.getDecision() == null) {
                joueur1.setDecision(decision);
                System.out.println("Décision de jouer2 : " + joueur2.getDecision());
                return true;
            }
        } else if (joueur2!=null && joueur2.getDecision() == null) {
            if(joueur2.getName().equals(pseudo)){
                joueur2.setDecision(decision);
                return true;
            }
            else if(!joueur2.isConnected()){
                joueur2.setDecision(joueur2.getStrategy().execute(null, null));          
              }
            return true;
        }
        return false;
    }

    public boolean peutJouerTour() {
        return (joueurs.get(0).getDecision() != null && joueurs.get(1).getDecision() != null) && tourActuel <= nbTours;
        
    }

    public void jouerTour() {
        Joueur joueur1 = joueurs.get(0);
        Joueur joueur2 = joueurs.size() > 1 ? joueurs.get(1) : null;
        if (!peutJouerTour()) {
            throw new IllegalStateException("Les décisions des deux joueurs ne sont pas encore prêtes.");
        }

        historiqueDecisionsMap.get(joueurs.get(0).getName()).add(joueur1.getDecision());
        historiqueDecisionsMap.get(joueurs.get(1).getName()).add(joueur1.getDecision());

        mettreAJourScores();

        joueur1.setDecision(null); 
        joueur2.setDecision(null);
        tourActuel++;
    }

    private void mettreAJourScores() {
        Decision decisionJoueur1 = joueurs.get(0).getDecision();
        Decision decisionJoueur2 = joueurs.get(1).getDecision();
        if (decisionJoueur1 == Decision.COOPERER && decisionJoueur2 == Decision.COOPERER) {
            joueurs.get(0).ajouterScore(3);
            joueurs.get(1).ajouterScore(3);
        } else if (decisionJoueur1 == Decision.TRAHIR && decisionJoueur2 == Decision.TRAHIR) {
            joueurs.get(0).ajouterScore(1);
            joueurs.get(1).ajouterScore(1);
        } else if (decisionJoueur1 == Decision.COOPERER && decisionJoueur2 == Decision.TRAHIR) {
            joueurs.get(0).ajouterScore(0);
            joueurs.get(1).ajouterScore(5);
        } else if (decisionJoueur1 == Decision.TRAHIR && decisionJoueur2 == Decision.COOPERER) {
            joueurs.get(0).ajouterScore(5);
            joueurs.get(1).ajouterScore(0);
        }
    }

    public boolean getDecisionOfOtherPlayer(String pseudo) {
        System.out.println("Getting decision of other player for pseudo: " + pseudo);
        for (Joueur j : joueurs) {
            System.out.println("Player in list: " + j.getName() + ", Decision: " + j.getDecision());
        }
    
        Joueur requestingPlayer = joueurs.stream()
                .filter(j -> j.getName().equals(pseudo))
                .findFirst()
                .orElse(null);
    
        if (requestingPlayer == null) {
            System.out.println("Requesting player not found.");
            return false;
        }
    
        Joueur otherPlayer = joueurs.stream()
                .filter(j -> !j.getName().equals(pseudo))
                .findFirst()
                .orElse(null);
    
        if (otherPlayer != null) {
            System.out.println("Other player found: " + otherPlayer.getName());
            if (otherPlayer.getDecision() != null) {
                return true;
            } else {
                System.out.println("Other player's decision is null.");
            }
        } else {
            System.out.println("No other player found.");
        }
        return false;
    }
    

}
