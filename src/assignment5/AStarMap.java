package assignment5;

import java.util.*;

public class AStarMap {

    // Constants — final means they can never be changed after being set
    static final int ROWS = 10;
    static final int COLS = 10;

    // The grid itself — a 2D array of Nodes
    // Think of it as: grid[row][col] gives you the Node at that position
    Node[][] grid = new Node[ROWS][COLS];

    // Constructor — sets up the whole map
    AStarMap() {
        // Step 1: Create every node
        // Two nested for-loops: outer loop = rows, inner loop = columns
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = new Node(r, c);
            }
        }

        // Step 2: Place named cities
        // We call a helper method to do this cleanly
        placeCity(0, 2, "Nordby");
        placeCity(2, 7, "Østby");
        placeCity(7, 1, "Vestby");
        placeCity(8, 8, "Sydby");
        placeCity(4, 4, "Midtby");
        placeCity(1, 0, "Havneby");

        // Step 3: Place walls (mountains/water — impassable terrain)
        int[][] wallCoords = {
                {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 3},   // western mountain range
                {5, 6}, {5, 7}, {6, 6}, {6, 7},           // eastern lake
                {3, 8}, {4, 8}, {4, 9}, {3, 9}            // northern cliffs
        };
        for (int[] w : wallCoords) {
            grid[w[0]][w[1]].isWall = true;
        }
    }

    // Helper: sets a cell's city name
    // Private means only this class can call it
    private void placeCity(int row, int col, String name) {
        grid[row][col].cityName = name;
    }
    // The heuristic: our "guess" of how far we are from the goal
    // Manhattan distance = |row difference| + |col difference|
    // Math.abs() gives us the absolute value (removes negative signs)
    private double heuristic(Node a, Node b) {
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    // Returns all walkable neighbors of a given node
    // We only move up/down/left/right — not diagonally
    private List<Node> getNeighbors(Node node) {
        // ArrayList is a resizable list — size grows as we add items
        List<Node> result = new ArrayList<>();

        // Each direction as a [rowChange, colChange] pair
        int[][] dirs = { {-1,0}, {1,0}, {0,-1}, {0,1} };

        for (int[] d : dirs) {
            int nr = node.row + d[0];  // new row
            int nc = node.col + d[1];  // new col

            // Check: is the neighbor inside the grid AND not a wall?
            boolean inBounds = nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS;
            if (inBounds && !grid[nr][nc].isWall) {
                result.add(grid[nr][nc]);
            }
        }
        return result;
    }
    // The main search — returns the path as an ordered list of nodes
    // Returns an empty list if no path exists
    List<Node> findPath(String fromCity, String toCity) {

        // Find the start and end nodes by scanning the grid for matching names
        Node start = null, end = null;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (fromCity.equals(grid[r][c].cityName)) start = grid[r][c];
                if (toCity.equals(grid[r][c].cityName))   end   = grid[r][c];
            }
        }
        // Guard clause: if either city wasn't found, stop immediately
        if (start == null || end == null) return Collections.emptyList();

        // Reset all scores from any previous search
        for (Node[] row : grid)
            for (Node n : row) { n.g = Double.MAX_VALUE; n.f = Double.MAX_VALUE; n.parent = null; }

        // PriorityQueue sorts automatically by Node's compareTo (lowest f first)
        PriorityQueue<Node> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(n -> n.f)
        );
        Set<Node> closedSet = new HashSet<>();

        // Initialise the start node
        start.g = 0;
        start.h = heuristic(start, end);
        start.f = start.g + start.h;
        openSet.add(start);

        // Main loop
        while (!openSet.isEmpty()) {
            Node current = openSet.poll(); // removes and returns the lowest-f node

            if (current == end) return buildPath(end); // found it!

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue; // skip already explored

                double newG = current.g + 1; // each step costs 1

                if (newG < neighbor.g) {     // found a shorter route to this neighbor
                    neighbor.parent = current;
                    neighbor.g = newG;
                    neighbor.h = heuristic(neighbor, end);
                    neighbor.f = neighbor.g + neighbor.h;
                    openSet.remove(neighbor); // re-add to update its position in the queue
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // no path found
    }

    // Traces parent pointers from end back to start, then reverses
    private List<Node> buildPath(Node end) {
        List<Node> path = new ArrayList<>();
        Node cur = end;
        while (cur != null) {
            path.add(cur);
            cur = cur.parent;
        }
        Collections.reverse(path);
        return path;
    }
    // Prints the grid with the found path marked
    void visualize(List<Node> path) {
        // Put all path nodes into a Set for O(1) lookup
        Set<Node> pathSet = new HashSet<>(path);

        System.out.println("\n=== KORTESTE VEJ ===\n");

        // Print column numbers across the top
        System.out.print("     ");
        for (int c = 0; c < COLS; c++) System.out.printf("%4d", c);
        System.out.println();

        for (int r = 0; r < ROWS; r++) {
            System.out.printf("%3d  ", r); // row number on the left

            for (int c = 0; c < COLS; c++) {
                Node n = grid[r][c];

                if (n.isWall)              System.out.print("  ██"); // wall
                else if (pathSet.contains(n) && n.cityName != null)
                    System.out.printf("%4s", n.cityName.substring(0,3));
                else if (pathSet.contains(n))
                    System.out.print("  **"); // path step
                else if (n.cityName != null)
                    System.out.printf("%4s", n.cityName.substring(0,3));
                else                       System.out.print("  . "); // open cell
            }
            System.out.println();
        }

        // Print the route as a readable sentence
        System.out.println("\nRute:");
        for (int i = 0; i < path.size(); i++) {
            Node n = path.get(i);
            String label = n.cityName != null ? n.cityName : "(" + n.row + "," + n.col + ")";
            System.out.print(label);
            if (i < path.size() - 1) System.out.print(" → ");
        }
        System.out.println();
        System.out.println("Antal skridt: " + (path.size() - 1));
    }
    public static void main(String[] args) {
        AStarMap map = new AStarMap();

        // Find path from Havneby to Sydby
        List<Node> path = map.findPath("Havneby", "Sydby");

        if (path.isEmpty()) {
            System.out.println("Ingen vej fundet!");
        } else {
            map.visualize(path);
        }
    }
}
