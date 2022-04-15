
package com.ads.library;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;


public class PreferenceManager {


    public final static String PREFS_NAME = "appname_prefs";

    // admob

    public static void AdMobInter(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdMobInter", id).commit();
        editor.apply();
    }

    public static String AdMobInter(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString("AdMobInter", "ca-app-pub-3940256099942544/1033173712");
    }

    public static void AdMobBanner(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdMobBanner", id).commit();
        editor.apply();
    }

    public static String AdMobBanner(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("AdMobBanner", "ca-app-pub-3940256099942544/6300978111");
    }

    public static void AdMobNative(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdMobNative", id).commit();
        editor.apply();
    }

    public static String AdMobNative(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("AdMobNative", "ca-app-pub-3940256099942544/2247696110");
    }

    public static void AdMobOpenAds(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdMobOpenAds", id).commit();
        editor.apply();

    }

    public static String AdMobOpenAds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("AdMobOpenAds", "ca-app-pub-3940256099942544/3419835294");
    }


    public static void AdMobReword(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdMobReword", id).commit();
        editor.apply();

    }

    public static String AdMobReword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("AdMobReword", "ca-app-pub-3940256099942544/5224354917");
    }

    // adx

    public static void AdxInter(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdxInter", id).commit();
        editor.apply();
    }

    public static String AdxInter(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AdxInter", "ca-app-pub-3940256099942544/1033173712");
    }


    public static void AdxBanner(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdxBanner", id).commit();
        editor.apply();

    }

    public static String AdxBanner(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AdxBanner", "ca-app-pub-3940256099942544/6300978111");
    }

    public static void AdxNative(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdxNative", id).commit();
        editor.apply();

    }

    public static String AdxNative(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AdxNative", "ca-app-pub-3940256099942544/2247696110");
    }

    public static void AdxOpenAds(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdxOpenAds", id).commit();
        editor.apply();

    }

    public static String AdxOpenAds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AdxOpenAds", "");
    }

    public static void AdxReword(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AdxReword", id).commit();
        editor.apply();

    }

    public static String AdxReword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("AdxReword", "yes");
    }


// Facebook

    public static void FBInter(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("FBInter", id).commit();
        editor.apply();

    }

    public static String FBInter(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("FBInter", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }

    public static void FBBanner(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("FBBanner", id).commit();
        editor.apply();

    }

    public static String FBBanner(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("FBBanner", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }

    public static void FBNative(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("FBNative", id).commit();
        editor.apply();

    }

    public static String FBNative(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("FBNative", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }

    // Appnext


    public static void AppnextInter(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AppnextInter", id).commit();
        editor.apply();

    }

    public static String AppnextInter(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AppnextInter", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }

    public static void AppnextBanner(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AppnextBanner", id).commit();
        editor.apply();

    }

    public static String AppnextBanner(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AppnextBanner", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }

    public static void AppnextNative(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AppnextNative", id).commit();
        editor.apply();

    }

    public static String AppnextNative(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("AppnextNative", "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID");
    }


    // Other

    public static String ADSetting(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("ADSetting", "google");
    }

    public static void ADSetting(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ADSetting", id).commit();
        editor.apply();

    }

    public static String OnBackAds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("onback_ads", "0");
    }

    public static void OnBackAds(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("onback_ads", id).commit();
        editor.apply();

    }

    public static int Adcounter(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getInt("ad_counter", 5);
    }

    public static void Adcounter(int id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ad_counter", id).commit();
        editor.apply();

    }

    public static int AppAdcounter(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getInt("AppAdcounter", 0);
    }

    public static void AppAdcounter(int id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("AppAdcounter", id).commit();
        editor.apply();

    }

    public static String InsidesAds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);

        return sharedPreferences.getString("insides_ads", "0");
    }

    public static void InsidesAds(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("insides_ads", id).commit();
        editor.apply();

    }


    public static void AppLovinSdk(Activity activity) {
        AudienceNetworkAds.initialize(activity);
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, configuration -> {
        });
    }

    public static void AdmobSdk(Activity activity, String Appid) {
        MobileAds.initialize(activity, Appid);
    }

    public static void CallOpenAds(Application application) {
        if (PreferenceManager.AdxReword(application.getApplicationContext()).equals("yes")) {
            AppOpenAdManager appManager = new AppOpenAdManager.Builder(application, PreferenceManager.AdxOpenAds(application.getApplicationContext())).setShowAdAutomatically(true).build();
        }
    }

    public static String Versioncode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("version_code", "0");
    }

    public static void Versioncode(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("version_code", id).commit();
        editor.apply();

    }

    public static String Privacypolicy(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("privacy_policy", "google");
    }

    public static void Privacypolicy(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("privacy_policy", id).commit();
        editor.apply();

    }

    public static String AppLink(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("app_link_new", "google");
    }

    public static void AppLink(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("app_link_new", id).commit();
        editor.apply();

    }

    public static String Other_1(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("other_1", "google");
    }

    public static void Other_1(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("other_1", id).commit();
        editor.apply();

    }

    public static String Other_2(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("other_2", "google");
    }

    public static void Other_2(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("other_2", id).commit();
        editor.apply();

    }

    public static String Other_3(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("other_3", "google");
    }

    public static void Other_3(String id, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("other_3", id).commit();
        editor.apply();

    }

}