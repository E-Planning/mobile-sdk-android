package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.ANVideoPlayerDefaultSettingsTest;
import net.eplanning.opensdk.ANVideoPlayerSettingsTest;
import net.eplanning.opensdk.ApplicationTest;
import net.eplanning.opensdk.BaseNativeTest;
import net.eplanning.opensdk.BaseViewAdTest;
import net.eplanning.opensdk.ClogListenerTest;
import net.eplanning.opensdk.DefaultSettingsTest;
import net.eplanning.opensdk.ExecutorForBackgroundTasksTests;
import net.eplanning.opensdk.HashingFunctionsTest;
import net.eplanning.opensdk.SDKSettingsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseNativeTest.class,
        BaseViewAdTest.class,
        DefaultSettingsTest.class,
        ClogListenerTest.class,
        ExecutorForBackgroundTasksTests.class,
        HashingFunctionsTest.class,
        ANVideoPlayerDefaultSettingsTest.class,
        ANVideoPlayerSettingsTest.class,
        ApplicationTest.class,
        SDKSettingsTest.class,
})
public class DefaultSettingsTestSuite {
}
