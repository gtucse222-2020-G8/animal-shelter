package com.company;

public class Filter {

    private String city;
    private String town;

    public Filter(String city, String town){
        this.city = city;
        this.town = town;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
