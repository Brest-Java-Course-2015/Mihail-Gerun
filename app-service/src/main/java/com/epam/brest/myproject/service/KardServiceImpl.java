package com.epam.brest.myproject.service;

import com.epam.brest.myproject.dao.KardDao;
import com.epam.brest.myproject.domain.Kard;
import com.epam.brest.myproject.domain.User;
import com.epam.brest.myproject.dto.KardDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import org.joda.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by bendar on 10.11.15.
 */
@Transactional
public class KardServiceImpl implements KardService {
    private static final Logger LOGGER = LogManager.getLogger();

    private KardDao kardDao;

    public void setKardDao(KardDao kardDao) {
        this.kardDao=kardDao;
    }

    @Override
    public List<Kard> getAllKard() {
        LOGGER.debug("getAllUsers()");
        return kardDao.getAllKard();
    }

    @Override
    public List<Kard> getAllKardOnUser(Integer userId) {
        LOGGER.debug("getAllKardOnUser(): userId = {} ", userId);
        Assert.notNull(userId, "User Id should not be null.");
        Assert.isTrue(userId>0, "User Id should be more 0.");
        return kardDao.getAllKardOnUser(userId);
    }

    @Override
    public Integer addKard(Kard kard) {
        LOGGER.debug("addKard(): kard = {} ", kard);
        Assert.notNull(kard, "Kard should not be null.");
        LOGGER.debug("addKard(): kard name = {} ", kard.getKardName());
        Assert.isNull(kard.getKardId(), "kard Id should be null.");
        Assert.notNull(kard.getKardName(), "Kard name should not be null.");
        if(kard.getUserId()!=null) {
            Assert.notNull(kard.getUserId(), "Kard userid should not be null.");
        }
        else
        {
            Assert.notNull(kard.getLogin(), "Kard login should not be null");
        }
        if (kardDao.getCountUsersKard(kard.getUserId(),kard.getKardName()) > 0) {
            throw new IllegalArgumentException("Kard name should be unique.");
        }
        return kardDao.addKard(kard);
    }

    @Override
    public void deleteKard(Integer kardId) {
        LOGGER.debug("deleteKard(): kard id = {} ", kardId);
        Assert.notNull(kardId, "Kard Id should not be null.");
        Assert.isTrue(kardId > 0, "Kard ID should be more 0.");
        kardDao.deleteKard(kardId);
    }

    @Override
    public void deleteAllKardOnUser(Integer userId) {
        LOGGER.debug("deleteAllKardOnUser(): user Id = {} ", userId);
        Assert.notNull(userId, "User Id should not be null.");
        Assert.isTrue(userId>0, "User id should be more 0.");
        kardDao.deleteAllKardOnUser(userId);
    }

    @Override
    public Integer changeBalance(Integer kardId, Integer operationOnKard) {
        LOGGER.debug("changeBalanceOnKard(): Kard ID = {} ", kardId);
        Assert.notNull(kardId, "User Login should not be null.");
        Assert.isTrue(kardId > 0, "Kard ID should be more 0.");
        LOGGER.debug("changeBalanceOnKard(): Operation On Kard = {} ", operationOnKard);
        Assert.notNull(operationOnKard, "Operation On Kard should not be null.");
        return kardDao.changeBalance(kardId, operationOnKard);
    }

    @Override
    public Integer getTotalCountKard() {
        LOGGER.debug("getTotalCountKard()");
        return kardDao.getTotalCountKard();
    }

    @Override
    public Kard getKardById(Integer kardId)
    {
        LOGGER.debug("getKardById: Kard id = {} ", kardId);
        Assert.notNull(kardId, "Kard id should not be null.");
        Assert.isTrue(kardId > 0, "Kard id should be more 0");
        return kardDao.getKardById(kardId);

    }

    @Override
    public List<Kard> getKardByName(String kardName) {
        LOGGER.debug("getKardByName: Kard name = {} ", kardName);
        Assert.notNull(kardName, "Kard name should not be null.");
        Assert.hasText(kardName, "Kard name should be text");
        return kardDao.getKardByName(kardName);
    }


    @Override
    public KardDto getKardDto(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate);
        Assert.notNull(endDate);
        KardDto kardDto = new KardDto();
        kardDto.setTotalKards(kardDao.dateFilter(startDate, endDate).size());
        if (kardDto.getTotalKards() > 0) {
            kardDto.setKards(kardDao.dateFilter(startDate, endDate));
        } else {
            kardDto.setKards(Collections.<Kard>emptyList());
        }
        return kardDto;
    }

    @Override
    public KardDto getKardDto(Integer userId) {
        Assert.notNull(userId);
        Assert.isTrue(userId>0);
        KardDto kardDto = new KardDto();
        kardDto.setTotalKards(kardDao.getAllKardOnUser(userId).size());
        if (kardDto.getTotalKards() > 0) {
            kardDto.setKards(kardDao.getAllKardOnUser(userId));
        } else {
            kardDto.setKards(Collections.<Kard>emptyList());
        }
        return kardDto;
    }

    @Override
    public KardDto getKardDto() {
        KardDto kardDto = new KardDto();
        kardDto.setTotalKards(kardDao.getAllKard().size());
        if (kardDto.getTotalKards() > 0) {
            kardDto.setKards(kardDao.getAllKard());
        } else {
            kardDto.setKards(Collections.<Kard>emptyList());
        }
        return kardDto;
    }
}
