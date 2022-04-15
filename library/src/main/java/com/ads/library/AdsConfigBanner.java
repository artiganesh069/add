package com.ads.library;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AdsConfigBanner {

    Activity context;
    AdView mAdView;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    public AdsConfigBanner(Activity context) {
        this.context = context;
    }

    public void BannerAds(LinearLayout adslayout) {
        if (isOnline()) {
            adslayout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.banner_ads_layout, null);
            adslayout.addView(view);
            if (PreferenceManager.ADSetting(context).equals("google")) {
                GoogleBanner(view.findViewById(R.id.ad_view_container));
            } else if (PreferenceManager.ADSetting(context).equals("applovin")) {
                Applovinbanner(view.findViewById(R.id.applovinadContainer));
            }
        } else {
            adslayout.setVisibility(View.GONE);
        }
    }

    void GoogleBanner(FrameLayout relativeLayout) {
        mAdView = new AdView(context);
        mAdView.setAdUnitId(PreferenceManager.AdMobBanner(context));
        relativeLayout.addView(mAdView);

        mAdView.setAdSize(AdSize.BANNER);
        mAdView.loadAd(CreateAdRequest());
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
            }

            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                relativeLayout.setVisibility(View.GONE);
                mAdView.setVisibility(View.GONE);
                AdxBanner(relativeLayout);
            }
        });
    }

    void AdxBanner(FrameLayout relativeLayout) {
        mAdView = new AdView(context);
        mAdView.setAdUnitId(PreferenceManager.AdxBanner(context));
        relativeLayout.addView(mAdView);

        mAdView.setAdSize(AdSize.BANNER);
        mAdView.loadAd(CreateAdRequest());
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
            }

            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                relativeLayout.setVisibility(View.GONE);
                mAdView.setVisibility(View.GONE);
                if (PreferenceManager.ADSetting(context).equals("google")) {
                    Applovinbanner(relativeLayout);
                }
            }
        });
    }

    void Applovinbanner(FrameLayout adContainer) {
        nativeAdLoader = new MaxNativeAdLoader(PreferenceManager.FBBanner(context), context);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                adContainer.removeAllViews();
                adContainer.addView(nativeAdView);
                adContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                // We recommend retrying with exponentially higher delays up to a maximum delay
                adContainer.setVisibility(View.GONE);
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleBanner(adContainer);
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                // Optional click callback
            }
        });

        nativeAdLoader.loadAd();
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public AdRequest CreateAdRequest() {
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");
        return new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
    }


}
