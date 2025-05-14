package proyectoFinal;

import java.util.Scanner;

public class Main {
	private static boolean consoleMode;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enable Console Mode? (y/n)");
		if (sc.next().toLowerCase().equals("y")) {
			consoleMode = true;
			System.out.println("CONSOLE MODE ENABLED\n");
			ConsoleUI console_ui = new ConsoleUI();
			console_ui.start();
		} else {
			consoleMode = false;
			System.out.println("Starting game...");
			GraphicalUI.start();
		}
	}

	public static boolean isConsoleMode() {
		return consoleMode;
	}
	
}