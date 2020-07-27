package user.manager.side.infraestructure.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.manager.side.infraestructure.config.UserCommandSideConfig;
import user.manager.side.infraestructure.dto.PartyDto;
import user.manager.side.infraestructure.dto.RoleDto;
import user.manager.side.infraestructure.enums.RoleEnum;
import user.manager.side.infraestructure.enums.UserStatusEnum;
import user.manager.side.infraestructure.enums.UserTypeEnum;
import user.manager.side.infraestructure.persistence.mybatis.dao.PartyDao;

import java.util.Date;


/**
 * Created by daniel.carvajal
 *
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserCommandSideConfig.class})
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
        partyDto.setCreateByUserLogin("dcarvajal");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal");

        partyDto.setUserLoginId("dcarvajal");
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
        partyDto.setCreateByUserLogin("dcarvajal");
        partyDto.setLastModifiedDate(new Date());
        partyDto.setLastModifiedByUserLogin("dcarvajal");

        partyDto.setUserLoginId("dcarvajal");
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
}
