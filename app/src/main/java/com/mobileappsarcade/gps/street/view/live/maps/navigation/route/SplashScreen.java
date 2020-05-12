package com.mobileappsarcade.gps.street.view.live.maps.navigation.route;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import cn.refactor.lib.colordialog.PromptDialog;
import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

public class SplashScreen extends ActivityManagePermission
{
    private boolean denay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.spalshscren);

        }
        catch (Exception ignored)
        {
        }

        try
        {
            askCompactPermissions(new String[]{
                            PermissionUtils.Manifest_ACCESS_FINE_LOCATION,
                            PermissionUtils.Manifest_ACCESS_COARSE_LOCATION},
                    new PermissionResult()
                    {
                        @Override
                        public void permissionGranted()
                        {
                            //permission granted
                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        startActivity(new Intent(SplashScreen.this, Select_Language_Activity.class));
                                        // close this activity
                                        finish();
                                    }
                                    catch (Exception ignored)
                                    {
                                    }
                                }
                            }, 2000);
                        }

                        @Override
                        public void permissionDenied()
                        {
                            denay = true;
                            permissionWarningDialog();
                        }

                        @Override
                        public void permissionForeverDenied()
                        {
                            denay = true;
                            permissionWarningDialog();
                        }

                    });
        }
        catch (Exception ignored)
        {
        }
    }

    @Override
    public void onBackPressed()
    {
        if (denay)
        {
            super.onBackPressed();
        }
    }

    public void permissionWarningDialog()
    {
        try
        {
            new PromptDialog(SplashScreen.this)
                    .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                    .setAnimationEnable(true)
                    .setTitleText(getString(R.string.pdialog_title))
                    .setContentText(getString(R.string.pdialog_text))
                    .setPositiveListener(getString(R.string.pdialog_setting_btn), new PromptDialog.OnPositiveListener()
                    {
                        @Override
                        public void onClick(PromptDialog dialog)
                        {
                            try
                            {
                                openSettingsApp(SplashScreen.this);
                            }
                            catch (Exception ignored)
                            {
                            }
                            dialog.dismiss();
                        }
                    }).show();
        }
        catch (Exception ignored)
        {
        }
    }
}
