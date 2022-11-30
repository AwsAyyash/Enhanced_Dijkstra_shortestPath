package com.example.proj3fx;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {


    private List<Edge>[] G; // adjacency list: array of LinkedList

    private int V; // num of vertex
    private ArrayList<Vertex> arrayList;

    public Graph(int v) { // v: num of vertecies
        this.V = v;
        arrayList = new ArrayList<>(V);
        G = new LinkedList[V];
        for (int i = 0; i < G.length; i++)
            G[i] = new LinkedList<>();
    }

    public int getV() {
        return V;
    }

    public ArrayList<Vertex> getArrayList() {
        return arrayList;
    }

    public List<Edge>[] getG() {
        return G;
    }

    public void insertVertex(Vertex vertex) {
        arrayList.add(vertex);
    }

    boolean isAdjacent(Vertex u, Vertex v) {
        for (Edge i : G[u.getNumToIndexArray()])
            if (i.getV().equals(v))
                return true;
        return false;
    }

    void addEdge(Vertex u, Vertex v, double wight) { // u: src, v: dest, weight = cost
        G[u.getNumToIndexArray()].add(new Edge(v, wight));
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < G.length; i++)
            result += i + "=>" + G[i] + "\n";
        return result;
    }


}

