/**
 * The Character class is just a character in the game.
 * It is as the base class for Hero and Monster subclasses
 */

public class Character implements WinningItem {
    private String name;
    private String rarity;
    private int power;

    public Character(String inName, String inRarity, int inPower) {
        name = inName;
        rarity = inRarity;
        power = inPower;
    }

    //Returns the name of the character.
    public String getName() {
        return name;
    }

    // Returns the rarity of the character.
    public String getRarity() {
        return rarity;
    }

    // Returns the power of the character.
    public int getPower() {
        return power;
    }

    // equals method to compare heroes based on their name only
    public boolean equals(Object inHero) {
        if (this == inHero)
            return true;
        if (!(inHero instanceof Hero))
            return false;
        Character otherHero = (Character) inHero;
        return name.equals(otherHero.name);
    }

    // Used in JComboBox (drop-down menu) to display the character's name and rarity.
    public String toString() {
        return name + "(" + rarity + ")";
    }


}
