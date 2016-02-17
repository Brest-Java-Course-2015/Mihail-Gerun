package com.epam.brest.myproject.service;

import com.epam.brest.myproject.domain.Kard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bendar on 10.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class KardServiceImplTest {
    private DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private KardService kardService;

    private Kard kard = new Kard(null,1,"userLogin1", "kard8");

    @Test
    public void testGetAllKard() throws Exception {
        LOGGER.debug("test: getAllKard()");
        Assert.assertTrue(kardService.getAllKard().size() > 0);
    }

    @Test
    public void testGetAllKardOnUser() throws Exception {
        LOGGER.debug("test: getAllKardOnUser()");
        Integer userId = kardService.getAllKard().get(0).getUserId();
        Assert.assertTrue(kardService.getAllKardOnUser(userId).size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllKardOnUserWithNullUserId() throws Exception {
        LOGGER.debug("test: getAllKardOnUser()");
        Assert.assertTrue(kardService.getAllKardOnUser(null).size() > 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetAllKardOnUserWithZeroUserID() throws Exception {
        LOGGER.debug("test: getAllKardOnUser()");
        Assert.assertTrue(kardService.getAllKardOnUser(0).size() > 0);
    }

    @Test
    public void testAddKard() throws Exception {
        LOGGER.debug("test: AddKard()={}",kard);
        int sizeBefore = kardService.getAllKard().size();
        kardService.addKard(kard);
        Assert.assertTrue(sizeBefore + 1 == kardService.getAllKard().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithNullKard() throws Exception {
        LOGGER.debug("test: getAllKardWithNullKard()");
        kardService.addKard(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithNullKardName() throws Exception {
        LOGGER.debug("test: getAllKardOnUserWithNullKardName()");
        String kardName=null;
        kard.setKardName(kardName);
        kardService.addKard(kard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithNullUserIdAndLogin() throws Exception {
        LOGGER.debug("test: getAllKardOnUserWithNullUserIdAndNullLogin()");
        Integer userId = null;
        String login = null;
        kard.setUserId(userId);
        kard.setLogin(login);
        kardService.addKard(kard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithId() throws Exception {
        LOGGER.debug("test: getAllKardWithId()");
        kard.setKardId(5);
        kardService.addKard(kard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithZeroId() throws Exception {
        LOGGER.debug("test: getAllKardWithId()");
        kard.setKardId(0);
        kardService.addKard(kard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddKardWithNotUniqueName() throws Exception {
        LOGGER.debug("test: addKardWithNotUniqueName()");
        Kard kard = new Kard(2,2, "kard8");
        kardService.addKard(kard);
        kardService.addKard(kard);
    }

    @Test
    public void testDeleteKard() throws Exception {
        LOGGER.debug("test: deleteKard()");
        int sizeBefore = kardService.getAllKard().size();
        kardService.deleteKard(1);
        Assert.assertTrue(sizeBefore - 1 == kardService.getAllKard().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteKardWithNullId() throws Exception {
        LOGGER.debug("test: deleteKardWithNullId()");
        Integer id = null;
        kardService.deleteKard(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteKardWithZeroId() throws Exception {
        LOGGER.debug("test: deleteKardWithZeroId()");
        Integer id = 0;
        kardService.deleteKard(id);
    }

    @Test
    public void testDeleteAllKardOnUser() throws Exception {
        LOGGER.debug("test: deleteAllKardOnUser()");
        kardService.deleteAllKardOnUser(1);
        Assert.assertTrue(0 == kardService.getAllKardOnUser(1).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAllKardOnUserWithNullId() throws Exception {
        LOGGER.debug("test: deleteKardWithNullId()");
        kardService.deleteAllKardOnUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAllKardOnUserWithZeroId() throws Exception {
        LOGGER.debug("test: deleteKardWithZeroId()");
        kardService.deleteAllKardOnUser(0);
    }

    @Test
    public void testChangeBalance() throws Exception {
        LOGGER.debug("test: ChangeBalance()");
        int balanceBefore = kardService.getKardById(1).getBalance();
        kardService.changeBalance(1,10000);
        Assert.assertTrue(balanceBefore+10000 == kardService.getKardById(1).getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeBalanceWithNullId() throws Exception {
        LOGGER.debug("test: ChangeBalanceWithNullId()");
        Integer id = null;
        kardService.changeBalance(id,10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeBalanceWithZeroId() throws Exception {
        LOGGER.debug("test: ChangeBalanceWithZeroId()");
        Integer id = 0;
        kardService.changeBalance(id,10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeBalanceWithNullOperationOnKard() throws Exception {
        LOGGER.debug("test: ChangeBalanceWithNullOperation()");
        Integer operation = null;
        kardService.changeBalance(1,operation);
    }

    @Test
    public void testGetTotalCountKard() throws Exception {
        LOGGER.debug("test: GetTotalCountKard()");
        Assert.assertTrue(kardService.getTotalCountKard() > 0);
    }

    @Test
    public void testGetKardById() throws Exception {
        LOGGER.debug("test: testGetKardById()");
        kardService.getKardById(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardByIdWithNullId() throws Exception {
        LOGGER.debug("test: testGetKardByIdWithNullId()");
        Integer id = null;
        kardService.getKardById(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardByIdWithZeroId() throws Exception {
        LOGGER.debug("test: testGetKardByIdWithZeroId()");
        Integer id = 0;
        kardService.getKardById(0);
    }

    @Test
    public void testGetKardByName() throws Exception {
        LOGGER.debug("test: testGetKardByName()");
        kardService.getKardByName(kardService.getKardById(2).getKardName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardByIdWithNullName() throws Exception {
        LOGGER.debug("test: testGetKardByIdWithNullName()");
        String kardName = null;
        kardService.getKardByName(kardName);
    }

    @Test
    public void testGetKardDto() throws Exception {
        LOGGER.debug("test: testGetKardDto()");
        kardService.getKardDto();
    }

    @Test
    public void testGetKardDtoByUserId() throws Exception {
        LOGGER.debug("test: testGetKardDtoByUserId()");
        kardService.getKardDto(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardDtoByNullUserId() throws Exception {
        LOGGER.debug("test: testGetKardDtoByUserId()");
        kardService.getKardDto(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardDtoByZeroUserId() throws Exception {
        LOGGER.debug("test: testGetKardDtoByUserId()");
        kardService.getKardDto(0);
    }

    @Test
    public void testGetKardDtoWithDateFilter() throws Exception {
        LOGGER.debug("test: testGetKardDtoWithDateFilter()");
        kardService.getKardDto(DATE_FORMAT.parseLocalDate("11/11/2011"),DATE_FORMAT.parseLocalDate("11/11/2016") );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardDtoWithDateFilterWithNullStartDate() throws Exception {
        LOGGER.debug("test: testGetKardDtoWithDateFilterWithNullStartDate()");
        kardService.getKardDto(null, DATE_FORMAT.parseLocalDate("11/11/2016"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardDtoWithDateFilterWithNullEndDate() throws Exception {
        LOGGER.debug("test: testGetKardDtoWithDateFilterWithNullEndDate()");
        kardService.getKardDto(DATE_FORMAT.parseLocalDate("11/11/2011"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetKardDtoWithDateFilterWithNullStartAndEndDate() throws Exception {
        LOGGER.debug("test: testGetKardDtoWithDateFilterWithNullStartAndEndDate()");
        kardService.getKardDto(null, null);
    }
}