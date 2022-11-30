package com.example.proj3fx;

import java.util.*;

public class Dijkstra {


    /*  private ArrayList<Vertex> path; // this is to use it for the return(the path) to put it in the result on the GUI*/
    private PathCost[] pathDist; // it has: cost + path(via vertex)
    private PriorityQueue<Edge> heap;
    private Set<Vertex> considered; // considered (مستقرة)

    /*public double[] getPathDist() {
        return pathDist;
    }*/

    public PathCost[] getPathDist() {
        return pathDist;
    }

    public void dijkstra(List<Edge>[] adjList, Vertex src, Vertex dest) {
        //this.adj = adjList;

        int v = adjList.length; // the number of vertexes
        pathDist = new PathCost[v];
        /*  path = new ArrayList<>();*/
        considered = new HashSet<>(); // this has the already taken(considered) vertex-es vertices
        heap = new PriorityQueue<>(v, new Edge());
        for (int i = 0; i < v; i++) {
            pathDist[i] = new PathCost();
            pathDist[i].setCost(Integer.MAX_VALUE);
        }

        // Add source node to the priority queue, with "weight=0"
        heap.add(new Edge(src, 0));

        // Distance to the source is 0
        pathDist[src.getNumToIndexArray()].setCost(0);
        pathDist[src.getNumToIndexArray()].setPath(src);


        //loops O(E) lime
        // int countTheRounds=0;
        while (considered.size() != v) { // while: i did not finish all the vertex-es

            // Terminating condition check when
            // the min_heap is empty, return
            if (heap.isEmpty())
                return;

            // Removing the minimum distance vertex from the min heap
            Vertex u = heap.remove().getV();


            if (u.getCity().equals(dest.getCity())) {
                // System.out.println(countTheRounds);
                return;
            }

            // We don't have to call neighbors(u)
            // if 'u' is already present in the considered list.
            if (considered.contains(u))
                // skips execution the following check
                continue;


            considered.add(u); // mark as taken

            neighbours(adjList, u);
            //countTheRounds++;

        }
    }

   /* public ArrayList<Vertex> getPath() {
        return path;
    }*/

    private void neighbours(List<Edge>[] adjList, Vertex u) {

        double edgeDistance;
        double newDistance;

        // All the neighbors of "u"
        for (int i = 0; i < adjList[u.getNumToIndexArray()].size(); i++) { // for each 'edge' of this vertex
            Edge edge = adjList[u.getNumToIndexArray()].get(i);

            // If current Vertex hasn't already been processed
            if (!considered.contains(edge.getV())) {
                edgeDistance = edge.getWeight();
                newDistance = pathDist[u.getNumToIndexArray()].getCost() + edgeDistance;

                // If new distance is less in cost
                if (newDistance < pathDist[edge.getV().getNumToIndexArray()].getCost()) {
                    pathDist[edge.getV().getNumToIndexArray()].setCost(newDistance);
                    pathDist[edge.getV().getNumToIndexArray()].setPath(u);

                }

                // Add the current edge to the heap
                heap.add(new Edge(edge.getV(), pathDist[edge.getV().getNumToIndexArray()].getCost()));
            }
        }
    }


    public ArrayList<Vertex> getResultsPaths(Vertex src, Vertex dest) {
        ArrayList<Vertex> results = new ArrayList<>();
        Vertex vertex = this.getPathDist()[dest.getNumToIndexArray()].getPath();

        while (true) {

            if (vertex == null)
                break;

            results.add(0, vertex);


            vertex = this.getPathDist()[vertex.getNumToIndexArray()].getPath();

            if (vertex.getCity().equals(src.getCity()))
                break;

        }
        results.add(0, src);

        results.add(dest);
        System.out.println(results.toString());
        return results;
    }
}


