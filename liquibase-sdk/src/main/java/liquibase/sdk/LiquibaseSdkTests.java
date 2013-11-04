package liquibase.sdk;

import liquibase.sdk.standardtests.change.StandardChangeTests;
import liquibase.sdk.standardtests.database.StandardDatabaseTests;
import liquibase.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Suite.class)
@Suite.SuiteClasses({StandardDatabaseTests.class, StandardChangeTests.class, LiquibaseSdkTests.EnvironmentTest.class})
public class LiquibaseSdkTests {

    public static class EnvironmentTest {

        private Context context;

        @Before
        public void before () {
            this.context = Context.getInstance();
        }

        @Test
        public void liquibaseSdkPropertiesFound() throws Exception {
            assertEquals("There should be exactly one "+Context.LIQUIBASE_SDK_PROPERTIES_FILENAME+" file in the root of your classpath", 1, Collections.list(this.getClass().getClassLoader().getResources(Context.LIQUIBASE_SDK_PROPERTIES_FILENAME)).size());
        }

        @Test
        public void contextInitialized() {
            if (!context.isInitialized()) {
                fail("Testing Context is not initialized. Create a "+Context.LIQUIBASE_SDK_PROPERTIES_FILENAME+" file in the root of your classpath.");
            }
        }

        @Test
        public void classesFound() {
            if (context.isInitialized()) {
                assertTrue("No extension classes were found", context.getSeenExtensionClasses().size() > 0);
            }
        }
    }

}
