/**
 *  This is the entry point of the application.
 * 
 * - Character: Base class representing a generic character in the game.
 *   - Hero: Subclass of Character, represents player-controlled characters with unique abilities.
 *   - Monster: Subclass of Character, represents enemy characters encountered in battles.
 * 
 * - WinningItem: Interface defining items that can be won in the GambleCasino.
 *   - Implementations of WinningItem may include specific rewards like coins, power-ups, or equipment.
 * 
 * - CharacterStorage: Manages a collection of Hero objects, allowing for organization and retrieval.
 * - Wallet: Tracks the player's coins and notifies listeners of changes.
 * - GambleCasino: Handles gambling logic and generates WinningItem objects as rewards.
 * - GambleWindow: GUI for interacting with the game, including gambling and battling.
 *   - BattleDialog: Sub-dialog for battles between Hero and Monster objects.
 */
public class MainClass
{
 public static void main(String[] args) 
    {
        CharacterStorage characterLobby = new CharacterStorage();
        Wallet wallet = new Wallet();
        GambleCasino casino = new GambleCasino();

        GambleWindow gambleWindow = new GambleWindow(characterLobby, wallet, casino);      //creates the game window!
        

        gambleWindow.display();
    }
}

