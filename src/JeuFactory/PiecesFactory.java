package JeuFactory;

import java.util.ArrayList;
import Jeu.Personnage;

public class PiecesFactory implements TypeObjets {
    private String nom;
    private int niveau;
    private ArrayList<ObjetFactory> objets;
    private ArrayList<Personnage> PNG; 
    private String synopsis;

    public PiecesFactory(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
        this.objets = new ArrayList<>();
        this.PNG = new ArrayList<>();
        this.synopsis=null;
    }


    public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void addObjet(ObjetFactory objet) {
        this.objets.add(objet);
    }

    public ArrayList<ObjetFactory> getObjets() {
        return objets;
    }

    public ArrayList<Personnage> getPNG() {
        return PNG;
    }

    @Override
    public String decrire() {
        return "Pièce: " + nom + " (Niveau " + niveau + ") contenant " + objets.size() + " objet(s).";
    }
}