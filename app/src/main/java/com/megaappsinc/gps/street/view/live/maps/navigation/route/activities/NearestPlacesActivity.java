package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.model.NearByModel;

import java.util.ArrayList;
import java.util.List;

public class NearestPlacesActivity extends AppCompatActivity {
    List<NearByModel> list;
    InterstitialAd mInterstitialAd;
    boolean isFromBackPress = false;
    int mPosition = 0;
    AdView mAdView;

    private void SetupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isFromBackPress && mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        isFromBackPress = true;
                        mInterstitialAd.show();
                    } else {
                        finish();
                    }
                }
            });
        } catch (Exception ignored) {
        }
    }

    private void listOptions() {
        try {
            list = new ArrayList<>();
            list.add(new NearByModel(R.drawable.accountant, getString(R.string.txt_accounting)));
            list.add(new NearByModel(R.drawable.airport, getString(R.string.txt_airport)));
            list.add(new NearByModel(R.drawable.amusementpark, getString(R.string.txt_amusement_park)));
            list.add(new NearByModel(R.drawable.aquarium, getString(R.string.txt_aquarium)));
            list.add(new NearByModel(R.drawable.artgallery, getString(R.string.txt_art_gallery)));
            list.add(new NearByModel(R.drawable.atm, getString(R.string.txt_atm)));
            list.add(new NearByModel(R.drawable.bakery, getString(R.string.txt_bakery)));
            list.add(new NearByModel(R.drawable.bank, getString(R.string.txt_bank)));
            list.add(new NearByModel(R.drawable.bar_near, getString(R.string.txt_bar)));
            list.add(new NearByModel(R.drawable.beauty_salon, getString(R.string.txt_beauty_salon)));
            list.add(new NearByModel(R.drawable.bicycle, getString(R.string.txt_bicycle_store)));
            list.add(new NearByModel(R.drawable.bookstore, getString(R.string.txt_book_store)));
            list.add(new NearByModel(R.drawable.bowlingalley, getString(R.string.txt_bowling_alley)));
            list.add(new NearByModel(R.drawable.bus_station, getString(R.string.txt_bus_stop)));
            list.add(new NearByModel(R.drawable.cafe, getString(R.string.txt_cafe)));
            list.add(new NearByModel(R.drawable.car_dealer, getString(R.string.txt_car_dealer)));
            list.add(new NearByModel(R.drawable.car_rental, getString(R.string.txt_car_rental)));
            list.add(new NearByModel(R.drawable.car_repair, getString(R.string.txt_car_repair)));
            list.add(new NearByModel(R.drawable.car_wash, getString(R.string.txt_car_wash)));
            list.add(new NearByModel(R.drawable.casino, getString(R.string.txt_casino)));
            list.add(new NearByModel(R.drawable.church, getString(R.string.txt_church)));
            list.add(new NearByModel(R.drawable.cemetery, getString(R.string.txt_cemetery)));
            list.add(new NearByModel(R.drawable.courthouse, getString(R.string.txt_court)));
            list.add(new NearByModel(R.drawable.city_hall, getString(R.string.txt_city_hall)));
            list.add(new NearByModel(R.drawable.convenience_store, getString(R.string.txt_convenience_store)));
            list.add(new NearByModel(R.drawable.clothing_store, getString(R.string.txt_clothing_store)));
            list.add(new NearByModel(R.drawable.dentist, getString(R.string.txt_dentist)));
            list.add(new NearByModel(R.drawable.department_store, getString(R.string.txt_department_store)));
            list.add(new NearByModel(R.drawable.doctor, getString(R.string.txt_doctor)));
            list.add(new NearByModel(R.drawable.electrician, getString(R.string.txt_electrician)));
            list.add(new NearByModel(R.drawable.electronics_store, getString(R.string.txt_electronics_store)));
            list.add(new NearByModel(R.drawable.establishment, getString(R.string.txt_establishment)));
            list.add(new NearByModel(R.drawable.embassy, getString(R.string.txt_embassy)));
            list.add(new NearByModel(R.drawable.finance, getString(R.string.txt_finance)));
            list.add(new NearByModel(R.drawable.fire_station, getString(R.string.txt_fire_station)));
            list.add(new NearByModel(R.drawable.florist, getString(R.string.txt_florist)));
            list.add(new NearByModel(R.drawable.food, getString(R.string.txt_food)));
            list.add(new NearByModel(R.drawable.funeral_home, getString(R.string.txt_funeral_home)));
            list.add(new NearByModel(R.drawable.furniture_store, getString(R.string.txt_furniture_store)));
            list.add(new NearByModel(R.drawable.gas_station, getString(R.string.txt_gas_station)));
            list.add(new NearByModel(R.drawable.general_contractor, getString(R.string.txt_general_contractor)));
            list.add(new NearByModel(R.drawable.gym, getString(R.string.txt_gym)));
            list.add(new NearByModel(R.drawable.hair_care, getString(R.string.txt_hair_care)));
            list.add(new NearByModel(R.drawable.health, getString(R.string.txt_health)));
            list.add(new NearByModel(R.drawable.hardware_store, getString(R.string.txt_hardware_store)));
            list.add(new NearByModel(R.drawable.home_goods_store, getString(R.string.txt_home_goods_store)));
            list.add(new NearByModel(R.drawable.hospital, getString(R.string.txt_hospital)));
            list.add(new NearByModel(R.drawable.insurance_agency, getString(R.string.txt_insurance_agency)));
            list.add(new NearByModel(R.drawable.jewelry_store, getString(R.string.txt_jewelry_store)));
            list.add(new NearByModel(R.drawable.laundry, getString(R.string.txt_laundry)));
            list.add(new NearByModel(R.drawable.lawyer, getString(R.string.txt_lawyer)));
            list.add(new NearByModel(R.drawable.liquor_store, getString(R.string.txt_liquor_store)));
            list.add(new NearByModel(R.drawable.library, getString(R.string.txt_library)));
            list.add(new NearByModel(R.drawable.local_government_office, getString(R.string.txt_local_government_office)));
            list.add(new NearByModel(R.drawable.locksmith, getString(R.string.txt_locksmith)));
            list.add(new NearByModel(R.drawable.lodging, getString(R.string.txt_lodging)));
            list.add(new NearByModel(R.drawable.meal_delivery, getString(R.string.txt_meal_delivery)));
            list.add(new NearByModel(R.drawable.meal_takeaway, getString(R.string.txt_meal_takeaway)));
            list.add(new NearByModel(R.drawable.mosque, getString(R.string.txt_mosque)));
            list.add(new NearByModel(R.drawable.movie_rental, getString(R.string.txt_movie_rental)));
            list.add(new NearByModel(R.drawable.moving_company, getString(R.string.txt_moving_company)));
            list.add(new NearByModel(R.drawable.movie_theater, getString(R.string.txt_movie_theater)));
            list.add(new NearByModel(R.drawable.museum, getString(R.string.txt_museum)));
            list.add(new NearByModel(R.drawable.night_club, getString(R.string.txt_night_club)));
            list.add(new NearByModel(R.drawable.painter, getString(R.string.txt_painter)));
            list.add(new NearByModel(R.drawable.park, getString(R.string.txt_park)));
            list.add(new NearByModel(R.drawable.pet_store, getString(R.string.txt_pet_store)));
            list.add(new NearByModel(R.drawable.parking, getString(R.string.txt_parking)));
            list.add(new NearByModel(R.drawable.pharmacy, getString(R.string.txt_pharmacy)));
            list.add(new NearByModel(R.drawable.physiotherapist, getString(R.string.txt_physiotherapist)));
            list.add(new NearByModel(R.drawable.place_of_worship, getString(R.string.txt_place_worship)));
            list.add(new NearByModel(R.drawable.plumber, getString(R.string.txt_plumber)));
            list.add(new NearByModel(R.drawable.post_office, getString(R.string.txt_post_office)));
            list.add(new NearByModel(R.drawable.police, getString(R.string.txt_police)));
            list.add(new NearByModel(R.drawable.real_estate_agency, getString(R.string.txt_real_estate_agency)));
            list.add(new NearByModel(R.drawable.restaurant, getString(R.string.txt_restaurant)));
            list.add(new NearByModel(R.drawable.roofing_contractor, getString(R.string.txt_roofing_contractor)));
            list.add(new NearByModel(R.drawable.rv_park, getString(R.string.txt_rv_park)));
            list.add(new NearByModel(R.drawable.school, getString(R.string.txt_school)));
            list.add(new NearByModel(R.drawable.shopping_mall, getString(R.string.txt_shopping_mall)));
            list.add(new NearByModel(R.drawable.shoe_store, getString(R.string.txt_shoe_store)));
            list.add(new NearByModel(R.drawable.spa, getString(R.string.txt_spa)));
            list.add(new NearByModel(R.drawable.stadium, getString(R.string.txt_stadium)));
            list.add(new NearByModel(R.drawable.storage, getString(R.string.txt_storage)));
            list.add(new NearByModel(R.drawable.store, getString(R.string.txt_store)));
            list.add(new NearByModel(R.drawable.supermarket, getString(R.string.txt_super_market)));
            list.add(new NearByModel(R.drawable.subway_station, getString(R.string.txt_subway_station)));
            list.add(new NearByModel(R.drawable.synagogue, getString(R.string.txt_synagogue)));
            list.add(new NearByModel(R.drawable.taxi_stand, getString(R.string.txt_taxi_stand)));
            list.add(new NearByModel(R.drawable.hindu_temple, getString(R.string.txt_temple)));
            list.add(new NearByModel(R.drawable.train_station, getString(R.string.txt_train_station)));
            list.add(new NearByModel(R.drawable.transit_station, getString(R.string.txt_transit_station)));
            list.add(new NearByModel(R.drawable.travel_agency, getString(R.string.txt_travel_agency)));
            list.add(new NearByModel(R.drawable.university, getString(R.string.txt_university)));
            list.add(new NearByModel(R.drawable.veterinary_care, getString(R.string.txt_veterinary_care)));
            list.add(new NearByModel(R.drawable.zoo, getString(R.string.txt_zoo)));
        } catch (Exception ignored) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.nearest_places);
            SetupToolbar();
            AppPurchasePref appPurchasePref = new AppPurchasePref(NearestPlacesActivity.this);
            if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

                BannerAdmob();
                mInterstitialAd = new InterstitialAd(this);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
                requestNewInterstitial();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        try {
                            requestNewInterstitial();
                            if (!isFromBackPress) {
                                showNearBy();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                });
            }
            listOptions();
            GridView gv = findViewById(R.id.gridView1_masha);
            gv.setAdapter(new AdapterNearestPlaces(this));
        } catch (Exception ignored) {
        }
    }

    private void requestNewInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void BannerAdmob() {
        final AdView adView = this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });
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


    @Override
    public void onBackPressed() {
        if (!isFromBackPress && mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            isFromBackPress = true;
            mInterstitialAd.show();
        } else {
            super.onBackPressed();
        }
    }

    private void showNearBy() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + list.get(mPosition).text);
        Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public class AdapterNearestPlaces extends BaseAdapter {
        private LayoutInflater inflater_new;
        private Context context;

        AdapterNearestPlaces(NearestPlacesActivity mainActivity) {
            context = mainActivity;
            inflater_new = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView = inflater_new.inflate(R.layout.near_by_row_new, null);
            try {
                holder.tv_new = rowView.findViewById(R.id.texticon_new);
                holder.img_new = rowView.findViewById(R.id.tpyeicon_new);
                holder.tv_new.setText(list.get(position).text);
                Glide.with(context).load(list.get(position).id).into(holder.img_new);
                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            mPosition = position;
                            isFromBackPress = false;
                            if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                showNearBy();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                });
            } catch (Exception ignored) {
            }
            return rowView;
        }

        class Holder {
            TextView tv_new;
            ImageView img_new;
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
