package assignment6_designpatterns.mazeSolver;

public class ZeroHeuristic implements HeuristicStrategy {
    @Override
    public int calculate(MazeNode node, MazeNode destination) {
        return 0;
    }
}
