package user.manager.side.infraestructure.persistence.mybatis.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.manager.side.infraestructure.dto.PartyDto;
import user.manager.side.infraestructure.dto.RoleDto;
import user.manager.side.infraestructure.enums.UserStatusEnum;
import user.manager.side.infraestructure.persistence.mybatis.dao.PartyDao;
import user.manager.side.infraestructure.persistence.mybatis.mapper.PartyMapper;

import java.util.Date;

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
    public Long nexValueForIdentifier() {
        long partyId = 0;
        try {
            partyId = partyMapper.nexValueForIdentifier();
            if(partyId > 0){
                log.info("[{}] Se ha generado el nuevo [partyId : {}] correctamente!!!", PartyDaoImpl.class.getSimpleName(), partyId);
            }else{
                if(log.isWarnEnabled()){
                    log.warn("[{}] Ha ocurrido un error en la consistencia de la data, No se ha generado el [partyId] correctamente!!!", PartyDaoImpl.class.getSimpleName());
                }
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("[{}] Ha ocurrido un error al generar el [partyId] desde la base datos!!!", PartyDaoImpl.class.getSimpleName());
                log.error(e.getMessage(), e);
            }
        }
        return partyId;
    }

    @Override
    public boolean  saveParty(PartyDto partyDto) {
        int rowAffected;
        boolean isSaved = false;
        try {
            // default values
            partyDto.setCreatedDate(new Date());
            partyDto.setCreateByUserLogin(partyDto.getUserLoginId());
            partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
            // save party
            rowAffected = partyMapper.saveParty(partyDto);
            if(rowAffected == 1){
                isSaved = true;
                //
                log.info("[{}] Se ha insertado correctamente el participante[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
            }else{
                if(log.isWarnEnabled()){
                    log.warn("[{}] Ha ocurrido un error en la consistencia de la data al insertar el participante[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
                }
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("[{}] Ha ocurrido un error al insertar el participante[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
                log.error(e.getMessage(), e);
            }
        }
        return isSaved;
    }

    @Override
    public boolean saveUserLogin(PartyDto partyDto) {
        int rowAffected;
        boolean isSaved = false;
        try {

            rowAffected = partyMapper.saveUserLogin(partyDto);
            if(rowAffected == 1){
                isSaved = true;
                //
                log.info("[{}] Se ha insertado correctamente el usuario[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
            }else{
                if(log.isWarnEnabled()){
                    log.warn("[{}] Ha ocurrido un error en la consistencia de la data al insertar el usuario[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
                }
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("[{}] Ha ocurrido un error al insertar el usuario[{}]!!!", PartyDaoImpl.class.getSimpleName(), partyDto);
                log.error(e.getMessage(), e);
            }
        }
        return isSaved;
    }

    @Override
    public boolean saveUserRole(RoleDto roleDto) {
        int rowAffected;
        boolean isSaved = false;
        try {

            rowAffected = partyMapper.saveRole(roleDto);
            if(rowAffected == 1){
                isSaved = true;
                //
                log.info("[{}] Se ha insertado correctamente el rol[{}]!!!", PartyDaoImpl.class.getSimpleName(), roleDto);
            }else{
                if(log.isWarnEnabled()){
                    log.warn("[{}] Ha ocurrido un error en la consistencia de la data al insertar el rol[{}]!!!", PartyDaoImpl.class.getSimpleName(), roleDto);
                }
            }
        }catch (Exception e){
            if(log.isErrorEnabled()) {
                log.error("[{}] Ha ocurrido un error al insertar el rol[{}]!!!", PartyDaoImpl.class.getSimpleName(), roleDto);
                log.error(e.getMessage(), e);
            }
        }
        return isSaved;
    }
}
