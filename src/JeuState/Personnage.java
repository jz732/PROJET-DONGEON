package JeuState;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Personnage {
    private String nom;
    private int armure;
    private int vie;
    private String specialite;
    public int degat;
    public int niveau;
    
    // --- AJOUT PATTERN STATE ---
    private EtatPersonnage etat; 

    public Personnage(String nom, int armure, int vie, String specialite, int degat) {
        this.armure = armure;
        this.nom = nom;
        this.specialite = specialite;
        this.vie = vie;
        this.degat = degat;
        this.niveau = 0;
        this.etat = new EtatNormal(); // État initial par défaut
    }

    // Getters et Setters pour l'État
    public EtatPersonnage getEtat() { return etat; }
    public void setEtat(EtatPersonnage nouvelEtat) { this.etat = nouvelEtat; }

    // Autres Getters/Setters inchangés...
    public String getnom() { return nom; }
    public String getspecialite() { return specialite; }
    public int getarmure() { return armure; }
    public int getvie() { return vie; }
    public void setvie(int vie) { this.vie = vie; }
    public int getDegat() { return degat; }

    // --- MISE À JOUR : SUBIR DEGATS ---
    public void subirDegats(int degats) {
        // On laisse l'état actuel calculer les dégâts réels nets reçus
        int degatsNets = etat.appliquerModificateurDefense(degats, armure);
        
        if (degatsNets > 0) {
            System.out.println("Attaque brute de : " + degats + " dégâts.");
            this.vie = Math.max(0, this.vie - degatsNets);
            System.out.println(nom + " subit finalement " + degatsNets + " dégât(s).");
        } else {
            System.out.println(nom + " ne subit aucun dégât.");
        }
    }

    // --- MISE À JOUR : COMBAT ---
    public void Combat(Personnage png) {
        String[] actionsCombat = {"Attaque Rapide", "Coup Puissant", "Parade", "Se Renforcer"};

        while (this.vie > 0 && png.getvie() > 0) {
            // Affichage incluant l'état actuel du joueur
            int coup = JOptionPane.showOptionDialog(null, 
                "--- COMBAT ---\n" +
                "Votre Vie : " + vie + " HP (" + etat.getNomEtat() + ")\n" +
                "Vie du " + png.getnom() + " : " + png.getvie() + " HP (" + png.getEtat().getNomEtat() + ")\n\n" +
                "Que faites-vous ?",
                "Duel", 0, JOptionPane.WARNING_MESSAGE, null, actionsCombat, actionsCombat[0]);

            // Calcul des dégâts infligés de base, modifiés par l'état actuel du joueur
            int degatsActuels = etat.appliquerModificateurAttaque(this.degat);

            if (coup == 0) { // Attaque Rapide
                JOptionPane.showMessageDialog(null, "Vous touchez le " + png.getnom() + " ! (" + degatsActuels + " HP)");
                png.subirDegats(degatsActuels);
            } 
            else if (coup == 1) { // Coup Puissant
                if (Math.random() > 0.4) {
                    JOptionPane.showMessageDialog(null, "Coup dévastateur ! (" + (degatsActuels * 2) + " HP)");
                    png.subirDegats(degatsActuels * 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Vous ratez... Le " + png.getnom() + " contre-attaque !");
                    this.subirDegats(png.getDegat() * 2);
                }
            }
            else if (coup == 2) { // Parade
                JOptionPane.showMessageDialog(null, "Vous vous protégez.");
                this.subirDegats(png.getDegat() / 2);
            }
            else if (coup == 3) { // Se Renforcer (Nouveau choix pour tester l'état)
                JOptionPane.showMessageDialog(null, "Vous vous concentrez. Vous entrez en état Renforcé !");
                this.setEtat(new EtatRenforce());
            }

            // Riposte automatique de l'ennemi s'il est en vie
            if (png.getvie() > 0 && this.vie > 0) {
                int degatsEnnemi = png.getEtat().appliquerModificateurAttaque(png.getDegat());
                JOptionPane.showMessageDialog(null, "Le " + png.getnom() + " riposte ! (" + degatsEnnemi + " HP)");
                this.subirDegats(degatsEnnemi);
            }

            // --- APPLICATION DES EFFETS DE FIN DE TOUR ---
            if (this.vie > 0) this.etat.surFinDeTour(this);
            if (png.getvie() > 0) png.getEtat().surFinDeTour(png);
        }

        // Conclusion du combat
        if (this.vie > 0 && png.getvie() <= 0) {
            JOptionPane.showMessageDialog(null, "Vous avez vaincu le " + png.getnom() + " !");
        } else {
            JOptionPane.showMessageDialog(null, "Vous avez échoué...");
        }
    }
}