package user.manager.side.infrastructure.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import user.manager.side.infrastructure.api.model.StatusModel;
import user.manager.side.infrastructure.api.model.UserModelResp;

import java.util.Date;

@Slf4j
@Component
public class UserRespConverter implements Converter<Triple<UserModelResp, HttpStatus, String>, ResponseEntity<UserModelResp>> {

    @Override
    public ResponseEntity<UserModelResp> convert(Triple<UserModelResp, HttpStatus, String> from) {
        UserModelResp userModelResp = from.getLeft();
        HttpStatus httpStatus = from.getMiddle();
        String statusDetails = from.getRight();
        //
        StatusModel statusModel = new StatusModel();
        statusModel.setCode(httpStatus.value());
        statusModel.setDescription(httpStatus.getReasonPhrase());
        statusModel.setStatusDate((new Date()));
        statusModel.setDetails(statusDetails);
        //
        userModelResp.setStatus(statusModel);
        //
        return new ResponseEntity<>(userModelResp, httpStatus);
    }
}
