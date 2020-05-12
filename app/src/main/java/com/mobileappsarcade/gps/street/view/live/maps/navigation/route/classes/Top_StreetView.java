package com.mobileappsarcade.gps.street.view.live.maps.navigation.route.classes;

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
import com.mobileappsarcade.gps.street.view.live.maps.navigation.route.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Top_StreetView extends AppCompatActivity {
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
            context = Top_StreetView.this;
            tvTitle = findViewById(R.id.tvTitle);
            tvTitle.setTypeface(null, Typeface.ITALIC);
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                listOptions(bundle.getString("Type", ""));
            }
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

    private void listOptions(String type) {
        try {
            placesModelList = new ArrayList<>();
            switch (type) {
                case "WorldWonders":
                    tvTitle.setText(String.valueOf("World Wonders"));
                    placesModelList.add(new TopStreetView_Model("Hanging Gardens of Babylon", 48.919861, 2.343222));
                    placesModelList.add(new TopStreetView_Model("Lighthouse of Alexandria", 38.790405, -77.040581));
                    placesModelList.add(new TopStreetView_Model("Temple of Artemis at Ephesus", 37.949872, 27.363381));
                    placesModelList.add(new TopStreetView_Model("Colossus of Rhodes", 36.451066, 28.225833));
                    placesModelList.add(new TopStreetView_Model("Statue of Zeus at Olympia", 38.099974, 21.583320));
                    placesModelList.add(new TopStreetView_Model("Great Pyramid of Giza", 29.979235, 31.134202));
                    placesModelList.add(new TopStreetView_Model("Mausoleum at Halicarnassus", 37.038132, 27.424385));
                    break;
                case "Famous":
                    tvTitle.setText(R.string.txt_famous_places);
                    placesModelList.add(new TopStreetView_Model("Kaaba", 21.422524, 39.826182));
                    placesModelList.add(new TopStreetView_Model("Burj Khalifa", 25.197197, 55.274376));
                    placesModelList.add(new TopStreetView_Model("Statue of Liberty", 40.689249, -74.044500));
                    placesModelList.add(new TopStreetView_Model("Notre-Dame de Paris", 48.852968, 2.349902));
                    placesModelList.add(new TopStreetView_Model("Ming tombs", 40.255149, 116.223556));
                    placesModelList.add(new TopStreetView_Model("Edinburgh Castle", 55.948595, -3.199913));
                    placesModelList.add(new TopStreetView_Model("Siena", 43.318809, 11.330757));
                    placesModelList.add(new TopStreetView_Model("Eiffel tower", 48.858370, 2.294481));
                    placesModelList.add(new TopStreetView_Model("Centre Georges Pompidou", 48.860642, 2.352245));
                    placesModelList.add(new TopStreetView_Model("Tower Bridge", 51.505597, -0.075338));
                    placesModelList.add(new TopStreetView_Model("Taj Mahal", 27.175015, 78.042155));
                    placesModelList.add(new TopStreetView_Model("Zandvoort", 52.371149, 4.533355));
                    placesModelList.add(new TopStreetView_Model("Acropolis of Athens", 37.9715322, 23.725749));
                    placesModelList.add(new TopStreetView_Model("Sagrada Família", 41.403630, 2.174356));
                    placesModelList.add(new TopStreetView_Model("Van Gogh Museum", 52.358416, 4.881076));
                    placesModelList.add(new TopStreetView_Model("London Eye", 51.503324, -0.119543));
                    placesModelList.add(new TopStreetView_Model("Colosseum", 41.890210, 12.492231));
                    placesModelList.add(new TopStreetView_Model("Louvre rivoli", 48.860719, 2.340958));
                    placesModelList.add(new TopStreetView_Model("Tower of London", 51.508112, -0.075949));
                    placesModelList.add(new TopStreetView_Model("Buckingham Palace", 51.501364, -0.141890));
                    placesModelList.add(new TopStreetView_Model("Stonehenge", 51.178882, -1.826215));
                    placesModelList.add(new TopStreetView_Model("Roman Forum", 41.892462, 12.485325));
                    break;
                case "Rivers":
                    tvTitle.setText(R.string.txt_rivers);
                    placesModelList.add(new TopStreetView_Model("Melon headed whales, Palmyra Atoll, USA", 5.859013, -162.104696));
                    placesModelList.add(new TopStreetView_Model("Mayreau Hot Springs, St Vincent & the Grenadines", 12.627667, -61.378149));
                    placesModelList.add(new TopStreetView_Model("The Channel, Palmyra Atoll, USA", 5.872685, -162.111947));
                    placesModelList.add(new TopStreetView_Model("Komodo Islands", -8.737039, 119.412259));
                    placesModelList.add(new TopStreetView_Model("Penguin Spit, Palmyra Atoll, USA", 5.87155, -162.110352));
                    placesModelList.add(new TopStreetView_Model("Yonaguni Monument, Yonaguni, Okinawa, Japan", 24.435919, 123.011134));
                    placesModelList.add(new TopStreetView_Model("Nishibama, Aka Island, Okinawa, Japan", 26.202737, 127.289096));
                    placesModelList.add(new TopStreetView_Model("Mega Jacks, Palmyra Atoll, USA", 5.864789, -162.14032));
                    placesModelList.add(new TopStreetView_Model("Giant Potato, Nagura Bay, Ishigaki, Okinawa, Japan", 24.399209, 124.117781));
                    placesModelList.add(new TopStreetView_Model("Jurassic Park, Palmyra Atoll, USA", 5.858315, -162.101257));
                    placesModelList.add(new TopStreetView_Model("Muli Kandu, Maldives", 2.927715, 73.590414));
                    placesModelList.add(new TopStreetView_Model("Yamada Point, Onna, Okinawa, Japan", 26.441502, 127.790058));
                    placesModelList.add(new TopStreetView_Model("Holei, Palmyra Atoll, USA", 5.866448, -162.067535));
                    placesModelList.add(new TopStreetView_Model("Ile Vache Marine, Chagos, British Indian Ocean Territory", -5.427774, 71.828873));
                    placesModelList.add(new TopStreetView_Model("Aharen Beach, Tokashiki, Okinawa, Japan", 26.168867, 127.343795));
                    placesModelList.add(new TopStreetView_Model("Crazy Corals, Palmyra Atoll, USA", 5.885904, -162.12561));
                    placesModelList.add(new TopStreetView_Model("The Liberty Wreck, Bali", -8.274153, 115.592694));
                    placesModelList.add(new TopStreetView_Model("Agariushi / Gahi Island, Zamami, Okinawa, Japan", 26.21729, 127.310158));
                    placesModelList.add(new TopStreetView_Model("Coral Gardens, Palmyra Atoll, USA", 5.885806, -162.060135));
                    placesModelList.add(new TopStreetView_Model("SS Antilla Shipwreck, Aruba", 12.601959, -70.058343));
                    placesModelList.add(new TopStreetView_Model("Leukan III A, Bunaken National Park, Indonesia", 1.602118, 124.766276));
                    placesModelList.add(new TopStreetView_Model("Fukui, Bunaken National Park, Indonesia", 1.611651, 124.739282));
                    placesModelList.add(new TopStreetView_Model("Batu Rufus, Indonesia", -0.565927, 130.284558));
                    placesModelList.add(new TopStreetView_Model("Timor II, Bunaken National Park, Indonesia", 1.618098, 124.781914));
                    placesModelList.add(new TopStreetView_Model("Shelly Beach, Australia", -33.893774, 151.282125));
                    placesModelList.add(new TopStreetView_Model("Aquarius Reef Base, Florida Keys - United States of America", 24.950493, -80.454069));
                    placesModelList.add(new TopStreetView_Model("Batu Bolong, Komodo, Indonesia", -8.537229, 119.613738));
                    placesModelList.add(new TopStreetView_Model("Galápagos Islands, Ecuador", -1.239203, -90.385735));
                    placesModelList.add(new TopStreetView_Model("Big Momma, American Samoa", -14.259231, -169.500376));
                    placesModelList.add(new TopStreetView_Model("Tatawa Kecil, Komodo, Indonesia", -8.53004, 119.62723));
                    placesModelList.add(new TopStreetView_Model("Crystal Bay, Indonesia", -8.715413, 115.457015));
                    placesModelList.add(new TopStreetView_Model("Tafeu Cove, American Samoa", -14.253016, -170.689206));
                    placesModelList.add(new TopStreetView_Model("Cape Kri, Raja Ampat, Indonesia", -0.556114, 130.690997));
                    placesModelList.add(new TopStreetView_Model("Devil's Crown, Galapagos", -1.216466, -90.42215));
                    placesModelList.add(new TopStreetView_Model("Nelson Island Outside, Chagos", -5.683498, 72.325919));
                    placesModelList.add(new TopStreetView_Model("Milky Way / Submarine, Sekisei Lagoon, Ishigaki, Okinawa, Japan", 24.300251, 124.08625));
                    placesModelList.add(new TopStreetView_Model("Taketomi-south Shark Home, Taketomi, Okinawa, Japan", 24.284623, 124.078206));
                    placesModelList.add(new TopStreetView_Model("Nudi's Retreat, Indonesia", 1.486096, 125.24151));
                    placesModelList.add(new TopStreetView_Model("Jaco Island, Timor Leste", -8.441477, 127.312285));
                    placesModelList.add(new TopStreetView_Model("Rarotonga, Cook Islands", -21.203982, -159.837009));
                    placesModelList.add(new TopStreetView_Model("Benwood Wreck, Florida Keys, USA", 25.052668, -80.332617));
                    placesModelList.add(new TopStreetView_Model("Cozumel, Mexico", 20.338045, -87.027587));
                    placesModelList.add(new TopStreetView_Model("Horseshoe Bay, Bermuda", 32.244589, -64.822922));
                    placesModelList.add(new TopStreetView_Model("Three Rock, Green Island, Taiwan", 22.639565, 121.490911));
                    placesModelList.add(new TopStreetView_Model("Moresby Island Inside, Chagos, British Indian Ocean Territory", -5.243066, 71.825168));
                    placesModelList.add(new TopStreetView_Model("Jessie Beazley Reef, Philippines", 9.043864, 119.819872));
                    placesModelList.add(new TopStreetView_Model("Da Bai Sha, Green Island, Taiwan", 22.638232, 121.490526));
                    placesModelList.add(new TopStreetView_Model("PUE Coral Gardens, Cook Islands", -21.201181, -159.771712));
                    placesModelList.add(new TopStreetView_Model("Fengjie", 31.018497, 109.463989));
                    placesModelList.add(new TopStreetView_Model("Nuclear Outlet, Kenting National Park, Taiwan", 21.930845, 120.745026));
                    placesModelList.add(new TopStreetView_Model("Cheeca Rocks, Florida Keys, USA", 24.904503, -80.616652));
                    placesModelList.add(new TopStreetView_Model("Lizard Island, Great Barrier Reef", -14.685821, 145.442162));
                    placesModelList.add(new TopStreetView_Model("Hong Chai, Kenting National Park, Taiwan", 21.953572, 120.710359));
                    placesModelList.add(new TopStreetView_Model("Freeport, Grand Bahama", 26.488173, -78.657134));
                    placesModelList.add(new TopStreetView_Model("São Francisco River", -9.1920377, -38.2939128));
                    placesModelList.add(new TopStreetView_Model("Champion Island East, Galápagos", -1.238894, -90.384239));
                    placesModelList.add(new TopStreetView_Model("Amur River", 52.6094914, 139.6296251));
                    placesModelList.add(new TopStreetView_Model("Nile River", 25.6978888, 32.6360721));
                    placesModelList.add(new TopStreetView_Model("Yukon River", 63.5810451, -149.6608154));
                    placesModelList.add(new TopStreetView_Model("Paraná Miní River", 41.890210, 12.492231));
                    placesModelList.add(new TopStreetView_Model("Mekong River", 16.342651, 103.081023));
                    placesModelList.add(new TopStreetView_Model("Yangtze River", 30.514649, 112.868837));
                    placesModelList.add(new TopStreetView_Model("Ob River", 61.2359639, 73.1181049));
                    placesModelList.add(new TopStreetView_Model("Madeira River", -5.584739, -61.0213124));
                    placesModelList.add(new TopStreetView_Model("Murray River", -36.0415361, 146.3584699));
                    placesModelList.add(new TopStreetView_Model("Yellow River Delta", 43.152714, -91.513799));
                    placesModelList.add(new TopStreetView_Model("Mississippi River", 45.5407679, -94.1514634));
                    placesModelList.add(new TopStreetView_Model("Tocantins River", -8.9827251, -48.1777573));
                    placesModelList.add(new TopStreetView_Model("Yenisei River", 53.7304248, 91.4961391));
                    placesModelList.add(new TopStreetView_Model("Volga River", 51.934396, 41.75655));
                    break;
                case "Mountain":
                    tvTitle.setText(R.string.txt_mountains);
                    placesModelList.add(new TopStreetView_Model("Mount Everest", 27.994511, 86.828381));
                    placesModelList.add(new TopStreetView_Model("K2 Mount", 35.7412835, 76.5114958));
                    placesModelList.add(new TopStreetView_Model("Kanchenjunga", 27.7024914, 88.147535));
                    placesModelList.add(new TopStreetView_Model("Nanga Parbat", 35.3333222, 74.5810094));
                    placesModelList.add(new TopStreetView_Model("Kilimanjaro", -3.0674314, 37.3556185));
                    placesModelList.add(new TopStreetView_Model("Table Mountain", -33.9581394, 18.4056875));
                    placesModelList.add(new TopStreetView_Model("Matterhron", 45.9917552, 7.7102036));
                    placesModelList.add(new TopStreetView_Model("Mount Hakkoda", 40.669528, 140.880223));
                    placesModelList.add(new TopStreetView_Model("Chimborazo", -1.4729278, -78.8385925));
                    placesModelList.add(new TopStreetView_Model("Mont Blanc", 45.833611, 6.865));
                    placesModelList.add(new TopStreetView_Model("Mount Iwaki", 40.65594, 140.303047));
                    placesModelList.add(new TopStreetView_Model("Aconcagua", -32.653179, -70.010864));
                    placesModelList.add(new TopStreetView_Model("Mount Kashima Yarigatake", 36.623194, 137.746316));
                    placesModelList.add(new TopStreetView_Model("Mount Akagi", 36.560392, 139.193277));
                    placesModelList.add(new TopStreetView_Model("Mount Notori", 35.621388, 138.236694));
                    placesModelList.add(new TopStreetView_Model("Cho Oyu", 28.0999996, 86.6499974));
                    placesModelList.add(new TopStreetView_Model("Mount Karasawa", 36.295057, 137.664726));
                    placesModelList.add(new TopStreetView_Model("Hotaka Mountains", 36.294379, 137.664148));
                    placesModelList.add(new TopStreetView_Model("Mount Aino", 35.648726, 138.228499));
                    placesModelList.add(new TopStreetView_Model("Mount Kurikoma", 38.959434, 140.792436));
                    placesModelList.add(new TopStreetView_Model("Makalu", 27.8856424, 87.0915968));
                    placesModelList.add(new TopStreetView_Model("Mount Takao", 35.624604, 139.243077));
                    placesModelList.add(new TopStreetView_Model("Tanzawa Mountains", 35.47047, 139.163379));
                    placesModelList.add(new TopStreetView_Model("Mount Aka", 35.971305, 138.370344));
                    placesModelList.add(new TopStreetView_Model("Mount Chuo", 27.07321, 142.218466));
                    placesModelList.add(new TopStreetView_Model("Mount Haruna", 36.476889, 138.878258));
                    placesModelList.add(new TopStreetView_Model("Mount Tsukuba", 36.226302, 140.100818));
                    placesModelList.add(new TopStreetView_Model("Mount Yarigatake", 36.342066, 137.64765));
                    break;
                case "Forest":
                    tvTitle.setText(R.string.txt_jungle_safaris);
                    placesModelList.add(new TopStreetView_Model("Mountain Zebra National Park", -32.102187,25.417349));
                    placesModelList.add(new TopStreetView_Model("Black Forest", 47.8445341, 7.9329069));
                    placesModelList.add(new TopStreetView_Model("The Tongass National Forest", 55.34975, -131.606925));
                    placesModelList.add(new TopStreetView_Model("Sundarbans", 21.949727, 89.18333));
                    placesModelList.add(new TopStreetView_Model("Biosphere Reserve", 19.850951, -87.639343));
                    placesModelList.add(new TopStreetView_Model("Loosehanger", 50.950576,-1.677872));
                    placesModelList.add(new TopStreetView_Model("Sinharaja Forest Reserve", 6.389722, 80.501389));
                    placesModelList.add(new TopStreetView_Model("Arashiyama Bamboo Grove", 35.017042, 135.671874));
                    placesModelList.add(new TopStreetView_Model("High Corner", 50.89016,-1.718173));
                    placesModelList.add(new TopStreetView_Model("Tsingy de Bemaraha National Park", -18.89767, 44.82981));
                    placesModelList.add(new TopStreetView_Model("Kruger National Park", -23.8935,31.546686));
                    placesModelList.add(new TopStreetView_Model("Great Otway National Park", -38.791218, 143.5419233));
                    placesModelList.add(new TopStreetView_Model("Milkham inclosure", 50.886174,-1.708976));
                    placesModelList.add(new TopStreetView_Model("Mossy Forest", 4.524223, 101.381927));
                    placesModelList.add(new TopStreetView_Model("Humboldt Redwoods State Park", 40.283723, -123.890317));
                    placesModelList.add(new TopStreetView_Model("North Weirs", 50.817399,-1.583032));
                    break;
                case "Desert":
                    tvTitle.setText(R.string.txt_deserts);
                    placesModelList.add(new TopStreetView_Model("Direction Island", -12.0911, 96.885288));
                    placesModelList.add(new TopStreetView_Model("Cocos Lagoon", -12.192331, 96.859568));
                    placesModelList.add(new TopStreetView_Model("Antarctica", -75.25097, -0.07139));
                    placesModelList.add(new TopStreetView_Model("Boulder track", -10.549466, 105.638712));
                    placesModelList.add(new TopStreetView_Model("Sahara Desert", 29.2052452, 25.5435246));
                    placesModelList.add(new TopStreetView_Model("Horsburgh Island", -12.081945, 96.845105));
                    placesModelList.add(new TopStreetView_Model("The Dales", -10.478835, 105.55961));
                    placesModelList.add(new TopStreetView_Model("Arabian LN", 32.66832, -114.66847));
                    placesModelList.add(new TopStreetView_Model("Margaret Knoll Lookout", -10.477045, 105.684215));
                    placesModelList.add(new TopStreetView_Model("Cocos Airport", -12.193344, 96.834912));
                    placesModelList.add(new TopStreetView_Model("Kalahari CBDC ", -27.2815253, 23.0847078));
                    placesModelList.add(new TopStreetView_Model("Martin Point", -10.469337, 105.557799));
                    placesModelList.add(new TopStreetView_Model("Great Basin Desert", 36.191879, -115.279388));
                    placesModelList.add(new TopStreetView_Model("West Island", -12.196077, 96.863094));
                    placesModelList.add(new TopStreetView_Model("The Grotto", -10.42308, 105.701954));
                    placesModelList.add(new TopStreetView_Model("Sonoran Desert", 32.6286125, -116.0819473));
                    placesModelList.add(new TopStreetView_Model("Golf Course Lookout", -10.423823, 105.698357));
                    placesModelList.add(new TopStreetView_Model("Thar Desert", 26.47167, 74.5670213));
                    placesModelList.add(new TopStreetView_Model("Winifred Beach", -10.496783, 105.546169));
                    placesModelList.add(new TopStreetView_Model("Liwa Desert Oasis", 23.067666, 53.788053));
                    placesModelList.add(new TopStreetView_Model("The Blowholes", -10.514544, 105.622572));
                    placesModelList.add(new TopStreetView_Model("Liwa Desert - South", 23.148055, 53.732017));
                    placesModelList.add(new TopStreetView_Model("Lily Beach", -10.466893, 105.711355));
                    placesModelList.add(new TopStreetView_Model("Liwa Desert - South West", 23.068008, 53.782982));
                    placesModelList.add(new TopStreetView_Model("Merrial Beach Track", -10.474312, 105.559443));
                    placesModelList.add(new TopStreetView_Model("Greta Beach", -10.50195, 105.674651));
                    placesModelList.add(new TopStreetView_Model("Dolly Beach", -10.520641, 105.675738));
                    placesModelList.add(new TopStreetView_Model("The Dales", -10.483353, 105.554388));
                    placesModelList.add(new TopStreetView_Model("Ethel Beach", -10.463977, 105.708019));
                    placesModelList.add(new TopStreetView_Model("West White Beach", -10.460865, 105.579462));
                    break;
                case "AirPorts":
                    tvTitle.setText(R.string.txt_air_ports);
                    placesModelList.add(new TopStreetView_Model("O'Hare International Airport", 41.9741625, -87.9073214));
                    placesModelList.add(new TopStreetView_Model("Tokyo Haneda International Airport", 35.5506372, 139.7888255));
                    placesModelList.add(new TopStreetView_Model("Hong Kong International Airport", 22.308889, 113.914722));
                    placesModelList.add(new TopStreetView_Model("Los Angeles International Airport", 33.9428607, -118.4103077));
                    placesModelList.add(new TopStreetView_Model("Shanghai Pudong International Airport", 31.1443439, 121.808273));
                    placesModelList.add(new TopStreetView_Model("Beijing Capital International Airport", 40.0798573, 116.6031121));
                    placesModelList.add(new TopStreetView_Model("Dallas/Fort Worth International Airport", 32.8982616, -97.0403608));
                    placesModelList.add(new TopStreetView_Model("Seattle-Tacoma International Airport", 47.4422254, -122.30016));
                    placesModelList.add(new TopStreetView_Model("Chhatrapati Shivaji International Airport", 19.0979433, 72.8746061));
                    placesModelList.add(new TopStreetView_Model("Amsterdam Airport Schiphol", 52.3083769, 4.7656394));
                    placesModelList.add(new TopStreetView_Model("Chengdu Shuangliu International Airport", 30.5794432, 103.9541533));
                    placesModelList.add(new TopStreetView_Model("Frankfurt Airport", 50.0452679, 8.5584559));
                    placesModelList.add(new TopStreetView_Model("Istanbul Atatürk Airport", 40.9825775, 28.817171));
                    placesModelList.add(new TopStreetView_Model("McCarran International Airport", 36.082, -115.139831));
                    placesModelList.add(new TopStreetView_Model("Guangzhou Baiyun International Airport", 23.3959079, 113.3079699));
                    placesModelList.add(new TopStreetView_Model("Charles de Gaulle Airport", 49.0155117, 2.5454583));
                    placesModelList.add(new TopStreetView_Model("Madrid Barajas Airport", 40.4910945, -3.5925065));
                    placesModelList.add(new TopStreetView_Model("John F. Kennedy International Airport", 40.6476861, -73.7759042));
                    placesModelList.add(new TopStreetView_Model("Incheon International Airport", 37.448827, 126.451403));
                    placesModelList.add(new TopStreetView_Model("Singapore Changi Airport", 1.3580556, 103.9105556));
                    placesModelList.add(new TopStreetView_Model("Kuala Lumpur International Airport", 2.7476377, 101.7133566));
                    placesModelList.add(new TopStreetView_Model("Dubai International Airport", 25.2482833, 55.3614967));
                    placesModelList.add(new TopStreetView_Model("Denver International Airport", 39.8560936, -104.6737717));
                    placesModelList.add(new TopStreetView_Model("San Francisco International Airport", 37.6239079, -122.3815924));
                    placesModelList.add(new TopStreetView_Model("Hartsfield–Jackson Atlanta International Airport", 33.6407334, -84.4277319));
                    placesModelList.add(new TopStreetView_Model("Suvarnabhumi Airport", 13.6899991, 100.7501124));
                    placesModelList.add(new TopStreetView_Model("Soekarno–Hatta International Airport", -6.1186197, 106.6652698));
                    placesModelList.add(new TopStreetView_Model("Changi Airport Singapore", 1.3580556, 103.9105556));
                    placesModelList.add(new TopStreetView_Model("Indira Gandhi International Airport", 28.5561624, 77.0999578));
                    placesModelList.add(new TopStreetView_Model("Heathrow Airport", 51.4699514, -0.4497753));
                    break;
                case "RailwayStations":
                    tvTitle.setText(R.string.txt_railway_stations);
                    placesModelList.add(new TopStreetView_Model("Amsterdam Centraal", 52.3791283, 4.900272));
                    placesModelList.add(new TopStreetView_Model("Gare de Lyon", 48.8443046, 2.3743773));
                    placesModelList.add(new TopStreetView_Model("Stuttgart Hauptbahnhof", 48.784171, 9.1789213));
                    placesModelList.add(new TopStreetView_Model("London Victoria", 51.4949103, -0.1432954));
                    placesModelList.add(new TopStreetView_Model("Köln Hauptbahnhof", 50.9430074, 6.9588518));
                    placesModelList.add(new TopStreetView_Model("Helsingin Päärautatieasema", 60.1718729, 24.9414217));
                    placesModelList.add(new TopStreetView_Model("Torino Porta Nuova", 45.0611946, 7.6780714));
                    placesModelList.add(new TopStreetView_Model("Utrecht Centraal", 52.0890738, 5.1105524));
                    placesModelList.add(new TopStreetView_Model("London Liverpool Street", 51.5187516, -0.0814374));
                    placesModelList.add(new TopStreetView_Model("Nürnberg Hauptbahnhof", 49.4454137, 11.0836134));
                    placesModelList.add(new TopStreetView_Model("Berlin Hauptbahnhof", 52.5250839, 13.369402));
                    placesModelList.add(new TopStreetView_Model("Firenze Santa Maria Novella", 43.7760037, 11.2480901));
                    placesModelList.add(new TopStreetView_Model("Haussmann-Saint-Lazare", 48.8958514, 2.3738871));
                    placesModelList.add(new TopStreetView_Model("Milano Centrale", 45.4892494, 9.2081271));
                    placesModelList.add(new TopStreetView_Model("London Euston", 51.52814, -0.1339));
                    placesModelList.add(new TopStreetView_Model("Barcelona Sants", 41.3790174, 2.1422508));
                    placesModelList.add(new TopStreetView_Model("Venezia Santa Lucia", 45.441162, 12.3208789));
                    placesModelList.add(new TopStreetView_Model("München Hauptbahnhof", 48.139922, 11.5591378));
                    placesModelList.add(new TopStreetView_Model("Glasgow Central", 55.8598517, -4.2575043));
                    placesModelList.add(new TopStreetView_Model("Birmingham New Street", 52.4778893, -1.8989573));
                    placesModelList.add(new TopStreetView_Model("München-Pasing", 48.1500343, 11.4621345));
                    placesModelList.add(new TopStreetView_Model("Copenhagen Central Station", 55.6727611, 12.5649245));
                    placesModelList.add(new TopStreetView_Model("Zürich Hauptbahnhof", 47.3781203, 8.5395607));
                    placesModelList.add(new TopStreetView_Model("Berlin-Lichtenberg", 52.5101023, 13.4961636));
                    placesModelList.add(new TopStreetView_Model("London Paddington", 51.5163429, -0.1767959));
                    placesModelList.add(new TopStreetView_Model("Berlin Ostbahnhof", 52.5250839, 13.369402));
                    placesModelList.add(new TopStreetView_Model("London St Pancras", 51.5339464, -0.1263803));
                    placesModelList.add(new TopStreetView_Model("Roma Termini", 41.9008904, 12.5014725));
                    placesModelList.add(new TopStreetView_Model("Hamburg-Altona", 53.5534, 9.9349617));
                    placesModelList.add(new TopStreetView_Model("Frankfurt (Main) Hauptbahnhof", 50.107065, 8.6623369));
                    placesModelList.add(new TopStreetView_Model("Lausanne", 46.516407, 6.6286659));
                    placesModelList.add(new TopStreetView_Model("Gare de la Part-Dieu", 45.760105, 4.8609652));
                    placesModelList.add(new TopStreetView_Model("Hamburg Hauptbahnhof", 53.5528457, 10.0066839));
                    placesModelList.add(new TopStreetView_Model("Gare de l'Est", 48.8776325, 2.3609696));
                    placesModelList.add(new TopStreetView_Model("Gare de Juvisy", 48.7652647, 2.410684));
                    placesModelList.add(new TopStreetView_Model("Gare de Châtelet - Les Halles", 48.861893, 2.347));
                    placesModelList.add(new TopStreetView_Model("Milano Cadorna", 45.4685944, 9.1751245));
                    placesModelList.add(new TopStreetView_Model("London King's Cross", 51.5316014, -0.1242503));
                    placesModelList.add(new TopStreetView_Model("Gare du Nord", 48.8804161, 2.3551932));
                    placesModelList.add(new TopStreetView_Model("Winterthur", 47.500005, 8.7232741));
                    break;
                case "ExploringWorld":
                    tvTitle.setText(R.string.txt_exploring_world);
                    placesModelList.add(new TopStreetView_Model("Main Street", -26.2074744, 28.0363871));
                    placesModelList.add(new TopStreetView_Model("Mount Zebra National Park, Salpeterkop trail", -32.1101556, 25.4402601));
                    placesModelList.add(new TopStreetView_Model("Andrew Kerr", 22.3368633, 31.6256717));
                    placesModelList.add(new TopStreetView_Model("Abu Simbel Temples", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetView_Model("Wild Coast Meander", -32.4443146, 28.6712164));
                    placesModelList.add(new TopStreetView_Model("Diagonal Street", -26.2055954, 28.0355892));
                    placesModelList.add(new TopStreetView_Model("Mount Zebra National Park, Cheetah Tracking Wilder", -32.102144, 25.4173855));
                    placesModelList.add(new TopStreetView_Model("Gamkaberg Nature Reserve, Ou Kraal Trail", -33.7307424, 21.9131958));
                    placesModelList.add(new TopStreetView_Model("Daniel SZYSZ", 22.3369639, 31.6256563));
                    placesModelList.add(new TopStreetView_Model("Anysberg Nature Reserve, Land se Kloof", -33.5048112, 20.576778));
                    placesModelList.add(new TopStreetView_Model("Tyrwhitt Avenue", -26.1450141, 28.0423329));
                    placesModelList.add(new TopStreetView_Model("Gideon Scheepers Monument", -32.2309201, 24.5180615));
                    placesModelList.add(new TopStreetView_Model("Woongyoung Park", 22.3373297, 31.6257865));
                    placesModelList.add(new TopStreetView_Model("Levite Chaumba", -17.8311917, 31.040665));
                    placesModelList.add(new TopStreetView_Model("Addo Elephant Main Rest Camp, PPC Trail", -33.4429831, 25.7516306));
                    placesModelList.add(new TopStreetView_Model("Anysberg Self Catering Cottages", -33.4657104, 20.5871439));
                    placesModelList.add(new TopStreetView_Model("Hole in the Wall Hiking Trail", -32.0400328, 29.1087185));
                    placesModelList.add(new TopStreetView_Model("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetView_Model("Kerk Street", -26.2024091, 28.0427613));
                    placesModelList.add(new TopStreetView_Model("Eerstefontein Trail", -32.274515, 24.5163145));
                    placesModelList.add(new TopStreetView_Model("Treetop Walk", -22.2007755, 29.3542062));
                    placesModelList.add(new TopStreetView_Model("Viviane Fontes", 22.3369577, 31.6259754));
                    placesModelList.add(new TopStreetView_Model("Addo Elephant National Park, Doringkloof Trail", -33.3480677, 25.733466));
                    placesModelList.add(new TopStreetView_Model("The Crag Lizard Trail", -32.2647392, 24.4866573));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Acacia Trail", -34.0728894, 20.4497461));
                    placesModelList.add(new TopStreetView_Model("Cederberg Wilderness Area", -32.543646, 19.32927159999997));
                    placesModelList.add(new TopStreetView_Model("Confluence Lookout Point", -22.2006143, 29.3705883));
                    placesModelList.add(new TopStreetView_Model("Noord Street", -26.1982695, 28.0444091));
                    placesModelList.add(new TopStreetView_Model("Mtentu Viewpoint", -31.2386119, 30.0325065));
                    placesModelList.add(new TopStreetView_Model("Garden Route National Park: Wilderness", -33.9674297, 22.596823900000004));
                    placesModelList.add(new TopStreetView_Model("Tan Mas", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetView_Model("Tamboti trail", -25.2946616, 27.1690196));
                    placesModelList.add(new TopStreetView_Model("Mapungubwe Hill", -22.2138856, 29.386949));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Termite Trail", -34.072885, 20.4483871));
                    placesModelList.add(new TopStreetView_Model("Nelson Mandela Capture site ", -29.4681918, 30.17038260000004));
                    placesModelList.add(new TopStreetView_Model("Madikwe River Lodge", -24.6840063, 26.423116));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Aloe Trail", -34.0798594, 20.452325));
                    placesModelList.add(new TopStreetView_Model("Madikwe Game Reserve, Tshukudu Dam", -24.727859, 26.366354));
                    placesModelList.add(new TopStreetView_Model("The Cradle of Humankind Visitor Centre Maropeng", -25.966763, 27.6626412));
                    placesModelList.add(new TopStreetView_Model("Fossil Trail", -32.3332878, 22.4978028));
                    placesModelList.add(new TopStreetView_Model("Wally's Cave", -33.9375679, 18.3897086));
                    placesModelList.add(new TopStreetView_Model("Strandloper and Mkhambathi Falls", -31.2743474, 30.023418));
                    placesModelList.add(new TopStreetView_Model("Modikela Safari Wilderness Trail", -24.4142572, 27.5386582));
                    placesModelList.add(new TopStreetView_Model("Jordi Vallverdu ", 22.3368613, 31.6257746));
                    placesModelList.add(new TopStreetView_Model("Royal National park Drakenberg", -28.7525581, 28.89430470000002));
                    placesModelList.add(new TopStreetView_Model("kruger national park ", -23.620009, 31.650487));
                    placesModelList.add(new TopStreetView_Model("Twee Rivieren", -26.4734049, 20.6126321));
                    placesModelList.add(new TopStreetView_Model("Bill Harrop's Original Balloon Safaris", -25.8139098, 27.7384832));
                    placesModelList.add(new TopStreetView_Model("Klipspringer Trail", -28.5889726, 20.3251937));
                    placesModelList.add(new TopStreetView_Model("Augrabies Falls", -28.5917509, 20.3405535));
                    placesModelList.add(new TopStreetView_Model("Pipe Track", -33.9478324, 18.394588));
                    placesModelList.add(new TopStreetView_Model("Hettema Trail - Sendelingsdrif", -28.1337012, 16.9107528));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Grysbok Circle", -33.9742738, 20.8207797));
                    placesModelList.add(new TopStreetView_Model("Golden Gate Highlands National Park", -28.5031212, 28.6203292));
                    placesModelList.add(new TopStreetView_Model("Madikwe Game Reserve, Safari drive", -24.6873997, 26.4192038));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Fonteintjiesbos ", -33.9818368, 20.8134073));
                    placesModelList.add(new TopStreetView_Model("Lion's Head Loop", -33.9284663, 18.3946677));
                    placesModelList.add(new TopStreetView_Model("Sterkfontein Caves", -26.0164956, 27.734291));
                    placesModelList.add(new TopStreetView_Model("Bakubung back Valley wilderness trail", -25.3252719, 27.0206081));
                    placesModelList.add(new TopStreetView_Model("Mokala National Park Bushwalk", -29.1599739, 24.3182358));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Bushbuck Trail", -33.9844697, 20.823392));
                    placesModelList.add(new TopStreetView_Model("Otter Trail", -34.021006, 23.8800573));
                    placesModelList.add(new TopStreetView_Model("Sun City", -25.3373742, 27.0870538));
                    placesModelList.add(new TopStreetView_Model("Sea Point Promenade", -33.8994868, 18.4037492));
                    placesModelList.add(new TopStreetView_Model("Heaviside Hiking Trail", -30.7236629, 17.5141112));
                    placesModelList.add(new TopStreetView_Model("V&A Waterfront", -33.9044883, 18.4207404));
                    placesModelList.add(new TopStreetView_Model("Lesedi Cultural Village", -25.8379163, 27.8810604));
                    placesModelList.add(new TopStreetView_Model("Skilpad Trail", -30.1661696, 17.7903668));
                    placesModelList.add(new TopStreetView_Model("Elandsberg Trail", -32.1942655, 19.9485041));
                    placesModelList.add(new TopStreetView_Model("Southern African Large Telescope", -32.3758799, 20.8114466));
                    placesModelList.add(new TopStreetView_Model("Pointer’s Hiking Trail", -32.341016, 22.4957517));
                    placesModelList.add(new TopStreetView_Model("Mokala National Park, Rock Engraving Trail", -29.1045866, 24.2638248));
                    placesModelList.add(new TopStreetView_Model("Korhan Trail", -30.1643018, 17.8041342));
                    placesModelList.add(new TopStreetView_Model("Agterstefontein Trail", -32.291762, 24.4852518));
                    placesModelList.add(new TopStreetView_Model("Rest Camp Trail", -32.3331512, 22.4930986));
                    placesModelList.add(new TopStreetView_Model("Helskloof Hiking Trail", -28.3187658, 16.9807969));
                    placesModelList.add(new TopStreetView_Model("Cape Town, Western Cape", -33.901501, 18.4213349));
                    placesModelList.add(new TopStreetView_Model("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetView_Model("Bossie Trail", -32.3345355, 22.4965986));
                    placesModelList.add(new TopStreetView_Model("Chapman's Peak Lookout", -34.0884531, 18.359611));
                    placesModelList.add(new TopStreetView_Model("Marsa Shagra Village ", 25.2458526, 34.7945753));
                    placesModelList.add(new TopStreetView_Model("Doreen Falls", -28.9623088, 29.2029319));
                    placesModelList.add(new TopStreetView_Model("Hole in the Wall Hiking", -32.0349255, 29.1122834));
                    placesModelList.add(new TopStreetView_Model("Table mountain National park", -33.9731969, 18.3927267));
                    break;
                    case "Exploring Africa":
                    tvTitle.setText(String.valueOf("Exploring Africa"));
                    placesModelList.add(new TopStreetView_Model("Hole in the Wall Hiking", -32.0349255, 29.1122834));
                    placesModelList.add(new TopStreetView_Model("Strandloper and Mkhambathi Falls", -31.2743474, 30.023418));
                    placesModelList.add(new TopStreetView_Model("Lesedi Cultural Village", -25.8379163, 27.8810604));
                    placesModelList.add(new TopStreetView_Model("Doreen Falls", -28.9623088, 29.2029319));
                    placesModelList.add(new TopStreetView_Model("Agterstefontein Trail", -32.291762, 24.4852518));
                    placesModelList.add(new TopStreetView_Model("Sun City", -25.3373742, 27.0870538));
                    placesModelList.add(new TopStreetView_Model("Golden Gate Highlands National Park", -28.5031212, 28.6203292));
                    placesModelList.add(new TopStreetView_Model("Modikela Safari Wilderness Trail", -24.4142572, 27.5386582));
                    placesModelList.add(new TopStreetView_Model("Madikwe Game Reserve, Safari drive", -24.6873997, 26.4192038));
                    placesModelList.add(new TopStreetView_Model("Madikwe Game Reserve, Tshukudu Dam", -24.727859, 26.366354));
                    placesModelList.add(new TopStreetView_Model("Mtentu Viewpoint", -31.2386119, 30.0325065));
                    placesModelList.add(new TopStreetView_Model("The Crag Lizard Trail", -32.2647392, 24.4866573));
                    placesModelList.add(new TopStreetView_Model("Eerstefontein Trail", -32.274515, 24.5163145));
                    placesModelList.add(new TopStreetView_Model("Tyrwhitt Avenue", -26.1450141, 28.0423329));
                    placesModelList.add(new TopStreetView_Model("Main Street", -26.2074744, 28.0363871));
                    placesModelList.add(new TopStreetView_Model("Mount Zebra National Park, Salpeterkop trail", -32.1101556, 25.4402601));
                    placesModelList.add(new TopStreetView_Model("Mount Zebra National Park, Cheetah Tracking Wilder", -32.102144, 25.4173855));
                    placesModelList.add(new TopStreetView_Model("Gideon Scheepers Monument", -32.2309201, 24.5180615));
                    placesModelList.add(new TopStreetView_Model("Hole in the Wall Hiking Trail", -32.0400328, 29.1087185));
                    placesModelList.add(new TopStreetView_Model("Treetop Walk", -22.2007755, 29.3542062));
                    placesModelList.add(new TopStreetView_Model("Confluence Lookout Point", -22.2006143, 29.3705883));
                    placesModelList.add(new TopStreetView_Model("Mapungubwe Hill", -22.2138856, 29.386949));
                    placesModelList.add(new TopStreetView_Model("Madikwe River Lodge", -24.6840063, 26.423116));
                    placesModelList.add(new TopStreetView_Model("Bakubung back Valley wilderness trail", -25.3252719, 27.0206081));
                    placesModelList.add(new TopStreetView_Model("Bill Harrop's Original Balloon Safaris", -25.8139098, 27.7384832));
                    placesModelList.add(new TopStreetView_Model("The Cradle of Humankind Visitor Centre Maropeng", -25.966763, 27.6626412));
                    placesModelList.add(new TopStreetView_Model("Sterkfontein Caves", -26.0164956, 27.734291));
                    placesModelList.add(new TopStreetView_Model("Elandsberg Trail", -32.1942655, 19.9485041));
                    placesModelList.add(new TopStreetView_Model("Bossie Trail", -32.3345355, 22.4965986));
                    placesModelList.add(new TopStreetView_Model("Rest Camp Trail", -32.3331512, 22.4930986));
                    placesModelList.add(new TopStreetView_Model("Fossil Trail", -32.3332878, 22.4978028));
                    placesModelList.add(new TopStreetView_Model("Pointer’s Hiking Trail", -32.341016, 22.4957517));
                    placesModelList.add(new TopStreetView_Model("Mokala National Park, Rock Engraving Trail", -29.1045866, 24.2638248));
                    placesModelList.add(new TopStreetView_Model("Mokala National Park Bushwalk", -29.1599739, 24.3182358));
                    placesModelList.add(new TopStreetView_Model("Southern African Large Telescope", -32.3758799, 20.8114466));
                    placesModelList.add(new TopStreetView_Model("Twee Rivieren", -26.4734049, 20.6126321));
                    placesModelList.add(new TopStreetView_Model("Klipspringer Trail", -28.5889726, 20.3251937));
                    placesModelList.add(new TopStreetView_Model("Augrabies Falls", -28.5917509, 20.3405535));
                    placesModelList.add(new TopStreetView_Model("Hettema Trail - Sendelingsdrif", -28.1337012, 16.9107528));
                    placesModelList.add(new TopStreetView_Model("Helskloof Hiking Trail", -28.3187658, 16.9807969));
                    placesModelList.add(new TopStreetView_Model("Korhan Trail", -30.1643018, 17.8041342));
                    placesModelList.add(new TopStreetView_Model("Skilpad Trail", -30.1661696, 17.7903668));
                    placesModelList.add(new TopStreetView_Model("Heaviside Hiking Trail", -30.7236629, 17.5141112));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Bushbuck Trail", -33.9844697, 20.823392));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Fonteintjiesbos ", -33.9818368, 20.8134073));
                    placesModelList.add(new TopStreetView_Model("Grootvadersbosch Nature Reserve, Grysbok Circle", -33.9742738, 20.8207797));
                    placesModelList.add(new TopStreetView_Model("kruger national park ", -23.620009, 31.650487));
                    placesModelList.add(new TopStreetView_Model("Royal National park Drakenberg", -28.7525581, 28.89430470000002));
                    placesModelList.add(new TopStreetView_Model("Table mountain National park", -33.9731969, 18.3927267));
                    placesModelList.add(new TopStreetView_Model("Nelson Mandela Capture site ", -29.4681918, 30.17038260000004));
                    placesModelList.add(new TopStreetView_Model("Garden Route National Park: Wilderness", -33.9674297, 22.596823900000004));
                    placesModelList.add(new TopStreetView_Model(" Cederberg Wilderness Area", -32.543646, 19.32927159999997));
                    placesModelList.add(new TopStreetView_Model("Benos Angaka Bonyoma", 0.2136714, 16.9848245));
                    placesModelList.add(new TopStreetView_Model("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetView_Model(" Levite Chaumba", -17.8311917, 31.040665));
                    placesModelList.add(new TopStreetView_Model("Daniel SZYSZ", 22.3369639, 31.6256563));
                    placesModelList.add(new TopStreetView_Model("Andrew Kerr", 22.3368633, 31.6256717));
                    placesModelList.add(new TopStreetView_Model("Abu Simbel Temples", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetView_Model(" Woongyoung Park", 22.3373297, 31.6257865));
                    placesModelList.add(new TopStreetView_Model("Viviane Fontes", 22.3369577, 31.6259754));
                    placesModelList.add(new TopStreetView_Model(" Tan Mas", 22.3372319, 31.625799));
                    placesModelList.add(new TopStreetView_Model("Jean-Marc Perfetti", -8.783195, 34.508523));
                    placesModelList.add(new TopStreetView_Model("Marsa Shagra Village ", 25.2458526, 34.7945753));
                    placesModelList.add(new TopStreetView_Model("Jordi Vallverdu ", 22.3368613, 31.6257746));
                    placesModelList.add(new TopStreetView_Model("Sea Point Promenade", -33.8994868, 18.4037492));
                    placesModelList.add(new TopStreetView_Model("Chapman's Peak Lookout", -34.0884531, 18.359611));
                    placesModelList.add(new TopStreetView_Model("Cape Town, Western Cape", -33.901501, 18.4213349));
                    placesModelList.add(new TopStreetView_Model("V&A Waterfront", -33.9044883, 18.4207404));
                    placesModelList.add(new TopStreetView_Model("Lion's Head Loop", -33.9284663, 18.3946677));
                    placesModelList.add(new TopStreetView_Model("Wally's Cave", -33.9375679, 18.3897086));
                    placesModelList.add(new TopStreetView_Model("Pipe Track", -33.9478324, 18.394588));
                    placesModelList.add(new TopStreetView_Model("Otter Trail", -34.021006, 23.8800573));
                    placesModelList.add(new TopStreetView_Model("Tamboti trail", -25.2946616, 27.1690196));
                    placesModelList.add(new TopStreetView_Model("Addo Elephant National Park, Doringkloof Trail", -33.3480677, 25.733466));
                    placesModelList.add(new TopStreetView_Model("Addo Elephant Main Rest Camp, PPC Trail", -33.4429831, 25.7516306));
                    placesModelList.add(new TopStreetView_Model("Wild Coast Meander", -32.4443146, 28.6712164));
                    placesModelList.add(new TopStreetView_Model("Diagonal Street", -26.2055954, 28.0355892));
                    placesModelList.add(new TopStreetView_Model("Kerk Street", -26.2024091, 28.0427613));
                    placesModelList.add(new TopStreetView_Model("Noord Street", -26.1982695, 28.0444091));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Aloe Trail", -34.0798594, 20.452325));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Termite Trail", -34.072885, 20.4483871));
                    placesModelList.add(new TopStreetView_Model("Bontebok National Park, Acacia Trail", -34.0728894, 20.4497461));
                    placesModelList.add(new TopStreetView_Model("Anysberg Self Catering Cottages", -33.4657104, 20.5871439));
                    placesModelList.add(new TopStreetView_Model("Anysberg Nature Reserve, Land se Kloof", -33.5048112, 20.576778));
                    placesModelList.add(new TopStreetView_Model("Gamkaberg Nature Reserve, Ou Kraal Trail", -33.7307424, 21.9131958));
                    break;
                case "Ocean":
                    tvTitle.setText(String.valueOf("Ocean"));
                    placesModelList.add(new TopStreetView_Model("Coral Gardens, Palmyra Atoll, USA",5.885806,-162.060135 ));
                    placesModelList.add(new TopStreetView_Model("Crazy Corals, Palmyra Atoll, USA", 5.885904,-162.12561));
                    placesModelList.add(new TopStreetView_Model("Holei, Palmyra Atoll, USA", 5.866448,-162.067535));
                    placesModelList.add(new TopStreetView_Model("Jurassic Park, Palmyra Atoll, USA",5.858315,-162.101257 ));
                    placesModelList.add(new TopStreetView_Model("Mega Jacks, Palmyra Atoll, USA",5.864789,-162.14032));
                    placesModelList.add(new TopStreetView_Model("Melon headed whales, Palmyra Atoll, USA",5.859013,-162.104696 ));
                    placesModelList.add(new TopStreetView_Model("Penguin Spit, Palmyra Atoll, USA", 5.87155,-162.110352));
                    placesModelList.add(new TopStreetView_Model("Yonaguni Monument, Yonaguni, Okinawa, Japan",24.435919,123.011134 ));
                    placesModelList.add(new TopStreetView_Model("Komodo Islands",-8.737039,119.412259 ));
                    placesModelList.add(new TopStreetView_Model("Nishibama, Aka Island, Okinawa, Japan",26.202737,127.289096 ));
                    placesModelList.add(new TopStreetView_Model("The Channel, Palmyra Atoll, USA",5.872685,-162.111947 ));
                    placesModelList.add(new TopStreetView_Model("Giant Potato, Nagura Bay, Ishigaki, Okinawa, Japan",24.399209,124.117781 ));
                    placesModelList.add(new TopStreetView_Model("Yamada Point, Onna, Okinawa, Japan",26.441502,127.790058 ));
                    placesModelList.add(new TopStreetView_Model("Aharen Beach, Tokashiki, Okinawa, Japan",26.168867,127.343795 ));
                    placesModelList.add(new TopStreetView_Model("Agariushi / Gahi Island, Zamami, Okinawa, Japan",26.21729,127.310158 ));
                    placesModelList.add(new TopStreetView_Model("Taketomi-south Shark Home, Taketomi, Okinawa, Japan",24.284623,124.078206 ));
                    placesModelList.add(new TopStreetView_Model("Milky Way / Submarine, Sekisei Lagoon, Ishigaki, Okinawa, Japan",24.300251,124.08625 ));
                    placesModelList.add(new TopStreetView_Model("Cape Kri, Raja Ampat, Indonesia",-0.556114,130.690997 ));
                    placesModelList.add(new TopStreetView_Model("Tatawa Kecil, Komodo, Indonesia",-8.53004,119.62723 ));
                    placesModelList.add(new TopStreetView_Model("Batu Bolong, Komodo, Indonesia",-8.537229,119.613738));
                    placesModelList.add(new TopStreetView_Model("Timor II, Bunaken National Park, Indonesia",1.618098,124.781914 ));
                    placesModelList.add(new TopStreetView_Model("Leukan III A, Bunaken National Park, Indonesia",1.602118,124.766276 ));
                    placesModelList.add(new TopStreetView_Model("Fukui, Bunaken National Park, Indonesia",1.611651,124.739282 ));
                    placesModelList.add(new TopStreetView_Model("Minke Whales on the Ribbon Reef",-14.865306,145.680527 ));
                    placesModelList.add(new TopStreetView_Model("Shelly Beach, Australia",-33.893774,151.282125 ));
                    placesModelList.add(new TopStreetView_Model("Galápagos Islands, Ecuador",-1.239203,-90.385735 ));
                    placesModelList.add(new TopStreetView_Model("Crystal Bay, Indonesia",-8.715413,115.457015 ));
                    placesModelList.add(new TopStreetView_Model("Devil's Crown, Galapagos",-1.216466,-90.42215 ));
                    placesModelList.add(new TopStreetView_Model("Nudi's Retreat, Indonesia",1.486096,125.24151 ));
                    placesModelList.add(new TopStreetView_Model("Champion Island East, Galápagos",-1.238894,-90.384239 ));
                    placesModelList.add(new TopStreetView_Model("Hong Chai, Kenting National Park, Taiwan",21.953572,120.710359 ));
                    placesModelList.add(new TopStreetView_Model("Nuclear Outlet, Kenting National Park, Taiwan",21.930845,120.745026 ));
                    placesModelList.add(new TopStreetView_Model("Da Bai Sha, Green Island, Taiwan",22.638232,121.490526 ));
                    placesModelList.add(new TopStreetView_Model("Three Rock, Green Island, Taiwan",22.639565,121.490911 ));
                    placesModelList.add(new TopStreetView_Model("Emily's Pinnacles, Bermuda",32.235303,-64.848371 ));
                    placesModelList.add(new TopStreetView_Model("Rarotonga, Cook Islands",-21.203982,-159.837009 ));
                    placesModelList.add(new TopStreetView_Model("Heron Island, Great Barrier Reef",-23.442896,151.906584 ));
                    placesModelList.add(new TopStreetView_Model("Muli Kandu, Maldives",2.927715,73.590414 ));
                    placesModelList.add(new TopStreetView_Model("The Liberty Wreck, Bali",-8.274153,115.592694 ));
                    placesModelList.add(new TopStreetView_Model("Batu Rufus, Indonesia",-0.565927,130.284558 ));
                    placesModelList.add(new TopStreetView_Model("Big Momma, American Samoa",-14.259231,-169.500376 ));
                    placesModelList.add(new TopStreetView_Model("Airport Reef, American Samoa",-14.328972,-170.70172 ));
                    placesModelList.add(new TopStreetView_Model("Nelson Island Outside, Chagos",-5.683498,72.325919 ));
                    placesModelList.add(new TopStreetView_Model("Tafeu Cove, American Samoa",-14.253016,-170.689206 ));
                    placesModelList.add(new TopStreetView_Model("Whale Sharks at Isla Contoy, Mexico",21.479702,-86.632599 ));
                    placesModelList.add(new TopStreetView_Model("Aquarius Reef Base, Florida Keys - United States of America",24.950493,-80.454069 ));
                    placesModelList.add(new TopStreetView_Model("Bleached Coral at Airport Reef, American Samoa - During",-14.328973,-170.70172 ));
                    placesModelList.add(new TopStreetView_Model("Dead Coral at Airport Reef, American Samoa - After",-14.328973,-170.70172 ));
                    placesModelList.add(new TopStreetView_Model("SS Antilla Shipwreck, Aruba",12.601959,-70.058343 ));
                    placesModelList.add(new TopStreetView_Model("Ile Vache Marine, Chagos, British Indian Ocean Territory",-5.427774,71.828873 ));
                    placesModelList.add(new TopStreetView_Model("Mayreau Hot Springs, St Vincent & the Grenadines",12.627667,-61.378149 ));
                    placesModelList.add(new TopStreetView_Model("Cozumel, Mexico",20.338045,-87.027587 ));
                    placesModelList.add(new TopStreetView_Model("Moresby Island Inside, Chagos, British Indian Ocean Territory",-5.243066,71.825168 ));
                    placesModelList.add(new TopStreetView_Model("PUE Coral Gardens, Cook Islands",-21.201181,-159.771712 ));
                    placesModelList.add(new TopStreetView_Model("Cheeca Rocks, Florida Keys, USA",24.904503,-80.616652 ));
                    placesModelList.add(new TopStreetView_Model("Freeport, Grand Bahama",26.488173,-78.657134 ));
                    placesModelList.add(new TopStreetView_Model("Jessie Beazley Reef, Philippines",9.043864,119.819872 ));
                    placesModelList.add(new TopStreetView_Model("Horseshoe Bay, Bermuda",32.244589,-64.822922 ));
                    placesModelList.add(new TopStreetView_Model("Benwood Wreck, Florida Keys, USA",25.052668,-80.332617 ));
                    placesModelList.add(new TopStreetView_Model("Jaco Island, Timor Leste",-8.441477,127.312285 ));
                    placesModelList.add(new TopStreetView_Model("Lizard Island, Great Barrier Reef",-14.685821,145.442162 ));
                    placesModelList.add(new TopStreetView_Model("Great Detached Reef, Great Barrier Reef",-11.737481,143.972687 ));
                    break;
                    case "Tv Studio":
                    tvTitle.setText(String.valueOf("Tv Studio"));
                    placesModelList.add(new TopStreetView_Model("Estudios de Canal 13", -33.427364,-70.627082));
                    placesModelList.add(new TopStreetView_Model("The Late Show with Stephen Colbert - 2", 40.763765,-73.98295));
                    placesModelList.add(new TopStreetView_Model("Telefe Noticias", -34.620483,-58.387732));
                    placesModelList.add(new TopStreetView_Model("Telefe Noticias", -34.62014,-58.387783));
                    placesModelList.add(new TopStreetView_Model("Late Show w/ Stephen Colbert", 40.763741,-73.982866));
                    placesModelList.add(new TopStreetView_Model("Harry Potter's Diagon Alley",51.693249,-0.419633 ));
                    placesModelList.add(new TopStreetView_Model("America TV",-34.584441,-58.437018 ));
                    placesModelList.add(new TopStreetView_Model("The Depository, London, UK", 51.566382,-0.075246));
                    placesModelList.add(new TopStreetView_Model("Ed Sullivan Theater",40.763838,-73.982864 ));
                    placesModelList.add(new TopStreetView_Model("The Ed Sullivan Theater - 2",40.763884,-73.9829 ));
                    placesModelList.add(new TopStreetView_Model("The Late Show With David Letterman - 1",40.76381,-73.982976 ));
                    placesModelList.add(new TopStreetView_Model("Adam Savage's Cave", 37.75387,-122.420658));
                    placesModelList.add(new TopStreetView_Model("Canada AM studio",43.78189,-79.257404));
                    placesModelList.add(new TopStreetView_Model("Tardis",51.492177,-0.193015));
                    placesModelList.add(new TopStreetView_Model("Late Show w/ David Letterman", 40.763851,-73.98288));
                    placesModelList.add(new TopStreetView_Model("The Colbert Report studio - 1",40.767857,-73.990773 ));
                    placesModelList.add(new TopStreetView_Model("The Colbert Report studio - 2", 40.767669,-73.990809));
                    break;
                case "Black History Culture In The Us":
                    tvTitle.setText(String.valueOf("Black History Culture In The Us"));
                placesModelList.add(new TopStreetView_Model("Creative Time", 40.715867,-73.967208));
                placesModelList.add(new TopStreetView_Model("Frederick Douglass National Historic Site",38.863443,-76.985178 ));
                placesModelList.add(new TopStreetView_Model("Dexter Avenue King Memorial Church",32.377731,-86.302505 ));
                placesModelList.add(new TopStreetView_Model("Ebenezer Baptist Church",33.75551,-84.374349 ));
                placesModelList.add(new TopStreetView_Model("16th Street Baptist Church (Birmingham, AL)", 33.516559,-86.814599));
                placesModelList.add(new TopStreetView_Model("Montgomery Greyhound Bus Station",32.374575,-86.308916 ));
                placesModelList.add(new TopStreetView_Model("Little Rock Central High School",34.736786,-92.297827 ));
                placesModelList.add(new TopStreetView_Model("Museum of African American History, Boston Campus",42.359974,-71.065202 ));
                placesModelList.add(new TopStreetView_Model("Edmund Pettus Bridge",32.405518,-87.018526 ));
                placesModelList.add(new TopStreetView_Model("Daisy Bates House",34.721838,-92.288331 ));
                placesModelList.add(new TopStreetView_Model("Brown v. Board of Education",39.037806,-95.676158 ));
                placesModelList.add(new TopStreetView_Model("Maggie L. Walker National Historic Site",37.547751,-77.437686 ));
                placesModelList.add(new TopStreetView_Model("Brown Chapel AME Church",32.412485,-87.016432 ));
                placesModelList.add(new TopStreetView_Model("The King Center",33.754984,-84.373202 ));
            break;
                case "Christmas Island":
                    tvTitle.setText(String.valueOf("Christmas Island"));
                    placesModelList.add(new TopStreetView_Model("Merrial Beach Track",-10.474312,105.559443 ));
                    placesModelList.add(new TopStreetView_Model("Dolly Beach",-10.520641,105.675738 ));
                    placesModelList.add(new TopStreetView_Model("The Dales",-10.483353,105.554388 ));
                    placesModelList.add(new TopStreetView_Model("West White Beach",-10.460865,105.579462 ));
                    placesModelList.add(new TopStreetView_Model("Ethel Beach",-10.463977,105.708019 ));
                    placesModelList.add(new TopStreetView_Model("Greta Beach",-10.50195,105.674651 ));
                    placesModelList.add(new TopStreetView_Model("Lily Beach",-10.466893,105.711355 ));
                    placesModelList.add(new TopStreetView_Model("The Blowholes",-10.514544,105.622572 ));
                    placesModelList.add(new TopStreetView_Model("Winifred Beach",-10.496783,105.546169 ));
                    placesModelList.add(new TopStreetView_Model("Golf Course Lookout",-10.423823,105.698357 ));
                    placesModelList.add(new TopStreetView_Model("The Grotto",-10.42308,105.701954 ));
                    placesModelList.add(new TopStreetView_Model("Martin Point",-10.469337,105.557799 ));
                    placesModelList.add(new TopStreetView_Model("Margaret Knoll Lookout",-10.477045,105.684215 ));
                    placesModelList.add(new TopStreetView_Model("The Dales",-10.478835,105.55961 ));
                    placesModelList.add(new TopStreetView_Model("Boulder track",-10.549466,105.638712 ));
                    break;
                    case "Cocos Keeling Islands":
                        tvTitle.setText(String.valueOf("Cocos Keeling Islands"));
                placesModelList.add(new TopStreetView_Model("Direction Island",-12.0911,96.885288 ));
                placesModelList.add(new TopStreetView_Model("West Island",-12.196077,96.863094 ));
                placesModelList.add(new TopStreetView_Model("Cocos Airport",-12.193344,96.834912 ));
                placesModelList.add(new TopStreetView_Model("Horsburgh Island",-12.081945,96.845105 ));
                placesModelList.add(new TopStreetView_Model("Cocos Lagoon",-12.192331,96.859568 ));
               break;
                case "Australia Highlights":
                    tvTitle.setText(String.valueOf("Australia Highlights"));
                    placesModelList.add(new TopStreetView_Model("Hisense Arena",-37.822779,144.981893 ));
                    placesModelList.add(new TopStreetView_Model("Margaret Court Arena",-37.821508,144.977749 ));
                    placesModelList.add(new TopStreetView_Model("Cape Willoughby, Kangaroo Island",-35.842016,138.133209 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-35.963795,136.654461 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-36.016935,136.856542 ));
                    placesModelList.add(new TopStreetView_Model("Ravine des Casoars Hike, Kangaroo Island",-35.793328,136.582107 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-36.04712,136.756926 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-35.950961,136.676119 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-35.95853,136.656554 ));
                    placesModelList.add(new TopStreetView_Model("Giraffe crossing, Monarto Zoo",-35.102711,139.156459 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo sunbathing, Kangaroo Island",-36.006964,136.861363 ));
                    placesModelList.add(new TopStreetView_Model("Monarto Zoo",-35.093052,139.16632 ));
                    placesModelList.add(new TopStreetView_Model("Kangaroo Island",-36.063356,136.704953 ));
                    placesModelList.add(new TopStreetView_Model("Adelaide Zoo",-34.914127,138.606372 ));
                    placesModelList.add(new TopStreetView_Model("Sydney Opera House",-33.857394,151.215435 ));
                    placesModelList.add(new TopStreetView_Model("Hyams Beach",-35.102298,150.693455 ));
                    placesModelList.add(new TopStreetView_Model("Concert Hall, Sydney Opera House",-33.856965,151.214948 ));
                    placesModelList.add(new TopStreetView_Model("Manly Beach",-33.786165,151.289112 ));
                    placesModelList.add(new TopStreetView_Model("National Gallery of Australia",-35.300799,149.136104 ));
                    placesModelList.add(new TopStreetView_Model("Kosciuszko National Park, Mount Kosciuszko summit",-36.455828,148.263492 ));
                    placesModelList.add(new TopStreetView_Model("Shelly Beach",-33.800715,151.298797 ));
                    placesModelList.add(new TopStreetView_Model("Joan Sutherland Theatre, Sydney Opera House",-33.857016,151.21547 ));
                    placesModelList.add(new TopStreetView_Model("Luna Park",-33.849198,151.210728 ));
                    placesModelList.add(new TopStreetView_Model("Barangaroo park",-33.85887,151.202199 ));
                    placesModelList.add(new TopStreetView_Model("Bronte Beach",-33.904869,151.268291 ));
                    placesModelList.add(new TopStreetView_Model("Invurt - Melbourne Street Art - Artists Lane",-37.853993,144.992311 ));
                    placesModelList.add(new TopStreetView_Model("Form (PUBLIC - Art in the City) - Wilson Parking Lot off of Murray Street, Perth",-31.95092,115.85443 ));
                    placesModelList.add(new TopStreetView_Model("Art Gallery of New South Wales",-33.869377,151.217646 ));
                    placesModelList.add(new TopStreetView_Model("Blue Mountains National Park, Echo Point lookout (Three Sisters)",-33.73285,150.312039 ));
                    placesModelList.add(new TopStreetView_Model("Ku-ring-gai Chase National Park, West Head lookout",-33.579289,151.309294 ));
                    placesModelList.add(new TopStreetView_Model("Royal National Park, Wattamolla Beach",-34.137256,151.117934 ));
                    placesModelList.add(new TopStreetView_Model("Drama Theatre, Sydney Opera House",-33.856658,151.215085 ));
                    placesModelList.add(new TopStreetView_Model("Sydney Harbour National Park, Fort Denison",-33.855069,151.225416 ));
                    placesModelList.add(new TopStreetView_Model("Hanging Rock",-37.329771,144.59378 ));
                    placesModelList.add(new TopStreetView_Model("Mungo National Park, Walls of China",-33.741578,143.12705 ));
                    placesModelList.add(new TopStreetView_Model("Cape Byron State Conservation Area, Cape Byron lighthouse",-28.638788,153.636479 ));
                    placesModelList.add(new TopStreetView_Model("Dorrigo National Park, Skywalk lookout",-30.363612,152.729783 ));
                    placesModelList.add(new TopStreetView_Model("Barrington Tops National Park, Barrington trail",-31.949395,151.443958 ));
                    placesModelList.add(new TopStreetView_Model("Ben Boyd National Park, Green Cape lookout",-37.261638,150.04943 ));
                    placesModelList.add(new TopStreetView_Model("Warrumbungle National Park, Breadknife and Grand High Tops walk",-31.332615,148.995792 ));
                    placesModelList.add(new TopStreetView_Model("Montague Island Nature Reserve, Montague Island walking track",-36.251293,150.227668));
                    placesModelList.add(new TopStreetView_Model("Gundabooka National Park, Valley of the Eagles walk",-30.593884,145.686808 ));
                    placesModelList.add(new TopStreetView_Model("Muttonbird Island Nature Reserve",-30.304711,153.153442 ));
                    placesModelList.add(new TopStreetView_Model("Sydney Harbour National Park, Bradleys Head",-33.853049,151.245498 ));
                    placesModelList.add(new TopStreetView_Model("Bouddi National Park, Bouddi coastal walk",-33.529776,151.374955 ));
                    placesModelList.add(new TopStreetView_Model("Melbourne Zoo",-37.785221,144.953041 ));
                    placesModelList.add(new TopStreetView_Model("Werribee Zoo",-37.922403,144.662957 ));
                    placesModelList.add(new TopStreetView_Model("Croajingolong",-37.782058,149.313572 ));
                    placesModelList.add(new TopStreetView_Model("Great Ocean Walk",-38.664017,143.104197 ));
                    placesModelList.add(new TopStreetView_Model("Phillip Island",-38.500644,145.231302 ));
                    placesModelList.add(new TopStreetView_Model("Mount Buffalo National Park",-36.732918,146.811495 ));
                    placesModelList.add(new TopStreetView_Model("Ballarat",-37.577173,143.866066 ));
                    placesModelList.add(new TopStreetView_Model("Melbourne CBD",-37.817954,144.968871 ));
                    placesModelList.add(new TopStreetView_Model("Bells Beach",-38.372692,144.279023 ));
                    placesModelList.add(new TopStreetView_Model("The Studio, Sydney Opera House",-33.857017,151.215045 ));
                    placesModelList.add(new TopStreetView_Model("Yarra Valley",-37.660055,145.468929 ));
                    placesModelList.add(new TopStreetView_Model("Falls Creek",-36.874735,147.243036 ));
                    placesModelList.add(new TopStreetView_Model("Mt Buller",-36.87484,147.242089 ));
                    placesModelList.add(new TopStreetView_Model("Grampians National Park",-37.293149,142.601589 ));
                    placesModelList.add(new TopStreetView_Model("Yarra Valley",-37.660055,145.468929 ));
                    placesModelList.add(new TopStreetView_Model("Falls Creek",-36.874735,147.243036 ));
                    placesModelList.add(new TopStreetView_Model("Mornington Peninsula",-38.497917,144.889008 ));
                    placesModelList.add(new TopStreetView_Model("Apollo Bay",-38.755857,143.554685 ));
                    placesModelList.add(new TopStreetView_Model("Wilsons Promontory",-39.030335,146.315712 ));
                    placesModelList.add(new TopStreetView_Model("Yarrawonga",-36.005968,146.003609 ));
                    placesModelList.add(new TopStreetView_Model("Perth Zoo",-31.975408,115.853526 ));
                    placesModelList.add(new TopStreetView_Model("Flemignton Racecourse",-37.789354,144.908532 ));
                    placesModelList.add(new TopStreetView_Model("Tarra Bulga National Park",-38.428897,146.566805 ));
                    placesModelList.add(new TopStreetView_Model("Puffing Billy",-37.908328,145.365155 ));
                    placesModelList.add(new TopStreetView_Model("Royal Botanic Gardens Melbourne",-37.828164,144.981004 ));
                    placesModelList.add(new TopStreetView_Model("The Utzon Room, Sydney Opera House",-33.857365,151.215516 ));
                    break;
                    case "World Wonder Project":
                        tvTitle.setText(String.valueOf("World Wonder Project"));
                        placesModelList.add(new TopStreetView_Model("Nijojo castle",35.012762,135.750338 ));
                        placesModelList.add(new TopStreetView_Model("Itsukushima Shinto Shrine",34.296968,132.318469 ));
                        placesModelList.add(new TopStreetView_Model("Archaeological Areas of Pompei",40.7517,14.494312 ));
                        placesModelList.add(new TopStreetView_Model("Scott's Hut, Cape Evans on Ross Island",-77.636248,166.417282 ));
                        placesModelList.add(new TopStreetView_Model("Versailles Palace",48.805224,2.118542 ));
                        placesModelList.add(new TopStreetView_Model("Hiroshima Peace Memorial",34.395108,132.453676 ));
                        placesModelList.add(new TopStreetView_Model("Historic Center of Prague",50.087989,14.420901 ));
                        placesModelList.add(new TopStreetView_Model("Independence Hall",39.949178,-75.150428 ));
                        placesModelList.add(new TopStreetView_Model("Three Castles, Bellinzona",46.192686,9.022496 ));
                        placesModelList.add(new TopStreetView_Model("Historic Walled Town of Cuenca",40.07836,-2.12978 ));
                        placesModelList.add(new TopStreetView_Model("Stonehenge",51.179197,-1.825302 ));
                        placesModelList.add(new TopStreetView_Model("Old Town Lunenburg",44.376978,-64.309726 ));
                        placesModelList.add(new TopStreetView_Model("Church Village of Gammelstad, Luleå",65.645377,22.026225 ));
                        placesModelList.add(new TopStreetView_Model("Mill Network at Kinderdijk-Elshout",51.878621,4.648365 ));
                        placesModelList.add(new TopStreetView_Model("Historic Town of Ouro Preto",-20.386195,-43.502827 ));
                        placesModelList.add(new TopStreetView_Model("Historic Center of Florence",43.773392,11.25518 ));
                        placesModelList.add(new TopStreetView_Model("Santa Prisca Parish Church",18.558093,-99.606553 ));
                        placesModelList.add(new TopStreetView_Model("Mill Network at Kinderdijk-Elshout",51.878621,4.648365 ));
                        placesModelList.add(new TopStreetView_Model("Historic Town of Ouro Preto",-20.386195,-43.502827 ));
                        placesModelList.add(new TopStreetView_Model("Historic Center of Florence",43.773392,11.25518 ));
                        placesModelList.add(new TopStreetView_Model("Santa Prisca Parish Church",18.558093,-99.606553 ));
                        placesModelList.add(new TopStreetView_Model("Shark Bay",-26.192525,113.68943 ));
                        placesModelList.add(new TopStreetView_Model("Sanctuary of Bom Jesus do Congonhas", -20.500908,-43.858205));
                        break;
                case "Sea Ports":
                    tvTitle.setText(String.valueOf("Sea Ports"));
                    placesModelList.add(new TopStreetView_Model("Port Hercule,", 43.7363698, 7.4261142));
                    placesModelList.add(new TopStreetView_Model("Port de Nice", 43.6976207, 7.2847341));
                    placesModelList.add(new TopStreetView_Model("Port Grimaud", 43.2730468, 6.5813216));
                    placesModelList.add(new TopStreetView_Model("Marina Baie des Anges", 43.6340053, 7.1431472));
                    placesModelList.add(new TopStreetView_Model("Port de Cannes (Vieux Port)", 43.5502448, 7.0131013));
                    placesModelList.add(new TopStreetView_Model("Port Mandelieu - La Napoule", 43.5214549, 6.9443649));
                    placesModelList.add(new TopStreetView_Model("Port de la Rague", 43.514371, 6.9400076));
                    placesModelList.add(new TopStreetView_Model("Marines de Cogolin", 43.2679412, 6.582139));
                    placesModelList.add(new TopStreetView_Model("Vieux Port de Golfe Juan", 43.5620873, 7.0758303));
                    placesModelList.add(new TopStreetView_Model("Menton Vieux Port", 43.777358, 7.5119376));
                    placesModelList.add(new TopStreetView_Model("Port de Villefranche-sur-Mer,", 43.6977072, 7.3083898));
                    placesModelList.add(new TopStreetView_Model("Menton Garavan and French Italian Border", 43.7836564, 7.5225648));
                    placesModelList.add(new TopStreetView_Model("Port de Théoule-sur-Mer", 43.5100428, 6.9394297));
                    placesModelList.add(new TopStreetView_Model("Port de Saint-Laurent-du-Var", 43.673416, 7.3261928));
                    placesModelList.add(new TopStreetView_Model("Port Figueirette-Miramar", 43.4994787, 6.9564714));
                    placesModelList.add(new TopStreetView_Model("Port Cogolin", 43.2682246, 6.5770008));
                    placesModelList.add(new TopStreetView_Model("Port de Sainte-Maxime", 43.2828839, 6.6011952));
                    placesModelList.add(new TopStreetView_Model("Port Camille Rayon", 43.5635814, 7.0746523));
                    placesModelList.add(new TopStreetView_Model("Port Vauban Antibes", 43.5863099, 7.1285712));
                    placesModelList.add(new TopStreetView_Model("Port de Cap d'Ail", 43.7292401, 7.4212074));
                    placesModelList.add(new TopStreetView_Model("Port de Saint-Jean Cap-Ferrat", 43.7172231, 7.3513383));
                    placesModelList.add(new TopStreetView_Model("Port de Beaulieu", 43.7074804, 7.3374262));
                    break;
                case "DryPorts":
                    tvTitle.setText(String.valueOf("DryPorts"));
                    placesModelList.add(new TopStreetView_Model("Port of Buenos Aires", -34.5799412, -58.3743793));
                    placesModelList.add(new TopStreetView_Model("Port Botany, Sydney", -33.9744036, 151.2239884));
                    placesModelList.add(new TopStreetView_Model("Port of Brisbane", -27.3845669, 153.172971));
                    placesModelList.add(new TopStreetView_Model("Port of Fremantle", -32.053335, 115.74132));
                    placesModelList.add(new TopStreetView_Model("Port of Melbourne", -37.8202626, 144.9744252));
                    placesModelList.add(new TopStreetView_Model("Port of Antwerp", 51.2438583, 4.3936195));
                    placesModelList.add(new TopStreetView_Model("Port of Centro", -23.9566111, -46.3132919));
                    placesModelList.add(new TopStreetView_Model("Port of Salvador", 13.4451379, -89.0570357));
                    placesModelList.add(new TopStreetView_Model("Halifax Port Authority", 44.6404839, -63.5655975));
                    placesModelList.add(new TopStreetView_Model("Port of Valparaiso", -33.0372889, -71.6275311));
                    placesModelList.add(new TopStreetView_Model("Port of Antofagasta", -23.653854, -70.4026981));
                    placesModelList.add(new TopStreetView_Model("Port of Iquique", -20.2065993, -70.1393933));
                    placesModelList.add(new TopStreetView_Model("Port of Hong Kong", 22.3500501, 114.1247631));
                    placesModelList.add(new TopStreetView_Model("Port of Rijeka", 45.3273995, 14.4372927));
                    placesModelList.add(new TopStreetView_Model("Aarhus", 56.1654276, 10.2259754));
                    placesModelList.add(new TopStreetView_Model("Copenhagen Malmö Port", 55.6207331, 13.004495));
                    placesModelList.add(new TopStreetView_Model("Fredericia", 55.5596809, 9.7548361));
                    placesModelList.add(new TopStreetView_Model("Port of Le Havre", 49.4798023, 0.1663282));
                    placesModelList.add(new TopStreetView_Model("Port of Germersheim", 49.23131, 8.37732));
                    placesModelList.add(new TopStreetView_Model("Tanjung Mas, Semarang", -6.9475734, 110.4291567));
                    placesModelList.add(new TopStreetView_Model("Port of Gioia Tauro", 38.4683286, 15.9194731));
                    placesModelList.add(new TopStreetView_Model("Port of Taranto", 40.4798828, 17.2052703));
                    placesModelList.add(new TopStreetView_Model("Port of Palermo", 38.1308744, 13.3636225));
                    placesModelList.add(new TopStreetView_Model("Port of Naples", 40.8496822, 14.3119485));
                    break;
                case "Beaches":
                    tvTitle.setText(String.valueOf("Beaches"));
                    placesModelList.add(new TopStreetView_Model("Bondi Beach", -33.89102, 151.277726));
                    placesModelList.add(new TopStreetView_Model("Blue Lagoon", 63.8797031, -22.4489511));
                    placesModelList.add(new TopStreetView_Model("Grace Bay", 21.8116045, -72.1658116));
                    placesModelList.add(new TopStreetView_Model("Anse Source d'Argent", -4.372587, 55.8284021));
                    placesModelList.add(new TopStreetView_Model("Piha Beach", -36.9585427, 174.4658125));
                    placesModelList.add(new TopStreetView_Model("Baia do Sancho", -3.8536342, -32.4428304));
                    placesModelList.add(new TopStreetView_Model("Siesta Key", 27.2671608, -82.5582104));
                    placesModelList.add(new TopStreetView_Model("Lake McKenzie", -25.4484484, 153.0572585));
                    placesModelList.add(new TopStreetView_Model("Rabbit Beach", 35.51297, 12.5579023));
                    placesModelList.add(new TopStreetView_Model("Antarctica", -75.25097, -0.07139));
                    placesModelList.add(new TopStreetView_Model("Goa Cina", -8.4471919, 112.6537097));
                    placesModelList.add(new TopStreetView_Model("Navagio", 37.8594645, 20.6249001));
                    placesModelList.add(new TopStreetView_Model("Praia do Abricó", -23.052495, -43.5362362));
                    placesModelList.add(new TopStreetView_Model("Lighthouse Field State Beach", 36.9533759, -122.0288092));
                    placesModelList.add(new TopStreetView_Model("Grande Anse Beach, La Digue Island, Seychelles", -4.3731524, 55.8285083));
                    placesModelList.add(new TopStreetView_Model("Matira Beach, Bora Bora, Tahiti", -16.5422249, -151.7356683));
                    placesModelList.add(new TopStreetView_Model("Rarotonga, Cook Islands", -21.2350587, -159.7767675));
                    placesModelList.add(new TopStreetView_Model("Navagio Beach, Greece", 37.8595009, 20.6248531));
                    placesModelList.add(new TopStreetView_Model("Trunk Bay, St. John, U.S. Virgin Islands", 18.3509979, -64.7708435));
                    placesModelList.add(new TopStreetView_Model("Bandon, Oregon, United States", 43.106954, -124.4358088));
                    placesModelList.add(new TopStreetView_Model("Bottom Bay, Barbados", 13.1362029, -59.425053));
                    placesModelList.add(new TopStreetView_Model("Falassarna Beach, Crete, Greece", 35.493186, 23.579767));
                    placesModelList.add(new TopStreetView_Model("Praia da Rocha Baixinha", 37.0753326, -8.1332169));
                    placesModelList.add(new TopStreetView_Model("Pipa Beach", -6.2272778, -35.0531239));
                    placesModelList.add(new TopStreetView_Model("Pantai Tamban", -8.4172026, 112.7101498));
                    placesModelList.add(new TopStreetView_Model("Drini Beach", -8.1381243, 110.5771656));
                    placesModelList.add(new TopStreetView_Model("As Catedrais beach", 43.5539831, -7.1572855));
                    placesModelList.add(new TopStreetView_Model("Champagne Beach", -15.1437919, 167.1203814));
                    placesModelList.add(new TopStreetView_Model("Playa de La Concha", 43.318508, -1.9852789));
                    placesModelList.add(new TopStreetView_Model("Nissi Beach", 34.98743, 33.9715508));
                    placesModelList.add(new TopStreetView_Model("South Bay (Taiwan)", 21.9594193, 120.766037));
                    placesModelList.add(new TopStreetView_Model("Anse Source d'Argent", -4.3693915, 55.8257262));
                    break;
                case "Leakes":
                    tvTitle.setText(String.valueOf("Leakes"));
                    placesModelList.add(new TopStreetView_Model("Marshall Island", 10.1617905, 166.0113424));
                    placesModelList.add(new TopStreetView_Model("Louvre Museum", 48.8612736, 2.335015));
                    placesModelList.add(new TopStreetView_Model("Proizd Near Korcula Island", 42.9866605, 16.6167329));
                    placesModelList.add(new TopStreetView_Model("Portofino", 44.3024841, 9.2118983));
                    placesModelList.add(new TopStreetView_Model("Neuschwanstein Castle Germany", 47.5550506, 10.7492342));
                    placesModelList.add(new TopStreetView_Model("Kauia Haiwaii", 22.1635778, -159.6691604));
                    placesModelList.add(new TopStreetView_Model("El Sardinero Santander", 43.4747616, -3.7930923));
                    placesModelList.add(new TopStreetView_Model("Cala Mariolu", 40.1235129, 9.6764042));
                    placesModelList.add(new TopStreetView_Model("Schrecksee", 47.4391215, 10.4670836));
                    placesModelList.add(new TopStreetView_Model("Cala Goloritze", 40.110693, 9.6848583));
                    placesModelList.add(new TopStreetView_Model("The Crooked Forest", 53.2141108, 14.4757408));
                    placesModelList.add(new TopStreetView_Model("Mirador Obispado Monterrey", 25.676239, -100.3466135));
                    placesModelList.add(new TopStreetView_Model("Cahir Castle", 52.3745471, -7.9270751));
                    placesModelList.add(new TopStreetView_Model("Napili Bay Beach", 20.9987213, -156.6672225));
                    placesModelList.add(new TopStreetView_Model("Seebrucke Sellin", 54.3828803, 13.6984421));
                    placesModelList.add(new TopStreetView_Model("Kayangan Lake in Coron", 11.9594607, 120.2265456));
                    placesModelList.add(new TopStreetView_Model("Batu Caves", 3.2381653, 101.6842671));
                    placesModelList.add(new TopStreetView_Model("Palawan", 9.8349493, 118.7383615));
                    placesModelList.add(new TopStreetView_Model("Morsum Kliff", 54.8767116, 8.4625196));
                    placesModelList.add(new TopStreetView_Model("Berliner Luft Und Badeparadies Neukolln", 52.4582371, 13.4476049));
                    placesModelList.add(new TopStreetView_Model("InterContinental Resort Tahiti", -17.5711632, -149.6195526));
                    placesModelList.add(new TopStreetView_Model("Portofino Italy", 44.3023164, 9.2139228));
                    placesModelList.add(new TopStreetView_Model("Lake Tahoe California Nevada", 39.1323571, -119.9463896));
                    placesModelList.add(new TopStreetView_Model("Kayangan Lake", 11.9577086, 120.2263569));
                    placesModelList.add(new TopStreetView_Model("Kauia Haiwaii", 22.1635778, -159.6691604));
                    placesModelList.add(new TopStreetView_Model("Ait Benhaddou Morocco", 31.0470695, -7.1277809));
                    placesModelList.add(new TopStreetView_Model("Aqua World Cancun", 21.0839509, -86.77503));
                    placesModelList.add(new TopStreetView_Model("Na Pali Coast State Park", 22.1790137, -159.6453283));
                    placesModelList.add(new TopStreetView_Model("A Coruna Galicia", 43.3623143, -8.4114859));
                    placesModelList.add(new TopStreetView_Model("Praia Preta da Costa do Sul", -23.7908773, -45.7146263));
                    placesModelList.add(new TopStreetView_Model("Firule Beach", 43.5003567, 16.4544));
                    placesModelList.add(new TopStreetView_Model("Antelope Canyon in Arizona", 36.8625172, -111.3746638));
                    placesModelList.add(new TopStreetView_Model("CYC Beach", 11.963845, 120.1806508));
                    placesModelList.add(new TopStreetView_Model("Le Meridien Bora Bora", -16.497917, -151.7019062));
                    placesModelList.add(new TopStreetView_Model("Teufelsberg", 52.497945, 13.2406519));
                    placesModelList.add(new TopStreetView_Model("County Waterford", 52.1389241, -7.3668495));
                    placesModelList.add(new TopStreetView_Model("Gonzalo Guerrero Playa Del Carmen", 20.6324755, -87.0725283));
                    placesModelList.add(new TopStreetView_Model("Ostrvo Gospa od Milosrđa", 42.4106727, 18.6784101));
                    placesModelList.add(new TopStreetView_Model("Caribean Cancun", 21.111259, -86.762268));
                    placesModelList.add(new TopStreetView_Model("Club Med Cancun", 21.0364043, -86.777625));
                    placesModelList.add(new TopStreetView_Model("Zion National Park", 37.3094714, -113.0522415));
                    placesModelList.add(new TopStreetView_Model("Embourios", 36.7021235, 24.4050475));
                    placesModelList.add(new TopStreetView_Model("Alexandra Falls Hiking Trail", -20.4403993, 57.4581368));
                    placesModelList.add(new TopStreetView_Model("Chichen Itza Playa Del Carmen", 20.7053058, -87.009796));
                    placesModelList.add(new TopStreetView_Model("Garganta del Diablo", -41.1878828, -71.8389028));
                    placesModelList.add(new TopStreetView_Model("Marina Barracuda", 21.0935758, -86.771264));
                    placesModelList.add(new TopStreetView_Model("Spreepark", 52.4847269, 13.4901219));
                    placesModelList.add(new TopStreetView_Model("Playa de los Genoveses", 36.7445895, -2.1220108));
                    break;
                case "Museums In Latin America":
                    tvTitle.setText(String.valueOf("Museums In Latin America"));
                    placesModelList.add(new TopStreetView_Model("Museu Oscar Niemeyer",-25.410076,-49.267302 ));
                    placesModelList.add(new TopStreetView_Model("Museu da Sustentabilidade",-23.563791,-46.701799 ));
                    placesModelList.add(new TopStreetView_Model("Museo Franz Mayer",19.437239,-99.143291 ));
                    placesModelList.add(new TopStreetView_Model("Museu Afro Brasil",-23.584018,-46.659207 ));
                    placesModelList.add(new TopStreetView_Model("Museo del Fútbol",-34.893988,-56.151614 ));
                    placesModelList.add(new TopStreetView_Model("Museu do Café",-23.932236,-46.330007 ));
                    placesModelList.add(new TopStreetView_Model("Museo Nacional del Tequila",20.884598,-103.839898 ));
                    placesModelList.add(new TopStreetView_Model("MMAPO",18.920825,-99.235606 ));
                    placesModelList.add(new TopStreetView_Model("Museo Fernando García Ponce", 20.966674,-89.622482));
                    placesModelList.add(new TopStreetView_Model("Museo del Juguete Antiguo",19.415465,-99.144514 ));
                    placesModelList.add(new TopStreetView_Model("Museo Submarino Abtao",-12.059832,-77.150871));
                    placesModelList.add(new TopStreetView_Model("Museo Archivo de la Fotografía",19.434995,-99.132223 ));
                    placesModelList.add(new TopStreetView_Model("Museo Paleontológico",5.641082,-73.522523 ));
                    placesModelList.add(new TopStreetView_Model("Polyforum Siqueiros",19.393385,-99.173392 ));
                    placesModelList.add(new TopStreetView_Model("Museu Casa Guilherme de Almeida",-23.5469,-46.673893 ));
                    placesModelList.add(new TopStreetView_Model("Museo Dolores Olmedo",19.265623,-99.124881 ));
                    placesModelList.add(new TopStreetView_Model("Museo de Sitio Bodega y Quadra",-12.044648,-77.028581 ));
                    placesModelList.add(new TopStreetView_Model("Museu de Arte Moderna de São Paulo, São Paulo Museum of Modern Art",-23.587958,-46.655548 ));
                    break;
                case "University of Italy":
                    tvTitle.setText(String.valueOf("University of Italy"));
                    placesModelList.add(new TopStreetView_Model("University of Bologna", 44.496865,11.352459));
                    placesModelList.add(new TopStreetView_Model("Palazzo Poggi Museum, University of Bologna",44.496839,11.352306 ));
                    placesModelList.add(new TopStreetView_Model("University of Padua",45.407039,11.877266 ));
                    placesModelList.add(new TopStreetView_Model("Bocconi University",45.450165,9.188318 ));
                    placesModelList.add(new TopStreetView_Model("University of Turin",45.069367,7.688842 ));
                    placesModelList.add(new TopStreetView_Model("University of Rome Tor Vergata",41.846137,12.627215 ));
                    placesModelList.add(new TopStreetView_Model("University of Milano-Bicocca",45.516449,9.213339 ));
                    placesModelList.add(new TopStreetView_Model("Polytechnic University of Turin",45.054172,7.686527 ));
                    placesModelList.add(new TopStreetView_Model("University of Milan",45.460261,9.194908 ));
                    break;
                    case "mexico-universities":
                    tvTitle.setText(String.valueOf("mexico-universities"));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus León",21.166254,-101.715729 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Tecnologica de Mexico - UNITEC Campus Atizapan",19.546258,-99.24108 ));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnologico y de Estudios Superiores de Monterrey Campus Santa Anita",20.568866,-103.484847 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Nacional Autónoma de México",19.332765,-99.18857 ));
                        placesModelList.add(new TopStreetView_Model("Waseda University Waseda Campus",35.709066,139.720978 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Panamericana Campus Ciudad de México",19.37298,-99.183641 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Valle de México Campus Zapopan",20.678434,-103.455799 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Futbol y Ciencias del Deporte",20.137604,-98.812491 ));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Cuernavaca",18.807425,-99.220563 ));
                        placesModelList.add(new TopStreetView_Model("Universidad de Quintana Roo (UQROO)",18.521614,-88.270896 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Iberoamericana Ciudad de Mexico",19.370894,-99.26396 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Istmo Campus Ixtepec",16.53348,-94.95664 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Tecnologica de Mexico - UNITEC Campus Cuitlahuac",19.459446,-99.174992 ));
                        placesModelList.add(new TopStreetView_Model("Universidad de Guadalajara Explanada Rectoría General",20.674975,-103.358906 ));
                        placesModelList.add(new TopStreetView_Model("Centro Universitario de Ciencias Económico Administrativas",20.741397,-103.379994 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Istmo UNISTMO Campus Tehuantepec",16.288792,-95.240803 ));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnologico y de Estudios Superiores de Monterrey Campus Hidalgo",20.096312,-98.765384 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Tecnológica de la Mixteca",17.8279,-97.802957 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Mar Campus Puerto Angel",15.662522,-96.500335 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Tecnologica de Mexico - UNITEC Campus Marina",19.448182,-99.180752 ));
                        placesModelList.add(new TopStreetView_Model("Centro Universitario de Ciencias Sociales y Humanidades Campus Belenes",19.448182,-99.180752 ));
                        placesModelList.add(new TopStreetView_Model("Centro Universitario de Arte, Arquitectura y Diseño",20.678553,-103.344964 ));
                        placesModelList.add(new TopStreetView_Model("Centro Universitario de Arte, Arquitectura y Diseño Campus Huentitán",20.740066,-103.311935 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Marista de Queretaro",20.601382,-100.3854 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Politecnica de Queretaro",20.546069,-100.275035 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Autonoma De Queretaro Centro de Negocio",20.704472,-100.441848 ));
                        placesModelList.add(new TopStreetView_Model("Universidad Juárez Autónoma de Tabasco (UJAT)",20.704472,-100.441848 ));
                        placesModelList.add(new TopStreetView_Model("Universidad de las Americas Puebla",19.053896,-98.282928 ));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Celaya",20.548296,-100.883973 ));
                        placesModelList.add(new TopStreetView_Model("Universidad del Valle de México Campus Tlalpan", 19.294824,-99.143945));
                        placesModelList.add(new TopStreetView_Model("Instituto Tecnológico y de Estudios Superiores de Monterrey Campus Irapuato",20.68605,-101.394252 ));
                        break;
                case "Indian Universties":
                    tvTitle.setText(String.valueOf("Indian Universties"));
                placesModelList.add(new TopStreetView_Model("All India Institute of Medical Sciences (AIIMS), Delhi",28.567468,77.208384 ));
                placesModelList.add(new TopStreetView_Model("Vellore Institute of Technology (VIT), Vellore",12.969701,79.158104 ));
                placesModelList.add(new TopStreetView_Model("Jamia Milia Islamia University, Delhi",28.562955,77.284264 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Technology (IIT), Roorkee",29.86555,77.896581 ));
                placesModelList.add(new TopStreetView_Model("BITS Pillani, Goa",15.387317,73.875597 ));
                placesModelList.add(new TopStreetView_Model("Indian School of Business (ISB), Mohali",30.669863,76.727108 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Management (IIM), Indore",22.626888,75.790345 ));
                placesModelList.add(new TopStreetView_Model("Indian School of Business (ISB), Hyderabad",17.435343,78.341366 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Management (IIM), Kozhikode",11.293988,75.873504 ));
                placesModelList.add(new TopStreetView_Model("University of Hyderabad (HCU), Hyderabad",17.460505,78.334488 ));
                placesModelList.add(new TopStreetView_Model("Amity University, Noida",28.5442,77.334446 ));
                placesModelList.add(new TopStreetView_Model("Central University of Kerala, Kasargod",12.390484,75.096256 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Technology (IIT), Delhi",28.54563,77.196389 ));
                placesModelList.add(new TopStreetView_Model("Sant Longowal Institute of Engineering & Technology (SLIET), Sangrur",30.220265,75.706786 ));
                placesModelList.add(new TopStreetView_Model("Amity University, Jaipur",27.178111,75.95865 ));
                placesModelList.add(new TopStreetView_Model("Central University of Haryana, Mahendergarh",28.360108,76.146814 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Calicut",11.321778,75.934823 ));
                placesModelList.add(new TopStreetView_Model("Central University of Punjab, Bhatinda",30.170802,74.968065 ));
                placesModelList.add(new TopStreetView_Model("Dr. Harisingh Gaur Vishwa Vidyalaya, Sagar",23.824671,78.771614 ));
                placesModelList.add(new TopStreetView_Model("Central University of Kerala, Kasargod",12.390484,75.096256 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Ponda",15.412579,73.977945 ));
                placesModelList.add(new TopStreetView_Model("Singhad Business School, Pune",18.50789,73.835433 ));
                placesModelList.add(new TopStreetView_Model("Great Lakes Institute of Management, Chennai",12.572806,80.1405 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Information Technology (IIIT), Allahabad",25.429252,81.770023 ));
                placesModelList.add(new TopStreetView_Model("S P Jain School of Global Management, Mumbai",19.123102,72.837124 ));
                placesModelList.add(new TopStreetView_Model("BITS Pilani, Hyderabad",17.545243,78.571248 ));
                placesModelList.add(new TopStreetView_Model("O P Jindal Global University, Sonipat",28.926207,77.057803 ));
                placesModelList.add(new TopStreetView_Model("Indian Statistical Institute (ISI), Kolkata",22.648033,88.377354 ));
                placesModelList.add(new TopStreetView_Model("Graphic Era University, Dehradun",30.267619,77.99556 ));
                placesModelList.add(new TopStreetView_Model("Indian Council of Social Science Research (ICSSR), Delhi",28.531796,77.170418 ));
                placesModelList.add(new TopStreetView_Model("Malaviya National Institute of Technology (MNIT), Jaipur",26.865095,75.807681 ));
                placesModelList.add(new TopStreetView_Model("BITS Pilani, Pillani",28.363249,75.586376 ));
                placesModelList.add(new TopStreetView_Model("Guru Ghasidas Vishwavidyalaya, Bilaspur",22.127849,82.138067 ));
                placesModelList.add(new TopStreetView_Model("Manipal Univesity, Manipal",13.353243,74.785051 ));
                placesModelList.add(new TopStreetView_Model("Mahatma Gandhi Antarrashtriya Hindi Vishwavidyalaya, Vardha",20.767319,78.583928 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Technology (IIT), Mumbai",19.125228,72.916532 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Science Education & Research (IISER), Chandigarh",30.662391,76.730276 ));
                placesModelList.add(new TopStreetView_Model("The English and Foreign Languages University (EFLU), Hyderabad",17.424398,78.526317 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Information Technology and Management (IIITM), Gwalior",26.248752,78.177105 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Management (IIM), Kolkata",22.444915,88.297018 ));
                placesModelList.add(new TopStreetView_Model("Jawaharlal Nehru University (JNU), Delhi",28.551117,77.169338 ));
                placesModelList.add(new TopStreetView_Model("Visvesvaraya National Institute of Technology, Nagpur",21.130898,79.050975 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Jallandhar",31.394273,75.533069 ));
                placesModelList.add(new TopStreetView_Model("Indian Council of Historical Research (ICHR), Delhi",28.624843,77.230319 ));
                placesModelList.add(new TopStreetView_Model("Graphic Era Hill University, Bhimtal",29.375521,79.531334 ));
                placesModelList.add(new TopStreetView_Model("Indian Statistical Institute (ISI), Delhi",28.539677,77.186208 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Technology (IIT), Ropar",30.974643,76.539266 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technical Teachers' Training & Research, Bhopal",23.240555,77.392753 ));
                placesModelList.add(new TopStreetView_Model("Pune Institute of Computers (PIC), Pune",18.4574,73.851343 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Delhi",28.843595,77.104849 ));
                placesModelList.add(new TopStreetView_Model("University Grants Commission (UGC), Delhi",28.629468,77.240127 ));
                placesModelList.add(new TopStreetView_Model("Shri Lal Bahadur Shastri Rashtriya Sanskrit Vidyapeeth, Delhi",28.541411,77.182575 ));
                placesModelList.add(new TopStreetView_Model("Manipal University - School of Life Sciences, Manipal",13.346296,74.788903 ));
                placesModelList.add(new TopStreetView_Model("Rashtriya Sanskrit Sansthan, Delhi",28.610665,77.103242 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technical Teachers' Training & Research, Chandigarh",30.729787,76.807211 ));
                placesModelList.add(new TopStreetView_Model("Indian Institute of Foreign Trade (IIFT), Delhi",28.536737,77.183385 ));
                placesModelList.add(new TopStreetView_Model("Management Development Institute (MDI), Gurgaon",28.471153,77.058435 ));
                placesModelList.add(new TopStreetView_Model("Amity University, Gwalior",26.274333,78.223746 ));
                placesModelList.add(new TopStreetView_Model("Amity University, Manesar",28.316818,76.914267 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Warangal",17.98442,79.530763 ));
                placesModelList.add(new TopStreetView_Model("IMT Hyderbad, Hyderabad",17.212175,78.367198 ));
                placesModelList.add(new TopStreetView_Model("Lovely Professional University (LPU), Phagwara",31.260742,75.70698 ));
                placesModelList.add(new TopStreetView_Model("Indira Gandhi National Tribal University, Anuppur",22.803324,81.752626 ));
                placesModelList.add(new TopStreetView_Model("Maulana Azad National Urdu University (MANUU), Hyderabad",17.428866,78.361759 ));
                placesModelList.add(new TopStreetView_Model("ABV - Indian Institute of Information Technology, Design & Manufacturing (IIITDM), Jabalpur",23.177927,80.025671 ));
                placesModelList.add(new TopStreetView_Model("Manipal Institute of Technology (MIT), Manipal",13.352989,74.791419 ));
                placesModelList.add(new TopStreetView_Model("Shiv Nadar University, Noida",28.525524,77.574021 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Technology (NIT), Surthakal",13.01067,74.79408 ));
                placesModelList.add(new TopStreetView_Model("School of Planning & Architecture, Delhi",28.628939,77.246142 ));
                placesModelList.add(new TopStreetView_Model("National Institute of Industrial Engineering (NIIE), Mumbai",19.136655,72.901388 ));
                placesModelList.add(new TopStreetView_Model("Graphic Era Hill University, Dehradun",30.273095,77.999475 ));
                placesModelList.add(new TopStreetView_Model("Maulana Azad National Institute of Technology (MANIT), Bhopal",23.220814,77.404093 ));
                break;
                case "Japanese University":
                    tvTitle.setText(String.valueOf("Japanese University"));
                    placesModelList.add(new TopStreetView_Model("Waseda University Waseda Campus",35.709066,139.720978 ));
                    placesModelList.add(new TopStreetView_Model("Senshu University Kanda Campus",35.696462,139.754461 ));
                    placesModelList.add(new TopStreetView_Model("Sakushin Gakuin University",36.542909,139.97662 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo Gakugei University",35.704135,139.490661 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo Univeristy of Foreign Studies", 35.674818,139.520083));
                    placesModelList.add(new TopStreetView_Model("Takasaki City University of Economics",36.346095,138.977549 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo University of Marine Science and Technology - Shinagawa Campus",35.628441,139.74773 ));
                    placesModelList.add(new TopStreetView_Model("Aoyamagakuin University Aoyama Campus",35.661255,139.709327 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo University of Agriculture and Technology Fuchu Campus",35.684156,139.481029 ));
                    placesModelList.add(new TopStreetView_Model("Shimane University Matsue Campus",35.484649,133.068119 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo University of Agriculture and Technology Koganei Campus",35.699483,139.520227 ));
                    placesModelList.add(new TopStreetView_Model("Toyama University - Gofuku Campus",36.70049,137.188854 ));
                    placesModelList.add(new TopStreetView_Model("Hokkaido University",43.073133,141.341096 ));
                    placesModelList.add(new TopStreetView_Model("Kyushu University Hakozaki Campus",33.622037,130.425451 ));
                    placesModelList.add(new TopStreetView_Model("Akita International University",39.626138,140.197792 ));
                    placesModelList.add(new TopStreetView_Model("Akita University Tegata Campus",39.726255,140.133597 ));
                    placesModelList.add(new TopStreetView_Model("Ibaraki University Mito Campus",36.400699,140.442306 ));
                    placesModelList.add(new TopStreetView_Model("Saitama University",35.863534,139.607121 ));
                    placesModelList.add(new TopStreetView_Model("Kanda University of International Studies",35.656846,140.042094 ));
                    placesModelList.add(new TopStreetView_Model("Fukuoka University of Education",33.812196,130.594102 ));
                    placesModelList.add(new TopStreetView_Model("The University of Electro-Communications",35.65568,139.543748 ));
                    placesModelList.add(new TopStreetView_Model("Kyushu Institute of Technology Iizuka Campus",33.652708,130.672083 ));
                    placesModelList.add(new TopStreetView_Model("Kyushu Women’s Junior College",33.868873,130.708374 ));
                    placesModelList.add(new TopStreetView_Model("Kyushu Women’s University",33.86883,130.709242 ));
                    placesModelList.add(new TopStreetView_Model("Seinan Gakuin University",33.58534,130.356652 ));
                    placesModelList.add(new TopStreetView_Model("Future University Hakodate",41.841605,140.765455 ));
                    placesModelList.add(new TopStreetView_Model("Hokkaido Institute Of Technology",43.134325,141.248246 ));
                    placesModelList.add(new TopStreetView_Model("Hokusei Gakuen University",43.025073,141.448337 ));
                    placesModelList.add(new TopStreetView_Model("Rakuno Gakuen University",43.07368,141.510707 ));
                    placesModelList.add(new TopStreetView_Model("Sapporo University",43.010366,141.387089 ));
                    placesModelList.add(new TopStreetView_Model("Senshu University Ikuta Campus",35.611101,139.553422 ));
                    placesModelList.add(new TopStreetView_Model("Tokai University Shonan Campus",35.365579,139.277705 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo City University",35.562211,139.574539 ));
                    placesModelList.add(new TopStreetView_Model("Yokohama College of Art and Design",35.557593,139.496744 ));
                    placesModelList.add(new TopStreetView_Model("Kumamoto Gakuen University",32.804137,130.730319 ));
                    placesModelList.add(new TopStreetView_Model("Kyoto Gakuen University",34.993748,135.552422 ));
                    placesModelList.add(new TopStreetView_Model("Tohoku Fukushi University Kunimigaoka First Campus",38.282791,140.834522 ));
                    placesModelList.add(new TopStreetView_Model("Tohoku University Aobayama Campus",38.257406,140.8371 ));
                    placesModelList.add(new TopStreetView_Model("Niigata University of Health and Welfare",37.966631,139.185709 ));
                    placesModelList.add(new TopStreetView_Model("University of Niigata Prefecture",37.92347,139.12622 ));
                    placesModelList.add(new TopStreetView_Model("Okinawa Prefectural University of Art",26.219001,127.719196 ));
                    placesModelList.add(new TopStreetView_Model("Nihon University College of Humanities and Sciences",35.663085,139.634321 ));
                    placesModelList.add(new TopStreetView_Model("Hannan University Main Campus",34.591306,135.535597 ));
                    placesModelList.add(new TopStreetView_Model("Osaka Institute of Technology",34.730513,135.544105 ));
                    placesModelList.add(new TopStreetView_Model("St Agnes College Takatsuki Campus",34.867913,135.586848 ));
                    placesModelList.add(new TopStreetView_Model("Daito Bunka University Higashi Matsuyama Campus",36.000833,139.368473 ));
                    placesModelList.add(new TopStreetView_Model("University of the Sacred Heart, Tokyo",35.109487,135.902077 ));
                    placesModelList.add(new TopStreetView_Model("The University of Tokushima Shinkura Campus",34.070008,134.559554 ));
                    placesModelList.add(new TopStreetView_Model("Showa Woman's University",35.683844,139.73131 ));
                    placesModelList.add(new TopStreetView_Model("Takachiho University",35.683473,139.638373 ));
                    placesModelList.add(new TopStreetView_Model("Tokyo Metropolitan University Minami-Osawa Campus",35.616329,139.377962 ));
                    placesModelList.add(new TopStreetView_Model("Takushoku University Hachioji Campus",35.625541,139.278294 ));
                    placesModelList.add(new TopStreetView_Model("Tottori University of Environmental studies",35.450706,134.256162 ));
                    placesModelList.add(new TopStreetView_Model("Teikyo University Hachioji Campus",35.642348,139.419632 ));
                    placesModelList.add(new TopStreetView_Model("Rissyo University Kumagaya Campus",36.108505,139.364863 ));
                    placesModelList.add(new TopStreetView_Model("Seian University of art and design",35.651299,139.719275 ));
                    placesModelList.add(new TopStreetView_Model("Kobe University Rokkodai First Campus",34.72779,135.234421 ));
                    placesModelList.add(new TopStreetView_Model("International University of Health and Welfare",36.870767,140.070493 ));
                    placesModelList.add(new TopStreetView_Model("Iwate Prefectural University Takizawa Campus", 39.800309,141.138382));
                    placesModelList.add(new TopStreetView_Model("Wakayama University",34.26713,135.151255 ));
                    placesModelList.add(new TopStreetView_Model("Chukyo University", 35.137665,136.965615));
                    placesModelList.add(new TopStreetView_Model("Toyo University Hakusan Campus",35.722883,139.750474 ));
                    placesModelList.add(new TopStreetView_Model("Osaka University Suita Campus",34.8229,135.519149 ));
                    placesModelList.add(new TopStreetView_Model("Osaka University Minoh Campus",34.852219,135.517361 ));
                    placesModelList.add(new TopStreetView_Model("Kurume Institute of Technology",33.266001,130.53799 ));
                    placesModelList.add(new TopStreetView_Model("Yamanashi Eiwa College",35.660996,138.614367 ));
                    placesModelList.add(new TopStreetView_Model("Fukuyama City University, Kitahonjo Campus",34.500054,133.348045 ));
                    placesModelList.add(new TopStreetView_Model("Ohkagakuen University / Nagoya College",35.059196,136.97584 ));
                    placesModelList.add(new TopStreetView_Model("Osaka University Toyonaka Campus",34.801422,135.454598 ));
                    placesModelList.add(new TopStreetView_Model("Tama Art University Hachioji Campus",35.611219,139.350093 ));
                    placesModelList.add(new TopStreetView_Model("Kitami Institute of Technology",43.822761,143.906071 ));
                    placesModelList.add(new TopStreetView_Model("Fukuyama University",34.458003,133.230837 ));
                    placesModelList.add(new TopStreetView_Model("Nagaoka Institute of Design",37.466743,138.829685 ));
           break;
                case "Uk Universties":
                    tvTitle.setText(String.valueOf("Uk Universties"));
                    placesModelList.add(new TopStreetView_Model("St Cross College, Oxford",51.756454,-1.260214 ));
                    placesModelList.add(new TopStreetView_Model("Hertford College, Oxford",51.754144,-1.253267 ));
                    placesModelList.add(new TopStreetView_Model("All Souls College, Oxford",51.753805,-1.252984 ));
                    placesModelList.add(new TopStreetView_Model("St. John’s College, Oxford",51.756157,-1.258111 ));
                    placesModelList.add(new TopStreetView_Model("Jesus College, Oxford",51.753401,-1.257321 ));
                    placesModelList.add(new TopStreetView_Model("Oriel College, Oxford",51.751626,-1.253588 ));
                    placesModelList.add(new TopStreetView_Model("Corpus Christi College",51.750958,-1.25361 ));
                    placesModelList.add(new TopStreetView_Model("Merton College, Oxford",51.750969,-1.251037 ));
                    placesModelList.add(new TopStreetView_Model("Pembroke College, Oxford",51.750013,-1.258388 ));
                    placesModelList.add(new TopStreetView_Model("St Edmund Hall, Oxford",51.753056,-1.250008 ));
                    placesModelList.add(new TopStreetView_Model("Lady Margaret Hall, Oxford",51.764973,-1.253923 ));
                    placesModelList.add(new TopStreetView_Model("Somerville College, Oxford",51.75975,-1.261526 ));
                    placesModelList.add(new TopStreetView_Model("Wadham College, Oxford",51.755665,-1.254435 ));
                    placesModelList.add(new TopStreetView_Model("New College, Oxford",51.754013,-1.250561 ));
                    placesModelList.add(new TopStreetView_Model("Magdalen College, Oxford",51.752378,-1.245741 ));
                    placesModelList.add(new TopStreetView_Model("Harris Manchester College, Oxford",51.755795,-1.252457 ));
                    placesModelList.add(new TopStreetView_Model("Green Templeton College, Oxford",51.761254,-1.262872 ));
                    placesModelList.add(new TopStreetView_Model("St Peter's College, Oxford",51.752641,-1.261067 ));
                    placesModelList.add(new TopStreetView_Model("St Stephen's House, Oxford",51.7464,-1.240543 ));
                    placesModelList.add(new TopStreetView_Model("Trinity College, Oxford",51.755636,-1.257216 ));
                    placesModelList.add(new TopStreetView_Model("Saïd Business School, Oxford",51.753993,-1.268429 ));
                    placesModelList.add(new TopStreetView_Model("Trinity Hall, Cambridge",52.205772,0.115165 ));
                    placesModelList.add(new TopStreetView_Model("St John's College, Cambridge",52.208551,0.115264 ));
                    placesModelList.add(new TopStreetView_Model("Gonville & Caius College, Cambridge",52.20609,0.116645 ));
                    placesModelList.add(new TopStreetView_Model("Jesus College, Cambridge",52.209475,0.124327 ));
                    placesModelList.add(new TopStreetView_Model("Queens' College, Cambridge",52.202297,0.116097 ));
                    placesModelList.add(new TopStreetView_Model("Great St Mary's Church, Cambridg",52.205312,0.118335 ));
                    placesModelList.add(new TopStreetView_Model("Newnham College, Cambridge",52.199766,0.105915 ));
                    placesModelList.add(new TopStreetView_Model("Cambridge University Botanic Garden",52.193585,0.126671 ));
                    placesModelList.add(new TopStreetView_Model("Durham University",54.764616,-1.570332 ));
                    placesModelList.add(new TopStreetView_Model("Keele University",52.999469,-2.270576 ));
                    placesModelList.add(new TopStreetView_Model("Cranfield University",52.07564,-0.625413 ));
                    placesModelList.add(new TopStreetView_Model("The University of Sheffield",53.3818,-1.488559 ));
                    placesModelList.add(new TopStreetView_Model("Swansea University",53.3818,-1.488559 ));
                    placesModelList.add(new TopStreetView_Model("University of Nottingham",52.937877,-1.198891 ));
                    placesModelList.add(new TopStreetView_Model("University College London",51.524285,-0.133657 ));
                    placesModelList.add(new TopStreetView_Model("Cardiff University",51.487556,-3.179933 ));
                    placesModelList.add(new TopStreetView_Model("University of St. Andrews",56.341862,-2.794353 ));
                    placesModelList.add(new TopStreetView_Model("Goldsmiths, University of London",51.475405,-0.037873 ));
                    break;
                case "Turkey Universities":
                    tvTitle.setText(String.valueOf("Turkey Universities"));
                placesModelList.add(new TopStreetView_Model("Bogazici University",41.08337,29.051893 ));
                placesModelList.add(new TopStreetView_Model("Istanbul Technical University Taşkışla Campus",41.041185,28.989549 ));
                placesModelList.add(new TopStreetView_Model("Istanbul Technical University - Ayazağa Campus",41.107213,29.021409 ));
                placesModelList.add(new TopStreetView_Model("Dokuz Eylül University",38.375913,27.193272 ));
                placesModelList.add(new TopStreetView_Model("Izmir Institute of Technology",38.3191,26.642493 ));
                placesModelList.add(new TopStreetView_Model("Koç University",41.205301,29.072375 ));
                placesModelList.add(new TopStreetView_Model("İhsan Doğramacı Bilkent University",39.869076,32.748825 ));
                placesModelList.add(new TopStreetView_Model("Yıldız Technical University",41.052244,29.010297 ));
                placesModelList.add(new TopStreetView_Model("Çankaya University",39.820314,32.558238 ));
                placesModelList.add(new TopStreetView_Model("Middle East Technical University",39.894891,32.784226 ));
                placesModelList.add(new TopStreetView_Model("Uludağ University",40.222531,28.866769 ));
                placesModelList.add(new TopStreetView_Model("Kadir Has University",41.023806,28.960115 ));
                placesModelList.add(new TopStreetView_Model("İstanbul Arel University",41.055541,28.500263 ));
                placesModelList.add(new TopStreetView_Model("Erciyes University",38.709911,35.533571 ));
                placesModelList.add(new TopStreetView_Model("Marmara University",40.985824,29.050737 ));
                placesModelList.add(new TopStreetView_Model("Kocaeli University",40.821666,29.930177 ));
                placesModelList.add(new TopStreetView_Model("Gebze Technical University",40.808987,29.36524 ));
                placesModelList.add(new TopStreetView_Model("Sakarya University",40.744192,30.340515 ));
                placesModelList.add(new TopStreetView_Model("Eskişehir Osmangazi University - Meşelik Campus",39.754272,30.488212 ));
                placesModelList.add(new TopStreetView_Model("Eskişehir Osmangazi University - Bademlik Campus",39.759731,30.523353 ));
                placesModelList.add(new TopStreetView_Model("Eskişehir Osmangazi University - Ali Numan Kıraç Campus",39.758997,30.474025 ));
                placesModelList.add(new TopStreetView_Model("Çanakkale Onsekiz Mart University",40.106525,26.410705 ));
                placesModelList.add(new TopStreetView_Model("Izmir University of Economics",38.388103,27.044711 ));
                placesModelList.add(new TopStreetView_Model("Selçuk University",38.016494,32.512631 ));
                placesModelList.add(new TopStreetView_Model("Balıkesir University",39.541275,28.006526 ));
                placesModelList.add(new TopStreetView_Model("Süleyman Demirel University",37.832497,30.529508 ));
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
            TopStreetView_Model placesModel = placesModelList.get(mPosition);
            startActivity(new Intent(context, StreetView_Map_Activity.class)
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
