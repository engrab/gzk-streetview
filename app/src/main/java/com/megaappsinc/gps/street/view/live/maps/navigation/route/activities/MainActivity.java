package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback
{
    Place place;
    InterstitialAd mInterstitialAd;
    Activity context;
    List<LatLng> streetViewList;
    StreetViewPanorama mStreetViewPanorama;
    private String TAG = MainActivity.class.getSimpleName();
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            context = MainActivity.this;
            SetupToolbar();
            AppPurchasePref appPurchasePref = new AppPurchasePref(MainActivity.this);
            if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

                BannerAdmob();
                mInterstitialAd = new InterstitialAd(this);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());

                            if (mStreetViewPanorama != null) {
                                mStreetViewPanorama.setPosition(streetViewList.get(randInt()));
                            }

                    }
                });
            }
            streetViewPlaces();
            findViewById(R.id.ivShuffle).setOnClickListener(new View.OnClickListener()
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
                        if (mStreetViewPanorama != null)
                        {
                            mStreetViewPanorama.setPosition(streetViewList.get(randInt()));
                        }
                    }
                }
            });
            findViewById(R.id.ivSearch).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    callPlaceSearchIntent();
                }
            });
            StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetviewpanorama);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        }
        catch (Exception ignored)
        {
        }
    }

    private int randInt()
    {
        return new Random().nextInt(streetViewList.size() - 1);
    }

    private void streetViewPlaces()
    {
        streetViewList = new ArrayList<>();
        streetViewList.add(new LatLng(31.567507, 74.307659));
        streetViewList.add(new LatLng(30.633488, 72.861289));
        streetViewList.add(new LatLng(31.622642, 74.302576));
        streetViewList.add(new LatLng(31.588197, 74.310603));
        streetViewList.add(new LatLng(31.606444, 74.293234));
        streetViewList.add(new LatLng(31.5881971, 74.3106033));
        streetViewList.add(new LatLng(25.1972652, 55.2744235));
        streetViewList.add(new LatLng(31.583173, 74.323248));
        streetViewList.add(new LatLng(62.093947, -7.413442));
        streetViewList.add(new LatLng(-23.8935, 31.546686));
        streetViewList.add(new LatLng(19.3617076, -99.2759285));
        streetViewList.add(new LatLng(31.588574, 74.314857));
        streetViewList.add(new LatLng(27.174628, 78.041666));
        streetViewList.add(new LatLng(28.5674181, 77.2086014));
        streetViewList.add(new LatLng(19.432567, -99.133045));
        streetViewList.add(new LatLng(47.527282, 108.551732));
        streetViewList.add(new LatLng(30.749205, 71.815355));
        streetViewList.add(new LatLng(29.560285, -95.085391));
        streetViewList.add(new LatLng(76.420794, -82.894116));
        streetViewList.add(new LatLng(48.197792, 16.353052));
        streetViewList.add(new LatLng(31.120574, 74.447965));
        streetViewList.add(new LatLng(22.448303, 120.712419));
        streetViewList.add(new LatLng(55.751828, 37.613355));
        streetViewList.add(new LatLng(-2.66387, -42.858229));
        streetViewList.add(new LatLng(30.666348, 73.654009));
        streetViewList.add(new LatLng(-34.605717, -58.435908));
        streetViewList.add(new LatLng(20.593144, -100.389723));
        streetViewList.add(new LatLng(-31.534914, -68.529155));
        streetViewList.add(new LatLng(2.897892, 104.168805));
        streetViewList.add(new LatLng(31.58851, 74.312295));
        streetViewList.add(new LatLng(-8.680658, 119.556821));
        streetViewList.add(new LatLng(37.828361, -75.994872));
        streetViewList.add(new LatLng(6.450035, 3.397014));
        streetViewList.add(new LatLng(42.64028, 18.112402));
        streetViewList.add(new LatLng(31.708592, 73.990438));
        streetViewList.add(new LatLng(35.781168, 129.222871));
        streetViewList.add(new LatLng(-22.903306, -43.191453));
        streetViewList.add(new LatLng(-36.455828, 148.263492));
        streetViewList.add(new LatLng(21.083667, -99.658596));
        streetViewList.add(new LatLng(-27.26913, -58.243943));
        streetViewList.add(new LatLng(31.70909, 73.990061));
        streetViewList.add(new LatLng(-25.365823, 131.064245));
        streetViewList.add(new LatLng(26.202737, 127.289096));
        streetViewList.add(new LatLng(36.42261, 9.218203));
        streetViewList.add(new LatLng(35.901236, 14.512841));
        streetViewList.add(new LatLng(31.587333, 74.382072));
        streetViewList.add(new LatLng(29.608163, 52.55278));
        streetViewList.add(new LatLng(43.837963, 7.829236));
        streetViewList.add(new LatLng(14.695105, -91.274181));
        streetViewList.add(new LatLng(-44.685526, 167.898206));
        streetViewList.add(new LatLng(31.742892, 73.95642));
        streetViewList.add(new LatLng(-16.250015, 168.135919));
        streetViewList.add(new LatLng(69.207943, -51.163007));
        streetViewList.add(new LatLng(-41.111308, -72.399388));
        streetViewList.add(new LatLng(43.317136, 11.330675));
        streetViewList.add(new LatLng(60.132973, 6.754007));
        streetViewList.add(new LatLng(47.421215, 10.98632));
        streetViewList.add(new LatLng(33.872132, 73.462546));
        streetViewList.add(new LatLng(1.221572, 103.848377));
        streetViewList.add(new LatLng(46.807648, -71.208281));
        streetViewList.add(new LatLng(50.957255, -1.752088));
        streetViewList.add(new LatLng(52.058723, -4.785427));
        streetViewList.add(new LatLng(5.553927, -0.200554));
        streetViewList.add(new LatLng(30.625175, 72.866206));
        streetViewList.add(new LatLng(-13.165071, -72.544715));
        streetViewList.add(new LatLng(14.673522, -17.449128));
        streetViewList.add(new LatLng(18.471385, -66.124589));
        streetViewList.add(new LatLng(45.832617, 6.865173));
        streetViewList.add(new LatLng(7.213689, 99.063963));
        streetViewList.add(new LatLng(31.568901, 74.309167));
        streetViewList.add(new LatLng(23.487231, 120.959349));
        streetViewList.add(new LatLng(26.202737, 127.289096));
        streetViewList.add(new LatLng(-25.687667, -54.443836));
        streetViewList.add(new LatLng(37.729414, -119.635868));
        streetViewList.add(new LatLng(45.694772, 10.08015));
        streetViewList.add(new LatLng(-45.394324, 167.529843));
        streetViewList.add(new LatLng(31.568333, 74.308207));
        streetViewList.add(new LatLng(37.24498, -122.145735));
        streetViewList.add(new LatLng(-34.607794, -58.370284));
        streetViewList.add(new LatLng(-32.023521, 115.450708));
        streetViewList.add(new LatLng(30.321265, 35.463307));
        streetViewList.add(new LatLng(31.583211, 74.309754));
        streetViewList.add(new LatLng(20.394425, 121.964275));
        streetViewList.add(new LatLng(47.181402, 0.05171));
        streetViewList.add(new LatLng(-3.1377605, -60.4933549));
        streetViewList.add(new LatLng(53.5437527, 9.9895097));
        streetViewList.add(new LatLng(27.1738903, 78.0419927));
        streetViewList.add(new LatLng(31.588574, 74.314857));
        streetViewList.add(new LatLng(13.412283, 103.8667697));
        streetViewList.add(new LatLng(51.7782918, -3.5549115));
        streetViewList.add(new LatLng(19.3021021, -99.1866006));
        streetViewList.add(new LatLng(-33.4273637, -70.6270822));
        streetViewList.add(new LatLng(31.622511, 74.303384));
        streetViewList.add(new LatLng(22.4167711, 114.1344739));
        streetViewList.add(new LatLng(0.5738056, 37.5750334));
        streetViewList.add(new LatLng(-33.8573944, 151.2154351));
        streetViewList.add(new LatLng(35.3594829, 138.7312602));
        streetViewList.add(new LatLng(29.9803885, 31.1329825));
        streetViewList.add(new LatLng(31.582459, 74.318482));
        streetViewList.add(new LatLng(25.6011274, 56.275658));
        streetViewList.add(new LatLng(22.2814232, 114.1892491));
        streetViewList.add(new LatLng(-4.675536, 29.6239701));
        streetViewList.add(new LatLng(36.7307426, 138.8485201));
        streetViewList.add(new LatLng(44.5961715, -63.5532612));
        streetViewList.add(new LatLng(31.588574, 74.314857));
        streetViewList.add(new LatLng(38.4810385, -122.9935721));
        streetViewList.add(new LatLng(-0.6130748, 30.8167786));
        streetViewList.add(new LatLng(33.8818358, 133.7601358));
        streetViewList.add(new LatLng(-33.9888381, 18.4310415));
        streetViewList.add(new LatLng(37.7431068, -119.5929432));
        streetViewList.add(new LatLng(32.965205, 73.571571));
        streetViewList.add(new LatLng(47.6151872, -122.3559095));
        streetViewList.add(new LatLng(37.7431068, -119.5929432));
        streetViewList.add(new LatLng(44.4716284, 9.9273314));
        streetViewList.add(new LatLng(44.9966156, 7.6060196));
        streetViewList.add(new LatLng(34.1017962, 136.2063821));
        streetViewList.add(new LatLng(31.574361, 74.482115));
        streetViewList.add(new LatLng(52.0815499, 4.3051539));
        streetViewList.add(new LatLng(59.3258838, 18.0718926));
        streetViewList.add(new LatLng(44.4968649, 11.3524586));
        streetViewList.add(new LatLng(-3.8387836, -32.4107365));
        streetViewList.add(new LatLng(45.4570045, 10.7074594));
        streetViewList.add(new LatLng(31.572677, 74.3065));
        streetViewList.add(new LatLng(-51.2929155, -60.5571657));
        streetViewList.add(new LatLng(43.2748019, -8.990355));
        streetViewList.add(new LatLng(44.9443399, 6.4963337));
        streetViewList.add(new LatLng(50.3794235, 2.773595));
        streetViewList.add(new LatLng(31.081636, 74.004072));
        streetViewList.add(new LatLng(-21.1854322, 55.5295826));
        streetViewList.add(new LatLng(48.865766, 2.3123589));
        streetViewList.add(new LatLng(51.0965481, 1.2034026));
        streetViewList.add(new LatLng(13.51481, 144.8068167));
        streetViewList.add(new LatLng(29.773048, 72.022419));
        streetViewList.add(new LatLng(25.2618177, 51.6133991));
        streetViewList.add(new LatLng(57.3247028, -4.4403388));
        streetViewList.add(new LatLng(27.7834303, 86.7232438));
        streetViewList.add(new LatLng(68.5090469, 27.481808));
        streetViewList.add(new LatLng(30.91703, 73.512948));
        streetViewList.add(new LatLng(42.4243778, 11.6350514));
        streetViewList.add(new LatLng(39.4514162, -120.2931912));
        streetViewList.add(new LatLng(40.6662307, 16.6105394));
        streetViewList.add(new LatLng(42.1335346, 12.0785144));
        streetViewList.add(new LatLng(47.59525, -122.3316443));
        streetViewList.add(new LatLng(32.723487, 72.950912));
        streetViewList.add(new LatLng(52.3598717, 4.8860311));
        streetViewList.add(new LatLng(35.7090719, 139.720969));
        streetViewList.add(new LatLng(-22.9121653, -43.2302485));
        streetViewList.add(new LatLng(19.4264742, -99.186645));
        streetViewList.add(new LatLng(-3.078972, -60.038147));
        streetViewList.add(new LatLng(32.962703, 73.573653));
        streetViewList.add(new LatLng(36.0977305, -112.0955717));
        streetViewList.add(new LatLng(36.4522249, -111.8371627));
        streetViewList.add(new LatLng(50.0861736, 14.413668));
        streetViewList.add(new LatLng(-1.2392027, -90.3857351));
        streetViewList.add(new LatLng(23.0676658, 53.7880529));
        streetViewList.add(new LatLng(31.569467, 74.321351));
        streetViewList.add(new LatLng(30.7409413, 104.1392259));
        streetViewList.add(new LatLng(35.4284078, 138.8618192));
        streetViewList.add(new LatLng(55.4443692, 14.2509393));
        streetViewList.add(new LatLng(45.4328051, 12.3405832));
        streetViewList.add(new LatLng(-8.7370392, 119.412259));
        streetViewList.add(new LatLng(31.554154, 74.331592));
        streetViewList.add(new LatLng(64.571418, -149.0163929));
        streetViewList.add(new LatLng(37.0121949, 24.7122498));
        streetViewList.add(new LatLng(37.0121949, 24.7122498));
        streetViewList.add(new LatLng(32.5436465, 34.9357438));
        streetViewList.add(new LatLng(28.423575, 69.911458));
        streetViewList.add(new LatLng(-33.7990738, 18.3749873));
        streetViewList.add(new LatLng(48.8583734, 2.2943675));
        streetViewList.add(new LatLng(38.4119183, 141.331895));
        streetViewList.add(new LatLng(-5.3045936, 72.2518816));
        streetViewList.add(new LatLng(31.569575, 74.31308));
        streetViewList.add(new LatLng(35.0545247, 135.6696805));
        streetViewList.add(new LatLng(40.724394, -73.9929852));
        streetViewList.add(new LatLng(47.507172, 19.0417304));
        streetViewList.add(new LatLng(25.3077605, 83.0108226));
        streetViewList.add(new LatLng(31.548372, 74.360071));
        streetViewList.add(new LatLng(40.0498116, 26.219028));
        streetViewList.add(new LatLng(7.3377012, 117.0478841));
        streetViewList.add(new LatLng(24.4131884, 54.4757479));
        streetViewList.add(new LatLng(46.3991741, 12.0620557));
        streetViewList.add(new LatLng(31.817258, 73.916479));
        streetViewList.add(new LatLng(57.1887296, -3.8528745));
        streetViewList.add(new LatLng(33.2464793, 126.5547625));
        streetViewList.add(new LatLng(42.9141199, 0.1360764));
        streetViewList.add(new LatLng(34.6874547, 135.5254503));
        streetViewList.add(new LatLng(31.538161, 74.339606));
        streetViewList.add(new LatLng(40.5396746, -108.9939756));
        streetViewList.add(new LatLng(-22.9518225, -43.2103652));
        streetViewList.add(new LatLng(46.5385039, 8.8214263));
        streetViewList.add(new LatLng(46.02574, 9.8780285));
        streetViewList.add(new LatLng(44.3432726, 6.8553789));
        streetViewList.add(new LatLng(48.1020789, 7.0786572));
        streetViewList.add(new LatLng(45.9848649, 7.748707));
        streetViewList.add(new LatLng(31.588634, 74.31483));
        streetViewList.add(new LatLng(43.7363698, 7.4261142));
        streetViewList.add(new LatLng(55.9054329, -2.1338347));
        streetViewList.add(new LatLng(42.4338148, 143.4013077));
        streetViewList.add(new LatLng(-54.0082926, -37.6931339));
        streetViewList.add(new LatLng(42.8886938, -9.1136128));
        streetViewList.add(new LatLng(19.4848077, -99.117759));
        streetViewList.add(new LatLng(0.0579368, -78.4923612));
        streetViewList.add(new LatLng(42.0887445, 14.1937039));
        streetViewList.add(new LatLng(19.0200445, -98.2448384));
        streetViewList.add(new LatLng(25.195232, 55.276428));
        streetViewList.add(new LatLng(41.0989375, -8.0816618));
        streetViewList.add(new LatLng(32.6280054, 129.7379019));
        streetViewList.add(new LatLng(45.4248361, -75.6997489));
        streetViewList.add(new LatLng(43.298942, 42.4641058));
        streetViewList.add(new LatLng(35.6822479, 139.7662536));
        streetViewList.add(new LatLng(56.3061475, -78.8758921));
        streetViewList.add(new LatLng(38.3558153, -122.2700231));
        streetViewList.add(new LatLng(59.5370001, -112.2302705));
        streetViewList.add(new LatLng(29.9803885, 31.1329825));
        streetViewList.add(new LatLng(37.3257889, 126.8139818));
        streetViewList.add(new LatLng(53.0633154, -4.0817971));
        streetViewList.add(new LatLng(42.8719328, 140.6669047));
        streetViewList.add(new LatLng(48.8634585, 2.3129887));
        streetViewList.add(new LatLng(35.3346711, 25.2245672));
        streetViewList.add(new LatLng(55.0223933, -2.3154889));
        streetViewList.add(new LatLng(37.2737157, -76.7020864));
        streetViewList.add(new LatLng(37.2901001, 13.5896609));
        streetViewList.add(new LatLng(14.1927108, 145.2254694));
        streetViewList.add(new LatLng(50.7398844, 15.3463993));
        streetViewList.add(new LatLng(51.5005644, -0.1223387));
        streetViewList.add(new LatLng(53.167435, -1.309288));
        streetViewList.add(new LatLng(41.497808, 76.425437));
        streetViewList.add(new LatLng(49.9827869, 20.0544207));
        streetViewList.add(new LatLng(62.214195, 25.772552));
        streetViewList.add(new LatLng(34.667009, 133.936379));
        streetViewList.add(new LatLng(58.758444, -93.231829));
        streetViewList.add(new LatLng(41.403609, 2.174448));
        streetViewList.add(new LatLng(41.904209, 140.650305));
        streetViewList.add(new LatLng(36.109859, -115.170842));
        streetViewList.add(new LatLng(46.309863, 6.076324));
        streetViewList.add(new LatLng(35.667228, 139.708859));
        streetViewList.add(new LatLng(28.605348, -80.669766));
        streetViewList.add(new LatLng(66.543514, 25.847051));
        streetViewList.add(new LatLng(35.012762, 135.750338));
        streetViewList.add(new LatLng(46.414382, 10.013988));
        streetViewList.add(new LatLng(42.435147, 1.536002));
        streetViewList.add(new LatLng(39.925177, 32.837174));
        streetViewList.add(new LatLng(25.242656, 55.371904));
        streetViewList.add(new LatLng(30.029001, 31.259644));
        streetViewList.add(new LatLng(30.85155, 29.664294));
        streetViewList.add(new LatLng(53.254919, 5.253953));
        streetViewList.add(new LatLng(-22.98695, -43.196101));
        streetViewList.add(new LatLng(-22.951879, -43.210216));
        streetViewList.add(new LatLng(32.878537, 131.051635));
        streetViewList.add(new LatLng(31.213635, 29.885642));
        streetViewList.add(new LatLng(30.005567, 31.229815));
        streetViewList.add(new LatLng(29.86907, 31.215795));
        streetViewList.add(new LatLng(43.704182, -72.288673));
        streetViewList.add(new LatLng(51.756454, -1.260214));
        streetViewList.add(new LatLng(41.08337, 29.051893));
        streetViewList.add(new LatLng(30.360837, 130.531898));
        streetViewList.add(new LatLng(36.800525, 117.841945));
        streetViewList.add(new LatLng(36.000712, 138.694797));
        streetViewList.add(new LatLng(34.557692, 117.74251));
        streetViewList.add(new LatLng(53.782392, -2.887673));
        streetViewList.add(new LatLng(63.734793, -68.515493));
        streetViewList.add(new LatLng(51.113958, 17.033783));
        streetViewList.add(new LatLng(52.288829, -1.205545));
        streetViewList.add(new LatLng(19.921671, 99.040533));
        streetViewList.add(new LatLng(40.975863, 29.07033));
        streetViewList.add(new LatLng(54.416269, -1.200632));
        streetViewList.add(new LatLng(49.936337, -124.831295));
        streetViewList.add(new LatLng(32.805619, 130.706046));
        streetViewList.add(new LatLng(-62.59609, -59.901651));
        streetViewList.add(new LatLng(-85.00001, -54.150798));
        streetViewList.add(new LatLng(36.732633, 138.462177));
        streetViewList.add(new LatLng(24.48213, 54.354727));
        streetViewList.add(new LatLng(-35.842016, 138.133209));
        streetViewList.add(new LatLng(25.030517, 88.976545));
        streetViewList.add(new LatLng(50.84637, 4.35292));
        streetViewList.add(new LatLng(32.250472, -64.823307));
        streetViewList.add(new LatLng(-16.495603, -68.133494));
        streetViewList.add(new LatLng(-2.945071, -60.676237));
        streetViewList.add(new LatLng(42.694163, 23.326531));
        streetViewList.add(new LatLng(11.576137, 104.922918));
        streetViewList.add(new LatLng(59.537, -112.230271));
        streetViewList.add(new LatLng(33.14786, 129.672837));
        streetViewList.add(new LatLng(41.403609, 2.174448));
        streetViewList.add(new LatLng(-46.296347, -71.933337));
        streetViewList.add(new LatLng(44.06181, 7.711943));
        streetViewList.add(new LatLng(4.837607, -75.548666));
        streetViewList.add(new LatLng(4.657327, -74.088447));
        streetViewList.add(new LatLng(44.893379, 15.603609));
        streetViewList.add(new LatLng(50.08687, 14.4165));
        streetViewList.add(new LatLng(56.159226, 10.191424));
        streetViewList.add(new LatLng(56.846954, -5.093128));
        streetViewList.add(new LatLng(4.837607, -75.548666));
        streetViewList.add(new LatLng(29.867805, 31.216356));
        streetViewList.add(new LatLng(47.249767, 0.29165));
        streetViewList.add(new LatLng(52.521621, 13.396513));
        streetViewList.add(new LatLng(35.582077, 23.593284));
        streetViewList.add(new LatLng(19.419751, -155.288204));
        streetViewList.add(new LatLng(32.806162, 130.706371));
        streetViewList.add(new LatLng(13.51481, 144.806817));
        streetViewList.add(new LatLng(46.250057, 20.146363));
        streetViewList.add(new LatLng(43.436051, 142.667102));
        streetViewList.add(new LatLng(33.165089, -110.79161));
        streetViewList.add(new LatLng(65.827953, -16.383705));
        streetViewList.add(new LatLng(53.339545, -6.2715));
        streetViewList.add(new LatLng(32.8813, 35.578729));
        streetViewList.add(new LatLng(44.530143, 10.861284));
        streetViewList.add(new LatLng(41.890062, 12.492549));
        streetViewList.add(new LatLng(34.828293, 137.66825));
        streetViewList.add(new LatLng(37.276597, 140.996672));
        streetViewList.add(new LatLng(32.281739, 35.891208));
        streetViewList.add(new LatLng(37.577886, 126.976966));
        streetViewList.add(new LatLng(35.973165, 138.263109));
        streetViewList.add(new LatLng(17.966526, 102.593592));
        streetViewList.add(new LatLng(37.129604, 140.038028));
        streetViewList.add(new LatLng(-20.250515, 44.418992));
        streetViewList.add(new LatLng(20.682512, -88.568789));
        streetViewList.add(new LatLng(47.807421, 107.530221));
        streetViewList.add(new LatLng(52.356341, 4.879227));
        streetViewList.add(new LatLng(33.414742, 131.627942));
        streetViewList.add(new LatLng(-32.252115, -70.09057));
        streetViewList.add(new LatLng(-16.398309, -71.536861));
        streetViewList.add(new LatLng(52.240364, 21.019292));
        streetViewList.add(new LatLng(39.659188, -8.826204));
        streetViewList.add(new LatLng(51.507826, 156.994889));
        streetViewList.add(new LatLng(37.577886, 126.976966));
        streetViewList.add(new LatLng(44.815939, 20.459167));
        streetViewList.add(new LatLng(49.133269, 20.428262));
        streetViewList.add(new LatLng(46.37607, 14.128075));
        streetViewList.add(new LatLng(-33.98885, 18.431044));
        streetViewList.add(new LatLng(36.915956, -4.77238));
        streetViewList.add(new LatLng(6.93092, 79.842328));
        streetViewList.add(new LatLng(48.842014, 2.252968));
        streetViewList.add(new LatLng(45.983426, 7.763384));
        streetViewList.add(new LatLng(51.878515, -3.746025));
        streetViewList.add(new LatLng(55.948673, -3.199851));
        streetViewList.add(new LatLng(-34.957816, -54.937016));
        streetViewList.add(new LatLng(37.912654, 139.06231));
        streetViewList.add(new LatLng(35.674973, 135.287543));
    }

    private void callPlaceSearchIntent()
    {
        try
        {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        }
        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
        {
            // TODO: Handle the error.
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try
        {
            if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE && data != null)
            {
                if (resultCode == RESULT_OK)
                {
                    place = PlaceAutocomplete.getPlace(this, data);
                    Log.i(TAG, "Place:" + place.toString());

                    startActivity(new Intent(context, StreetViewMapActivity.class)
                            .putExtra("latLng", place.getLatLng())
                            .putExtra("name", place.getName()));
                }
                else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
                {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    Log.i(TAG, status.getStatusMessage());
                }
            }
        }
        catch (Exception ignored)
        {
        }
    }

    @Override
    public void onBackPressed()
    {

            super.onBackPressed();

    }

    private void BannerAdmob()
    {
        final AdView adView = this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama)
    {
        if (streetViewPanorama != null)
        {
            mStreetViewPanorama = streetViewPanorama;
            mStreetViewPanorama.setPosition(streetViewList.get(randInt()));
            mStreetViewPanorama.setStreetNamesEnabled(true);
            mStreetViewPanorama.setUserNavigationEnabled(true);
            mStreetViewPanorama.setZoomGesturesEnabled(true);
            mStreetViewPanorama.setPanningGesturesEnabled(true);
        }
    }
}
