package assignment6_designpatterns.mazeSolver;

public interface HeuristicStrategy {
    int calculate(MazeNode source, MazeNode destination);
}
