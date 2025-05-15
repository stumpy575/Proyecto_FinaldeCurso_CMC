package proyectoFinal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GUIwelcome extends JFrame implements Variables {
	Planet planet= new Planet(1,1,500,500);

    public GUIwelcome() {
        setTitle("The Great Space Wars!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1026, 552);
        setResizable(false);
        setLocationRelativeTo(null);

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
        
        //Imagen
        BufferedImage img=null;
        try {
            img = ImageIO.read(getClass().getResource("/imagenes/planeta.png")); //TODO que esta ruta sea la buena
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imgplanet = new ImageIcon(img.getScaledInstance(225, 225, Image.SCALE_SMOOTH));      
        JPanel planetStatsPanel = new JPanel(new BorderLayout(10, 10));
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

        infoPanel.add(new JLabel("Metal: " + planet.getMetal()));
        infoPanel.add(new JLabel());
        infoPanel.add(new JLabel("Deuterium: " + planet.getDeuterium()));
        infoPanel.add(new JLabel());
        
        //se meten los botones en paneles de flowlayout para que no ocupen todo el grid y que estén bonitos
        infoPanel.add(new JLabel("Defense Tech: " + planet.getTechnologyDefense()));
        JPanel contdefense = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contdefense.add(boton_upgrade_defense);
        infoPanel.add(contdefense);
        infoPanel.add(new JLabel("Attack Tech: " + planet.getTechnologyAttack()));
        JPanel contattack = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contattack.add(boton_upgrade_attack);
        infoPanel.add(contattack);
        planetStatsPanel.add(infoPanel, BorderLayout.EAST);

        mainPanel.add(planetStatsPanel);

     // Panel unidades
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JPanel unitsPanel = new JPanel(new GridLayout(2, 4, 5, 5));

        // unidades ataque
        ImageIcon img_lighthunter = new ImageIcon(getClass().getResource("../imagenes/lighthunter.png"));
        unitsPanel.add(new JLabel(img_lighthunter));
        ImageIcon img_heavyhunter = new ImageIcon(getClass().getResource("../imagenes/heavyhunter.png"));
        unitsPanel.add(new JLabel(img_heavyhunter));
        ImageIcon img_battleship= new ImageIcon(getClass().getResource("../imagenes/battleship.png"));
        unitsPanel.add(new JLabel(img_battleship));
        ImageIcon img_armoredship= new ImageIcon(getClass().getResource("../imagenes/armoredship.png"));
        unitsPanel.add(new JLabel(img_armoredship));
 
        JLabel label_lighthunter = new JLabel("Light Hunter: ");
        label_lighthunter.setHorizontalAlignment(SwingConstants.CENTER);
        unitsPanel.add(label_lighthunter);

        JLabel label_heavyhunter = new JLabel("Heavy Hunter: 100");
        label_heavyhunter.setHorizontalAlignment(SwingConstants.CENTER);
        unitsPanel.add(label_heavyhunter);

        JLabel label_battleship= new JLabel("Battleship: 100");
        label_battleship.setHorizontalAlignment(SwingConstants.CENTER);
        unitsPanel.add(label_battleship);

        JLabel label_armoredship= new JLabel("Armored Ship: 100");
        label_armoredship.setHorizontalAlignment(SwingConstants.CENTER);
        unitsPanel.add(label_armoredship);

        // unidades defensa
        JPanel defensePanel = new JPanel(new GridLayout(2, 3, 5, 5));

        ImageIcon img_missilelauncher = new ImageIcon(getClass().getResource("../imagenes/missilelauncher.png"));
        defensePanel.add(new JLabel(img_missilelauncher));
        ImageIcon img_ioncannon = new ImageIcon(getClass().getResource("../imagenes/ioncannon.png"));
        defensePanel.add(new JLabel(img_ioncannon));
        ImageIcon img_plasmacannon = new ImageIcon(getClass().getResource("../imagenes/plasmacannon.png"));
        defensePanel.add(new JLabel(img_plasmacannon));

        JLabel label_missile = new JLabel("Missile Launcher: 100");
        label_missile.setHorizontalAlignment(SwingConstants.CENTER);
        defensePanel.add(label_missile);

        JLabel label_ion = new JLabel("Ion Cannon: 100");
        label_ion.setHorizontalAlignment(SwingConstants.CENTER);
        defensePanel.add(label_ion);

        JLabel label_plasma = new JLabel("Plasma Cannon: 100");
        label_plasma.setHorizontalAlignment(SwingConstants.CENTER);
        defensePanel.add(label_plasma);

        // Añadir los paneles al principal si hace falta
        combinedPanel.add(unitsPanel, BorderLayout.NORTH);
        combinedPanel.add(defensePanel, BorderLayout.SOUTH);

    

        combinedPanel.add(new JLabel("Units & Defenses"), BorderLayout.SOUTH);
        combinedPanel.add(unitsPanel, BorderLayout.NORTH);
        combinedPanel.add(defensePanel, BorderLayout.SOUTH);
        mainPanel.add(combinedPanel); // segunda posición

        // Panel enemigos
        JPanel attackContainer = new JPanel(new BorderLayout(5, 5));

        // Progreso encima
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(60);
        progressBar.setStringPainted(false);
        attackContainer.add(progressBar, BorderLayout.NORTH);

        // Panel ataque debajo
        JPanel attackPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        attackPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        attackPanel.add(new JLabel(new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB))));
        attackPanel.add(new JLabel("5"));
        attackPanel.add(new JLabel(new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB))));
        attackPanel.add(new JLabel("3"));

        attackContainer.add(attackPanel, BorderLayout.CENTER);

        mainPanel.add(attackContainer); // tercera posición

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
        scroll.setPreferredSize(new Dimension(0, 100));
        consolePanel.add(new JLabel("Console"), BorderLayout.NORTH);
        consolePanel.add(scroll, BorderLayout.CENTER);

        mainPanel.add(consolePanel); // cuarta posición

        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIwelcome();
    }
}
