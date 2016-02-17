package com.epam.brest.myproject.rest;

import com.epam.brest.myproject.domain.Kard;
import com.epam.brest.myproject.dto.KardDto;
import com.epam.brest.myproject.service.KardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMock;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;


import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by bendar on 19.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-mock.xml"})
public class KardControllerMockTest {

    @Resource
    private KardRestController kardRestController;

    private MockMvc mockMvc;


    @Autowired
    private KardService kardService;

    @Before
    public void setUp()
    {
        mockMvc = standaloneSetup(kardRestController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown()
    {
        verify(kardService);
        reset(kardService);
    }

    @Test
    public void addKardTest() throws Exception {

        expect(kardService.addKard(anyObject(Kard.class))).andReturn(4);
        replay(kardService);

        String kard =new ObjectMapper().writeValueAsString(new Kard(null,2,"kard5"));

        mockMvc.perform(post("/kard").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(kard)).andDo(print()).andExpect(status().isCreated()).andExpect(content().string("4"));
    }


    @Test
    public void deleteAllKardOnUserTest() throws Exception {

        kardService.deleteAllKardOnUser(EasyMock.anyObject(Integer.class));
        replay(kardService);
        mockMvc.perform(delete("/kards/delete/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardsTest() throws Exception {

        expect(kardService.getAllKard()).andReturn(Arrays.asList(new Kard()));
        replay(kardService);
        mockMvc.perform(get("/kards").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardOnUserTest() throws Exception {

        expect(kardService.getAllKardOnUser(EasyMock.anyObject(Integer.class))).andReturn(Arrays.asList(new Kard()));
        replay(kardService);
        mockMvc.perform(get("/kards/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteKardTest() throws Exception {

        kardService.deleteKard(EasyMock.anyObject(Integer.class));
        replay(kardService);
        mockMvc.perform(delete("/kard/delete/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getTotalCountKardsTest() throws Exception {

        expect(kardService.getTotalCountKard()).andReturn(3);
        replay(kardService);
        mockMvc.perform(get("/kards/count").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardByNameTest() throws Exception {

        expect(kardService.getKardByName(EasyMock.anyObject(String.class))).andReturn(Arrays.asList(new Kard()));
        replay(kardService);
        mockMvc.perform(get("/kards/name/kard1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardByIdTest() throws Exception {

        expect(kardService.getKardById(EasyMock.anyObject(Integer.class))).andReturn(new Kard());
        replay(kardService);
        mockMvc.perform(get("/kard/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void kardOperationTest() throws Exception {

        expect(kardService.changeBalance(EasyMock.anyObject(Integer.class),EasyMock.anyObject(Integer.class))).andReturn(0);
        replay(kardService);
        mockMvc.perform(post("/kard/operation/1/-1000").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardDto() throws Exception {
        expect(kardService.getKardDto()).andReturn(new KardDto());
        replay(kardService);
        mockMvc.perform(get("/karddto").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardDtoByUser() throws Exception {
        expect(kardService.getKardDto(EasyMock.anyObject(Integer.class))).andReturn(new KardDto());
        replay(kardService);
        mockMvc.perform(get("/karddto/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getKardDtoWithDateFilter() throws Exception {
        expect(kardService.getKardDto(EasyMock.anyObject(LocalDate.class),EasyMock.anyObject(LocalDate.class))).andReturn(new KardDto());
        replay(kardService);
        mockMvc.perform(get("/karddto/filter?startDate=31/12/2016&endDate=5/2/2016").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }
}
