package net.eplanning.opensdk.instreamvideo;

import android.app.Activity;
import net.eplanning.opensdk.XandrAd;
import net.eplanning.opensdk.instreamvideo.shadows.ShadowSettings;
import net.eplanning.opensdk.instreamvideo.util.Lock;
import net.eplanning.opensdk.ut.UTConstants;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.Scheduler;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static org.robolectric.Shadows.shadowOf;

import androidx.annotation.NonNull;

public class BaseRoboTest {
    public static final int placementID = 1;
    public static final int width = 320;
    public static final int height = 50;
    Activity activity;
    Scheduler uiScheduler, bgScheduler;
    public MockWebServer server;

    @Before
    public void setup() {
        XandrAd.init(9325, null, false, null);
        Robolectric.getBackgroundThreadScheduler().reset();
        Robolectric.getForegroundThreadScheduler().reset();
        ShadowLog.stream = System.out;
        activity = Robolectric.buildActivity(MockMainActivity.class).create().start().resume().visible().get();
        shadowOf(activity).grantPermissions("android.permission.INTERNET");
        server= new MockWebServer();
        try {
            server.start();
            HttpUrl url= server.url("/");
            UTConstants.REQUEST_BASE_URL_UT = url.toString();
            System.out.println(UTConstants.REQUEST_BASE_URL_UT);
            ShadowSettings.setTestURL(url.toString());
        } catch (IOException e) {
            System.out.print("IOException");
        }
        bgScheduler = Robolectric.getBackgroundThreadScheduler();
        uiScheduler = Robolectric.getForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        Robolectric.flushForegroundThreadScheduler();
        bgScheduler.pause();
        uiScheduler.pause();
    }


    @After
    public void tearDown() {
        try {
            server.shutdown();
            bgScheduler.reset();
            uiScheduler.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        activity.finish();
    }

    private void scheduleTimerToCheckForTasks() {
        Timer timer = new Timer();
        final int[] counter = {330};
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter[0]--;
                if (uiScheduler.areAnyRunnable() || bgScheduler.areAnyRunnable() || counter[0] == 0) {
                    Lock.unpause();
                    this.cancel();
                }
            }
        }, 0, 100);
    }

    public void waitForTasks() {
        scheduleTimerToCheckForTasks();
        Lock.pause();
    }

    /**
     *creates a mock web server dispatcher with prerecorded requests and responses
     **/
    public Dispatcher getDispatcher(final String response) {
        return new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest request) {
                return new MockResponse().setResponseCode(200)
                        .setBody(response);
            }
        };
    }
}