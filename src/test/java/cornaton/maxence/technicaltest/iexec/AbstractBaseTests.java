package cornaton.maxence.technicaltest.iexec;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Base test class for all tests. It defines the properties path.
 */
@SpringBootTest
@TestPropertySource("/application-test.properties")
abstract public class AbstractBaseTests {
}
