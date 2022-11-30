package com.example.proj3fx;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    final static int constValueForDraw = 500;
    static int numOfClicks = 0;

    public static Graph readFile(File file, Pane pane, ComboBox<String> comboBoxCitiesNamesSrc, ComboBox<String> comboBoxCitiesNamesDest) throws Exception {

        try {
            Scanner scanner = new Scanner(file);

            String[] arrLengths = scanner.nextLine().trim().split(" ");


            int numOfCities = Integer.parseInt(arrLengths[0]); // means the Vertices
            int numOfEdges = Integer.parseInt(arrLengths[1]); // means the adjacent (edges)

            Graph graph = new Graph(numOfCities);
            int i = 0;
            while (i < numOfCities) {

                String line = scanner.nextLine().trim();
                String[] arr = line.split(" ");
                City city = new City(arr[0], Double.parseDouble(arr[2]), Double.parseDouble(arr[1]), scaleX(Double.parseDouble(arr[2])), scaleY(Double.parseDouble(arr[1])));
                Vertex vi = new Vertex(city, i);
                graph.insertVertex(vi);
                i++;

                HelloApplication.addCircleToPane(city, pane);
                comboBoxCitiesNamesSrc.getItems().add(city.getName());
                comboBoxCitiesNamesDest.getItems().add(city.getName());

            }


            i = 0;
            while (i < numOfEdges) {
                String[] arr = scanner.nextLine().trim().split(" ");

                // Graph.Edge edge = new Graph.Edge();
                City citySrc = new City();
                citySrc.setName(arr[0]);

                City cityDest = new City();
                cityDest.setName(arr[1]);

                Vertex srcVertex = new Vertex();
                Vertex destVertex = new Vertex();

                ArrayList<Vertex> arrList = graph.getArrayList();
                boolean foundDest = false;
                boolean foundSrc = false;

                for (int j = 0; j < arrList.size(); j++) {
                    if (!foundDest) {
                        if (cityDest.equals(arrList.get(j).getCity())) {
                            destVertex = arrList.get(j);
                            foundDest = true;
                        }
                    }
                    if (!foundSrc) {
                        if (citySrc.equals(arrList.get(j).getCity())) {
                            srcVertex = arrList.get(j);
                            foundSrc = true;
                        }
                    }
                    //if (foundDest && foundSrc)
                    //  break;

                }
                if (!foundSrc || !foundDest) {
                    System.out.println("no:" + i);
                    // throw new NoSuchFieldException();
                    throw new IllegalArgumentException();
                }

               // double xSrc = srcVertex.getCity().getLongitude();
                //double ySrc = srcVertex.getCity().getLatitude();

                //double xDest = destVertex.getCity().getLongitude();
                //double yDest = destVertex.getCity().getLatitude();

                // double weight = Math.sqrt(Math.pow((xSrc - xDest), 2) + Math.pow((ySrc - yDest), 2));


                double weight = getDistanceFromLatLonInKm(srcVertex.getCity().getLatitude(),
                        srcVertex.getCity().getLongitude(), destVertex.getCity().getLatitude(), destVertex.getCity().getLongitude());
                graph.addEdge(srcVertex, destVertex, weight);

                i++;

            }
            return graph;

        } catch (Exception e) {
            System.out.println(e);

        }
        return null;

    }


    private static double scaleY(double latitude) {
        return -178.59 * latitude + 5950;
        //return -178.69 * v.getCity().getLatitude() + 5950;

    }

    private static double scaleX(double longitude) {
        return 331.77932 * longitude - 11292.2733;
        //return 330.77932 * v.getCity().getLongitude() - 11292.2733;

    }

    private static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double ditance = R * c; // Distance in km
        return ditance;
    }

    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
}


