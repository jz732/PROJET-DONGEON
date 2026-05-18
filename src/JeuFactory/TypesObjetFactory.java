package JeuFactory;

public class TypesObjetFactory {
    
    // On passe les caractéristiques en paramètres pour ne plus avoir de valeurs 'null'
    public static TypeObjets creerElement(String type, String nom, String specificite, int valeur) {
        switch (type.trim()) {
            case "Objet":
                // Ici, optionnel = specificite (ex: "Soin", "Attaque")
                return  new ObjetFactory(nom, specificite, valeur);
                
            case "Pieces":
                // Ici, valeur = niveau de la pièce
                return   new PiecesFactory(nom, valeur);
                
            default:
                throw new IllegalArgumentException("Type d'élément de jeu inconnu : " + type);
        }
    }
}