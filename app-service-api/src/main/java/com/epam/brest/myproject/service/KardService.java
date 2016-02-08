package com.epam.brest.myproject.service;

import com.epam.brest.myproject.domain.Kard;
import com.epam.brest.myproject.dto.KardDto;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by bendar on 10.11.15.
 */
public interface KardService {

    public List<Kard> getAllKard();

    public List<Kard> getAllKardOnUser(Integer userId);

    public Integer addKard(Kard kard);

    public void deleteKard(Integer kardId);

    public void deleteAllKardOnUser(Integer userId);

    public Integer changeBalance(Integer kardId,Integer operationOnKard);

    public Integer getTotalCountKard();

    public Kard getKardById(Integer kardId);

    public List<Kard> getKardByName(String kardName);

    Integer returnUserIdByLogin(String login);

    KardDto getKardDto(LocalDate startDate, LocalDate endDate);

    KardDto getKardDto(Integer userId);

    KardDto getKardDto();
}
