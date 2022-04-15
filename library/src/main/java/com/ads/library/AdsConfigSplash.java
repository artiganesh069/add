package com.ads.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import io.jitpack.api.R;


public class AdsConfigSplash {
    Activity context;

    public AdsConfigSplash(Activity context) {
        this.context = context;
    }

    public AdRequest CreateAdRequest() {
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");
        return new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
    }

    public void AdShow(Intent intent) {
        if (isOnline()) {
            switch (PreferenceManager.ADSetting(context)) {
                case "google":
                    GoogleInter(intent);
                    break;
                case "applovin":
                    Applovinmax(intent);
                    break;
            }
        } else {
            intentAfterInterstitial(intent);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    void GoogleInter(final Intent intent) {
        try {
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        AdxInter(intent);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent);
                }

                public void onAdFailedToLoad(int i) {
                    AdxInter(intent);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.AdMobInter(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                AdxInter(intent);
            }
        } catch (Exception e) {
            intentAfterInterstitial(intent);
            e.printStackTrace();
        }
    }

    void AdxInter(final Intent intent) {
        try {
            //AdRequest build = new AdRequest.Builder().build();
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        Applovinmax(intent);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent);
                }

                public void onAdFailedToLoad(int i) {
                    Applovinmax(intent);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.AdxInter(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                if (PreferenceManager.ADSetting(context).equals("google")) {
                    Applovinmax(intent);
                } else {
                    intentAfterInterstitial(intent);
                }
            }
        } catch (Exception e) {
            intentAfterInterstitial(intent);
            e.printStackTrace();
        }
    }

    void Applovinmax(final Intent intent) {
        final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(PreferenceManager.FBInter(context), context);
        maxInterstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
                if (maxInterstitialAd.isReady()) {
                    maxInterstitialAd.showAd();
                } else {
                    if (PreferenceManager.ADSetting(context).equals("applovin")) {
                        GoogleInter(intent);
                    } else {
                        intentAfterInterstitial(intent);
                    }
                }
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                intentAfterInterstitial(intent);
            }

            @Override
            public void onAdClicked(MaxAd ad) {
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent);
                } else {
                    intentAfterInterstitial(intent);
                }
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent);
                } else {
                    intentAfterInterstitial(intent);
                }
            }
        });
        maxInterstitialAd.loadAd();
    }

    void intentAfterInterstitial(Intent intent) {
        context.startActivity(intent);
        context.finish();
    }

}
