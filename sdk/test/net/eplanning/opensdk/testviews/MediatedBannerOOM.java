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
import android.view.View;

import net.eplanning.opensdk.MediatedBannerAdView;
import net.eplanning.opensdk.MediatedBannerAdViewController;
import net.eplanning.opensdk.TargetingParameters;
import net.eplanning.opensdk.util.Lock;

public class MediatedBannerOOM implements MediatedBannerAdView {
    @Override
    public View requestAd(MediatedBannerAdViewController mBC, Activity activity, String parameter, String uid, int width, int height, TargetingParameters tp) {
        Lock.explicitSleep(2); // This is for generating latency and total latency in the response url
        throw new OutOfMemoryError("Out of memory!");
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
}
