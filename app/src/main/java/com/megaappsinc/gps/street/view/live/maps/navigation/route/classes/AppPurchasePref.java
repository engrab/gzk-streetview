package com.megaappsinc.gps.street.view.live.maps.navigation.route.classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.megaappsinc.gps.street.view.live.maps.navigation.route.R;

public class AppPurchasePref {
    public  final String productId = "com.megaappsinc.gps.street.view.live.maps.navigation.route";
    private final String TransactionDetails = "megaappsincstreetviewdetail";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;



    public AppPurchasePref(Context context)
    {
        pref = context.getSharedPreferences(context.getResources().getString(R.string.app_name), 0);
        editor = pref.edit();
        editor.apply();
    }

    public String getProductId()
    {
        return pref.getString(productId, "");
    }

    public void setProductId(String id)
    {
        editor.putString(productId, id).commit();
    }

    public void setItemDetails(String details)
    {
        editor.putString(TransactionDetails, details).commit();
    }

    public String getItemDetail()
    {
        return pref.getString(TransactionDetails, "");
    }

}
