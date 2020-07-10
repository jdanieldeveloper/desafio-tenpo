package cl.tenpo.api.command.side.application.repository.imp;

import cl.tenpo.api.command.side.application.repository.impl.MathRepositoryImpl;
import cl.tenpo.api.command.side.domain.Addition;
import cl.tenpo.api.command.side.infraestructure.dto.OperationDto;
import cl.tenpo.api.command.side.infraestructure.enums.OperationTypeEnum;
import cl.tenpo.api.command.side.infraestructure.persistence.dao.MathDao;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;


/**
 * Created by daniel.carvajal handle 10-12-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MathRepositoryTest {

    @Mock
    private MathDao mathDao;

    @InjectMocks
    private MathRepositoryImpl mathRepository;

    @Test
    public void nextOperationIdOk() {
        Mockito.when(mathDao.nextOperationId()).thenReturn(1L);
        //
        long operationId = mathRepository.getOperationId();
        Assert.assertEquals(operationId, 1);
    }

    @Test
    public void getAdditionOpByValuesIsPresent() {
        OperationDto operationDto = new OperationDto();
        operationDto.setOperationId(1);
        operationDto.setName(OperationTypeEnum.ADDITION.getName());
        operationDto.setType(OperationTypeEnum.ADDITION.getType());
        operationDto.setDescription(OperationTypeEnum.ADDITION.getDescription());
        //
        operationDto.setValues(Arrays.toString(new int[]{1, 2, 3}));
        operationDto.setResult(6);
        //
        operationDto.setCreatedBy("dcarvajal");
        operationDto.setCreatedDate(new Date());
        operationDto.setDetails("1 + 1 = 2");

        Mockito.when(
                mathDao.getOpByTypeAndValues(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Lists.newArrayList(operationDto, operationDto, operationDto));

        Optional<Addition> addition = mathRepository.getAdditionOpByValues(1, 2);
        Assert.assertTrue(addition.isPresent());
    }

    @Test
    public void getAdditionOpByValuesNotPresent() {
        Mockito.when(
                mathDao.getOpByTypeAndValues(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Lists.newArrayList());

        Optional<Addition> addition = mathRepository.getAdditionOpByValues(1, 2);
        Assert.assertTrue(!addition.isPresent());
    }
}
