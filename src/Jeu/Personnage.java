package Jeu;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Observer.GameEvent;
import Observer.GameEventType;
import Observer.Observable;
import Observer.Observer;
import JeuStrategy.CombatStrategy;
import JeuStrategy.RandomStrategy;

public class Personnage implements Observable {
private String nom;
private int armure;
private int vie;
private String specialite;
public Inventaire inventaire;
public int degat;
public int niveau;
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
	// default strategy for NPCs (can be overwritten)
	this.combatStrategy = new RandomStrategy();
	niveau=0;
}

private CombatStrategy combatStrategy;

public void setCombatStrategy(CombatStrategy strategy) {
	this.combatStrategy = strategy;
}

public CombatStrategy getCombatStrategy() {
	return this.combatStrategy;
}

/** Called to let this personnage perform an automated action against target using its strategy */
public void act(Personnage target) {
	if (combatStrategy == null) combatStrategy = new RandomStrategy();
	int action = combatStrategy.chooseAction(this, target);
	// 0 = FastAttack, 1 = HeavyAttack, 2 = Defensive
	if (action == 0) {
		target.subirDegats(this.degat);
	} else if (action == 1) {
		double chance = Math.random();
		if (chance > 0.4) {
			target.subirDegats(this.degat * 2);
		} else {
			// failed heavy attack: suffer counter-damage
			this.subirDegats(target.getDegat() * 2);
		}
	} else {
		// Defensive: reduce incoming damage by half this round (approximate by increasing armure temporarily)
		int oldArmure = this.armure;
		this.armure += 2;
		// no immediate damage dealt; armure will affect subsequent subtractDegats calls
		// We'll revert armure to old value immediately for simplicity (could be improved with State)
		this.armure = oldArmure;
	}
}

//Getters
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
//Setters

public void setnom(String nom) {
	this.nom=nom;
}

public void setspecialite(String specialite) {
	this.specialite=specialite;
}
public void setarmure(int armure) {
	this.armure=armure;
}
public void setvie(int vie) {
	int ancienneVie = this.vie;
	this.vie=vie;
	notifyObservers(new GameEvent(GameEventType.HEALTH_CHANGED, this,
			"Vie modifiée pour " + nom, ancienneVie, this.vie));
	if (this.vie <= 0) {
		notifyObservers(new GameEvent(GameEventType.CHARACTER_DIED, this,
				nom + " est mort", ancienneVie, this.vie));
	}
}
//methode toString
@Override
public String toString() {
 if(vie!=0)
return specialite + nom +"," + "armure : "+armure +", "+"point de vie : "+vie ;	
 
 else 
	 return "Game over";
}

public int getDegat() {
	return degat;
}

public void setDegat(int degat) {
	this.degat = degat;
}

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
	notifyObservers(new GameEvent(GameEventType.COMBAT_STARTED, this,
			"Combat engagé contre " + ennemi.getnom()));
}

public void notifierCombatTermine(Personnage ennemi) {
	notifyObservers(new GameEvent(GameEventType.COMBAT_ENDED, this,
			"Combat terminé contre " + ennemi.getnom(), vie, ennemi.getvie()));
}

//affichage personnage
public void afficherPersonnage(ArrayList<Personnage> listeperso ) {
for(Personnage p : listeperso) {
	if(p.vie!=0)
  System.out.println(p.getspecialite() + p.getnom() +"," + "armure : "+p.getarmure() +", "+"point de vie : "+p.getvie() );
	else
		System.out.println(specialite+" " +nom+" est mort");
}}
public int jetDeDes() {	
	int r= (int) ( Math.random()*(5)+1 );
	return r;
}
public void subirDegats(int degats) {
	int vieAvant = vie;
	if(armure<degats) {
		System.out.println("attaque de :"+degats+"degat(s)");
		vie=vie+armure-degats;
		degats=armure-degats;
		System.out.println(nom+" subit "+degats+"degat(s)");	
	
	}
	else
	System.out.println(nom +" ne subit aucun degat");	
	notifyObservers(new GameEvent(GameEventType.HEALTH_CHANGED, this,
			"Dégâts reçus par " + nom, vieAvant, vie));
	if (vie <= 0) {
		notifyObservers(new GameEvent(GameEventType.CHARACTER_DIED, this,
				nom + " a été vaincu", vieAvant, vie));
	}
}
//fonction isVictory
public boolean isVictory(ArrayList<Personnage> listeperso) {
	int cpt=0;
	for(Personnage p : listeperso) {
		if(p.getvie()==0) {cpt++;}
	}
		if(cpt==listeperso.size())
			return true;
		else
			return false;
}
public void Niveausuivant() {
	niveau=niveau+1;
	
}
public void Combat(Personnage png) {
	notifierCombatCommence(png);
	
	String[] actionsCombat = {"Attaque Rapide", "Coup Puissant", "Parade"};

	// Boucle de combat simplifiée
	while (vie > 0 && png.getvie() > 0) {
	    int coup = JOptionPane.showOptionDialog(null, 
	        "--- COMBAT ---\nVotre Vie : " + vie + " HP\nVie du Garde : " + png.getvie() + " HP\n\nQue faites-vous ?",
	        "Duel dans le Couloir", 0, JOptionPane.WARNING_MESSAGE, null, actionsCombat, actionsCombat[0]);

	    if (coup == 0) { // Attaque Rapide
	        JOptionPane.showMessageDialog(null, "Vous touchez "+"le "+png.getnom()+" avec agilité ! "+degat+" HP)");
	        png.subirDegats(degat);
	    } 
	    else if (coup == 1) { // Coup Puissant
	        // Plus risqué : on peut rater
	        double chance = Math.random();
	        if (chance > 0.4) {
	            JOptionPane.showMessageDialog(null, "Coup dévastateur ! L'armure du garde se fissure. ("+degat+" HP)");
	            png.subirDegats(degat*2);
	        } else {
	            JOptionPane.showMessageDialog(null, "Vous sifflez dans le vide... "+"Le "+png.getnom()+" contre-attaque !");
	            subirDegats(png.getDegat()*2);
	        }
	    }
	    else { // Parade
	        JOptionPane.showMessageDialog(null, "Vous vous protégez derrière vos bras. Le choc est rude mais vous tenez bon. ("+png.getDegat()/2+" HP)");
	        subirDegats(png.getDegat()/2);
	    }

	    // Riposte du garde si il est encore en vie
	    if (png.vie > 0 && vie>0) {
	        Combat(png);
	    }
	}

	// Conclusion de la scène
	if ((png.getvie()<0 ||png.getvie()==0)&& vie>0) {
	    JOptionPane.showMessageDialog(null, "Le "+png.getnom()+" s'effondre dans un bruit métallique.\nVous avez vaincu votre premier ennemi !");
	    // Direction la pièce suivante : L'Armurerie
	} else {
	    JOptionPane.showMessageDialog(null, "Le froid vous envahit... Vous avez échoué.");
	    // Logique de Game Over
	}
	notifierCombatTermine(png);
}
}
