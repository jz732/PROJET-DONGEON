package JeuState;

public interface EtatPersonnage {
	// Modifie les dégâts infligés par le personnage
    int appliquerModificateurAttaque(int degatsDeBase);
    
    // Modifie les dégâts subis par le personnage
    int appliquerModificateurDefense(int degatsSubis, int armure);
    
    // Actions à exécuter au début ou à la fin d'un tour (ex: dégâts de poison)
    void surFinDeTour(Personnage personnage);
    
    // Renvoie le nom de l'état pour l'affichage
    String getNomEtat();

}
