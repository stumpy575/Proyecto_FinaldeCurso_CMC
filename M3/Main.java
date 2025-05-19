package proyectoFinal;

import java.util.Scanner;

public class Main {
	private static boolean consoleMode;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
		System.out.println("Enable Console Mode? (y/n)");
		if (sc.next().toLowerCase().equals("y")) {
=======
		System.out.println("Enable Graphical Mode? (y/n)");
		if (sc.next().toLowerCase().equals("n")) {
>>>>>>> marc
			consoleMode = true;
			System.out.println("CONSOLE MODE ENABLED\n");
			ConsoleUI console_ui = new ConsoleUI();
			console_ui.start();
		} else {
			consoleMode = false;
			System.out.println("Starting game...");
			GraphicalUI graphical_ui = new GraphicalUI();
<<<<<<< HEAD
			graphical_ui.start();
=======
>>>>>>> marc
		}
	}

	public static boolean isConsoleMode() {
		return consoleMode;
	}
	
}