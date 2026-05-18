package Observer;

import javax.swing.JOptionPane;

public class ConsoleObserver implements Observer {

	@Override
	public void update(GameEvent event) {
		String sourceName = event.getSource() == null ? "Source inconnue" : event.getSource().getClass().getSimpleName();
		String message = "[" + event.getType() + "] " + sourceName + " - " + event.getMessage();
		System.out.println(message);
		JOptionPane.showMessageDialog(null, message, "Notification jeu", JOptionPane.INFORMATION_MESSAGE);
	}
}
