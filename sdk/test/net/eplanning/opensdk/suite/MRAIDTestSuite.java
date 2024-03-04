package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.MRAIDImplementationTest;
import net.eplanning.opensdk.MRAIDTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MRAIDTest.class,
        MRAIDImplementationTest.class,
})
public class MRAIDTestSuite {
}
