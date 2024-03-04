package net.eplanning.opensdk.suite;

import net.eplanning.opensdk.ANAdResponseInfoBannerTests;
import net.eplanning.opensdk.ANAdResponseInfoInterstitialTests;
import net.eplanning.opensdk.ANAdResponseInfoNativeTest;
import net.eplanning.opensdk.AdListenerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ANAdResponseInfoInterstitialTests.class,
        ANAdResponseInfoNativeTest.class,
        ANAdResponseInfoBannerTests.class,
        AdListenerTest.class,
})
public class AdResponseInfoTestSuite {
}
