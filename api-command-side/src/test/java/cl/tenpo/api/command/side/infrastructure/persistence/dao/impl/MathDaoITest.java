package cl.tenpo.api.command.side.infrastructure.persistence.dao.impl;

import cl.tenpo.api.command.side.infrastructure.config.ApiCmdSideTestConfig;
import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;
import cl.tenpo.api.command.side.infrastructure.enums.OperationTypeEnum;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.MathDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by daniel.carvajal on 10-12-2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiCmdSideTestConfig.class})
public class MathDaoITest {

    @Autowired
    private MathDao mathDao;

    @Test
    public void nextOperationIdOk()  {
        //
        long identifier = mathDao.nextOperationId();
        Assert.assertTrue(identifier > 0);
    }

    @Test
    public void saveOperationOk()  {
        //
        long operationId = mathDao.nextOperationId();
        Assert.assertTrue(operationId > 0);

        OperationDto operationDto = new OperationDto();
        operationDto.setOperationId(operationId);
        operationDto.setName(OperationTypeEnum.ADDITION.getName());
        operationDto.setType(OperationTypeEnum.ADDITION.getType());
        operationDto.setDescription(OperationTypeEnum.ADDITION.getDescription());
        //
        operationDto.setValues(Arrays.toString(new int[] {1,2,3}));
        operationDto.setResult(6);
        //
        operationDto.setCreatedBy("dcarvajal");
        operationDto.setCreatedDate(new Date());
        operationDto.setDetails("1 + 1 = 2");

        boolean isSaved = mathDao.saveOperation(operationDto);
        Assert.assertTrue(isSaved);
    }

    @Test
    public void getOpByTypeAndValuesOk()  {
        //
        long operationId = mathDao.nextOperationId();
        Assert.assertTrue(operationId > 0);

        OperationDto operationDto = new OperationDto();
        operationDto.setOperationId(operationId);
        operationDto.setName(OperationTypeEnum.ADDITION.getName());
        operationDto.setType(OperationTypeEnum.ADDITION.getType());
        operationDto.setDescription(OperationTypeEnum.ADDITION.getDescription());
        //
        operationDto.setValues(Arrays.toString(new int[] {1,2,3}));
        operationDto.setResult(6);
        //
        operationDto.setCreatedBy("dcarvajal");
        operationDto.setCreatedDate(new Date());
        operationDto.setDetails("1 + 1 = 2");

        boolean isSaved = mathDao.saveOperation(operationDto);
        Assert.assertTrue(isSaved);

        List<OperationDto> operationsDtos =
                mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), operationDto.getValues());
        Assert.assertTrue(!operationsDtos.isEmpty());
    }

}
