package JeuFactory;

import java.util.ArrayList;
import Jeu.Personnage;



public class Pieces implements TypeObjets {
public String nom;
public int niveau;
public ArrayList<Objet> objets;
public ArrayList<Personnage> PNG ;
public Pieces(String nom, int niveau) {
	this.nom = nom;
	this.niveau = niveau;
	this.objets= new ArrayList<Objet>();
	PNG = new ArrayList<>();
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public int getNiveau() {
	return niveau;
}
public void setNiveau(int niveau) {
	this.niveau = niveau;
}
public void AddObjet(Objet objet) {
objets.add(objet);
}
public ArrayList<Objet> getObjets() {
	return objets;
}

@Override
public String decrire() {
	System.out.print("Je contiens des objets");
	return nom;

}

}
