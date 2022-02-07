package uk.co.mycompany.healthservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.mycompany.healthservice.service.impl.LocalHealthService;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(properties = {"server.port=12345"})
class HeatlhServiceApplicationTests {

	@Autowired
	private LocalHealthService localHealthService;

	@Before
	public void contextLoads() {
		assertNotNull(localHealthService);
	}

	@Test
	public void main() {
		HealthServiceApplication.main(new String[] {});
	}

}
