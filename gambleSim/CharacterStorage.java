/**
 * CharacterStorage class has a collection of Hero objects
 * 
 * 
 * - stores Hero objects in a list 
 * - methods to access, add, and check for Hero objects
 * - can return hero names and combine them into a single string
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class CharacterStorage {
    private List<Hero> heroes = new ArrayList<Hero>();

    // returns a Hero object at the specified index
    public Hero getHero(int i) {
        return heroes.get(i);
    }
    // returns all Hero objects
    public List<Hero> getHeroes() {
        return heroes;
    }

    // returns an array of all Hero names
    public String[] getAllHeroesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < heroes.size(); i++) {
            names.add(heroes.get(i).getName());
        }
        return names.toArray(new String[0]);
    }

    // return hero names and combine them into a single string
    public String getAllHeroesNamesCombined() {
        String names = "";
        for (int i = 0; i < heroes.size(); i++) {
            names += heroes.get(i).getName() + "\n";
        }
        return names;
    }

    // adds a Hero object to the list
    public void addHero(Hero inHero) {
        heroes.add(inHero);
    }

    // checks if a Hero object is already in the list
    public boolean has(Hero inHero) {
        return heroes.contains(inHero);
    }

    // returns the number of Hero objects in the list
    public int size() {
        return heroes.size();
    }

}