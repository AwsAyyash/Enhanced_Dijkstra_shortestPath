package com.example.proj3fx;

public class City {

    private String name;
    private double longitude;
    private double latitude;
    private double x; // for the map
    private double y; // for the map

    public City(String name, double longitude, double latitude, double x, double y) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.x = x;
        this.y = y;
    }

    public City() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {

        City city = (City) o;
        return this.name.equalsIgnoreCase(city.getName());

    }

    public boolean equalsCoordinates(City city) {
        return (this.x >= city.x - 3 && this.x < city.x + 3 || this.x >= city.x + 3 && this.x < city.x - 3) &&
                (this.y >= city.y - 3 && this.y < city.y + 3 || this.y >= city.y + 3 && this.y < city.y - 3);
        //return this.longitude == city.longitude && this.latitude == city.latitude;

    }
}


