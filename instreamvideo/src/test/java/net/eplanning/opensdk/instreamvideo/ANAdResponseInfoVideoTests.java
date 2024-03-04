/*
 *    Copyright 2018 APPNEXUS INC
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

package net.eplanning.opensdk.instreamvideo;

import net.eplanning.opensdk.ANAdResponseInfo;
import net.eplanning.opensdk.AdType;
import net.eplanning.opensdk.ResultCode;
import net.eplanning.opensdk.instreamvideo.shadows.ShadowAsyncTaskNoExecutor;
import net.eplanning.opensdk.instreamvideo.shadows.ShadowCustomWebView;
import net.eplanning.opensdk.instreamvideo.shadows.ShadowSettings;
import net.eplanning.opensdk.instreamvideo.shadows.ShadowWebSettings;
import net.eplanning.opensdk.ut.UTConstants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import junit.framework.Assert;

@Config(sdk = 21,
        shadows = {ShadowAsyncTaskNoExecutor.class,
                ShadowCustomWebView.class, ShadowWebSettings.class, ShadowSettings.class, ShadowLog.class})
@RunWith(RobolectricTestRunner.class)
public class ANAdResponseInfoVideoTests extends BaseRoboTest implements VideoAdLoadListener {

    VideoAd videoAd;
    ANAdResponseInfo adResponseInfo;

    @Override
    public void setup() {
        super.setup();
        videoAd = new VideoAd(activity,"12345");
        videoAd.setAdLoadListener(this);
    }

    @Override
    public void tearDown() {
        super.tearDown();
        videoAd = null;
        adResponseInfo = null;
    }

    //test
    @Test
    public void testAdResponseInfoRTBVideo() {
        server.setDispatcher(getDispatcher(TestUTResponses.video())); // First queue a regular HTML banner response
        assertNull(videoAd.getAdResponseInfo());
        executeVideoRequest();
        assertNotNull(videoAd.getAdResponseInfo());
        Assert.assertEquals(videoAd.getAdResponseInfo().getAdType(), AdType.VIDEO);
        Assert.assertEquals(videoAd.getAdResponseInfo().getCreativeId(), "6332753");
        Assert.assertEquals(videoAd.getAdResponseInfo().getTagId(), "123456");
        Assert.assertEquals(videoAd.getAdResponseInfo().getBuyMemberId(), 123);
        Assert.assertEquals(videoAd.getAdResponseInfo().getContentSource(), UTConstants.RTB);
        Assert.assertEquals(videoAd.getAdResponseInfo().getNetworkName(), "");
        Assert.assertEquals(videoAd.getAdResponseInfo().getAuctionId(), "123456789");
        Assert.assertEquals(videoAd.getAdResponseInfo().getCpm(), 0.000010);
        Assert.assertEquals(videoAd.getAdResponseInfo().getCpmPublisherCurrency(), 0.000010);
        Assert.assertEquals(videoAd.getAdResponseInfo().getPublisherCurrencyCode(), "$");
    }

    @Test
    public void testAdResponseInfoRTBVideoNoBid() {
        server.setDispatcher(getDispatcher(TestUTResponses.NO_BID));
        assertNull(videoAd.getAdResponseInfo());
        executeVideoRequest();
        assertNotNull(videoAd.getAdResponseInfo());
        Assert.assertEquals(videoAd.getAdResponseInfo().getAdType(), null);
        Assert.assertEquals(videoAd.getAdResponseInfo().getCreativeId(), "");
        Assert.assertEquals(videoAd.getAdResponseInfo().getTagId(), "123456789");
        Assert.assertEquals(videoAd.getAdResponseInfo().getBuyMemberId(), 0);
        Assert.assertEquals(videoAd.getAdResponseInfo().getContentSource(), "");
        Assert.assertEquals(videoAd.getAdResponseInfo().getNetworkName(), "");
        Assert.assertEquals(videoAd.getAdResponseInfo().getAuctionId(), "3552547938089377051000000");
        Assert.assertEquals(videoAd.getAdResponseInfo().getCpm(), 0d);
        Assert.assertEquals(videoAd.getAdResponseInfo().getCpmPublisherCurrency(), 0d);
        Assert.assertEquals(videoAd.getAdResponseInfo().getPublisherCurrencyCode(), "");

    }

    @Test
    public void testAdResponseInfoRTBVideoBlank() {
        server.setDispatcher(getDispatcher(TestUTResponses.blank()));
        assertNull(videoAd.getAdResponseInfo());
        executeVideoRequest();
        assertNull(videoAd.getAdResponseInfo());
    }

    private void executeVideoRequest() {
        videoAd.loadAd();

        waitForTasks();
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();

        waitForTasks();
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();
    }

    @Override
    public void onAdLoaded(VideoAd videoAd) {

    }

    @Override
    public void onAdRequestFailed(VideoAd videoAd, ResultCode errorCode) {
    }
}
