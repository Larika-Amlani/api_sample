package com.a1.larika.api_sample.Service;

import android.os.AsyncTask;

import com.a1.larika.api_sample.data.channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Larika on 21-06-2017.
 */
public class YahooWeatherService {
    private WeatherServiceCallback wsc;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback wsc) {
        this.wsc = wsc;
    }

    public String getLocation() {
        return location;
    }
public void refreshWeather(String l){
 this.location=l;
    new AsyncTask<String, Void, String>() {
        @Override
        protected String doInBackground(String... strings) {
            String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",strings[0]);

            String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", URLEncoder.encode(YQL));
            try {
                URL url = new URL(endpoint);
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                StringBuilder res = new StringBuilder();
                String line;
                while ((line = buf.readLine()) != null)
                    res.append(line);

                return res.toString();
            }catch (Exception e1) {
                error=e1;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s==null && error!=null)
            {
                wsc.serviceFailure(error);
                return;
            }
            try {
                JSONObject data=new JSONObject(s);
                JSONObject query=data.optJSONObject("query");
                int count=query.optInt("count");
                if(count==0)
                    wsc.serviceFailure(new LocationUnfound("no weather data found for"+location));
                channel chann=new channel();
                chann.populate(query.optJSONObject("results").optJSONObject("channel"));
                wsc.serviceSuccess(chann);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }.execute(location);
}
    public class LocationUnfound extends Exception{
        public LocationUnfound(String message) {

        }
    }
}