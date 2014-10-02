package android.weather.tkachdan.com.weather.models;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class CurrentWeather {
    public String humidity;
    public String precipMM;
    public String pressure;
    public String temp_C;
    public String temp_F;
    public String weatherDesc;
    public String wetherIconUrl;
    public String windDir;
    public String windspeedKmph;
    public String windspeedMiles;
    public String region;
    public String coutry;

    public CurrentWeather(String humidity, String precipMM, String pressure, String temp_C, String temp_F, String weatherDesc,
                          String wetherIconUrl, String windDir, String windspeedKmph, String windspeedMiles, String region, String coutry) {
        this.humidity = humidity;
        this.precipMM = precipMM;
        this.pressure = pressure;
        this.temp_C = temp_C;
        this.temp_F = temp_F;
        this.weatherDesc = weatherDesc;
        this.wetherIconUrl = wetherIconUrl;
        this.windDir = windDir;
        this.windspeedKmph = windspeedKmph;
        this.windspeedMiles = windspeedMiles;
        this.region = region;
        this.coutry = coutry;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(String precipMM) {
        this.precipMM = precipMM;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
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

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWetherIconUrl() {
        return wetherIconUrl;
    }

    public void setWetherIconUrl(String wetherIconUrl) {
        this.wetherIconUrl = wetherIconUrl;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(String windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public void setWindspeedMiles(String windspeedMiles) {
        this.windspeedMiles = windspeedMiles;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCoutry() {
        return coutry;
    }

    public void setCoutry(String coutry) {
        this.coutry = coutry;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "humidity='" + humidity + '\'' +
                ", precipMM='" + precipMM + '\'' +
                ", pressure='" + pressure + '\'' +
                ", temp_C='" + temp_C + '\'' +
                ", temp_F='" + temp_F + '\'' +
                ", weatherDesc='" + weatherDesc + '\'' +
                ", wetherIconUrl='" + wetherIconUrl + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windspeedKmph='" + windspeedKmph + '\'' +
                ", windspeedMiles='" + windspeedMiles + '\'' +
                ", region='" + region + '\'' +
                ", coutry='" + coutry + '\'' +
                '}';
    }
}
