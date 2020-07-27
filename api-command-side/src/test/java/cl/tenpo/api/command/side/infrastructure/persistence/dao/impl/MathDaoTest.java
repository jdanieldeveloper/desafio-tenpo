package cl.tenpo.api.command.side.infrastructure.persistence.dao.impl;

import cl.tenpo.api.command.side.infrastructure.dto.OperationDto;
import cl.tenpo.api.command.side.infrastructure.enums.OperationTypeEnum;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper.OperationMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel.carvajal on 10-12-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MathDaoTest {

    @Mock
    private OperationMapper operationMapper;

    @InjectMocks
    private MathDaoImpl mathDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nextOperationIdOk()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenReturn(1L);
        //
        long identifier = mathDao.nextOperationId();
        Assert.assertTrue(identifier > 0);
    }

    @Test
    public void nextOperationIdNok()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenReturn(0L);
        //
        long identifier = mathDao.nextOperationId();
        Assert.assertTrue(identifier == 0);
    }

    @Test
    public void nextOperationIdException()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenThrow(new NullPointerException());
        //
        long identifier = mathDao.nextOperationId();
        Assert.assertTrue(identifier == 0);
    }


    @Test
    public void saveOperationAdditionOk()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenReturn(1L);
        Mockito.when(operationMapper.insertOperation(Mockito.any(OperationDto.class))).thenReturn(1);
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
    public void saveOperationAdditionNOk()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenReturn(1L);
        Mockito.when(operationMapper.insertOperation(Mockito.any(OperationDto.class))).thenReturn(0);
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
        Assert.assertTrue(!isSaved);
    }


    @Test
    public void saveOperationAdditionException()  {
        Mockito.when(operationMapper.selectOperationIdFromSeq()).thenReturn(1L);
        Mockito.when(operationMapper.insertOperation(
                Mockito.any(OperationDto.class))).thenThrow(new NullPointerException());
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
        Assert.assertTrue(!isSaved);
    }


    @Test
    public void getOpByTypeAndValuesWithValues()  {
        //
        OperationDto operationDto = new OperationDto();
        operationDto.setOperationId(1);
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

        Mockito.when(operationMapper.selectOpByTypeAndValues(
                Mockito.anyString(), Mockito.anyString())).thenReturn(Lists.newArrayList(operationDto));
        //
        List<OperationDto> operationsDtos =
                mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), operationDto.getValues());
        Assert.assertEquals(operationsDtos.size(), 1);
    }

    @Test
    public void getOpByTypeAndValuesWithOutValues()  {
        Mockito.when(operationMapper.selectOpByTypeAndValues(
                Mockito.anyString(), Mockito.anyString())).thenReturn(Lists.newArrayList());
        //
        List<OperationDto> operationsDtos =
                mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), "[1, 2]");
        Assert.assertEquals(operationsDtos.size(), 0);
    }

    @Test
    public void getOpByTypeAndValuesException()  {
        Mockito.when(operationMapper.selectOpByTypeAndValues(
                Mockito.anyString(), Mockito.anyString())).thenThrow(new NullPointerException());
        //
        List<OperationDto> operationsDtos =
                mathDao.getOpByTypeAndValues(OperationTypeEnum.ADDITION.getType(), "[1, 2]");
        Assert.assertEquals(operationsDtos.size(), 0);
    }
}
