package net.eplanning.opensdk.suite;

import net.eplanning.opensdk.MediatedBannerAdViewControllerTest;
import net.eplanning.opensdk.MediatedInterstitialAdViewControllerTest;
import net.eplanning.opensdk.MediatedNativeAdViewControllerTest;
import net.eplanning.opensdk.MediatedSSMAdViewControllerTest;
import net.eplanning.opensdk.MediationCallbacksTest;
import net.eplanning.opensdk.MediationTimeoutTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MediatedBannerAdViewControllerTest.class,
        MediatedInterstitialAdViewControllerTest.class,
        MediatedSSMAdViewControllerTest.class,
        MediatedNativeAdViewControllerTest.class,
        MediationTimeoutTest.class,
        MediationCallbacksTest.class,
})
public class MediationTestSuite {
}
