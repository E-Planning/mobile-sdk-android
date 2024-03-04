package net.eplanning.opensdk.instreamvideo;


import android.content.Context;

import net.eplanning.opensdk.AdFetcher;

public class MockVideoAdOwner extends VideoAd {

    AdFetcher adFetcher;

    public MockVideoAdOwner(Context context) {
        super(context, "PLACEMENT_ID");
        adFetcher = new AdFetcher(this);
    }

    @Override
    public boolean isReadyToStart() {
        return true;
    }


    public void startAdFetcher(){
        adFetcher.start();
    }
}
