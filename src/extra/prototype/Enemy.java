package extra.prototype;

import java.util.ArrayList;
import java.util.List;

public class Enemy {
    private String name;
    private int health;
    private String weapon;
    private List<String> abilities;

    public Enemy(String name, int health, String weapon, List<String> abilities) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.abilities = abilities;
    }

    // Deep copy clone - creates fully independent copy
    public Enemy clone() {
        return new Enemy(
                this.name,
                this.health,
                this.weapon,
                new ArrayList<>(this.abilities) // deep copy of the list
        );
    }

    // Setters - so we can tweak clones after copying
    public void setName(String name)         { this.name = name; }
    public void setHealth(int health)        { this.health = health; }
    public void setWeapon(String weapon)     { this.weapon = weapon; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }

    public List<String> getAbilities() { return abilities; }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", weapon='" + weapon + '\'' +
                ", abilities=" + abilities +
                '}';
    }
}
