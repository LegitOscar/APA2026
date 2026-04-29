package assignment3;

public class MazeExercise {
    static final int N = 4;

    static int[][] maze = {
            {1, 0, 1, 1},
            {1, 1, 1, 0},
            {0, 0, 1, 1},
            {1, 1, 0, 1}
    };

    static int[][] path = new int[N][N];

    public static void main(String[] args) {
        if (solveMaze(0, 0)) {
            printPath();
        } else {
            System.out.println("Ingen løsning fundet.");
        }
    }

    static boolean solveMaze(int row, int col) {

        // 1. Out of bounds check
        if (row < 0 || col < 0 || row >= N || col >= N) {
            return false;
        }

        // 2. Wall check
        if (maze[row][col] == 0) {
            return false;
        }

        // 3. Already visited check
        if (path[row][col] == 1) {
            return false;
        }

        // 4. Mark this cell as part of our current path
        path[row][col] = 1;

        // 5. Are we at the goal?
        if (row == N - 1 && col == N - 1) {
            return true;
        }

        // 6. Try all four directions recursively
        if (solveMaze(row + 1, col)) return true; // down
        if (solveMaze(row, col + 1)) return true; // right
        if (solveMaze(row - 1, col)) return true; // up
        if (solveMaze(row, col - 1)) return true; // left

        // 7. Nothing worked — backtrack
        path[row][col] = 0;
        return false;
    }

    static void printPath() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }
}
