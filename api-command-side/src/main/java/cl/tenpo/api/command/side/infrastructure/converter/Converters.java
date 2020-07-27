package cl.tenpo.api.command.side.infrastructure.converter;

import cl.tenpo.api.command.side.application.event.CreatedAdditionEvent;
import cl.tenpo.api.command.side.domain.Addition;
import cl.tenpo.api.command.side.infrastructure.api.model.SummedValuesModel;
import cl.tenpo.api.command.side.infrastructure.api.model.StatusModel;
import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;
import cl.tenpo.api.command.side.infrastructure.enums.OperationTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * Convierte un {@link SummedValuesModel} con su {@link HttpStatus} respectivo y su mensaje a un {@link ResponseEntity}
 *
 * @author daniel.carvajal
 *
 */
@Slf4j
public class Converters {

    public static Function<Triple<SummedValuesModel, HttpStatus, String>, ResponseEntity<SummedValuesModel>> toSumedValuesResponse = from -> {
        SummedValuesModel summedValuesModel = from.getLeft();
        HttpStatus fromHttpStatus = from.getMiddle();
        String fromEstatusDetalle = from.getRight();
        //
        ResponseEntity<SummedValuesModel> to = null;
        try {
            if (Objects.nonNull(summedValuesModel) && Objects.nonNull(fromHttpStatus)) {
                StatusModel statusModel = new StatusModel();
                statusModel.setCode(fromHttpStatus.value());
                statusModel.setDescription(fromHttpStatus.getReasonPhrase());
                statusModel.setDate(new Date());
                statusModel.setDetails(fromEstatusDetalle);
                //
                summedValuesModel.setEstatus(statusModel);
                //
                to = new ResponseEntity<>(summedValuesModel, fromHttpStatus);
            } else {
                throw new IllegalStateException("Los parametros ingresados funcion [toSumedValuesResponse] no pueden ser nulos!!!");
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
                //
                throw e;
            }
        }
        return to;
    };

    public static Function<CreatedAdditionEvent,  OperationDto> toAdditionDto = from -> {
        OperationDto to = new OperationDto();
        to.setOperationId(from.getOperationId());
        to.setName(OperationTypeEnum.ADDITION.getName());
        to.setType(OperationTypeEnum.ADDITION.getType());
        to.setDescription(OperationTypeEnum.ADDITION.getDescription());
        to.setValues(Arrays.toString(from.getValues()));
        to.setResult(from.getResult());
        to.setCreatedBy(from.getCreatedBy());
        to.setCreatedDate(from.getCreatedDate());

        StringBuilder details = new StringBuilder();
        boolean interruptor = true;
        for(int value : from.getValues()) {
            if (interruptor){
                details.append(value).append(" ");
                interruptor = false;
            } else{
                details = details.append(" + ").append(value);
            }
        }
        details.append(" = ").append(from.getResult());
        //
        to.setDetails(details.toString());

        return to;
    };


    public static Function<OperationDto, Addition> toAddition = from -> {
        Addition addition = null;
        //
        int[] values = null;
        if(Objects.nonNull(from.getValues()) && !from.getValues().isEmpty()){
            values = Arrays.stream(from.getValues()
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\s", "")
                    .split(","))
            .mapToInt(i -> Integer.parseInt(i))
            .toArray();
        }
        return Addition.builder()
                .values(values).result(from.getResult()).build();
    };
}
