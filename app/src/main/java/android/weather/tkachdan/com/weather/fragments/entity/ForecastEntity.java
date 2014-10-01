package android.weather.tkachdan.com.weather.fragments.entity;

import android.graphics.Bitmap;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class ForecastEntity {
    private Bitmap image;
    private String date;
    private String temp;
    private String wetherDesc;


    public ForecastEntity() {

    }

    public ForecastEntity(Bitmap image, String date, String temp, String wetherDesc) {
        this.image = image;
        this.date = date;
        this.temp = temp;
        this.wetherDesc = wetherDesc;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWetherDesc() {
        return wetherDesc;
    }

    public void setWetherDesc(String wetherDesc) {
        this.wetherDesc = wetherDesc;
    }
}
