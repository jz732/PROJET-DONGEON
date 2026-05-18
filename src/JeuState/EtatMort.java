package JeuState;

import Jeu.Personnage;

public class EtatMort
implements EtatPersonnage {

    @Override
    public int appliquerModificateurAttaque(
            int degatsBase
    ) {

        return 0;
    }

    @Override
    public int appliquerModificateurDefense(
            int degatsRecus,
            int armure
    ) {

        return 0;
    }

    @Override
    public void surFinDeTour(
            Personnage personnage
    ) {

        // Rien
    }

    @Override
    public String getNomEtat() {

        return "Mort";
    }
}