import java.util.*;
import java.util.LinkedList;

//referenced: Textbook pg. 378
public class Vertex {
    public LinkedList<Integer>[] AdjacencyList;
    public ArrayList<Boolean> known;
    public ArrayList<Integer> dist;
    public Vertex previous;

    //method to print the shortest path after dijkstra has run
    void printPath(Vertex v) {
        if (v.previous != null) {
            printPath(v.previous);
            System.out.print(" to ");
        }
        System.out.print(v);
    }

    //dijkstra algorithm
    void dijkstra(Vertex s, int initialIndex){
        for(int i = 0; i < s.AdjacencyList.length; i++){
            s.dist.set(i, -1);
            s.known.set(i, false);
        }
        s.dist.set(initialIndex, 0);

        //compare to find the smallest distance for this vertex

        }
    }

