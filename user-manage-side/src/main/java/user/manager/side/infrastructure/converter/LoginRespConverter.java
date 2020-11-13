package user.manager.side.infrastructure.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import user.manager.side.infrastructure.api.model.LoginModelResp;
import user.manager.side.infrastructure.api.model.StatusModel;


import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class LoginRespConverter implements Converter<Triple<LoginModelResp, HttpStatus, String>, ResponseEntity<LoginModelResp>> {

    @Override
    public ResponseEntity<LoginModelResp> convert(Triple<LoginModelResp, HttpStatus, String> from) {
        LoginModelResp loginModelResp = from.getLeft();
        HttpStatus httpStatus = from.getMiddle();
        String statusDetails = from.getRight();
        //
        StatusModel statusModel = new StatusModel();
        statusModel.setCode(httpStatus.value());
        statusModel.setDescription(httpStatus.getReasonPhrase());
        statusModel.setStatusDate((new Date()));
        statusModel.setDetails(statusDetails);
        //
        loginModelResp.setStatus(statusModel);
        // set Auth header
        HttpHeaders headers = new HttpHeaders();
        if(Objects.nonNull(loginModelResp) && Objects.nonNull(loginModelResp.getToken())) {
            headers.add("Authorization", String.format("Bearer %s", loginModelResp.getToken()));
        }
        return new ResponseEntity<>(loginModelResp, headers, httpStatus);
    }
}
