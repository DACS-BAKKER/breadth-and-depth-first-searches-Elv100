import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;
import javafx.css.converter.LadderConverter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GraphBuilder {
    private int vertexNum;
    private int edgesNum;
    private boolean[][] adjMatrix;

    //makes empty graph/adjMatrix with vertexNum size
    public GraphBuilder(int vertexNum) {
        if (vertexNum < 1) return;
        this.vertexNum = vertexNum;
        this.edgesNum = 0;
        this.adjMatrix = new boolean[vertexNum][vertexNum];
    }


    //connects vertex a and b with edge
    public void addEdge(int vertA, int vertB) {
        //if not already connected,
        if (!adjMatrix[vertA][vertB]) {
            //then connect both directions, inc edgesNum
            adjMatrix[vertA][vertB] = true;
            adjMatrix[vertB][vertA] = true;
            edgesNum++;
        }
    }

    //returns num of vert
    public int getVertexNum() {
        return vertexNum;
    }

    //returns num of edges
    public int getEdgesNum() {
        return edgesNum;
    }

    //returns if Vert A and B are connected by edge
    public boolean isConnected(int vertA, int vertB) {
        return adjMatrix[vertA][vertB];
    }

    public Iterable<Integer> adjacent(int vertA) {
        return new AdjacentIterator(vertA);
    }

    private class AdjacentIterator implements Iterator<Integer>, Iterable<Integer> {
        private int vertA;
        private int currVert;

        AdjacentIterator(int vertA) {
            this.vertA = vertA;
            currVert = 0;
        }

        public Iterator<Integer> iterator() {
            return this;
        }

        //runs through AdjMatrix's VertA row to find connections
        public boolean hasNext() {
            while (currVert < vertexNum) {
                if (adjMatrix[vertA][currVert]) {
                    return true;
                }
                currVert++;
            }
            return false;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currVert++;
        }

    }

    //CORRECT THIS
    //lists all Verticies and Edges (connections)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertexNum + " " + edgesNum + "\n");
        for (int v = 0; v < vertexNum; v++) {
            s.append(v + ": ");
            for (int w : adjacent(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void dfsStack(int start, int goal) {
        boolean[] checked = new boolean[vertexNum]; // defaults to all False; all not checked
        int[] prevEdge = new int[vertexNum];
        Stack myStack = new Stack();

        // Push the current source node
        myStack.push(start);

        while (!myStack.isEmpty()) {
            // pop vertex, set checked=true
            int currVert = (int) myStack.pop();
            checked[currVert] = true;

//            StdOut.println(currVert + "Aye ");

            //for each Adj vertex, if not already visited, then push and remember prev edge
            for (int adj: adjacent(currVert)){
                if(!checked[adj]){
                    myStack.push(adj);
                    prevEdge[adj] = currVert;
                }
            }
        }
        //walk back from Goal, to get to Start
        //use prevEdge
        for (int i = goal; i != start; i = prevEdge[i]) {
            StdOut.println(i);
        }
    }

    public void dfs(int start, int goal) {
        boolean[] checked = new boolean[vertexNum]; // defaults to all False; all not checked
        int[] prevEdge = new int[vertexNum];
        checked[start] = true;
        dfsIter(start, goal, checked, prevEdge);
        if (!checked[goal]) {
            StdOut.print("No Path Found");
            return;
        }
        for (int i = goal; i != start; i = prevEdge[i]) {
            StdOut.println(i);
        }
    }

    private void dfsIter(int start, int goal, boolean[] checked, int[] prevEdge) {
        checked[start] = true;
        for (int adj : adjacent(start)) {
            if (!checked[adj]) {
                prevEdge[adj] = start;
                dfsIter(adj, goal, checked, prevEdge);
            }
        }
    }


    public void bfs(int start, int goal) {
        Queue myQ = new Queue();
        boolean[] checked = new boolean[vertexNum]; // defaults to all False; all not checked
        int[] distToStart = new int[vertexNum];  //shortest distance to Start
        int[] prevEdge = new int[vertexNum];  //prev edge for shortest path

        //resets distances
        for (int v = 0; v < vertexNum; v++) {
            distToStart[v] = -1;
        }
        distToStart[start] = 0;
        checked[start] = true;
        myQ.enqueue(start);


        while (!myQ.isEmpty()) {
            int currVert = (int) myQ.dequeue();
            for (int adj : adjacent(currVert)) {
                if (!checked[adj]) {
                    prevEdge[adj] = currVert;
                    distToStart[adj] = distToStart[currVert] + 1;
                    checked[adj] = true;
                    myQ.enqueue(adj);
                }
            }
        }
        if (!checked[goal]) {
            StdOut.print("No Path Found");
            return;
        }
        for (int i = goal; distToStart[i] != 0; i = prevEdge[i]) {
            StdOut.println(i);
        }
    }

    // test client
    public static void main(String[] args) {
        int vertexNum = 4;
        Graph currGraph = new Graph(vertexNum);
        currGraph.addEdge(0, 1);
        currGraph.addEdge(0, 2);
        currGraph.addEdge(1, 3);
        currGraph.addEdge(1, 2);
        StdOut.println(currGraph);
    }
}
