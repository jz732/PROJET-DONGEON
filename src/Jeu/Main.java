package Jeu;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;

import Observer.ConsoleObserver;
import JeuStrategy.StrategyFactory;

public class Main {

	public static void main(String[] args) {
		// --- CRÉATION DES OBJETS EN JEU ---
		Objet arme = new Objet("Epee rouillee", "attaque", 2);
		Objet potion = new Objet("Potion de soin mineur", "soin", 2);
		Objet bouclierBois = new Objet("Bouclier de bois", "Defense", 2);
		Objet dagueGarde = new Objet("Dague de garde", "Attaque", 4);
		Objet armureCapitaine = new Objet("Armure de capitaine", "Defense", 4);
		Objet amuletteProt = new Objet("Amulette de protection", "Magie", 5);
		Objet elixirRoyal = new Objet("Elixir Royal", "Soin", 20);
		Objet lameLumiere = new Objet("Lame de Lumiere", "Attaque", 10);

		// --- CRÉATION DES PIÈCES ---
		Pieces piece1 = new Pieces("LA CELLULE DE PIERRE", 1);
		piece1.setSynopsis("Le froid de la pierre vous rappelle la trahison de Ragnaros. Vous reprenez conscience dans une cellule humide.\nLa lourde porte en bois devant vous est verrouillee.\nVous devez fouiller la paille sale pour trouver la clef de fer ou prendre le risque de briser le verrou.");
		piece1.AddObjet(arme);
		piece1.AddObjet(potion);

		Pieces piece2 = new Pieces("LE COULOIR SOMBRE", 2);
		piece2.setSynopsis("Le verrou a saute. Vous glissez prudemment dans le couloir sombre du donjon.\nMalheureusement, le grincement a alerte le Garde Ivre.\nLe battre est votre seule chance de continuer.");
		Personnage garde = new Personnage("Garde Ivre", 1, 8, "Guerrier", 2);
		garde.setCombatStrategy(StrategyFactory.getStrategy("fast"));
		piece2.PNG.add(garde);
		piece2.AddObjet(bouclierBois);

		Pieces p3 = new Pieces("LA SALLE DES TORTURES", 3);
		p3.setSynopsis("L'odeur de fer et de sang vous souleve le coeur. Le Bourreau est la, brandissant sa hache.\nSi vous le terrassez, un soldat fidele aura peut-etre le temps de vous reveler un secret.");
		Personnage bourreau = new Personnage("Le Bourreau", 2, 15, "Boss", 4);
		bourreau.setCombatStrategy(StrategyFactory.getStrategy("heavy"));
		p3.PNG.add(bourreau);

		Pieces p4 = new Pieces("L'ARMURERIE ROYALE", 4);
		p4.setSynopsis("Vous atteignez l'Armurerie Royale. C'est une mine d'or.\nRagnaros y a expose votre ancienne Armure de Capitaine et votre Dague.");
		p4.AddObjet(armureCapitaine);
		p4.AddObjet(dagueGarde);

		Pieces p5 = new Pieces("LA GRANDE BIBLIOTHEQUE", 5);
		p5.setSynopsis("Au milieu des milliers de livres, l'Ermite vous attend de pied ferme.\nCe vieux sage refuse de vous laisser monter sans tester votre esprit.");
		Personnage ermite = new Personnage("L'Ermite", 0, 100, "Sage", 0);
		ermite.setCombatStrategy(StrategyFactory.getStrategy("defensive"));
		p5.PNG.add(ermite);

		Pieces p6 = new Pieces("LA CHAPELLE MAUDITE", 6);
		p6.setSynopsis("L'air devient glacial. L'autel a ete profane par le Pretre Dechu.\nVous devez briser sa malediction.");
		Personnage pretre = new Personnage("Pretre Dechu", 1, 20, "Mage", 5);
		pretre.setCombatStrategy(StrategyFactory.getStrategy("heavy"));
		p6.PNG.add(pretre);
		p6.AddObjet(amuletteProt);

		Pieces p7 = new Pieces("LES CUISINES DESERTES", 7);
		p7.setSynopsis("En fouillant les reserves royales abandonnees, vous decouvrez une fiole doree : un Elixir Royal.");
		p7.AddObjet(elixirRoyal);

		Pieces p8 = new Pieces("LE PONT-LEVIS INTERIEUR", 8);
		p8.setSynopsis("Une silhouette familiale vous barre le passage. C'est Sir Alister, votre ancien frere d'armes.\nForce de servir l'Usurpateur, il tire son epee les larmes aux yeux.");
		Personnage alister = new Personnage("Sir Alister", 5, 25, "Chevalier", 6);
		alister.setCombatStrategy(StrategyFactory.getStrategy("heavy"));
		p8.PNG.add(alister);

		Pieces p9 = new Pieces("L'ANTICHAMBRE DU TRONE", 9);
		p9.setSynopsis("Une piece vide occupied par un miroir magique geant. Il demande la verite sur vos intentions.");

		Pieces p10 = new Pieces("LA SALLE DU TRONE", 10);
		p10.setSynopsis("Ragnaros l'Usurpateur est assis sur votre siege, la Lame de Lumiere a ses cotes.\nC'est l'heure de la vengeance !");
		Personnage ragnaros = new Personnage("Ragnaros l'Usurpateur", 8, 50, "Roi", 10);
		ragnaros.setCombatStrategy(StrategyFactory.getStrategy("heavy"));
		p10.PNG.add(ragnaros);
		p10.AddObjet(lameLumiere);

		// --- AJOUT DES PIÈCES DANS LE MONDE ---
		World monde = new World();
		monde.AddPieces(piece1); // Index 0
		monde.AddPieces(piece2); // Index 1
		monde.AddPieces(p3);     // Index 2
		monde.AddPieces(p4);     // Index 3
		monde.AddPieces(p5);     // Index 4
		monde.AddPieces(p6);     // Index 5
		monde.AddPieces(p7);     // Index 6
		monde.AddPieces(p8);     // Index 7
		monde.AddPieces(p9);     // Index 8
		monde.AddPieces(p10);    // Index 9

		// --- CONFIGURATION DE L'UI ---
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		
		Narration("Le silence regne dans la piece . Avant d'ouvrir les yeux, vous devez vous souvenir ...\n", null);

		Narration("Qui étiez-vous avant la chute?", null);

		Narration("1. un Magicien dote de savoirs et d'arcanes ? ", null);

		Narration("ou", null);

		Narration("2. un Chevalier d'acier et d'honneur ?", null);

		Narration("A vous >", null);

		Narration("[1]Magicien \n [2]Chevalier", null);

		ConsoleObserver consoleObserver = new ConsoleObserver();
		String[] options = { "[1]Magicien", "[2]Chevalier" };

		int choix = JOptionPane.showOptionDialog(null, "Qui etiez-vous avant la chute ?", "Choix de classe",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		Personnage player = null;

		if (choix == 1) { // Chevalier sélectionné
			player = new Personnage("RAGNAR", 2, 8, "Chevalier", 5);
			player.addObserver(consoleObserver);
			Narration("Vous vous souvenez des batailles passees et du froid de l'acier.", player);
			Narration("Votre volonte est votre bouclier et votre bras votre justice.", player);
			Start(player.getspecialite(), player, monde);
		} else if (choix == 0) { // Magicien sélectionné
			player = new Personnage("FLOKI", 1, 5, "Magicien", 2);
			player.addObserver(consoleObserver);
			Narration("Vous vous souvenez des grimoires anciens et de la puissance des astres.", player);
			Narration("Les arcanes coulent dans vos veines, pretes a se dechainer.", player);
			Start(player.getspecialite(), player, monde);
		}
	}

	public static void Narration(String message, Personnage player) {
		JLabel label = new JLabel("<html><div style='text-align: center; width: 350px; color: white;'>" + message.replaceAll("\n", "<br>") + "</div></html>");

		String[] options = { "Continuer", "Inventaire", "Pause" };
		int n = JOptionPane.showOptionDialog(null, label, "Chronique", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (n == 1 && player != null) {
			String inv = "Sac de " + player.getnom() + " :\n";
			if (player.inventaire.inventaire.isEmpty()) {
				inv += "(Votre inventaire est vide)";
			} else {
				for (Objet o : player.inventaire.inventaire) {
					inv += "- " + o.getNom() + " (" + o.getSpecificite() + " : " + o.getValeur() + ")\n";
				}
			}
			JOptionPane.showMessageDialog(null, inv, "Inventaire", JOptionPane.INFORMATION_MESSAGE);
			Narration(message, player); // Relance la narration après fermeture de l'inventaire
		} else if (n == 2) {
			MenuIntermediaire(player);
			Narration(message, player);
		}
	}

	public static void Start(String specialite, Personnage player, World monde) {
		ArrayList<Pieces> pieces = monde.getWorldPieces();

		if (specialite.equals("Chevalier")) {
			// --- PIÈCE 1 ---
			Narration("---------- " + pieces.get(0).getNom() + " ----------", player);
			Narration(pieces.get(0).getSynopsis(), player);
			
			int choixArme = JOptionPane.showConfirmDialog(null, "Vous trouvez une epee rouillee au sol. La ramasser ?", "Objet", JOptionPane.YES_NO_OPTION);
			if (choixArme == JOptionPane.YES_OPTION) {
				player.inventaire.AddObjet(pieces.get(0).getObjets().get(0));
				Narration("Epee ajoutee a votre inventaire.", player);
			}

			String[] choix1 = { "Defoncer la porte à l'epaule", "Fouiller la paille" };
			int action = JOptionPane.showOptionDialog(null, "Que faites-vous ?", "Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix1, choix1[0]);
			
			if (action == 0) {
				Narration("BOOM ! La porte cede sous votre puissance, mais vous perdez 2 PV.", player);
				player.setvie(player.getvie() - 2);
			} else {
				Narration("Dans la paille, vous trouvez une Potion de soin ! Vous forcez ensuite le verrou.", player);
				player.inventaire.AddObjet(pieces.get(0).getObjets().get(1));
				player.setvie(player.getvie() - 2);
			}

			// --- PIÈCE 2 ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(1).getNom() + " ----------", player);
			Narration(pieces.get(1).getSynopsis(), player);
			player.Combat(pieces.get(1).PNG.get(0));
			
			Narration("Vous ramassez le " + pieces.get(1).getObjets().get(0).getNom() + " sur son cadavre.", player);
			player.inventaire.AddObjet(pieces.get(1).getObjets().get(0));

			// --- PIÈCE 3 ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(2).getNom() + " ----------", player);
			Narration(pieces.get(2).getSynopsis(), player);
			player.Combat(pieces.get(2).PNG.get(0));

			// --- PIÈCE 4 (L'ARMURERIE) ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(3).getNom() + " ----------", player);
			Narration(pieces.get(3).getSynopsis(), player);
			player.inventaire.AddObjet(pieces.get(3).getObjets().get(0)); // Armure
			player.setarmure(player.getarmure() + 4);
			Narration("Vous revetez l'Armure Royale. Votre defense augmente !", player);

			// --- PIÈCE 5 (BIBLIOTHÈQUE - ÉNIGME) ---
			Narration("---------- " + pieces.get(4).getNom() + " ----------", player);
			Narration(pieces.get(4).getSynopsis(), player);
			String enigme = JOptionPane.showInputDialog("L'Ermite vous regarde : 'Je n'ai pas de voix et pourtant je parle. Qui suis-je ?'");
			if (enigme != null && enigme.equalsIgnoreCase("echo")) {
				Narration("'Sage reponse.' Vous gagnez 5 PV.", player);
				player.setvie(player.getvie() + 5);
			} else {
				Narration("'Faux !' L'Ermite vous frappe avec son baton. Vous perdez 2 PV.", player);
				player.setvie(player.getvie() - 2);
			}

			// --- PIÈCE 6 (CHAPELLE) ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(5).getNom() + " ----------", player);
			Narration(pieces.get(5).getSynopsis(), player);
			player.Combat(pieces.get(5).PNG.get(0));
			player.inventaire.AddObjet(pieces.get(5).getObjets().get(0));

			// --- PIÈCE 7 (CUISINES) ---
			Narration("---------- " + pieces.get(6).getNom() + " ----------", player);
			Narration(pieces.get(6).getSynopsis(), player);
			player.inventaire.AddObjet(pieces.get(6).getObjets().get(0));

			// --- PIÈCE 8 (PONT-LEVIS) ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(7).getNom() + " ----------", player);
			Narration(pieces.get(7).getSynopsis(), player);
			player.Combat(pieces.get(7).PNG.get(0));

			// --- PIÈCE 9 (ANTICHAMBRE) ---
			Narration("---------- " + pieces.get(8).getNom() + " ----------", player);
			Narration(pieces.get(8).getSynopsis(), player);
			JOptionPane.showInputDialog("Le miroir magique demande : 'Pourquoi te bats-tu ?'");
			Narration("Le miroir valide vos intentions et se brise en eclats.", player);

			// --- PIÈCE 10 (TRÔNE) ---
			if (player.getvie() <= 0) { GameOver(); return; }
			Narration("---------- " + pieces.get(9).getNom() + " ----------", player);
			Narration(pieces.get(9).getSynopsis(), player);
			player.Combat(pieces.get(9).PNG.get(0));

			if (player.getvie() > 0) {
				Narration("Ragnaros est vaincu ! Vous recuperez la " + pieces.get(9).getObjets().get(0).getNom(), player);
				Narration("Le royaume est libere. Longue vie au Roi " + player.getnom() + " !", player);
			} else {
				GameOver();
			}

		} else if (specialite.equals("Magicien")) {
			// --- LOGIQUE UNIQUE DU MAGICIEN ---
			Narration("---------- " + pieces.get(0).getNom() + " ----------", player);
			Narration("L'energie mystique crepite dans vos doigts.", player);
			Narration("Vous utilisez une etincelle magique pour faire fondre le verrou sans effort ! Pas de degats reçus.", player);
			
			// Le magicien enchaîne les pièces différemment ou avec moins de santé physique
			Narration("Vous entrez directement et prudemment dans " + pieces.get(1).getNom(), player);
			player.Combat(pieces.get(1).PNG.get(0));
			
			// Ajoutez ici le déroulement spécifique ou mutualisé pour le Magicien
			Narration("Le chemin du Magicien est complexe, vos sorts vous porteront vers le trone...", player);
		}
	}

	public static void GameOver() {
		JOptionPane.showMessageDialog(null, "Vous etes mort... Votre quete s'arrete ici.", "Game Over", JOptionPane.ERROR_MESSAGE);
	}

	public static void MenuIntermediaire(Personnage player) {
		String[] choix = { "Continuer l'aventure", "Ouvrir l'Inventaire (Soins/Equipement)", "Changer la strategie" };
		int action = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?", "Pause", 0,
				JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

		if (action == 1) {
			if (player.inventaire.inventaire.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Votre inventaire est vide !");
			} else {
				String[] items = new String[player.inventaire.inventaire.size()];
				for (int i = 0; i < player.inventaire.inventaire.size(); i++) {
					items[i] = player.inventaire.inventaire.get(i).getNom();
				}

				int itemChoisi = JOptionPane.showOptionDialog(null, "Choisissez un objet a utiliser :", "Inventaire", 0,
						JOptionPane.PLAIN_MESSAGE, null, items, items[0]);

				if (itemChoisi != -1) {
					Objet o = player.inventaire.inventaire.get(itemChoisi);

					if (o.getSpecificite().toLowerCase().contains("soin")) {
						player.setvie(player.getvie() + o.getValeur());
						JOptionPane.showMessageDialog(null, "Vous utilisez " + o.getNom() + ". + " + o.getValeur() + " PV !");
						player.inventaire.inventaire.remove(itemChoisi);
					} else if (o.getSpecificite().toLowerCase().contains("defense")) {
						player.setarmure(player.getarmure() + o.getValeur());
						JOptionPane.showMessageDialog(null, "Vous equipez " + o.getNom() + ". Armure augmentee !");
						player.inventaire.inventaire.remove(itemChoisi);
					} else {
						JOptionPane.showMessageDialog(null, "Cet objet ne peut pas etre utilise de cette façon.");
					}
				}
			}
			MenuIntermediaire(player); // Boucle le menu tant qu'on ne clique pas sur continuer
		}
		else if (action == 2) {
			String[] stratOptions = { "Attaque rapide", "Coup puissant", "Defensif", "Aleatoire" };
			int s = JOptionPane.showOptionDialog(null, "Choisissez votre strategie de combat :", "Strategie", 0,
				JOptionPane.QUESTION_MESSAGE, null, stratOptions, stratOptions[0]);
			if (s >= 0) {
				String type = "random";
				switch (s) {
				case 0:
					type = "fast";
					break;
				case 1:
					type = "heavy";
					break;
				case 2:
					type = "defensive";
					break;
				case 3:
					type = "random";
					break;
				}
				player.setCombatStrategy(JeuStrategy.StrategyFactory.getStrategy(type));
				JOptionPane.showMessageDialog(null, "Strategie definie : " + stratOptions[s]);
			}
			MenuIntermediaire(player);
		}
	}
}