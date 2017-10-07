package com.example.android.quakereport;

/**
 * Created by HP on 7/21/2017.
 */

public class Earthquake {

    private Double magnitude;
    private String area;
    private Long date;
    private String url;

    Earthquake(Double magnitudeCall, String areaCall, Long dateCall, String urlCall)
    {
        magnitude=magnitudeCall;
        area=areaCall;
        date=dateCall;
        url = urlCall;
    }

    public Double  getMagnitude()
    {
        return magnitude;
    }
    public String getArea()
    {
        return area;
    }
    public Long getDate()
    {
        return date;
    }
    public  String getUrl()
    {
        return url;
    }
}
