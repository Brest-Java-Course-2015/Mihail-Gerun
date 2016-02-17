package com.epam.brest.myproject.dao;

import com.epam.brest.myproject.domain.Kard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bendar on 06.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class KardDaoImplTest {
    private DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String NAME_KARD="kard1";
    private static final Integer ID_KARD=1;
    private static final Integer ID_USER=1;
    private static final Integer OPERATION_KARD=10000;
    private static final Kard kard = new Kard(1,1,"kard6");

    @Autowired
    KardDao kardDao;

    @Test
    public void testGetAllKard() throws Exception {
        LOGGER.debug("Test: getAllKards()");
        List<Kard> kards = kardDao.getAllKard();
        assertTrue(kards.size() == 3);
    }

    @Test
    public void testGetAllKardOnUser() throws Exception {
        LOGGER.debug("Test: getAllKardsOnUser()");
        assertTrue(kardDao.getAllKardOnUser(ID_USER).size() == 2);
    }

    @Test
    public void testAddKardWithId() throws Exception {
        LOGGER.debug("Test: addKard()");
        Integer kardId = kardDao.addKard(kard);
        assertNotNull(kardId);
        Kard newKard = kardDao.getKardById(kardId);
        assertNotNull(newKard);
        assertTrue(kard.getKardName().equals(newKard.getKardName()));
        assertTrue(kard.getLogin().equals(newKard.getLogin()));
        assertNotNull(newKard.getCreatedDate().toString());
    }

    @Test
    public void testAddKardWithLogin() throws Exception {
        LOGGER.debug("Test: addKard()");
        kard.setUserId(null);
        kard.setLogin("userLogin1");
        Integer kardId = kardDao.addKard(kard);
        assertNotNull(kardId);
        Kard newKard = kardDao.getKardById(kardId);
        assertNotNull(newKard);
        assertTrue(kard.getKardName().equals(newKard.getKardName()));
        assertTrue(kard.getLogin().equals(newKard.getLogin()));
        assertNotNull(newKard.getCreatedDate().toString());
    }

    @Test
    public void testDeleteKard() throws Exception {
        LOGGER.debug("test :deleteKard()");
        List<Kard> kards = kardDao.getAllKard();
        assertTrue(kards.size() > 0);
        int sizeBefore = kards.size();
        kardDao.deleteKard(kards.get(0).getKardId());
        assertTrue((sizeBefore - 1) == kardDao.getAllKard().size());
    }

    @Test
    public void testDeleteAllKardOnUser() throws Exception {
        LOGGER.debug("test :deleteKard()");
        assertTrue(kardDao.getAllKardOnUser(ID_USER).size() > 0);
        kardDao.deleteAllKardOnUser(ID_USER);
        assertTrue(kardDao.getAllKardOnUser(ID_USER).size() == 0);
    }

    @Test
    public void testChangeBalance() throws Exception {
        LOGGER.debug("Test: changeBalance()");
        Integer balance = kardDao.getKardById(ID_KARD).getBalance();
        Integer newBalance =  kardDao.changeBalance(ID_KARD, OPERATION_KARD);
        assertTrue(balance + OPERATION_KARD == newBalance);
    }

    @Test
    public void testGetTotalCountKard() throws Exception {
        LOGGER.debug("Test: getTotalCountKard()");
        assertTrue(kardDao.getTotalCountKard() == kardDao.getAllKard().size());
    }


    @Test
    public void testGetKardById() throws Exception {
        LOGGER.debug("Test: getKardById()");
        Kard kard = kardDao.getKardById(ID_KARD);
        assertNotNull(kard);
        assertTrue(kard.getKardId().equals(ID_KARD));
    }

    @Test
    public void testGetKardByName() throws Exception {
        LOGGER.debug("Test: getKardByName()");
        List<Kard> kard = kardDao.getKardByName(NAME_KARD);
        assertNotNull(kard);
        assertTrue(kard.get(0).getKardName().equals(NAME_KARD));
    }

    @Test
    public void testCountUsersKard()throws Exception {
        LOGGER.debug("test: countUsersKard()");
        Integer userId = kardDao.getAllKard().get(0).getUserId();
        String kardName = kardDao.getAllKard().get(0).getKardName();
        assertNotNull(userId);
        assertNotNull(kardName);
        Integer kardCount = kardDao.getCountUsersKard(userId, kardName);
        assertNotNull(kardCount);
        assertTrue(kardCount.equals(1));
    }

    @Test
    public void testZeroCountUsers() throws Exception {
        LOGGER.debug("test: zeroCountUsersKard()");
        Integer userId = 15;
        String kardName = "wadawad";
        Integer kardCount = kardDao.getCountUsersKard(userId, kardName);
        assertNotNull(kardCount);
        assertTrue(kardCount.equals(0));
    }

    @Test
    public void testDateFilter() throws Exception
    {
        LOGGER.debug("test: dateFilter()");
        LocalDate lstart = DATE_FORMAT.parseLocalDate("1/1/2014");
        LocalDate lend = LocalDate.now();
        List<Kard> testList= kardDao.dateFilter(lstart, lend);
        assertTrue(testList.size() == 3);
    }
}

