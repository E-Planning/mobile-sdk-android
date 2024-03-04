package net.eplanning.opensdk.suite;


import net.eplanning.opensdk.ANOmidViewabiltyTests;
import net.eplanning.opensdk.viewability.ANOMIDNativeViewabilityTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ANOmidViewabiltyTests.class,
        ANOMIDNativeViewabilityTests.class
})
public class OmidTestSuite {
}
