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

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class AdsConfigOpen {
    Activity context;
    Dialog pd;

    public AdsConfigOpen(Activity context) {
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


    public void AdShowOpen(Intent intent, int type, boolean isShow) {
        pd = new Dialog(context);
        pd.setContentView(R.layout.ads_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pd.setCancelable(false);
        if (isShow) {
            pd.show();
        }

        GoogleOpenAds(intent, type);

    }

    public void GoogleOpenAds(final Intent intent, final int type) {
        try {
            AppOpenAd.load(context, PreferenceManager.AdxOpenAds(context), CreateAdRequest(), 1, new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd appOpenAd) {
                    super.onAdLoaded(appOpenAd);
                    appOpenAd.show(context);
                    appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            AdxOpenAds(intent, type);

                        }

                        @Override // com.google.android.gms.ads.FullScreenContentCallback
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            try {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            intentAfterInterstitial(intent, type);

                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    AdxOpenAds(intent, type);

                }
            });
        } catch (Exception e) {
            intentAfterInterstitial(intent, type);
        }

    }

    void AdxOpenAds(final Intent intent, final int type) {
        try {
            AppOpenAd.load(context, PreferenceManager.AdMobOpenAds(context), CreateAdRequest(), 1, new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd appOpenAd) {
                    super.onAdLoaded(appOpenAd);
                    appOpenAd.show(context);
                    appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }
                            intentAfterInterstitial(intent, type);
                        }

                        @Override // com.google.android.gms.ads.FullScreenContentCallback
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            try {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }
                            } catch (Exception e) {

                            }
                            intentAfterInterstitial(intent, type);
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    intentAfterInterstitial(intent, type);
                }
            });
        } catch (Exception e) {
            intentAfterInterstitial(intent, type);
        }
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
            context.startActivityForResult(intent, 518);
        } else if (type == 200) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivity(intent);
            context.finish();
        } else if (type == 300) {
            context.startActivity(intent);
            context.finish();
        } else if (type == 100) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 100);
        } else if (type == 500) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.startActivityForResult(intent, 500);
        } else if (type == 400) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            context.finish();
        } else {
            if (pd.isShowing()) {
                pd.dismiss();
            }
        }
    }

}
