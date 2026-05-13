package Jeu;
import java.util.ArrayList;

public class World {
public ArrayList<Pieces> worldPieces;
public World() {
	worldPieces=new ArrayList<Pieces>();
}
public void AddPieces(Pieces piece) {
	worldPieces.add(piece);
}
public ArrayList<Pieces> getWorldPieces() {
	return worldPieces;
}

}
