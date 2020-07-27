package cl.tenpo.api.command.side.infrastructure.api;

import cl.tenpo.api.command.side.infrastructure.api.model.ValuesModel;
import cl.tenpo.api.command.side.infrastructure.config.ApiCmdSideTestConfig;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.MathDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiCmdSideTestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ApiControllerITest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MathDao mathDao;

    //@SpyBean
    //private CommandHandler commandHandler;

    @Test
    public void postSumValues200Ok() throws Exception {
        ValuesModel valuesModel = new ValuesModel();
        valuesModel.setValue1(1);
        valuesModel.setValue2(1);
        //
        mockMvc.perform(post("/api/math/operation/sum")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(valuesModel)))
                .andDo(print())
        .andExpect(status().isOk());
    }


    @Test
    public void postSumValues202Accepted() throws Exception {
        ValuesModel valuesModel = new ValuesModel();
        valuesModel.setValue1(1);
        valuesModel.setValue2(1);
        //
        Mockito.when(mathDao.getOpByTypeAndValues(Mockito.any(), Mockito.any()))
                .thenThrow(NullPointerException.class);
        //
        mockMvc.perform(post("/api/math/operation/sum")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(valuesModel)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }


    /*@Test //TODO to implement
    public void postSumValues500InteralServerError() throws Exception {
        ValuesModel valuesModel = new ValuesModel();
        valuesModel.setValue1(1);
        valuesModel.setValue2(1);
        //
        Mockito.when(commandHandler.handle(Mockito.any()))
                .thenThrow(Exception.class);
        //
        mockMvc.perform(post("/api/math/operation/sum")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(valuesModel)))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }*/
}



