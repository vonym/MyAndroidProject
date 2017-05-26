package com.example.lbsapp;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private LocationManager manager;
    private Location location;
    private String latLongInfo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        getLocationInfo(location);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                getLocationInfo(null);
            }

            @Override
            public void onProviderDisabled(String provider) {
                getLocationInfo(null);
            }
        });

    }

    public void getLocationInfo(Location location) {
        textView = (TextView) findViewById(R.id.tv);
        if (location != null) {
            double lat = location.getLatitude();//获取纬度
            double lng = location.getLongitude();//获取经度
            latLongInfo = "纬度为: " + lat + "\n经度为: " + lng;
        } else {
            latLongInfo = "没有发现到当前位置";
        }
        textView.setText(latLongInfo);
    }
}
