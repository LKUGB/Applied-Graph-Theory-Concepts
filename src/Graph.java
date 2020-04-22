import java.util.LinkedList;

/** A simple class to represent a graph.  Can be used for directed or
 *  undirected graphs by including each edge twice.
 *  Uses an adjacency list representation of the graph.
 */
class Graph{
    private int V;    // number of vertices
    private int E;    // number of edges

    // these are made public to simplify access - not a good programming practice!
    // AdjacencyList is an array of pointers to LinkedLists
    public LinkedList<Integer>[] AdjacencyList;
    public String[] VertexList;

    // constructor - must know size before constructing
    public Graph(int V, int E){
        this.V = V;  this.E = E;
        VertexList = new String[V];
        AdjacencyList = new LinkedList[E];
        // initialize with empty linkedlists
        for (int i = 0; i < V ; i++) {
            AdjacencyList[i] = new LinkedList<>();
        }
    }

    public int getV(){
        return V;
    }

    public int getE(){
        return E;
    }

    // add an edge to the graph by adding a vertex number to a linked list
    public void addEdge(int from, int to){
        // add an edge by adding a "to" value to AdjacencyList[from]
        AdjacencyList[from].add(to);
    }

    // set the label associated with a vertex
    public void setVertex(int index, String label){
        VertexList[index] = label;
    }
}