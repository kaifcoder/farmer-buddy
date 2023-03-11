package com.example.farmerbuddy;

public class Crops {
    String crop_name;
    String image;
    String n;
    String p;
    String k;
    String rainfall;
    String humidity;
    String temp;
    String soiltype;

    public Crops(){}

    public Crops(String crop_name, String image, String n, String p, String k, String rainfall, String humidity, String temp, String soiltype) {
        this.crop_name = crop_name;
        this.image = image;
        this.n = n;
        this.p = p;
        this.k = k;
        this.rainfall = rainfall;
        this.humidity = humidity;
        this.temp = temp;
        this.soiltype = soiltype;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getSoiltype() {
        return soiltype;
    }

    public void setSoiltype(String soiltype) {
        this.soiltype = soiltype;
    }


}
