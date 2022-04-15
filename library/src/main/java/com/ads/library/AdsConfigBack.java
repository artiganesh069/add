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
import android.view.View;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import io.jitpack.api.R;


public class AdsConfigBack {
    Activity context;
    Dialog pd;

    public AdsConfigBack(Activity context) {
        this.context = context;
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

    public void AdShow(Intent intent, int typ, boolean isShow) {
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
                    GoogleInter(intent, typ);
                    break;
                case "applovin":
                    Applovinmax(intent, typ);
                    break;
            }
        } else {
            intentAfterInterstitial(intent, typ);
        }
    }

    void GoogleInter(final Intent intent, int typ) {
        try {
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        AdxInter(intent, typ);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent, typ);
                }

                public void onAdFailedToLoad(int i) {
                    AdxInter(intent, typ);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.Other_1(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                AdxInter(intent, typ);

            }
        } catch (Exception e) {
            intentAfterInterstitial(intent, typ);
            e.printStackTrace();
        }
    }

    void AdxInter(final Intent intent, int typ) {
        try {
            //AdRequest build = new AdRequest.Builder().build();
            final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
            interstitialAd.setAdListener(new AdListener() {
                public void onAdOpened() {
                }

                public void onAdLoaded() {
                    if (!interstitialAd.isLoaded()) {
                        Applovinmax(intent, typ);
                    } else {
                        interstitialAd.show();
                    }
                }

                public void onAdClosed() {
                    intentAfterInterstitial(intent, typ);
                }

                public void onAdFailedToLoad(int i) {
                    Applovinmax(intent, typ);
                }
            });
            interstitialAd.setAdUnitId(PreferenceManager.Other_2(context));
            if (!interstitialAd.getAdUnitId().equals("NA")) {
                interstitialAd.loadAd(CreateAdRequest());
            } else {
                if (PreferenceManager.ADSetting(context).equals("google")) {
                    Applovinmax(intent, typ);
                } else {
                    intentAfterInterstitial(intent, typ);
                }
            }
        } catch (Exception e) {
            intentAfterInterstitial(intent, typ);
            e.printStackTrace();
        }
    }

    void Applovinmax(final Intent intent, int typ) {
        final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(PreferenceManager.FBInter(context), context);
        maxInterstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
                if (maxInterstitialAd.isReady()) {
                    maxInterstitialAd.showAd();
                } else {
                    if (PreferenceManager.ADSetting(context).equals("applovin")) {
                        GoogleInter(intent, typ);
                    } else {
                        intentAfterInterstitial(intent, typ);
                    }
                }
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                intentAfterInterstitial(intent, typ);
            }

            @Override
            public void onAdClicked(MaxAd ad) {
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent, typ);
                } else {
                    intentAfterInterstitial(intent, typ);
                }
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (PreferenceManager.ADSetting(context).equals("applovin")) {
                    GoogleInter(intent, typ);
                } else {
                    intentAfterInterstitial(intent, typ);
                }
            }
        });
        maxInterstitialAd.loadAd();
    }

    void intentAfterInterstitial(Intent intent, int typ) {
        if (typ == 0) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivity(intent);
            context.finish();
        } else if (typ == 1) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            final BottomSheetDialog dialog = new BottomSheetDialog(context);
            dialog.setContentView(R.layout.ratus_dailog);
            dialog.setCancelable(false);
            LinearLayout ll_native_ads = dialog.findViewById(R.id.ll_native_ads);
            ll_native_ads.setVisibility(View.VISIBLE);
            AdsConfigMaIn.AdNative(ll_native_ads, context);
            dialog.findViewById(R.id.ok).setOnClickListener(view -> {
                dialog.dismiss();
                context.finishAffinity();
            });
            dialog.findViewById(R.id.cancle).setOnClickListener(view -> dialog.dismiss());
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();
        } else {
            if (pd.isShowing()) {
                pd.dismiss();
            }
        }
    }

}
