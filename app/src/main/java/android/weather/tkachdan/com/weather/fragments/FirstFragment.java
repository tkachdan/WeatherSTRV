package android.weather.tkachdan.com.weather.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.fragments.async.DownloadImageTask;
import android.weather.tkachdan.com.weather.fragments.entity.CurrentWeather;
import android.weather.tkachdan.com.weather.fragments.utils.JsonParser;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CurrentWeather currentWeather;

    public Bitmap bitmap;

    public String imageURL = "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView text = (TextView) getView().findViewById(R.id.area_textView);


        new HttpAsyncTask().execute("http://api.worldweatheronline.com/free/v1/weather.ashx?q=40%2C20&format=json&num_of_days=5&includelocation=yes&key=4ae48b676ad301da1f7fcb2c1e351b291c8223f0");


    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Log.d("json", result);
            CurrentWeather currentWeather = parseJson(result);
            imageURL = currentWeather.getWetherIconUrl();
            new DownloadImageTask((ImageView) getView().findViewById(R.id.imageView))
                    .execute(currentWeather.getWetherIconUrl());

            TextView area = (TextView) getView().findViewById(R.id.area_textView);
            area.setText(currentWeather.getRegion() + ", " + currentWeather.getCoutry());

            TextView temp = (TextView) getView().findViewById(R.id.temp_textView);
            temp.setText(currentWeather.getTemp_C() + " | " + currentWeather.getWeatherDesc());

            TextView humidity = (TextView) getView().findViewById(R.id.humidity_textView);
            humidity.setText(currentWeather.getHumidity());
            TextView precip = (TextView) getView().findViewById(R.id.precip_textView);
            precip.setText(currentWeather.getPrecipMM());
            TextView pressure = (TextView) getView().findViewById(R.id.pressure_textView);
            pressure.setText(currentWeather.getPressure());

            TextView windSpeed = (TextView) getView().findViewById(R.id.wind_speed_textView);
            windSpeed.setText(currentWeather.getWindspeedKmph());

            TextView windDir = (TextView) getView().findViewById(R.id.wind_direction_textView);
            windDir.setText(currentWeather.getWindDir());


        }
    }

    public CurrentWeather parseJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json);
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

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
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

        return result;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
