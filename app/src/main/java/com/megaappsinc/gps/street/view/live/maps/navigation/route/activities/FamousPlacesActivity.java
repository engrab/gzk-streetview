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

public class FamousPlacesActivity extends AppCompatActivity {
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
            context = FamousPlacesActivity.this;
            tvTitle = findViewById(R.id.tvTitle);
            tvTitle.setTypeface(null, Typeface.ITALIC);
            listOptions();
            AppPurchasePref appPurchasePref = new AppPurchasePref(FamousPlacesActivity.this);
            if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {

                BannerAdmob();
                requestNewInterstitial();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestLoadInterstitial();

                            clickListener();

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listOptions() {
        try {
            placesModelList = new ArrayList<>();
            tvTitle.setText("Famous Street View");
            placesModelList.add(new TopStreetViewModel("Discover Force Island", 62.093947, -7.413442));
            placesModelList.add(new TopStreetViewModel("Discover South Africa", -23.8935, 31.546686));
            placesModelList.add(new TopStreetViewModel("Mexico Shopping Malls", 19.3617076, -99.2759285));
            placesModelList.add(new TopStreetViewModel("Archaeological Survey Of India", 27.174628, 78.041666));
            placesModelList.add(new TopStreetViewModel("Universities In India", 28.5674181, 77.2086014));
            placesModelList.add(new TopStreetViewModel("Day Of The Dead In Mexico", 19.432567, -99.133045));
            placesModelList.add(new TopStreetViewModel("This Is Home", 47.527282, 108.551732));
            placesModelList.add(new TopStreetViewModel("International Space Station", 29.560285, -95.085391));
            placesModelList.add(new TopStreetViewModel("Quttinirpaaq National Park Nunavut Canada", 76.420794, -82.894116));
            placesModelList.add(new TopStreetViewModel("Street View Challenge Vienna", 48.197792, 16.353052));
            placesModelList.add(new TopStreetViewModel("Indigenous Culture Around The World", 22.448303, 120.712419));
            placesModelList.add(new TopStreetViewModel("Russian Landmarks", 55.751828, 37.613355));
            placesModelList.add(new TopStreetViewModel("Brazil National Parks", -2.66387, -42.858229));
            placesModelList.add(new TopStreetViewModel("Buenos Aires Parks And Squares", -34.605717, -58.435908));
            placesModelList.add(new TopStreetViewModel("Queretaro Cradle Of Independence", 20.593144, -100.389723));
            placesModelList.add(new TopStreetViewModel("Argentina Provincia De San Juan Highlights", -31.534914, -68.529155));
            placesModelList.add(new TopStreetViewModel("Malaysia Highlights", 2.897892, 104.168805));
            placesModelList.add(new TopStreetViewModel("Indonesia Highlights", -8.680658, 119.556821));
            placesModelList.add(new TopStreetViewModel("Tangier Island", 37.828361, -75.994872));
            placesModelList.add(new TopStreetViewModel("Discover Nigeria", 6.450035, 3.397014));
            placesModelList.add(new TopStreetViewModel("Game Of Thrones Locations", 42.64028, 18.112402));
            placesModelList.add(new TopStreetViewModel("Korea National Parks", 35.781168, 129.222871));
            placesModelList.add(new TopStreetViewModel("Getting Around Rio Transportation", -22.903306, -43.191453));
            placesModelList.add(new TopStreetViewModel("New South Wales National Parks", -36.455828, 148.263492));
            placesModelList.add(new TopStreetViewModel("Mexico National Parks And Protected Natural Areas", 21.083667, -99.658596));
            placesModelList.add(new TopStreetViewModel("Argentina Provincia De Corrientes Highlights", -27.26913, -58.243943));
            placesModelList.add(new TopStreetViewModel("Uluru Kata Tjuta National Park", -25.365823, 131.064245));
            placesModelList.add(new TopStreetViewModel("Oceans", 26.202737, 127.289096));
            placesModelList.add(new TopStreetViewModel("Tunisia Highlights", 36.42261, 9.218203));
            placesModelList.add(new TopStreetViewModel("Discover Malta", 35.901236, 14.512841));
            placesModelList.add(new TopStreetViewModel("Iran By Willy Kaemena", 29.608163, 52.55278));
            placesModelList.add(new TopStreetViewModel("Bussana Vecchia Italy", 43.837963, 7.829236));
            placesModelList.add(new TopStreetViewModel("Guatemala Highlights", 14.695105, -91.274181));
            placesModelList.add(new TopStreetViewModel("New Zealand Highlight", -44.685526, 167.898206));
            placesModelList.add(new TopStreetViewModel("Mbrym Volcano Vanuatu", -16.250015, 168.135919));
            placesModelList.add(new TopStreetViewModel("Greenland Highlight", 69.207943, -51.163007));
            placesModelList.add(new TopStreetViewModel("Chile National Parks", -41.111308, -72.399388));
            placesModelList.add(new TopStreetViewModel("Grand Tour", 43.317136, 11.330675));
            placesModelList.add(new TopStreetViewModel("Norway Highlights", 60.132973, 6.754007));
            placesModelList.add(new TopStreetViewModel("Zugspitze Germany", 47.421215, 10.98632));
            placesModelList.add(new TopStreetViewModel("Singapore Highlights", 1.221572, 103.848377));
            placesModelList.add(new TopStreetViewModel("Quebec Street View Challenge", 46.807648, -71.208281));
            placesModelList.add(new TopStreetViewModel("New Forest National Park Uk", 50.957255, -1.752088));
            placesModelList.add(new TopStreetViewModel("Pembrokeshire Coast Path", 52.058723, -4.785427));
            placesModelList.add(new TopStreetViewModel("Ghana Highlights", 5.553927, -0.200554));
            placesModelList.add(new TopStreetViewModel("Machu Picchu", -13.165071, -72.544715));
            placesModelList.add(new TopStreetViewModel("Senegal Highlights", 14.673522, -17.449128));
            placesModelList.add(new TopStreetViewModel("Puerto Rico Highlights", 18.471385, -66.124589));
            placesModelList.add(new TopStreetViewModel("Mont Blanc", 45.832617, 6.865173));
            placesModelList.add(new TopStreetViewModel("Thailand Highlights", 7.213689, 99.063963));
            placesModelList.add(new TopStreetViewModel("Taiwan Highlights", 23.487231, 120.959349));
            placesModelList.add(new TopStreetViewModel("Okinawa", 26.202737, 127.289096));
            placesModelList.add(new TopStreetViewModel("Argentina National Parks", -25.687667, -54.443836));
            placesModelList.add(new TopStreetViewModel("Yosemite", 37.729414, -119.635868));
            placesModelList.add(new TopStreetViewModel("The Floating Piers", 45.694772, 10.08015));
            placesModelList.add(new TopStreetViewModel("Great Walks Of New Zealand", -45.394324, 167.529843));
            placesModelList.add(new TopStreetViewModel("California State Parks", 37.24498, -122.145735));
            placesModelList.add(new TopStreetViewModel("Argentina Highlights", -34.607794, -58.370284));
            placesModelList.add(new TopStreetViewModel("Rottnest Island", -32.023521, 115.450708));
            placesModelList.add(new TopStreetViewModel("Petra", 30.321265, 35.463307));
            placesModelList.add(new TopStreetViewModel("Philippines Highlights", 20.394425, 121.964275));
            placesModelList.add(new TopStreetViewModel("Loire Castles France", 47.181402, 0.05171));
            placesModelList.add(new TopStreetViewModel("Merrial Beach Track", -10.474312, 105.559443));
            placesModelList.add(new TopStreetViewModel("Dolly Beach", -10.520641, 105.675738));
            placesModelList.add(new TopStreetViewModel("The Dales", -10.483353, 105.554388));
            placesModelList.add(new TopStreetViewModel("West White Beach", -10.460865, 105.579462));
            placesModelList.add(new TopStreetViewModel("Ethel Beach", -10.463977, 105.708019));
            placesModelList.add(new TopStreetViewModel("Greta Beach", -10.50195, 105.674651));
            placesModelList.add(new TopStreetViewModel("Lily Beach", -10.466893, 105.711355));
            placesModelList.add(new TopStreetViewModel("The Blowholes", -10.514544, 105.622572));
            placesModelList.add(new TopStreetViewModel("Winifred Beach", -10.496783, 105.546169));
            placesModelList.add(new TopStreetViewModel("Golf Course Lookout", -10.423823, 105.698357));
            placesModelList.add(new TopStreetViewModel("The Grotto", -10.42308, 105.701954));
            placesModelList.add(new TopStreetViewModel("Martin Point", -10.469337, 105.557799));
            placesModelList.add(new TopStreetViewModel("Margaret Knoll Lookout", -10.477045, 105.684215));
            placesModelList.add(new TopStreetViewModel("The Dales", -10.478835, 105.55961));
            placesModelList.add(new TopStreetViewModel("Boulder track", -10.549466, 105.638712));
            placesModelList.add(new TopStreetViewModel("Creative Time", 40.715867, -73.967208));
            placesModelList.add(new TopStreetViewModel("Frederick Douglass National Historic Site", 38.863443, -76.985178));
            placesModelList.add(new TopStreetViewModel("Dexter Avenue King Memorial Church", 32.377731, -86.302505));
            placesModelList.add(new TopStreetViewModel("Ebenezer Baptist Church", 33.75551, -84.374349));
            placesModelList.add(new TopStreetViewModel("16th Street Baptist Church (Birmingham, AL)", 33.516559, -86.814599));
            placesModelList.add(new TopStreetViewModel("Montgomery Greyhound Bus Station", 32.374575, -86.308916));
            placesModelList.add(new TopStreetViewModel("Little Rock Central High School", 34.736786, -92.297827));
            placesModelList.add(new TopStreetViewModel("Museum of African American History, Boston Campus", 42.359974, -71.065202));
            placesModelList.add(new TopStreetViewModel("Edmund Pettus Bridge", 32.405518, -87.018526));
            placesModelList.add(new TopStreetViewModel("Daisy Bates House", 34.721838, -92.288331));
            placesModelList.add(new TopStreetViewModel("Brown v. Board of Education", 39.037806, -95.676158));
            placesModelList.add(new TopStreetViewModel("Maggie L. Walker National Historic Site", 37.547751, -77.437686));
            placesModelList.add(new TopStreetViewModel("Brown Chapel AME Church", 32.412485, -87.016432));
            placesModelList.add(new TopStreetViewModel("The King Center", 33.754984, -84.373202));
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
            placesModelList.add(new TopStreetViewModel("Mountain Zebra National Park", -32.102187, 25.417349));
            placesModelList.add(new TopStreetViewModel("Black Forest", 47.8445341, 7.9329069));
            placesModelList.add(new TopStreetViewModel("The Tongass National Forest", 55.34975, -131.606925));
            placesModelList.add(new TopStreetViewModel("Sundarbans", 21.949727, 89.18333));
            placesModelList.add(new TopStreetViewModel("Biosphere Reserve", 19.850951, -87.639343));
            placesModelList.add(new TopStreetViewModel("Loosehanger", 50.950576, -1.677872));
            placesModelList.add(new TopStreetViewModel("Sinharaja Forest Reserve", 6.389722, 80.501389));
            placesModelList.add(new TopStreetViewModel("Arashiyama Bamboo Grove", 35.017042, 135.671874));
            placesModelList.add(new TopStreetViewModel("High Corner", 50.89016, -1.718173));
            placesModelList.add(new TopStreetViewModel("Tsingy de Bemaraha National Park", -18.89767, 44.82981));
            placesModelList.add(new TopStreetViewModel("Kruger National Park", -23.8935, 31.546686));
            placesModelList.add(new TopStreetViewModel("Great Otway National Park", -38.791218, 143.5419233));
            placesModelList.add(new TopStreetViewModel("Milkham inclosure", 50.886174, -1.708976));
            placesModelList.add(new TopStreetViewModel("Mossy Forest", 4.524223, 101.381927));
            placesModelList.add(new TopStreetViewModel("Humboldt Redwoods State Park", 40.283723, -123.890317));
            placesModelList.add(new TopStreetViewModel("North Weirs", 50.817399, -1.583032));
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
            placesModelList.add(new TopStreetViewModel("Coral Gardens, Palmyra Atoll, USA", 5.885806, -162.060135));
            placesModelList.add(new TopStreetViewModel("Crazy Corals, Palmyra Atoll, USA", 5.885904, -162.12561));
            placesModelList.add(new TopStreetViewModel("Holei, Palmyra Atoll, USA", 5.866448, -162.067535));
            placesModelList.add(new TopStreetViewModel("Jurassic Park, Palmyra Atoll, USA", 5.858315, -162.101257));
            placesModelList.add(new TopStreetViewModel("Mega Jacks, Palmyra Atoll, USA", 5.864789, -162.14032));
            placesModelList.add(new TopStreetViewModel("Melon headed whales, Palmyra Atoll, USA", 5.859013, -162.104696));
            placesModelList.add(new TopStreetViewModel("Penguin Spit, Palmyra Atoll, USA", 5.87155, -162.110352));
            placesModelList.add(new TopStreetViewModel("Yonaguni Monument, Yonaguni, Okinawa, Japan", 24.435919, 123.011134));
            placesModelList.add(new TopStreetViewModel("Komodo Islands", -8.737039, 119.412259));
            placesModelList.add(new TopStreetViewModel("Nishibama, Aka Island, Okinawa, Japan", 26.202737, 127.289096));
            placesModelList.add(new TopStreetViewModel("The Channel, Palmyra Atoll, USA", 5.872685, -162.111947));
            placesModelList.add(new TopStreetViewModel("Giant Potato, Nagura Bay, Ishigaki, Okinawa, Japan", 24.399209, 124.117781));
            placesModelList.add(new TopStreetViewModel("Yamada Point, Onna, Okinawa, Japan", 26.441502, 127.790058));
            placesModelList.add(new TopStreetViewModel("Aharen Beach, Tokashiki, Okinawa, Japan", 26.168867, 127.343795));
            placesModelList.add(new TopStreetViewModel("Agariushi / Gahi Island, Zamami, Okinawa, Japan", 26.21729, 127.310158));
            placesModelList.add(new TopStreetViewModel("Taketomi-south Shark Home, Taketomi, Okinawa, Japan", 24.284623, 124.078206));
            placesModelList.add(new TopStreetViewModel("Milky Way / Submarine, Sekisei Lagoon, Ishigaki, Okinawa, Japan", 24.300251, 124.08625));
            placesModelList.add(new TopStreetViewModel("Cape Kri, Raja Ampat, Indonesia", -0.556114, 130.690997));
            placesModelList.add(new TopStreetViewModel("Tatawa Kecil, Komodo, Indonesia", -8.53004, 119.62723));
            placesModelList.add(new TopStreetViewModel("Batu Bolong, Komodo, Indonesia", -8.537229, 119.613738));
            placesModelList.add(new TopStreetViewModel("Timor II, Bunaken National Park, Indonesia", 1.618098, 124.781914));
            placesModelList.add(new TopStreetViewModel("Leukan III A, Bunaken National Park, Indonesia", 1.602118, 124.766276));
            placesModelList.add(new TopStreetViewModel("Fukui, Bunaken National Park, Indonesia", 1.611651, 124.739282));
            placesModelList.add(new TopStreetViewModel("Minke Whales on the Ribbon Reef", -14.865306, 145.680527));
            placesModelList.add(new TopStreetViewModel("Shelly Beach, Australia", -33.893774, 151.282125));
            placesModelList.add(new TopStreetViewModel("Galápagos Islands, Ecuador", -1.239203, -90.385735));
            placesModelList.add(new TopStreetViewModel("Crystal Bay, Indonesia", -8.715413, 115.457015));
            placesModelList.add(new TopStreetViewModel("Devil's Crown, Galapagos", -1.216466, -90.42215));
            placesModelList.add(new TopStreetViewModel("Nudi's Retreat, Indonesia", 1.486096, 125.24151));
            placesModelList.add(new TopStreetViewModel("Champion Island East, Galápagos", -1.238894, -90.384239));
            placesModelList.add(new TopStreetViewModel("Hong Chai, Kenting National Park, Taiwan", 21.953572, 120.710359));
            placesModelList.add(new TopStreetViewModel("Nuclear Outlet, Kenting National Park, Taiwan", 21.930845, 120.745026));
            placesModelList.add(new TopStreetViewModel("Da Bai Sha, Green Island, Taiwan", 22.638232, 121.490526));
            placesModelList.add(new TopStreetViewModel("Three Rock, Green Island, Taiwan", 22.639565, 121.490911));
            placesModelList.add(new TopStreetViewModel("Emily's Pinnacles, Bermuda", 32.235303, -64.848371));
            placesModelList.add(new TopStreetViewModel("Rarotonga, Cook Islands", -21.203982, -159.837009));
            placesModelList.add(new TopStreetViewModel("Heron Island, Great Barrier Reef", -23.442896, 151.906584));
            placesModelList.add(new TopStreetViewModel("Muli Kandu, Maldives", 2.927715, 73.590414));
            placesModelList.add(new TopStreetViewModel("The Liberty Wreck, Bali", -8.274153, 115.592694));
            placesModelList.add(new TopStreetViewModel("Batu Rufus, Indonesia", -0.565927, 130.284558));
            placesModelList.add(new TopStreetViewModel("Big Momma, American Samoa", -14.259231, -169.500376));
            placesModelList.add(new TopStreetViewModel("Airport Reef, American Samoa", -14.328972, -170.70172));
            placesModelList.add(new TopStreetViewModel("Nelson Island Outside, Chagos", -5.683498, 72.325919));
            placesModelList.add(new TopStreetViewModel("Tafeu Cove, American Samoa", -14.253016, -170.689206));
            placesModelList.add(new TopStreetViewModel("Whale Sharks at Isla Contoy, Mexico", 21.479702, -86.632599));
            placesModelList.add(new TopStreetViewModel("Aquarius Reef Base, Florida Keys - United States of America", 24.950493, -80.454069));
            placesModelList.add(new TopStreetViewModel("Bleached Coral at Airport Reef, American Samoa - During", -14.328973, -170.70172));
            placesModelList.add(new TopStreetViewModel("Dead Coral at Airport Reef, American Samoa - After", -14.328973, -170.70172));
            placesModelList.add(new TopStreetViewModel("SS Antilla Shipwreck, Aruba", 12.601959, -70.058343));
            placesModelList.add(new TopStreetViewModel("Ile Vache Marine, Chagos, British Indian Ocean Territory", -5.427774, 71.828873));
            placesModelList.add(new TopStreetViewModel("Mayreau Hot Springs, St Vincent & the Grenadines", 12.627667, -61.378149));
            placesModelList.add(new TopStreetViewModel("Cozumel, Mexico", 20.338045, -87.027587));
            placesModelList.add(new TopStreetViewModel("Moresby Island Inside, Chagos, British Indian Ocean Territory", -5.243066, 71.825168));
            placesModelList.add(new TopStreetViewModel("PUE Coral Gardens, Cook Islands", -21.201181, -159.771712));
            placesModelList.add(new TopStreetViewModel("Cheeca Rocks, Florida Keys, USA", 24.904503, -80.616652));
            placesModelList.add(new TopStreetViewModel("Freeport, Grand Bahama", 26.488173, -78.657134));
            placesModelList.add(new TopStreetViewModel("Jessie Beazley Reef, Philippines", 9.043864, 119.819872));
            placesModelList.add(new TopStreetViewModel("Horseshoe Bay, Bermuda", 32.244589, -64.822922));
            placesModelList.add(new TopStreetViewModel("Benwood Wreck, Florida Keys, USA", 25.052668, -80.332617));
            placesModelList.add(new TopStreetViewModel("Jaco Island, Timor Leste", -8.441477, 127.312285));
            placesModelList.add(new TopStreetViewModel("Lizard Island, Great Barrier Reef", -14.685821, 145.442162));
            placesModelList.add(new TopStreetViewModel("Great Detached Reef, Great Barrier Reef", -11.737481, 143.972687));
            placesModelList.add(new TopStreetViewModel("Estudios de Canal 13", -33.427364, -70.627082));
            placesModelList.add(new TopStreetViewModel("The Late Show with Stephen Colbert - 2", 40.763765, -73.98295));
            placesModelList.add(new TopStreetViewModel("Telefe Noticias", -34.620483, -58.387732));
            placesModelList.add(new TopStreetViewModel("Telefe Noticias", -34.62014, -58.387783));
            placesModelList.add(new TopStreetViewModel("Late Show w/ Stephen Colbert", 40.763741, -73.982866));
            placesModelList.add(new TopStreetViewModel("Harry Potter's Diagon Alley", 51.693249, -0.419633));
            placesModelList.add(new TopStreetViewModel("America TV", -34.584441, -58.437018));
            placesModelList.add(new TopStreetViewModel("The Depository, London, UK", 51.566382, -0.075246));
            placesModelList.add(new TopStreetViewModel("Ed Sullivan Theater", 40.763838, -73.982864));
            placesModelList.add(new TopStreetViewModel("The Ed Sullivan Theater - 2", 40.763884, -73.9829));
            placesModelList.add(new TopStreetViewModel("The Late Show With David Letterman - 1", 40.76381, -73.982976));
            placesModelList.add(new TopStreetViewModel("Adam Savage's Cave", 37.75387, -122.420658));
            placesModelList.add(new TopStreetViewModel("Canada AM studio", 43.78189, -79.257404));
            placesModelList.add(new TopStreetViewModel("Tardis", 51.492177, -0.193015));
            placesModelList.add(new TopStreetViewModel("Late Show w/ David Letterman", 40.763851, -73.98288));
            placesModelList.add(new TopStreetViewModel("The Colbert Report studio - 1", 40.767857, -73.990773));
            placesModelList.add(new TopStreetViewModel("The Colbert Report studio - 2", 40.767669, -73.990809));
            placesModelList.add(new TopStreetViewModel("Creative Time", 40.715867, -73.967208));
            placesModelList.add(new TopStreetViewModel("Frederick Douglass National Historic Site", 38.863443, -76.985178));
            placesModelList.add(new TopStreetViewModel("Dexter Avenue King Memorial Church", 32.377731, -86.302505));
            placesModelList.add(new TopStreetViewModel("Ebenezer Baptist Church", 33.75551, -84.374349));
            placesModelList.add(new TopStreetViewModel("16th Street Baptist Church (Birmingham, AL)", 33.516559, -86.814599));
            placesModelList.add(new TopStreetViewModel("Montgomery Greyhound Bus Station", 32.374575, -86.308916));
            placesModelList.add(new TopStreetViewModel("Little Rock Central High School", 34.736786, -92.297827));
            placesModelList.add(new TopStreetViewModel("Museum of African American History, Boston Campus", 42.359974, -71.065202));
            placesModelList.add(new TopStreetViewModel("Edmund Pettus Bridge", 32.405518, -87.018526));
            placesModelList.add(new TopStreetViewModel("Daisy Bates House", 34.721838, -92.288331));
            placesModelList.add(new TopStreetViewModel("Brown v. Board of Education", 39.037806, -95.676158));
            placesModelList.add(new TopStreetViewModel("Maggie L. Walker National Historic Site", 37.547751, -77.437686));
            placesModelList.add(new TopStreetViewModel("Brown Chapel AME Church", 32.412485, -87.016432));
            placesModelList.add(new TopStreetViewModel("The King Center", 33.754984, -84.373202));
            placesModelList.add(new TopStreetViewModel("Merrial Beach Track", -10.474312, 105.559443));
            placesModelList.add(new TopStreetViewModel("Dolly Beach", -10.520641, 105.675738));
            placesModelList.add(new TopStreetViewModel("The Dales", -10.483353, 105.554388));
            placesModelList.add(new TopStreetViewModel("West White Beach", -10.460865, 105.579462));
            placesModelList.add(new TopStreetViewModel("Ethel Beach", -10.463977, 105.708019));
            placesModelList.add(new TopStreetViewModel("Greta Beach", -10.50195, 105.674651));
            placesModelList.add(new TopStreetViewModel("Lily Beach", -10.466893, 105.711355));
            placesModelList.add(new TopStreetViewModel("The Blowholes", -10.514544, 105.622572));
            placesModelList.add(new TopStreetViewModel("Winifred Beach", -10.496783, 105.546169));
            placesModelList.add(new TopStreetViewModel("Golf Course Lookout", -10.423823, 105.698357));
            placesModelList.add(new TopStreetViewModel("The Grotto", -10.42308, 105.701954));
            placesModelList.add(new TopStreetViewModel("Martin Point", -10.469337, 105.557799));
            placesModelList.add(new TopStreetViewModel("Margaret Knoll Lookout", -10.477045, 105.684215));
            placesModelList.add(new TopStreetViewModel("The Dales", -10.478835, 105.55961));
            placesModelList.add(new TopStreetViewModel("Boulder track", -10.549466, 105.638712));

            placesModelList.add(new TopStreetViewModel("Direction Island", -12.0911, 96.885288));
            placesModelList.add(new TopStreetViewModel("West Island", -12.196077, 96.863094));
            placesModelList.add(new TopStreetViewModel("Cocos Airport", -12.193344, 96.834912));
            placesModelList.add(new TopStreetViewModel("Horsburgh Island", -12.081945, 96.845105));
            placesModelList.add(new TopStreetViewModel("Cocos Lagoon", -12.192331, 96.859568));

            placesModelList.add(new TopStreetViewModel("Hisense Arena", -37.822779, 144.981893));
            placesModelList.add(new TopStreetViewModel("Margaret Court Arena", -37.821508, 144.977749));
            placesModelList.add(new TopStreetViewModel("Cape Willoughby, Kangaroo Island", -35.842016, 138.133209));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -35.963795, 136.654461));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -36.016935, 136.856542));
            placesModelList.add(new TopStreetViewModel("Ravine des Casoars Hike, Kangaroo Island", -35.793328, 136.582107));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -36.04712, 136.756926));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -35.950961, 136.676119));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -35.95853, 136.656554));
            placesModelList.add(new TopStreetViewModel("Giraffe crossing, Monarto Zoo", -35.102711, 139.156459));
            placesModelList.add(new TopStreetViewModel("Kangaroo sunbathing, Kangaroo Island", -36.006964, 136.861363));
            placesModelList.add(new TopStreetViewModel("Monarto Zoo", -35.093052, 139.16632));
            placesModelList.add(new TopStreetViewModel("Kangaroo Island", -36.063356, 136.704953));
            placesModelList.add(new TopStreetViewModel("Adelaide Zoo", -34.914127, 138.606372));
            placesModelList.add(new TopStreetViewModel("Sydney Opera House", -33.857394, 151.215435));
            placesModelList.add(new TopStreetViewModel("Hyams Beach", -35.102298, 150.693455));
            placesModelList.add(new TopStreetViewModel("Concert Hall, Sydney Opera House", -33.856965, 151.214948));
            placesModelList.add(new TopStreetViewModel("Manly Beach", -33.786165, 151.289112));
            placesModelList.add(new TopStreetViewModel("National Gallery of Australia", -35.300799, 149.136104));
            placesModelList.add(new TopStreetViewModel("Kosciuszko National Park, Mount Kosciuszko summit", -36.455828, 148.263492));
            placesModelList.add(new TopStreetViewModel("Shelly Beach", -33.800715, 151.298797));
            placesModelList.add(new TopStreetViewModel("Joan Sutherland Theatre, Sydney Opera House", -33.857016, 151.21547));
            placesModelList.add(new TopStreetViewModel("Luna Park", -33.849198, 151.210728));
            placesModelList.add(new TopStreetViewModel("Barangaroo park", -33.85887, 151.202199));
            placesModelList.add(new TopStreetViewModel("Bronte Beach", -33.904869, 151.268291));
            placesModelList.add(new TopStreetViewModel("Invurt - Melbourne Street Art - Artists Lane", -37.853993, 144.992311));
            placesModelList.add(new TopStreetViewModel("Form (PUBLIC - Art in the City) - Wilson Parking Lot off of Murray Street, Perth", -31.95092, 115.85443));
            placesModelList.add(new TopStreetViewModel("Art Gallery of New South Wales", -33.869377, 151.217646));
            placesModelList.add(new TopStreetViewModel("Blue Mountains National Park, Echo Point lookout (Three Sisters)", -33.73285, 150.312039));
            placesModelList.add(new TopStreetViewModel("Ku-ring-gai Chase National Park, West Head lookout", -33.579289, 151.309294));
            placesModelList.add(new TopStreetViewModel("Royal National Park, Wattamolla Beach", -34.137256, 151.117934));
            placesModelList.add(new TopStreetViewModel("Drama Theatre, Sydney Opera House", -33.856658, 151.215085));
            placesModelList.add(new TopStreetViewModel("Sydney Harbour National Park, Fort Denison", -33.855069, 151.225416));
            placesModelList.add(new TopStreetViewModel("Hanging Rock", -37.329771, 144.59378));
            placesModelList.add(new TopStreetViewModel("Mungo National Park, Walls of China", -33.741578, 143.12705));
            placesModelList.add(new TopStreetViewModel("Cape Byron State Conservation Area, Cape Byron lighthouse", -28.638788, 153.636479));
            placesModelList.add(new TopStreetViewModel("Dorrigo National Park, Skywalk lookout", -30.363612, 152.729783));
            placesModelList.add(new TopStreetViewModel("Barrington Tops National Park, Barrington trail", -31.949395, 151.443958));
            placesModelList.add(new TopStreetViewModel("Ben Boyd National Park, Green Cape lookout", -37.261638, 150.04943));
            placesModelList.add(new TopStreetViewModel("Warrumbungle National Park, Breadknife and Grand High Tops walk", -31.332615, 148.995792));
            placesModelList.add(new TopStreetViewModel("Montague Island Nature Reserve, Montague Island walking track", -36.251293, 150.227668));
            placesModelList.add(new TopStreetViewModel("Gundabooka National Park, Valley of the Eagles walk", -30.593884, 145.686808));
            placesModelList.add(new TopStreetViewModel("Muttonbird Island Nature Reserve", -30.304711, 153.153442));
            placesModelList.add(new TopStreetViewModel("Sydney Harbour National Park, Bradleys Head", -33.853049, 151.245498));
            placesModelList.add(new TopStreetViewModel("Bouddi National Park, Bouddi coastal walk", -33.529776, 151.374955));
            placesModelList.add(new TopStreetViewModel("Melbourne Zoo", -37.785221, 144.953041));
            placesModelList.add(new TopStreetViewModel("Werribee Zoo", -37.922403, 144.662957));
            placesModelList.add(new TopStreetViewModel("Croajingolong", -37.782058, 149.313572));
            placesModelList.add(new TopStreetViewModel("Great Ocean Walk", -38.664017, 143.104197));
            placesModelList.add(new TopStreetViewModel("Phillip Island", -38.500644, 145.231302));
            placesModelList.add(new TopStreetViewModel("Mount Buffalo National Park", -36.732918, 146.811495));
            placesModelList.add(new TopStreetViewModel("Ballarat", -37.577173, 143.866066));
            placesModelList.add(new TopStreetViewModel("Melbourne CBD", -37.817954, 144.968871));
            placesModelList.add(new TopStreetViewModel("Bells Beach", -38.372692, 144.279023));
            placesModelList.add(new TopStreetViewModel("The Studio, Sydney Opera House", -33.857017, 151.215045));
            placesModelList.add(new TopStreetViewModel("Yarra Valley", -37.660055, 145.468929));
            placesModelList.add(new TopStreetViewModel("Falls Creek", -36.874735, 147.243036));
            placesModelList.add(new TopStreetViewModel("Mt Buller", -36.87484, 147.242089));
            placesModelList.add(new TopStreetViewModel("Grampians National Park", -37.293149, 142.601589));
            placesModelList.add(new TopStreetViewModel("Yarra Valley", -37.660055, 145.468929));
            placesModelList.add(new TopStreetViewModel("Falls Creek", -36.874735, 147.243036));
            placesModelList.add(new TopStreetViewModel("Mornington Peninsula", -38.497917, 144.889008));
            placesModelList.add(new TopStreetViewModel("Apollo Bay", -38.755857, 143.554685));
            placesModelList.add(new TopStreetViewModel("Wilsons Promontory", -39.030335, 146.315712));
            placesModelList.add(new TopStreetViewModel("Yarrawonga", -36.005968, 146.003609));
            placesModelList.add(new TopStreetViewModel("Perth Zoo", -31.975408, 115.853526));
            placesModelList.add(new TopStreetViewModel("Flemignton Racecourse", -37.789354, 144.908532));
            placesModelList.add(new TopStreetViewModel("Tarra Bulga National Park", -38.428897, 146.566805));
            placesModelList.add(new TopStreetViewModel("Puffing Billy", -37.908328, 145.365155));
            placesModelList.add(new TopStreetViewModel("Royal Botanic Gardens Melbourne", -37.828164, 144.981004));
            placesModelList.add(new TopStreetViewModel("The Utzon Room, Sydney Opera House", -33.857365, 151.215516));

            placesModelList.add(new TopStreetViewModel("Nijojo castle", 35.012762, 135.750338));
            placesModelList.add(new TopStreetViewModel("Itsukushima Shinto Shrine", 34.296968, 132.318469));
            placesModelList.add(new TopStreetViewModel("Archaeological Areas of Pompei", 40.7517, 14.494312));
            placesModelList.add(new TopStreetViewModel("Scott's Hut, Cape Evans on Ross Island", -77.636248, 166.417282));
            placesModelList.add(new TopStreetViewModel("Versailles Palace", 48.805224, 2.118542));
            placesModelList.add(new TopStreetViewModel("Hiroshima Peace Memorial", 34.395108, 132.453676));
            placesModelList.add(new TopStreetViewModel("Historic Center of Prague", 50.087989, 14.420901));
            placesModelList.add(new TopStreetViewModel("Independence Hall", 39.949178, -75.150428));
            placesModelList.add(new TopStreetViewModel("Three Castles, Bellinzona", 46.192686, 9.022496));
            placesModelList.add(new TopStreetViewModel("Historic Walled Town of Cuenca", 40.07836, -2.12978));
            placesModelList.add(new TopStreetViewModel("Stonehenge", 51.179197, -1.825302));
            placesModelList.add(new TopStreetViewModel("Old Town Lunenburg", 44.376978, -64.309726));
            placesModelList.add(new TopStreetViewModel("Church Village of Gammelstad, Luleå", 65.645377, 22.026225));
            placesModelList.add(new TopStreetViewModel("Mill Network at Kinderdijk-Elshout", 51.878621, 4.648365));
            placesModelList.add(new TopStreetViewModel("Historic Town of Ouro Preto", -20.386195, -43.502827));
            placesModelList.add(new TopStreetViewModel("Historic Center of Florence", 43.773392, 11.25518));
            placesModelList.add(new TopStreetViewModel("Santa Prisca Parish Church", 18.558093, -99.606553));
            placesModelList.add(new TopStreetViewModel("Mill Network at Kinderdijk-Elshout", 51.878621, 4.648365));
            placesModelList.add(new TopStreetViewModel("Historic Town of Ouro Preto", -20.386195, -43.502827));
            placesModelList.add(new TopStreetViewModel("Historic Center of Florence", 43.773392, 11.25518));
            placesModelList.add(new TopStreetViewModel("Santa Prisca Parish Church", 18.558093, -99.606553));
            placesModelList.add(new TopStreetViewModel("Shark Bay", -26.192525, 113.68943));
            placesModelList.add(new TopStreetViewModel("Sanctuary of Bom Jesus do Congonhas", -20.500908, -43.858205));

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
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void requestLoadInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
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
