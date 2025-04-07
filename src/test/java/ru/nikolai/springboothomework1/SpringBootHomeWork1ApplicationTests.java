package ru.nikolai.springboothomework1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootHomeWork1ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void contextLoads() {
        Integer devPort = devApp.getMappedPort(8080);
        Integer prodPort = prodApp.getMappedPort(8081);

        ResponseEntity<String> entityFromDev = restTemplate.getForEntity("http://localhost:" + devPort, String.class);
        ResponseEntity<String> entityFromProd = restTemplate.getForEntity("http://localhost:" + prodPort, String.class);

        System.out.println("DevApp: " + entityFromDev.getBody());
        System.out.println("ProdApp: " + entityFromProd.getBody());
    }

}
