package graph.search;

import graph.ds.Graph;
import graph.ds.Graph.Node;
import graph.ds.Graph.Edge;

import java.util.Stack;

public class DFS {

    public void dfs(Graph g, int start) {

        boolean[] visited = new boolean[g.size()];
        Stack<Node> stack = new Stack<>();
        stack.push(g.get(start));

        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            if (!visited[curr.key]) {
                visited[curr.key] = true;

                for (Edge e : curr.outgoing) {
                    stack.push(g.get(e.dest));
                }
            }
        }
    }

    public void recDFS(Graph g, int start) {

        boolean[] visited = new boolean[g.size()];
        recDFSHelper(g, start, visited);
    }
    private void recDFSHelper(Graph g, int curr, boolean[] visited) {

        visited[curr] = true;

        for (Edge e : g.get(curr).outgoing) {
            if (!visited[e.dest]) {
                recDFSHelper(g, e.dest, visited);
            }
        }
        System.out.print(curr + " ");       // --- output is printed in reverse order
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

        DFS dfs = new DFS();
        dfs.dfs(ug, 0);     // 0 2 5 6 7 3 4 1

        dfs.recDFS(ug, 0);
    }
}
