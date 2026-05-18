package JeuStrategy;

import Jeu.Personnage;

public class RandomStrategy implements CombatStrategy {
    @Override
    public int chooseAction(Personnage actor, Personnage target) {
        return (int) (Math.random() * 3);
    }
}
