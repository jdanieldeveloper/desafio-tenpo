package user.manager.side.infrastructure.api;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.infrastructure.api.model.LoginModelReq;
import user.manager.side.infrastructure.api.model.LoginModelResp;
import user.manager.side.infrastructure.api.model.UserModelReq;
import user.manager.side.infrastructure.api.model.UserModelResp;
import user.manager.side.infrastructure.converter.LoginRespConverter;
import user.manager.side.infrastructure.converter.UserRespConverter;
import user.manager.side.infrastructure.handler.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

import static user.manager.side.infrastructure.util.UtilJwtToken.generateToken;
import static user.manager.side.infrastructure.util.UtilJwtToken.resolveToken;
import static user.manager.side.infrastructure.util.UtilJwtToken.validateToken;

@Slf4j
@RestController
public class UserApi {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private UserRespConverter userRespConverter;

    @Autowired
    private LoginRespConverter loginRespConverter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/api/command/user/create")
    @ApiOperation(value = "createUser", notes = "Create new user")
    public ResponseEntity<UserModelResp> creteUser(@RequestBody UserModelReq userModelReq) {
        ResponseEntity<UserModelResp> responseEntity;

        // TODO validate fields

        String messageInfo;
        UserModelResp userModelResp = new UserModelResp();
        try {
            // create person
            commandHandler.handle(new CreateUserCommand(userModelReq.getUserLogin(), userModelReq.getCurrentPassword()));

            messageInfo = MessageFormat.format("Se ha creado el nuevo usuario[{0}] correctamente!!!", userModelReq);
            log.info(messageInfo);

            userModelResp.setUser(userModelReq);
            //
            // it's Ok
            responseEntity = userRespConverter.convert(Triple.of(userModelResp, HttpStatus.OK, messageInfo));

        } catch (Exception e) {
            responseEntity = userRespConverter.convert(Triple.of(userModelResp, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
            //
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/api/auth/login")
    @ApiOperation(value = "createAuthentication", notes = "Create Authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginModelReq loginModelReq) {
        ResponseEntity<LoginModelResp> responseEntity;

        // TODO validate fields

        String messageInfo;
        LoginModelResp loginModelResp = new LoginModelResp();
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModelReq.getUserLogin(), loginModelReq.getCurrentPassword()));

            //if authentication was succesful else throw an exception
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());

            messageInfo = MessageFormat.format("Se ha autentificado el usuario[{0}] correctamente!!!", authenticate.getName());
            log.info(messageInfo);

            loginModelResp.setUser(authenticate.getName());
            loginModelResp.setToken(generateToken(userDetails)); // generate token
            //
            // it's Ok
            responseEntity = loginRespConverter.convert(Triple.of(loginModelResp, HttpStatus.OK, messageInfo));

        } catch (Exception e) {
            responseEntity = loginRespConverter.convert(Triple.of(loginModelResp, HttpStatus.UNAUTHORIZED, e.getMessage()));
            //
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/api/auth/validate")
    @ApiOperation(value = "createAuthentication", notes = "Validate Authentication")
    public ResponseEntity<?> validateAuthenticationToken(HttpServletRequest request, @RequestBody LoginModelReq loginModelReq) {
        ResponseEntity<LoginModelResp> responseEntity;

        // TODO validate fields

        String messageInfo;
        LoginModelResp loginModelResp = new LoginModelResp();
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModelReq.getUserLogin(), loginModelReq.getCurrentPassword()));

            //if authentication was succesful else throw an exception
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());
            boolean isValid = validateToken(resolveToken(request), userDetails);
            // check
            if(isValid){
                messageInfo = MessageFormat.format("Se ha validado el usuario[{0}] correctamente!!!", authenticate.getName());
                log.info(messageInfo);

                loginModelResp.setUser(authenticate.getName());
                loginModelResp.setToken(generateToken(userDetails)); // generate token
                //
                // it's Ok
                responseEntity = loginRespConverter.convert(Triple.of(loginModelResp, HttpStatus.OK, messageInfo));

            }else{
                // it's Nok
                messageInfo = MessageFormat.format("No Se ha validado el usuario[{0}] correctamente!!!", authenticate.getName());
                log.info(messageInfo);

                responseEntity = loginRespConverter.convert(Triple.of(loginModelResp, HttpStatus.UNAUTHORIZED, messageInfo));
            }

        } catch (Exception e) {
            responseEntity = loginRespConverter.convert(Triple.of(loginModelResp, HttpStatus.UNAUTHORIZED, e.getMessage()));
            //
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return responseEntity;
    }
}
