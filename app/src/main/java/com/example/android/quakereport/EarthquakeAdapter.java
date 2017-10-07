package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 7/21/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String LOCATION_SEPERATOR="of";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View listView = convertView;

        if(listView==null)
        {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquaque_custom_list,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView)listView.findViewById(R.id.levelTV);
        String formattedMag = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMag);
        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String primary;
        String offset;

        String currentLoc = currentEarthquake.getArea();
        if(currentLoc.contains(LOCATION_SEPERATOR))
        {

            String[] parts = currentLoc.split(LOCATION_SEPERATOR);
            offset = parts[0]+LOCATION_SEPERATOR;
            primary = parts[1];
        }
        else
        {
            offset = getContext().getString(R.string.near_the);
            primary = currentLoc;
        }

        TextView areaView = (TextView)listView.findViewById(R.id.areaTV);
        areaView.setText(offset);

        TextView areaViewOne = (TextView)listView.findViewById(R.id.areaOneTV);
        areaViewOne.setText(primary);


        Date dateObject = new Date(currentEarthquake.getDate());
        TextView dateTV=(TextView)listView.findViewById(R.id.dateTV);
        String formatedDate = formatDate(dateObject);
        dateTV.setText(formatedDate);

        TextView timeTV =(TextView)listView.findViewById(R.id.timeTV);
        String formattedTime = formatTime(dateObject);
        timeTV.setText(formattedTime);

        return listView;
    }


    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor)
        {
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
