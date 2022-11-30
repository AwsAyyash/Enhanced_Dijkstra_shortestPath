package com.example.proj3fx;

public class Vertex {

    private City city;
    private int numToIndexArray;


    public Vertex(City city, int numToIndexArray) {
        this.city = city;
        this.numToIndexArray = numToIndexArray;
    }

    public Vertex(City city) {
        this.city = city;
    }

    public Vertex() {
    }

    public int getNumToIndexArray() {
        return numToIndexArray;
    }

    public void setNumToIndexArray(int numToIndexArray) {
        this.numToIndexArray = numToIndexArray;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "city=" + city +
                '}';
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {

        Vertex vertex = (Vertex) o;
        return this.city.equals(vertex.getCity());
    }


}


