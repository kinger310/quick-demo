package com.wd.demo.interview.pdd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DFSGraph {
    private int vertices;
    private List<Integer>[] adjList;

    boolean[] visited;

    public DFSGraph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
    }

    public void findAllPaths(int source, int destination) {
        visited = new boolean[vertices];
        LinkedList<Integer> pathList = new LinkedList<>();
        pathList.add(source);
        dfs(source, destination, pathList);
    }

    private void dfs(int vertex, int destination, LinkedList<Integer> pathList) {
        if (vertex == destination) {
            System.out.println(pathList);
            return;
        }
        for (int neighbor : adjList[vertex]) {
            if (visited[neighbor]) continue;
            visited[neighbor] = true;
            pathList.add(neighbor);
            dfs(neighbor, destination, pathList);
            pathList.removeLast();
            visited[neighbor] = false;

        }

        visited[vertex] = false;
    }

    public static void main(String[] args) {
        DFSGraph graph = new DFSGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.findAllPaths(0, 5);
    }
}
