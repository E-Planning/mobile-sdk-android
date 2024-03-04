package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.ResultCodeTest;
import net.eplanning.opensdk.TestANClickThroughAction;
import net.eplanning.opensdk.UTAdResponseTest;
import net.eplanning.opensdk.VideoImplementationTest;
import net.eplanning.opensdk.WebviewUtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        VideoImplementationTest.class,
        ResultCodeTest.class,
        WebviewUtilTest.class,
        UTAdResponseTest.class,
        TestANClickThroughAction.class,
})
public class UtAdTestSuite {
}
