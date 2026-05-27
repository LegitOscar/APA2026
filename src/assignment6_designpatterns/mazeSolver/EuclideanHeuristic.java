package assignment6_designpatterns.mazeSolver;

public class EuclideanHeuristic implements HeuristicStrategy {
    @Override
    public int calculate(MazeNode node, MazeNode destination){
        int dr = destination.getRow() - node.getRow();
        int dc = destination.getCol() - node.getCol();
        return (int) Math.sqrt(dr * dr + dc * dc);
    }
}
