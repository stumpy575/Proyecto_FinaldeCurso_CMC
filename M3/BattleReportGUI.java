package proyectoFinal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BattleReportGUI extends JFrame implements Variables {
    private Game game;
    private JTextArea brconsole;
    private JTextField inputField;

    public BattleReportGUI(Game game) {
        this.game = game;

        setTitle("Battle Report Viewer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        brconsole = new JTextArea();
        brconsole.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        brconsole.setEditable(false);
        brconsole.setLineWrap(true);
        brconsole.setWrapStyleWord(true);
        brconsole.setText("> Welcome to the Battle Report Viewer\n> Enter a number below and press ENTER to select a report.");

        JScrollPane scroll = new JScrollPane(brconsole);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 30));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(inputField, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(scroll, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);

        // Inicio de listeners
        inputField.addActionListener(new ActionListener() {
            private boolean developmentView = false;
            private int currentReportIndex = -1;

            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim().toLowerCase();
                inputField.setText("");

                if (!developmentView) {
                    try {
                        int index = Integer.parseInt(input);
                        int max = game.getBattleReportAmount();

                        if (index == 0 || index > max) {
                            brconsole.append("\n> Invalid battle number. Available: 1 to " + max);
                        } else {
                            currentReportIndex = index - 1;
                            brconsole.append("\n\n> BATTLE REPORT #" + index + "\n");
                            brconsole.append(game.getBattle_stats_logs()[currentReportIndex]);
                            brconsole.append("\n> View battle development? (y/n)");
                            developmentView = true;
                        }
                    } catch (NumberFormatException ex) {
                        brconsole.append("\n> Please enter a valid number.");
                    }
                } else {
                    if (input.equals("y")) {
                        brconsole.append("\n" + game.getBattle_development_logs()[currentReportIndex]);
                    } else {
                        brconsole.append("\n> Cancelled step-by-step view.");
                    }
                    developmentView = false;
                }

                brconsole.setCaretPosition(brconsole.getDocument().getLength());
            }
        });
    }
}
