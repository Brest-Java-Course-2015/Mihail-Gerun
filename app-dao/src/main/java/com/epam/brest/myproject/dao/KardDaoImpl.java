package com.epam.brest.myproject.dao;

import com.epam.brest.myproject.domain.Kard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



/**
 * Created by bendar on 31.10.15.
 */
public class KardDaoImpl implements KardDao {
    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${kard.select}")
    private String kardSelect;

    @Value("${kard.selectById}")
    private String kardSelectById;

    @Value("${kard.selectByKardName}")
    private String kardSelectByKardName;

    @Value("${kard.selectByUser}")
    private String kardSelectByUser;

    @Value("${kard.insertKard}")
    private String insertKard;

    @Value("${kard.deleteKard}")
    private String deleteKard;

    @Value("${kard.deleteKardOnUser}")
    private String deleteKardOnUser;

    @Value("${kard.changeBalance}")
    private String changeBalance;

    @Value("${kard.countKard}")
    private String countKard;

    @Value("${kard.countUsersKard}")
    private String countUsersKard;

    @Value("${user.returnIdByLogin}")
    private String returnIdByLogin;

    @Value("${kard.dateFilter}")
    private String dateFilterSql;



    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public KardDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Kard> getAllKard() {
        LOGGER.debug("getAllKard()");
        return jdbcTemplate.query(kardSelect, new KardRowMapper());
    }

    @Override
    public List<Kard> getAllKardOnUser(Integer userId)
    {
        LOGGER.debug("getAllKardOnUser()");
        return jdbcTemplate.query(kardSelectByUser, new Object[]{userId}, new KardRowMapper());
    }

    @Override
    public Integer addKard(Kard kard)
    {
        LOGGER.debug("addKardOnUser(): kardName={}");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertKard, getParametersMap(kard), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void deleteKard(Integer kardId) {
        LOGGER.debug("deleteKard()");
        jdbcTemplate.update(deleteKard, kardId);

    }

    @Override
    public void deleteAllKardOnUser(Integer userId) {
        LOGGER.debug("deleteAllKardOnUser()");
        jdbcTemplate.update(deleteKardOnUser, userId);
    }

    @Override
    public Integer changeBalance(Integer kardId, Integer operationOnKard) {
        LOGGER.debug("changeBalanceOnKard()");
        Kard kard = getKardById(kardId);
        kard.setBalance(kard.getBalance() + operationOnKard);
        jdbcTemplate.update(changeBalance, kard.getBalance(),LocalDate.now().toDateTimeAtStartOfDay().toDate(),kard.getKardId());
        return kard.getBalance();
    }

    @Override
    public Integer getTotalCountKard() {
        LOGGER.debug("getTotalCountKard()");
        return jdbcTemplate.queryForObject(countKard, Integer.class);
    }

    @Override
    public Kard getKardById(Integer kardId) {
        LOGGER.debug("getKardById()");
        return jdbcTemplate.queryForObject(kardSelectById, new Object[]{kardId}, new KardRowMapper());
    }

    @Override
    public List<Kard> getKardByName(String kardName) {
        LOGGER.debug("getKardByName()");
        return jdbcTemplate.query(kardSelectByKardName, new Object[]{kardName}, new KardRowMapper());
    }

    @Override
    public Integer getCountUsersKard(String userLogin, String kardName) {
        LOGGER.debug("getCountUsersKard");
        return jdbcTemplate.queryForObject(countUsersKard, new Object[]{userLogin,kardName}, Integer.class);

    }

    @Override
    public Integer returnUserIdByLogin(String login) {
        LOGGER.debug("retunrUserIDByLogin");
        return jdbcTemplate.queryForObject(returnIdByLogin, new Object[]{login}, Integer.class);

    }

    @Override
    public List<Kard> dateFilter(LocalDate startDateFilter, LocalDate endDateFilter) {
        LOGGER.debug("dateFilter");
        MapSqlParameterSource parameterSource =
                new MapSqlParameterSource("startDate", startDateFilter.toDateTimeAtStartOfDay().toDate());
        parameterSource.addValue("endDate", endDateFilter.toDateTimeAtStartOfDay().toDate());
        return namedParameterJdbcTemplate.query(dateFilterSql,parameterSource,new KardRowMapper());
    }

    private MapSqlParameterSource getParametersMap(Kard kard) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("kardId", kard.getKardId());
        parameterSource.addValue("userId", kard.getUserId());
        parameterSource.addValue("login", kard.getLogin());
        parameterSource.addValue("kardName", kard.getKardName());
        parameterSource.addValue("balance", kard.getBalance());
        Date createdDate = kard.getCreatedDate().toDateTimeAtStartOfDay().toDate();
        parameterSource.addValue("createdDate", createdDate);
        Date updatedDate = kard.getUpdatedDate().toDateTimeAtStartOfDay().toDate();
        parameterSource.addValue("updatedDate", updatedDate);
        return parameterSource;
    }

    private class KardRowMapper implements RowMapper<Kard> {

        @Override
        public Kard mapRow(ResultSet resultSet, int i) throws SQLException {
            Date createdDate = resultSet.getTimestamp("createdDate");
            LocalDate localCreatedDate = new LocalDate(createdDate);
            Date updatedDate = resultSet.getTimestamp("updatedDate");
            LocalDate localUpdatedDate = new LocalDate(updatedDate);
            return new Kard(resultSet.getInt("kardId"),
                    resultSet.getInt("userId"),
                    resultSet.getString("login"),
                    resultSet.getString("kardName"),
                    resultSet.getInt("balance"),
                    localCreatedDate,
                    localUpdatedDate);
        }
    }
}
