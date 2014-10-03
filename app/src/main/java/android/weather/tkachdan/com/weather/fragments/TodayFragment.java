package android.weather.tkachdan.com.weather.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.async.HttpAsyncTask;
import android.weather.tkachdan.com.weather.models.CurrentWeather;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String DEGREE = "\u00b0";
    public static String JSON;
    public static Double LAT;
    public static Double LON;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CurrentWeather currentWeather;

    public Bitmap bitmap;

    public String imageURL = "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";

    private OnFragmentInteractionListener mListener;

    LocationClient mLocationClient;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TodayFragment() {

    }

    // dialogType: 1. Location error OR 2. Internet error
    public void showErrorDialog(String msg, final int dialogType) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error");  // GPS not found
        builder.setMessage(msg); // Want to enable?

        builder.setCancelable(false);
        builder.setPositiveButton("Turn on", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogType == 1)
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                if (dialogType == 2)
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        builder.create().show();
    }

    private boolean servicesConnected() {
        boolean result;
        // Check that Google Play services is available
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            ProgressDialog dialog = new ProgressDialog(getActivity());
            result = false;
            showErrorDialog("Location should be turned on to proceed.", 1);
        } else {
            result = true;
        }

        return result;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mLocationClient = new LocationClient(getActivity().getApplicationContext(), this, this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (servicesConnected()) {
            if (mLocationClient == null) {
                mLocationClient = new LocationClient(getActivity().getApplicationContext(), this, this);
            }
            mLocationClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.disconnect();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (servicesConnected()) {

            Location location = mLocationClient.getLastLocation();
            LAT = location.getLatitude();
            LON = location.getLongitude();
            if (isNetworkAvailable()) {
                View view = getView();
                Activity activity = getActivity();
                new HttpAsyncTask(activity, imageURL, view).execute("http://api.worldweatheronline.com/free/v1/weather.ashx?q=" + LAT
                        + "%2C" + LON + "&format=json&num_of_days=5&includelocation=yes" +
                        "&key=4ae48b676ad301da1f7fcb2c1e351b291c8223f0");
                JSON = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=" +
                        "" + LAT + "%2C" + LON + "&format=json&num_of_days=5&includelocat" +
                        "ion=yes&key=4ae48b676ad301da1f7fcb2c1e351b291c8223f0";
            } else {

                showErrorDialog("Internet should be turned on to proceed.", 2);
            }
        }

    }

    @Override
    public void onDisconnected() {
        mLocationClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

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


    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(Uri uri);
    }

}
