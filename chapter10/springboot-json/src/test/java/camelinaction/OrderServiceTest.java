package camelinaction;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit test with Camel and spring-boot
@CamelSpringBootTest
@SpringBootTest(classes = OrderApplication.class,
    // turn on web during test on the defined 8080 port
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// re-create Spring/Camel for each test
@DirtiesContext
public class OrderServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceTest.class);

    // inject template so we can use Camel to send a message to the order service via http
    @Autowired
    private ProducerTemplate template;

    @Test
    public void testCreateOrder() throws Exception {
        String json = "{\"partName\":\"motor\",\"amount\":1,\"customerName\":\"honda\"}";

        LOG.info("Sending order using json payload: {}", json);

        // use http component to send the order
        String id = template.requestBody("http://localhost:8080/api/orders", json, String.class);
        assertNotNull(id);

        LOG.info("Created new order with id " + id);

        // should create a new order with id 3 or 4
        assertTrue(id.contains("3") || id.contains("4"));
    }

    @Test
    public void testCreateAndGetOrder() throws Exception {
        String json = "{\"partName\":\"motor\",\"amount\":1,\"customerName\":\"honda\"}";

        LOG.info("Sending order using json payload: {}", json);

        // use http component to send the order
        String id = template.requestBody("http://localhost:8080/api/orders", json, String.class);
        assertNotNull(id);

        LOG.info("Created new order with id " + id);

        // should create a new order with id 3 or 4
        assertTrue(id.contains("3") || id.contains("4"));

        // use http component to get the order
        String response = template.requestBody("http://localhost:8080/api/orders/" + id, null, String.class);
        LOG.info("Response: {}", response);
    }

}
