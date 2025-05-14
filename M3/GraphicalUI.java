package proyectoFinal;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalUI extends JFrame {
	MainScreen main_screen;
	
	public static void start() {
		GraphicalUI gui = new GraphicalUI();
	}
	
	GraphicalUI(){
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Space Wars");
		
		main_screen = new MainScreen();
		add(main_screen);
		main_screen.setBackground(new Color(17,34,68));
		
		setResizable(false);
		setVisible(true);
	}

}

class MainScreen extends JPanel {
	private Planet planet;
	private String[] battle_stats_logs;
	private String[] battle_development_logs;
	
	MainScreen(){
		super();
	}
	
}
