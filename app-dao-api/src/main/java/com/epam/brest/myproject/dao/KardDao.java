package com.epam.brest.myproject.dao;

import com.epam.brest.myproject.domain.Kard;

import org.joda.time.LocalDate;
import java.util.List;

/**
 * Created by bendar on 28.10.15.
 */
public interface KardDao {

    public List<Kard> getAllKard();

    public List<Kard> getAllKardOnUser(Integer userId);

    public Integer addKard(Kard kard);

    public void deleteKard(Integer kardId);

    public void deleteAllKardOnUser(Integer userId);

    public Integer changeBalance(Integer kardId,Integer operationOnKard);

    public Integer getTotalCountKard();

    public Kard getKardById(Integer kardId);

    public List<Kard> getKardByName(String kardName);

    public Integer getCountUsersKard(Integer userId,String kardName);

    public Integer getCountUsersKard(String login,String kardName);

    public List<Kard> dateFilter(LocalDate startDateFilter, LocalDate endDateFilter);
}
