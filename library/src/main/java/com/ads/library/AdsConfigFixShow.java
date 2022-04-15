package com.ads.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class AdsConfigFixShow {
    Activity context;
    Dialog pd;

    public AdsConfigFixShow(Activity context) {
        this.context = context;
    }

    public AdRequest CreateAdRequest() {
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "MA");
        return new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
    }

    public void AdShow(Intent intent, int type, boolean isShow) {
        if (isOnline()) {
            pd = new Dialog(context);
            pd.setContentView(R.layout.ads_dialog);
            pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pd.setCancelable(false);
            if (isShow) {
                pd.show();
            }

            switch (PreferenceManager.ADSetting(context)) {
                case "google":
                    GoogleInter(intent, type);
                    break;
                case "applovin":
                    Applovinmax(intent, type);
                    break;
            }
        } else {
            intentAfterInterstitial(intent, type);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    void GoogleInter(final Intent intent, final int type) {
        try {
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        AdxInter(intent, type);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent, type);
                }

                public void onAdFailedToLoad(int i) {
                    AdxInter(intent, type);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.AppnextInter(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                AdxInter(intent, type);

            }
        } catch (Exception e) {
            intentAfterInterstitial(intent, type);
            e.printStackTrace();
        }
    }

    void AdxInter(final Intent intent, final int type) {
        try {
            //AdRequest build = new AdRequest.Builder().build();
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        Applovinmax(intent, type);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent, type);
                }

                public void onAdFailedToLoad(int i) {
                    Applovinmax(intent, type);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.AppnextBanner(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                if (PreferenceManager.ADSetting(context).equals("google")) {
                    Applovinmax(intent, type);
                } else {
                    intentAfterInterstitial(intent, type);
                }
            }
        } catch (Exception e) {
            intentAfterInterstitial(intent, type);
            e.printStackTrace();
        }
    }

    void Applovinmax(final Intent intent, final int type) {
        final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(PreferenceManager.FBInter(context), context);
        maxInterstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
                if (maxInterstitialAd.isReady()) {
                    maxInterstitialAd.showAd();
                } else {
                    if (PreferenceManager.ADSetting(context).equals("applovin")) {
                        GoogleInter(intent, type);
                    } else {
                        intentAfterInterstitial(intent, type);
                    }
                }
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                intentAfterInterstitial(intent, type);
            }

            @Override
            public void onAdClicked(MaxAd ad) {
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent, type);
                } else {
                    intentAfterInterstitial(intent, type);
                }
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent, type);
                } else {
                    intentAfterInterstitial(intent, type);
                }
            }
        });
        maxInterstitialAd.loadAd();
    }


    void intentAfterInterstitial(Intent intent, int type) {
        if (type == 0) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivity(intent);
        } else if (type == 1) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 1001);
        } else if (type == 100) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 100);
        } else if (type == 200) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivity(intent);
            context.finish();

        } else if (type == 500) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 500);
        } else if (type == 49) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 49);
        } else if (type == 1001) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 1001);
        } else {
            if (pd.isShowing()) {
                pd.dismiss();
            }
        }
    }

}
