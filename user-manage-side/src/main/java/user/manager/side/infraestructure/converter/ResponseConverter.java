package user.manager.side.infraestructure.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import user.manager.side.infraestructure.api.model.StatusModel;
import user.manager.side.infraestructure.api.model.UsersModel;

import java.util.Date;

@Slf4j
@Component
public class ResponseConverter implements Converter<Triple<UsersModel, HttpStatus, String>, ResponseEntity<UsersModel>> {

    @Override
    public ResponseEntity<UsersModel> convert(Triple<UsersModel, HttpStatus, String> from) {
        UsersModel usersModel = from.getLeft();
        HttpStatus httpStatus = from.getMiddle();
        String statusDetails = from.getRight();
        //
        StatusModel statusModel = new StatusModel();
        statusModel.setCode(httpStatus.value());
        statusModel.setDescription(httpStatus.getReasonPhrase());
        statusModel.setStatusDate((new Date()));
        statusModel.setDetails(statusDetails);
        //
        usersModel.setStatus(statusModel);
        //
        return new ResponseEntity<>(usersModel, httpStatus);
    }
}
