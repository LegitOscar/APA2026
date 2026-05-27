package extra.composite;

import java.util.*;

public class EnemyGroup implements GameObject {
    private String name;
    private List<GameObject> members = new ArrayList<>();

    public EnemyGroup(String name) {
        this.name = name;
    }

    public void add(GameObject obj) {
        members.add(obj);
    }

    public void update() {
        System.out.println("Gruppe [" + name + "] opdaterer:");
        for (GameObject obj : members) {
            obj.update();
        }
    }
}