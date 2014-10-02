package android.weather.tkachdan.com.weather.models;

import android.graphics.Bitmap;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class ForecastEntity {
    private String image;
    private String date;
    private String temp_C;
    private String temp_F;
    private String wetherDesc;
    private Bitmap imageBitmap;

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public ForecastEntity() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(String temp_C) {
        this.temp_C = temp_C;
    }

    public String getTemp_F() {
        return temp_F;
    }

    public void setTemp_F(String temp_F) {
        this.temp_F = temp_F;
    }

    public String getWetherDesc() {
        return wetherDesc;
    }

    public void setWetherDesc(String wetherDesc) {
        this.wetherDesc = wetherDesc;
    }

    public ForecastEntity(String image, String date, String temp_C, String temp_F, String wetherDesc) {

        this.image = image;
        this.date = date;
        this.temp_C = temp_C;
        this.temp_F = temp_F;
        this.wetherDesc = wetherDesc;
    }

    @Override
    public String toString() {
        return "Forecast for the next " + date + " is  " + wetherDesc + ", " +
                temp_C + "/" + temp_F;
    }
}
