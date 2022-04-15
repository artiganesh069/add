package com.ads.library;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import io.jitpack.api.R;


public class AdsConfigMaIn {

    public static void AdShowSplash(Intent intent, Activity context) {
        AdsConfigSplash adsConfig = new AdsConfigSplash(context);
        adsConfig.AdShow(intent);
    }

    public static void AdShowBack(Intent intent, int type, boolean isShow, Activity context) {
        AdsConfigBack adsConfigBack = new AdsConfigBack(context);
        adsConfigBack.AdShow(intent, type, isShow);
    }

    public static void AdShowCount(Intent intent, int type, boolean isShow, Activity context) {
        AdsConfigCount adsConfigCount = new AdsConfigCount(context);
        adsConfigCount.AdShowCount(intent, type, isShow);
    }

    public static void AdShowOpen(Intent intent, int type, boolean isShow, Activity context) {
        AdsConfigOpen adsConfigOpen = new AdsConfigOpen(context);
        adsConfigOpen.AdShowOpen(intent, type, isShow);
    }

    public static void AdShow(Intent intent, int type, boolean isShow, Activity context) {
        AdsConfigFixShow adsConfigFixShow = new AdsConfigFixShow(context);
        adsConfigFixShow.AdShow(intent, type, isShow);
    }

    public static void AdBanner(LinearLayout adslayout, Activity context) {
        AdsConfigBanner adsConfigBanner = new AdsConfigBanner(context);
        adsConfigBanner.BannerAds(adslayout);
    }

    public static void AdNative(LinearLayout adslayout, Activity context) {
        AdsConfigNative adsConfigNative = new AdsConfigNative(context);
        adsConfigNative.NativeAds(adslayout);
    }
}
