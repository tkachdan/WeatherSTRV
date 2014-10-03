package android.weather.tkachdan.com.weather.utils;

import android.util.Log;
import android.weather.tkachdan.com.weather.fragments.TodayFragment;
import android.weather.tkachdan.com.weather.models.CurrentWeather;
import android.weather.tkachdan.com.weather.models.ForecastEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class JsonParser {
    /*
    * number - number of days to forecast(default 5)
    * */
    public ArrayList<ForecastEntity> parseForecast() {
        ArrayList<ForecastEntity> list = new ArrayList<ForecastEntity>();
        GregorianCalendar gc = new GregorianCalendar();
        Calendar calendar = Calendar.getInstance();
        int dayCal = calendar.get(Calendar.DAY_OF_WEEK);
        gc.add(Calendar.DAY_OF_WEEK, 1);
        for (int i = 0; i < 5; i++) {
            String imageURL;
            dayCal = gc.get(Calendar.DAY_OF_WEEK);
            String day = getDay(dayCal);
            String temp_C;
            String temp_F;
            String wether_desc;
            JSONObject all = null;

            try {
                all = new JSONObject(TodayFragment.JSON);
                JSONObject data = all.getJSONObject("data");
                JSONArray weather = data.getJSONArray("weather");
                JSONObject index = weather.getJSONObject(i);
                temp_C = index.getString("tempMaxC");
                temp_F = index.getString("tempMaxF");
                temp_C = index.getString("tempMaxC");
                wether_desc = index.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
                imageURL = index.getJSONArray("weatherIconUrl").getJSONObject(0).getString("value");

                ForecastEntity entity = new ForecastEntity(imageURL, day, temp_C + TodayFragment.DEGREE + "C",
                        temp_F + TodayFragment.DEGREE + "F", wether_desc);
                list.add(entity);

            } catch (Exception e) {
                e.printStackTrace();
            }
            gc.add(Calendar.DAY_OF_WEEK, 1);
        }


        return list;
    }

    public String getDay(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";

            case Calendar.WEDNESDAY:
                return "Wednesday";

            case Calendar.THURSDAY:
                return "Thursday";

            case Calendar.FRIDAY:
                return "Friday";

            case Calendar.SATURDAY:
                return "Saturday";

            case Calendar.SUNDAY:
                return "Sunday";
        }
        return "";

    }

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

            JSONArray nearestAreaJson = data.getJSONArray("nearest_area");
            JSONObject tmp2 = nearestAreaJson.getJSONObject(0);
            JSONArray countryJson = tmp2.getJSONArray("country");
            JSONObject cJson = countryJson.getJSONObject(0);
            country = cJson.getString("value").toString();

            JSONArray regionJson = tmp2.getJSONArray("region");
            JSONObject tmp3 = regionJson.getJSONObject(0);
            region = tmp3.getString("value").toString();

            currentWeather = new CurrentWeather(humidity, precipMM, pressure, temp_C, temp_F, weatherDesc,
                    wetherImageURL, windDir, windspeedKmph, windspeedMiles, region, country);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return currentWeather;
    }

}
