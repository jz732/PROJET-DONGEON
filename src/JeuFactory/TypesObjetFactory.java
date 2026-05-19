package JeuFactory;

public class TypesObjetFactory {
    
   
    public static TypeObjets creerElement(String type, String nom, String specificite, int valeur) {
        switch (type.trim()) {
            case "Objet":
               
                return  new ObjetFactory(nom, specificite, valeur);
                
            case "Pieces":
            
                return   new PiecesFactory(nom, valeur);
                
            default:
                throw new IllegalArgumentException("Type d'élément de jeu inconnu : " + type);
        }
    }
}