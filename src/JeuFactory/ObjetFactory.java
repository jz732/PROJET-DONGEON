package JeuFactory;

public class ObjetFactory implements TypeObjets {
    private String nom;
    private String specificite;
    private int valeur;

    public ObjetFactory(String nom, String specificite, int valeur) {
        this.nom = nom;
        this.specificite = specificite;
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecificite() {
        return specificite;
    }

    public int getValeur() {
        return valeur;
    }

    @Override
    public String decrire() {
        return "Objet: " + nom + " [" + specificite + " +" + valeur + "]";
    }

    @Override
    public String toString() {
        return decrire();
    }
}