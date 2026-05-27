package extra.composite;

public class Main {
    public static void main(String[] args) {

        // Individual enemies
        Enemy goblin = new Enemy("Goblin");
        Enemy troll = new Enemy("Troll");
        Enemy orc = new Enemy("Orc");
        Enemy dragon = new Enemy("Dragon");
        Enemy skeleton = new Enemy("Skeleton");

        // Inner group - contains individual enemies
        EnemyGroup dungeonGroup = new EnemyGroup("Dungeon Group");
        dungeonGroup.add(goblin);
        dungeonGroup.add(troll);
        dungeonGroup.add(orc);

        // Inner group 2 - another group of enemies
        EnemyGroup bossGroup = new EnemyGroup("Boss Group");
        bossGroup.add(dragon);
        bossGroup.add(skeleton);

        // Outer group - contains both inner groups (two levels deep!)
        EnemyGroup allEnemies = new EnemyGroup("All Enemies");
        allEnemies.add(dungeonGroup);
        allEnemies.add(bossGroup);

        // Call update on the top level group
        allEnemies.update();
    }
}