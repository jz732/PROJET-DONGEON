package JeuSingleton;


import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

import Jeu.Personnage;

public class GameManager {
    
    // 1. L'unique instance du Singleton
    private static GameManager instance;
    
    // Attribut d'état pour suivre la pièce actuelle
    private int pieceActuelleIndex = 0;

    // 2. Le constructeur privé
    private GameManager() {}

    // 3. Le point d'accès global au Singleton
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // --- GETTERS ET SETTERS ---
    public int getPieceActuelleIndex() { 
        return pieceActuelleIndex; 
    }
    
    public void setPieceActuelleIndex(int index) { 
        this.pieceActuelleIndex = index; 
    }


    public void sauvegarderPartie(Personnage player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sauvegarde.txt"))) {
            
            // On écrit les données du joueur ligne par ligne dans le fichier
            writer.println(player.getnom());
            writer.println(player.getvie());
            writer.println(player.getarmure());
            writer.println(player.getDegat());
            writer.println(pieceActuelleIndex); // Sauvegarde de la position (pièce actuelle)
            
            JOptionPane.showMessageDialog(null, "💾 Partie sauvegardee avec succes !");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    // ========================================================
    // LA MÉTHODE DE CHARGEMENT
    // ========================================================
    public void chargerPartie(Personnage player) {
        File file = new File("sauvegarde.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "⚠️ Aucune sauvegarde trouvee.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // On lit et réassigne les variables dans le même ordre
            player.setnom(reader.readLine());
            player.setvie(Integer.parseInt(reader.readLine()));
            player.setarmure(Integer.parseInt(reader.readLine()));
            player.setDegat(Integer.parseInt(reader.readLine()));
            this.pieceActuelleIndex = Integer.parseInt(reader.readLine());
            
            JOptionPane.showMessageDialog(null, "🔄 Partie chargee ! Statistiques restaurees.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Erreur lors du chargement : " + e.getMessage());
        }
    }
}