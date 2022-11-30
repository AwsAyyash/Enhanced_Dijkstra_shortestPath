package com.example.proj3fx;

public class PathCost {

    private double cost;
    private Vertex path; // via which بواسطة :

    public PathCost() {
    }

    public PathCost(double cost, Vertex path) {
        this.cost = cost;
        this.path = path;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vertex getPath() {
        return path;
    }

    public void setPath(Vertex path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PathCost{" +
                "cost=" + cost +
                ", path=" + path +
                '}';
    }
}


