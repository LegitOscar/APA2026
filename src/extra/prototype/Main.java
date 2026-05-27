package extra.prototype;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create one original enemy with full setup
        Enemy original = new Enemy(
                "Goblin",
                100,
                "Sword",
                List.of("Slash", "Dodge", "Flee")
        );

        // Clone 1 - just change the name
        Enemy goblinChief = original.clone();
        goblinChief.setName("Goblin Chief");

        // Clone 2 - change name and boost health
        Enemy goblinBoss = original.clone();
        goblinBoss.setName("Goblin Boss");
        goblinBoss.setHealth(500);

        // Clone 3 - change everything
        Enemy goblinKing = original.clone();
        goblinKing.setName("Goblin King");
        goblinKing.setWeapon("Magic Staff");
        goblinKing.setHealth(1000);
        goblinKing.getAbilities().add("Fireball");

        // Print all enemies
        System.out.println("--- All Enemies ---");
        System.out.println(original);
        System.out.println(goblinChief);
        System.out.println(goblinBoss);
        System.out.println(goblinKing);
    }
}