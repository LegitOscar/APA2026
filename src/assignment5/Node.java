package assignment5;

// A Node represents one cell in the grid
public class Node {
    int row, col;           // Where is this cell? (its address)
    double g;               // How many steps did it take to reach here from start?
    double h;               // How many steps do we estimate to the goal? (the heuristic)
    double f;               // g + h — the "total score". Lower is better.
    Node parent;            // Which node did we come from? Used to trace back the path.
    String cityName;        // If this cell is a city, what is its name? Otherwise null.
    boolean isWall;         // Can we walk here? false = open road, true = blocked

    // Constructor — called when we create a new Node with new Node(r, c)
    Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.g = Double.MAX_VALUE; // We haven't reached it yet — treat as "infinite"
        this.isWall = false;
        this.cityName = null;
    }
}
