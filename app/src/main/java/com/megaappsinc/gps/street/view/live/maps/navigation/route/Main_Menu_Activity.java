package com.megaappsinc.gps.street.view.live.maps.navigation.route;

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
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.Famous_Places;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LiveAddress;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.Search_StreetView;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.Top_StreetView;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.Top_StreetView_Main;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.Universties_StreetView;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

public class Main_Menu_Activity extends AppCompatActivity implements View.OnClickListener {
    public static double latitude = 0;
    public static double longitude = 0;
    LinearLayout Live_streetview, Streetview, University, Australia, World, Africa, Map, Worldwonder, Famouseplace, RouterFinder, Voice_Navigation, Nearby_Place, Current_Locaton, Rate_us, More_App, Share;
    InterstitialAd mInterstitialAd;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};
    int viewId = 0;
    boolean isFromBackPress = false;
    StartAppAd startAppAd;
    AdView adView;
    private LocationManager locationManager;
    private Listener listener;
    private Dialog mDialog;

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

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        adView = findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        StartAppSDK.init(this, "200779191", false);
        StartAppAd.disableSplash();
        startAppAd = new StartAppAd(this);
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                if (startAppAd != null && startAppAd.isReady()) {
                    startAppAd.showAd();
                }
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {

            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (!isFromBackPress) {
                    clickListeners();
                }
                requestNewInterstitial();
            }
        });
        setUpLoation();
        Live_streetview = findViewById(R.id.ic_live);
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
        Share = findViewById(R.id.ic_share);
        More_App = findViewById(R.id.ic_moreapp);
        Rate_us = findViewById(R.id.ic_rateus);

        Live_streetview.setOnClickListener(this);
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
        Share.setOnClickListener(this);
        More_App.setOnClickListener(this);
        Rate_us.setOnClickListener(this);
        findViewById(R.id.ic_current).setOnClickListener(this);
    }

    private void requestNewInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    @Override
    public void onClick(View v) {
        viewId = v.getId();
        isFromBackPress = false;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            clickListeners();
        }

    }



    private void clickListeners() {
        switch (viewId) {
            case R.id.ic_live:
                startActivity(new Intent(Main_Menu_Activity.this, Search_StreetView.class));
                break;
            case R.id.ic_street:
                startActivity(new Intent(Main_Menu_Activity.this, MainActivity.class));
                break;
            case R.id.ic_famouse:
                startActivity(new Intent(Main_Menu_Activity.this,Famous_Places.class));
                break;
            case R.id.ic_africa:
                startActivity(new Intent(Main_Menu_Activity.this,Top_StreetView.class).putExtra("Type","Exploring Africa"));
            break;
            case R.id.ic_map:
                startActivity(new Intent(Main_Menu_Activity.this,Rout.class));
                break;
            case R.id.ic_wonder:
                startActivity(new Intent(Main_Menu_Activity.this,Top_StreetView.class).putExtra("Type","WorldWonders"));
            break;
            case R.id.ic_university:
                startActivity(new Intent(Main_Menu_Activity.this, Universties_StreetView.class));
                break;
                case R.id.ic_route:
                startActivity(new Intent(Main_Menu_Activity.this, Route_Finder_Activity.class));
                break;
            case R.id.ic_voice:
                startActivity(new Intent(Main_Menu_Activity.this, Voice_Navigation_Activity.class));
                break;
            case R.id.ic_nearby:
                startActivity(new Intent(Main_Menu_Activity.this, Nearest_Places_Activity.class));
                break;
            case R.id.ic_world:
                startActivity(new Intent(Main_Menu_Activity.this, Top_StreetView_Main.class));
                break;
            case R.id.ic_current:
                startActivity(new Intent(Main_Menu_Activity.this, LiveAddress.class));
                break;
            case R.id.ic_australia:
startActivity(new Intent(Main_Menu_Activity.this,Top_StreetView.class).putExtra("Type","Australia Highlights"));
break;
            case R.id.ic_share:
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
                break;
            case R.id.ic_moreapp:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Prime%20Free%20Apps%20Wallet&hl=en"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } catch (Exception ignored) {
                }
                break;
            case R.id.ic_rateus:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } catch (Exception ignored) {
                }
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (!isFromBackPress && mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            isFromBackPress = true;
            mInterstitialAd.show();
        } else {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            } else {
                finish();
            }
        }

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
        if (startAppAd != null) {
            startAppAd.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (startAppAd != null) {
            startAppAd.onPause();
        }
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
