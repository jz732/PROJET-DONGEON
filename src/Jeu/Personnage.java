package Jeu;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import Observer.GameEvent;
import Observer.GameEventType;
import Observer.Observable;
import Observer.Observer;

public class Personnage implements Observable {
	private String nom;
	private int armure;
	private int vie;
	private String specialite;
	public Inventaire inventaire;
	public int degat;
	public int niveau;

	// --- NOUVEAUX ATTRIBUTS D'ÉQUIPEMENT ---
	public Objet armeEquipee;
	public Objet armureEquipee; // Ajouté pour suivre l'armure portée

	private final ArrayList<Observer> observers;

public Personnage(String nom,int armure,int vie,String specialite,int degat) {
	this.armure=armure;
	this.nom=nom;
	this.specialite=specialite;
	this.vie=vie;
	this.inventaire=new Inventaire();
	this.degat=degat;
	
	this.observers=new ArrayList<Observer>();
	this.inventaire.addObserver(new Observer() {
		@Override
		public void update(GameEvent event) {
			notifyObservers(event);
		}
	});
	niveau=0;
}

	// Getters standard
	public String getnom() {
		return nom;
	}

	public String getspecialite() {
		return specialite;
	}

	public int getarmure() {
		return armure;
	}

	public int getvie() {
		return vie;
	}

	public int getDegat() {
		return degat;
	}

	// Setters standard
	public void setnom(String nom) {
		this.nom = nom;
	}

	public void setspecialite(String specialite) {
		this.specialite = specialite;
	}

	public void setarmure(int armure) {
		this.armure = armure;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public void setvie(int vie) {
		int ancienneVie = this.vie;
		this.vie = vie;
		notifyObservers(
				new GameEvent(GameEventType.HEALTH_CHANGED, this, "Vie modifiée pour " + nom, ancienneVie, this.vie));
		if (this.vie <= 0) {
			notifyObservers(
					new GameEvent(GameEventType.CHARACTER_DIED, this, nom + " est mort", ancienneVie, this.vie));
		}
	}

	@Override
	public String toString() {
		if (vie > 0)
			return specialite + " " + nom + ", armure : " + armure + ", point de vie : " + vie;
		else
			return "Game over";
	}

	// --- GESTION DES OBSERVERS ---
	@Override
	public void addObserver(Observer observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(GameEvent event) {
		for (Observer observer : observers) {
			observer.update(event);
		}
	}

	public void notifierCombatCommence(Personnage ennemi) {
		notifyObservers(new GameEvent(GameEventType.COMBAT_STARTED, this, "Combat engagé contre " + ennemi.getnom()));
	}

	public void notifierCombatTermine(Personnage ennemi) {
		notifyObservers(new GameEvent(GameEventType.COMBAT_ENDED, this, "Combat terminé contre " + ennemi.getnom(), vie,
				ennemi.getvie()));
	}

	public void afficherPersonnage(ArrayList<Personnage> listeperso) {
		for (Personnage p : listeperso) {
			if (p.vie > 0)
				System.out.println(p.getspecialite() + " " + p.getnom() + ", armure : " + p.getarmure()
						+ ", point de vie : " + p.getvie());
			else
				System.out.println(p.getspecialite() + " " + p.getnom() + " est mort");
		}
	}

	public int jetDeDes() {
		return (int) (Math.random() * 5 + 1);
	}

	// --- CORRECTION DU BUG DE DÉGATS ---
	public void subirDegats(int degats) {
		int vieAvant = vie;
		if (armure < degats) {
			int degatsNet = degats - armure; // Calcule les dégâts réels traversant l'armure
			System.out.println("Attaque de : " + degats + " dégâts bruts");

			this.vie = this.vie - degatsNet; // Correction ici : On SOUSTRAIT la vie !

			System.out.println(nom + " subit " + degatsNet + " dégât(s) (Armure a bloqué " + armure + ")");
		} else {
			System.out.println(nom + " ne subit aucun dégât (Armure trop solide !)");
		}

		notifyObservers(new GameEvent(GameEventType.HEALTH_CHANGED, this, "Dégâts reçus par " + nom, vieAvant, vie));
		if (vie <= 0) {
			notifyObservers(new GameEvent(GameEventType.CHARACTER_DIED, this, nom + " a été vaincu", vieAvant, vie));
		}
	}

	public boolean isVictory(ArrayList<Personnage> listeperso) {
		int cpt = 0;
		for (Personnage p : listeperso) {
			if (p.getvie() <= 0) {
				cpt++;
			}
		}
		return cpt == listeperso.size();
	}

	public void Niveausuivant() {
		niveau = niveau + 1;
	}

	// --- LOGIQUE DE COMBAT ET D'ÉQUIPEMENT AMÉLIORÉE ---
	public void Combat(Personnage png) {
		notifierCombatCommence(png);
		String[] actionsCombat = { "Attaque Rapide", "Coup Puissant", "Parade", "Ouvrir l'Inventaire" };

		while (this.vie > 0 && png.getvie() > 0) {
			int coup = JOptionPane.showOptionDialog(null,
					"--- COMBAT ---\nVotre Vie : " + this.vie + " HP | Armure effective : " + this.armure
							+ " | Force : " + this.degat + "\nVie de l'ennemi (" + png.getnom() + ") : " + png.getvie()
							+ " HP\n\nQue faites-vous ?",
					"Duel en cours", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, actionsCombat,
					actionsCombat[0]);

			if (coup == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(null, "Vous prenez la fuite lâchement !");
				break;
			}

			boolean tourConsomme = true;

			if (coup == 0) { // Attaque Rapide
				JOptionPane.showMessageDialog(null,
						"Vous touchez le " + png.getnom() + " avec agilité ! (" + this.degat + " dégâts)");
				png.subirDegats(this.degat);
			} else if (coup == 1) { // Coup Puissant
				if (Math.random() > 0.4) {
					JOptionPane.showMessageDialog(null, "Coup dévastateur ! (" + (this.degat * 2) + " dégâts)");
					png.subirDegats(this.degat * 2);
				} else {
					JOptionPane.showMessageDialog(null,
							"Vous sifflez dans le vide... Le " + png.getnom() + " vous contre !");
					this.subirDegats(png.getDegat() * 2);
					tourConsomme = false;
				}
			} else if (coup == 2) { // Parade
				JOptionPane.showMessageDialog(null, "Vous vous protégez. Le choc est atténué.");
				this.subirDegats(png.getDegat() / 2);
				tourConsomme = false;
			} else if (coup == 3) { // Ouvrir l'Inventaire
				if (this.inventaire.inventaire.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Votre inventaire est vide !");
					tourConsomme = false;
				} else {
					String[] items = new String[this.inventaire.inventaire.size()];
					for (int i = 0; i < this.inventaire.inventaire.size(); i++) {
						Objet obj = this.inventaire.inventaire.get(i);
						items[i] = obj.getNom() + " [" + obj.getSpecificite() + " +" + obj.getValeur() + "]";
					}

					int itemChoisi = JOptionPane.showOptionDialog(null, "Choisissez un objet à utiliser/équiper :",
							"Inventaire en Combat", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, items,
							items[0]);

					if (itemChoisi != JOptionPane.CLOSED_OPTION) {
						Objet o = this.inventaire.inventaire.get(itemChoisi);
						String spec = o.getSpecificite().toLowerCase().trim();

						// 1. UTILISATION : SOIN
						if (spec.contains("soin")) {
							this.setvie(this.getvie() + o.getValeur());
							JOptionPane.showMessageDialog(null,
									"Vous consommez " + o.getNom() + ". + " + o.getValeur() + " PV !");
							this.inventaire.inventaire.remove(itemChoisi);
						}
						// 2. ÉQUIPEMENT : ARMURE / DÉFENSE
						else if (spec.contains("defense") || spec.contains("armure")) {
							// On retire l'ancienne si présente
							if (this.armureEquipee != null) {
								this.armure -= this.armureEquipee.getValeur();
								this.inventaire.AddObjet(this.armureEquipee);
								JOptionPane.showMessageDialog(null, "Vous retirez : " + this.armureEquipee.getNom());
							}
							// On équipe la nouvelle
							this.armureEquipee = o;
							this.armure += o.getValeur();
							this.inventaire.inventaire.remove(itemChoisi);
							JOptionPane.showMessageDialog(null,
									"Vous équipez : " + o.getNom() + " (Armure +" + o.getValeur() + ")");
						}
						// 3. ÉQUIPEMENT : ARME / ATTAQUE
						else if (spec.contains("attaque")) {
							// On retire l'ancienne si présente
							if (this.armeEquipee != null) {
								this.degat -= this.armeEquipee.getValeur();
								this.inventaire.AddObjet(this.armeEquipee);
								JOptionPane.showMessageDialog(null, "Vous rangez : " + this.armeEquipee.getNom());
							}
							// On équipe la nouvelle
							this.armeEquipee = o;
							this.degat += o.getValeur();
							this.inventaire.inventaire.remove(itemChoisi);
							JOptionPane.showMessageDialog(null,
									"Vous brandissez : " + o.getNom() + " (Dégâts +" + o.getValeur() + ")");
						} else {
							JOptionPane.showMessageDialog(null, "Cet objet ne peut pas être utile en combat !");
							tourConsomme = false;
						}
					} else {
						tourConsomme = false;
					}
				}
			}

			// --- RIPOSTE DE L'ENNEMI ---
			if (png.getvie() > 0 && tourConsomme) {
				JOptionPane.showMessageDialog(null, "Le " + png.getnom() + " contre-attaque !");
				this.subirDegats(png.getDegat());
			}
		}

		// Fin du combat
		if (this.vie <= 0) {
			JOptionPane.showMessageDialog(null, "Vous avez été vaincu...");
		} else if (png.getvie() <= 0) {
			JOptionPane.showMessageDialog(null, "Victoire ! Vous avez terrassé le " + png.getnom() + " !");
			notifierCombatTermine(png);
		}
	}
}