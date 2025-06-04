
/**
 * BattleDialog is the battle interface in the game
 *
 * - the player can select a Hero from their gamble collection and an enemy to battle
 * - Displays a battle log of the battle
 * - Updates the player's wallet based on the type of battle commenced
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleDialog extends JDialog {

    private JTextArea battleLog;
    private JComboBox<Hero> playerSelect;
    private JComboBox<Monster> enemySelect;
    private JButton fightButton;

    private CharacterStorage characterStorage;
    private Wallet wallet;

    public BattleDialog(CharacterStorage inCharacterStorage, Wallet inWallet) {
        // all this code below sets up the dialog box UI
        
        setModal(true);
        characterStorage = inCharacterStorage;
        wallet = inWallet;

        setTitle("Battle Arena");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        battleLog = new JTextArea("Battle begins here...\n");
        battleLog.setEditable(false);
        battleLog.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(battleLog);
        scrollPane.setPreferredSize(new Dimension(480, 250));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JProgressBar progressBar = new JProgressBar(0, 100);
        bottomPanel.add(progressBar, BorderLayout.NORTH);
        progressBar.setVisible(false);

        JPanel selectionPanel = new JPanel(new FlowLayout());

        playerSelect = new JComboBox<Hero>(characterStorage.getHeroes().toArray(new Hero[0]));
        enemySelect = new JComboBox<>(new Monster[] {
                Monster.SLIME,
                Monster.GOBLIN,
                Monster.BEAST
        });
        selectionPanel.add(new JLabel("Your Character:"));
        selectionPanel.add(playerSelect);
        selectionPanel.add(new JLabel("Enemy:"));
        selectionPanel.add(enemySelect);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        fightButton = new JButton("FIGHT!");
        buttonPanel.add(fightButton);

        bottomPanel.add(selectionPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action listener for the fight button. This code runs when the fight button is pressed.
        fightButton.addActionListener(e -> {
            progressBar.setVisible(true);

            // Configures the progress bar animation
            int interval = 20; // update every 20 milliseconds 
            int totalDuration = 1000; // total time to finish is 1 second
            int steps = totalDuration / interval;

            Timer timer = new Timer(interval, null);
            final int[] count = { 0 };

            // Action listener for the timer to update the progress bar. This code runs every 20 milliseconds.
            timer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    count[0]++;
                    int progress = (int) ((count[0] / (float) steps) * 100);
                    progressBar.setValue(progress);

                    if (count[0] >= steps) {
                        // Timer has finished, stop it and update the battle log
                        timer.stop();
                        progressBar.setVisible(false);

                        Hero player = (Hero) playerSelect.getSelectedItem();
                        Monster enemy = (Monster) enemySelect.getSelectedItem();
                        int money = 0;
                        money += player.getPower() + enemy.getPower();
                        wallet.changeCoins(money);

                        battleLog.append(player + " attacks " + enemy + " with a " + typeOfAttack() + "\n");
                        battleLog.append("+$" + money + "!" + "\n");

                    }
                }
            });
            timer.start();

        });

    }

    public String typeOfAttack() {
        String[] attack = { "Banana", "Toaster", "Spatula",
                "Sock",
                "Stapler",
                "Keyboard",
                "Blender",
                "Toilet Brush",
                "Measuring Tape",
                "Alarm Clock",
                "Remote Control",
                "Cheese Grater",
                "Vacuum",
                "Mop",
                "Microwave" };
        return attack[(int) (Math.random() * attack.length)];

    }
}