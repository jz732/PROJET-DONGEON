package JeuState;

import Jeu.Personnage;

public interface EtatPersonnage {

    int appliquerModificateurAttaque(int degatsBase);

    int appliquerModificateurDefense(
            int degatsRecus,
            int armure
    );

    void surFinDeTour(Personnage personnage);

    String getNomEtat();
}