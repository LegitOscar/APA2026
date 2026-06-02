package assignment5_astar;

public class Node {
    int row, col;       // grid position
    double g;           // cost from start to this node
    double h;           // estimated cost to goal (heuristic)
    double f;           // g + h — lower is better
    Node parent;        // previous node in path
    String cityName;    // null if not a city
    boolean isWall;     // true = impassable

    Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.g = Double.MAX_VALUE;
        this.isWall = false;
        this.cityName = null;
    }
}