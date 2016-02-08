package com.epam.brest.myproject.dto;

import com.epam.brest.myproject.domain.Kard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bendar on 18.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-spring-core.xml"})
public class TestKardDto {

    private static final Integer totalKards = 5;
    @Autowired
    private KardDto kardDto;
    @Autowired
    private Kard kard;

    @Test
    public void testGetUsers() throws Exception {
        List<Kard> list = new ArrayList<Kard>() {};
        list.add(kard);
        kardDto.setKards(list);
        assertNotNull(kardDto.getKards());
        assertTrue(kardDto.getKards().size() > 0);
        assertEquals(kard.getClass(), kardDto.getKards().get(0).getClass());
    }

    @Test
    public void testGetTotalUsers() throws Exception {
        kardDto.setTotalKards(totalKards);
        assertNotNull(kardDto.getTotalKards());
        assertTrue(kardDto.getTotalKards() == totalKards);
    }
}
