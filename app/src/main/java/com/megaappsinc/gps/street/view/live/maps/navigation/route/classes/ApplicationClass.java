package com.megaappsinc.gps.street.view.live.maps.navigation.route.classes;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.megaappsinc.gps.street.view.live.maps.navigation.route.classes.LocaleHelper;

import java.util.Locale;

public class ApplicationClass extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, Locale.getDefault().getLanguage()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
    }
}
