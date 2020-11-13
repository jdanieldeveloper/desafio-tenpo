package cl.tenpo.api.command.side.infrastructure.persistence.dao.impl;

import cl.tenpo.api.command.side.infrastructure.dto.PartyDto;
import cl.tenpo.api.command.side.infrastructure.dto.RoleDto;
import cl.tenpo.api.command.side.infrastructure.persistence.dao.PartyDao;
import cl.tenpo.api.command.side.infrastructure.persistence.mybatis.mapper.PartyMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 * @author daniel.carvajal
 */
@Slf4j
@Component
public class PartyDaoImpl implements PartyDao {

    @Autowired
    private PartyMapper partyMapper;


    @Override
    public PartyDto findPartyByUserLoginId(String userLoginId) {
        PartyDto partyDto = null;
        try {
            partyDto = partyMapper.findPartyByUserLoginId(userLoginId);
            if(partyDto != null) {
                List<RoleDto> roleDtos = findRoleByUserLoginId(userLoginId);
                partyDto.setRoleDtos(roleDtos);

                //
                log.info("[{}] Se ha encontrado el participante [{}] relacionado !!! ", PartyDaoImpl.class.getSimpleName(), partyDto);
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("Ha ocurrido un error al obtener el participante con el userlogin[{}]!!!", userLoginId);
                log.error(e.getMessage(), e);
            }
        }
        return partyDto;
    }

    @Override
    public List<RoleDto> findRoleByUserLoginId(String userLoginId) {
        List<RoleDto> roleDtos = Lists.newArrayList();
        try {
            roleDtos = partyMapper.findRoleByUserLoginId(userLoginId);
            //
            log.info("[{}] Se han encontrado los [{}] roles por el userLoginId [{}]", PartyDaoImpl.class.getSimpleName(), roleDtos.size(), userLoginId);

            if(log.isDebugEnabled()){
                log.debug("[{}] Se han encotrado los roles [{}] por el userLoginId [{}]", PartyDaoImpl.class.getSimpleName(), roleDtos, userLoginId);
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("Ha ocurrido un error al obtener los roles asociados al userLoginId [{}] !!!", userLoginId);
                log.error(e.getMessage(), e);
            }
        }
        return roleDtos;
    }
}
