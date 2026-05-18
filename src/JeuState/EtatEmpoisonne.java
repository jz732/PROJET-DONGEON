package JeuState;

import Jeu.Personnage;
import Observer.GameEvent;
import Observer.GameEventType;

public class EtatEmpoisonne implements EtatPersonnage {

    @Override
    public int appliquerModificateurAttaque(
            int degatsBase
    ) {

        return degatsBase - 1;
    }

    @Override
    public int appliquerModificateurDefense(
            int degatsRecus,
            int armure
    ) {

        return Math.max(
            0,
            degatsRecus - armure
        );
    }

    @Override
    public void surFinDeTour(
            Personnage personnage
    ) {

        personnage.setvie(
            personnage.getvie() - 1
        );

        personnage.notifyObservers(
            new GameEvent(
                GameEventType.HEALTH_CHANGED,
                personnage,
                personnage.getnom()
                + " souffre du poison."
            )
        );

        if(personnage.getvie() <= 0) {

            personnage.setEtat(
                new EtatMort()
            );
        }
    }

    @Override
    public String getNomEtat() {

        return "Empoisonné";
    }
}