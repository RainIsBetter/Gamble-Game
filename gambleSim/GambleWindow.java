
/**
 * GambleWindow is the main window for the game.
 * this is where the player gambles and theres a battle button
 * 
 * It displays the player's wallet, backpack (list of collected characters), and results of each gambling attempt.
 * It has buttons for gambling (single or 5x) and starting a battle.
 * It has logic to update the wallet, backpack, and results of the gambles.
 */

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GambleWindow extends JFrame implements ActionListener {
    public boolean gambleButtonPressed;
    private Container contentPane;
    private JPanel buttonPanel;
    private JButton gambleButton;
    private JButton gambleButton5Times;
    private JButton battleButton;
    private JPanel outputPanel;

    private JTextArea backPackTextArea;
    private JTextArea resultTextArea;
    private JTextArea walletTextArea;
    JLabel gambleBucksLabel = new JLabel("Wallet:");
    JLabel backPackLabel = new JLabel("Backpack:");
    JLabel resultLabel = new JLabel("Result:");


    private CharacterStorage characterStorage;
    private Wallet wallet;
    private GambleCasino casino;


    public GambleWindow(CharacterStorage inCharacterStorage, Wallet inWallet, GambleCasino inCasino) {
        characterStorage = inCharacterStorage;
        wallet = inWallet;
        casino = inCasino;
        
        outputPanel = new JPanel();
        backPackTextArea = new JTextArea(10, 30);
        backPackTextArea.setEditable(false);
        backPackTextArea.setFocusable(false);
        backPackTextArea.setFont(new Font("Arial", Font.PLAIN,16)); 

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFocusable(false); 
        resultTextArea.setFont(new Font("Arial", Font.PLAIN,16));


        walletTextArea = new JTextArea();
        walletTextArea.setEditable(false);
        walletTextArea.setFocusable(false);
        walletTextArea.setFont(new Font("Arial", Font.BOLD,28));
        Border blackline = BorderFactory.createLineBorder(Color.gray, 1);
        walletTextArea.setBorder(blackline);

        contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout());
        ((JComponent) contentPane).setBorder(new EmptyBorder(10, 10, 10, 10));
        
        contentPane.setBackground(new Color(240, 248, 255)); // set to Light blue 
    

        setSize(new Dimension(1000, 400));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setResizable(false);

        setTitle("Grand Gracious Gamble Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setVisible(true);

        gambleButtonPressed = true;

        buttonPanel = new JPanel();

        gambleButton = new JButton("Gamble $100");
        gambleButton5Times = new JButton("Gamble x5 $500");
        battleButton = new JButton("BATTLE");

        contentPane.add(walletTextArea, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(outputPanel, BorderLayout.CENTER);

        buttonPanel.add(gambleButton, null);
        buttonPanel.add(gambleButton5Times, null);
        buttonPanel.add(battleButton, null);

        
        outputPanel.setLayout(new FlowLayout());
        outputPanel.add(backPackTextArea, null);
        outputPanel.add(resultTextArea, null);
        
        gambleButton.setEnabled(true);
        gambleButton5Times.setEnabled(true);
        battleButton.setEnabled(true);


        backPackTextArea.setText("Your backpack is empty"); // button labels
        resultTextArea.setText("No results yet");
        int width = getWidth();
        int height = getHeight();
        // walletLabel.setPreferredSize(new Dimension(width - 50, 30));
        backPackTextArea.setPreferredSize(new Dimension(width / 2 - 160, height));
        resultTextArea.setPreferredSize(new Dimension(width / 2 + 40, height));
        walletTextArea.setText("$" + wallet.getCoins());

        gambleButton.addActionListener(this);
        gambleButton5Times.addActionListener(this);
        battleButton.addActionListener(this);
        wallet.addActionListener(this);
    }

    public void display() {
        setVisible(true);
    }

    public void displayBrokePopUp() {
        JOptionPane optionPane = new JOptionPane(
                "You don't have enough coins!",
                JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Not enough coins");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        dialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gambleButton5Times && wallet.getCoins() < 500) 
            displayBrokePopUp();
        if ((event.getSource()== gambleButton) && wallet.getCoins() < 100) 
            displayBrokePopUp();

        if ((event.getSource() == gambleButton) && gambleButtonPressed && wallet.getCoins() > 99) {
            WinningItem winningItem = casino.gambleAttempt(wallet, characterStorage);
            resultTextArea.setText(winningItem.toString());
            backPackTextArea.setText(characterStorage.getAllHeroesNamesCombined());
        }
        if (event.getSource() == gambleButton5Times && wallet.getCoins() > 499) {
            String resultText = "";
            for(int i = 0; i < 5; i++) {
                WinningItem winningItem = casino.gambleAttempt(wallet, characterStorage);
                resultText += winningItem.toString() + "\n";
            }
            resultTextArea.setText(resultText);
            backPackTextArea.setText(characterStorage.getAllHeroesNamesCombined());
        }


        if (event.getSource() == battleButton) {
            if(characterStorage.size() == 0) {
                JOptionPane.showMessageDialog(this, "You have no characters to battle with!", "No Characters", JOptionPane.WARNING_MESSAGE);
                return;
            }
            BattleDialog battleDialog =  new BattleDialog(characterStorage, wallet);
            battleDialog.setVisible(true);
        }
        if( event.getSource() == wallet) {
            walletTextArea.setText("$" + wallet.getCoins());
        }
    }
}

