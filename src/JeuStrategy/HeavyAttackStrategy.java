package JeuStrategy;

import Jeu.Personnage;

public class HeavyAttackStrategy implements CombatStrategy {
    @Override
    public int chooseAction(Personnage actor, Personnage target) {
        double chance = Math.random();
        return chance > 0.4 ? 1 : 0;
    }
}
