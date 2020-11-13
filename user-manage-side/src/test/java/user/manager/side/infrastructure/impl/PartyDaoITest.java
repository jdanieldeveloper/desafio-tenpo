package user.manager.side.infrastructure.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.manager.side.infrastructure.config.UserManageSideConfig;
import user.manager.side.infrastructure.dto.PartyDto;
import user.manager.side.infrastructure.dto.RoleDto;
import user.manager.side.infrastructure.enums.RoleEnum;
import user.manager.side.infrastructure.enums.UserStatusEnum;
import user.manager.side.infrastructure.enums.UserTypeEnum;
import user.manager.side.infrastructure.persistence.mybatis.dao.PartyDao;

import java.util.Date;
import java.util.List;


/**
 * Created by daniel.carvajal
 *
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserManageSideConfig.class})
public class PartyDaoITest {

    @Autowired
    private PartyDao partyDao;

    @Test
    public void nexValueForIdentifierOK()  {
        //
        long identifier = partyDao.nexValueForIdentifier();
        Assert.assertTrue(identifier > 0);
    }

    @Test
    public void saveUserLogin()  {
        //
        long partyId = partyDao.nexValueForIdentifier();
        Assert.assertTrue(partyId > 0);

        PartyDto partyDto = new PartyDto();
        partyDto.setPartyId(partyId);

        partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
        partyDto.setDescription("Personaje de la serie los Simpson");
        partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
        //
        partyDto.setCreatedDate(new Date());
        partyDto.setCreateByUserLogin("dcarvajal1");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal1");

        partyDto.setUserLoginId("dcarvajal1");
        partyDto.setCurrentPassword("123456789");

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        Assert.assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        Assert.assertTrue(isSaved);

    }

    @Test
    public void saveUserLoginWithRoles()  {
        //
        long partyId = partyDao.nexValueForIdentifier();
        Assert.assertTrue(partyId > 0);

        PartyDto partyDto = new PartyDto();
        partyDto.setPartyId(partyId);

        partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
        partyDto.setDescription("Personaje de la serie los Simpson");
        partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
        //
        partyDto.setCreatedDate(new Date());
        partyDto.setCreateByUserLogin("dcarvajal2");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal2");

        partyDto.setUserLoginId("dcarvajal2");
        partyDto.setCurrentPassword("123456789");

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        Assert.assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        Assert.assertTrue(isSaved);

        RoleDto roleDto = new RoleDto();
        roleDto.setPartyId(partyId);
        roleDto.setRoleTypeId(RoleEnum.VISITOR_CREATE.getRoleId());
        roleDto.setDescription(RoleEnum.VISITOR_CREATE.getDescription());
        boolean isSavedRole = partyDao.saveUserRole(roleDto);
        Assert.assertTrue(isSavedRole);

    }

    @Test
    public void saveUserLoginAndFindUser() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        Assert.assertTrue(partyId > 0);

        PartyDto partyDto = new PartyDto();
        partyDto.setPartyId(partyId);

        partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
        partyDto.setDescription("Personaje de la serie los Simpson");
        partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
        //
        partyDto.setCreatedDate(new Date());
        partyDto.setCreateByUserLogin("dcarvajal3");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal3");

        partyDto.setUserLoginId("dcarvajal3");
        partyDto.setCurrentPassword("123456789");

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        Assert.assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        Assert.assertTrue(isSaved);

        PartyDto partyDtoFind = partyDao.findPartyByUserLoginId(partyDto.getUserLoginId());
        Assert.assertEquals(partyDto.getUserLoginId(), partyDtoFind.getUserLoginId());

    }

    @Test
    public void saveUserLoginAndFindRoles() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        Assert.assertTrue(partyId > 0);

        PartyDto partyDto = new PartyDto();
        partyDto.setPartyId(partyId);

        partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
        partyDto.setDescription("Personaje de la serie los Simpson");
        partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
        //
        partyDto.setCreatedDate(new Date());
        partyDto.setCreateByUserLogin("dcarvajal4");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal4");

        partyDto.setUserLoginId("dcarvajal4");
        partyDto.setCurrentPassword("123456789");

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        Assert.assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        Assert.assertTrue(isSaved);

        RoleDto roleDtoCreate = new RoleDto();
        roleDtoCreate.setPartyId(partyId);
        roleDtoCreate.setRoleTypeId(RoleEnum.VISITOR_CREATE.getRoleId());
        roleDtoCreate.setDescription(RoleEnum.VISITOR_CREATE.getDescription());
        boolean isSavedRole = partyDao.saveUserRole(roleDtoCreate);
        Assert.assertTrue(isSavedRole);

        RoleDto roleDtoUpdate = new RoleDto();
        roleDtoUpdate.setPartyId(partyId);
        roleDtoUpdate.setRoleTypeId(RoleEnum.VISITOR_UPDATE.getRoleId());
        roleDtoUpdate.setDescription(RoleEnum.VISITOR_UPDATE.getDescription());
        isSavedRole = partyDao.saveUserRole(roleDtoUpdate);
        Assert.assertTrue(isSavedRole);

        List<RoleDto> roleDtos = partyDao.findRoleByUserLoginId(partyDto.getUserLoginId());
        Assert.assertEquals(roleDtos.size(), 2);

    }


    @Test
    public void saveUserLoginAndFindUserWithRoles() {
        //
        long partyId = partyDao.nexValueForIdentifier();
        Assert.assertTrue(partyId > 0);

        PartyDto partyDto = new PartyDto();
        partyDto.setPartyId(partyId);

        partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
        partyDto.setDescription("Personaje de la serie los Simpson");
        partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
        //
        partyDto.setCreatedDate(new Date());
        partyDto.setCreateByUserLogin("dcarvajal5");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal5");

        partyDto.setUserLoginId("dcarvajal5");
        partyDto.setCurrentPassword("123456789");

        boolean isSavedPerson = partyDao.saveUserLogin(partyDto);
        Assert.assertTrue(isSavedPerson);

        boolean isSaved = partyDao.saveParty(partyDto);
        Assert.assertTrue(isSaved);

        RoleDto roleDtoCreate = new RoleDto();
        roleDtoCreate.setPartyId(partyId);
        roleDtoCreate.setRoleTypeId(RoleEnum.VISITOR_CREATE.getRoleId());
        roleDtoCreate.setDescription(RoleEnum.VISITOR_CREATE.getDescription());
        boolean isSavedRole = partyDao.saveUserRole(roleDtoCreate);
        Assert.assertTrue(isSavedRole);

        RoleDto roleDtoUpdate = new RoleDto();
        roleDtoUpdate.setPartyId(partyId);
        roleDtoUpdate.setRoleTypeId(RoleEnum.VISITOR_UPDATE.getRoleId());
        roleDtoUpdate.setDescription(RoleEnum.VISITOR_UPDATE.getDescription());
        isSavedRole = partyDao.saveUserRole(roleDtoUpdate);
        Assert.assertTrue(isSavedRole);


        PartyDto partyDtoFind = partyDao.findPartyByUserLoginId(partyDto.getUserLoginId());
        Assert.assertEquals(partyDto.getUserLoginId(), partyDtoFind.getUserLoginId());
        //
        List<RoleDto> roleDtos = partyDao.findRoleByUserLoginId(partyDto.getUserLoginId());
        Assert.assertEquals(roleDtos.size(), 2);

    }
}
