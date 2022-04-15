package com.ads.library;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.List;
import io.jitpack.api.R;

public class AdsConfigNative {
    Activity context;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    public AdsConfigNative(Activity context) {
        this.context = context;
    }

    public void NativeAds(LinearLayout adslayout) {
        if (isOnline()) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.native_ads_layout, null);
            adslayout.addView(view);
            if (PreferenceManager.ADSetting(context).equals("google")) {
                GoogleNative(view.findViewById(R.id.adContainer));
            } else if (PreferenceManager.ADSetting(context).equals("applovin")) {
                ApplovinNativeAd(view.findViewById(R.id.applovinadContainer));
            }
            adslayout.setVisibility(View.VISIBLE);
        } else {
            adslayout.setVisibility(View.GONE);
        }
    }

    void GoogleNative(FrameLayout adContainer) {
        AdLoader.Builder builder = new AdLoader.Builder(context, PreferenceManager.AdMobNative(context));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd nativeAppInstallAd) {

                UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(context)
                        .inflate(R.layout.big_native_adavance, null);
                adContainer.removeAllViews();
                adContainer.addView(adView);
                adContainer.setVisibility(View.VISIBLE);


                VideoController vc = nativeAppInstallAd.getVideoController();
                vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });

                adView.setHeadlineView(adView.findViewById(R.id.appinstall_headline));
                adView.setBodyView(adView.findViewById(R.id.appinstall_body));
                adView.setCallToActionView(adView.findViewById(R.id.appinstall_call_to_action));
                adView.setIconView(adView.findViewById(R.id.appinstall_app_icon));
                adView.setPriceView(adView.findViewById(R.id.appinstall_price));
                adView.setStarRatingView(adView.findViewById(R.id.appinstall_stars));
                adView.setStoreView(adView.findViewById(R.id.appinstall_store));

                // Some assets are guaranteed to be in every NativeAppInstallAd.
                ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
                ((TextView) adView.getBodyView()).setText(nativeAppInstallAd.getBody());
                ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());
                try {
                    ((ImageView) adView.getIconView()).setImageDrawable(
                            nativeAppInstallAd.getIcon().getDrawable());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                MediaView mediaView = adView.findViewById(R.id.appinstall_media);
                ImageView mainImageView = adView.findViewById(R.id.appinstall_image);

                // Apps can check the VideoController's hasVideoContent property to determine if the
                // NativeAppInstallAd has a hdplayers asset.
                adView.setMediaView(mediaView);
                if (vc.hasVideoContent()) {
                    mainImageView.setVisibility(View.GONE);
                    /*mVideoStatus.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 hdplayers asset.",
                    vc.getAspectRatio()));*/
                } else {
                    adView.setImageView(mainImageView);
                    mediaView.setVisibility(View.VISIBLE);

                    // At least one image is guaranteed.
                    List<NativeAd.Image> images = nativeAppInstallAd.getImages();
                    if (images != null && images.size() > 0)
                        mainImageView.setImageDrawable(images.get(0).getDrawable());
                }


                if (nativeAppInstallAd.getPrice() == null) {
                    adView.getPriceView().setVisibility(View.GONE);
                } else {
                    adView.getPriceView().setVisibility(View.VISIBLE);
                    ((TextView) adView.getPriceView()).setText(nativeAppInstallAd.getPrice());
                }

                if (nativeAppInstallAd.getStore() == null) {
                    adView.getStoreView().setVisibility(View.INVISIBLE);
                } else {
                    adView.getStoreView().setVisibility(View.VISIBLE);
                    ((TextView) adView.getStoreView()).setText(nativeAppInstallAd.getStore());
                }

                if (nativeAppInstallAd.getStarRating() == null) {
                    adView.getStarRatingView().setVisibility(View.GONE);
                } else {
                    ((RatingBar) adView.getStarRatingView())
                            .setRating(nativeAppInstallAd.getStarRating().floatValue());
                    adView.getStarRatingView().setVisibility(View.VISIBLE);
                }

                // Assign native ad object to the native view.
                adView.setNativeAd(nativeAppInstallAd);
            }
        });


        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {

                adContainer.setVisibility(View.GONE);
                AdxNative(adContainer);

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adContainer.setVisibility(View.VISIBLE);
            }
        }).withNativeAdOptions(new NativeAdOptions.Builder()
                // Methods in the NativeAdOptions.Builder class can be
                // used here to specify individual options settings.
                .build())
                .build();

        // Load the Native Express ad.

        adLoader.loadAd(CreateAdRequest());
    }

    void AdxNative(FrameLayout adContainer) {
        AdLoader.Builder builder = new AdLoader.Builder(context, PreferenceManager.AdxNative(context));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd nativeAppInstallAd) {

                UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(context)
                        .inflate(R.layout.big_native_adavance, null);
                adContainer.removeAllViews();
                adContainer.addView(adView);
                adContainer.setVisibility(View.VISIBLE);


                VideoController vc = nativeAppInstallAd.getVideoController();
                vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {

                        super.onVideoEnd();
                    }
                });

                adView.setHeadlineView(adView.findViewById(R.id.appinstall_headline));
                adView.setBodyView(adView.findViewById(R.id.appinstall_body));
                adView.setCallToActionView(adView.findViewById(R.id.appinstall_call_to_action));
                adView.setIconView(adView.findViewById(R.id.appinstall_app_icon));
                adView.setPriceView(adView.findViewById(R.id.appinstall_price));
                adView.setStarRatingView(adView.findViewById(R.id.appinstall_stars));
                adView.setStoreView(adView.findViewById(R.id.appinstall_store));

                // Some assets are guaranteed to be in every NativeAppInstallAd.
                ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
                ((TextView) adView.getBodyView()).setText(nativeAppInstallAd.getBody());
                ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());
                try {
                    ((ImageView) adView.getIconView()).setImageDrawable(
                            nativeAppInstallAd.getIcon().getDrawable());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                MediaView mediaView = adView.findViewById(R.id.appinstall_media);
                ImageView mainImageView = adView.findViewById(R.id.appinstall_image);

                // Apps can check the VideoController's hasVideoContent property to determine if the
                // NativeAppInstallAd has a hdplayers asset.
                adView.setMediaView(mediaView);
                if (vc.hasVideoContent()) {
                    mainImageView.setVisibility(View.GONE);
                    /*mVideoStatus.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 hdplayers asset.",
                    vc.getAspectRatio()));*/
                } else {
                    adView.setImageView(mainImageView);
                    mediaView.setVisibility(View.VISIBLE);

                    // At least one image is guaranteed.
                    List<NativeAd.Image> images = nativeAppInstallAd.getImages();
                    if (images != null && images.size() > 0)
                        mainImageView.setImageDrawable(images.get(0).getDrawable());


                }


                if (nativeAppInstallAd.getPrice() == null) {
                    adView.getPriceView().setVisibility(View.GONE);
                } else {
                    adView.getPriceView().setVisibility(View.VISIBLE);
                    ((TextView) adView.getPriceView()).setText(nativeAppInstallAd.getPrice());
                }

                if (nativeAppInstallAd.getStore() == null) {
                    adView.getStoreView().setVisibility(View.INVISIBLE);
                } else {
                    adView.getStoreView().setVisibility(View.VISIBLE);
                    ((TextView) adView.getStoreView()).setText(nativeAppInstallAd.getStore());
                }

                if (nativeAppInstallAd.getStarRating() == null) {
                    adView.getStarRatingView().setVisibility(View.GONE);
                } else {
                    ((RatingBar) adView.getStarRatingView())
                            .setRating(nativeAppInstallAd.getStarRating().floatValue());
                    adView.getStarRatingView().setVisibility(View.VISIBLE);
                }

                // Assign native ad object to the native view.
                adView.setNativeAd(nativeAppInstallAd);
            }
        });


        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                adContainer.setVisibility(View.GONE);
                if (PreferenceManager.ADSetting(context).equals("google")) {
                    ApplovinNativeAd(adContainer);
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adContainer.setVisibility(View.VISIBLE);
            }
        }).withNativeAdOptions(new NativeAdOptions.Builder()
                // Methods in the NativeAdOptions.Builder class can be
                // used here to specify individual options settings.
                .build())
                .build();

        // Load the Native Express ad.

        adLoader.loadAd(CreateAdRequest());
    }

    void ApplovinNativeAd(FrameLayout adContainer) {
        nativeAdLoader = new MaxNativeAdLoader(PreferenceManager.FBNative(context), context);
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
                    GoogleNative(adContainer);
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
