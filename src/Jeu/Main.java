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
		// --- ACTE 1 ---
		Objet clefFer = new Objet("Clef de fer", "Quete", 0);
		Objet bouclierBois = new Objet("Bouclier de bois", "Defense", 2);

		// --- ACTE 2 ---
		Objet dagueGarde = new Objet("Dague de garde", "Attaque", 4);
		Objet armureCapitaine = new Objet("Armure de capitaine", "Defense", 4);
		Objet amuletteProt = new Objet("Amulette de protection", "Magie", 5);

		// --- ACTE 3 ---
		Objet elixirRoyal = new Objet("Elixir Royal", "Soin", 20);
		Objet lameLumiere = new Objet("Lame de Lumiere", "Attaque", 10);

		// creation des personnage non joueur
		Personnage garde = new Personnage("Garde Ivre", 1, 5, "Guerrier", 2);
		garde.inventaire.AddObjet(arme);
		garde.inventaire.AddObjet(bouclier);
		new Personnage("Prisonnier Fou", 0, 6, "Voleur", 2);
		new Personnage("Sir Alister", 4, 12, "Chevalier", 3);
		new Personnage("L'Ermite", 0, 100, "Sage", 4);
		Personnage gardeIvre = new Personnage("Garde Ivre", 1, 8, "Guerrier", 2);
		Personnage bourreau = new Personnage("Le Bourreau", 2, 15, "Boss", 4);
		Personnage pretreDechu = new Personnage("Pretre Dechu", 1, 20, "Mage", 5);
		Personnage alister = new Personnage("Sir Alister", 5, 25, "Chevalier", 6);
		Personnage usurpateur = new Personnage("Ragnaros", 8, 50, "Roi", 10);
		// creation des pieces
		Pieces piece1 = new Pieces("LA CELLULE DE PIERRE", 1);
		Pieces piece2 = new Pieces("LE COULOIR SOMBRE", 2);
		Pieces p3 = new Pieces("LA SALLE DES TORTURES", 3);
        p3.PNG.add(new Personnage("Le Bourreau", 2, 15, "Boss", 4));

        Pieces p4 = new Pieces("L'ARMURERIE ROYALE", 4);
        p4.AddObjet(armureCapitaine);
        p4.AddObjet(dagueGarde);

        Pieces p5 = new Pieces("LA GRANDE BIBLIOTHEQUE", 5);
        p5.PNG.add(new Personnage("L'Ermite", 0, 100, "Sage", 0));

        Pieces p6 = new Pieces("LA CHAPELLE MAUDITE", 6);
        p6.PNG.add(new Personnage("Pretre Dechu", 1, 20, "Mage", 5));
        p6.AddObjet(amuletteProt);

        Pieces p7 = new Pieces("LES CUISINES DESERTES", 7);
        p7.AddObjet(elixirRoyal);

        Pieces p8 = new Pieces("LE PONT-LEVIS INTERIEUR", 8);
        p8.PNG.add(new Personnage("Sir Alister", 5, 25, "Chevalier", 6));

        Pieces p9 = new Pieces("L'ANTICHAMBRE DU TRONE", 9); // Pièce de l'énigme finale

        Pieces p10 = new Pieces("LA SALLE DU TRONE", 10);
        p10.PNG.add(new Personnage("Ragnaros l'Usurpateur", 8, 50, "Roi", 10));
        p10.AddObjet(lameLumiere);

		// equipement des pieces
		piece1.AddObjet(arme);
		piece1.AddObjet(potion);
		piece2.AddObjet(bouclier);
		piece2.PNG.add(garde);
		piece2.PNG.add(new Personnage("Sir Alister", 4, 12, "Chevalier", 3));
		
		// ajout des pieces dans le monde
		World monde = new World();
		monde.AddPieces(piece1);
		monde.AddPieces(piece2);
		monde.AddPieces(p3);
		monde.AddPieces(p5);
		monde.AddPieces(p4);
		monde.AddPieces(p6);
		monde.AddPieces(p7);
		monde.AddPieces(p8);
		monde.AddPieces(p9);
		monde.AddPieces(p10);
		

		// debut
		Scanner scanner = new Scanner(System.in);

		Narration("Le silence regne dans la piece . Avant d'ouvrir les yeux, vous devez vous souvenir ...\n",null);
		Narration("Qui étiez-vous avant la chute?",null);
		Narration("1. un Magicien dote de savoirs et d'arcanes ? ",null);
		Narration("ou",null);
		Narration("2. un Chevalier d'acier et d'honneur ?",null);
		Narration("A vous >",null);
		Narration("[1]Magicien",null);
		Narration("[2]Chevalier",null);
		int choix = scanner.nextInt();
		if (choix == 2) {
			Personnage player = new Personnage("RAGNAR", 2, 8, "Chevalier", 5);

			Narration("Vous vous souvenez des batailles passees et du froid de l'acier.",player);
			Narration("Votre volonte est votre bouclier et votre bras votre justice .",player);
			Start(player.getspecialite(), player,monde);
		}
		if (choix == 1) {
			Personnage player = new Personnage("FLOKI", 1, 5, "Magicien", 2);

			Narration("Vous vous souvenez des batailles passees et du froid de l'acier.",player);
			Narration("Votre volont est votre bouclier et votre bras votre justice .",player);
			Start(player.getspecialite(), player,monde);
		}

	}

	public static void Narration(String message, Personnage player) {
		UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        JLabel label = new JLabel("<html><div style='text-align: center; width: 300px; color: white;'>" + message + "</div></html>");
        
        String[] options = {"Continuer", "Inventaire","pause"};
        int n = JOptionPane.showOptionDialog(null, label, "Chronique",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (n == 1 && player != null) {
            String inv = "Sac de " + player.getnom() + ":\n";
            for (Objet o : player.inventaire.inventaire) {
                inv += "- " + o.getNom() + " (" + o.getSpecificite() + ")\n";
            }
            JOptionPane.showMessageDialog(null, inv, "Inventaire", JOptionPane.INFORMATION_MESSAGE);
            Narration(message, player);
        }
        if(n==2) {
        	
        	MenuIntermediaire(player);
        }
		
		 /*for (char c : message.toCharArray()) {
		
		 
		  System.out.print(c); System.out.flush(); try { Thread.sleep(60); } catch
		  (InterruptedException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); } 
		 
		System.out.println();}*/
	}
	

	public static void Start(String specialite, Personnage player,World monde) {
		if (specialite.equals("Chevalier")) {
			System.out.println("----------------------------------------------------------");
			Narration("----------piece 1 : "+monde.getWorldPieces().get(0).getNom()+"------------",player);
			System.out.println("----------------------------------------------------------");
			Narration("Vous vous reveillez avec un sensation de lourdeur.",player);
			Narration(
					"Meme sans armure votre corps de guerrier est pret au combat. Et sur le sol froid , vous apercevez une epee roulle",player);
			int choix = JOptionPane.showConfirmDialog(null,
					"vous trouvez  une epee roullé \n voulez vous la ramasser? ", "Objet trouve!",
					JOptionPane.YES_NO_OPTION);

			if (choix == JOptionPane.YES_OPTION) {
				
				player.inventaire.AddObjet(monde.getWorldPieces().get(0).getObjets().get(0));
				Narration("Votre arme se trouve dans votre inventaire",player);
			} else {
				Narration("l'arme est rester au sol",player);
			}

			Narration(
					"Vous etes debout. La porte en bois semble ancienne mais solide . Que decidez vou de faire, Chevalier?",player);
			String[] choix1 = { "Defoncer la porte", "Fouiller la paille" };
			int answer = JOptionPane.showOptionDialog(null, "Que decidez vou de faire, Chevalier?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix1, choix1[0]);
			if (answer == 0) {
				Narration("BOOOOOM! votre epaule percute le bois . La porte cede ! mais vous recevez des degats",player);
				player.setvie(player.getvie() - 2);
			}
			if (answer == 1) {
				Narration(
						"Vous trouvez une potion de soin et ensuite vous defoncer la porte ! Mais vous recever des degats ",player);
				player.setvie(player.getvie() - 2);
				
				player.inventaire.AddObjet(monde.getWorldPieces().get(0).objets.get(1));
			}
			Narration("vous Apercevez un garde qui fait le tour et le combat commence ",player);
			Narration(monde.getWorldPieces().get(1).PNG.get(0).toString(),player);
			player.Combat(monde.getWorldPieces().get(1).PNG.get(0));
			Narration("Vous avez obtenue "+monde.getWorldPieces().get(1).getObjets().get(0).nom,player);
			player.inventaire.AddObjet(monde.getWorldPieces().get(1).getObjets().get(0));
			Narration("Une odeur de fer et de sang... Vous êtes dans " + monde.getWorldPieces().get(2).getNom(), player);
	        Personnage b = monde.getWorldPieces().get(2).PNG.get(0);
	        Narration("Le " + b.getnom() + " lève sa hache : 'Nouvelle victime, nouveau plaisir !'", player);
	        player.Combat(b);

	        // --- PIÈCE 5 : LA BIBLIOTHÈQUE (Transition) ---
	        Narration("Vous fuyez les cris pour la tranquillité de " + monde.getWorldPieces().get(3).getNom(), player);
	        Personnage sage = monde.getWorldPieces().get(3).PNG.get(0);
	        Narration("Un vieil homme, " + sage.getnom() + ", vous observe : 'Pour passer, l'esprit compte plus que l'acier.'", player);
	        
	        String enigme = JOptionPane.showInputDialog("Je n'ai pas de voix et pourtant je parle. Qui suis-je ?");
	        if (enigme != null && enigme.equalsIgnoreCase("echo")) {
	            Narration("'Sage réponse. Reprenez des forces.' Votre vie remonte !", player);
	            player.setvie(player.getvie() + 5);
	        } else {
	            Narration("'L'ignorance fait mal...' Il vous frappe de son bâton.", player);
	            player.setvie(player.getvie() - 2);
	        }

	        // --- PIÈCE 4 : L'ARMURERIE ---
	        Narration("Vous atteignez enfin " + monde.getWorldPieces().get(4).getNom(), player);
	        Narration("Des râteliers pleins ! Vous équipez une " + monde.getWorldPieces().get(4).getObjets().get(0).nom, player);
	        player.inventaire.AddObjet(monde.getWorldPieces().get(4).getObjets().get(0));
	        player.setarmure(player.getarmure() + 4);

	        // --- PIÈCE 6 : LA CHAPELLE ---
	        Narration("L'ambiance devient glaciale dans " + monde.getWorldPieces().get(5).getNom(), player);
	        Personnage p = monde.getWorldPieces().get(5).PNG.get(0);
	        Narration("Le " + p.getnom() + " psalmodie : 'Ton âme m'appartient !'", player);
	        player.Combat(p);
	        player.inventaire.AddObjet(monde.getWorldPieces().get(5).getObjets().get(0));

	        // --- PIÈCE 7 : LES CUISINES ---
	        Narration("Vous passez par " + monde.getWorldPieces().get(6).getNom(), player);
	        Narration("Sur une table, vous trouvez un " + monde.getWorldPieces().get(6).getObjets().get(0).nom, player);
	        player.inventaire.AddObjet(monde.getWorldPieces().get(6).getObjets().get(0));

	        // --- PIÈCE 8 : LE PONT-LEVIS ---
	        Narration("Le vent siffle sur " + monde.getWorldPieces().get(7).getNom(), player);
	        Personnage ali = monde.getWorldPieces().get(7).PNG.get(0);
	        Narration(ali.getnom() + " tire son épée : 'Je ne peux te laisser passer, ancien ami.'", player);
	        player.Combat(ali);

	        // --- PIÈCE 9 : L'ANTICHAMBRE ---
	        Narration("Vous êtes aux portes du pouvoir : " + monde.getWorldPieces().get(8).getNom(), player);
	        Narration("Un miroir magique vous demande : 'Pourquoi te bats-tu ?'", player);
	        JOptionPane.showInputDialog("Votre raison :");
	        Narration("Le miroir tremble. Le chemin vers le trône est ouvert.", player);

	        // --- PIÈCE 10 : LE TRÔNE ---
	        Narration("La porte s'ouvre lourdement... " + monde.getWorldPieces().get(9).getNom(), player);
	        Personnage boss = monde.getWorldPieces().get(9).PNG.get(0);
	        Narration(boss.getnom() + " ricane : 'Tu es venu mourir pour une couronne de poussière !'", player);
	        player.Combat(boss);

	        if (player.getvie() > 0) {
	            Narration("Le tyran tombe. Vous ramassez la " + monde.getWorldPieces().get(9).getObjets().get(0).nom, player);
	            Narration("Le royaume est libre. Longue vie au Roi " + player.getnom() + " !", player);
	        }

	     else {
	        // --- LOGIQUE MAGICIEN ---
	        Narration("----------PIÈCE 1 : LA CELLULE DE PIERRE------------", player);
	        Narration("L'énergie mystique crépite dans vos doigts.", player);
	        Narration("Vous utilisez une étincelle pour faire sauter le verrou de la cellule !", player);
	        // Utilise la même structure de boucles (monde.getWorldPieces().get(X)...) pour le magicien
	        Narration("Vous avancez dans le couloir sombre...", player);
	    }
			
			//à continuer je suis casse...
		} else {
			System.out.println("----------------------------------------------------------");
			Narration("----------piece 1 : LA CELLULE DE PIERRE------------",player);
			System.out.println("----------------------------------------------------------");
			Narration(
					"Vous vous reveillez avec l'esprit en feu . Et des formules magiques oubliees dansent devant vos yeux.",player);
			Narration("une étrangère poussiere d'etoile brille sur vos vetements",player);
			System.out.println("a");
		}

	}
	public static void passerAPieceSuivante(int numero, Personnage player, World monde) {
	    Pieces p = monde.getWorldPieces().get(numero);
	    Narration("Vous entrez dans : " + p.getNom()+ player.getnom(),player);
	    // Logique spécifique par numéro de pièce...
	}
	public static void MenuIntermediaire(Personnage player) {
	    String[] choix = {"Continuer l'aventure", "Ouvrir l'Inventaire (Soins/Équipement)"};
	    int action = JOptionPane.showOptionDialog(null, "Que voulez-vous faire avant d'avancer ?", 
	            "Pause", 0, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

	    if (action == 1) {
	        if (player.inventaire.inventaire.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Votre inventaire est vide !");
	        } else {
	            // Création d'une liste de noms d'objets pour le menu
	            String[] items = new String[player.inventaire.inventaire.size()];
	            for (int i = 0; i < player.inventaire.inventaire.size(); i++) {
	                items[i] = player.inventaire.inventaire.get(i).getNom();
	            }

	            int itemChoisi = JOptionPane.showOptionDialog(null, "Choisissez un objet à utiliser :", 
	                    "Inventaire", 0, JOptionPane.PLAIN_MESSAGE, null, items, items[0]);

	            if (itemChoisi != -1) {
	                Objet o = player.inventaire.inventaire.get(itemChoisi);
	                
	                // LOGIQUE D'UTILISATION
	                if (o.getSpecificite().toLowerCase().contains("soin")) {
	                    player.setvie(player.getvie() + o.getValeur());
	                    JOptionPane.showMessageDialog(null, "Vous utilisez " + o.getNom() + ". + " + o.getValeur() + " PV !");
	                    player.inventaire.inventaire.remove(itemChoisi); // On consomme l'objet
	                } 
	                else if (o.getSpecificite().toLowerCase().contains("defense") || o.getSpecificite().toLowerCase().contains("armure")) {
	                    player.setarmure(player.getarmure() + o.getValeur());
	                    JOptionPane.showMessageDialog(null, "Vous équipez " + o.getNom() + ". Armure augmentée !");
	                    player.inventaire.inventaire.remove(itemChoisi);
	                }
	                else {
	                    JOptionPane.showMessageDialog(null, "Cet objet ne peut pas être utilisé ainsi.");
	                }
	            }
	        }
	        // Rappel du menu pour pouvoir utiliser plusieurs objets à la suite
	        MenuIntermediaire(player);
	    }
	}
}
