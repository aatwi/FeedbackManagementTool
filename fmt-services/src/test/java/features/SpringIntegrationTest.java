package features;

import dev.aatwi.fmt.services.FmtServicesApplication;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = FmtServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class SpringIntegrationTest {

    @Before
    public void setUp() {
    }
}
