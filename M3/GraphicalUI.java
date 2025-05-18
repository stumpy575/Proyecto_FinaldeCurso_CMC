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
import java.io.IOException;
import java.net.URL;

public class GraphicalUI extends JFrame implements Variables {
	Game game;
    private JProgressBar progressBar;
    private JPanel attackPanel;
    private JPanel[] enemyPanels = new JPanel[4];
    private JPanel enemigoPanel;
    private JLabel radarLabel;
    
    private JLabel infometal;
    private JLabel infodeuterium;
    private JLabel datalabel_lighthunter;
    private JLabel datalabel_heavyhunter;
    private JLabel datalabel_armoredship;
    private JLabel datalabel_battleship;
    private JLabel datalabel_missilelauncher;
    private JLabel datalabel_ioncannon;
    private JLabel datalabel_plasmacannon;
    private JLabel[] datalabel_units = {datalabel_lighthunter, datalabel_heavyhunter, datalabel_armoredship, datalabel_battleship, datalabel_missilelauncher, datalabel_ioncannon, datalabel_plasmacannon};

    public GraphicalUI() {
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

        // Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(mainPanel);

        // Panel planeta
        JPanel planetStatsPanel = new JPanel(new BorderLayout(10, 10));
        //Imagen
        BufferedImage img=null;
        try {
            img = ImageIO.read(getClass().getResource("/imagenes/planeta.png")); //TODO que esta ruta sea la buena
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imgplanet = new ImageIcon(img.getScaledInstance(225, 225, Image.SCALE_SMOOTH));      
    
        planetStatsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel planetImage = new JLabel(imgplanet);
        planetStatsPanel.add(planetImage, BorderLayout.CENTER);

        //Recursos y botones
        try {
            img = ImageIO.read(getClass().getResource("/imagenes/upgrade.png")); //TODO que esta ruta sea la buena
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon arrowup = new ImageIcon(img.getScaledInstance(33, 33, Image.SCALE_SMOOTH));        
        JButton boton_upgrade_defense = new JButton(arrowup);
        JButton boton_upgrade_attack = new JButton(arrowup);
        
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        infometal = new JLabel("Metal: "+game.getPlanet().getMetal());
        infodeuterium = new JLabel("Deuterium: " + game.getPlanet().getDeuterium());

        infoPanel.add(infometal);
        infoPanel.add(new JLabel());
        infoPanel.add(infodeuterium);
        infoPanel.add(new JLabel());
        
        //Se meten los botones en paneles de flowlayout para que no ocupen todo el grid y que estén bonitos
        JLabel infotechattack= new JLabel("Attack Tech: " + game.getPlanet().getTechnologyAttack());
        JLabel infotechdefense= new JLabel("Defense Tech: " + game.getPlanet().getTechnologyDefense());
        infoPanel.add(infotechattack);
        JPanel contattack = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contattack.add(boton_upgrade_attack);
        infoPanel.add(contattack);
        infoPanel.add(infotechdefense);
        JPanel contdefense = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contdefense.add( boton_upgrade_defense);
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

 
        datalabel_lighthunter = new JLabel("Light Hunter: "+game.getPlanet().getArmy()[0].size());
        datalabel_lighthunter.setHorizontalAlignment(SwingConstants.CENTER);
        unitsattackPanel.add(datalabel_lighthunter);

        datalabel_heavyhunter = new JLabel("Heavy Hunter: "+game.getPlanet().getArmy()[1].size());
        datalabel_heavyhunter.setHorizontalAlignment(SwingConstants.CENTER);
        unitsattackPanel.add(datalabel_heavyhunter);

        datalabel_battleship = new JLabel("Battleship: "+game.getPlanet().getArmy()[2].size());
        datalabel_battleship.setHorizontalAlignment(SwingConstants.CENTER);
        unitsattackPanel.add(datalabel_battleship);

        datalabel_armoredship= new JLabel("Armored Ship: "+game.getPlanet().getArmy()[3].size());
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
        
        datalabel_missilelauncher = new JLabel("Missile Launcher: "+game.getPlanet().getArmy()[4].size());
        datalabel_missilelauncher.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_missilelauncher);

        datalabel_ioncannon = new JLabel("Ion Cannon: "+game.getPlanet().getArmy()[5].size());
        datalabel_ioncannon.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_ioncannon);

        datalabel_plasmacannon = new JLabel("Plasma Cannon: "+game.getPlanet().getArmy()[6].size());
        datalabel_plasmacannon.setHorizontalAlignment(SwingConstants.CENTER);
        unitsdefensePanel.add(datalabel_plasmacannon);

        unitsPanel.add(unitsattackPanel, BorderLayout.NORTH);
        unitsPanel.add(unitsdefensePanel, BorderLayout.SOUTH);
        unitsPanel.add(unitsattackPanel, BorderLayout.NORTH);
        unitsPanel.add(unitsdefensePanel, BorderLayout.SOUTH);
        mainPanel.add(unitsPanel);

        // Panel enemigos
        JPanel attackContainer = new JPanel(new BorderLayout(5, 5));
       
        // Barra de progreso
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        attackContainer.add(progressBar, BorderLayout.NORTH);
        
        // panel enemigos
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
      //Eventos
      		boton_upgrade_attack.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				try {
      					game.getPlanet().upgradeTechnologyAttack();
      					infotechattack.setText("Attack Tech: " + game.getPlanet().getTechnologyAttack());
      					infodeuterium.setText("Deuterium: "+ game.getPlanet().getDeuterium());
      					
      				} catch (ResourceException e1) {
      					// TODO Auto-generated catch block
      					e1.printStackTrace();
      					console.append("\n>"+e1.getMessage());
      				}
      				
      			}
      		});
      		
      		boton_upgrade_defense.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				try {
      					game.getPlanet().upgradeTechnologyDefense();
      					infotechdefense.setText("Defense Tech: " + game.getPlanet().getTechnologyDefense());
      					infodeuterium.setText("Deuterium: "+ game.getPlanet().getDeuterium());
      					
      				} catch (ResourceException e1) {
      					// TODO Auto-generated catch block
      					e1.printStackTrace();
      					console.append("\n>"+e1.getMessage());
      				}
      			}
      		});
      		labelImgLightHunter.addMouseListener(new MouseAdapter() {
      		    public void mouseClicked(MouseEvent e) {
      		        build(0);
      		    }
      		});

      		labelImgHeavyHunter.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      		        build(1);
      			}
      		});
      		labelImgArmoredShip.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      		        build(2);
      			}
      		});
      		labelImgBattleship.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      		        build(3);
      			}
      		});
      		labelImgMissileLauncher.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      		        build(4);
      			}
      		});
      		labelImgIonCannon.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      				build(5);
      			}
      		});
      		labelImgPlasmaCannon.addMouseListener(new MouseAdapter( ) {
      			public void mouseClicked(MouseEvent e) {
      		        build(6);
      			}
      		});
              
      		int[] clickcount = {0};
      		planetImage.addMouseListener(new MouseAdapter() {
      			public void mouseClicked(MouseEvent e) {
      				clickcount[0]++;
      				if (clickcount[0]==10) {
      					try {
      		                BufferedImage easterEggImg = ImageIO.read(getClass().getResource("../imagenes/easteregg.png"));
      		                planetImage.setIcon(new ImageIcon(easterEggImg.getScaledInstance(225, 225, Image.SCALE_SMOOTH)));
      		                console.append("\n>monke");
      		            } catch (IOException ex) {
      		                ex.printStackTrace();
      		                console.append("\n>[ERROR] NO FUNCIONA EL MONKE :((((((((");
      		            }
      				}
      			}
      		});
      		startEnemyTimer();
      	
    }
    
    private void build(int unitType) {
        String input = JOptionPane.showInputDialog(null, "How many "+UNIT_NAMES[unitType]+"s do you want to create?");
        if (input != null) {
            try {
                int n = Integer.parseInt(input);
                int metalCost = METAL_COST_UNITS[unitType] * n;
                int deuteriumCost = DEUTERIUM_COST_UNITS[unitType] * n;

                int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "This will cost:\nMetal: " + metalCost + "\nDeuterium: " + deuteriumCost + "\n\nCreate?",
                    "Confirm Creation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    try {
                        game.build(unitType, n);
                    } catch (ResourceException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Resource Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                infodeuterium.setText("Deuterium: " + game.getPlanet().getDeuterium());
                infometal.setText("Metal: " + game.getPlanet().getMetal());
                datalabel_units[unitType].setText(UNIT_NAMES[unitType]+": " + game.getPlanet().getArmy()[0].size());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void startEnemyTimer() {
        Timer timer = new Timer(100, null);
        final int[] progreso = {0};
        final int[] oleada = {1};
        final boolean[] radarOculto = {false};
        final boolean[] enemigosCargados = {false};

        CardLayout cl = (CardLayout) attackPanel.getLayout();

        timer.addActionListener(e -> {
            progreso[0]++;
            progressBar.setValue(progreso[0]);
            progressBar.setString(progreso[0] + " %");

            if (progreso[0] >= 50 && !enemigosCargados[0]) {
                game.createEnemyArmy();
            	
            	cl.show(attackPanel, "ENEMIGOS");

                // Mostrar aviso de detección
                JOptionPane.showMessageDialog(this, "¡Detectando enemigos!", "Alerta", JOptionPane.WARNING_MESSAGE);

                String[] rutas = {"/imagenes/lighthunter.png","/imagenes/heavyhunter.png","/imagenes/battleship.png", "/imagenes/armoredship.png"};

                for (int i = 0; i < 4; i++) {
                    enemyPanels[i].removeAll();
                    JLabel img = new JLabel(new ImageIcon(getClass().getResource(rutas[i])));
                    JLabel txt = new JLabel(UNIT_NAMES[i]+": "+game.getEnemy_army()[i].size());
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
            	game.initBattle();

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
                JOptionPane.showMessageDialog(
                    this,
                    "A battle has ensued!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });

        timer.start();
        setVisible(true);
    }


    public void start() {
    	game = new Game();
    	game.start();
        new GraphicalUI();
    }
}