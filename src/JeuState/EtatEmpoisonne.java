package JeuState;

import javax.swing.JOptionPane;

public class EtatEmpoisonne implements EtatPersonnage {
    private int toursRestants = 3; // Le poison dure 3 tours

    @Override
    public int appliquerModificateurAttaque(int degatsDeBase) {
        return degatsDeBase; 
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
        if (toursRestants > 0) {
            int degatsPoison = 5;
            // On baisse directement la vie (le poison ignore l'armure)
            personnage.setvie(Math.max(0, personnage.getvie() - degatsPoison));
            JOptionPane.showMessageDialog(null, personnage.getnom() + " souffre du poison et perd " + degatsPoison + " HP !");
            toursRestants--;
        }
        
        if (toursRestants <= 0) {
            JOptionPane.showMessageDialog(null, "Le poison se dissipe pour " + personnage.getnom() + ".");
            personnage.setEtat(new EtatNormal()); // Retour à l'état normal
        }
    }

    @Override
    public String getNomEtat() {
        return "Empoisonné";
    }
}