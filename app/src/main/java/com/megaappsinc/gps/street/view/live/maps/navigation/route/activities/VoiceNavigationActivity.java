package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.adapters.VoiceNavigationAdapter;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;

import java.util.List;

public class VoiceNavigationActivity extends AppCompatActivity implements View.OnClickListener {
    int PLACE_PICKER_REQUEST = 11;
    int VOICE_REQUEST = 12;
    AdView mAdView;
    Activity context;
    RecyclerView recyclerView;
    private InterstitialAd mInterstitialAd;

    private void SetupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        finish();

                }
            });
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_navigation);
        Init();
    }

    private void Init() {
        context = VoiceNavigationActivity.this;
        SetupToolbar();
        AppPurchasePref appPurchasePref = new AppPurchasePref(this);
        if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

            mAdView = findViewById(R.id.adView);
            mAdView.loadAd(new AdRequest.Builder().build());
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }
            });
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        speechRecognize();

                }
            });
        }
        recyclerView = findViewById(R.id.rvItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        findViewById(R.id.tvVoice).setOnClickListener(this);
        findViewById(R.id.iv).setOnClickListener(this);
        findViewById(R.id.cv).setOnClickListener(this);
        findViewById(R.id.ivRecorder).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvVoice:
            case R.id.iv:
            case R.id.cv:
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;
            case R.id.ivRecorder:
                try {

                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        speechRecognize();
                    }
                } catch (Exception ignored) {
                }
                break;
        }
    }

    private void speechRecognize() {
        try {
            if (context.getPackageManager().queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0).size() != 0) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.txt_where_you_want_go));
                startActivityForResult(intent, VOICE_REQUEST);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(getString(R.string.txt_warning));
                alertDialog.setMessage(getString(R.string.txt_voice_recognition_not_active));
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (mAdView != null) {
                mAdView.resume();
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }

//    @Override
//    protected void attachBaseContext(Context base)
//    {
//        super.attachBaseContext(LocaleHelper.onAttach(base));
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                try {
                    Place place = PlacePicker.getPlace(context, data);
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + place.getName());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                } catch (Exception ignored) {
                }
            }
        } else if (requestCode == VOICE_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                try {
                    List<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (list != null && list.size() > 0) {
                        recyclerView.setAdapter(new VoiceNavigationAdapter(context, list));
                    } else {
                        recyclerView.setAdapter(null);
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
