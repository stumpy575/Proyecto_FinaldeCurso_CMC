package proyectoFinal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.net.URL;

public class GUIwelcome extends JFrame implements Variables {
	Planet planet = new Planet(0, 0, PLANET_STARTING_METAL, PLANET_STARTING_DEUTERIUM);
	private JProgressBar progressBar;
	private JPanel attackPanel;
	private JPanel[] enemyPanels = new JPanel[4];
	private JPanel enemigoPanel;
	private JLabel radarLabel;

	public GUIwelcome() {
		setTitle("The Great Space Wars!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1026, 552);
		setResizable(false);
		setLocationRelativeTo(null);
		//        setFocusable(true);

		// Menú barra herramientas
		JMenuBar menuBar = new JMenuBar();
		JMenu optionsmenu = new JMenu("Options");
		menuBar.add(optionsmenu);
		setJMenuBar(menuBar);

		// Panel prinipal
		JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(mainPanel);

		// Panel planeta
		JPanel planetStatsPanel = new JPanel(new BorderLayout(10, 10));
		// Imagen
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("/imagenes/planeta.png")); // TODO que esta ruta sea la buena
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imgplanet = new ImageIcon(img.getScaledInstance(225, 225, Image.SCALE_SMOOTH));

		planetStatsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		JLabel planetImage = new JLabel(imgplanet);
		planetStatsPanel.add(planetImage, BorderLayout.CENTER);

		// Recursos y botones
		try {
			img = ImageIO.read(getClass().getResource("/imagenes/upgrade.png")); // TODO que esta ruta sea la buena
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon arrowup = new ImageIcon(img.getScaledInstance(33, 33, Image.SCALE_SMOOTH));
		JButton boton_upgrade_defense = new JButton(arrowup);
		JButton boton_upgrade_attack = new JButton(arrowup);

		JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		JLabel infometal = new JLabel("Metal: " + planet.getMetal());
		JLabel infodeuterium = new JLabel("Deuterium: " + planet.getDeuterium());

		infoPanel.add(infometal);
		infoPanel.add(new JLabel());
		infoPanel.add(infodeuterium);
		infoPanel.add(new JLabel());

		// Se meten los botones en paneles de flowlayout para que no ocupen todo el grid
		// y que estén bonitos
		JLabel infotechattack = new JLabel("Attack Tech: " + planet.getTechnologyAttack());
		JLabel infotechdefense = new JLabel("Defense Tech: " + planet.getTechnologyDefense());
		infoPanel.add(infotechattack);
		JPanel contattack = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		contattack.add(boton_upgrade_attack);
		infoPanel.add(contattack);
		infoPanel.add(infotechdefense);
		JPanel contdefense = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		contdefense.add(boton_upgrade_defense);
		infoPanel.add(contdefense);
		planetStatsPanel.add(infoPanel, BorderLayout.EAST);

		mainPanel.add(planetStatsPanel);

		// Panel unidades
		JPanel unitsPanel = new JPanel(new BorderLayout());
		unitsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		unitsPanel.setBackground(new Color(200, 200, 255));

		JPanel unitsattackPanel = new JPanel(new GridLayout(2, 4, 5, 5));
		unitsattackPanel.setBackground(new Color(200, 200, 255));

		// Unidades ataque
		ImageIcon imgLightHunter = new ImageIcon(getClass().getResource("../imagenes/lighthunter.png"));
		JLabel labelImgLightHunter = new JLabel(imgLightHunter);
		unitsattackPanel.add(labelImgLightHunter);

		ImageIcon img_heavyhunter = new ImageIcon(getClass().getResource("../imagenes/heavyhunter.png"));
		JLabel labelImgHeavyHunter = new JLabel(img_heavyhunter);
		unitsattackPanel.add(labelImgHeavyHunter);

		ImageIcon img_battleship = new ImageIcon(getClass().getResource("../imagenes/battleship.png"));
		JLabel labelImgBattleship = new JLabel(img_battleship);
		unitsattackPanel.add(labelImgBattleship);

		ImageIcon img_armoredship = new ImageIcon(getClass().getResource("../imagenes/armoredship.png"));
		JLabel labelImgArmoredShip = new JLabel(img_armoredship);
		unitsattackPanel.add(labelImgArmoredShip);

		JLabel datalabel_lighthunter = new JLabel("Light Hunter: " + planet.getArmy()[0].size());
		datalabel_lighthunter.setHorizontalAlignment(SwingConstants.CENTER);
		unitsattackPanel.add(datalabel_lighthunter);

		JLabel datalabel_heavyhunter = new JLabel("Heavy Hunter: " + planet.getArmy()[1].size());
		datalabel_heavyhunter.setHorizontalAlignment(SwingConstants.CENTER);
		unitsattackPanel.add(datalabel_heavyhunter);

		JLabel datalabel_battleship = new JLabel("Battleship: " + planet.getArmy()[2].size());
		datalabel_battleship.setHorizontalAlignment(SwingConstants.CENTER);
		unitsattackPanel.add(datalabel_battleship);

		JLabel datalabel_armoredship = new JLabel("Armored Ship: " + planet.getArmy()[3].size());
		datalabel_armoredship.setHorizontalAlignment(SwingConstants.CENTER);
		unitsattackPanel.add(datalabel_armoredship);

		// Unidades defensa
		JPanel unitsdefensePanel = new JPanel(new GridLayout(2, 3, 5, 5));
		unitsdefensePanel.setBackground(new Color(200, 200, 255));

		ImageIcon img_missilelauncher = new ImageIcon(getClass().getResource("../imagenes/missilelauncher.png"));
		JLabel labelImgMissileLauncher = new JLabel(img_missilelauncher);
		unitsdefensePanel.add(labelImgMissileLauncher);

		ImageIcon img_ioncannon = new ImageIcon(getClass().getResource("../imagenes/ioncannon.png"));
		JLabel labelImgIonCannon = new JLabel(img_ioncannon);
		unitsdefensePanel.add(labelImgIonCannon);

		ImageIcon img_plasmacannon = new ImageIcon(getClass().getResource("../imagenes/plasmacannon.png"));
		JLabel labelImgPlasmaCannon = new JLabel(img_plasmacannon);
		unitsdefensePanel.add(labelImgPlasmaCannon);

        JLabel datalabel_missile = new JLabel("Missile Launcher: "+planet.getArmy()[4].size());
        datalabel_missile.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_missile);

        JLabel datalabel_ion = new JLabel("Ion Cannon: "+planet.getArmy()[5].size());
        datalabel_ion.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_ion);

        JLabel datalabel_plasma = new JLabel("Plasma Cannon: "+planet.getArmy()[6].size());
        datalabel_plasma.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_plasma);

		unitsPanel.add(unitsattackPanel, BorderLayout.NORTH);
		unitsPanel.add(unitsdefensePanel, BorderLayout.SOUTH);
		unitsPanel.add(unitsattackPanel, BorderLayout.NORTH);
		unitsPanel.add(unitsdefensePanel, BorderLayout.SOUTH);
		mainPanel.add(unitsPanel);

		// Panel consola
		JPanel consolePanel = new JPanel(new BorderLayout());
		consolePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JTextArea console = new JTextArea();
		console.setEditable(false);
		console.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		console.setText(">All quiet on the western front, commander...");
		console.setLineWrap(true);
		console.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(console);
		consolePanel.add(scroll, BorderLayout.CENTER);
		mainPanel.add(consolePanel);

		// Panel enemigos
		JPanel attackContainer = new JPanel(new BorderLayout(5, 5));

		// Barra de progreso
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		attackContainer.add(progressBar, BorderLayout.NORTH);

		// panel conteo flota enemiga
		attackPanel = new JPanel(new CardLayout());
		attackPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		attackPanel.setBackground(new Color(255, 200, 200));

		// Panel radar
		radarLabel = new JLabel(new ImageIcon(getClass().getResource("/imagenes/radar.gif")));
		radarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel radarPanel = new JPanel(new BorderLayout());
		radarPanel.setBackground(new Color(255, 200, 200));
		radarPanel.add(radarLabel, BorderLayout.CENTER);
		attackPanel.add(radarPanel, "RADAR");

		// Panel de enemigos vacío (se rellenará más tarde)
		enemigoPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		enemigoPanel.setBackground(new Color(255, 200, 200));
		for (int i = 0; i < 4; i++) {
			enemyPanels[i] = new JPanel();
			enemyPanels[i].setLayout(new BoxLayout(enemyPanels[i], BoxLayout.Y_AXIS));
			enemyPanels[i].setOpaque(false);
			enemigoPanel.add(enemyPanels[i]);
		}
		attackPanel.add(enemigoPanel, "ENEMIGOS");

		attackContainer.add(attackPanel, BorderLayout.CENTER);
		mainPanel.add(attackContainer);

		// Eventos
		boton_upgrade_attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					planet.upgradeTechnologyAttack();
					infotechattack.setText("Attack Tech: " + planet.getTechnologyAttack());
					infodeuterium.setText("Deuterium: " + planet.getDeuterium());

				} catch (ResourceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					console.append("\n>" + e1.getMessage());
				}

			}
		});

		boton_upgrade_defense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					planet.upgradeTechnologyDefense();
					infotechdefense.setText("Defense Tech: " + planet.getTechnologyDefense());
					infodeuterium.setText("Deuterium: " + planet.getDeuterium());

				} catch (ResourceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					console.append("\n>" + e1.getMessage());
				}
			}
		});
		// LIGHT HUNTER
		labelImgLightHunter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Light Hunters do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_LIGTHHUNTER * n;
						int deuteriumCost = DEUTERIUM_COST_LIGTHHUNTER * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newLightHunter(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_lighthunter.setText("Light hunter: " + planet.getArmy()[0].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// HEAVY HUNTER
		labelImgHeavyHunter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Heavy Hunters do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_HEAVYHUNTER * n;
						int deuteriumCost = DEUTERIUM_COST_HEAVYHUNTER * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newHeavyHunter(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_heavyhunter.setText("Heavy hunter: " + planet.getArmy()[1].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// BATTLESHIP
		labelImgBattleship.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Battleships do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_BATTLESHIP * n;
						int deuteriumCost = DEUTERIUM_COST_BATTLESHIP * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newBattleship(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_battleship.setText("Battleship: " + planet.getArmy()[2].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// ARMORED SHIP
		labelImgArmoredShip.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Armored Ships do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_ARMOREDSHIP * n;
						int deuteriumCost = DEUTERIUM_COST_ARMOREDSHIP * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newArmoredShip(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_armoredship.setText("Armored ship: " + planet.getArmy()[3].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// MISSILE LAUNCHER
		labelImgMissileLauncher.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Missile Launchers do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_MISSILELAUNCHER * n;
						int deuteriumCost = DEUTERIUM_COST_MISSILELAUNCHER * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newMissileLauncher(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_missile.setText("Missile Launcher: " + planet.getArmy()[4].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// ION CANNON
		labelImgIonCannon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Ion Cannons do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_IONCANNON * n;
						int deuteriumCost = DEUTERIUM_COST_IONCANNON * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newIonCannon(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_ion.setText("Ion Cannon: " + planet.getArmy()[5].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// PLASMA CANNON
		labelImgPlasmaCannon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String input = JOptionPane.showInputDialog(null, "How many Plasma Cannons do you want to create?");
				if (input != null) {
					try {
						int n = Integer.parseInt(input);
						int metalCost = METAL_COST_PLASMACANNON * n;
						int deuteriumCost = DEUTERIUM_COST_PLASMACANNON * n;

						int opcion = JOptionPane.showConfirmDialog(null,
								"This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
								"Confirm Creation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							planet.newPlasmaCannon(n);
							console.append("\n> " + planet.getLastBuildMessage());
						}

						infodeuterium.setText("Deuterium: " + planet.getDeuterium());
						infometal.setText("Metal: " + planet.getMetal());
						datalabel_plasma.setText("Plasma Cannon: " + planet.getArmy()[6].size());

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});


		int[] clickcount = { 0 };
		planetImage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickcount[0]++;
				if (clickcount[0] == 10) {
					try {
						BufferedImage easterEggImg = ImageIO.read(getClass().getResource("../imagenes/easteregg.png"));
						planetImage
						.setIcon(new ImageIcon(easterEggImg.getScaledInstance(225, 225, Image.SCALE_SMOOTH)));
						console.append("\n>monke");
					} catch (IOException ex) {
						ex.printStackTrace();
						console.append("\n>[!] NO FUNCIONA EL MONKE :((((((((");
					}
				}
			}
		});
		iniciarCuentaAtrasBarra();
	}

	public void iniciarCuentaAtrasBarra() {
		Timer timer = new Timer(100, null);
		final int[] progreso = { 0 };
		final int[] oleada = { 1 };
		final boolean[] radarOculto = { false };
		final boolean[] enemigosCargados = { false };
		CardLayout cl = (CardLayout) attackPanel.getLayout();

		timer.addActionListener(e -> {
			progreso[0]++;
			progressBar.setValue(progreso[0]);
			progressBar.setString(progreso[0] + " %");

			if (progreso[0] >= 50 && !enemigosCargados[0]) {
				cl.show(attackPanel, "ENEMIGOS");

				JOptionPane.showMessageDialog(this, "¡Detectando enemigos!", "Alerta", JOptionPane.WARNING_MESSAGE);

				String[] rutas = { "/imagenes/lighthunter.png", "/imagenes/heavyhunter.png", "/imagenes/battleship.png",
				"/imagenes/armoredship.png" };
				String[] nombres = { "Light Hunter: 30", "Heavy Hunter: 20", "Battleship: 15", "Armored Ship: 10" };

				for (int i = 0; i < 4; i++) {
					enemyPanels[i].removeAll();
					JLabel img = new JLabel(new ImageIcon(getClass().getResource(rutas[i])));
					JLabel txt = new JLabel(nombres[i]);
					img.setAlignmentX(Component.CENTER_ALIGNMENT);
					txt.setAlignmentX(Component.CENTER_ALIGNMENT);
					enemyPanels[i].add(Box.createVerticalGlue());
					enemyPanels[i].add(img);
					enemyPanels[i].add(Box.createVerticalStrut(2));
					enemyPanels[i].add(txt);
					enemyPanels[i].add(Box.createVerticalGlue());
					enemyPanels[i].revalidate();
					enemyPanels[i].repaint();
				}

				enemigosCargados[0] = true;
			}

			// Reiniciar al 100%
			if (progreso[0] >= 100) {
				progreso[0] = 0;
				radarOculto[0] = false;
				enemigosCargados[0] = false;

				for (int i = 0; i < 4; i++) {
					enemyPanels[i].removeAll();
					enemyPanels[i].revalidate();
					enemyPanels[i].repaint();
				}

				cl.show(attackPanel, "RADAR");
				oleada[0]++;
				JOptionPane.showMessageDialog(this, "¡Prepárate para la batalla!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		//        timer.start();
		setVisible(true);
	}

	public static void main(String[] args) {
		new GUIwelcome();
	}
}