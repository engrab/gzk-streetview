package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.DirectionsJSONParser;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.utiles.Utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class RoutActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap
        .OnMyLocationButtonClickListener
{
    boolean refresh = false;
    Handler handler = new Handler();
    int repeatTime = 3000;
    LinearLayout topline;
    Geocoder geocoder;
    List<Address> addresses;
    GoogleMap map;
    ArrayList<LatLng> mMarkerPoints;
    TextView distance;
     AdView mAdView;
    Runnable r = new Runnable()
    {
        @Override
        public void run()
        {

            if (Utils.isNetworkAvailable(getApplicationContext()))
            {
                repeatTime = 1000;
                distance.setVisibility(View.VISIBLE);

                topline.setVisibility(View.GONE);
                if (refresh)
                {
                    refresh = false;
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_internet_access), Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                refresh = true;
                topline.setVisibility(View.VISIBLE);
                distance.setVisibility(View.GONE);
                repeatTime = 3000;
            }
            handler.postDelayed(r, repeatTime);
        }
    };
    String TAG = "mapactivity";
    boolean showDialog = false;
    String addressFrom, addressTo, distanceInfo;
    boolean mapBusy = false;
    UiSettings mUiSettings;
    private String style = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            style = bundle.getString("style");
        }
        AppPurchasePref appPurchasePref = new AppPurchasePref(this);
        if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

            if (Utils.isNetworkAvailable(getApplicationContext())) {
                BannerAdmob();


            }

        }
        geocoder = new Geocoder(this, Locale.getDefault());
        findViewById(R.id.latlong).setVisibility(View.GONE);
        topline = findViewById(R.id.topline);
        distance = findViewById(R.id.latlong);
        distance.setText(getString(R.string.txt_tap_on_map));

        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getBaseContext());

        if (status != ConnectionResult.SUCCESS)
        {
            int requestCode = 10;
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, status, requestCode);
            dialog.show();
        }
        else
        {
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            fm.getMapAsync(this);
            distance.setOnClickListener(this);
            handler.postDelayed(r, repeatTime);
            topline.setOnClickListener(this);
        }
    }
    private void BannerAdmob()
    {

        mAdView = this.findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed()
    {

            super.onBackPressed();

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest)
    {

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }
        catch (Exception e)
        {
            Log.d(TAG, e.toString());
        }
        finally
        {
            if (iStream != null)
            {
                iStream.close();
            }
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
        return data;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        try
        {
            // Initializing
            mMarkerPoints = new ArrayList<>();
            map = googleMap;
            if (map != null)
            {
                if (style.equals("3d"))
                {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
                else if (style.equals("satellite"))
                {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                mUiSettings = map.getUiSettings();

                // Keep the UI Settings state in sync with the checkboxes.
                mUiSettings.setZoomControlsEnabled(true);
                mUiSettings.setCompassEnabled(true);
                mUiSettings.setMyLocationButtonEnabled(true);
                mUiSettings.setScrollGesturesEnabled(true);
                mUiSettings.setZoomGesturesEnabled(true);
                mUiSettings.setTiltGesturesEnabled(true);
                mUiSettings.setRotateGesturesEnabled(true);
                mUiSettings.setMapToolbarEnabled(true);

                map.setOnMapClickListener(new OnMapClickListener()
                {

                    @Override
                    public void onMapClick(LatLng point)
                    {
                        try
                        {
                            if (map == null)
                            {
                                return;
                            }

                            if (map == null)
                            {
                                return;
                            }
                            if (mapBusy)
                            {
                                Utils.mapeBusy(getApplicationContext());
                                return;
                            }
                            if (!Utils.isNetworkAvailable(getApplicationContext()))
                            {
                                Utils.wifiNotFound(getApplicationContext());
                                return;
                            }
                            if (!Utils.isLocationEnabled(getApplicationContext()))
                            {
                                Utils.locationNotFound(getApplicationContext());
                                return;
                            }
                            // Already map contain destination location
                            if (mMarkerPoints.size() == 0)
                            {
                                map.clear();
                                mMarkerPoints = new ArrayList<>();
                                drawMarker(point);
                            }
                            else if (mMarkerPoints.size() == 1)
                            {
                                drawMarker(point);
                            }
                            else if (mMarkerPoints.size() == 2)
                            {
                                map.clear();
                                mMarkerPoints = new ArrayList<>();
                                drawMarker(point);
                            }


                            // Checks, whether start and end locations are captured
                            if (mMarkerPoints.size() == 2)
                            {
                                mapBusy = true;
                                LatLng origin = mMarkerPoints.get(0);
                                LatLng dest = mMarkerPoints.get(1);

                                // Getting URL to the Google Directions API
                                String url = getDirectionsUrl(origin, dest);
                                CalculationByDistance(origin, dest);

                                // Start downloading json data from Google Directions API
                                new DownloadTask().execute(url);
                            }

                        }
                        catch (Exception ignored)
                        {
                        }
                    }
                });

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    finish();
                    return;
                }

                map.setMyLocationEnabled(true);
                map.setOnMyLocationButtonClickListener(this);
            }
        }
        catch (Exception ignored)
        {
        }
    }

    public void CalculationByDistance(LatLng StartP, LatLng EndP)
    {
        int Radius = 6371;
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i(TAG, "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

        if (valueResult > 1)
        {
            distanceInfo = getString(R.string.txt_you_are) + " " + kmInDec + " KM " + meterInDec + " M " + getString(R.string
                    .txt_away_from);
            distance.setText(String.valueOf(getString(R.string.txt_distance) + "\n" + distanceInfo));
        }
        else
        {
            distanceInfo = getString(R.string.txt_you_are) + " " + valueResult + " KM " + getString(R.string
                    .txt_away_from);
            distance.setText(String.valueOf(getString(R.string.txt_distance) + "\n" + distanceInfo));
        }

        addressFrom = Utils.getCompleteAddressString(getApplicationContext(), StartP.latitude, StartP.longitude);
        addressTo = Utils.getCompleteAddressString(getApplicationContext(), EndP.latitude, EndP.longitude);

        showDialog = true;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.latlong:
                openDialog();
                break;
            case R.id.topline:
                Utils.openWifi(getApplicationContext());
                break;
        }
    }

    private void openDialog()
    {
        if (!showDialog)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.txt_select_destination), Toast.LENGTH_SHORT).show();
            return;
        }
        final Dialog dialog = new Dialog(RoutActivity.this, R.style.MaterialDialogSheet);
        dialog.setContentView(R.layout.rout_info_dialog);
        dialog.setTitle(getString(R.string.txt_route_information));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView from = dialog.findViewById(R.id.from);
        TextView to = dialog.findViewById(R.id.to);
        TextView innfoDistance = dialog.findViewById(R.id.distanceInfo);
        from.setText(addressFrom);
        to.setText(addressTo);
        innfoDistance.setText(distanceInfo);

        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
        dialog.show();

    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    private void drawMarker(LatLng point)
    {
        if (map == null)
        {
            return;
        }

        mMarkerPoints.add(point);
        MarkerOptions options = new MarkerOptions();
        options.position(point);
        options.title(getAddress(point));
        if (mMarkerPoints.size() == 1)
        {
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location_marker));
        }
        else if (mMarkerPoints.size() == 2)
        {
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.destination));
        }
        map.addMarker(options);
    }

    @Override
    protected void onPause()
    {
        if (mAdView != null)
        {
            mAdView.pause();
        }

        super.onPause();
    }

    @Override
    protected void onResume()
    {


        super.onResume();
        if (mAdView != null)
        {
            mAdView.resume();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        handler.removeCallbacks(r);
    }

    @Override
    protected void onDestroy()
    {
        if (mAdView != null)
        {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onMyLocationButtonClick()
    {
        displayLocationSettingsRequest(this);
        return false;
    }

    private void displayLocationSettingsRequest(Context context)
    {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>()
        {
            @Override
            public void onResult(@NonNull LocationSettingsResult result)
            {
                final Status status = result.getStatus();
                switch (status.getStatusCode())
                {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try
                        {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(RoutActivity.this, 12);
                        }
                        catch (IntentSender.SendIntentException e)
                        {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    private String getAddress(LatLng location)
    {
        String address = "";
        try
        {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1); // Here 1 represent max location
            if (addresses != null)
            {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                if (returnedAddress.getMaxAddressLineIndex() == 0)
                {
                    if (returnedAddress.getAddressLine(0) != null && !returnedAddress.getAddressLine(0).equals(""))
                    {
                        strReturnedAddress.append(returnedAddress.getAddressLine(0)).append(", ");
                    }
                }
                else
                {
                    for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++)
                    {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                    }
                }
                address = strReturnedAddress.toString();
                if (!address.equals(""))
                {
                    address = address.substring(0, address.length() - 2);
                }
//                Log.w(TAG, "My Current loction address" + strReturnedAddress.toString());
            }
        }
        catch (Exception e)
        {
            address = "";
            e.printStackTrace();
        }
        return address;
    }

    /**
     * A class to download data from Google Directions URL
     */
    private class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... url)
        {

            Log.d(TAG, "DownloadTask doInBackground: ");
            String data = "";

            try
            {
                data = downloadUrl(url[0]);
            }
            catch (Exception e)
            {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.d(TAG, "DownloadTask onPostExecute: ");
            new ParserTask().execute(result);
        }
    }

    /**
     * A class to parse the Google Directions in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>>
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData)
        {

            Log.d(TAG, "ParserTask doInBackground: ");
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try
            {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result)
        {

            if (map == null)
            {
                return;
            }
            Log.d(TAG, "ParserTask onPostExecute: ");
            System.gc();
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            if (result != null && result.size() > 0)
            {
                for (int i = 0; i < result.size(); i++)
                {
                    points = new ArrayList<>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);
                    if (path != null && path.size() > 0)
                    {
                        // Fetching all the points in i-th route
                        for (int j = 0; j < path.size(); j++)
                        {
                            HashMap<String, String> point = path.get(j);
                            if (point != null)
                            {
                                double lat = Double.parseDouble(point.get("lat"));
                                double lng = Double.parseDouble(point.get("lng"));
                                LatLng position = new LatLng(lat, lng);

                                points.add(position);
                            }
                        }
                    }
                    if (points.size() > 0)
                    {
                        // Adding all the points in the route to LineOptions
                        lineOptions.addAll(points);
                        lineOptions.width(5);
                        lineOptions.color(Color.RED);
                    }

                }
            }

            if (lineOptions != null)
            {
                // Drawing polyline in the Google Map for the i-th route
                map.addPolyline(lineOptions);
            }
            mapBusy = false;
        }
    }
}
