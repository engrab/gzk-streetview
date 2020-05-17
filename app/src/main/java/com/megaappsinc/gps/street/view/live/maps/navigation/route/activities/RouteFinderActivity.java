package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class RouteFinderActivity extends AppCompatActivity implements View.OnClickListener
{
    int PLACE_PICKER_SOURCE_REQUEST = 11;
    int PLACE_PICKER_DESTINATION_REQUEST = 12;
    DecimalFormat decimalFormat;
    private Activity context;
    private TextView tvSourceMessage;
    private TextView tvSourceLatLng;
    private ImageView ivSource;
    private TextView tvDestinationMessage;
    private TextView tvDesLatLng;
    private TextView tvDesAddress;
    private TextView tvDesName;
    private TextView tvSourceAddress;
    private TextView tvSourceName;
    private ImageView ivDes;
    private LatLng sLatlng, dLatLng;
    private InterstitialAd mInterstitialAd;
    private Resources resources;
    AdView mAdView;

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_finder);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }
        resources = getResources();
        AppPurchasePref appPurchasePref = new AppPurchasePref(this);
        if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            BannerAdmob();
        }
        SetupToolbar();
        context = RouteFinderActivity.this;
        findViewById(R.id.srl).setOnClickListener(this);
        findViewById(R.id.cv).setOnClickListener(this);
        findViewById(R.id.ll).setOnClickListener(this);
        findViewById(R.id.iv).setOnClickListener(this);
        findViewById(R.id.tvSource).setOnClickListener(this);
        findViewById(R.id.drl).setOnClickListener(this);
        findViewById(R.id.ll2).setOnClickListener(this);
        findViewById(R.id.cv2).setOnClickListener(this);
        findViewById(R.id.iv2).setOnClickListener(this);
        findViewById(R.id.tvDestination).setOnClickListener(this);
        findViewById(R.id.btnFindRoute).setOnClickListener(this);
        decimalFormat = new DecimalFormat(".###");

        tvSourceMessage = findViewById(R.id.tvSourceMessage);
        tvSourceLatLng = findViewById(R.id.tvSourceLatLng);
        ivSource = findViewById(R.id.ivSource);
        tvDestinationMessage = findViewById(R.id.tvDestinationMessage);
        tvDesLatLng = findViewById(R.id.tvDesLatLng);
        tvDesAddress = findViewById(R.id.tvDesAddress);
        tvDesName = findViewById(R.id.tvDesName);
        tvSourceAddress = findViewById(R.id.tvSourceAddress);
        tvSourceName = findViewById(R.id.tvSourceName);
        ivDes = findViewById(R.id.ivDes);
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
    public void onResume()
    {
        super.onResume();
        try
        {
            if (mAdView != null)
            {
                mAdView.resume();
            }
        }
        catch (Exception ignored)
        {
        }
    }

    @Override
    public void onPause()
    {
        if (mAdView != null)
        {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mAdView != null)
        {
            mAdView.destroy();
        }
    }

    @Override
    public void onBackPressed()
    {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.cv:
            case R.id.srl:
            case R.id.ll:
            case R.id.iv:
            case R.id.tvSource:
                try
                {
                    List<Place.Field> fields = Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME, com.google.android.libraries.places.api.model.Place.Field.LAT_LNG);
                    Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this);
                    startActivityForResult(intent, PLACE_PICKER_SOURCE_REQUEST);
                }
                catch (Exception e)
                {
                    // TODO: Handle the error.
                }
                break;
            case R.id.cv2:
            case R.id.drl:
            case R.id.ll2:
            case R.id.iv2:
            case R.id.tvDestination:
                try
                {
                    List<Place.Field> fields = Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME, com.google.android.libraries.places.api.model.Place.Field.LAT_LNG);
                    Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this);
                    startActivityForResult(intent, PLACE_PICKER_DESTINATION_REQUEST);
                }
                catch (Exception e)
                {
                    // TODO: Handle the error.
                }
                break;
            case R.id.btnFindRoute:
                if (sLatlng == null && dLatLng == null)
                {
                    Toast.makeText(context, resources.getString(R.string.txt_select_source_n_destination), Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (sLatlng == null)
                {
                    Toast.makeText(context, resources.getString(R.string.txt_select_source_first), Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (dLatLng == null)
                {
                    Toast.makeText(context, resources.getString(R.string.txt_select_destination_first), Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?saddr=" + sLatlng.latitude + "," + sLatlng.longitude + "&daddr="
                        + dLatLng.latitude + "," + dLatLng.longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(mapIntent);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == PLACE_PICKER_SOURCE_REQUEST && data != null)
            {
                try
                {
                    tvSourceMessage.setVisibility(View.GONE);
                    ivSource.setVisibility(View.VISIBLE);
                    tvSourceLatLng.setVisibility(View.VISIBLE);
                    tvSourceAddress.setVisibility(View.VISIBLE);
                    tvSourceName.setVisibility(View.VISIBLE);
                    Place place = Autocomplete.getPlaceFromIntent(data);
                    sLatlng = place.getLatLng();
                    LatLng latLng = place.getLatLng();
                    tvSourceAddress.setText(place.getAddress());
                    tvSourceName.setText(place.getName());
                    tvSourceLatLng.setText(String.valueOf("Lat:" + decimalFormat.format(latLng.latitude) + " Lng:" + decimalFormat.format(latLng
                            .longitude)));
                    Glide.with(context).load("http://maps.google.com/maps/api/staticmap?center=" + latLng.latitude + "," +
                            latLng.longitude + "&zoom=15&size=250x250&sensor=false&markers=" + latLng.latitude + "," +
                            latLng.longitude).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache
                            (true).centerCrop()).into(ivSource);
                }
                catch (Exception ignored)
                {
                }
            }
            else if (requestCode == PLACE_PICKER_DESTINATION_REQUEST && data != null)
            {
                try
                {
                    tvDestinationMessage.setVisibility(View.GONE);
                    ivDes.setVisibility(View.VISIBLE);
                    tvDesLatLng.setVisibility(View.VISIBLE);
                    tvDesAddress.setVisibility(View.VISIBLE);
                    tvDesName.setVisibility(View.VISIBLE);
                    Place place = Autocomplete.getPlaceFromIntent(data);
                    LatLng latLng = place.getLatLng();
                    dLatLng = place.getLatLng();
                    tvDesAddress.setText(place.getAddress());
                    tvDesName.setText(place.getName());
                    Toast.makeText(context, "" + place.getName(), Toast.LENGTH_SHORT).show();
                    tvDesLatLng.setText(String.valueOf("Lat:" + decimalFormat.format(latLng.latitude) + " Lng:" + decimalFormat.format(latLng
                            .longitude)));
                    Glide.with(context).load("http://maps.google.com/maps/api/staticmap?center=" + latLng.latitude + "," +
                            latLng.longitude + "&zoom=15&size=250x250&sensor=false&markers=" + latLng.latitude + "," +
                            latLng.longitude).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache
                            (true).centerCrop()).into(ivDes);
                }
                catch (Exception ignored)
                {
                }
            }
        }
    }

    private void SetupToolbar()
    {
        try
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded())
                    {
                        mInterstitialAd.show();
                    }
                    else
                    {
                        finish();
                    }
                }
            });
        }
        catch (Exception ignored)
        {
        }
    }
}
