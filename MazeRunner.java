import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeRunner {
    public static MazeEncoder chosenMaze;

    public static void main(String[] args) {
        StdOut.println("Welcome to Elven's MazeRunner/Solver");
        StdOut.println("Which maze would you like to see?");
        StdOut.println("1 : Small Maze");
        StdOut.println("2 : Medium Maze");
        StdOut.println("3 : Large Maze");
        StdOut.println("4 : Huge Maze");
//        StdOut.println("5 : Generate Own Maze");
        StdOut.println("Please input the number corresponding to your Maze selection.");
        int mazeSelec = StdIn.readInt();
        if (!(mazeSelec > 0 && mazeSelec < 5)) {
            StdOut.println("You have failed to enter a valid metric number, defaulting to Medium Maze");
            mazeSelec = 2;
        }

        switch (mazeSelec) {
            case 1:
                chosenMaze = new MazeEncoder("src/smallMaze.txt");
                break;
            case 2:
                chosenMaze = new MazeEncoder("src/mediumMaze.txt");
                break;
            case 3:
                chosenMaze = new MazeEncoder("src/largeMaze.txt");
                break;
            case 4:
                chosenMaze = new MazeEncoder("src/hugeMaze.txt");
                break;
        }
        StdOut.println("Now Please input your desired Search Type:");
        StdOut.println("Which search would you like to see?");
        StdOut.println("1 : Breadth First");
        StdOut.println("2 : Depth First (iterative)");
        StdOut.println("3 : Depth First (w/ Stack)");
        int searchSelec = StdIn.readInt();
        if (!(searchSelec > 0 && searchSelec < 4)) {
            StdOut.println("You have failed to enter a valid metric number, defaulting to Breadth");
            searchSelec = 1;
        }

        GraphBuilder myGraph = new GraphBuilder(chosenMaze.sideLength * chosenMaze.sideLength);
        chosenMaze.loadText(myGraph);

        StdOut.println(myGraph);
        StdOut.println("startIndex: " + chosenMaze.startIndex);
        StdOut.println("goalIndex: " + chosenMaze.goalIndex);

        switch (searchSelec) {
            case 1:
                StdOut.print("Running Breadth First Search");
                myGraph.bfs(chosenMaze.startIndex, chosenMaze.goalIndex);
                break;
            case 2:
                StdOut.print("Running Depth First Search");
                myGraph.dfs(chosenMaze.startIndex, chosenMaze.goalIndex);
                break;
            case 3:
                StdOut.print("Running Depth First Search w/ Stack");
                myGraph.dfsStack(chosenMaze.startIndex, chosenMaze.goalIndex);
                break;
        }

    }
}
