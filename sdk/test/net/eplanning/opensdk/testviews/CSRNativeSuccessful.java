package net.eplanning.opensdk.testviews;

import android.content.Context;

import net.eplanning.opensdk.CSRAd;
import net.eplanning.opensdk.CSRController;
import net.eplanning.opensdk.TargetingParameters;
import net.eplanning.opensdk.mocks.MockFBNativeBannerAdResponse;
import net.eplanning.opensdk.util.Lock;

public class CSRNativeSuccessful implements CSRAd {

    private CSRController mBC;

    @Override
    public void requestAd(Context context, String payload, CSRController mBC, TargetingParameters tp) {
        Lock.explicitSleep(2);
        this.mBC = mBC;
        if (mBC != null) {
            mBC.onAdLoaded(new MockFBNativeBannerAdResponse(mBC));
        }
    }


}
