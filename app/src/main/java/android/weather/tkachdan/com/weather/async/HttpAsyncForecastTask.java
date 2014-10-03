package android.weather.tkachdan.com.weather.async;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.adapters.ForecastAdapter;
import android.weather.tkachdan.com.weather.listeners.ListViewListener;
import android.weather.tkachdan.com.weather.models.ForecastEntity;
import android.weather.tkachdan.com.weather.utils.JsonParser;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by tkachdan on 10/3/2014.
 */
public class HttpAsyncForecastTask extends AsyncTask<String, Void, String> {
    private View view;
    private Activity activity;
    private String imageURL;
    List<ForecastEntity> forecast;
    ArrayAdapter<ForecastEntity> adapter;
    ListView listView;

    public HttpAsyncTask(View view, Activity activity, String imageURL,
                         List<ForecastEntity> forecast,
                         ArrayAdapter<ForecastEntity> adapter, ListView listView) {
        this.view = view;
        this.activity = activity;
        this.imageURL = imageURL;
        this.forecast = forecast;
        this.adapter = adapter;
        this.listView = listView;
    }

    @Override
    protected String doInBackground(String... urls) {

        return GET(urls[0]);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        JsonParser parser = new JsonParser();
        forecast = parser.parseForecast();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String chooseCelsiusFahrenheit = sharedPreferences.getString("temperature_list1", "0");
        if (chooseCelsiusFahrenheit.equals("0")) {
            adapter = new ForecastAdapter(activity, forecast, true);
        } else {

            adapter = new ForecastAdapter(activity, forecast);
        }

        listView = (ListView) view
                .findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListViewListener(activity));
        listView.setAdapter(adapter);

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

    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Error!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
}
