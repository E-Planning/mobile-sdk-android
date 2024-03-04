package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.AdActivityTest;
import net.eplanning.opensdk.AdFetcherTest;
import net.eplanning.opensdk.AdRequestToAdRequesterTest;
import net.eplanning.opensdk.AdViewFriendlyObstructionTests;
import net.eplanning.opensdk.AdViewRequestManagerTest;
import net.eplanning.opensdk.UTAdRequestTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdViewFriendlyObstructionTests.class,
        AdViewRequestManagerTest.class,
        AdActivityTest.class,
        AdFetcherTest.class,
        AdRequestToAdRequesterTest.class,
        UTAdRequestTest.class,
})
public class AdRequestTestSuite {
}
