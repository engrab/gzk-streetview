package com.mobileappsarcade.gps.street.view.live.maps.navigation.route.classes;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileappsarcade.gps.street.view.live.maps.navigation.route.Main_Menu_Activity;
import com.mobileappsarcade.gps.street.view.live.maps.navigation.route.R;

import java.util.List;
import java.util.Locale;


public class LiveAddress extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    InterstitialAd mInterstitialAd;
    TextView knowAddressText, cityText, addressText, longitudeText, latitudeText, postalCodeText, stateText, countyText;
    Button Normal, Satellite, Hybrid;
   ImageView Zoom_in,Zoom_out;

    @Override
    protected void onPause() {
        super.onPause();
        if (mInterstitialAd != null) {
            mInterstitialAd.setAdListener(null);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_address);
        knowAddressText = findViewById(R.id.knowAddressText);
        knowAddressText.setTypeface(null, Typeface.ITALIC);
        cityText = findViewById(R.id.cityText);
        cityText.setTypeface(null,Typeface.ITALIC);
        addressText = findViewById(R.id.addressText);
        addressText.setTypeface(null,Typeface.ITALIC);
        longitudeText = findViewById(R.id.longitudeText);
        longitudeText.setTypeface(null,Typeface.ITALIC);
        latitudeText = findViewById(R.id.latitudeText);
        latitudeText.setTypeface(null,Typeface.ITALIC);
        postalCodeText = findViewById(R.id.postalCodeText);
        postalCodeText.setTypeface(null,Typeface.ITALIC);
        stateText = findViewById(R.id.stateText);
        stateText.setTypeface(null,Typeface.ITALIC);
        countyText = findViewById(R.id.countyText);
        countyText.setTypeface(null,Typeface.ITALIC);
        Normal = findViewById(R.id.btn_normal);
        Satellite = findViewById(R.id.btn_satellite);
        Hybrid = findViewById(R.id.hybrid);
       Zoom_in= findViewById(R.id.img_zoom_in);
        Zoom_out = findViewById(R.id.img_zoom_out);
        Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoogleMap != null) {
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        Satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoogleMap != null) {
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        Hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoogleMap != null) {
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

Zoom_in.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
    }

});
Zoom_out.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomOut());
    }
});
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fm.getMapAsync(this);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (Main_Menu_Activity.longitude != 0 && Main_Menu_Activity.latitude != 0) {

            LatLng latLng = new LatLng(Main_Menu_Activity.latitude, Main_Menu_Activity.longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mGoogleMap.animateCamera(cameraUpdate);
            setAddress(Main_Menu_Activity.longitude, Main_Menu_Activity.latitude);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        mGoogleMap.addMarker(new MarkerOptions().position(
                new LatLng(Main_Menu_Activity.latitude,Main_Menu_Activity.longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.current_pin)));
    }

    private void setAddress(double longitude, double latitude) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null) {

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    addressText.setText(" " + address);
                    cityText.setText(" " + city);
                    stateText.setText(" " + state);
                    knowAddressText.setText(" " + knownName);
                    postalCodeText.setText(" " + postalCode);
                    countyText.setText(" " + country);
                    latitudeText.setText(String.valueOf(" " + latitude));
                    longitudeText.setText(String.valueOf(" " + longitude));


                } else {
                    Log.w("", "My Current loction address,No Address returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("", "My Current loction address,Canont get Address!");
            }

        } catch (Exception ignored) {

        }


    }
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
