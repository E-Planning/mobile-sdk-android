package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.NativeAdSDKTest;
import net.eplanning.opensdk.NativeAdToRequestParametersTest;
import net.eplanning.opensdk.NativeFriendlyObstructionTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        NativeAdSDKTest.class,
        NativeFriendlyObstructionTests.class,
        NativeAdToRequestParametersTest.class,
})
public class NativeAdSDKTestSuite {
}
