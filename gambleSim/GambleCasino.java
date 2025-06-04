
/**
 * GambleCasino class handles gambling logic.
 * 
 *
 * - players can trade coins for a gamble attempt to get a character
 * - Generates Heroes(characters) based on random numbers and thier rarity
 * - Returns different WinningItem objects like Hero, UselessTrash, or ErrorItem, based on the outcome of the gamble.
 * 
 */

public class GambleCasino {
    private static final Hero SUSAN = new Hero("stinky susan", "COMMON", 20);
    private static final Hero OLIVER = new Hero("okay oliver", "RARE", 50);
    private static final Hero AMELIA = new Hero("awsome amelia", "EPIC", 70);
    private static final Hero STEWART = new Hero("Super Sigma Stewart", "LEGENDARY", 100);
    private static final double CHANCE_OF_NOTHING = 0.3; // 30% chance to return nothing

    private boolean gotNothing() {
        return Math.random() >= CHANCE_OF_NOTHING;
    }

    // Generates a Hero based on a random number.
    private Hero generateHero(int num) {
        if (num >= 90) {
            return STEWART;
        } else if (num >= 70) {
            return AMELIA;
        } else if (num >= 50) {
            return OLIVER;
        } else {
            return SUSAN;
        }
    }

    // Makes a gamble, deducts coins from the wallet, and returns a WinningItem.
    public WinningItem gambleAttempt(Wallet inWallet, CharacterStorage inCharacterStorage) {
        int randomNumber = (int) (Math.random() * 100);
        if (inWallet.getCoins() > 0) {
            // Deducts 100 coins for the gamble
            inWallet.changeCoins(-100); 
            if (gotNothing()) {
                return new UselessTrash();
            } else {
                Hero newHero = generateHero(randomNumber);
                if (inCharacterStorage.has(newHero) == false) {
                    inCharacterStorage.addHero(newHero);
                }
                return newHero;
            }
        } else {
            return new ErrorItem("Not enough coins!");
        }

    }
}
