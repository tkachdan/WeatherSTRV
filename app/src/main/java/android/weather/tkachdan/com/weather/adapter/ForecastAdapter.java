package android.weather.tkachdan.com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.fragments.entity.ForecastEntity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class ForecastAdapter extends ArrayAdapter<ForecastEntity> {

    public ForecastAdapter(Context context, List<ForecastEntity> objects) {
        super(context, R.layout.fragment_second, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.fragment_second, parent, false);
        ImageView imageView = (ImageView) rowView
                .findViewById(R.id.forecastImage_imageView);
        TextView day = (TextView) rowView
                .findViewById(R.id.day_textView);
        TextView temp = (TextView) rowView
                .findViewById(R.id.temp_forecast_textView);
        TextView cond = (TextView) rowView
                .findViewById(R.id.cond_forecast_textView);

        imageView.setImageBitmap(this.getItem(position).getImageBitmap());
        day.setText(this.getItem(position).getDate());
        //TODO: make setting for different choices
        temp.setText(this.getItem(position).getTemp_C());
        cond.setText(this.getItem(position)
                .getWetherDesc());

        return rowView;


    }
}
