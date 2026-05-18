package Jeu;
import java.util.ArrayList;

import Observer.GameEvent;
import Observer.GameEventType;
import Observer.Observable;
import Observer.Observer;

public class Inventaire implements Observable {
public  ArrayList<Objet> inventaire;
private final ArrayList<Observer> observers;

public Inventaire() {
	inventaire= new ArrayList<Objet>();
	observers = new ArrayList<Observer>();
}
public void AddObjet(Objet objet) {
	inventaire.add(objet);
	notifyObservers(new GameEvent(GameEventType.ITEM_ADDED, this,
			"Objet ajouté à l'inventaire : " + objet.getNom()));
	
	}

public void RemoveObjet(Objet objet) {
	if (inventaire.remove(objet)) {
		notifyObservers(new GameEvent(GameEventType.ITEM_USED, this,
				"Objet retiré de l'inventaire : " + objet.getNom()));
	}

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

public void AffichageInventaire() { 
if(inventaire==null)
	System.out.println("vide");
for(Objet o : inventaire)
	o.toString();
}


}
