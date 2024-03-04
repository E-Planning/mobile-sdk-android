/*
 *    Copyright 2013 APPNEXUS INC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.eplanning.opensdk.testviews;

import android.app.Activity;

import net.eplanning.opensdk.MediatedInterstitialAdView;
import net.eplanning.opensdk.MediatedInterstitialAdViewController;
import net.eplanning.opensdk.TargetingParameters;
import net.eplanning.opensdk.util.Lock;
import net.eplanning.opensdk.util.TestUtil;
import net.eplanning.opensdk.utils.Clog;

public class MediatedInterstitialNoRequest implements MediatedInterstitialAdView {
    public static boolean didInstantiate;

    public MediatedInterstitialNoRequest() {
        super();
        Clog.d(TestUtil.testLogTag, "set to true!");
        didInstantiate = true;
    }


    @Override
    public void requestAd(MediatedInterstitialAdViewController mIC, Activity activity, String parameter, String uid, TargetingParameters tp) {
        Lock.explicitSleep(2); // This is for generating latency and total latency in the response url
        return;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void show() {

    }

    @Override
    public boolean isReady() {
        return false;
    }
}
