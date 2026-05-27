package assignment6_designpatterns.buildPattern;

public class City {
    public final String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
