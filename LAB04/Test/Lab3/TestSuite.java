package Lab3;

import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("Lab3")
@IncludePackages("Lab3")

public class TestSuite {
    // Nic dodatkowego nie jest wymagane, ponieważ jest to tylko kontener dla adnotacji
}
