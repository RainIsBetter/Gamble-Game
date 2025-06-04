/**
 * The Wallet class stores the current amount of coins the player has
 * Notifies the listeners when the coin amount changes.
 */

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Wallet {
    // Initial amount of coins
    private int coins = 1000;
    private final List<ActionListener> listeners = new ArrayList<>();

    // Add a listener to changes in the amount of coins
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    // Remove a listener
    public void removeActionListener(ActionListener listener) {
        listeners.remove(listener);
    }

    // Notify all listeners
    private void fireMoneyChangedEvent() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "moneyChanged");
        for (ActionListener listener : listeners) {
            listener.actionPerformed(event);
        }
    }

    // Returns the current amount of coins
    public int getCoins() {
        return coins;
    }
    
    // Change the amount of coins by a specified amount
    // Returns true if the change was successful, false if not enough coins
    // This is the only method that modifies the coins variable
    public boolean changeCoins(int change) {
        if (coins + change < 0) {
            coins = coins + change;
            fireMoneyChangedEvent();
            return false; // Not enough coins to change
        } else {
            coins = coins + change;
            fireMoneyChangedEvent();
            return true;
        }

    }
}