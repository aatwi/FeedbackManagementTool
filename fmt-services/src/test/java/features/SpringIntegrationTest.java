package features;

import dev.aatwi.fmtservices.FmtServicesApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = FmtServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class SpringIntegrationTest {
    @Autowired
    public RestTemplate restTemplate;

}
