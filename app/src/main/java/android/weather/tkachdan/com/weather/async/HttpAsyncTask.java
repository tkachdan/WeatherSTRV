package android.weather.tkachdan.com.weather.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.fragments.TodayFragment;
import android.weather.tkachdan.com.weather.models.CurrentWeather;
import android.weather.tkachdan.com.weather.utils.JsonParser;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tkachdan on 10/3/2014.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {
    private ProgressDialog dialog;
    private Activity activity;
    private String imageURL;
    private View view;

    @Override
    protected String doInBackground(String... urls) {

        return get(urls[0]);
    }

    public HttpAsyncTask(Activity activity, String imageURL, View view) {
        this.activity = activity;
        this.imageURL = imageURL;
        this.view = view;
        dialog = new ProgressDialog(activity);

    }

    protected void onPreExecute() {
        this.dialog.setMessage("Downloading data");
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();

    }


    @Override
    protected void onPostExecute(String result) {
        this.dialog.dismiss();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        Log.d("json", result);
        CurrentWeather currentWeather = parseJson(result);
        imageURL = currentWeather.getWetherIconUrl();
        new DownloadImageTask((ImageView) view.findViewById(R.id.imageView))
                .execute(currentWeather.getWetherIconUrl());

        TextView area = (TextView) view.findViewById(R.id.area_textView);
        area.setText(currentWeather.getRegion() + ", " + currentWeather.getCoutry());
        String chooseCelsiusFahrenheit = sharedPreferences.getString("temperature_list1", "0");

        if (chooseCelsiusFahrenheit.equals("0")) {
            TextView temp = (TextView) view.findViewById(R.id.temp_textView);
            temp.setText(currentWeather.getTemp_C() + TodayFragment.DEGREE + "C | " + currentWeather.getWeatherDesc());
        } else {
            TextView temp = (TextView) view.findViewById(R.id.temp_textView);
            temp.setText(currentWeather.getTemp_F() + TodayFragment.DEGREE + "F | " + currentWeather.getWeatherDesc());
        }


        TextView humidity = (TextView) view.findViewById(R.id.humidity_textView);
        humidity.setText(currentWeather.getHumidity() + "%");
        TextView precip = (TextView) view.findViewById(R.id.precip_textView);
        precip.setText(currentWeather.getPrecipMM() + " mm");
        TextView pressure = (TextView) view.findViewById(R.id.pressure_textView);
        pressure.setText(currentWeather.getPressure() + " hPa");

        String windSpeedChoose = sharedPreferences.getString("length_list", "0");
        if (windSpeedChoose.equals("0")) {
            TextView windSpeed = (TextView) view.findViewById(R.id.wind_speed_textView);
            windSpeed.setText(currentWeather.getWindspeedKmph() + " km/h");
        } else {
            TextView windSpeed = (TextView) view.findViewById(R.id.wind_speed_textView);
            windSpeed.setText(currentWeather.getWindspeedMiles() + " mil/h");
        }

        TextView windDir = (TextView) view.findViewById(R.id.wind_direction_textView);
        windDir.setText(currentWeather.getWindDir());


    }

    public static String get(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make get request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        TodayFragment.JSON = result;
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public CurrentWeather parseJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json);
    }
}
