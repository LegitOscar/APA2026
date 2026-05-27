package assignment6_designpatterns.mazeSolver;

public class ManhattanHeuristic implements HeuristicStrategy {
    @Override
    public int calculate(MazeNode node, MazeNode destination) {
        return Math.abs(destination.getRow() - node.getRow())
                + Math.abs(destination.getCol() - node.getCol());
    }
}
