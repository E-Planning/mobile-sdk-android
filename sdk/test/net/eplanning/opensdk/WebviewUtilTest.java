/*
/*
 *    Copyright 2016 APPNEXUS INC
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
package net.eplanning.opensdk;

import net.eplanning.opensdk.mocks.MockDefaultExecutorSupplier;
import net.eplanning.opensdk.shadows.ShadowAsyncTaskNoExecutor;
import net.eplanning.opensdk.shadows.ShadowSettings;
import net.eplanning.opensdk.shadows.ShadowWebSettings;
import net.eplanning.opensdk.util.Lock;
import net.eplanning.opensdk.utils.Settings;
import net.eplanning.opensdk.utils.WebviewUtil;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowWebView;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

import androidx.annotation.NonNull;

@Config(sdk = 21,
        shadows = {ShadowAsyncTaskNoExecutor.class,
                ShadowWebView.class, ShadowWebSettings.class, ShadowSettings.class,ShadowLog.class})
@RunWith(RobolectricTestRunner.class)
public class WebviewUtilTest extends BaseViewAdTest {

    AdViewRequestManager requestManager2;

    @Override
    public void setup() {
        super.setup();
        requestManager = new AdViewRequestManager(bannerAdView);
        requestManager2 = new AdViewRequestManager(interstitialAdView);
    }


    //Set the cookies to -1
    public void resetCookies() {

    }

    //This verifies that the AsyncTask for Request is being executed on the Correct Executor.
    @Test
    public void testRequestExecutorForBackgroundTasks() {
        SDKSettings.setExternalExecutor(MockDefaultExecutorSupplier.getInstance().forBackgroundTasks());
        assertNotSame(ShadowAsyncTaskNoExecutor.getExecutor(), MockDefaultExecutorSupplier.getInstance().forBackgroundTasks());
        requestManager.execute();
        assertEquals(ShadowAsyncTaskNoExecutor.getExecutor(), MockDefaultExecutorSupplier.getInstance().forBackgroundTasks());
    }


    //This verifies that the cookies in response are synced correctly to the device.
    @Test
    public void test1CookiesSync() {
        server.setDispatcher(getWebViewDispatcherWithHeader(TestResponsesUT.banner(), "Set-Cookie", TestResponsesUT.UUID_COOKIE_1));
        requestManager.execute();
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();
        String wvcookie = WebviewUtil.getCookie();
        //Asserts the Cookie stored in the device is the same as that of the one we sent back in the response.
        assertEquals(getUUId2(wvcookie), getUUId2(TestResponsesUT.UUID_COOKIE_1));
    }

    //This verifies the Cookie is reset properly.
    @Test
    public void test2CookiesReset() {
        server.setDispatcher(getWebViewDispatcherWithHeader(TestResponsesUT.banner(), "Set-Cookie", TestResponsesUT.UUID_COOKIE_RESET));
        requestManager.execute();
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();

        String wvcookie = WebviewUtil.getCookie();
        System.out.println(wvcookie);
        assertEquals(getUUId2(wvcookie), getUUId2(TestResponsesUT.UUID_COOKIE_RESET));
    }

    private static String getUUId2(String wvcookie) {
        String[] existingCookies = wvcookie.split("; ");
        for (String cookie : existingCookies) {
            if (cookie != null && cookie.contains(Settings.AN_UUID)) {
                return cookie;
            }
        }
        return null;
    }


    @Override
    public void onAdLoaded(AdView adView) {
        super.onAdLoaded(adView);
        Lock.unpause();
        System.out.println("Ad loaded unpassed");
    }

    @Override
    public void onAdRequestFailed(AdView adView, ResultCode resultCode) {
        super.onAdRequestFailed(adView, resultCode);
        Lock.unpause();
    }

    private Dispatcher getWebViewDispatcherWithHeader(final String response, final String headerName, final String cookieStrings) {
        return new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest request) {
                return new MockResponse().setResponseCode(200)
                        .setHeader(headerName, cookieStrings)
                        .setBody(response);
            }
        };
    }

// @TODO RoboCookieManager adds cookies to the cookie store instead of replacing them need to figure out a way around that.
// @TODO also server.takerequest doesnot have the cookie in the header so unable to test cookie sent correctly in Unit test cases.


}
