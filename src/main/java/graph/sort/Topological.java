package graph.sort;

import graph.ds.Graph;
import graph.ds.Graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topological {

    private final boolean[] visited;
    private final Graph dag;
    private final List<Integer> ordering;

    public Topological(Graph dag) {
        this.dag = dag;
        visited = new boolean[dag.size()];
        ordering = new ArrayList<>();
    }

    public List<Integer> sort() {

        for (int vertex : dag.vertices()) {
            if (!visited[vertex]) {
                recDFS(dag, vertex);
            }
        }

        return ordering;
    }

    private void recDFS(Graph dag, int vertex) {

        visited[vertex] = true;
        for (Edge e : dag.get(vertex).outgoing) {
            if (!visited[e.dest]) {
                recDFS(dag, e.dest);
            }
        }
        ordering.add(vertex);
    }

    public static void main(String[] args) {

        int[] vertices = { 0, 1, 2, 3, 4, 5, 6 };

        Graph dag = new Graph(vertices);
        dag.edge(1, 3, 1);
        dag.edge(1, 6, 1);
        dag.edge(3, 5, 1);
        dag.edge(6, 2, 1);
        dag.edge(0, 5, 1);
        dag.edge(0, 2, 1);
        dag.edge(5, 4, 1);
        dag.edge(5, 2, 1);
        dag.edge(2, 4, 1);

        Topological topo = new Topological(dag);
        List<Integer> ordering = topo.sort();

        Collections.reverse(ordering);

        System.out.println("topological order: " + ordering);
    }
}
