package net.eplanning.opensdk.testviews;

import android.content.Context;

import net.eplanning.opensdk.CSRAd;
import net.eplanning.opensdk.CSRController;
import net.eplanning.opensdk.ResultCode;
import net.eplanning.opensdk.TargetingParameters;
import net.eplanning.opensdk.util.Lock;

public class CSRNativeNoFill implements CSRAd {
    @Override
    public void requestAd(Context context, String payload, CSRController mBC, TargetingParameters tp) {
        Lock.explicitSleep(2);
        if (mBC != null) {
            mBC.onAdFailed(ResultCode.getNewInstance(ResultCode.UNABLE_TO_FILL));
        }
    }
}
