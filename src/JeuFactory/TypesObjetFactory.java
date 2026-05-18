package JeuFactory;

import java.util.ArrayList;


import Jeu.Personnage;

public class TypesObjetFactory {
	public static TypeObjets getTransport(String type) {
		String nom = null,specificite = null,nomPiece = null;
		int valeur = 0,niveau = 0;
		ArrayList<Objet> objets;
		ArrayList<Personnage> PNG ;
		switch (type) {
		case "Objet":
		return new Objet(nom,specificite,valeur);
		case "Pieces":
		return new Pieces(nomPiece,niveau);
		
		default:
		throw new IllegalArgumentException("Type de transport inconnu : " + type);
		}
		}
	

}
