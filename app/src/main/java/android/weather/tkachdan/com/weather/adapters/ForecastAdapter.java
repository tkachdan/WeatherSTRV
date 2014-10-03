package android.weather.tkachdan.com.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.async.DownloadImageTask;
import android.weather.tkachdan.com.weather.models.ForecastEntity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class ForecastAdapter extends ArrayAdapter<ForecastEntity> {

    Context context;
    boolean isCelsius;

    public ForecastAdapter(Context context, List<ForecastEntity> objects) {
        super(context, R.layout.fragment_forecast, objects);
        this.context = context;
    }

    public ForecastAdapter(Context context, List<ForecastEntity> objects, boolean isCelsius) {
        super(context, R.layout.fragment_forecast, objects);
        this.context = context;
        this.isCelsius = isCelsius;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.forecast_list_view, parent, false);
        ImageView imageView = (ImageView) rowView
                .findViewById(R.id.forecastImage_imageView);
        TextView day = (TextView) rowView
                .findViewById(R.id.day_textView);


        TextView temp = (TextView) rowView
                .findViewById(R.id.temp_forecast_textView);
        TextView cond = (TextView) rowView
                .findViewById(R.id.cond_forecast_textView);
        ForecastEntity en = this.getItem(position);
        String url = en.getImage();
        new DownloadImageTask(imageView).execute(url);
        day.setText(this.getItem(position).getDate());
        if (isCelsius) {
            temp.setText(this.getItem(position).getTemp_C());
        } else {
            temp.setText(this.getItem(position).getTemp_F());
        }
        cond.setText(this.getItem(position)
                .getWetherDesc());

        return rowView;


    }
}
