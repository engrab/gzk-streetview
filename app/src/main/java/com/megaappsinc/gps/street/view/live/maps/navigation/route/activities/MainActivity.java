package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TYPE_KEY = "type";
    public static double latitude = 0;
    public static double longitude = 0;
    LinearLayout  Streetview, University, Australia, World, Africa, Map, Worldwonder, Famouseplace, RouterFinder, Voice_Navigation, Nearby_Place, Current_Locaton;
    InterstitialAd mInterstitialAd;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};
    int viewId = 0;
    AdView adView;
    private LocationManager locationManager;
    private Listener listener;
    private Dialog mDialog;
    AdView mAdView;

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void BannerAdmob() {

        mAdView = this.findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        AppPurchasePref appPurchasePref = new AppPurchasePref(MainActivity.this);
        if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

            BannerAdmob();
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
            requestNewInterstitial();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    clickListeners();

                    requestNewInterstitial();
                }
            });
        }
        setUpLoation();
        Streetview = findViewById(R.id.ic_street);
        Africa = findViewById(R.id.ic_africa);
        World = findViewById(R.id.ic_world);
        Worldwonder = findViewById(R.id.ic_wonder);
        Famouseplace = findViewById(R.id.ic_famouse);
        University = findViewById(R.id.ic_university);
        Australia = findViewById(R.id.ic_australia);
        RouterFinder = findViewById(R.id.ic_route);
        Voice_Navigation = findViewById(R.id.ic_voice);
        Nearby_Place = findViewById(R.id.ic_nearby);
        Map = findViewById(R.id.ic_map);
        Current_Locaton = findViewById(R.id.ic_current);
        findViewById(R.id.ic_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                    if (sharingIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.txt_share_via)));
                    }
                } catch (Exception ignored) {
                }
            }
        });
        findViewById(R.id.ic_moreapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Mega+Apps+Inc&hl=en"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } catch (Exception ignored) {
                }
            }
        });
       findViewById(R.id.ic_rateus).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   Intent intent = new Intent(Intent.ACTION_VIEW);
                   intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                   if (intent.resolveActivity(getPackageManager()) != null) {
                       startActivity(intent);
                   }
               } catch (Exception ignored) {
               }
           }
       });

        Streetview.setOnClickListener(this);
        Africa.setOnClickListener(this);
        World.setOnClickListener(this);
        Worldwonder.setOnClickListener(this);
        RouterFinder.setOnClickListener(this);
        Famouseplace.setOnClickListener(this);
        University.setOnClickListener(this);
        Australia.setOnClickListener(this);
        RouterFinder.setOnClickListener(this);
        Voice_Navigation.setOnClickListener(this);
        Nearby_Place.setOnClickListener(this);
        Current_Locaton.setOnClickListener(this);
        Map.setOnClickListener(this);
        findViewById(R.id.ic_current).setOnClickListener(this);
    }

    private void requestNewInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    @Override
    public void onClick(View v) {
        viewId = v.getId();

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            clickListeners();
        }

    }


    private void clickListeners() {
        switch (viewId) {

            case R.id.ic_street:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "All"));
                break;
            case R.id.ic_famouse:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "Favourits Places"));
                break;
            case R.id.ic_africa:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "Exploring Africa"));
                break;
            case R.id.ic_map:
                startActivity(new Intent(MainActivity.this, RoutActivity.class));
                break;
            case R.id.ic_wonder:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "World Wonders"));
                break;
            case R.id.ic_university:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "World Class Universities"));
                break;
            case R.id.ic_route:
                startActivity(new Intent(MainActivity.this, RouteFinderActivity.class));
                break;
            case R.id.ic_voice:
                startActivity(new Intent(MainActivity.this, VoiceNavigationActivity.class));
                break;
            case R.id.ic_nearby:
                startActivity(new Intent(MainActivity.this, NearestPlacesActivity.class));
                break;
            case R.id.ic_world:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "Exploring World"));
                break;
            case R.id.ic_current:
                startActivity(new Intent(MainActivity.this, LiveAddressActivity.class));
                break;
            case R.id.ic_australia:
                startActivity(new Intent(MainActivity.this, AllStreetViewActivity.class).putExtra(TYPE_KEY, "Australia Highlights"));
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }

    }

    @Override
    protected void onPause() {

        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private void setUpLoation() {
        listener = new Listener();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
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
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, listener);
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, listener);
            } else {
                locationManager.removeUpdates(listener);
                Toast.makeText(this, getString(R.string.txt_no_internet), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Listener implements LocationListener {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

}
