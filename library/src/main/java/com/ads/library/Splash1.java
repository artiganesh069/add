package com.ads.library;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Splash1 {

    public static boolean isOnline(Activity context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void StartActivity(Activity context, String pkg, Intent intent) {
        PreferenceManager.AppLovinSdk(context);
        if (isOnline(context)) {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest postRequest = new StringRequest(Request.Method.POST, "https://makinfotech.net/AppAds/getAds.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray arr = obj.getJSONArray("result");
                                String AppID = arr.getJSONObject(0).getString("admob_appid");
                                PreferenceManager.AdMobBanner(arr.getJSONObject(0).getString("admob_banner"), context);
                                PreferenceManager.AdMobInter(arr.getJSONObject(0).getString("admob_ineterstitial"), context);
                                PreferenceManager.AdMobNative(arr.getJSONObject(0).getString("admob_native"), context);
                                PreferenceManager.AdMobOpenAds(arr.getJSONObject(0).getString("admob_openads"), context);
                                PreferenceManager.AdMobReword(arr.getJSONObject(0).getString("admob_reward"), context);

                                PreferenceManager.AdxBanner(arr.getJSONObject(0).getString("adx_banner"), context);
                                PreferenceManager.AdxInter(arr.getJSONObject(0).getString("adx_ineterstitial"), context);
                                PreferenceManager.AdxNative(arr.getJSONObject(0).getString("adx_native"), context);
                                PreferenceManager.AdxOpenAds(arr.getJSONObject(0).getString("adx_openad"), context);
                                PreferenceManager.AdxReword(arr.getJSONObject(0).getString("adx_reward"), context);

                                PreferenceManager.FBInter(arr.getJSONObject(0).getString("fb_interstitial"), context);
                                PreferenceManager.FBBanner(arr.getJSONObject(0).getString("fb_banner"), context);
                                PreferenceManager.FBNative(arr.getJSONObject(0).getString("fb_native"), context);

                                PreferenceManager.AppnextBanner(arr.getJSONObject(0).getString("appnext_banner"), context);
                                PreferenceManager.AppnextInter(arr.getJSONObject(0).getString("appnext_inter"), context);
                                PreferenceManager.AppnextNative(arr.getJSONObject(0).getString("appnext_native"), context);

                                PreferenceManager.Privacypolicy(arr.getJSONObject(0).getString("privacy_policy"), context);
                                PreferenceManager.OnBackAds(arr.getJSONObject(0).getString("onback_ads"), context);
                                PreferenceManager.Adcounter(Integer.parseInt(arr.getJSONObject(0).getString("ad_counter")), context);
                                PreferenceManager.InsidesAds(arr.getJSONObject(0).getString("insides_ads"), context);
                                PreferenceManager.Versioncode(arr.getJSONObject(0).getString("version_code"), context);
                                PreferenceManager.ADSetting(arr.getJSONObject(0).getString("ad_setting"), context);
                                PreferenceManager.AppLink(arr.getJSONObject(0).getString("app_link_new"), context);

                                PreferenceManager.Other_1(arr.getJSONObject(0).getString("other_1"), context);
                                PreferenceManager.Other_2(arr.getJSONObject(0).getString("other_2"), context);
                                PreferenceManager.Other_3(arr.getJSONObject(0).getString("other_3"), context);
                                Log.d("Response", response);
                                if (!response.equals("")) {
                                    PreferenceManager.AdmobSdk(context, AppID);
                                    AdsConfigMaIn.AdShowSplash(intent, context);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("app_package", pkg);

                    return params;
                }
            };
            queue.add(postRequest);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(intent);
                    context.finish();
                }
            }, 2000);
        }

    }

}
