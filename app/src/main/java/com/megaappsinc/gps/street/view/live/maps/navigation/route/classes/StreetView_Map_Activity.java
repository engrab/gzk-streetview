package com.megaappsinc.gps.street.view.live.maps.navigation.route.classes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.material.tabs.TabLayout;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;

import java.util.List;
import java.util.Locale;

public class StreetView_Map_Activity extends AppCompatActivity {

    LatLng latLng;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            super.onBackPressed();
        }
    }

    private void Init() {
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager container = findViewById(R.id.container);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        tabs.setSelectedTabIndicatorHeight(2);
        container.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streetview_map_activity);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            latLng = bundle.getParcelable("latLng");
        }
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Init();

    }

    public static class MapFragment extends Fragment implements OnMapReadyCallback {

        private Activity context;
        private LatLng latLng;
        private MapView mMapView;
        private Bundle mBundle;
        private GoogleMap mMap;
        TextView knowAddressText, cityText, addressText, longitudeText, latitudeText, postalCodeText, stateText, countyText;
        public MapFragment() {
            // Required empty public constructor
        }

        public static MapFragment newInstance(LatLng latLng) {
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            args.putParcelable("latLng", latLng);
            fragment.setArguments(args);
            return fragment;
        }

        private void Init(View view) {
            context = getActivity();
            if (getArguments() != null) {
                latLng = getArguments().getParcelable("latLng");
            }
            MapsInitializer.initialize(context);
            mMapView = view.findViewById(R.id.map);
            knowAddressText = view.findViewById(R.id.knowAddressText);
            cityText = view.findViewById(R.id.cityText);
            addressText =view.findViewById(R.id.addressText);
            longitudeText =view.findViewById(R.id.longitudeText);
            latitudeText = view.findViewById(R.id.latitudeText);
            postalCodeText = view.findViewById(R.id.postalCodeText);
            stateText = view.findViewById(R.id.stateText);
            countyText = view.findViewById(R.id.countyText);
            knowAddressText.setTypeface(null, Typeface.ITALIC);
            cityText.setTypeface(null, Typeface.ITALIC);
            addressText.setTypeface(null, Typeface.ITALIC);
            longitudeText.setTypeface(null, Typeface.ITALIC);
            latitudeText.setTypeface(null, Typeface.ITALIC);
            postalCodeText.setTypeface(null, Typeface.ITALIC);
            stateText.setTypeface(null, Typeface.ITALIC);
            countyText.setTypeface(null, Typeface.ITALIC);
            mMapView.onCreate(mBundle);
            mMapView.getMapAsync(this);
            setAddress(latLng.longitude, latLng.latitude);
            view.findViewById(R.id.btn_satellite).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMap != null) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    }
                }
            });
            view.findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMap != null) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                }
            });
            view.findViewById(R.id.hybrid).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMap != null) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }
                }
            });
        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mBundle = savedInstanceState;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_map, container, false);
            Init(view);
            return view;


        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            if (mMap != null) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.addMarker(new MarkerOptions().position(latLng).title(Utils.getCompleteAddressString(context, latLng.latitude, latLng.longitude)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            if (mMapView != null) {
                mMapView.onResume();
            }
        }

        @Override
        public void onPause() {
            if (mMapView != null) {
                mMapView.onPause();
            }
            super.onPause();
        }

        @Override
        public void onDestroy() {
            if (mMapView != null) {
                mMapView.onDestroy();
            }
            super.onDestroy();
        }
        private void setAddress(double longitude, double latitude) {
            try {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
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
    }

    public static class StreetViewFragment extends Fragment implements OnStreetViewPanoramaReadyCallback {

        private Activity context;
        private LatLng latLng;
        private StreetViewPanoramaView streetViewPanoramaView;
        private Bundle mBundle;


        public StreetViewFragment() {
            // Required empty public constructor
        }

        public static StreetViewFragment newInstance(LatLng latLng) {
            StreetViewFragment fragment = new StreetViewFragment();
            Bundle args = new Bundle();
            args.putParcelable("latLng", latLng);
            fragment.setArguments(args);
            return fragment;

        }

        private void Init(View view) {
            context = getActivity();
            if (getArguments() != null) {
                latLng = getArguments().getParcelable("latLng");
            }
            streetViewPanoramaView = view.findViewById(R.id.streetviewpanorama);
            streetViewPanoramaView.onCreate(mBundle);
            streetViewPanoramaView.getStreetViewPanoramaAsync(this);

        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mBundle = savedInstanceState;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_streetview, container, false);
            Init(view);
            return view;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
        }

        @Override
        public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
            try {
                streetViewPanorama.setPosition(latLng);
                streetViewPanorama.setOnStreetViewPanoramaChangeListener(new StreetViewPanorama.OnStreetViewPanoramaChangeListener() {
                    @Override
                    public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
                        if (streetViewPanoramaLocation == null || streetViewPanoramaLocation.links == null) {
                            try {
                                Toast.makeText(context.getApplicationContext(), "Street View Not Available at this Location",
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception ignored) {
                            }
                        }
                    }
                });
                streetViewPanorama.setStreetNamesEnabled(true);
                streetViewPanorama.setUserNavigationEnabled(true);
                streetViewPanorama.setZoomGesturesEnabled(true);
                streetViewPanorama.setPanningGesturesEnabled(true);
            } catch (Exception ignored) {
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            if (streetViewPanoramaView != null) {
                streetViewPanoramaView.onResume();
            }
        }

        @Override
        public void onPause() {
            if (streetViewPanoramaView != null) {
                streetViewPanoramaView.onPause();
            }
            super.onPause();
        }

        @Override
        public void onDestroy() {
            if (streetViewPanoramaView != null) {
                streetViewPanoramaView.onDestroy();
            }
            super.onDestroy();
        }
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return MapFragment.newInstance(latLng);
            } else {
                return StreetViewFragment.newInstance(latLng);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Map View";
                case 1:
                    return "StreetView";

            }
            return null;
        }

    }
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}


