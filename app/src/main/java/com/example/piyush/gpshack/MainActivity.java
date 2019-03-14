package com.example.piyush.gpshack;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager lm ;
    LocationListener locationListener;
    Location test ;
    public void mockLocation1(){
         lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy( Criteria.ACCURACY_FINE );
        String mocLocationProvider = LocationManager.GPS_PROVIDER;
        if ( mocLocationProvider == null ) {
            Toast.makeText(getApplicationContext(), "No location provider found!", Toast.LENGTH_SHORT).show();
            return;
        }

        lm.addTestProvider(mocLocationProvider, false, false,
                false, false, true, true, true, 0, 5);
        lm.setTestProviderEnabled(mocLocationProvider, true);

        Location loc = new Location(mocLocationProvider);
        Location mockLocation = new Location(mocLocationProvider); // a string
      //  mockLocation.reset();
        mockLocation.setLatitude(-26.902038);  // double
        mockLocation.setLongitude(-48.671337);
        mockLocation.setAltitude(loc.getAltitude());
        mockLocation.setTime(System.currentTimeMillis());
        mockLocation.setAccuracy(1.0f);
        mockLocation.setVerticalAccuracyMeters(1.0f);
        mockLocation.setBearing(1.0f);
        mockLocation.setBearingAccuracyDegrees(1.0f);
        mockLocation.setElapsedRealtimeNanos(1);
        mockLocation.setSpeedAccuracyMetersPerSecond(1.0f);
       // mockLocation.setProvider(mockLocationProvider);
        mockLocation.setSpeed(1.0f);
        mockLocation.setVerticalAccuracyMeters(1.0f);
        lm.setTestProviderLocation( mocLocationProvider, mockLocation);
        Toast.makeText(getApplicationContext(), "Working ::  "+ mockLocation.getLatitude() + " Long = " + mockLocation.getLongitude() , Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("Location", location.toString());
                //TextView tv1 = (TextView) findViewById(R.id.text);
               // tv1.setTextSize(25);

               // tv1.setText(location.toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Location" +location.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {


            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            ;
           // lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }



        mockLocation1();



        Location newLocation = new Location(LocationManager.GPS_PROVIDER);
        double lat = newLocation.getLatitude();
        double lon = newLocation.getLongitude();

        Toast.makeText(getApplicationContext(), "Latititude = " +lat + " Longitude = "+lon, Toast.LENGTH_SHORT).show();
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //setMock(0.23,3.123,1);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





