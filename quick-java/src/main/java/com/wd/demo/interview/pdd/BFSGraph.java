package com.wd.demo.interview.pdd;

import java.util.*;

public class BFSGraph {
    private int V;
    private List<Integer>[] adjList;

    public BFSGraph(int V) {
        this.V = V;
        adjList = new List[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    public List<List<Integer>> findAllPaths(int start, int end) {
        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNode = currentPath.get(currentPath.size() - 1);

            if (lastNode == end) {
                paths.add(new ArrayList<>(currentPath));
            }

            List<Integer> neighbors = adjList[lastNode];
            for (int neighbor : neighbors) {
                if (!currentPath.contains(neighbor)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.offer(newPath);
                }
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        int V = 5;
        BFSGraph graph = new BFSGraph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        int start = 0;
        int end = 4;
        List<List<Integer>> paths = graph.findAllPaths(start, end);

        System.out.println("All paths from " + start + " to " + end + ":");
        for (List<Integer> path : paths) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}

