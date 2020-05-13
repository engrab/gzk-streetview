package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.google.android.gms.maps.model.LatLng;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.model.TopStreetViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopStreetViewActivity extends AppCompatActivity {
    Activity context;
    List<TopStreetViewModel> placesModelList;
    RecyclerView lvfamus;
    InterstitialAd mInterstitialAd;
    int mPosition = 0;
    TextView tvTitle;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_top_streetview);
            context = TopStreetViewActivity.this;
            tvTitle = findViewById(R.id.tvTitle);
            tvTitle.setTypeface(null, Typeface.ITALIC);
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                listOptions(bundle.getString("Type", ""));
            }
            AppPurchasePref appPurchasePref = new AppPurchasePref(this);
            if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

                BannerAdmob();
                mInterstitialAd = new InterstitialAd(this);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
                requestNewInterstitial();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestNewInterstitial();
                            clickListener();

                    }
                });
            }
        } catch (Exception ignored) {
        }
    }


    private void listOptions(String type) {
        try {
            placesModelList = new ArrayList<>();
            switch (type) {
                case "WorldWonders":
                    tvTitle.setText(String.valueOf("World Wonders"));
                    placesModelList.add(new TopStreetViewModel("Hanging Gardens of Babylon", 48.919861, 2.343222));
                    placesModelList.add(new TopStreetViewModel("Lighthouse of Alexandria", 38.790405, -77.040581));
                    placesModelList.add(new TopStreetViewModel("Temple of Artemis at Ephesus", 37.949872, 27.363381));
                    placesModelList.add(new TopStreetViewModel("Colossus of Rhodes", 36.451066, 28.225833));
                    placesModelList.add(new TopStreetViewModel("Statue of Zeus at Olympia", 38.099974, 21.583320));
                    placesModelList.add(new TopStreetViewModel("Great Pyramid of Giza", 29.979235, 31.134202));
                    placesModelList.add(new TopStreetViewModel("Mausoleum at Halicarnassus", 37.038132, 27.424385));
                    break;
                case "Famous":
                    tvTitle.setText(R.string.txt_famous_places);
                    placesModelList.add(new TopStreetViewModel("Kaaba", 21.422524, 39.826182));
                    placesModelList.add(new TopStreetViewModel("Burj Khalifa", 25.197197, 55.274376));
                    placesModelList.add(new TopStreetViewModel("Statue of Liberty", 40.689249, -74.044500));
                    placesModelList.add(new TopStreetViewModel("Notre-Dame de Paris", 48.852968, 2.349902));
                    placesModelList.add(new TopStreetViewModel("Ming tombs", 40.255149, 116.223556));
                    placesModelList.add(new TopStreetViewModel("Edinburgh Castle", 55.948595, -3.199913));
                    placesModelList.add(new TopStreetViewModel("Siena", 43.318809, 11.330757));
                    placesModelList.add(new TopStreetViewModel("Eiffel tower", 48.858370, 2.294481));
                    placesModelList.add(new TopStreetViewModel("Centre Georges Pompidou", 48.860642, 2.352245));
                    placesModelList.add(new TopStreetViewModel("Tower Bridge", 51.505597, -0.075338));
                    placesModelList.add(new TopStreetViewModel("Taj Mahal", 27.175015, 78.042155));
                    placesModelList.add(new TopStreetViewModel("Zandvoort", 52.371149, 4.533355));
                    placesModelList.add(new TopStreetViewModel("Acropolis of Athens", 37.9715322, 23.725749));
                    placesModelList.add(new TopStreetViewModel("Sagrada Família", 41.403630, 2.174356));
                    placesModelList.add(new TopStreetViewModel("Van Gogh Museum", 52.358416, 4.881076));
                    placesModelList.add(new TopStreetViewModel("London Eye", 51.503324, -0.119543));
                    placesModelList.add(new TopStreetViewModel("Colosseum", 41.890210, 12.492231));
                    placesModelList.add(new TopStreetViewModel("Louvre rivoli", 48.860719, 2.340958));
                    placesModelList.add(new TopStreetViewModel("Tower of London", 51.508112, -0.075949));
                    placesModelList.add(new TopStreetViewModel("Buckingham Palace", 51.501364, -0.141890));
                    placesModelList.add(new TopStreetViewModel("Stonehenge", 51.178882, -1.826215));
                    placesModelList.add(new TopStreetViewModel("Roman Forum", 41.892462, 12.485325));
                    break;
                case "Rivers":
                    tvTitle.setText(R.string.txt_rivers);
                    placesModelList.add(new TopStreetViewModel("Melon headed whales, Palmyra Atoll, USA", 5.859013, -162.104696));
                    placesModelList.add(new TopStreetViewModel("Mayreau Hot Springs, St Vincent & the Grenadines", 12.627667, -61.378149));
                    placesModelList.add(new TopStreetViewModel("The Channel, Palmyra Atoll, USA", 5.872685, -162.111947));
                    placesModelList.add(new TopStreetViewModel("Komodo Islands", -8.737039, 119.412259));
                    placesModelList.add(new TopStreetViewModel("Penguin Spit, Palmyra Atoll, USA", 5.87155, -162.110352));
                    placesModelList.add(new TopStreetViewModel("Yonaguni Monument, Yonaguni, Okinawa, Japan", 24.435919, 123.011134));
                    placesModelList.add(new TopStreetViewModel("Nishibama, Aka Island, Okinawa, Japan", 26.202737, 127.289096));
                    placesModelList.add(new TopStreetViewModel("Mega Jacks, Palmyra Atoll, USA", 5.864789, -162.14032));
                    placesModelList.add(new TopStreetViewModel("Giant Potato, Nagura Bay, Ishigaki, Okinawa, Japan", 24.399209, 124.117781));
                    placesModelList.add(new TopStreetViewModel("Jurassic Park, Palmyra Atoll, USA", 5.858315, -162.101257));
                    placesModelList.add(new TopStreetViewModel("Muli Kandu, Maldives", 2.927715, 73.590414));
                    placesModelList.add(new TopStreetViewModel("Yamada Point, Onna, Okinawa, Japan", 26.441502, 127.790058));
                    placesModelList.add(new TopStreetViewModel("Holei, Palmyra Atoll, USA", 5.866448, -162.067535));
                    placesModelList.add(new TopStreetViewModel("Ile Vache Marine, Chagos, British Indian Ocean Territory", -5.427774, 71.828873));
                    placesModelList.add(new TopStreetViewModel("Aharen Beach, Tokashiki, Okinawa, Japan", 26.168867, 127.343795));
                    placesModelList.add(new TopStreetViewModel("Crazy Corals, Palmyra Atoll, USA", 5.885904, -162.12561));
                    placesModelList.add(new TopStreetViewModel("The Liberty Wreck, Bali", -8.274153, 115.592694));
                    placesModelList.add(new TopStreetViewModel("Agariushi / Gahi Island, Zamami, Okinawa, Japan", 26.21729, 127.310158));
                    placesModelList.add(new TopStreetViewModel("Coral Gardens, Palmyra Atoll, USA", 5.885806, -162.060135));
                    placesModelList.add(new TopStreetViewModel("SS Antilla Shipwreck, Aruba", 12.601959, -70.058343));
                    placesModelList.add(new TopStreetViewModel("Leukan III A, Bunaken National Park, Indonesia", 1.602118, 124.766276));
                    placesModelList.add(new TopStreetViewModel("Fukui, Bunaken National Park, Indonesia", 1.611651, 124.739282));
                    placesModelList.add(new TopStreetViewModel("Batu Rufus, Indonesia", -0.565927, 130.284558));
                    placesModelList.add(new TopStreetViewModel("Timor II, Bunaken National Park, Indonesia", 1.618098, 124.781914));
                    placesModelList.add(new TopStreetViewModel("Shelly Beach, Australia", -33.893774, 151.282125));
                    placesModelList.add(new TopStreetViewModel("Aquarius Reef Base, Florida Keys - United States of America", 24.950493, -80.454069));
                    placesModelList.add(new TopStreetViewModel("Batu Bolong, Komodo, Indonesia", -8.537229, 119.613738));
                    placesModelList.add(new TopStreetViewModel("Galápagos Islands, Ecuador", -1.239203, -90.385735));
                    placesModelList.add(new TopStreetViewModel("Big Momma, American Samoa", -14.259231, -169.500376));
                    placesModelList.add(new TopStreetViewModel("Tatawa Kecil, Komodo, Indonesia", -8.53004, 119.62723));
                    placesModelList.add(new TopStreetViewModel("Crystal Bay, Indonesia", -8.715413, 115.457015));
                    placesModelList.add(new TopStreetViewModel("Tafeu Cove, American Samoa", -14.253016, -170.689206));
                    placesModelList.add(new TopStreetViewModel("Cape Kri, Raja Ampat, Indonesia", -0.556114, 130.690997));
                    placesModelList.add(new TopStreetViewModel("Devil's Crown, Galapagos", -1.216466, -90.42215));
                    placesModelList.add(new TopStreetViewModel("Nelson Island Outside, Chagos", -5.683498, 72.325919));
                    placesModelList.add(new TopStreetViewModel("Milky Way / Submarine, Sekisei Lagoon, Ishigaki, Okinawa, Japan", 24.300251, 124.08625));
                    placesModelList.add(new TopStreetViewModel("Taketomi-south Shark Home, Taketomi, Okinawa, Japan", 24.284623, 124.078206));
                    placesModelList.add(new TopStreetViewModel("Nudi's Retreat, Indonesia", 1.486096, 125.24151));
                    placesModelList.add(new TopStreetViewModel("Jaco Island, Timor Leste", -8.441477, 127.312285));
                    placesModelList.add(new TopStreetViewModel("Rarotonga, Cook Islands", -21.203982, -159.837009));
                    placesModelList.add(new TopStreetViewModel("Benwood Wreck, Florida Keys, USA", 25.052668, -80.332617));
                    placesModelList.add(new TopStreetViewModel("Cozumel, Mexico", 20.338045, -87.027587));
                    placesModelList.add(new TopStreetViewModel("Horseshoe Bay, Bermuda", 32.244589, -64.822922));
                    placesModelList.add(new TopStreetViewModel("Three Rock, Green Island, Taiwan", 22.639565, 121.490911));
                    placesModelList.add(new TopStreetViewModel("Moresby Island Inside, Chagos, British Indian Ocean Territory", -5.243066, 71.825168));
                    placesModelList.add(new TopStreetViewModel("Jessie Beazley Reef, Philippines", 9.043864, 119.819872));
                    placesModelList.add(new TopStreetViewModel("Da Bai Sha, Green Island, Taiwan", 22.638232, 121.490526));
                    placesModelList.add(new TopStreetViewModel("PUE Coral Gardens, Cook Islands", -21.201181, -159.771712));
                    placesModelList.add(new TopStreetViewModel("Fengjie", 31.018497, 109.463989));
                    placesModelList.add(new TopStreetViewModel("Nuclear Outlet, Kenting National Park, Taiwan", 21.930845, 120.745026));
                    placesModelList.add(new TopStreetViewModel("Cheeca Rocks, Florida Keys, USA", 24.904503, -80.616652));
                    placesModelList.add(new TopStreetViewModel("Lizard Island, Great Barrier Reef", -14.685821, 145.442162));
                    placesModelList.add(new TopStreetViewModel("Hong Chai, Kenting National Park, Taiwan", 21.953572, 120.710359));
                    placesModelList.add(new TopStreetViewModel("Freeport, Grand Bahama", 26.488173, -78.657134));
                    placesModelList.add(new TopStreetViewModel("São Francisco River", -9.1920377, -38.2939128));
                    placesModelList.add(new TopStreetViewModel("Champion Island East, Galápagos", -1.238894, -90.384239));
                    placesModelList.add(new TopStreetViewModel("Amur River", 52.6094914, 139.6296251));
                    placesModelList.add(new TopStreetViewModel("Nile River", 25.6978888, 32.6360721));
                    placesModelList.add(new TopStreetViewModel("Yukon River", 63.5810451, -149.6608154));
                    placesModelList.add(new TopStreetViewModel("Paraná Miní River", 41.890210, 12.492231));
                    placesModelList.add(new TopStreetViewModel("Mekong River", 16.342651, 103.081023));
                    placesModelList.add(new TopStreetViewModel("Yangtze River", 30.514649, 112.868837));
                    placesModelList.add(new TopStreetViewModel("Ob River", 61.2359639, 73.1181049));
                    placesModelList.add(new TopStreetViewModel("Madeira River", -5.584739, -61.0213124));
                    placesModelList.add(new TopStreetViewModel("Murray River", -36.0415361, 146.3584699));
                    placesModelList.add(new TopStreetViewModel("Yellow River Delta", 43.152714, -91.513799));
                    placesModelList.add(new TopStreetViewModel("Mississippi River", 45.5407679, -94.1514634));
                    placesModelList.add(new TopStreetViewModel("Tocantins River", -8.9827251, -48.1777573));
                    placesModelList.add(new TopStreetViewModel("Yenisei River", 53.7304248, 91.4961391));
                    placesModelList.add(new TopStreetViewModel("Volga River", 51.934396, 41.75655));
                    break;
                case "Mountain":
                    tvTitle.setText(R.string.txt_mountains);
                    placesModelList.add(new TopStreetViewModel("Mount Everest", 27.994511, 86.828381));
                    placesModelList.add(new TopStreetViewModel("K2 Mount", 35.7412835, 76.5114958));
                    placesModelList.add(new TopStreetViewModel("Kanchenjunga", 27.7024914, 88.147535));
                    placesModelList.add(new TopStreetViewModel("Nanga Parbat", 35.3333222, 74.5810094));
                    placesModelList.add(new TopStreetViewModel("Kilimanjaro", -3.0674314, 37.3556185));
                    placesModelList.add(new TopStreetViewModel("Table Mountain", -33.9581394, 18.4056875));
                    placesModelList.add(new TopStreetViewModel("Matterhron", 45.9917552, 7.7102036));
                    placesModelList.add(new TopStreetViewModel("Mount Hakkoda", 40.669528, 140.880223));
                    placesModelList.add(new TopStreetViewModel("Chimborazo", -1.4729278, -78.8385925));
                    placesModelList.add(new TopStreetViewModel("Mont Blanc", 45.833611, 6.865));
                    placesModelList.add(new TopStreetViewModel("Mount Iwaki", 40.65594, 140.303047));
                    placesModelList.add(new TopStreetViewModel("Aconcagua", -32.653179, -70.010864));
                    placesModelList.add(new TopStreetViewModel("Mount Kashima Yarigatake", 36.623194, 137.746316));
                    placesModelList.add(new TopStreetViewModel("Mount Akagi", 36.560392, 139.193277));
                    placesModelList.add(new TopStreetViewModel("Mount Notori", 35.621388, 138.236694));
                    placesModelList.add(new TopStreetViewModel("Cho Oyu", 28.0999996, 86.6499974));
                    placesModelList.add(new TopStreetViewModel("Mount Karasawa", 36.295057, 137.664726));
                    placesModelList.add(new TopStreetViewModel("Hotaka Mountains", 36.294379, 137.664148));
                    placesModelList.add(new TopStreetViewModel("Mount Aino", 35.648726, 138.228499));
                    placesModelList.add(new TopStreetViewModel("Mount Kurikoma", 38.959434, 140.792436));
                    placesModelList.add(new TopStreetViewModel("Makalu", 27.8856424, 87.0915968));
                    placesModelList.add(new TopStreetViewModel("Mount Takao", 35.624604, 139.243077));
                    placesModelList.add(new TopStreetViewModel("Tanzawa Mountains", 35.47047, 139.163379));
                    placesModelList.add(new TopStreetViewModel("Mount Aka", 35.971305, 138.370344));
                    placesModelList.add(new TopStreetViewModel("Mount Chuo", 27.07321, 142.218466));
                    placesModelList.add(new TopStreetViewModel("Mount Haruna", 36.476889, 138.878258));
                    placesModelList.add(new TopStreetViewModel("Mount Tsukuba", 36.226302, 140.100818));
                    placesModelList.add(new TopStreetViewModel("Mount Yarigatake", 36.342066, 137.64765));
                    break;
                case "Forest":
                    tvTitle.setText(R.string.txt_jungle_safaris);
                    placesModelList.add(new TopStreetViewModel("Mountain Zebra National Park", -32.102187,25.417349));
                    placesModelList.add(new TopStreetViewModel("Black Forest", 47.8445341, 7.9329069));
                    placesModelList.add(new TopStreetViewModel("The Tongass National Forest", 55.34975, -131.606925));
                    placesModelList.add(new TopStreetViewModel("Sundarbans", 21.949727, 89.18333));
                    placesModelList.add(new TopStreetViewModel("Biosphere Reserve", 19.850951, -87.639343));
                    placesModelList.add(new TopStreetViewModel("Loosehanger", 50.950576,-1.677872));
                    placesModelList.add(new TopStreetViewModel("Sinharaja Forest Reserve", 6.389722, 80.501389));
                    placesModelList.add(new TopStreetViewModel("Arashiyama Bamboo Grove", 35.017042, 135.671874));
                    placesModelList.add(new TopStreetViewModel("High Corner", 50.89016,-1.718173));
                    placesModelList.add(new TopStreetViewModel("Tsingy de Bemaraha National Park", -18.89767, 44.82981));
                    placesModelList.add(new TopStreetViewModel("Kruger National Park", -23.8935,31.546686));
                    placesModelList.add(new TopStreetViewModel("Great Otway National Park", -38.791218, 143.5419233));
                    placesModelList.add(new TopStreetViewModel("Milkham inclosure", 50.886174,-1.708976));
                    placesModelList.add(new TopStreetViewModel("Mossy Forest", 4.524223, 101.381927));
                    placesModelList.add(new TopStreetViewModel("Humboldt Redwoods State Park", 40.283723, -123.890317));
                    placesModelList.add(new TopStreetViewModel("North Weirs", 50.817399,-1.583032));
                    break;
                case "Desert":
                    tvTitle.setText(R.string.txt_deserts);
                    placesModelList.add(new TopStreetViewModel("Direction Island", -12.0911, 96.885288));
                    placesModelList.add(new TopStreetViewModel("Cocos Lagoon", -12.192331, 96.859568));
                    placesModelList.add(new TopStreetViewModel("Antarctica", -75.25097, -0.07139));
                    placesModelList.add(new TopStreetViewModel("Boulder track", -10.549466, 105.638712));
                    placesModelList.add(new TopStreetViewModel("Sahara Desert", 29.2052452, 25.5435246));
                    placesModelList.add(new TopStreetViewModel("Horsburgh Island", -12.081945, 96.845105));
                    placesModelList.add(new TopStreetViewModel("The Dales", -10.478835, 105.55961));
                    placesModelList.add(new TopStreetViewModel("Arabian LN", 32.66832, -114.66847));
                    placesModelList.add(new TopStreetViewModel("Margaret Knoll Lookout", -10.477045, 105.684215));
                    placesModelList.add(new TopStreetViewModel("Cocos Airport", -12.193344, 96.834912));
                    placesModelList.add(new TopStreetViewModel("Kalahari CBDC ", -27.2815253, 23.0847078));
                    placesModelList.add(new TopStreetViewModel("Martin Point", -10.469337, 105.557799));
                    placesModelList.add(new TopStreetViewModel("Great Basin Desert", 36.191879, -115.279388));
                    placesModelList.add(new TopStreetViewModel("West Island", -12.196077, 96.863094));
                    placesModelList.add(new TopStreetViewModel("The Grotto", -10.42308, 105.701954));
                    placesModelList.add(new TopStreetViewModel("Sonoran Desert", 32.6286125, -116.0819473));
                    placesModelList.add(new TopStreetViewModel("Golf Course Lookout", -10.423823, 105.698357));
                    placesModelList.add(new TopStreetViewModel("Thar Desert", 26.47167, 74.5670213));
                    placesModelList.add(new TopStreetViewModel("Winifred Beach", -10.496783, 105.546169));
                    placesModelList.add(new TopStreetViewModel("Liwa Desert Oasis", 23.067666, 53.788053));
                    placesModelList.add(new TopStreetViewModel("The Blowholes", -10.514544, 105.622572));
                    placesModelList.add(new TopStreetViewModel("Liwa Desert - South", 23.148055, 53.732017));
                    placesModelList.add(new TopStreetViewModel("Lily Beach", -10.466893, 105.711355));
                    placesModelList.add(new TopStreetViewModel("Liwa Desert - South West", 23.068008, 53.782982));
                    placesModelList.add(new TopStreetViewModel("Merrial Beach Track", -10.474312, 105.559443));
                    placesModelList.add(new TopStreetViewModel("Greta Beach", -10.50195, 105.674651));
                    placesModelList.add(new TopStreetViewModel("Dolly Beach", -10.520641, 105.675738));
                    placesModelList.add(new TopStreetViewModel("The Dales", -10.483353, 105.554388));
                    placesModelList.add(new TopStreetViewModel("Ethel Beach", -10.463977, 105.708019));
                    placesModelList.add(new TopStreetViewModel("West White Beach", -10.460865, 105.579462));
                    break;
                case "AirPorts":
                    tvTitle.setText(R.string.txt_air_ports);
                    placesModelList.add(new TopStreetViewModel("O'Hare International Airport", 41.9741625, -87.9073214));
                    placesModelList.add(new TopStreetViewModel("Tokyo Haneda International Airport", 35.5506372, 139.7888255));
                    placesModelList.add(new TopStreetViewModel("Hong Kong International Airport", 22.308889, 113.914722));
                    placesModelList.add(new TopStreetViewModel("Los Angeles International Airport", 33.9428607, -118.4103077));
                    placesModelList.add(new TopStreetViewModel("Shanghai Pudong International Airport", 31.1443439, 121.808273));
                    placesModelList.add(new TopStreetViewModel("Beijing Capital International Airport", 40.0798573, 116.6031121));
                    placesModelList.add(new TopStreetViewModel("Dallas/Fort Worth International Airport", 32.8982616, -97.0403608));
                    placesModelList.add(new TopStreetViewModel("Seattle-Tacoma International Airport", 47.4422254, -122.30016));
                    placesModelList.add(new TopStreetViewModel("Chhatrapati Shivaji International Airport", 19.0979433, 72.8746061));
                    placesModelList.add(new TopStreetViewModel("Amsterdam Airport Schiphol", 52.3083769, 4.7656394));
                    placesModelList.add(new TopStreetViewModel("Chengdu Shuangliu International Airport", 30.5794432, 103.9541533));
                    placesModelList.add(new TopStreetViewModel("Frankfurt Airport", 50.0452679, 8.5584559));
                    placesModelList.add(new TopStreetViewModel("Istanbul Atatürk Airport", 40.9825775, 28.817171));
                    placesModelList.add(new TopStreetViewModel("McCarran International Airport", 36.082, -115.139831));
                    placesModelList.add(new TopStreetViewModel("Guangzhou Baiyun International Airport", 23.3959079, 113.3079699));
                    placesModelList.add(new TopStreetViewModel("Charles de Gaulle Airport", 49.0155117, 2.5454583));
                    placesModelList.add(new TopStreetViewModel("Madrid Barajas Airport", 40.4910945, -3.5925065));
                    placesModelList.add(new TopStreetViewModel("John F. Kennedy International Airport", 40.6476861, -73.7759042));
                    placesModelList.add(new TopStreetViewModel("Incheon International Airport", 37.448827, 126.451403));
                    placesModelList.add(new TopStreetViewModel("Singapore Changi Airport", 1.3580556, 103.9105556));
                    placesModelList.add(new TopStreetViewModel("Kuala Lumpur International Airport", 2.7476377, 101.7133566));
                    placesModelList.add(new TopStreetViewModel("Dubai International Airport", 25.2482833, 55.3614967));
                    placesModelList.add(new TopStreetViewModel("Denver International Airport", 39.8560936, -104.6737717));
                    placesModelList.add(new TopStreetViewModel("San Francisco International Airport", 37.6239079, -122.3815924));
                    placesModelList.add(new TopStreetViewModel("Hartsfield–Jackson Atlanta International Airport", 33.6407334, -84.4277319));
                    placesModelList.add(new TopStreetViewModel("Suvarnabhumi Airport", 13.6899991, 100.7501124));
                    placesModelList.add(new TopStreetViewModel("Soekarno–Hatta International Airport", -6.1186197, 106.6652698));
                    placesModelList.add(new TopStreetViewModel("Changi Airport Singapore", 1.3580556, 103.9105556));
                    placesModelList.add(new TopStreetViewModel("Indira Gandhi International Airport", 28.5561624, 77.0999578));
                    placesModelList.add(new TopStreetViewModel("Heathrow Airport", 51.4699514, -0.4497753));
                    break;
                case "RailwayStations":
                    tvTitle.setText(R.string.txt_railway_stations);
                    placesModelList.add(new TopStreetViewModel("Amsterdam Centraal", 52.3791283, 4.900272));
                    placesModelList.add(new TopStreetViewModel("Gare de Lyon", 48.8443046, 2.3743773));
                    placesModelList.add(new TopStreetViewModel("Stuttgart Hauptbahnhof", 48.784171, 9.1789213));
                    placesModelList.add(new TopStreetViewModel("London Victoria", 51.4949103, -0.1432954));
                    placesModelList.add(new TopStreetViewModel("Köln Hauptbahnhof", 50.9430074, 6.9588518));
                    placesModelList.add(new TopStreetViewModel("Helsingin Päärautatieasema", 60.1718729, 24.9414217));
                    placesModelList.add(new TopStreetViewModel("Torino Porta Nuova", 45.0611946, 7.6780714));
                    placesModelList.add(new TopStreetViewModel("Utrecht Centraal", 52.0890738, 5.1105524));
                    placesModelList.add(new TopStreetViewModel("London Liverpool Street", 51.5187516, -0.0814374));
                    placesModelList.add(new TopStreetViewModel("Nürnberg Hauptbahnhof", 49.4454137, 11.0836134));
                    placesModelList.add(new TopStreetViewModel("Berlin Hauptbahnhof", 52.5250839, 13.369402));
                    placesModelList.add(new TopStreetViewModel("Firenze Santa Maria Novella", 43.7760037, 11.2480901));
                    placesModelList.add(new TopStreetViewModel("Haussmann-Saint-Lazare", 48.8958514, 2.3738871));
                    placesModelList.add(new TopStreetViewModel("Milano Centrale", 45.4892494, 9.2081271));
                    placesModelList.add(new TopStreetViewModel("London Euston", 51.52814, -0.1339));
                    placesModelList.add(new TopStreetViewModel("Barcelona Sants", 41.3790174, 2.1422508));
                    placesModelList.add(new TopStreetViewModel("Venezia Santa Lucia", 45.441162, 12.3208789));
                    placesModelList.add(new TopStreetViewModel("München Hauptbahnhof", 48.139922, 11.5591378));
                    placesModelList.add(new TopStreetViewModel("Glasgow Central", 55.8598517, -4.2575043));
                    placesModelList.add(new TopStreetViewModel("Birmingham New Street", 52.4778893, -1.8989573));
                    placesModelList.add(new TopStreetViewModel("München-Pasing", 48.1500343, 11.4621345));
                    placesModelList.add(new TopStreetViewModel("Copenhagen Central Station", 55.6727611, 12.5649245));
                    placesModelList.add(new TopStreetViewModel("Zürich Hauptbahnhof", 47.3781203, 8.5395607));
                    placesModelList.add(new TopStreetViewModel("Berlin-Lichtenberg", 52.5101023, 13.4961636));
                    placesModelList.add(new TopStreetViewModel("London Paddington", 51.5163429, -0.1767959));
                    placesModelList.add(new TopStreetViewModel("Berlin Ostbahnhof", 52.5250839, 13.369402));
                    placesModelList.add(new TopStreetViewModel("London St Pancras", 51.5339464, -0.1263803));
                    placesModelList.add(new TopStreetViewModel("Roma Termini", 41.9008904, 12.5014725));
                    placesModelList.add(new TopStreetViewModel("Hamburg-Altona", 53.5534, 9.9349617));
                    placesModelList.add(new TopStreetViewModel("Frankfurt (Main) Hauptbahnhof", 50.107065, 8.6623369));
                    placesModelList.add(new TopStreetViewModel("Lausanne", 46.516407, 6.6286659));
                    placesModelList.add(new TopStreetViewModel("Gare de la Part-Dieu", 45.760105, 4.8609652));
                    placesModelList.add(new TopStreetViewModel("Hamburg Hauptbahnhof", 53.5528457, 10.0066839));
                    placesModelList.add(new TopStreetViewModel("Gare de l'Est", 48.8776325, 2.3609696));
                    placesModelList.add(new TopStreetViewModel("Gare de Juvisy", 48.7652647, 2.410684));
                    placesModelList.add(new TopStreetViewModel("Gare de Châtelet - Les Halles", 48.861893, 2.347));
                    placesModelList.add(new TopStreetViewModel("Milano Cadorna", 45.4685944, 9.1751245));
                    placesModelList.add(new TopStreetViewModel("London King's Cross", 51.5316014, -0.1242503));
                    placesModelList.add(new TopStreetViewModel("Gare du Nord", 48.8804161, 2.3551932));
                    placesModelList.add(new TopStreetViewModel("Winterthur", 47.500005, 8.7232741));
                    break;
                case "ExploringWorld":
                    tvTitle.setText(R.string.txt_exploring_world);
                    placesModelList.add(new TopStreetViewModel("Main Street", -26.2074744, 28.0363871));
                    placesModelList.add(new TopStreetViewModel("Mount Zebra National Park, Salpeterkop trail", -32.1101556, 25.4402601));
                    placesModelList.add(new TopStreetViewModel("Andrew Kerr", 22.3368633, 31.6256717));
                    placesModelList.add(new TopStreetViewModel("Abu Simbel Temples", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetViewModel("Wild Coast Meander", -32.4443146, 28.6712164));
                    placesModelList.add(new TopStreetViewModel("Diagonal Street", -26.2055954, 28.0355892));
                    placesModelList.add(new TopStreetViewModel("Mount Zebra National Park, Cheetah Tracking Wilder", -32.102144, 25.4173855));
                    placesModelList.add(new TopStreetViewModel("Gamkaberg Nature Reserve, Ou Kraal Trail", -33.7307424, 21.9131958));
                    placesModelList.add(new TopStreetViewModel("Daniel SZYSZ", 22.3369639, 31.6256563));
                    placesModelList.add(new TopStreetViewModel("Anysberg Nature Reserve, Land se Kloof", -33.5048112, 20.576778));
                    placesModelList.add(new TopStreetViewModel("Tyrwhitt Avenue", -26.1450141, 28.0423329));
                    placesModelList.add(new TopStreetViewModel("Gideon Scheepers Monument", -32.2309201, 24.5180615));
                    placesModelList.add(new TopStreetViewModel("Woongyoung Park", 22.3373297, 31.6257865));
                    placesModelList.add(new TopStreetViewModel("Levite Chaumba", -17.8311917, 31.040665));
                    placesModelList.add(new TopStreetViewModel("Addo Elephant Main Rest Camp, PPC Trail", -33.4429831, 25.7516306));
                    placesModelList.add(new TopStreetViewModel("Anysberg Self Catering Cottages", -33.4657104, 20.5871439));
                    placesModelList.add(new TopStreetViewModel("Hole in the Wall Hiking Trail", -32.0400328, 29.1087185));
                    placesModelList.add(new TopStreetViewModel("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetViewModel("Kerk Street", -26.2024091, 28.0427613));
                    placesModelList.add(new TopStreetViewModel("Eerstefontein Trail", -32.274515, 24.5163145));
                    placesModelList.add(new TopStreetViewModel("Treetop Walk", -22.2007755, 29.3542062));
                    placesModelList.add(new TopStreetViewModel("Viviane Fontes", 22.3369577, 31.6259754));
                    placesModelList.add(new TopStreetViewModel("Addo Elephant National Park, Doringkloof Trail", -33.3480677, 25.733466));
                    placesModelList.add(new TopStreetViewModel("The Crag Lizard Trail", -32.2647392, 24.4866573));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Acacia Trail", -34.0728894, 20.4497461));
                    placesModelList.add(new TopStreetViewModel("Cederberg Wilderness Area", -32.543646, 19.32927159999997));
                    placesModelList.add(new TopStreetViewModel("Confluence Lookout Point", -22.2006143, 29.3705883));
                    placesModelList.add(new TopStreetViewModel("Noord Street", -26.1982695, 28.0444091));
                    placesModelList.add(new TopStreetViewModel("Mtentu Viewpoint", -31.2386119, 30.0325065));
                    placesModelList.add(new TopStreetViewModel("Garden Route National Park: Wilderness", -33.9674297, 22.596823900000004));
                    placesModelList.add(new TopStreetViewModel("Tan Mas", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetViewModel("Tamboti trail", -25.2946616, 27.1690196));
                    placesModelList.add(new TopStreetViewModel("Mapungubwe Hill", -22.2138856, 29.386949));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Termite Trail", -34.072885, 20.4483871));
                    placesModelList.add(new TopStreetViewModel("Nelson Mandela Capture site ", -29.4681918, 30.17038260000004));
                    placesModelList.add(new TopStreetViewModel("Madikwe River Lodge", -24.6840063, 26.423116));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Aloe Trail", -34.0798594, 20.452325));
                    placesModelList.add(new TopStreetViewModel("Madikwe Game Reserve, Tshukudu Dam", -24.727859, 26.366354));
                    placesModelList.add(new TopStreetViewModel("The Cradle of Humankind Visitor Centre Maropeng", -25.966763, 27.6626412));
                    placesModelList.add(new TopStreetViewModel("Fossil Trail", -32.3332878, 22.4978028));
                    placesModelList.add(new TopStreetViewModel("Wally's Cave", -33.9375679, 18.3897086));
                    placesModelList.add(new TopStreetViewModel("Strandloper and Mkhambathi Falls", -31.2743474, 30.023418));
                    placesModelList.add(new TopStreetViewModel("Modikela Safari Wilderness Trail", -24.4142572, 27.5386582));
                    placesModelList.add(new TopStreetViewModel("Jordi Vallverdu ", 22.3368613, 31.6257746));
                    placesModelList.add(new TopStreetViewModel("Royal National park Drakenberg", -28.7525581, 28.89430470000002));
                    placesModelList.add(new TopStreetViewModel("kruger national park ", -23.620009, 31.650487));
                    placesModelList.add(new TopStreetViewModel("Twee Rivieren", -26.4734049, 20.6126321));
                    placesModelList.add(new TopStreetViewModel("Bill Harrop's Original Balloon Safaris", -25.8139098, 27.7384832));
                    placesModelList.add(new TopStreetViewModel("Klipspringer Trail", -28.5889726, 20.3251937));
                    placesModelList.add(new TopStreetViewModel("Augrabies Falls", -28.5917509, 20.3405535));
                    placesModelList.add(new TopStreetViewModel("Pipe Track", -33.9478324, 18.394588));
                    placesModelList.add(new TopStreetViewModel("Hettema Trail - Sendelingsdrif", -28.1337012, 16.9107528));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Grysbok Circle", -33.9742738, 20.8207797));
                    placesModelList.add(new TopStreetViewModel("Golden Gate Highlands National Park", -28.5031212, 28.6203292));
                    placesModelList.add(new TopStreetViewModel("Madikwe Game Reserve, Safari drive", -24.6873997, 26.4192038));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Fonteintjiesbos ", -33.9818368, 20.8134073));
                    placesModelList.add(new TopStreetViewModel("Lion's Head Loop", -33.9284663, 18.3946677));
                    placesModelList.add(new TopStreetViewModel("Sterkfontein Caves", -26.0164956, 27.734291));
                    placesModelList.add(new TopStreetViewModel("Bakubung back Valley wilderness trail", -25.3252719, 27.0206081));
                    placesModelList.add(new TopStreetViewModel("Mokala National Park Bushwalk", -29.1599739, 24.3182358));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Bushbuck Trail", -33.9844697, 20.823392));
                    placesModelList.add(new TopStreetViewModel("Otter Trail", -34.021006, 23.8800573));
                    placesModelList.add(new TopStreetViewModel("Sun City", -25.3373742, 27.0870538));
                    placesModelList.add(new TopStreetViewModel("Sea Point Promenade", -33.8994868, 18.4037492));
                    placesModelList.add(new TopStreetViewModel("Heaviside Hiking Trail", -30.7236629, 17.5141112));
                    placesModelList.add(new TopStreetViewModel("V&A Waterfront", -33.9044883, 18.4207404));
                    placesModelList.add(new TopStreetViewModel("Lesedi Cultural Village", -25.8379163, 27.8810604));
                    placesModelList.add(new TopStreetViewModel("Skilpad Trail", -30.1661696, 17.7903668));
                    placesModelList.add(new TopStreetViewModel("Elandsberg Trail", -32.1942655, 19.9485041));
                    placesModelList.add(new TopStreetViewModel("Southern African Large Telescope", -32.3758799, 20.8114466));
                    placesModelList.add(new TopStreetViewModel("Pointer’s Hiking Trail", -32.341016, 22.4957517));
                    placesModelList.add(new TopStreetViewModel("Mokala National Park, Rock Engraving Trail", -29.1045866, 24.2638248));
                    placesModelList.add(new TopStreetViewModel("Korhan Trail", -30.1643018, 17.8041342));
                    placesModelList.add(new TopStreetViewModel("Agterstefontein Trail", -32.291762, 24.4852518));
                    placesModelList.add(new TopStreetViewModel("Rest Camp Trail", -32.3331512, 22.4930986));
                    placesModelList.add(new TopStreetViewModel("Helskloof Hiking Trail", -28.3187658, 16.9807969));
                    placesModelList.add(new TopStreetViewModel("Cape Town, Western Cape", -33.901501, 18.4213349));
                    placesModelList.add(new TopStreetViewModel("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetViewModel("Bossie Trail", -32.3345355, 22.4965986));
                    placesModelList.add(new TopStreetViewModel("Chapman's Peak Lookout", -34.0884531, 18.359611));
                    placesModelList.add(new TopStreetViewModel("Marsa Shagra Village ", 25.2458526, 34.7945753));
                    placesModelList.add(new TopStreetViewModel("Doreen Falls", -28.9623088, 29.2029319));
                    placesModelList.add(new TopStreetViewModel("Hole in the Wall Hiking", -32.0349255, 29.1122834));
                    placesModelList.add(new TopStreetViewModel("Table mountain National park", -33.9731969, 18.3927267));
                    break;
                    case "Exploring Africa":
                    tvTitle.setText(String.valueOf("Exploring Africa"));
                    placesModelList.add(new TopStreetViewModel("Hole in the Wall Hiking", -32.0349255, 29.1122834));
                    placesModelList.add(new TopStreetViewModel("Strandloper and Mkhambathi Falls", -31.2743474, 30.023418));
                    placesModelList.add(new TopStreetViewModel("Lesedi Cultural Village", -25.8379163, 27.8810604));
                    placesModelList.add(new TopStreetViewModel("Doreen Falls", -28.9623088, 29.2029319));
                    placesModelList.add(new TopStreetViewModel("Agterstefontein Trail", -32.291762, 24.4852518));
                    placesModelList.add(new TopStreetViewModel("Sun City", -25.3373742, 27.0870538));
                    placesModelList.add(new TopStreetViewModel("Golden Gate Highlands National Park", -28.5031212, 28.6203292));
                    placesModelList.add(new TopStreetViewModel("Modikela Safari Wilderness Trail", -24.4142572, 27.5386582));
                    placesModelList.add(new TopStreetViewModel("Madikwe Game Reserve, Safari drive", -24.6873997, 26.4192038));
                    placesModelList.add(new TopStreetViewModel("Madikwe Game Reserve, Tshukudu Dam", -24.727859, 26.366354));
                    placesModelList.add(new TopStreetViewModel("Mtentu Viewpoint", -31.2386119, 30.0325065));
                    placesModelList.add(new TopStreetViewModel("The Crag Lizard Trail", -32.2647392, 24.4866573));
                    placesModelList.add(new TopStreetViewModel("Eerstefontein Trail", -32.274515, 24.5163145));
                    placesModelList.add(new TopStreetViewModel("Tyrwhitt Avenue", -26.1450141, 28.0423329));
                    placesModelList.add(new TopStreetViewModel("Main Street", -26.2074744, 28.0363871));
                    placesModelList.add(new TopStreetViewModel("Mount Zebra National Park, Salpeterkop trail", -32.1101556, 25.4402601));
                    placesModelList.add(new TopStreetViewModel("Mount Zebra National Park, Cheetah Tracking Wilder", -32.102144, 25.4173855));
                    placesModelList.add(new TopStreetViewModel("Gideon Scheepers Monument", -32.2309201, 24.5180615));
                    placesModelList.add(new TopStreetViewModel("Hole in the Wall Hiking Trail", -32.0400328, 29.1087185));
                    placesModelList.add(new TopStreetViewModel("Treetop Walk", -22.2007755, 29.3542062));
                    placesModelList.add(new TopStreetViewModel("Confluence Lookout Point", -22.2006143, 29.3705883));
                    placesModelList.add(new TopStreetViewModel("Mapungubwe Hill", -22.2138856, 29.386949));
                    placesModelList.add(new TopStreetViewModel("Madikwe River Lodge", -24.6840063, 26.423116));
                    placesModelList.add(new TopStreetViewModel("Bakubung back Valley wilderness trail", -25.3252719, 27.0206081));
                    placesModelList.add(new TopStreetViewModel("Bill Harrop's Original Balloon Safaris", -25.8139098, 27.7384832));
                    placesModelList.add(new TopStreetViewModel("The Cradle of Humankind Visitor Centre Maropeng", -25.966763, 27.6626412));
                    placesModelList.add(new TopStreetViewModel("Sterkfontein Caves", -26.0164956, 27.734291));
                    placesModelList.add(new TopStreetViewModel("Elandsberg Trail", -32.1942655, 19.9485041));
                    placesModelList.add(new TopStreetViewModel("Bossie Trail", -32.3345355, 22.4965986));
                    placesModelList.add(new TopStreetViewModel("Rest Camp Trail", -32.3331512, 22.4930986));
                    placesModelList.add(new TopStreetViewModel("Fossil Trail", -32.3332878, 22.4978028));
                    placesModelList.add(new TopStreetViewModel("Pointer’s Hiking Trail", -32.341016, 22.4957517));
                    placesModelList.add(new TopStreetViewModel("Mokala National Park, Rock Engraving Trail", -29.1045866, 24.2638248));
                    placesModelList.add(new TopStreetViewModel("Mokala National Park Bushwalk", -29.1599739, 24.3182358));
                    placesModelList.add(new TopStreetViewModel("Southern African Large Telescope", -32.3758799, 20.8114466));
                    placesModelList.add(new TopStreetViewModel("Twee Rivieren", -26.4734049, 20.6126321));
                    placesModelList.add(new TopStreetViewModel("Klipspringer Trail", -28.5889726, 20.3251937));
                    placesModelList.add(new TopStreetViewModel("Augrabies Falls", -28.5917509, 20.3405535));
                    placesModelList.add(new TopStreetViewModel("Hettema Trail - Sendelingsdrif", -28.1337012, 16.9107528));
                    placesModelList.add(new TopStreetViewModel("Helskloof Hiking Trail", -28.3187658, 16.9807969));
                    placesModelList.add(new TopStreetViewModel("Korhan Trail", -30.1643018, 17.8041342));
                    placesModelList.add(new TopStreetViewModel("Skilpad Trail", -30.1661696, 17.7903668));
                    placesModelList.add(new TopStreetViewModel("Heaviside Hiking Trail", -30.7236629, 17.5141112));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Bushbuck Trail", -33.9844697, 20.823392));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Fonteintjiesbos ", -33.9818368, 20.8134073));
                    placesModelList.add(new TopStreetViewModel("Grootvadersbosch Nature Reserve, Grysbok Circle", -33.9742738, 20.8207797));
                    placesModelList.add(new TopStreetViewModel("kruger national park ", -23.620009, 31.650487));
                    placesModelList.add(new TopStreetViewModel("Royal National park Drakenberg", -28.7525581, 28.89430470000002));
                    placesModelList.add(new TopStreetViewModel("Table mountain National park", -33.9731969, 18.3927267));
                    placesModelList.add(new TopStreetViewModel("Nelson Mandela Capture site ", -29.4681918, 30.17038260000004));
                    placesModelList.add(new TopStreetViewModel("Garden Route National Park: Wilderness", -33.9674297, 22.596823900000004));
                    placesModelList.add(new TopStreetViewModel(" Cederberg Wilderness Area", -32.543646, 19.32927159999997));
                    placesModelList.add(new TopStreetViewModel("Benos Angaka Bonyoma", 0.2136714, 16.9848245));
                    placesModelList.add(new TopStreetViewModel("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetViewModel(" Levite Chaumba", -17.8311917, 31.040665));
                    placesModelList.add(new TopStreetViewModel("Daniel SZYSZ", 22.3369639, 31.6256563));
                    placesModelList.add(new TopStreetViewModel("Andrew Kerr", 22.3368633, 31.6256717));
                    placesModelList.add(new TopStreetViewModel("Abu Simbel Temples", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetViewModel(" Woongyoung Park", 22.3373297, 31.6257865));
                    placesModelList.add(new TopStreetViewModel("Viviane Fontes", 22.3369577, 31.6259754));
                    placesModelList.add(new TopStreetViewModel(" Tan Mas", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetViewModel("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetViewModel("Marsa Shagra Village ", 25.2458526, 34.7945753));
                    placesModelList.add(new TopStreetViewModel("Jordi Vallverdu ", 22.3368613, 31.6257746));
                    placesModelList.add(new TopStreetViewModel("Sea Point Promenade", -33.8994868, 18.4037492));
                    placesModelList.add(new TopStreetViewModel("Chapman's Peak Lookout", -34.0884531, 18.359611));
                    placesModelList.add(new TopStreetViewModel("Cape Town, Western Cape", -33.901501, 18.4213349));
                    placesModelList.add(new TopStreetViewModel("V&A Waterfront", -33.9044883, 18.4207404));
                    placesModelList.add(new TopStreetViewModel("Lion's Head Loop", -33.9284663, 18.3946677));
                    placesModelList.add(new TopStreetViewModel("Wally's Cave", -33.9375679, 18.3897086));
                    placesModelList.add(new TopStreetViewModel("Pipe Track", -33.9478324, 18.394588));
                    placesModelList.add(new TopStreetViewModel("Otter Trail", -34.021006, 23.8800573));
                    placesModelList.add(new TopStreetViewModel("Tamboti trail", -25.2946616, 27.1690196));
                    placesModelList.add(new TopStreetViewModel("Addo Elephant National Park, Doringkloof Trail", -33.3480677, 25.733466));
                    placesModelList.add(new TopStreetViewModel("Addo Elephant Main Rest Camp, PPC Trail", -33.4429831, 25.7516306));
                    placesModelList.add(new TopStreetViewModel("Wild Coast Meander", -32.4443146, 28.6712164));
                    placesModelList.add(new TopStreetViewModel("Diagonal Street", -26.2055954, 28.0355892));
                    placesModelList.add(new TopStreetViewModel("Kerk Street", -26.2024091, 28.0427613));
                    placesModelList.add(new TopStreetViewModel("Noord Street", -26.1982695, 28.0444091));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Aloe Trail", -34.0798594, 20.452325));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Termite Trail", -34.072885, 20.4483871));
                    placesModelList.add(new TopStreetViewModel("Bontebok National Park, Acacia Trail", -34.0728894, 20.4497461));
                    placesModelList.add(new TopStreetViewModel("Anysberg Self Catering Cottages", -33.4657104, 20.5871439));
                    placesModelList.add(new TopStreetViewModel("Anysberg Nature Reserve, Land se Kloof", -33.5048112, 20.576778));
                    placesModelList.add(new TopStreetViewModel("Gamkaberg Nature Reserve, Ou Kraal Trail", -33.7307424, 21.9131958));
                    break;
                case "Ocean":
                    tvTitle.setText(String.valueOf("Ocean"));
                    placesModelList.add(new TopStreetViewModel("Coral Gardens, Palmyra Atoll, USA",5.885806,-162.060135 ));
                    placesModelList.add(new TopStreetViewModel("Crazy Corals, Palmyra Atoll, USA", 5.885904,-162.12561));
                    placesModelList.add(new TopStreetViewModel("Holei, Palmyra Atoll, USA", 5.866448,-162.067535));
                    placesModelList.add(new TopStreetViewModel("Jurassic Park, Palmyra Atoll, USA",5.858315,-162.101257 ));
                    placesModelList.add(new TopStreetViewModel("Mega Jacks, Palmyra Atoll, USA",5.864789,-162.14032));
                    placesModelList.add(new TopStreetViewModel("Melon headed whales, Palmyra Atoll, USA",5.859013,-162.104696 ));
                    placesModelList.add(new TopStreetViewModel("Penguin Spit, Palmyra Atoll, USA", 5.87155,-162.110352));
                    placesModelList.add(new TopStreetViewModel("Yonaguni Monument, Yonaguni, Okinawa, Japan",24.435919,123.011134 ));
                    placesModelList.add(new TopStreetViewModel("Komodo Islands",-8.737039,119.412259 ));
                    placesModelList.add(new TopStreetViewModel("Nishibama, Aka Island, Okinawa, Japan",26.202737,127.289096 ));
                    placesModelList.add(new TopStreetViewModel("The Channel, Palmyra Atoll, USA",5.872685,-162.111947 ));
                    placesModelList.add(new TopStreetViewModel("Giant Potato, Nagura Bay, Ishigaki, Okinawa, Japan",24.399209,124.117781 ));
                    placesModelList.add(new TopStreetViewModel("Yamada Point, Onna, Okinawa, Japan",26.441502,127.790058 ));
                    placesModelList.add(new TopStreetViewModel("Aharen Beach, Tokashiki, Okinawa, Japan",26.168867,127.343795 ));
                    placesModelList.add(new TopStreetViewModel("Agariushi / Gahi Island, Zamami, Okinawa, Japan",26.21729,127.310158 ));
                    placesModelList.add(new TopStreetViewModel("Taketomi-south Shark Home, Taketomi, Okinawa, Japan",24.284623,124.078206 ));
                    placesModelList.add(new TopStreetViewModel("Milky Way / Submarine, Sekisei Lagoon, Ishigaki, Okinawa, Japan",24.300251,124.08625 ));
                    placesModelList.add(new TopStreetViewModel("Cape Kri, Raja Ampat, Indonesia",-0.556114,130.690997 ));
                    placesModelList.add(new TopStreetViewModel("Tatawa Kecil, Komodo, Indonesia",-8.53004,119.62723 ));
                    placesModelList.add(new TopStreetViewModel("Batu Bolong, Komodo, Indonesia",-8.537229,119.613738));
                    placesModelList.add(new TopStreetViewModel("Timor II, Bunaken National Park, Indonesia",1.618098,124.781914 ));
                    placesModelList.add(new TopStreetViewModel("Leukan III A, Bunaken National Park, Indonesia",1.602118,124.766276 ));
                    placesModelList.add(new TopStreetViewModel("Fukui, Bunaken National Park, Indonesia",1.611651,124.739282 ));
                    placesModelList.add(new TopStreetViewModel("Minke Whales on the Ribbon Reef",-14.865306,145.680527 ));
                    placesModelList.add(new TopStreetViewModel("Shelly Beach, Australia",-33.893774,151.282125 ));
                    placesModelList.add(new TopStreetViewModel("Galápagos Islands, Ecuador",-1.239203,-90.385735 ));
                    placesModelList.add(new TopStreetViewModel("Crystal Bay, Indonesia",-8.715413,115.457015 ));
                    placesModelList.add(new TopStreetViewModel("Devil's Crown, Galapagos",-1.216466,-90.42215 ));
                    placesModelList.add(new TopStreetViewModel("Nudi's Retreat, Indonesia",1.486096,125.24151 ));
                    placesModelList.add(new TopStreetViewModel("Champion Island East, Galápagos",-1.238894,-90.384239 ));
                    placesModelList.add(new TopStreetViewModel("Hong Chai, Kenting National Park, Taiwan",21.953572,120.710359 ));
                    placesModelList.add(new TopStreetViewModel("Nuclear Outlet, Kenting National Park, Taiwan",21.930845,120.745026 ));
                    placesModelList.add(new TopStreetViewModel("Da Bai Sha, Green Island, Taiwan",22.638232,121.490526 ));
                    placesModelList.add(new TopStreetViewModel("Three Rock, Green Island, Taiwan",22.639565,121.490911 ));
                    placesModelList.add(new TopStreetViewModel("Emily's Pinnacles, Bermuda",32.235303,-64.848371 ));
                    placesModelList.add(new TopStreetViewModel("Rarotonga, Cook Islands",-21.203982,-159.837009 ));
                    placesModelList.add(new TopStreetViewModel("Heron Island, Great Barrier Reef",-23.442896,151.906584 ));
                    placesModelList.add(new TopStreetViewModel("Muli Kandu, Maldives",2.927715,73.590414 ));
                    placesModelList.add(new TopStreetViewModel("The Liberty Wreck, Bali",-8.274153,115.592694 ));
                    placesModelList.add(new TopStreetViewModel("Batu Rufus, Indonesia",-0.565927,130.284558 ));
                    placesModelList.add(new TopStreetViewModel("Big Momma, American Samoa",-14.259231,-169.500376 ));
                    placesModelList.add(new TopStreetViewModel("Airport Reef, American Samoa",-14.328972,-170.70172 ));
                    placesModelList.add(new TopStreetViewModel("Nelson Island Outside, Chagos",-5.683498,72.325919 ));
                    placesModelList.add(new TopStreetViewModel("Tafeu Cove, American Samoa",-14.253016,-170.689206 ));
                    placesModelList.add(new TopStreetViewModel("Whale Sharks at Isla Contoy, Mexico",21.479702,-86.632599 ));
                    placesModelList.add(new TopStreetViewModel("Aquarius Reef Base, Florida Keys - United States of America",24.950493,-80.454069 ));
                    placesModelList.add(new TopStreetViewModel("Bleached Coral at Airport Reef, American Samoa - During",-14.328973,-170.70172 ));
                    placesModelList.add(new TopStreetViewModel("Dead Coral at Airport Reef, American Samoa - After",-14.328973,-170.70172 ));
                    placesModelList.add(new TopStreetViewModel("SS Antilla Shipwreck, Aruba",12.601959,-70.058343 ));
                    placesModelList.add(new TopStreetViewModel("Ile Vache Marine, Chagos, British Indian Ocean Territory",-5.427774,71.828873 ));
                    placesModelList.add(new TopStreetViewModel("Mayreau Hot Springs, St Vincent & the Grenadines",12.627667,-61.378149 ));
                    placesModelList.add(new TopStreetViewModel("Cozumel, Mexico",20.338045,-87.027587 ));
                    placesModelList.add(new TopStreetViewModel("Moresby Island Inside, Chagos, British Indian Ocean Territory",-5.243066,71.825168 ));
                    placesModelList.add(new TopStreetViewModel("PUE Coral Gardens, Cook Islands",-21.201181,-159.771712 ));
                    placesModelList.add(new TopStreetViewModel("Cheeca Rocks, Florida Keys, USA",24.904503,-80.616652 ));
                    placesModelList.add(new TopStreetViewModel("Freeport, Grand Bahama",26.488173,-78.657134 ));
                    placesModelList.add(new TopStreetViewModel("Jessie Beazley Reef, Philippines",9.043864,119.819872 ));
                    placesModelList.add(new TopStreetViewModel("Horseshoe Bay, Bermuda",32.244589,-64.822922 ));
                    placesModelList.add(new TopStreetViewModel("Benwood Wreck, Florida Keys, USA",25.052668,-80.332617 ));
                    placesModelList.add(new TopStreetViewModel("Jaco Island, Timor Leste",-8.441477,127.312285 ));
                    placesModelList.add(new TopStreetViewModel("Lizard Island, Great Barrier Reef",-14.685821,145.442162 ));
                    placesModelList.add(new TopStreetViewModel("Great Detached Reef, Great Barrier Reef",-11.737481,143.972687 ));
                    break;
                    case "Tv Studio":
                    tvTitle.setText(String.valueOf("Tv Studio"));
                    placesModelList.add(new TopStreetViewModel("Estudios de Canal 13", -33.427364,-70.627082));
                    placesModelList.add(new TopStreetViewModel("The Late Show with Stephen Colbert - 2", 40.763765,-73.98295));
                    placesModelList.add(new TopStreetViewModel("Telefe Noticias", -34.620483,-58.387732));
                    placesModelList.add(new TopStreetViewModel("Telefe Noticias", -34.62014,-58.387783));
                    placesModelList.add(new TopStreetViewModel("Late Show w/ Stephen Colbert", 40.763741,-73.982866));
                    placesModelList.add(new TopStreetViewModel("Harry Potter's Diagon Alley",51.693249,-0.419633 ));
                    placesModelList.add(new TopStreetViewModel("America TV",-34.584441,-58.437018 ));
                    placesModelList.add(new TopStreetViewModel("The Depository, London, UK", 51.566382,-0.075246));
                    placesModelList.add(new TopStreetViewModel("Ed Sullivan Theater",40.763838,-73.982864 ));
                    placesModelList.add(new TopStreetViewModel("The Ed Sullivan Theater - 2",40.763884,-73.9829 ));
                    placesModelList.add(new TopStreetViewModel("The Late Show With David Letterman - 1",40.76381,-73.982976 ));
                    placesModelList.add(new TopStreetViewModel("Adam Savage's Cave", 37.75387,-122.420658));
                    placesModelList.add(new TopStreetViewModel("Canada AM studio",43.78189,-79.257404));
                    placesModelList.add(new TopStreetViewModel("Tardis",51.492177,-0.193015));
                    placesModelList.add(new TopStreetViewModel("Late Show w/ David Letterman", 40.763851,-73.98288));
                    placesModelList.add(new TopStreetViewModel("The Colbert Report studio - 1",40.767857,-73.990773 ));
                    placesModelList.add(new TopStreetViewModel("The Colbert Report studio - 2", 40.767669,-73.990809));
                    break;
                case "Black History Culture In The Us":
                    tvTitle.setText(String.valueOf("Black History Culture In The Us"));
                placesModelList.add(new TopStreetViewModel("Creative Time", 40.715867,-73.967208));
                placesModelList.add(new TopStreetViewModel("Frederick Douglass National Historic Site",38.863443,-76.985178 ));
                placesModelList.add(new TopStreetViewModel("Dexter Avenue King Memorial Church",32.377731,-86.302505 ));
                placesModelList.add(new TopStreetViewModel("Ebenezer Baptist Church",33.75551,-84.374349 ));
                placesModelList.add(new TopStreetViewModel("16th Street Baptist Church (Birmingham, AL)", 33.516559,-86.814599));
                placesModelList.add(new TopStreetViewModel("Montgomery Greyhound Bus Station",32.374575,-86.308916 ));
                placesModelList.add(new TopStreetViewModel("Little Rock Central High School",34.736786,-92.297827 ));
                placesModelList.add(new TopStreetViewModel("Museum of African American History, Boston Campus",42.359974,-71.065202 ));
                placesModelList.add(new TopStreetViewModel("Edmund Pettus Bridge",32.405518,-87.018526 ));
                placesModelList.add(new TopStreetViewModel("Daisy Bates House",34.721838,-92.288331 ));
                placesModelList.add(new TopStreetViewModel("Brown v. Board of Education",39.037806,-95.676158 ));
                placesModelList.add(new TopStreetViewModel("Maggie L. Walker National Historic Site",37.547751,-77.437686 ));
                placesModelList.add(new TopStreetViewModel("Brown Chapel AME Church",32.412485,-87.016432 ));
                placesModelList.add(new TopStreetViewModel("The King Center",33.754984,-84.373202 ));
            break;
                case "Christmas Island":
                    tvTitle.setText(String.valueOf("Christmas Island"));
                    placesModelList.add(new TopStreetViewModel("Merrial Beach Track",-10.474312,105.559443 ));
                    placesModelList.add(new TopStreetViewModel("Dolly Beach",-10.520641,105.675738 ));
                    placesModelList.add(new TopStreetViewModel("The Dales",-10.483353,105.554388 ));
                    placesModelList.add(new TopStreetViewModel("West White Beach",-10.460865,105.579462 ));
                    placesModelList.add(new TopStreetViewModel("Ethel Beach",-10.463977,105.708019 ));
                    placesModelList.add(new TopStreetViewModel("Greta Beach",-10.50195,105.674651 ));
                    placesModelList.add(new TopStreetViewModel("Lily Beach",-10.466893,105.711355 ));
                    placesModelList.add(new TopStreetViewModel("The Blowholes",-10.514544,105.622572 ));
                    placesModelList.add(new TopStreetViewModel("Winifred Beach",-10.496783,105.546169 ));
                    placesModelList.add(new TopStreetViewModel("Golf Course Lookout",-10.423823,105.698357 ));
                    placesModelList.add(new TopStreetViewModel("The Grotto",-10.42308,105.701954 ));
                    placesModelList.add(new TopStreetViewModel("Martin Point",-10.469337,105.557799 ));
                    placesModelList.add(new TopStreetViewModel("Margaret Knoll Lookout",-10.477045,105.684215 ));
                    placesModelList.add(new TopStreetViewModel("The Dales",-10.478835,105.55961 ));
                    placesModelList.add(new TopStreetViewModel("Boulder track",-10.549466,105.638712 ));
                    break;
                    case "Cocos Keeling Islands":
                        tvTitle.setText(String.valueOf("Cocos Keeling Islands"));
                placesModelList.add(new TopStreetViewModel("Direction Island",-12.0911,96.885288 ));
                placesModelList.add(new TopStreetViewModel("West Island",-12.196077,96.863094 ));
                placesModelList.add(new TopStreetViewModel("Cocos Airport",-12.193344,96.834912 ));
                placesModelList.add(new TopStreetViewModel("Horsburgh Island",-12.081945,96.845105 ));
                placesModelList.add(new TopStreetViewModel("Cocos Lagoon",-12.192331,96.859568 ));
               break;
                case "Australia Highlights":
                    tvTitle.setText(String.valueOf("Australia Highlights"));
                    placesModelList.add(new TopStreetViewModel("Hisense Arena",-37.822779,144.981893 ));
                    placesModelList.add(new TopStreetViewModel("Margaret Court Arena",-37.821508,144.977749 ));
                    placesModelList.add(new TopStreetViewModel("Cape Willoughby, Kangaroo Island",-35.842016,138.133209 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-35.963795,136.654461 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-36.016935,136.856542 ));
                    placesModelList.add(new TopStreetViewModel("Ravine des Casoars Hike, Kangaroo Island",-35.793328,136.582107 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-36.04712,136.756926 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-35.950961,136.676119 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-35.95853,136.656554 ));
                    placesModelList.add(new TopStreetViewModel("Giraffe crossing, Monarto Zoo",-35.102711,139.156459 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo sunbathing, Kangaroo Island",-36.006964,136.861363 ));
                    placesModelList.add(new TopStreetViewModel("Monarto Zoo",-35.093052,139.16632 ));
                    placesModelList.add(new TopStreetViewModel("Kangaroo Island",-36.063356,136.704953 ));
                    placesModelList.add(new TopStreetViewModel("Adelaide Zoo",-34.914127,138.606372 ));
                    placesModelList.add(new TopStreetViewModel("Sydney Opera House",-33.857394,151.215435 ));
                    placesModelList.add(new TopStreetViewModel("Hyams Beach",-35.102298,150.693455 ));
                    placesModelList.add(new TopStreetViewModel("Concert Hall, Sydney Opera House",-33.856965,151.214948 ));
                    placesModelList.add(new TopStreetViewModel("Manly Beach",-33.786165,151.289112 ));
                    placesModelList.add(new TopStreetViewModel("National Gallery of Australia",-35.300799,149.136104 ));
                    placesModelList.add(new TopStreetViewModel("Kosciuszko National Park, Mount Kosciuszko summit",-36.455828,148.263492 ));
                    placesModelList.add(new TopStreetViewModel("Shelly Beach",-33.800715,151.298797 ));
                    placesModelList.add(new TopStreetViewModel("Joan Sutherland Theatre, Sydney Opera House",-33.857016,151.21547 ));
                    placesModelList.add(new TopStreetViewModel("Luna Park",-33.849198,151.210728 ));
                    placesModelList.add(new TopStreetViewModel("Barangaroo park",-33.85887,151.202199 ));
                    placesModelList.add(new TopStreetViewModel("Bronte Beach",-33.904869,151.268291 ));
                    placesModelList.add(new TopStreetViewModel("Invurt - Melbourne Street Art - Artists Lane",-37.853993,144.992311 ));
                    placesModelList.add(new TopStreetViewModel("Form (PUBLIC - Art in the City) - Wilson Parking Lot off of Murray Street, Perth",-31.95092,115.85443 ));
                    placesModelList.add(new TopStreetViewModel("Art Gallery of New South Wales",-33.869377,151.217646 ));
                    placesModelList.add(new TopStreetViewModel("Blue Mountains National Park, Echo Point lookout (Three Sisters)",-33.73285,150.312039 ));
                    placesModelList.add(new TopStreetViewModel("Ku-ring-gai Chase National Park, West Head lookout",-33.579289,151.309294 ));
                    placesModelList.add(new TopStreetViewModel("Royal National Park, Wattamolla Beach",-34.137256,151.117934 ));
                    placesModelList.add(new TopStreetViewModel("Drama Theatre, Sydney Opera House",-33.856658,151.215085 ));
                    placesModelList.add(new TopStreetViewModel("Sydney Harbour National Park, Fort Denison",-33.855069,151.225416 ));
                    placesModelList.add(new TopStreetViewModel("Hanging Rock",-37.329771,144.59378 ));
                    placesModelList.add(new TopStreetViewModel("Mungo National Park, Walls of China",-33.741578,143.12705 ));
                    placesModelList.add(new TopStreetViewModel("Cape Byron State Conservation Area, Cape Byron lighthouse",-28.638788,153.636479 ));
                    placesModelList.add(new TopStreetViewModel("Dorrigo National Park, Skywalk lookout",-30.363612,152.729783 ));
                    placesModelList.add(new TopStreetViewModel("Barrington Tops National Park, Barrington trail",-31.949395,151.443958 ));
                    placesModelList.add(new TopStreetViewModel("Ben Boyd National Park, Green Cape lookout",-37.261638,150.04943 ));
                    placesModelList.add(new TopStreetViewModel("Warrumbungle National Park, Breadknife and Grand High Tops walk",-31.332615,148.995792 ));
                    placesModelList.add(new TopStreetViewModel("Montague Island Nature Reserve, Montague Island walking track",-36.251293,150.227668));
                    placesModelList.add(new TopStreetViewModel("Gundabooka National Park, Valley of the Eagles walk",-30.593884,145.686808 ));
                    placesModelList.add(new TopStreetViewModel("Muttonbird Island Nature Reserve",-30.304711,153.153442 ));
                    placesModelList.add(new TopStreetViewModel("Sydney Harbour National Park, Bradleys Head",-33.853049,151.245498 ));
                    placesModelList.add(new TopStreetViewModel("Bouddi National Park, Bouddi coastal walk",-33.529776,151.374955 ));
                    placesModelList.add(new TopStreetViewModel("Melbourne Zoo",-37.785221,144.953041 ));
                    placesModelList.add(new TopStreetViewModel("Werribee Zoo",-37.922403,144.662957 ));
                    placesModelList.add(new TopStreetViewModel("Croajingolong",-37.782058,149.313572 ));
                    placesModelList.add(new TopStreetViewModel("Great Ocean Walk",-38.664017,143.104197 ));
                    placesModelList.add(new TopStreetViewModel("Phillip Island",-38.500644,145.231302 ));
                    placesModelList.add(new TopStreetViewModel("Mount Buffalo National Park",-36.732918,146.811495 ));
                    placesModelList.add(new TopStreetViewModel("Ballarat",-37.577173,143.866066 ));
                    placesModelList.add(new TopStreetViewModel("Melbourne CBD",-37.817954,144.968871 ));
                    placesModelList.add(new TopStreetViewModel("Bells Beach",-38.372692,144.279023 ));
                    placesModelList.add(new TopStreetViewModel("The Studio, Sydney Opera House",-33.857017,151.215045 ));
                    placesModelList.add(new TopStreetViewModel("Yarra Valley",-37.660055,145.468929 ));
                    placesModelList.add(new TopStreetViewModel("Falls Creek",-36.874735,147.243036 ));
                    placesModelList.add(new TopStreetViewModel("Mt Buller",-36.87484,147.242089 ));
                    placesModelList.add(new TopStreetViewModel("Grampians National Park",-37.293149,142.601589 ));
                    placesModelList.add(new TopStreetViewModel("Yarra Valley",-37.660055,145.468929 ));
                    placesModelList.add(new TopStreetViewModel("Falls Creek",-36.874735,147.243036 ));
                    placesModelList.add(new TopStreetViewModel("Mornington Peninsula",-38.497917,144.889008 ));
                    placesModelList.add(new TopStreetViewModel("Apollo Bay",-38.755857,143.554685 ));
                    placesModelList.add(new TopStreetViewModel("Wilsons Promontory",-39.030335,146.315712 ));
                    placesModelList.add(new TopStreetViewModel("Yarrawonga",-36.005968,146.003609 ));
                    placesModelList.add(new TopStreetViewModel("Perth Zoo",-31.975408,115.853526 ));
                    placesModelList.add(new TopStreetViewModel("Flemignton Racecourse",-37.789354,144.908532 ));
                    placesModelList.add(new TopStreetViewModel("Tarra Bulga National Park",-38.428897,146.566805 ));
                    placesModelList.add(new TopStreetViewModel("Puffing Billy",-37.908328,145.365155 ));
                    placesModelList.add(new TopStreetViewModel("Royal Botanic Gardens Melbourne",-37.828164,144.981004 ));
                    placesModelList.add(new TopStreetViewModel("The Utzon Room, Sydney Opera House",-33.857365,151.215516 ));
                    break;
                    case "World Wonder Project":
                        tvTitle.setText(String.valueOf("World Wonder Project"));
                        placesModelList.add(new TopStreetViewModel("Nijojo castle",35.012762,135.750338 ));
                        placesModelList.add(new TopStreetViewModel("Itsukushima Shinto Shrine",34.296968,132.318469 ));
                        placesModelList.add(new TopStreetViewModel("Archaeological Areas of Pompei",40.7517,14.494312 ));
                        placesModelList.add(new TopStreetViewModel("Scott's Hut, Cape Evans on Ross Island",-77.636248,166.417282 ));
                        placesModelList.add(new TopStreetViewModel("Versailles Palace",48.805224,2.118542 ));
                        placesModelList.add(new TopStreetViewModel("Hiroshima Peace Memorial",34.395108,132.453676 ));
                        placesModelList.add(new TopStreetViewModel("Historic Center of Prague",50.087989,14.420901 ));
                        placesModelList.add(new TopStreetViewModel("Independence Hall",39.949178,-75.150428 ));
                        placesModelList.add(new TopStreetViewModel("Three Castles, Bellinzona",46.192686,9.022496 ));
                        placesModelList.add(new TopStreetViewModel("Historic Walled Town of Cuenca",40.07836,-2.12978 ));
                        placesModelList.add(new TopStreetViewModel("Stonehenge",51.179197,-1.825302 ));
                        placesModelList.add(new TopStreetViewModel("Old Town Lunenburg",44.376978,-64.309726 ));
                        placesModelList.add(new TopStreetViewModel("Church Village of Gammelstad, Luleå",65.645377,22.026225 ));
                        placesModelList.add(new TopStreetViewModel("Mill Network at Kinderdijk-Elshout",51.878621,4.648365 ));
                        placesModelList.add(new TopStreetViewModel("Historic Town of Ouro Preto",-20.386195,-43.502827 ));
                        placesModelList.add(new TopStreetViewModel("Historic Center of Florence",43.773392,11.25518 ));
                        placesModelList.add(new TopStreetViewModel("Santa Prisca Parish Church",18.558093,-99.606553 ));
                        placesModelList.add(new TopStreetViewModel("Mill Network at Kinderdijk-Elshout",51.878621,4.648365 ));
                        placesModelList.add(new TopStreetViewModel("Historic Town of Ouro Preto",-20.386195,-43.502827 ));
                        placesModelList.add(new TopStreetViewModel("Historic Center of Florence",43.773392,11.25518 ));
                        placesModelList.add(new TopStreetViewModel("Santa Prisca Parish Church",18.558093,-99.606553 ));
                        placesModelList.add(new TopStreetViewModel("Shark Bay",-26.192525,113.68943 ));
                        placesModelList.add(new TopStreetViewModel("Sanctuary of Bom Jesus do Congonhas", -20.500908,-43.858205));
                        break;
                case "Sea Ports":
                    tvTitle.setText(String.valueOf("Sea Ports"));
                    placesModelList.add(new TopStreetViewModel("Port Hercule,", 43.7363698, 7.4261142));
                    placesModelList.add(new TopStreetViewModel("Port de Nice", 43.6976207, 7.2847341));
                    placesModelList.add(new TopStreetViewModel("Port Grimaud", 43.2730468, 6.5813216));
                    placesModelList.add(new TopStreetViewModel("Marina Baie des Anges", 43.6340053, 7.1431472));
                    placesModelList.add(new TopStreetViewModel("Port de Cannes (Vieux Port)", 43.5502448, 7.0131013));
                    placesModelList.add(new TopStreetViewModel("Port Mandelieu - La Napoule", 43.5214549, 6.9443649));
                    placesModelList.add(new TopStreetViewModel("Port de la Rague", 43.514371, 6.9400076));
                    placesModelList.add(new TopStreetViewModel("Marines de Cogolin", 43.2679412, 6.582139));
                    placesModelList.add(new TopStreetViewModel("Vieux Port de Golfe Juan", 43.5620873, 7.0758303));
                    placesModelList.add(new TopStreetViewModel("Menton Vieux Port", 43.777358, 7.5119376));
                    placesModelList.add(new TopStreetViewModel("Port de Villefranche-sur-Mer,", 43.6977072, 7.3083898));
                    placesModelList.add(new TopStreetViewModel("Menton Garavan and French Italian Border", 43.7836564, 7.5225648));
                    placesModelList.add(new TopStreetViewModel("Port de Théoule-sur-Mer", 43.5100428, 6.9394297));
                    placesModelList.add(new TopStreetViewModel("Port de Saint-Laurent-du-Var", 43.673416, 7.3261928));
                    placesModelList.add(new TopStreetViewModel("Port Figueirette-Miramar", 43.4994787, 6.9564714));
                    placesModelList.add(new TopStreetViewModel("Port Cogolin", 43.2682246, 6.5770008));
                    placesModelList.add(new TopStreetViewModel("Port de Sainte-Maxime", 43.2828839, 6.6011952));
                    placesModelList.add(new TopStreetViewModel("Port Camille Rayon", 43.5635814, 7.0746523));
                    placesModelList.add(new TopStreetViewModel("Port Vauban Antibes", 43.5863099, 7.1285712));
                    placesModelList.add(new TopStreetViewModel("Port de Cap d'Ail", 43.7292401, 7.4212074));
                    placesModelList.add(new TopStreetViewModel("Port de Saint-Jean Cap-Ferrat", 43.7172231, 7.3513383));
                    placesModelList.add(new TopStreetViewModel("Port de Beaulieu", 43.7074804, 7.3374262));
                    break;
                case "DryPorts":
                    tvTitle.setText(String.valueOf("DryPorts"));
                    placesModelList.add(new TopStreetViewModel("Port of Buenos Aires", -34.5799412, -58.3743793));
                    placesModelList.add(new TopStreetViewModel("Port Botany, Sydney", -33.9744036, 151.2239884));
                    placesModelList.add(new TopStreetViewModel("Port of Brisbane", -27.3845669, 153.172971));
                    placesModelList.add(new TopStreetViewModel("Port of Fremantle", -32.053335, 115.74132));
                    placesModelList.add(new TopStreetViewModel("Port of Melbourne", -37.8202626, 144.9744252));
                    placesModelList.add(new TopStreetViewModel("Port of Antwerp", 51.2438583, 4.3936195));
                    placesModelList.add(new TopStreetViewModel("Port of Centro", -23.9566111, -46.3132919));
                    placesModelList.add(new TopStreetViewModel("Port of Salvador", 13.4451379, -89.0570357));
                    placesModelList.add(new TopStreetViewModel("Halifax Port Authority", 44.6404839, -63.5655975));
                    placesModelList.add(new TopStreetViewModel("Port of Valparaiso", -33.0372889, -71.6275311));
                    placesModelList.add(new TopStreetViewModel("Port of Antofagasta", -23.653854, -70.4026981));
                    placesModelList.add(new TopStreetViewModel("Port of Iquique", -20.2065993, -70.1393933));
                    placesModelList.add(new TopStreetViewModel("Port of Hong Kong", 22.3500501, 114.1247631));
                    placesModelList.add(new TopStreetViewModel("Port of Rijeka", 45.3273995, 14.4372927));
                    placesModelList.add(new TopStreetViewModel("Aarhus", 56.1654276, 10.2259754));
                    placesModelList.add(new TopStreetViewModel("Copenhagen Malmö Port", 55.6207331, 13.004495));
                    placesModelList.add(new TopStreetViewModel("Fredericia", 55.5596809, 9.7548361));
                    placesModelList.add(new TopStreetViewModel("Port of Le Havre", 49.4798023, 0.1663282));
                    placesModelList.add(new TopStreetViewModel("Port of Germersheim", 49.23131, 8.37732));
                    placesModelList.add(new TopStreetViewModel("Tanjung Mas, Semarang", -6.9475734, 110.4291567));
                    placesModelList.add(new TopStreetViewModel("Port of Gioia Tauro", 38.4683286, 15.9194731));
                    placesModelList.add(new TopStreetViewModel("Port of Taranto", 40.4798828, 17.2052703));
                    placesModelList.add(new TopStreetViewModel("Port of Palermo", 38.1308744, 13.3636225));
                    placesModelList.add(new TopStreetViewModel("Port of Naples", 40.8496822, 14.3119485));
                    break;
                case "Beaches":
                    tvTitle.setText(String.valueOf("Beaches"));
                    placesModelList.add(new TopStreetViewModel("Bondi Beach", -33.89102, 151.277726));
                    placesModelList.add(new TopStreetViewModel("Blue Lagoon", 63.8797031, -22.4489511));
                    placesModelList.add(new TopStreetViewModel("Grace Bay", 21.8116045, -72.1658116));
                    placesModelList.add(new TopStreetViewModel("Anse Source d'Argent", -4.372587, 55.8284021));
                    placesModelList.add(new TopStreetViewModel("Piha Beach", -36.9585427, 174.4658125));
                    placesModelList.add(new TopStreetViewModel("Baia do Sancho", -3.8536342, -32.4428304));
                    placesModelList.add(new TopStreetViewModel("Siesta Key", 27.2671608, -82.5582104));
                    placesModelList.add(new TopStreetViewModel("Lake McKenzie", -25.4484484, 153.0572585));
                    placesModelList.add(new TopStreetViewModel("Rabbit Beach", 35.51297, 12.5579023));
                    placesModelList.add(new TopStreetViewModel("Antarctica", -75.25097, -0.07139));
                    placesModelList.add(new TopStreetViewModel("Goa Cina", -8.4471919, 112.6537097));
                    placesModelList.add(new TopStreetViewModel("Navagio", 37.8594645, 20.6249001));
                    placesModelList.add(new TopStreetViewModel("Praia do Abricó", -23.052495, -43.5362362));
                    placesModelList.add(new TopStreetViewModel("Lighthouse Field State Beach", 36.9533759, -122.0288092));
                    placesModelList.add(new TopStreetViewModel("Grande Anse Beach, La Digue Island, Seychelles", -4.3731524, 55.8285083));
                    placesModelList.add(new TopStreetViewModel("Matira Beach, Bora Bora, Tahiti", -16.5422249, -151.7356683));
                    placesModelList.add(new TopStreetViewModel("Rarotonga, Cook Islands", -21.2350587, -159.7767675));
                    placesModelList.add(new TopStreetViewModel("Navagio Beach, Greece", 37.8595009, 20.6248531));
                    placesModelList.add(new TopStreetViewModel("Trunk Bay, St. John, U.S. Virgin Islands", 18.3509979, -64.7708435));
                    placesModelList.add(new TopStreetViewModel("Bandon, Oregon, United States", 43.106954, -124.4358088));
                    placesModelList.add(new TopStreetViewModel("Bottom Bay, Barbados", 13.1362029, -59.425053));
                    placesModelList.add(new TopStreetViewModel("Falassarna Beach, Crete, Greece", 35.493186, 23.579767));
                    placesModelList.add(new TopStreetViewModel("Praia da Rocha Baixinha", 37.0753326, -8.1332169));
                    placesModelList.add(new TopStreetViewModel("Pipa Beach", -6.2272778, -35.0531239));
                    placesModelList.add(new TopStreetViewModel("Pantai Tamban", -8.4172026, 112.7101498));
                    placesModelList.add(new TopStreetViewModel("Drini Beach", -8.1381243, 110.5771656));
                    placesModelList.add(new TopStreetViewModel("As Catedrais beach", 43.5539831, -7.1572855));
                    placesModelList.add(new TopStreetViewModel("Champagne Beach", -15.1437919, 167.1203814));
                    placesModelList.add(new TopStreetViewModel("Playa de La Concha", 43.318508, -1.9852789));
                    placesModelList.add(new TopStreetViewModel("Nissi Beach", 34.98743, 33.9715508));
                    placesModelList.add(new TopStreetViewModel("South Bay (Taiwan)", 21.9594193, 120.766037));
                    placesModelList.add(new TopStreetViewModel("Anse Source d'Argent", -4.3693915, 55.8257262));
                    break;
                case "Leakes":
                    tvTitle.setText(String.valueOf("Leakes"));
                    placesModelList.add(new TopStreetViewModel("Marshall Island", 10.1617905, 166.0113424));
                    placesModelList.add(new TopStreetViewModel("Louvre Museum", 48.8612736, 2.335015));
                    placesModelList.add(new TopStreetViewModel("Proizd Near Korcula Island", 42.9866605, 16.6167329));
                    placesModelList.add(new TopStreetViewModel("Portofino", 44.3024841, 9.2118983));
                    placesModelList.add(new TopStreetViewModel("Neuschwanstein Castle Germany", 47.5550506, 10.7492342));
                    placesModelList.add(new TopStreetViewModel("Kauia Haiwaii", 22.1635778, -159.6691604));
                    placesModelList.add(new TopStreetViewModel("El Sardinero Santander", 43.4747616, -3.7930923));
                    placesModelList.add(new TopStreetViewModel("Cala Mariolu", 40.1235129, 9.6764042));
                    placesModelList.add(new TopStreetViewModel("Schrecksee", 47.4391215, 10.4670836));
                    placesModelList.add(new TopStreetViewModel("Cala Goloritze", 40.110693, 9.6848583));
                    placesModelList.add(new TopStreetViewModel("The Crooked Forest", 53.2141108, 14.4757408));
                    placesModelList.add(new TopStreetViewModel("Mirador Obispado Monterrey", 25.676239, -100.3466135));
                    placesModelList.add(new TopStreetViewModel("Cahir Castle", 52.3745471, -7.9270751));
                    placesModelList.add(new TopStreetViewModel("Napili Bay Beach", 20.9987213, -156.6672225));
                    placesModelList.add(new TopStreetViewModel("Seebrucke Sellin", 54.3828803, 13.6984421));
                    placesModelList.add(new TopStreetViewModel("Kayangan Lake in Coron", 11.9594607, 120.2265456));
                    placesModelList.add(new TopStreetViewModel("Batu Caves", 3.2381653, 101.6842671));
                    placesModelList.add(new TopStreetViewModel("Palawan", 9.8349493, 118.7383615));
                    placesModelList.add(new TopStreetViewModel("Morsum Kliff", 54.8767116, 8.4625196));
                    placesModelList.add(new TopStreetViewModel("Berliner Luft Und Badeparadies Neukolln", 52.4582371, 13.4476049));
                    placesModelList.add(new TopStreetViewModel("InterContinental Resort Tahiti", -17.5711632, -149.6195526));
                    placesModelList.add(new TopStreetViewModel("Portofino Italy", 44.3023164, 9.2139228));
                    placesModelList.add(new TopStreetViewModel("Lake Tahoe California Nevada", 39.1323571, -119.9463896));
                    placesModelList.add(new TopStreetViewModel("Kayangan Lake", 11.9577086, 120.2263569));
                    placesModelList.add(new TopStreetViewModel("Kauia Haiwaii", 22.1635778, -159.6691604));
                    placesModelList.add(new TopStreetViewModel("Ait Benhaddou Morocco", 31.0470695, -7.1277809));
                    placesModelList.add(new TopStreetViewModel("Aqua World Cancun", 21.0839509, -86.77503));
                    placesModelList.add(new TopStreetViewModel("Na Pali Coast State Park", 22.1790137, -159.6453283));
                    placesModelList.add(new TopStreetViewModel("A Coruna Galicia", 43.3623143, -8.4114859));
                    placesModelList.add(new TopStreetViewModel("Praia Preta da Costa do Sul", -23.7908773, -45.7146263));
                    placesModelList.add(new TopStreetViewModel("Firule Beach", 43.5003567, 16.4544));
                    placesModelList.add(new TopStreetViewModel("Antelope Canyon in Arizona", 36.8625172, -111.3746638));
                    placesModelList.add(new TopStreetViewModel("CYC Beach", 11.963845, 120.1806508));
                    placesModelList.add(new TopStreetViewModel("Le Meridien Bora Bora", -16.497917, -151.7019062));
                    placesModelList.add(new TopStreetViewModel("Teufelsberg", 52.497945, 13.2406519));
                    placesModelList.add(new TopStreetViewModel("County Waterford", 52.1389241, -7.3668495));
                    placesModelList.add(new TopStreetViewModel("Gonzalo Guerrero Playa Del Carmen", 20.6324755, -87.0725283));
                    placesModelList.add(new TopStreetViewModel("Ostrvo Gospa od Milosrđa", 42.4106727, 18.6784101));
                    placesModelList.add(new TopStreetViewModel("Caribean Cancun", 21.111259, -86.762268));
                    placesModelList.add(new TopStreetViewModel("Club Med Cancun", 21.0364043, -86.777625));
                    placesModelList.add(new TopStreetViewModel("Zion National Park", 37.3094714, -113.0522415));
                    placesModelList.add(new TopStreetViewModel("Embourios", 36.7021235, 24.4050475));
                    placesModelList.add(new TopStreetViewModel("Alexandra Falls Hiking Trail", -20.4403993, 57.4581368));
                    placesModelList.add(new TopStreetViewModel("Chichen Itza Playa Del Carmen", 20.7053058, -87.009796));
                    placesModelList.add(new TopStreetViewModel("Garganta del Diablo", -41.1878828, -71.8389028));
                    placesModelList.add(new TopStreetViewModel("Marina Barracuda", 21.0935758, -86.771264));
                    placesModelList.add(new TopStreetViewModel("Spreepark", 52.4847269, 13.4901219));
                    placesModelList.add(new TopStreetViewModel("Playa de los Genoveses", 36.7445895, -2.1220108));
                    break;
                case "Museums In Latin America":
                    tvTitle.setText(String.valueOf("Museums In Latin America"));
                    placesModelList.add(new TopStreetViewModel("Museu Oscar Niemeyer",-25.410076,-49.267302 ));
                    placesModelList.add(new TopStreetViewModel("Museu da Sustentabilidade",-23.563791,-46.701799 ));
                    placesModelList.add(new TopStreetViewModel("Museo Franz Mayer",19.437239,-99.143291 ));
                    placesModelList.add(new TopStreetViewModel("Museu Afro Brasil",-23.584018,-46.659207 ));
                    placesModelList.add(new TopStreetViewModel("Museo del Fútbol",-34.893988,-56.151614 ));
                    placesModelList.add(new TopStreetViewModel("Museu do Café",-23.932236,-46.330007 ));
                    placesModelList.add(new TopStreetViewModel("Museo Nacional del Tequila",20.884598,-103.839898 ));
                    placesModelList.add(new TopStreetViewModel("MMAPO",18.920825,-99.235606 ));
                    placesModelList.add(new TopStreetViewModel("Museo Fernando García Ponce", 20.966674,-89.622482));
                    placesModelList.add(new TopStreetViewModel("Museo del Juguete Antiguo",19.415465,-99.144514 ));
                    placesModelList.add(new TopStreetViewModel("Museo Submarino Abtao",-12.059832,-77.150871));
                    placesModelList.add(new TopStreetViewModel("Museo Archivo de la Fotografía",19.434995,-99.132223 ));
                    placesModelList.add(new TopStreetViewModel("Museo Paleontológico",5.641082,-73.522523 ));
                    placesModelList.add(new TopStreetViewModel("Polyforum Siqueiros",19.393385,-99.173392 ));
                    placesModelList.add(new TopStreetViewModel("Museu Casa Guilherme de Almeida",-23.5469,-46.673893 ));
                    placesModelList.add(new TopStreetViewModel("Museo Dolores Olmedo",19.265623,-99.124881 ));
                    placesModelList.add(new TopStreetViewModel("Museo de Sitio Bodega y Quadra",-12.044648,-77.028581 ));
                    placesModelList.add(new TopStreetViewModel("Museu de Arte Moderna de São Paulo, São Paulo Museum of Modern Art",-23.587958,-46.655548 ));
                    break;
                case "University of Italy":
                    tvTitle.setText(String.valueOf("University of Italy"));
                    placesModelList.add(new TopStreetViewModel("University of Bologna", 44.496865,11.352459));
                    placesModelList.add(new TopStreetViewModel("Palazzo Poggi Museum, University of Bologna",44.496839,11.352306 ));
                    placesModelList.add(new TopStreetViewModel("University of Padua",45.407039,11.877266 ));
                    placesModelList.add(new TopStreetViewModel("Bocconi University",45.450165,9.188318 ));
                    placesModelList.add(new TopStreetViewModel("University of Turin",45.069367,7.688842 ));
                    placesModelList.add(new TopStreetViewModel("University of Rome Tor Vergata",41.846137,12.627215 ));
                    placesModelList.add(new TopStreetViewModel("University of Milano-Bicocca",45.516449,9.213339 ));
                    placesModelList.add(new TopStreetViewModel("Polytechnic University of Turin",45.054172,7.686527 ));
                    placesModelList.add(new TopStreetViewModel("University of Milan",45.460261,9.194908 ));
                    break;
                    case "mexico-universities":
                    tvTitle.setText(String.valueOf("mexico-universities"));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus León",21.166254,-101.715729 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Tecnologica de Mexico - UNITEC Campus Atizapan",19.546258,-99.24108 ));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnologico y de Estudios Superiores de Monterrey Campus Santa Anita",20.568866,-103.484847 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Nacional Autónoma de México",19.332765,-99.18857 ));
                        placesModelList.add(new TopStreetViewModel("Waseda University Waseda Campus",35.709066,139.720978 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Panamericana Campus Ciudad de México",19.37298,-99.183641 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Valle de México Campus Zapopan",20.678434,-103.455799 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Futbol y Ciencias del Deporte",20.137604,-98.812491 ));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Cuernavaca",18.807425,-99.220563 ));
                        placesModelList.add(new TopStreetViewModel("Universidad de Quintana Roo (UQROO)",18.521614,-88.270896 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Iberoamericana Ciudad de Mexico",19.370894,-99.26396 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Istmo Campus Ixtepec",16.53348,-94.95664 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Tecnologica de Mexico - UNITEC Campus Cuitlahuac",19.459446,-99.174992 ));
                        placesModelList.add(new TopStreetViewModel("Universidad de Guadalajara Explanada Rectoría General",20.674975,-103.358906 ));
                        placesModelList.add(new TopStreetViewModel("Centro Universitario de Ciencias Económico Administrativas",20.741397,-103.379994 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Istmo UNISTMO Campus Tehuantepec",16.288792,-95.240803 ));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnologico y de Estudios Superiores de Monterrey Campus Hidalgo",20.096312,-98.765384 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Tecnológica de la Mixteca",17.8279,-97.802957 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Mar Campus Puerto Angel",15.662522,-96.500335 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Tecnologica de Mexico - UNITEC Campus Marina",19.448182,-99.180752 ));
                        placesModelList.add(new TopStreetViewModel("Centro Universitario de Ciencias Sociales y Humanidades Campus Belenes",19.448182,-99.180752 ));
                        placesModelList.add(new TopStreetViewModel("Centro Universitario de Arte, Arquitectura y Diseño",20.678553,-103.344964 ));
                        placesModelList.add(new TopStreetViewModel("Centro Universitario de Arte, Arquitectura y Diseño Campus Huentitán",20.740066,-103.311935 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Marista de Queretaro",20.601382,-100.3854 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Politecnica de Queretaro",20.546069,-100.275035 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Autonoma De Queretaro Centro de Negocio",20.704472,-100.441848 ));
                        placesModelList.add(new TopStreetViewModel("Universidad Juárez Autónoma de Tabasco (UJAT)",20.704472,-100.441848 ));
                        placesModelList.add(new TopStreetViewModel("Universidad de las Americas Puebla",19.053896,-98.282928 ));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Celaya",20.548296,-100.883973 ));
                        placesModelList.add(new TopStreetViewModel("Universidad del Valle de México Campus Tlalpan", 19.294824,-99.143945));
                        placesModelList.add(new TopStreetViewModel("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Irapuato",20.68605,-101.394252 ));
                        break;
                case "Indian Universties":
                    tvTitle.setText(String.valueOf("Indian Universties"));
                placesModelList.add(new TopStreetViewModel("All India Institute of Medical Sciences (AIIMS), Delhi",28.567468,77.208384 ));
                placesModelList.add(new TopStreetViewModel("Vellore Institute of Technology (VIT), Vellore",12.969701,79.158104 ));
                placesModelList.add(new TopStreetViewModel("Jamia Milia Islamia University, Delhi",28.562955,77.284264 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Technology (IIT), Roorkee",29.86555,77.896581 ));
                placesModelList.add(new TopStreetViewModel("BITS Pillani, Goa",15.387317,73.875597 ));
                placesModelList.add(new TopStreetViewModel("Indian School of Business (ISB), Mohali",30.669863,76.727108 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Management (IIM), Indore",22.626888,75.790345 ));
                placesModelList.add(new TopStreetViewModel("Indian School of Business (ISB), Hyderabad",17.435343,78.341366 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Management (IIM), Kozhikode",11.293988,75.873504 ));
                placesModelList.add(new TopStreetViewModel("University of Hyderabad (HCU), Hyderabad",17.460505,78.334488 ));
                placesModelList.add(new TopStreetViewModel("Amity University, Noida",28.5442,77.334446 ));
                placesModelList.add(new TopStreetViewModel("Central University of Kerala, Kasargod",12.390484,75.096256 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Technology (IIT), Delhi",28.54563,77.196389 ));
                placesModelList.add(new TopStreetViewModel("Sant Longowal Institute of Engineering & Technology (SLIET), Sangrur",30.220265,75.706786 ));
                placesModelList.add(new TopStreetViewModel("Amity University, Jaipur",27.178111,75.95865 ));
                placesModelList.add(new TopStreetViewModel("Central University of Haryana, Mahendergarh",28.360108,76.146814 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Calicut",11.321778,75.934823 ));
                placesModelList.add(new TopStreetViewModel("Central University of Punjab, Bhatinda",30.170802,74.968065 ));
                placesModelList.add(new TopStreetViewModel("Dr. Harisingh Gaur Vishwa Vidyalaya, Sagar",23.824671,78.771614 ));
                placesModelList.add(new TopStreetViewModel("Central University of Kerala, Kasargod",12.390484,75.096256 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Ponda",15.412579,73.977945 ));
                placesModelList.add(new TopStreetViewModel("Singhad Business School, Pune",18.50789,73.835433 ));
                placesModelList.add(new TopStreetViewModel("Great Lakes Institute of Management, Chennai",12.572806,80.1405 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Information Technology (IIIT), Allahabad",25.429252,81.770023 ));
                placesModelList.add(new TopStreetViewModel("S P Jain School of Global Management, Mumbai",19.123102,72.837124 ));
                placesModelList.add(new TopStreetViewModel("BITS Pilani, Hyderabad",17.545243,78.571248 ));
                placesModelList.add(new TopStreetViewModel("O P Jindal Global University, Sonipat",28.926207,77.057803 ));
                placesModelList.add(new TopStreetViewModel("Indian Statistical Institute (ISI), Kolkata",22.648033,88.377354 ));
                placesModelList.add(new TopStreetViewModel("Graphic Era University, Dehradun",30.267619,77.99556 ));
                placesModelList.add(new TopStreetViewModel("Indian Council of Social Science Research (ICSSR), Delhi",28.531796,77.170418 ));
                placesModelList.add(new TopStreetViewModel("Malaviya National Institute of Technology (MNIT), Jaipur",26.865095,75.807681 ));
                placesModelList.add(new TopStreetViewModel("BITS Pilani, Pillani",28.363249,75.586376 ));
                placesModelList.add(new TopStreetViewModel("Guru Ghasidas Vishwavidyalaya, Bilaspur",22.127849,82.138067 ));
                placesModelList.add(new TopStreetViewModel("Manipal Univesity, Manipal",13.353243,74.785051 ));
                placesModelList.add(new TopStreetViewModel("Mahatma Gandhi Antarrashtriya Hindi Vishwavidyalaya, Vardha",20.767319,78.583928 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Technology (IIT), Mumbai",19.125228,72.916532 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Science Education & Research (IISER), Chandigarh",30.662391,76.730276 ));
                placesModelList.add(new TopStreetViewModel("The English and Foreign Languages University (EFLU), Hyderabad",17.424398,78.526317 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Information Technology and Management (IIITM), Gwalior",26.248752,78.177105 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Management (IIM), Kolkata",22.444915,88.297018 ));
                placesModelList.add(new TopStreetViewModel("Jawaharlal Nehru University (JNU), Delhi",28.551117,77.169338 ));
                placesModelList.add(new TopStreetViewModel("Visvesvaraya National Institute of Technology, Nagpur",21.130898,79.050975 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Jallandhar",31.394273,75.533069 ));
                placesModelList.add(new TopStreetViewModel("Indian Council of Historical Research (ICHR), Delhi",28.624843,77.230319 ));
                placesModelList.add(new TopStreetViewModel("Graphic Era Hill University, Bhimtal",29.375521,79.531334 ));
                placesModelList.add(new TopStreetViewModel("Indian Statistical Institute (ISI), Delhi",28.539677,77.186208 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Technology (IIT), Ropar",30.974643,76.539266 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technical Teachers' Training & Research, Bhopal",23.240555,77.392753 ));
                placesModelList.add(new TopStreetViewModel("Pune Institute of Computers (PIC), Pune",18.4574,73.851343 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Delhi",28.843595,77.104849 ));
                placesModelList.add(new TopStreetViewModel("University Grants Commission (UGC), Delhi",28.629468,77.240127 ));
                placesModelList.add(new TopStreetViewModel("Shri Lal Bahadur Shastri Rashtriya Sanskrit Vidyapeeth, Delhi",28.541411,77.182575 ));
                placesModelList.add(new TopStreetViewModel("Manipal University - School of Life Sciences, Manipal",13.346296,74.788903 ));
                placesModelList.add(new TopStreetViewModel("Rashtriya Sanskrit Sansthan, Delhi",28.610665,77.103242 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technical Teachers' Training & Research, Chandigarh",30.729787,76.807211 ));
                placesModelList.add(new TopStreetViewModel("Indian Institute of Foreign Trade (IIFT), Delhi",28.536737,77.183385 ));
                placesModelList.add(new TopStreetViewModel("Management Development Institute (MDI), Gurgaon",28.471153,77.058435 ));
                placesModelList.add(new TopStreetViewModel("Amity University, Gwalior",26.274333,78.223746 ));
                placesModelList.add(new TopStreetViewModel("Amity University, Manesar",28.316818,76.914267 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Warangal",17.98442,79.530763 ));
                placesModelList.add(new TopStreetViewModel("IMT Hyderbad, Hyderabad",17.212175,78.367198 ));
                placesModelList.add(new TopStreetViewModel("Lovely Professional University (LPU), Phagwara",31.260742,75.70698 ));
                placesModelList.add(new TopStreetViewModel("Indira Gandhi National Tribal University, Anuppur",22.803324,81.752626 ));
                placesModelList.add(new TopStreetViewModel("Maulana Azad National Urdu University (MANUU), Hyderabad",17.428866,78.361759 ));
                placesModelList.add(new TopStreetViewModel("ABV - Indian Institute of Information Technology, Design & Manufacturing (IIITDM), Jabalpur",23.177927,80.025671 ));
                placesModelList.add(new TopStreetViewModel("Manipal Institute of Technology (MIT), Manipal",13.352989,74.791419 ));
                placesModelList.add(new TopStreetViewModel("Shiv Nadar University, Noida",28.525524,77.574021 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Technology (NIT), Surthakal",13.01067,74.79408 ));
                placesModelList.add(new TopStreetViewModel("School of Planning & Architecture, Delhi",28.628939,77.246142 ));
                placesModelList.add(new TopStreetViewModel("National Institute of Industrial Engineering (NIIE), Mumbai",19.136655,72.901388 ));
                placesModelList.add(new TopStreetViewModel("Graphic Era Hill University, Dehradun",30.273095,77.999475 ));
                placesModelList.add(new TopStreetViewModel("Maulana Azad National Institute of Technology (MANIT), Bhopal",23.220814,77.404093 ));
                break;
                case "Japanese University":
                    tvTitle.setText(String.valueOf("Japanese University"));
                    placesModelList.add(new TopStreetViewModel("Waseda University Waseda Campus",35.709066,139.720978 ));
                    placesModelList.add(new TopStreetViewModel("Senshu University Kanda Campus",35.696462,139.754461 ));
                    placesModelList.add(new TopStreetViewModel("Sakushin Gakuin University",36.542909,139.97662 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo Gakugei University",35.704135,139.490661 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo Univeristy of Foreign Studies", 35.674818,139.520083));
                    placesModelList.add(new TopStreetViewModel("Takasaki City University of Economics",36.346095,138.977549 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo University of Marine Science and Technology - Shinagawa Campus",35.628441,139.74773 ));
                    placesModelList.add(new TopStreetViewModel("Aoyamagakuin University Aoyama Campus",35.661255,139.709327 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo University of Agriculture and Technology Fuchu Campus",35.684156,139.481029 ));
                    placesModelList.add(new TopStreetViewModel("Shimane University Matsue Campus",35.484649,133.068119 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo University of Agriculture and Technology Koganei Campus",35.699483,139.520227 ));
                    placesModelList.add(new TopStreetViewModel("Toyama University - Gofuku Campus",36.70049,137.188854 ));
                    placesModelList.add(new TopStreetViewModel("Hokkaido University",43.073133,141.341096 ));
                    placesModelList.add(new TopStreetViewModel("Kyushu University Hakozaki Campus",33.622037,130.425451 ));
                    placesModelList.add(new TopStreetViewModel("Akita International University",39.626138,140.197792 ));
                    placesModelList.add(new TopStreetViewModel("Akita University Tegata Campus",39.726255,140.133597 ));
                    placesModelList.add(new TopStreetViewModel("Ibaraki University Mito Campus",36.400699,140.442306 ));
                    placesModelList.add(new TopStreetViewModel("Saitama University",35.863534,139.607121 ));
                    placesModelList.add(new TopStreetViewModel("Kanda University of International Studies",35.656846,140.042094 ));
                    placesModelList.add(new TopStreetViewModel("Fukuoka University of Education",33.812196,130.594102 ));
                    placesModelList.add(new TopStreetViewModel("The University of Electro-Communications",35.65568,139.543748 ));
                    placesModelList.add(new TopStreetViewModel("Kyushu Institute of Technology Iizuka Campus",33.652708,130.672083 ));
                    placesModelList.add(new TopStreetViewModel("Kyushu Women’s Junior College",33.868873,130.708374 ));
                    placesModelList.add(new TopStreetViewModel("Kyushu Women’s University",33.86883,130.709242 ));
                    placesModelList.add(new TopStreetViewModel("Seinan Gakuin University",33.58534,130.356652 ));
                    placesModelList.add(new TopStreetViewModel("Future University Hakodate",41.841605,140.765455 ));
                    placesModelList.add(new TopStreetViewModel("Hokkaido Institute Of Technology",43.134325,141.248246 ));
                    placesModelList.add(new TopStreetViewModel("Hokusei Gakuen University",43.025073,141.448337 ));
                    placesModelList.add(new TopStreetViewModel("Rakuno Gakuen University",43.07368,141.510707 ));
                    placesModelList.add(new TopStreetViewModel("Sapporo University",43.010366,141.387089 ));
                    placesModelList.add(new TopStreetViewModel("Senshu University Ikuta Campus",35.611101,139.553422 ));
                    placesModelList.add(new TopStreetViewModel("Tokai University Shonan Campus",35.365579,139.277705 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo City University",35.562211,139.574539 ));
                    placesModelList.add(new TopStreetViewModel("Yokohama College of Art and Design",35.557593,139.496744 ));
                    placesModelList.add(new TopStreetViewModel("Kumamoto Gakuen University",32.804137,130.730319 ));
                    placesModelList.add(new TopStreetViewModel("Kyoto Gakuen University",34.993748,135.552422 ));
                    placesModelList.add(new TopStreetViewModel("Tohoku Fukushi University Kunimigaoka First Campus",38.282791,140.834522 ));
                    placesModelList.add(new TopStreetViewModel("Tohoku University Aobayama Campus",38.257406,140.8371 ));
                    placesModelList.add(new TopStreetViewModel("Niigata University of Health and Welfare",37.966631,139.185709 ));
                    placesModelList.add(new TopStreetViewModel("University of Niigata Prefecture",37.92347,139.12622 ));
                    placesModelList.add(new TopStreetViewModel("Okinawa Prefectural University of Art",26.219001,127.719196 ));
                    placesModelList.add(new TopStreetViewModel("Nihon University College of Humanities and Sciences",35.663085,139.634321 ));
                    placesModelList.add(new TopStreetViewModel("Hannan University Main Campus",34.591306,135.535597 ));
                    placesModelList.add(new TopStreetViewModel("Osaka Institute of Technology",34.730513,135.544105 ));
                    placesModelList.add(new TopStreetViewModel("St Agnes College Takatsuki Campus",34.867913,135.586848 ));
                    placesModelList.add(new TopStreetViewModel("Daito Bunka University Higashi Matsuyama Campus",36.000833,139.368473 ));
                    placesModelList.add(new TopStreetViewModel("University of the Sacred Heart, Tokyo",35.109487,135.902077 ));
                    placesModelList.add(new TopStreetViewModel("The University of Tokushima Shinkura Campus",34.070008,134.559554 ));
                    placesModelList.add(new TopStreetViewModel("Showa Woman's University",35.683844,139.73131 ));
                    placesModelList.add(new TopStreetViewModel("Takachiho University",35.683473,139.638373 ));
                    placesModelList.add(new TopStreetViewModel("Tokyo Metropolitan University Minami-Osawa Campus",35.616329,139.377962 ));
                    placesModelList.add(new TopStreetViewModel("Takushoku University Hachioji Campus",35.625541,139.278294 ));
                    placesModelList.add(new TopStreetViewModel("Tottori University of Environmental studies",35.450706,134.256162 ));
                    placesModelList.add(new TopStreetViewModel("Teikyo University Hachioji Campus",35.642348,139.419632 ));
                    placesModelList.add(new TopStreetViewModel("Rissyo University Kumagaya Campus",36.108505,139.364863 ));
                    placesModelList.add(new TopStreetViewModel("Seian University of art and design",35.651299,139.719275 ));
                    placesModelList.add(new TopStreetViewModel("Kobe University Rokkodai First Campus",34.72779,135.234421 ));
                    placesModelList.add(new TopStreetViewModel("International University of Health and Welfare",36.870767,140.070493 ));
                    placesModelList.add(new TopStreetViewModel("Iwate Prefectural University Takizawa Campus", 39.800309,141.138382));
                    placesModelList.add(new TopStreetViewModel("Wakayama University",34.26713,135.151255 ));
                    placesModelList.add(new TopStreetViewModel("Chukyo University", 35.137665,136.965615));
                    placesModelList.add(new TopStreetViewModel("Toyo University Hakusan Campus",35.722883,139.750474 ));
                    placesModelList.add(new TopStreetViewModel("Osaka University Suita Campus",34.8229,135.519149 ));
                    placesModelList.add(new TopStreetViewModel("Osaka University Minoh Campus",34.852219,135.517361 ));
                    placesModelList.add(new TopStreetViewModel("Kurume Institute of Technology",33.266001,130.53799 ));
                    placesModelList.add(new TopStreetViewModel("Yamanashi Eiwa College",35.660996,138.614367 ));
                    placesModelList.add(new TopStreetViewModel("Fukuyama City University, Kitahonjo Campus",34.500054,133.348045 ));
                    placesModelList.add(new TopStreetViewModel("Ohkagakuen University / Nagoya College",35.059196,136.97584 ));
                    placesModelList.add(new TopStreetViewModel("Osaka University Toyonaka Campus",34.801422,135.454598 ));
                    placesModelList.add(new TopStreetViewModel("Tama Art University Hachioji Campus",35.611219,139.350093 ));
                    placesModelList.add(new TopStreetViewModel("Kitami Institute of Technology",43.822761,143.906071 ));
                    placesModelList.add(new TopStreetViewModel("Fukuyama University",34.458003,133.230837 ));
                    placesModelList.add(new TopStreetViewModel("Nagaoka Institute of Design",37.466743,138.829685 ));
           break;
                case "Uk Universties":
                    tvTitle.setText(String.valueOf("Uk Universties"));
                    placesModelList.add(new TopStreetViewModel("St Cross College, Oxford",51.756454,-1.260214 ));
                    placesModelList.add(new TopStreetViewModel("Hertford College, Oxford",51.754144,-1.253267 ));
                    placesModelList.add(new TopStreetViewModel("All Souls College, Oxford",51.753805,-1.252984 ));
                    placesModelList.add(new TopStreetViewModel("St. John’s College, Oxford",51.756157,-1.258111 ));
                    placesModelList.add(new TopStreetViewModel("Jesus College, Oxford",51.753401,-1.257321 ));
                    placesModelList.add(new TopStreetViewModel("Oriel College, Oxford",51.751626,-1.253588 ));
                    placesModelList.add(new TopStreetViewModel("Corpus Christi College",51.750958,-1.25361 ));
                    placesModelList.add(new TopStreetViewModel("Merton College, Oxford",51.750969,-1.251037 ));
                    placesModelList.add(new TopStreetViewModel("Pembroke College, Oxford",51.750013,-1.258388 ));
                    placesModelList.add(new TopStreetViewModel("St Edmund Hall, Oxford",51.753056,-1.250008 ));
                    placesModelList.add(new TopStreetViewModel("Lady Margaret Hall, Oxford",51.764973,-1.253923 ));
                    placesModelList.add(new TopStreetViewModel("Somerville College, Oxford",51.75975,-1.261526 ));
                    placesModelList.add(new TopStreetViewModel("Wadham College, Oxford",51.755665,-1.254435 ));
                    placesModelList.add(new TopStreetViewModel("New College, Oxford",51.754013,-1.250561 ));
                    placesModelList.add(new TopStreetViewModel("Magdalen College, Oxford",51.752378,-1.245741 ));
                    placesModelList.add(new TopStreetViewModel("Harris Manchester College, Oxford",51.755795,-1.252457 ));
                    placesModelList.add(new TopStreetViewModel("Green Templeton College, Oxford",51.761254,-1.262872 ));
                    placesModelList.add(new TopStreetViewModel("St Peter's College, Oxford",51.752641,-1.261067 ));
                    placesModelList.add(new TopStreetViewModel("St Stephen's House, Oxford",51.7464,-1.240543 ));
                    placesModelList.add(new TopStreetViewModel("Trinity College, Oxford",51.755636,-1.257216 ));
                    placesModelList.add(new TopStreetViewModel("Saïd Business School, Oxford",51.753993,-1.268429 ));
                    placesModelList.add(new TopStreetViewModel("Trinity Hall, Cambridge",52.205772,0.115165 ));
                    placesModelList.add(new TopStreetViewModel("St John's College, Cambridge",52.208551,0.115264 ));
                    placesModelList.add(new TopStreetViewModel("Gonville & Caius College, Cambridge",52.20609,0.116645 ));
                    placesModelList.add(new TopStreetViewModel("Jesus College, Cambridge",52.209475,0.124327 ));
                    placesModelList.add(new TopStreetViewModel("Queens' College, Cambridge",52.202297,0.116097 ));
                    placesModelList.add(new TopStreetViewModel("Great St Mary's Church, Cambridg",52.205312,0.118335 ));
                    placesModelList.add(new TopStreetViewModel("Newnham College, Cambridge",52.199766,0.105915 ));
                    placesModelList.add(new TopStreetViewModel("Cambridge University Botanic Garden",52.193585,0.126671 ));
                    placesModelList.add(new TopStreetViewModel("Durham University",54.764616,-1.570332 ));
                    placesModelList.add(new TopStreetViewModel("Keele University",52.999469,-2.270576 ));
                    placesModelList.add(new TopStreetViewModel("Cranfield University",52.07564,-0.625413 ));
                    placesModelList.add(new TopStreetViewModel("The University of Sheffield",53.3818,-1.488559 ));
                    placesModelList.add(new TopStreetViewModel("Swansea University",53.3818,-1.488559 ));
                    placesModelList.add(new TopStreetViewModel("University of Nottingham",52.937877,-1.198891 ));
                    placesModelList.add(new TopStreetViewModel("University College London",51.524285,-0.133657 ));
                    placesModelList.add(new TopStreetViewModel("Cardiff University",51.487556,-3.179933 ));
                    placesModelList.add(new TopStreetViewModel("University of St. Andrews",56.341862,-2.794353 ));
                    placesModelList.add(new TopStreetViewModel("Goldsmiths, University of London",51.475405,-0.037873 ));
                    break;
                case "Turkey Universities":
                    tvTitle.setText(String.valueOf("Turkey Universities"));
                placesModelList.add(new TopStreetViewModel("Bogazici University",41.08337,29.051893 ));
                placesModelList.add(new TopStreetViewModel("Istanbul Technical University Taşkışla Campus",41.041185,28.989549 ));
                placesModelList.add(new TopStreetViewModel("Istanbul Technical University - Ayazağa Campus",41.107213,29.021409 ));
                placesModelList.add(new TopStreetViewModel("Dokuz Eylül University",38.375913,27.193272 ));
                placesModelList.add(new TopStreetViewModel("Izmir Institute of Technology",38.3191,26.642493 ));
                placesModelList.add(new TopStreetViewModel("Koç University",41.205301,29.072375 ));
                placesModelList.add(new TopStreetViewModel("İhsan Doğramacı Bilkent University",39.869076,32.748825 ));
                placesModelList.add(new TopStreetViewModel("Yıldız Technical University",41.052244,29.010297 ));
                placesModelList.add(new TopStreetViewModel("Çankaya University",39.820314,32.558238 ));
                placesModelList.add(new TopStreetViewModel("Middle East Technical University",39.894891,32.784226 ));
                placesModelList.add(new TopStreetViewModel("Uludağ University",40.222531,28.866769 ));
                placesModelList.add(new TopStreetViewModel("Kadir Has University",41.023806,28.960115 ));
                placesModelList.add(new TopStreetViewModel("İstanbul Arel University",41.055541,28.500263 ));
                placesModelList.add(new TopStreetViewModel("Erciyes University",38.709911,35.533571 ));
                placesModelList.add(new TopStreetViewModel("Marmara University",40.985824,29.050737 ));
                placesModelList.add(new TopStreetViewModel("Kocaeli University",40.821666,29.930177 ));
                placesModelList.add(new TopStreetViewModel("Gebze Technical University",40.808987,29.36524 ));
                placesModelList.add(new TopStreetViewModel("Sakarya University",40.744192,30.340515 ));
                placesModelList.add(new TopStreetViewModel("Eskişehir Osmangazi University - Meşelik Campus",39.754272,30.488212 ));
                placesModelList.add(new TopStreetViewModel("Eskişehir Osmangazi University - Bademlik Campus",39.759731,30.523353 ));
                placesModelList.add(new TopStreetViewModel("Eskişehir Osmangazi University - Ali Numan Kıraç Campus",39.758997,30.474025 ));
                placesModelList.add(new TopStreetViewModel("Çanakkale Onsekiz Mart University",40.106525,26.410705 ));
                placesModelList.add(new TopStreetViewModel("Izmir University of Economics",38.388103,27.044711 ));
                placesModelList.add(new TopStreetViewModel("Selçuk University",38.016494,32.512631 ));
                placesModelList.add(new TopStreetViewModel("Balıkesir University",39.541275,28.006526 ));
                placesModelList.add(new TopStreetViewModel("Süleyman Demirel University",37.832497,30.529508 ));
            break;
            }
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
            TopStreetViewModel placesModel = placesModelList.get(mPosition);
            startActivity(new Intent(context, StreetViewMapActivity.class)
                    .putExtra("latLng", new LatLng(placesModel.latitude, placesModel.longitude)));
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
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
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

            super.onBackPressed();

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
