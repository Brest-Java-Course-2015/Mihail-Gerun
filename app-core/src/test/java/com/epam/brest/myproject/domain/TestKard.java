package com.epam.brest.myproject.domain;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by bendar on 26.10.15.
 */
public class TestKard {

    private static final Logger LOGGER = LogManager.getLogger();

    private Kard kard;
    private LocalDate testDate;
    private Integer testKardId;
    private Integer testBalance;
    private Kard kard1;
    private Kard kard2;
    private Kard kard3;
    private Kard kard4;
    private Integer testUserId;
    private String testKardName;
    private String testLogin;


    @Before
    public void setUp() throws Exception
    {
        kard = new Kard();
        testDate = new LocalDate();
        testKardId =3;
        testUserId = 1;
        testBalance = 5555;
        testKardName ="kard";
        testLogin = "user";
        kard1 = new Kard(testKardId,1, testKardName);
        kard2 = new Kard(testKardName, testLogin);
        kard3 = new Kard(testUserId,testLogin, testKardName);
        kard4 = new Kard(testKardId, testUserId,testLogin,testKardName, testBalance, testDate, testDate);
    }


    @Test
    public void testGetKardId()
    {
        LOGGER.debug("test: getIdOperation()");

        kard.setKardId(testKardId);
        assertEquals(testKardId, kard.getKardId());
    }

    @Test
    public void testGetUpdatedDate()
    {
        LOGGER.debug("test: getUpdatedDate()");

        kard.setUpdatedDate(testDate);
        assertEquals(testDate, kard.getUpdatedDate());
    }

    @Test
    public void testGetBalance()
    {
        LOGGER.debug("test: getBalance()");
        kard.setBalance(testBalance);
        assertEquals(testBalance, kard.getBalance());
    }


    @Test
    public void testGetKardName()
    {
        LOGGER.debug("test: getKardName()");

        kard.setKardName("KardName");
        assertEquals("KardName", kard.getKardName());
    }

    @Test
    public void testGetKardCreatedDate()
    {
        LOGGER.debug("test: getKardCreatedDate()");

        kard.setCreatedDate(testDate);
        assertEquals(testDate, kard.getCreatedDate());
    }

    @Test
    public void testGetLogin()
    {
        LOGGER.debug("test: getLogin()");

        kard.setLogin("userLogin1");
        assertEquals("userLogin1",kard.getLogin());
    }

    @Test
    public void testGetUserId()
    {
        LOGGER.debug("test: getUserId()");

        kard.setUserId(testKardId);
        assertEquals(testKardId,kard.getUserId());
    }

    @Test
    public void testConstKard()
    {
        LOGGER.debug("test: construktors Kard");
        assertTrue(kard1.getKardId() == testKardId);
        assertTrue(kard3.getUserId() == testUserId);
        assertTrue(kard4.getKardId() == testKardId);
        assertTrue(kard4.getUserId() == testUserId);
        assertEquals(kard1.getKardName(),testKardName);
        assertEquals(kard2.getKardName(),testKardName);
        assertEquals(kard4.getKardName(),testKardName);
        assertTrue(kard4.getBalance()==testBalance);
        assertEquals(kard4.getCreatedDate(),testDate);
        assertEquals(kard4.getUpdatedDate(), testDate);
    }

    @Test
    public void testEquals()
    {
        LOGGER.debug("test: equals Kard");
        assertTrue(kard1.equals(kard1));
        assertTrue(kard1.equals(kard2));
        kard1.setKardName("test");
        assertTrue(!kard1.equals(kard2));
        assertTrue(!kard1.equals(kard3));
    }

}
