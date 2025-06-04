/**
 * The Monster class is  enemy characters, used in BattleDialog(to fight against).
 * It defines three hardcoded monster instances: SLIME, GOBLIN, and (shadow)BEAST
 */

public class Monster extends Character {
    public static final Monster SLIME = new Monster("Slime", "lv5", 50);
    public static final Monster GOBLIN = new Monster("Goblin", "lv10", 100);
    public static final Monster BEAST = new Monster("Shadow Beast", "lv30", 200);
    
    public Monster(String inName, String inRarity, int inPower) {
        super(inName, inRarity, inPower);
    }

    // Overrides the toString() method to use in the JComboBox (drop-down menu) in BattleDialog.
    public String toString() {
        return super.toString();
    }
    
}
