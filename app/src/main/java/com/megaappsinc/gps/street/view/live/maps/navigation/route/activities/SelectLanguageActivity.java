package com.megaappsinc.gps.street.view.live.maps.navigation.route.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.AppPurchasePref;
import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;

import static com.megaappsinc.gps.street.view.live.maps.navigation.route.utiles.Utils.animation;

public class SelectLanguageActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler
{
    InterstitialAd mInterstitialAd;
    int mPosition = 0;
    private Activity context;
    private Spinner spLanguage;
    private LayoutInflater inflter;
    private AdView mAdView;
    private String lang;
    BillingProcessor bp;
    ImageView ivRemoveAd;
    private Dialog mDialog;

    private AppPurchasePref appPurchasePref;

    private String[] languages = {"Afrikaans",
            "Albanian",
            "Amharic",
            "Arabic",
            "Armenian",
            "Azeerbaijani",
            "Basque",
            "Belarusian",
            "Bengali",
            "Bosnian",
            "Bulgarian",
            "Catalan",
            "Chinese",
            "Corsican",
            "Croatian",
            "Czech",
            "Danish",
            "Dutch",
            "English",
            "Estonian",
            "Finnish",
            "French",
            "Frisian",
            "Galician",
            "Georgian",
            "German",
            "Greek",
            "Gujarati",
            "Haitian Creole",
            "Hausa",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Icelandic",
            "Igbo",
            "Indonesian",
            "Irish",
            "Italian",
            "Japanese",
            "Kannada",
            "Kazakh",
            "Khmer",
            "Korean",
            "Kyrgyz",
            "Lao",
            "Latvian",
            "Lithuanian",
            "Luxembourgish",
            "Macedonian",
            "Malagasy",
            "Malay",
            "Malayalam",
            "Maltese",
            "Maori",
            "Marathi",
            "Mongolian",
            "Myanmar",
            "Nepali",
            "Norwegian",
            "Nyanja",
            "Pashto",
            "Persian",
            "Polish",
            "Portuguese",
            "Punjabi",
            "Romanian",
            "Russian",
            "Samoan",
            "Scots Gaelic",
            "Serbian",
            "Sesotho",
            "Shona",
            "Sindhi",
            "Sinhala",
            "Slovak",
            "Slovenian",
            "Somali",
            "Spanish",
            "Sundanese",
            "Swahili",
            "Swedish",
            "Tagalog",
            "Tajik",
            "Tamil",
            "Telugu",
            "Thai",
            "Turkish",
            "Ukrainian",
            "Urdu",
            "Uzbek",
            "Vietnamese",
            "Welsh",
            "Xhosa",
            "Yiddish",
            "Yoruba",
            "Zulu"};

    private int[] flags = {
            R.drawable.flag_za,
            R.drawable.flag_al,
            R.drawable.flag_et,
            R.drawable.flag_ae,
            R.drawable.flag_am,
            R.drawable.flag_az,
            R.drawable.flag_es,
            R.drawable.flag_by,
            R.drawable.flag_bd,
            R.drawable.flag_ba,
            R.drawable.flag_bg,
            R.drawable.flag_es,
            R.drawable.flag_cn,
            R.drawable.flag_fr,
            R.drawable.flag_hr,
            R.drawable.flag_cz,
            R.drawable.flag_dk,
            R.drawable.flag_nl,
            R.drawable.flag_us,
            R.drawable.flag_ee,
            R.drawable.flag_fi,
            R.drawable.flag_fr,
            R.drawable.flag_nl,
            R.drawable.flag_es,
            R.drawable.flag_ge,
            R.drawable.flag_de,
            R.drawable.flag_gr,
            R.drawable.flag_in,
            R.drawable.flag_ht,
            R.drawable.flag_ng,
            R.drawable.flag_il,
            R.drawable.flag_in,
            R.drawable.flag_hu,
            R.drawable.flag_is,
            R.drawable.flag_ng,
            R.drawable.flag_id,
            R.drawable.flag_ie,
            R.drawable.flag_it,
            R.drawable.flag_jp,
            R.drawable.flag_in,
            R.drawable.flag_kz,
            R.drawable.flag_kh,
            R.drawable.flag_kr,
            R.drawable.flag_kg,
            R.drawable.flag_la,
            R.drawable.flag_lv,
            R.drawable.flag_lt,
            R.drawable.flag_lu,
            R.drawable.flag_mk,
            R.drawable.flag_mg,
            R.drawable.flag_my,
            R.drawable.flag_ml,
            R.drawable.flag_mt,
            R.drawable.flag_nz,
            R.drawable.flag_in,
            R.drawable.flag_mn,
            R.drawable.flag_mm,
            R.drawable.flag_np,
            R.drawable.flag_no,
            R.drawable.flag_mw,
            R.drawable.flag_af,
            R.drawable.flag_ir,
            R.drawable.flag_pl,
            R.drawable.flag_pt,
            R.drawable.flag_pk,
            R.drawable.flag_ro,
            R.drawable.flag_ru,
            R.drawable.flag_ws,
            R.drawable.flag_ca,
            R.drawable.flag_rs,
            R.drawable.flag_ls,
            R.drawable.flag_zw,
            R.drawable.flag_pk,
            R.drawable.flag_lk,
            R.drawable.flag_sk,
            R.drawable.flag_si,
            R.drawable.flag_so,
            R.drawable.flag_es,
            R.drawable.flag_sg,
            R.drawable.flag_tz,
            R.drawable.flag_se,
            R.drawable.flag_ph,
            R.drawable.flag_tj,
            R.drawable.flag_in,
            R.drawable.flag_in,
            R.drawable.flag_th,
            R.drawable.flag_tr,
            R.drawable.flag_ua,
            R.drawable.flag_pk,
            R.drawable.flag_uz,
            R.drawable.flag_vn,
            R.drawable.flag_cn,
            R.drawable.flag_za,
            R.drawable.flag_lr,
            R.drawable.flag_nr,
            R.drawable.flag_za,
    };


    private String[] values = {"af",
            "sq",
            "am",
            "ar",
            "hy",
            "az",
            "eu",
            "be",
            "bn",
            "bs",
            "bg",
            "ca",
            "zh",
            "co",
            "hr",
            "cs",
            "da",
            "nl",
            "en",
            "et",
            "fi",
            "fr",
            "fy",
            "gl",
            "ka",
            "de",
            "el",
            "gu",
            "ht",
            "ha",
            "iw",
            "hi",
            "hu",
            "is",
            "ig",
            "in",
            "ga",
            "it",
            "ja",
            "kn",
            "kk",
            "km",
            "ko",
            "ky",
            "lo",
            "lv",
            "lt",
            "lb",
            "mk",
            "mg",
            "ms",
            "ml",
            "mt",
            "mi",
            "mr",
            "mn",
            "my",
            "ne",
            "no",
            "ny",
            "ps",
            "fa",
            "pl",
            "pt",
            "pa",
            "ro",
            "ru",
            "sm",
            "gd",
            "sr",
            "st",
            "sn",
            "sd",
            "si",
            "sk",
            "sl",
            "so",
            "es",
            "su",
            "sw",
            "sv",
            "tl",
            "tg",
            "ta",
            "te",
            "th",
            "tr",
            "uk",
            "ur",
            "uz",
            "vi",
            "cy",
            "xh",
            "yi",
            "yo",
            "zu"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        context = SelectLanguageActivity.this;
        appPurchasePref = new AppPurchasePref(getApplicationContext());

        bp = new BillingProcessor(getApplicationContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhvwzs6vdSpjRmMgR10Hsx6hbhbz0qy43z+5FpDmXyaFes1Mv15M0j8YLxxZbYgq4xXeBSAP62NFy5CizDcbf0wv0XS5A43Yo78aZYlYuGl2qr5qrHy6tZisZlvpDU2N4b6xDDxJU1qPKgGtlYqjmD+Bj95NqUlppM27j75MSZKuUdQEdkdfAm9YjLZp5xK4dS3P/kezvgs50L9KNN3kRg6Dx0jvt5Xi+FZRCGb/smMrUYKDQ+W4eTIMv/rXeetQjaOWmp8y2JnVsXxd1Ih0YXa2xC2nAKXgrDaQyQF7mKEPEjfHU3oMq4+BNCkaNq+kXRZk6ELV9oLuy7GwAhHsx1wIDAQAB", this);

        ivRemoveAd = findViewById(R.id.iv_remove_ads);
        ivRemoveAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bp.purchase(SelectLanguageActivity.this, appPurchasePref.getProductId());
                } catch (Exception ignored) {
                }
            }
        });
        animation(ivRemoveAd);


        if (appPurchasePref.getItemDetail().equals("") && appPurchasePref.getProductId().equals("")) {


            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener()
            {
                @Override
                public void onAdClosed()
                {

                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    onSelection();

                }
            });
            mAdView = findViewById(R.id.adView);
            mAdView.loadAd(new AdRequest.Builder().build());
            mAdView.setAdListener(new AdListener(){
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }
            });
        }

        Init();
    }
    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        ViewUpdate();
        appPurchasePref.setProductId(productId);
        if (details != null) {
            appPurchasePref.setItemDetails(details.toString());
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {
        ViewUpdate();
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {
        ViewUpdate();
    }


    private void ViewUpdate() {
        if (bp != null && bp.isPurchased(appPurchasePref.getProductId())) {
            appPurchasePref.setProductId(getPackageName());
            appPurchasePref.setItemDetails(getPackageName());
            ivRemoveAd.setVisibility(View.INVISIBLE);


        }
    }
    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void Init()
    {
        try
        {
            context = SelectLanguageActivity.this;
            lang = LocaleHelper.getLanguage(SelectLanguageActivity.this);
            inflter = (LayoutInflater.from(context));
            spLanguage = findViewById(R.id.SpLanguage);
            spLanguage.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

            spLanguage.setAdapter(new CustomAdapter());
            findViewById(R.id.IvForward).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mPosition = spLanguage.getSelectedItemPosition();
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded())
                    {
                        mInterstitialAd.show();
                    }
                    else
                    {
                        onSelection();
                    }
                }
            });
            onLanguage();
        }
        catch (Exception ignored)
        {
        }
    }

    public void onSelection()
    {
        try
        {
            LocaleHelper.setLocale(context, values[spLanguage.getSelectedItemPosition()]);
            startActivity(new Intent(context, MainMenuActivity.class));
        }
        catch (Exception ignored)
        {
        }
    }

    public void onLanguage()
    {
        try
        {
            for (int i = 0; i < values.length; i++)
            {
                if (values[i].equals(lang))
                {
                    spLanguage.setSelection(i);
                    break;
                }
            }
        }
        catch (Exception ignored)
        {
        }
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
    public void onBackPressed() {

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        } else {
            ExitDialog();
        }


    }
    private void ExitDialog() {

        try {
            View view = LayoutInflater.from(SelectLanguageActivity.this).inflate(R.layout.exit_dialog, null, false);
            view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        mDialog.dismiss();
                        finish();
                    } catch (Exception ignored) {
                    }

                }
            });
            view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            RatingBar ratingBar = view.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        mDialog.dismiss();
                    } catch (Exception ignored) {
                    }

                }
            });


            mDialog = new Dialog(SelectLanguageActivity.this, R.style.MaterialDialogSheet);
            mDialog.setContentView(view);
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            if (mDialog.getWindow() != null) {
                mDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
            }
            mDialog.show();
        } catch (Exception ignored) {
        }
    }
    public class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return flags.length;
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view = inflter.inflate(R.layout.custom_spinner_items, null);
            ImageView icon = view.findViewById(R.id.textIcon);
            TextView names = view.findViewById(R.id.textView);
            icon.setImageResource(flags[i]);
            names.setText(languages[i]);
            return view;
        }

        @Override
        public View getDropDownView(int i, View view, ViewGroup parent)
        {
            view = inflter.inflate(R.layout.custom_spinner_items, null);
            ImageView icon = view.findViewById(R.id.textIcon);
            TextView names = view.findViewById(R.id.textView);
            icon.setImageResource(flags[i]);
            names.setText(languages[i]);
            return view;
        }
    }
}
