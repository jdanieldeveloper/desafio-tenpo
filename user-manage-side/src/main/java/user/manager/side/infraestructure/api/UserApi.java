package user.manager.side.infraestructure.api;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.manager.side.application.command.CreateGroupCommand;
import user.manager.side.application.command.CreateUserCommand;
import user.manager.side.infraestructure.api.model.UserModel;
import user.manager.side.infraestructure.api.model.UsersModel;
import user.manager.side.infraestructure.converter.ResponseConverter;
import user.manager.side.infraestructure.enums.UserTypeEnum;
import user.manager.side.infraestructure.handler.CommandHandler;

import java.text.MessageFormat;
import java.util.Arrays;


@Slf4j
@RestController
public class UserApi {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private ResponseConverter responseConverter;

    @PostMapping(value = "/api/command/user/create")
    @ApiOperation(value = "createUser", notes = "Create new user")
    public ResponseEntity<UsersModel> creteUser(@RequestBody UserModel userModel) {
        ResponseEntity<UsersModel> responseEntity;

        // TODO validate fields

        String messageInfo;
        UsersModel usersModel = new UsersModel();
        try {
            if (UserTypeEnum.VISITOR.getTypeId().equals(userModel.getUserType())) {
                // create person
                commandHandler.handle(new CreateUserCommand(userModel.getSocialIdentifier(), userModel.getFirstName(), userModel.getLastName()));

                messageInfo = MessageFormat.format("Se ha creado el nuevo usuario[{0}] correctamente!!!", userModel);
                log.info(messageInfo);
            } else {
                // create party group
                commandHandler.handle(new CreateGroupCommand(userModel.getSocialIdentifier(), userModel.getGroupName()));
                //
                messageInfo = MessageFormat.format("Se ha creado el nuevo grupo[{0}] correctamente!!!", userModel);
                log.info(messageInfo);
            }
            usersModel.setUsers(Arrays.asList(userModel));
            //
            // it's Ok
            responseEntity = responseConverter.convert(Triple.of(usersModel, HttpStatus.OK, messageInfo));

        } catch (Exception e) {
            responseEntity = responseConverter.convert(Triple.of(usersModel, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
            //
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return responseEntity;
    }


}
