import java.util.*;
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
        AdjacencyList = new LinkedList[V];
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

//method to find the maximum in and out degree among all vertex
//method referenced https://www.geeksforgeeks.org/finding-in-and-out-degrees-of-all-vertices-in-a-graph/
    public void findMaxInOut(LinkedList<Integer>[] adjacencyList, int adjSize, String[] vertexList){
        int outDegree[] = new int[adjSize]; //create empty array for saving the out degree for all vertices
        int inDegree[] = new int[adjSize]; //create empty array for saving the in degree for all vertices
        //iterate through the adjList and mark out degree of all vertices
        for(int i = 0; i < adjacencyList.length; i++){
            //remove the link between "ith" vertex and itself
            if(adjacencyList[i].contains(i)){
                outDegree[i] = adjacencyList[i].size() - 1; //get the out degree of each linkedList in the adjacencyList
            }
            else {
                outDegree[i] = adjacencyList[i].size(); //get the out degree of each linkedList in the adjacencyList
            }
            //use nested for loop to increment the vertices that is being directed to within each linked list in the adjacencyList
            //thus the inDegree is computed
            for(int j = 0; j < adjacencyList[i].size(); j++){
                if(adjacencyList[i].get(j) != i)
                    inDegree[adjacencyList[i].get(j)]++;
            }
        }

        //iterate through the array that all the out degrees are stored and find the maximum out degree
        int maxOut = outDegree[0]; //initiate maxOutDegree to out[0]
        int maxOutVertex = 0; //initiate maxOutDegree vertex to vertex 0
        for(int i = 0; i < adjSize; i++){
            if(outDegree[i] > maxOut){
                maxOut = outDegree[i];
                maxOutVertex = i;
            }
        }

        //iterate through the array that all the in degrees are stored and find the maximum in degree
        int maxIn = inDegree[0]; //initiate maxOutDegree to in[0]
        int maxInVertex = 0; //initiate maxOutDegree vertex to vertex 0
        for(int i = 0; i < adjSize; i++){
            if(inDegree[i] > maxIn){
                maxIn = inDegree[i];
                maxInVertex = i;
            }
        }
        System.out.println("The maximum out degree vertex is: " + '\t' + maxOutVertex);
        System.out.println("The maximum out degree is: " + '\t' + maxOut);
        System.out.println("The maximum out degree vertex URL is: " + vertexList[maxOutVertex]);
        System.out.println("The maximum in degree vertex is: " + '\t' + maxInVertex);
        System.out.println("The maximum in degree is: " + '\t' + maxIn);
        System.out.println("The maximum in degree vertex URL is: " + vertexList[maxInVertex]);
    }

    //depth first search method to find if every vertex can traverse and reach every other vertices in the graph
    //method referenced https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
    public void DFS(int currVertex, Boolean visited[]){
        //mark current vertex as visited
        visited[currVertex] = true;
        //traverse through the linkedList associated with the current vertex and recursively find if this vertex can reach every
        //other vertices in the graph by using a list iterator
        Iterator<Integer> it = AdjacencyList[currVertex].iterator();
        while (it.hasNext()) {
            currVertex = it.next();
            if (!visited[currVertex])
                DFS(currVertex, visited);
        }
    }

    //method to create a reversed graph
    Graph getReverse() {
        Graph reverseG = new Graph(V, E); //create an instance of the reversed graph
        //traverse through all vertex of the original graph and add the current vertex to all vertices in the linkedList
        // of the current vertex
        for(int i = 0; i < this.AdjacencyList.length; i++){
            Iterator<Integer> it = this.AdjacencyList[i].listIterator();
            while(it.hasNext()){
                reverseG.AdjacencyList[it.next()].add(i);
            }
        }
        return reverseG;
    }


    //method to determine whether the graph is strongly connected
    //method referenced https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
    public Boolean isStronglyConnected(){
        //mark all the vertices in the graph as not visited
        Boolean visited[] = new Boolean[V];
        for(int i = 0; i < V; i++){
            visited[i] = false;
        }

        //use DFS
        DFS(0, visited);

        //determine if all vertices are visited by traversing through the visited array
        for(int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        //Since DFS through all vertices will be time consuming which is V^2, we create reverse graph
        // since if I can reach every vertex from a reversed graph from the current vertex, then every vertex
        //can reach the current vertex in the original graph
        Graph reverseG = getReverse();

        //mark all the vertices in the graph as not visited for preparing the second DFS
        for(int i = 0; i < V; i++){
            visited[i] = false;
        }

        //use DFS for the reverseGraph
        reverseG.DFS(0, visited);

        //determine if all vertices are visited in the reverse graph by traversing through the visited array
        for(int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                return false;
            }
        }

        //if all vertices are visited in the original and the reverse graph, then we return true
        return true;
    }

    //method to check if the graph is weakly connected
    public Boolean isWeaklyConnected(){
        //ignore the direction of the link by adding links that is in the opposite direction of the original link
        for(int i = 0; i < this.V; i++){
            Iterator<Integer> it = this.AdjacencyList[i].listIterator();
            while(it.hasNext()){
                this.AdjacencyList[it.next()].add(i);
            }
        }

        Boolean visited[] = new Boolean[this.V];
        for(int i = 0; i < visited.length; i++){
            visited[i] = false;
        }

        DFS(0, visited);
        for(int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true; //if all vertices are visited, we may conclude that the graph is weakly connected
    }
}

