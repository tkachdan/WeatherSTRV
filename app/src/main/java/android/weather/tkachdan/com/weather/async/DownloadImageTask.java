package android.weather.tkachdan.com.weather.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.weather.tkachdan.com.weather.models.ForecastEntity;
import android.weather.tkachdan.com.weather.utils.BitmapUtil;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    ForecastEntity entity;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public DownloadImageTask(ForecastEntity entity) {
        this.entity = entity;
    }

    public DownloadImageTask() {

    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        BitmapUtil bitmapUtil = new BitmapUtil();
        result = bitmapUtil.getCroppedBitmap(result);
//        this.bitmap = result;
        if (bmImage != null)
            bmImage.setImageBitmap(result);

        if (entity != null) {
            entity.setImageBitmap(result);
        }
    }

}
