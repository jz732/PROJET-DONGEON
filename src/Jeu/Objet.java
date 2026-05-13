package Jeu;
public class Objet {
public String nom;
public String Specificite;
public int valeur;
public String getNom() {
	return nom;
}

public Objet(String nom, String specificite, int valeur) {
	this.nom = nom;
	Specificite = specificite;
	this.valeur = valeur;
}

public void setNom(String nom) {
	this.nom = nom;
}
public String getSpecificite() {
	return Specificite;
}
public void setSpecificite(String specificite) {
	Specificite = specificite;
}
public int getValeur() {
	return valeur;
}
public void setValeur(int valeur) {
	this.valeur = valeur;
}

@Override
public String toString() {
	return "Objet [nom=" + nom + " (" + Specificite +")" + "  valeur=" + valeur + "]";
}

}
