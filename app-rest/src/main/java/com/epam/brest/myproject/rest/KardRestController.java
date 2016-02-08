package com.epam.brest.myproject.rest;

import com.epam.brest.myproject.domain.Kard;
import com.epam.brest.myproject.dto.KardDto;
import com.epam.brest.myproject.service.KardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by bendar on 18.11.15.
 */
@RestController
public class KardRestController {
    DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private KardService kardService;

    @RequestMapping(value = "/kards", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Kard> getKards()
    {
        LOGGER.debug("getKards()");
        return kardService.getAllKard();
    }

    @RequestMapping(value = "/kards/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Integer getTotalCountKards()
    {
        LOGGER.debug("getTotalCountKards()");
        return kardService.getTotalCountKard();
    }

    @RequestMapping(value = "/kards/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Kard> getKardsOnUser(@PathVariable(value = "userId") Integer userId)
    {
        LOGGER.debug("getKardsOnUser()");
        return kardService.getAllKardOnUser(userId);
    }

    @RequestMapping(value = "/kard", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addUser(@RequestBody Kard kard) {
        LOGGER.debug("addKard: kard = {}", kard);
        return kardService.addKard(kard);
    }

    @RequestMapping(value = "/kard/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Kard getKard(@PathVariable(value = "id") Integer id){
        LOGGER.debug("getKardById: id = {}", id);
        return kardService.getKardById(id);
    }

    @RequestMapping(value = "/kard/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteKard(@PathVariable(value = "id") Integer id)
    {
        LOGGER.debug("deleteKard: id = {}", id);
        kardService.deleteKard(id);
    }

    @RequestMapping(value = "/kards/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteKardOnUser(@PathVariable(value = "userId") Integer userId)
    {
        LOGGER.debug("deleteAllKardOnUser: userLogin = {}", userId);
        kardService.deleteAllKardOnUser(userId);
    }

    @RequestMapping(value = "/kards/name/{kardName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Kard> getKard(@PathVariable(value = "kardName") String kardName) {
        LOGGER.debug("getKards: kardName = {}", kardName);
        return kardService.getKardByName(kardName);
    }

    @RequestMapping(value = "/kard/operation/{id}/{operationOnKard}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer updateBalance(@PathVariable(value = "id") Integer id,
                                            @PathVariable(value = "operationOnKard") Integer operationOnKard) {
        LOGGER.debug("getUser: updateBalance = {}", id);
        return kardService.changeBalance(id,operationOnKard);
    }

    @RequestMapping(value = "/karddto", method = RequestMethod.GET)
    public @ResponseBody KardDto getKardDto() {
        LOGGER.debug("getKardDto()");
        return kardService.getKardDto();
    }

    @RequestMapping(value = "/karddto/{userId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody KardDto getKardDto(@PathVariable(value = "userId") Integer userId)
    {
        LOGGER.debug("getKardDto: userId = {}", userId);
        return kardService.getKardDto(userId);
    }

    @RequestMapping(value = "/karddto/filter", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody KardDto getKardDto(@RequestParam(value = "startDate") String startDate,
                       @RequestParam(value = "endDate") String endDate) throws ParseException
    {
        LOGGER.debug("getKardDto: Date = {} - {}", startDate , endDate);
        return kardService.getKardDto(DATE_FORMAT.parseLocalDate(startDate),DATE_FORMAT.parseLocalDate(endDate));
    }
}
