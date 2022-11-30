package com.example.proj3fx;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {

    private Vertex v;
    private double weight;

    public Edge() {
    }

    public Edge(Vertex v, double w) {
        this.v = v;
        this.weight = w;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + v + "," + weight + ")";
    }


    @Override
    public int compare(Edge o1, Edge o2) {
        if (o1.weight < o2.weight)
            return -1;

        if (o1.weight > o2.weight)
            return 1;

        return 0;
    }
}

