import java.util.ArrayList;

public class Inventaire {
public  ArrayList<Objet> inventaire;

public Inventaire() {
	inventaire= new ArrayList<Objet>();
}
public void AddObjet(Objet objet) {
	inventaire.add(objet);
	
}

public void AffichageInventaire() { 
if(inventaire==null)
	System.out.println("vide");
for(Objet o : inventaire)
	o.toString();
}


}
