package JeuFacade;

import Jeu.Main;
import Jeu.Personnage;
import Jeu.World;

public class GameFacade {
    private Personnage player;
    private World monde;

    public GameFacade(Personnage player, World monde) {
        this.player = player;
        this.monde = monde;
    }

    // C'est cette méthode unique qui sert de Façade !
    public void demarrerLHistoire(String classeChoisie) {
        // Elle appelle la plomberie complexe qui est restée dans le Main
        Main.Start(classeChoisie, this.player, this.monde);
    }
}