package JeuState;

public class EtatNormal implements EtatPersonnage {
    @Override
    public int appliquerModificateurAttaque(int degatsDeBase) {
        return degatsDeBase; // Dégâts normaux
    }

    @Override
    public int appliquerModificateurDefense(int degatsSubis, int armure) {
        if (armure < degatsSubis) {
            return degatsSubis - armure;
        }
        return 0;
    }

    @Override
    public void surFinDeTour(Personnage personnage) {
        // Rien ne se passe
    }

    @Override
    public String getNomEtat() {
        return "Normal";
    }
}