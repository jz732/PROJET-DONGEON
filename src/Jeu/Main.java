package Jeu;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class Main {

	public static void main(String[] args) {
		// creation des objets
		Objet arme = new Objet("Epee roulle", "attaque", 2);
		Objet epee = new Objet("Epee de fer", "attaque", 3);
		Objet potion = new Objet("potion de soin mineur", "soin ", 2);
		Objet bouclier = new Objet("Bouclier de bois", "Defense", 3);
		Objet armure = new Objet("Armure de capitaine", "Defense", 4);
		Objet pain = new Objet("Pain rassis", "soin", 1);
		Objet dague = new Objet("Dague de garde", "attaque", 3);
		Objet bandage = new Objet("Bandages sales", "soin", 3);
		Objet clef = new Objet("Clef de fer", "quete", 0);
		Objet excalibur = new Objet("Lame de Lumiere", "attaque", 10);
		Objet elixir = new Objet("Elixir Royal", "soin", 20);
		Objet Torche = new Objet("Torche", "outils", 0);
		Objet amulette = new Objet("Amulette de protection", "Defense", 6);

		// creation des personnage non joueur
		Personnage garde = new Personnage("Garde Ivre", 1, 5, "Guerrier", 2);
		garde.inventaire.AddObjet(arme);
		garde.inventaire.AddObjet(bouclier);
		new Personnage("Prisonnier Fou", 0, 6, "Voleur", 2);
		new Personnage("Sir Alister", 4, 12, "Chevalier", 3);
		new Personnage("L'Ermite", 0, 100, "Sage", 4);
		// creation des pieces
		Pieces piece1 = new Pieces("LA CELLULE DE PIERRE", 1);
		Pieces piece2 = new Pieces("LE COULOIR SOMBRE", 2);
		Pieces piece3 = new Pieces("SALLE DE GARDE", 3);
		Pieces piece4 = new Pieces("L'ARMURERIE", 4);

		// equipement des pieces
		piece1.AddObjet(arme);
		piece1.AddObjet(potion);
		piece2.AddObjet(bouclier);
		piece2.PNG.add(garde);
		piece2.PNG.add(new Personnage("Sir Alister", 4, 12, "Chevalier", 3));
		piece3.PNG.add(new Personnage("Sir Alister", 4, 12, "Chevalier", 3));
		piece4.PNG.add(new Personnage("L'Ermite", 0, 100, "Sage", 4));
		// ajout des pieces dans le monde
		World monde = new World();
		monde.AddPieces(piece1);
		monde.AddPieces(piece2);
		monde.AddPieces(piece3);
		monde.AddPieces(piece4);

		// debut
		Scanner scanner = new Scanner(System.in);

		Narration("Le silence regne dans la piece . Avant d'ouvrir les yeux, vous devez vous souvenir ...\n");
		Narration("Qui étiez-vous avant la chute?");
		Narration("1. un Magicien dote de savoirs et d'arcanes ? ");
		Narration("ou");
		Narration("2. un Chevalier d'acier et d'honneur ?");
		Narration("A vous >");
		Narration("[1]Magicien");
		Narration("[2]Chevalier");
		int choix = scanner.nextInt();
		if (choix == 2) {
			Personnage player = new Personnage("RAGNAR", 2, 8, "Chevalier", 5);

			Narration("Vous vous souvenez des batailles passees et du froid de l'acier.");
			Narration("Votre volonte est votre bouclier et votre bras votre justice .");
			Start(player.getspecialite(), player,monde);
		}
		if (choix == 1) {
			Personnage player = new Personnage("FLOKI", 1, 5, "Magicien", 2);

			Narration("Vous vous souvenez des batailles passees et du froid de l'acier.");
			Narration("Votre volont est votre bouclier et votre bras votre justice .");
			Start(player.getspecialite(), player,monde);
		}

	}

	public static void Narration(String message) {
		/*// On crée un label pour pouvoir mettre du texte en couleur sur fond noir
		JLabel label = new JLabel(
				"<html><div style='text-align: center; width: 300px; color: Black;'>" + message + "</div></html>");

		// On personnalise un peu l'affichage
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.WHITE);

		// Une simple boîte de dialogue qui attend que l'utilisateur clique sur "OK"
		JOptionPane.showMessageDialog(null, label, "Narration", JOptionPane.PLAIN_MESSAGE);
*/
		
		  for (char c : message.toCharArray()) {
		
		 
		  System.out.print(c); System.out.flush(); try { Thread.sleep(60); } catch
		  (InterruptedException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); } }
		 
		System.out.println();
	}

	public static void Start(String specialite, Personnage player,World monde) {
		if (specialite.equals("Chevalier")) {
			System.out.println("----------------------------------------------------------");
			Narration("----------piece 1 : "+monde.getWorldPieces().get(0).getNom()+"------------");
			System.out.println("----------------------------------------------------------");
			Narration("Vous vous reveillez avec un sensation de lourdeur.");
			Narration(
					"Meme sans armure votre corps de guerrier est pret au combat. Et sur le sol froid , vous apercevez une epee roulle");
			int choix = JOptionPane.showConfirmDialog(null,
					"vous trouvez  une epee roullé \n voulez vous la ramasser? ", "Objet trouve!",
					JOptionPane.YES_NO_OPTION);

			if (choix == JOptionPane.YES_OPTION) {
				
				player.inventaire.AddObjet(monde.getWorldPieces().get(0).getObjets().get(0));
				Narration("Votre arme se trouve dans votre inventaire");
			} else {
				Narration("l'arme est rester au sol");
			}

			Narration(
					"Vous etes debout. La porte en bois semble ancienne mais solide . Que decidez vou de faire, Chevalier?");
			String[] choix1 = { "Defoncer la porte", "Fouiller la paille" };
			int answer = JOptionPane.showOptionDialog(null, "Que decidez vou de faire, Chevalier?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix1, choix1[0]);
			if (answer == 0) {
				Narration("BOOOOOM! votre epaule percute le bois . La porte cede ! mais vous recevez des degats");
				player.setvie(player.getvie() - 2);
			}
			if (answer == 1) {
				Narration(
						"Vous trouvez une potion de soin et ensuite vous defoncer la porte ! Mais vous recever des degats ");
				player.setvie(player.getvie() - 2);
				
				player.inventaire.AddObjet(monde.getWorldPieces().get(0).objets.get(1));
			}
			Narration("vous Apercevez un garde qui fait le tour et le combat commence ");
			Narration(monde.getWorldPieces().get(1).PNG.get(0).toString());
			player.Combat(monde.getWorldPieces().get(1).PNG.get(0));
			Narration("Vous avez obtenue "+monde.getWorldPieces().get(1).getObjets().get(0).nom);
			player.inventaire.AddObjet(monde.getWorldPieces().get(1).getObjets().get(0));
			
			
			//à continuer je suis casse...
		} else {
			System.out.println("----------------------------------------------------------");
			Narration("----------piece 1 : LA CELLULE DE PIERRE------------");
			System.out.println("----------------------------------------------------------");
			Narration(
					"Vous vous reveillez avec l'esprit en feu . Et des formules magiques oubliees dansent devant vos yeux.");
			Narration("une étrangère poussiere d'etoile brille sur vos vetements");
		}

	}
}
