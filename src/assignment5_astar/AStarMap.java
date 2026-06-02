package assignment5_astar;

import java.util.*;

public class AStarMap {

    static final int ROWS = 10;
    static final int COLS = 10;

    Node[][] grid = new Node[ROWS][COLS];

    AStarMap() {
        // Step 1: create every node
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                grid[r][c] = new Node(r, c);

        // Step 2: place named cities
        placeCity(0, 2, "Nordby");
        placeCity(2, 7, "Østby");
        placeCity(7, 1, "Vestby");
        placeCity(8, 8, "Sydby");
        placeCity(4, 4, "Midtby");
        placeCity(1, 0, "Havneby");

        // Step 3: place walls
        int[][] wallCoords = {
                {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 3},   // western mountain range
                {5, 6}, {5, 7}, {6, 6}, {6, 7},           // eastern lake
                {3, 8}, {4, 8}, {4, 9}, {3, 9}            // northern cliffs
        };
        for (int[] w : wallCoords)
            grid[w[0]][w[1]].isWall = true;
    }

    private void placeCity(int row, int col, String name) {
        grid[row][col].cityName = name;
    }

    // Manhattan distance — |row diff| + |col diff|
    private double heuristic(Node a, Node b) {
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    // Returns walkable neighbours — up/down/left/right only
    private List<Node> getNeighbors(Node node) {
        List<Node> result = new ArrayList<>();
        int[][] dirs = { {-1,0}, {1,0}, {0,-1}, {0,1} };

        for (int[] d : dirs) {
            int nr = node.row + d[0];
            int nc = node.col + d[1];
            boolean inBounds = nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS;
            if (inBounds && !grid[nr][nc].isWall)
                result.add(grid[nr][nc]);
        }
        return result;
    }

    List<Node> findPath(String fromCity, String toCity) {
        Node start = null, end = null;
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++) {
                if (fromCity.equals(grid[r][c].cityName)) start = grid[r][c];
                if (toCity.equals(grid[r][c].cityName))   end   = grid[r][c];
            }

        if (start == null || end == null) return Collections.emptyList();

        // reset scores from any previous search
        for (Node[] row : grid)
            for (Node n : row) { n.g = Double.MAX_VALUE; n.f = Double.MAX_VALUE; n.parent = null; }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        Set<Node> closedSet = new HashSet<>();

        start.g = 0;
        start.h = heuristic(start, end);
        start.f = start.g + start.h;
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll(); // lowest f first

            if (current == end) return buildPath(end);

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue;

                double newG = current.g + 1; // each step costs 1

                if (newG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = newG;
                    neighbor.h = heuristic(neighbor, end);
                    neighbor.f = neighbor.g + neighbor.h;
                    openSet.remove(neighbor); // re-add to update priority
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    // trace parent pointers from end back to start, then reverse
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

    void visualize(List<Node> path) {
        Set<Node> pathSet = new HashSet<>(path);

        System.out.println("\n=== KORTESTE VEJ ===\n");
        System.out.print("     ");
        for (int c = 0; c < COLS; c++) System.out.printf("%4d", c);
        System.out.println();

        for (int r = 0; r < ROWS; r++) {
            System.out.printf("%3d  ", r);
            for (int c = 0; c < COLS; c++) {
                Node n = grid[r][c];
                if (n.isWall)                                System.out.print("  ██");
                else if (pathSet.contains(n) && n.cityName != null) System.out.printf("%4s", n.cityName.substring(0,3));
                else if (pathSet.contains(n))                System.out.print("  **");
                else if (n.cityName != null)                 System.out.printf("%4s", n.cityName.substring(0,3));
                else                                         System.out.print("  . ");
            }
            System.out.println();
        }

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
        List<Node> path = map.findPath("Havneby", "Sydby");

        if (path.isEmpty()) {
            System.out.println("Ingen vej fundet!");
        } else {
            map.visualize(path);
        }
    }
}