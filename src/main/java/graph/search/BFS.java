package graph.search;

import graph.ds.Graph;
import graph.ds.Graph.Edge;
import graph.ds.Graph.Node;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {

    public void bfs(Graph g, int start) {

        boolean[] visited = new boolean[g.size()];
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(g.get(start));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Edge e : curr.outgoing) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.offer(g.get(e.dest));

                    System.out.print(e.dest + " ");
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] vertices = { 0, 1, 2, 3, 4, 5, 6, 7 };

        Graph ug = new Graph(vertices);
        ug.connect(0, 1, 1);
        ug.connect(0, 2, 1);
        ug.connect(1, 2, 1);
        ug.connect(2, 3, 1);
        ug.connect(2, 4, 1);
        ug.connect(2, 5, 1);
        ug.connect(3, 6, 1);
        ug.connect(5, 6, 1);
        ug.connect(6, 7, 1);
        ug.print();

        BFS bfs = new BFS();
        bfs.bfs(ug, 0);
    }
}
