package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.BannerAdToRequestParametersTest;
import net.eplanning.opensdk.BannerAdViewLoadAdTests;
import net.eplanning.opensdk.BannerAdViewTest;
import net.eplanning.opensdk.BannerImpressionTests;
import net.eplanning.opensdk.InterstitialAdToRequestParametersTest;
import net.eplanning.opensdk.InterstitialAdViewLoadAdTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BannerAdViewLoadAdTests.class,
        BannerAdToRequestParametersTest.class,
        BannerAdViewTest.class,
        BannerImpressionTests.class,
        InterstitialAdToRequestParametersTest.class,
        InterstitialAdViewLoadAdTest.class,
})
public class AdViewTestSuite {
}
