/**
 *   Startup code for Project 4 - CSC 221 - Spring 2020
 *
 *  Honor Pledge:
 *  The code submitted for this project was developed by
 *  YOUR NAME HERE without outside assistance or consultation
 *  except as allowed by the instructions for this project.
 *  Any use of code from web sites is clearly documented.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Graph G = null;
        // open a file containing links (edges), read the data and construct the graph G
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/links.dat"));
            String line = br.readLine();
            // first line contains V and E
            int V = Integer.parseInt(line.split(" ")[0]);
            int E = Integer.parseInt(line.split(" ")[1]);

            G = new Graph(V, E);
            // read the rest of the lines containing one "from" -> "to" pair per line
            while ((line = br.readLine()) != null) {
                int from = Integer.parseInt(line.split(" ")[0]) - 1;
                int to = Integer.parseInt(line.split(" ")[1]) - 1;
                G.addEdge(from, to);
            }
        } catch (IOException e) {
            System.out.format("Exception: %s while reading edge data.\n", e);
        }

        // read the file of vertex labels - not critical, but used in reporting
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/URLs.dat"));
            String line;
            while ((line = br.readLine()) != null) {
                int id = Integer.parseInt(line.split(" ")[0]) - 1;
                String URL = line.split(" ")[1];
                G.setVertex(id, URL);
            }
        } catch (IOException e) {
            System.out.format("Exception: %s while reading vertex labels.\n", e);
        }

        // acknowledge that graph has been constructed
        System.out.format("Constructed Directed Graph with %d Vertices and %d Edges\n",
                          G.getV(), G.getE());
    }
}
