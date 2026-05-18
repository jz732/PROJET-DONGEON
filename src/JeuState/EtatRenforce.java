package JeuState;

import javax.swing.JOptionPane;

public class EtatRenforce implements EtatPersonnage {
    private int toursRestants = 2;

    @Override
    public int appliquerModificateurAttaque(int degatsDeBase) {
        return (int) (degatsDeBase * 1.5); // +50% de dégâts
    }

    @Override
    public int appliquerModificateurDefense(int degatsSubis, int armure) {
        // Subit 30% de dégâts en moins après réduction de l'armure
        int degatsApresArmure = Math.max(0, degatsSubis - armure);
        return (int) (degatsApresArmure * 0.7);
    }

    @Override
    public void surFinDeTour(Personnage personnage) {
        toursRestants--;
        if (toursRestants <= 0) {
            JOptionPane.showMessageDialog(null, "L'effet de renforcement de " + personnage.getnom() + " prend fin.");
            personnage.setEtat(new EtatNormal());
        }
    }

    @Override
    public String getNomEtat() {
        return "Renforcé";
    }
}