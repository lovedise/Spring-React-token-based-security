package com.namee;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NosUtilApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0"})

public class NosUtilApplicationTests {
	@Value("${local.server.port}")
	protected int port;

	protected RestTemplate restTemplate = new TestRestTemplate();
}
