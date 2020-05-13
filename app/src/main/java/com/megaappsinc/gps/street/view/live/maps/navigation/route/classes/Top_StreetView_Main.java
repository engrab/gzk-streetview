package com.megaappsinc.gps.street.view.live.maps.navigation.route.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Top_StreetView_Main extends AppCompatActivity {
    Activity context;
    List<TopStreetView_Model> placesModelList;
    RecyclerView lvfamus;
    InterstitialAd mInterstitialAd;
    int mPosition = 0;
    boolean isFromBackPress = false;
    TextView tvTitle;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_top_streetview);
            context = Top_StreetView_Main.this;
            tvTitle = findViewById(R.id.tvTitle);
            mAdView = findViewById(R.id.adView);
            mAdView.loadAd(new AdRequest.Builder().build());
                listOptions();
            BannerAdmob();
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
            requestNewInterstitial();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    if (!isFromBackPress) {
                        clickListener();
                    }
                }
            });
        } catch (Exception ignored) {
        }
    }

    private void listOptions() {
        try {
            placesModelList = new ArrayList<>();
            tvTitle.setText(String.valueOf("Exploring World"));
            placesModelList.add(new TopStreetView_Model("Museums In Latin America", -25.410076,-49.267302));
            placesModelList.add(new TopStreetView_Model("Mountain", 27.994511, 86.828381));
            placesModelList.add(new TopStreetView_Model("Rivers", 5.859013, -162.104696));
            placesModelList.add(new TopStreetView_Model("Famous", 25.197197, 55.274376));
            placesModelList.add(new TopStreetView_Model("Ocean", 5.87155,-162.110352));
            placesModelList.add(new TopStreetView_Model("Tv Studio",  -34.620483,-58.387732));
            placesModelList.add(new TopStreetView_Model("Exploring Africa", -32.0349255, 29.1122834));
            placesModelList.add(new TopStreetView_Model("Railway", 48.8804161, 2.3551932));
            placesModelList.add(new TopStreetView_Model("Aqua", 21.0839509, -86.77503));
            placesModelList.add(new TopStreetView_Model("Airports", 31.1443439, 121.808273));
            placesModelList.add(new TopStreetView_Model("Black History Culture In The Us", 34.721838,-92.288331));
            placesModelList.add(new TopStreetView_Model("Christmas Island", -10.462708,105.706782));
            placesModelList.add(new TopStreetView_Model("Cocos Keeling Islands", -12.0911,96.885288));
            placesModelList.add(new TopStreetView_Model("Australia Highlights",-37.822779,144.981893));
            placesModelList.add(new TopStreetView_Model("World Wonder Project",35.012762,135.750338));
            placesModelList.add(new TopStreetView_Model("RailwayStations",52.3791283, 4.900272));
            placesModelList.add(new TopStreetView_Model("Sea Ports",43.7363698, 7.4261142));
            placesModelList.add(new TopStreetView_Model("DryPorts",-34.5799412, -58.3743793));
            placesModelList.add(new TopStreetView_Model("Beaches",-33.89102, 151.277726));
            placesModelList.add(new TopStreetView_Model("Leakes",10.1617905, 166.0113424));
            if (placesModelList != null && placesModelList.size() > 0) {
                lvfamus = findViewById(R.id.rvItems);
                lvfamus.setLayoutManager(new GridLayoutManager(context, 2));
                lvfamus.setAdapter(new TopStreetView_Adaptor());
            }
        } catch (Exception ignored) {
        }
    }

    private void clickListener() {
        try {
            startActivity(new Intent(context, Top_StreetView.class)
                    .putExtra("Type", placesModelList.get(mPosition).PlaceName));
        } catch (Exception ignored) {
        }
    }


    private void requestNewInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        mAdView = this.findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }

    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
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

    public class TopStreetView_Adaptor extends RecyclerView.Adapter<TopStreetView_Adaptor.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.places_row_new, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv_new.setText(placesModelList.get(position).PlaceName);
            String url = "https://maps.googleapis.com/maps/api/streetview?size=300x300&location=" +
                    placesModelList.get(position).latitude + "," + placesModelList.get(position).longitude + "&heading=151.78&pitch=-0.76&key=AIzaSyBo3ZMOQ34ff3FQMqSj7ZF5Fi52Jgy9vPE";
            Picasso.get().load(url).into(holder.ivImage);
        }

        @Override
        public int getItemCount() {
            return placesModelList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_new;
            ImageView ivImage;

            ViewHolder(View itemView) {
                super(itemView);
                tv_new = itemView.findViewById(R.id.tvTitle);
                ivImage = itemView.findViewById(R.id.ivImage);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPosition = getAdapterPosition();
                        isFromBackPress = false;
                        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            clickListener();
                        }
                    }
                });
            }
        }
    }
}
