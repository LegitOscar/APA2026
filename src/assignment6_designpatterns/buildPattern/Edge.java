package assignment6_designpatterns.buildPattern;

public class Edge {
    public final City destination;
    public final int weight;

    public Edge(City destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
