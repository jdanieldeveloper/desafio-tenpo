package user.manager.side.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import user.manager.side.infrastructure.api.model.LoginModelReq;
import user.manager.side.infrastructure.api.model.UserModelReq;
import user.manager.side.infrastructure.config.UserManageSideConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserManageSideConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerITest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserOk() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());

        LoginModelReq loginModelReq = new LoginModelReq();
        loginModelReq.setUserLogin(userModelReq.getUserLogin());
        loginModelReq.setCurrentPassword(userModelReq.getCurrentPassword());

        //
        mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserFail() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());

        LoginModelReq loginModelReq = new LoginModelReq();
        loginModelReq.setUserLogin("dcarvajalFail"); // user fail
        loginModelReq.setCurrentPassword(userModelReq.getCurrentPassword());

        //
        mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginUserPassFail() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());

        LoginModelReq loginModelReq = new LoginModelReq();
        loginModelReq.setUserLogin(userModelReq.getUserLogin());
        loginModelReq.setCurrentPassword("123456789"); // fail pass

        //
        mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginUserValidateTokenOk() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());


        LoginModelReq loginModelReq = new LoginModelReq();
        loginModelReq.setUserLogin(userModelReq.getUserLogin());
        loginModelReq.setCurrentPassword(userModelReq.getCurrentPassword());

        MvcResult response = mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        //
        mockMvc.perform(post( "/api/auth/validate")
                .header("Authorization", response.getResponse().getHeaderValue("Authorization"))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserValidateTokenNOk() throws Exception {
        UserModelReq userModelReq = new UserModelReq();
        userModelReq.setUserLogin("dcarvajals");
        userModelReq.setCurrentPassword("1234678");

        //
        mockMvc.perform(post("/api/command/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModelReq)))
                .andDo(print())
                .andExpect(status().isOk());


        LoginModelReq loginModelReq = new LoginModelReq();
        loginModelReq.setUserLogin(userModelReq.getUserLogin());
        loginModelReq.setCurrentPassword(userModelReq.getCurrentPassword());

        MvcResult response = mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        //
        String badToken = response.getResponse().getHeaderValue("Authorization").toString().concat("bad");
        mockMvc.perform(post( "/api/auth/validate")
                .header("Authorization", badToken)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginModelReq)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
