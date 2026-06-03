package se.iths.yunuscan.microprojectorderservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import se.iths.yunuscan.microprojectorderservice.config.TestRabbitMQConfig;

@SpringBootTest
@Import(TestRabbitMQConfig.class)
class MicroProjectOrderServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
