package graph.ds;

import lombok.Builder;

import java.util.*;

public class Graph {
    @Builder
    public static class Edge {
        public int src;
        public int dest;
        public int weight;
        @Override
        public String toString() { return "(" + src + "," + dest + "," + weight + ") "; }
    }
    @Builder
    public static class Node {
        public int key;
        public List<Edge> outgoing;
        // --- utility field (e.g. Dijkstra's Score or weight of vertex etc...)
        public int score;
        @Override
        public String toString() { return key + "," + score + ": "; }
    }

    private final Map<Integer, Node> map;

    public Graph(int[] vertices) {
        map = new HashMap<>();
        Arrays.stream(vertices).forEach(v -> map.put(v, Node.builder().key(v).outgoing(new ArrayList<>()).build()));
    }

    /**
     * Creates a weighted edge in both directions for undirected graphs.
     * @param src source node
     * @param dest destination node
     * @param weight edge weight
     */
    public void connect(int src, int dest, int weight) {
        map.get(src).outgoing.add(Edge.builder().src(src).dest(dest).weight(weight).build());
        map.get(dest).outgoing.add(Edge.builder().src(dest).dest(src).weight(weight).build());
    }

    /**
     * Creates a weighted edge in one direction for directed graphs.
     * @param src source node
     * @param dest destination node
     * @param weight edge weight
     */
    public void edge(int src, int dest, int weight) {
        map.get(src).outgoing.add(Edge.builder().src(src).dest(dest).weight(weight).build());
    }

    public Node get(int key) { return map.get(key); }
    public int size() { return map.size(); }
    public Set<Integer> vertices() { return map.keySet(); }

    // --- print graph
    public void print() {
        for (Node v : map.values()) {
            System.out.print(v);
            for (Edge e : v.outgoing) {
                System.out.print(e);
            }
            System.out.println();
        }
        System.out.println();
    }
}
