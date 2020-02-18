import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeRunnerGraphics {
    private static int N;
    private static final int APPLICATION_SIZE = 500;
    private static double SQR_SIZE = 0.5 / N;

    //makes the 3 different states of sites
    private static final Color openSite = new Color(255, 255, 255);
    private static final Color closedSite = new Color(20, 20, 20);
    private static final Color filledSite = new Color(	255, 	150, 200);
    public static MazeEncoder chosenMaze;

    private static void draw(int r, int c) {
        StdDraw.setPenColor (openSite);
        StdDraw.filledSquare(2 * c * SQR_SIZE + SQR_SIZE, 2 * (N - r - 1) * SQR_SIZE + SQR_SIZE, SQR_SIZE);
        StdDraw.setPenColor(filledSite);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
//                if (perc.isFull(i, j) && perc.isOpen(i, j)) {
//                    StdDraw.filledSquare(2 * j * SQR_SIZE + SQR_SIZE, 2 * (N - i - 1) * SQR_SIZE + SQR_SIZE, SQR_SIZE);
//                }
            }
    }


    public static void main(String[] args) {
//        StdOut.println("Welcome to Elven's MazeRunner/Solver w/ Graphics!");
//        StdOut.println("Which maze would you like to see?");
//        StdOut.println("1 : Small Maze");
//        StdOut.println("2 : Medium Maze");
//        StdOut.println("3 : Large Maze");
//        StdOut.println("4 : Huge Maze");
//        StdOut.println("5 : Generate Own Maze");
//        StdOut.println("Please input the number corresponding to your Maze selection.");
//        int mazeSelec = StdIn.readInt();
//        if (!(mazeSelec > 0 && mazeSelec < 6)) {
//            StdOut.println("You have failed to enter a valid metric number, defaulting to Medium Maze");
//            mazeSelec = 2;
//        }
//
//        switch (mazeSelec) {
//            case 1:
//                chosenMaze = new MazeEncoder("src/smallMaze.txt");
//                break;
//            case 2:
//                chosenMaze = new MazeEncoder("src/mediumMaze.txt");
//                break;
//            case 3:
//                chosenMaze = new MazeEncoder("src/largeMaze.txt");
//                break;
//            case 4:
//                chosenMaze = new MazeEncoder("src/hugeMaze.txt");
//                break;
//        }
        StdDraw.setCanvasSize(APPLICATION_SIZE, APPLICATION_SIZE);
        StdDraw.setPenColor(closedSite);
        StdDraw.filledSquare(APPLICATION_SIZE / 2, APPLICATION_SIZE / 2, APPLICATION_SIZE);



        GraphBuilder myGraph = new GraphBuilder(chosenMaze.sideLength * chosenMaze.sideLength);
        chosenMaze.loadText(myGraph);

        StdOut.println(myGraph);
        StdOut.println("startIndex: " + chosenMaze.startIndex);
        StdOut.println("goalIndex: " + chosenMaze.goalIndex);
        myGraph.bfs(chosenMaze.startIndex, chosenMaze.goalIndex);


    }
}
