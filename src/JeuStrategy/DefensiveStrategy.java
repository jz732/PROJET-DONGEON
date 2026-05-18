package JeuStrategy;

import Jeu.Personnage;

public class DefensiveStrategy implements CombatStrategy {
    @Override
    public int chooseAction(Personnage actor, Personnage target) {
        // If low health, try to defend (2), otherwise quick attack
        if (actor.getvie() <= 3) return 2;
        return 0;
    }
}
