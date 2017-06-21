package com.a1.larika.api_sample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1.larika.api_sample.Service.WeatherServiceCallback;
import com.a1.larika.api_sample.Service.YahooWeatherService;
import com.a1.larika.api_sample.data.channel;
import com.a1.larika.api_sample.data.item;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
ImageView icon,picture;
    TextView location,condition,temp;
    private YahooWeatherService yws;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture= (ImageView) findViewById(R.id.imageView2);
        icon= (ImageView) findViewById(R.id.imageView);
        location= (TextView) findViewById(R.id.textView3);
        condition= (TextView) findViewById(R.id.textView2);
        temp= (TextView) findViewById(R.id.textView);
        /*dialog=new ProgressDialog(getApplicationContext());
        dialog.setMessage("Loading...");
        dialog.show();
        */
        yws=new YahooWeatherService(this);
        yws.refreshWeather("Austin,TX");

    }


    @Override
    public void serviceSuccess(channel chann) {
//dialog.hide();
        item itm=chann.getItems();
        location.setText(yws.getLocation());
        temp.setText(chann.getItems().getCond().getTemp()+"\u00B0"+chann.getUnit().getTemp());
        condition.setText(chann.getItems().getCond().getDescription());

    }

    @Override
    public void serviceFailure(Exception exception) {
        //dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
