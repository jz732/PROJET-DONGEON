package JeuState;
import Jeu.Personnage;

public class EtatNormal implements EtatPersonnage {
 
    @Override
    public int appliquerModificateurAttaque(
            int degatsBase
    ) {

        return degatsBase;
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
    public void surFinDeTour(Personnage personnage) {

        // Aucun effet
    }

    @Override
    public String getNomEtat() {

        return "Normal";
    }
}