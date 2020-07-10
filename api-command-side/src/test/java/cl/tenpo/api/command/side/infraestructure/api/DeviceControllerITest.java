package cl.tenpo.api.command.side.infraestructure.api;

import cl.tenpo.api.command.side.infraestructure.config.ApiCmdSideTestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiCmdSideTestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class DeviceControllerITest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

}
