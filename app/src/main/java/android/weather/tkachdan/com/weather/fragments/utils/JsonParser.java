package android.weather.tkachdan.com.weather.fragments.utils;

import android.util.Log;
import android.weather.tkachdan.com.weather.fragments.entity.CurrentWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class JsonParser {

    public CurrentWeather parse(String json) {
        String humidity;
        String precipMM;
        String pressure;
        String temp_C;
        String temp_F;
        String weatherDesc;
        String windDir;
        String windspeedKmph;
        String windspeedMiles;
        String region;
        String country;
        String wetherImageURL;

        JSONObject all = null;
        CurrentWeather currentWeather = null;
        try {
            Log.d("jsonstr", json);
            all = new JSONObject(json);
            JSONObject data = all.getJSONObject("data");
            JSONArray currentCondition = data.getJSONArray("current_condition");

            JSONObject temp1 = (JSONObject) currentCondition.get(0);

            humidity = temp1.get("humidity").toString();
            precipMM = temp1.get("precipMM").toString();
            pressure = temp1.get("pressure").toString();
            temp_C = temp1.get("temp_C").toString();
            temp_F = temp1.get("temp_F").toString();
            windDir = temp1.get("winddir16Point").toString();
            windspeedKmph = temp1.get("windspeedKmph").toString();
            windspeedMiles = temp1.get("windspeedMiles").toString();
            precipMM = temp1.get("precipMM").toString();

            JSONArray wetherDescription = temp1.getJSONArray("weatherDesc");
            JSONObject temp2 = wetherDescription.getJSONObject(0);
            weatherDesc = temp2.getString("value").toString();

            JSONArray wetherIconUrl = temp1.getJSONArray("weatherIconUrl");
            JSONObject wetherIconUrlJson = wetherIconUrl.getJSONObject(0);
            wetherImageURL = wetherIconUrlJson.getString("value").toString();
///SMTH HERE
            JSONArray nearestAreaJson = data.getJSONArray("nearest_area");
            JSONObject tmp2 = nearestAreaJson.getJSONObject(0);
            JSONArray countryJson = tmp2.getJSONArray("country");
            JSONObject cJson = countryJson.getJSONObject(0);
            country = cJson.getString("value").toString();

            JSONArray regionJson = tmp2.getJSONArray("region");
            JSONObject tmp3 = regionJson.getJSONObject(0);
            region = tmp3.getString("value").toString();

            //wetherImageURL = wetherImageURL.replaceAll("\\","/");

            currentWeather = new CurrentWeather(humidity, precipMM, pressure, temp_C, temp_F, weatherDesc,
                    wetherImageURL, windDir, windspeedKmph, windspeedMiles, region, country);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return currentWeather;
    }

}
