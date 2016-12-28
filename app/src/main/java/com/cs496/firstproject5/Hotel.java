package com.cs496.firstproject5;

/**
 * Created by q on 2016-12-27.
 */

public class Hotel {
    private String name;
    private int price;
    private int thumbnail;
    private double toCity;
    private double toEiffel;
    private double rating;
    private int star;



    public Hotel(String name, int price, int thumbnail,double toCity, double toEiffel, double rating, int star) {
        this.name = name;
        this.price= price;
        this.thumbnail = thumbnail;
        this.toCity = toCity;
        this.toEiffel = toEiffel;
        this.rating = rating;
        this.star = star;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getToCity() { return toCity;}
    public void setToCity(double dis) { this.toCity = dis; }

    public double getToEiffel() { return toEiffel;}
    public void setToEiffel(double dis) { this.toEiffel = dis; }

    public double getRating() { return rating;}
    public void setRating(double rate) { this.rating = rate; }

    public int getStar() { return star;}
    public void setStar(int star) { this.star = star; }
}
