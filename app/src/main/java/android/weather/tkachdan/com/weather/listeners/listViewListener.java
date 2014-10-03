package android.weather.tkachdan.com.weather.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.weather.tkachdan.com.weather.models.ForecastEntity;
import android.widget.AdapterView;

/**
 * Created by tkachdan on 10/3/2014.
 */
public class ListViewListener implements AdapterView.OnItemClickListener {
    Activity activity;

    public ListViewListener(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        ForecastEntity forecast = (ForecastEntity) parent.getAdapter().getItem(position);
        sendIntent.putExtra(Intent.EXTRA_TEXT, forecast.toString());
        sendIntent.setType("text/plain");
        Bundle options = new Bundle();


        activity.startActivity(Intent.createChooser(sendIntent, "Share"));

    }
}
