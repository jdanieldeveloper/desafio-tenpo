package cl.tenpo.api.query.side.infrastructure.persistence.dao.impl;

import cl.tenpo.api.query.side.infrastructure.ApiQuerySideApp;
import cl.tenpo.api.query.side.infrastructure.config.ApiQuerySideTestConfig;
import cl.tenpo.api.query.side.infrastructure.persistence.repository.OperationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiQuerySideApp.class})
@ContextConfiguration(classes = {ApiQuerySideTestConfig.class})
public class RepositoryDaoITest {

    @Autowired
    private OperationRepository repository;

    @Test
    public void findAllSumOpByUserWithOutPaginateOk(){
        repository
                .findAllOpByUser("dcarvajal")
                .as(StepVerifier::create)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void findAllSumOpByUserWithPaginateOk(){
                repository
                        .findAllOpByUser("dcarvajal", 5, 0)
                        .as(StepVerifier::create)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void findAllSumOpByUserWithPaginateOutOfLimitOk(){
        repository
                .findAllOpByUser("dcarvajal", 10, 0)
                .as(StepVerifier::create)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void findAllSumOpByUserWithPaginateInLimitOk(){
        repository
                .findAllOpByUser("dcarvajal", 3, 0)
                .as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void findAllSumOpByUserWithPaginateRange2To4Ok(){
        repository
                .findAllOpByUser("dcarvajal", 2, 2)
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }
}
