package android.weather.tkachdan.com.weather.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weather.tkachdan.com.weather.R;
import android.weather.tkachdan.com.weather.models.NavDrawerItem;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_nav_item, null);
        }

        TextView txtTab = (TextView) convertView.findViewById(R.id.tvTitle);

        XmlResourceParser xrp = convertView.getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(convertView.getResources(), xrp);
            txtTab.setTextColor(csl);
        } catch (Exception e) {
        }


        txtTab.setText("Agregar Idea");

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.tvTitle);


        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        return convertView;
    }

}