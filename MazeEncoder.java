import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


//encodes Maze txt file into Graph
public class MazeEncoder {
    public String file;
    public int sideLength;
    public int startIndex;
    public int goalIndex;


    public MazeEncoder(String mazeFile){
        file = mazeFile;
        sideLength = findMazeLeng();
        startIndex = 0;
        goalIndex = 0;
    }

    //outputs MazeSize, number of verticies
    //assumption: maze is square
    private int findMazeLeng(){
        int mazeLength = 0;
        try {
            //reads only 1 line, to determine length
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String currLine = bufferedReader.readLine();
            mazeLength = currLine.length();

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mazeLength;
    }

    //loads Text into currGraph
    public void loadText(GraphBuilder currGraph) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            //make the "-1st" line, used for init w/ 0th line.
            String prevLine = "";
            String currLine;
            int currLineNum = 0;

            while ((currLine = bufferedReader.readLine()) != null) {
                parseLine(currGraph, prevLine, currLine, currLineNum);

                currLineNum++;
                prevLine = currLine;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //parses each individual Line
    private void parseLine(GraphBuilder currGraph, String prevLine, String currLine, int currLineNum){
        //skip process, since line is "" and has no connections
        if (currLineNum == 0) {return;}

        //for each elem, connect left & upwards
        //begin for loop at 1, bc no need to check 0th & -1st col
        for(int i = 1; i<sideLength; i++){
            int vertIndex = getVertIndex(currLineNum,i);
            if (isOpenSpace(currLine.charAt(i))){
                //check left for openSpace
                if(isOpenSpace(currLine.charAt(i-1))){
                    currGraph.addEdge(vertIndex, vertIndex-1);
                }

                //check above for OpenSpace
                if(isOpenSpace(prevLine.charAt(i))){
                    currGraph.addEdge(vertIndex, vertIndex-sideLength);
                }

                //sets startIndex, if at S
                if(currLine.charAt(i)=='S'){
                    startIndex = vertIndex;
                }

                //sets goalIndex, if at G
                if(currLine.charAt(i)=='G'){
                    goalIndex = vertIndex;
                }
            }
        }
    }

    private boolean isOpenSpace(char currChar){
        return currChar=='.'||currChar=='S'||currChar=='G';
    }


    private int getVertIndex(int currLineNum, int currCharNum){
        return sideLength*currLineNum + currCharNum;
    }

    //test client
    public static void main(String[] args) {
        MazeEncoder myMazeEnc = new MazeEncoder("src/smallMaze.txt");
        GraphBuilder myGraph = new GraphBuilder(myMazeEnc.sideLength * myMazeEnc.sideLength);
        myMazeEnc.loadText(myGraph);

        StdOut.println(myGraph);
        StdOut.println("startIndex: " + myMazeEnc.startIndex);
        StdOut.println("goalIndex: " + myMazeEnc.goalIndex);

        myGraph.dfsStack(myMazeEnc.startIndex, myMazeEnc.goalIndex);
    }

}
