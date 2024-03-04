package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.ANMultiAdRequestApiTest;
import net.eplanning.opensdk.ANMultiAdRequestLoadTests;
import net.eplanning.opensdk.ANMultiAdRequestToRequestParametersTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ANMultiAdRequestApiTest.class,
        ANMultiAdRequestToRequestParametersTest.class,
        ANMultiAdRequestLoadTests.class,

})
public class MARTestSuite {
}
