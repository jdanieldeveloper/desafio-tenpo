package cl.tenpo.api.query.side.infrastructure.api;

import cl.tenpo.api.query.side.infrastructure.config.ApiQuerySideTestConfig;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiQuerySideTestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiControllerITest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void findGrupoPorDefectoOK() {
        webTestClient.get()
                .uri("/api/math/operations/dcarvajal/sum?page=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}