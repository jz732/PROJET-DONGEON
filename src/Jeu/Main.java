package Jeu;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import JeuSingleton.GameManager;

import java.awt.Color;

import Observer.ConsoleObserver;

public class Main {

	public static void main(String[] args) {
		// --- CRÉATION DES OBJETS EN JEU ---

		Objet arme = new Objet("Epee rouillee", "Attaque", 2);
		Objet potion = new Objet("Potion de soin mineur", "soin", 2);
		Objet bouclierBois = new Objet("Bouclier de bois", "Defense", 2);
		Objet dagueGarde = new Objet("Dague de garde", "Attaque", 4);
		Objet armureCapitaine = new Objet("Armure de capitaine", "Defense", 4);
		Objet amuletteProt = new Objet("Amulette de protection", "Defense", 5);
		Objet elixirRoyal = new Objet("Elixir Royal", "Soin", 20);
		Objet lameLumiere = new Objet("Lame de Lumiere", "Attaque", 10);

		// --- CRÉATION DES PIÈCES ---
		Pieces piece1 = new Pieces("LA CELLULE DE PIERRE", 1);
		piece1.setSynopsis(
				"Le froid de la pierre vous rappelle la trahison de Ragnaros. Vous reprenez conscience dans une cellule humide.\nLa lourde porte en bois devant vous est verrouillee.\nVous devez fouiller la paille sale pour trouver la clef de fer ou prendre le risque de briser le verrou.");
		piece1.AddObjet(arme);
		piece1.AddObjet(potion);

		Pieces piece2 = new Pieces("LE COULOIR SOMBRE", 2);
		piece2.setSynopsis(
				"Le verrou a saute. Vous glissez prudemment dans le couloir sombre du donjon.\nMalheureusement, le grincement a alerte le Garde Ivre.\nLe battre est votre seule chance de continuer.");
		piece2.PNG.add(new Personnage("Garde Ivre", 1, 8, "Guerrier", 2));
		piece2.AddObjet(bouclierBois);

		Pieces p3 = new Pieces("LA SALLE DES TORTURES", 3);
		p3.setSynopsis(
				"L'odeur de fer et de sang vous souleve le coeur. Le Bourreau est la, brandissant sa hache.\nSi vous le terrassez, un soldat fidele aura peut-etre le temps de vous reveler un secret.");
		p3.PNG.add(new Personnage("Le Bourreau", 2, 15, "Boss", 4));

		Pieces p4 = new Pieces("L'ARMURERIE ROYALE", 4);
		p4.setSynopsis(
				"Vous atteignez l'Armurerie Royale. C'est une mine d'or.\nRagnaros y a expose votre ancienne Armure de Capitaine et votre Dague.");
		p4.AddObjet(armureCapitaine);
		p4.AddObjet(dagueGarde);

		Pieces p5 = new Pieces("LA GRANDE BIBLIOTHEQUE", 5);
		p5.setSynopsis(
				"Au milieu des milliers de livres, l'Ermite vous attend de pied ferme.\nCe vieux sage refuse de vous laisser monter sans tester votre esprit.");
		p5.PNG.add(new Personnage("L'Ermite", 0, 100, "Sage", 0));

		Pieces p6 = new Pieces("LA CHAPELLE MAUDITE", 6);
		p6.setSynopsis(
				"L'air devient glacial. L'autel a ete profane par le Pretre Dechu.\nVous devez briser sa malediction.");
		p6.PNG.add(new Personnage("Pretre Dechu", 1, 20, "Mage", 5));
		p6.AddObjet(amuletteProt);

		Pieces p7 = new Pieces("LES CUISINES DESERTES", 7);
		p7.setSynopsis(
				"En fouillant les reserves royales abandonnees, vous decouvrez une fiole doree : un Elixir Royal.");
		p7.AddObjet(elixirRoyal);

		Pieces p8 = new Pieces("LE PONT-LEVIS INTERIEUR", 8);
		p8.setSynopsis(
				"Une silhouette familiale vous barre le passage. C'est Sir Alister, votre ancien frere d'armes.\nForce de servir l'Usurpateur, il tire son epee les larmes aux yeux.");
		p8.PNG.add(new Personnage("Sir Alister", 5, 25, "Chevalier", 6));

		Pieces p9 = new Pieces("L'ANTICHAMBRE DU TRONE", 9);
		p9.setSynopsis("Une piece vide occupied par un miroir magique geant. Il demande la verite sur vos intentions.");

		Pieces p10 = new Pieces("LA SALLE DU TRONE", 10);
		p10.setSynopsis(
				"Ragnaros l'Usurpateur est assis sur votre siege, la Lame de Lumiere a ses cotes.\nC'est l'heure de la vengeance !");
		p10.PNG.add(new Personnage("Ragnaros l'Usurpateur", 8, 50, "Roi", 10));
		p10.AddObjet(lameLumiere);

		// --- AJOUT DES PIÈCES DANS LE MONDE ---
		World monde = new World();
		monde.AddPieces(piece1); // Index 0
		monde.AddPieces(piece2); // Index 1
		monde.AddPieces(p3); // Index 2
		monde.AddPieces(p4); // Index 3
		monde.AddPieces(p5); // Index 4
		monde.AddPieces(p6); // Index 5
		monde.AddPieces(p7); // Index 6
		monde.AddPieces(p8); // Index 7
		monde.AddPieces(p9); // Index 8
		monde.AddPieces(p10); // Index 9

		// --- CONFIGURATION DE L'UI ---
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		GameManager.getInstance().setPieceActuelleIndex(0); // Synchronise le Singleton
		Narration("Le silence regne dans la piece . Avant d'ouvrir les yeux, vous devez vous souvenir ...\n", null);
		Narration("Qui étiez-vous avant la chute?", null);

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
		JLabel label = new JLabel("<html><div style='text-align: center; width: 350px; color: white;'>"
				+ message.replaceAll("\n", "<br>") + "</div></html>");

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
			Narration(message, player);
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

			int choixArme = JOptionPane.showConfirmDialog(null, "Vous trouvez une epee rouillee au sol. La ramasser ?",
					"Objet", JOptionPane.YES_NO_OPTION);
			if (choixArme == JOptionPane.YES_OPTION) {
				player.inventaire.AddObjet(pieces.get(0).getObjets().get(0));
				Narration("Epee ajoutee a votre inventaire.", player);
			}

			String[] choix1 = { "Defoncer la porte à l'epaule", "Fouiller la paille" };
			int action = JOptionPane.showOptionDialog(null, "Que faites-vous ?", "Action", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, choix1, choix1[0]);
			GameManager.getInstance().setPieceActuelleIndex(1);
			if (action == 0) {
				Narration("BOOM ! La porte cede sous votre puissance, mais vous perdez 2 PV.", player);
				player.setvie(player.getvie() - 2);
			} else {
				Narration("Dans la paille, vous trouvez une Potion de soin ! Vous forcez ensuite le verrou.", player);
				player.inventaire.AddObjet(pieces.get(0).getObjets().get(1));
				player.setvie(player.getvie() - 2);
			}
			checkDeath(player); // Vérification après dégâts environnementaux

			// --- PIÈCE 2 ---
			Narration("---------- " + pieces.get(1).getNom() + " ----------", player);
			Narration(pieces.get(1).getSynopsis(), player);
			player.Combat(pieces.get(1).PNG.get(0));
			checkDeath(player); // Vérification après combat

			Narration("Vous ramassez le " + pieces.get(1).getObjets().get(0).getNom() + " sur son cadavre.", player);
			player.inventaire.AddObjet(pieces.get(1).getObjets().get(0));

			// --- PIÈCE 3 ---
			GameManager.getInstance().setPieceActuelleIndex(2);
			Narration("---------- " + pieces.get(2).getNom() + " ----------", player);
			Narration(pieces.get(2).getSynopsis(), player);
			player.Combat(pieces.get(2).PNG.get(0));
			checkDeath(player);

			// --- PIÈCE 4 (L'ARMURERIE) ---
			GameManager.getInstance().setPieceActuelleIndex(3);
			Narration("---------- " + pieces.get(3).getNom() + " ----------", player);
			Narration(pieces.get(3).getSynopsis(), player);
			player.inventaire.AddObjet(pieces.get(3).getObjets().get(0));
			player.setarmure(player.getarmure() + 4);
			Narration("Vous revetez l'Armure Royale. Votre defense augmente !", player);

			// --- PIÈCE 5 (BIBLIOTHÈQUE - ÉNIGME) ---
			GameManager.getInstance().setPieceActuelleIndex(4);
			Narration("---------- " + pieces.get(4).getNom() + " ----------", player);
			Narration(pieces.get(4).getSynopsis(), player);
			String enigme = JOptionPane.showInputDialog(
					"L'Ermite vous regarde : 'Je n'ai pas de voix et pourtant je parle. Qui suis-je ?'");
			if (enigme != null && enigme.equalsIgnoreCase("echo")) {
				Narration("'Sage reponse.' Vous gagnez 5 PV.", player);
				player.setvie(player.getvie() + 5);
			} else {
				Narration("'Faux !' L'Ermite vous frappe avec son baton. Vous perdez 2 PV.", player);
				player.setvie(player.getvie() - 2);
			}
			checkDeath(player);

			// --- PIÈCE 6 (CHAPELLE) ---
			GameManager.getInstance().setPieceActuelleIndex(5);
			Narration("---------- " + pieces.get(5).getNom() + " ----------", player);
			Narration(pieces.get(5).getSynopsis(), player);
			player.Combat(pieces.get(5).PNG.get(0));
			checkDeath(player);
			player.inventaire.AddObjet(pieces.get(5).getObjets().get(0));

			// --- PIÈCE 7 (CUISINES) ---
			GameManager.getInstance().setPieceActuelleIndex(6);
			Narration("---------- " + pieces.get(6).getNom() + " ----------", player);
			Narration(pieces.get(6).getSynopsis(), player);
			player.inventaire.AddObjet(pieces.get(6).getObjets().get(0));

			// --- PIÈCE 8 (PONT-LEVIS) ---
			GameManager.getInstance().setPieceActuelleIndex(7);
			Narration("---------- " + pieces.get(7).getNom() + " ----------", player);
			Narration(pieces.get(7).getSynopsis(), player);
			player.Combat(pieces.get(7).PNG.get(0));
			checkDeath(player);

			// --- PIÈCE 9 (ANTICHAMBRE) ---
			GameManager.getInstance().setPieceActuelleIndex(8);
			Narration("---------- " + pieces.get(8).getNom() + " ----------", player);
			Narration(pieces.get(8).getSynopsis(), player);
			JOptionPane.showInputDialog("Le miroir magique demande : 'Pourquoi te bats-tu ?'");
			Narration("Le miroir valide vos intentions et se brise en eclats.", player);

			// --- PIÈCE 10 (TRÔNE) ---
			GameManager.getInstance().setPieceActuelleIndex(9);
			Narration("---------- " + pieces.get(9).getNom() + " ----------", player);
			Narration(pieces.get(9).getSynopsis(), player);
			player.Combat(pieces.get(9).PNG.get(0));
			checkDeath(player);

			Narration("Ragnaros est vaincu ! Vous recuperez la " + pieces.get(9).getObjets().get(0).getNom(), player);
			Narration("Le royaume est libere. Longue vie au Roi " + player.getnom() + " !", player);

		} else if (specialite.equals("Magicien")) {
			// --- LOGIQUE UNIQUE DU MAGICIEN ---
			Narration("---------- " + pieces.get(0).getNom() + " ----------", player);
			Narration("L'energie mystique crepite dans vos doigts.", player);
			Narration(
					"Vous utilisez une etincelle magique pour faire fondre le verrou sans effort ! Pas de degats reçus.",
					player);

			Narration("Vous entrez directement et prudemment dans " + pieces.get(1).getNom(), player);
			player.Combat(pieces.get(1).PNG.get(0));
			checkDeath(player);

			Narration("Le chemin du Magicien est complexe, vos sorts vous porteront vers le trone...", player);
		}
	}

	// Centralise la vérification de santé pour éviter la répétition de code

	public static void checkDeath(Personnage player) {
		if (player.getvie() <= 0) {
			GameOver();
		}
	}

	public static void GameOver() {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);

		JLabel label = new JLabel(
				"<html><div style='text-align: center; color: red; font-size: 16px; font-weight: bold;'>"
						+ "☠️ GAME OVER ☠️<br><br><span style='color: white; font-size: 12px;'>Vous etes mort... Votre quete s'arrete ici.</span></div></html>");

		JOptionPane.showMessageDialog(null, label, "Fin de partie", JOptionPane.PLAIN_MESSAGE);
		System.exit(0); // Coupe net l'application pour empêcher l'exécution des dialogues restants
	}

	public static void MenuIntermediaire(Personnage player) {
		// Ajout des options de sauvegarde dans le menu
		String[] choix = { "Continuer l'aventure", "Ouvrir l'Inventaire", "Sauvegarder la partie",
				"Charger la partie" };
		int action = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?", "Pause / Gestion du Jeu", 0,
				JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

		if (action == 1) { // --- INVENTAIRE ---
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
					String spec = o.getSpecificite().toLowerCase().trim();

					if (spec.contains("soin")) {
						player.setvie(player.getvie() + o.getValeur());
						JOptionPane.showMessageDialog(null,
								"Vous utilisez " + o.getNom() + ". + " + o.getValeur() + " PV !");
						player.inventaire.inventaire.remove(itemChoisi);
					} else if (spec.contains("defense") || spec.contains("armure")) {
						if (player.armureEquipee != null) {
							player.setarmure(player.getarmure() - player.armureEquipee.getValeur());
							player.inventaire.inventaire.add(player.armureEquipee);
						}
						player.armureEquipee = o;
						player.setarmure(player.getarmure() + o.getValeur());
						player.inventaire.inventaire.remove(itemChoisi);
						JOptionPane.showMessageDialog(null, "Vous equipez " + o.getNom() + ". Armure augmentee !");
					} else if (spec.contains("attaque")) {
						if (player.armeEquipee != null) {
							player.setDegat(player.getDegat() - player.armeEquipee.getValeur());
							player.inventaire.inventaire.add(player.armeEquipee);
						}
						player.armeEquipee = o;
						player.setDegat(player.getDegat() + o.getValeur());
						player.inventaire.inventaire.remove(itemChoisi);
						JOptionPane.showMessageDialog(null, "Vous brandissez " + o.getNom() + ". Attaque augmentee !");
					} else {
						JOptionPane.showMessageDialog(null, "Cet objet ne peut pas etre utilise de cette façon.");
					}
					checkDeath(player);
				}
			}
			MenuIntermediaire(player);
		} else if (action == 2) { // SAUVEGARDER (Appel Singleton)
			GameManager.getInstance().sauvegarderPartie(player);
			MenuIntermediaire(player); // Reste dans le menu après action
		} else if (action == 3) { // CHARGER (Appel Singleton)
			GameManager.getInstance().chargerPartie(player);
			// Note : Dans une structure séquentielle pure (liée à des lignes de code
			// fixes),
			// le chargement rechargera tes stats mais ne te téléportera pas magiquement à
			// la ligne de code de la pièce.
			// Pour cela, il faudra utiliser la boucle Façade ou un switch/case basé sur
			// GameManager.getInstance().getPieceActuelleIndex().
			MenuIntermediaire(player);
		}
	}
}