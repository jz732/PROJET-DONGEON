package JeuStrategy;

import Jeu.Personnage;

public interface CombatStrategy {
    
     /*
     * Actions: 0 = FastAttack, 1 = HeavyAttack, 2 = Defensive
     */
    int chooseAction(Personnage actor, Personnage target);
}
