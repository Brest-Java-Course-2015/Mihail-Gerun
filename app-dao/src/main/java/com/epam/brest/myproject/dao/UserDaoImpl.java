package com.epam.brest.myproject.dao;

import com.epam.brest.myproject.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by bendar on 31.10.15.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${user.select}")
    private String userSelect;

    @Value("${user.selectById}")
    private String userSelectById;

    @Value("${user.selectByLogin}")
    private String userSelectByLogin;

    @Value("${user.insertUser}")
    private String insertUser;

    @Value("${user.deleteUser}")
    private String deleteUser;

    @Value("${user.countUsers}")
    private String countUser;

//    @Value("${user.getBalance}")
//    private String balanceUser;
//
//    @Value("${user.getCountKard}")
//    private String countKardUser;
//
//    @Value("${user.setCountKardAndBalance}")
//    private String setCountKardAndBalanceUser;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<User> userMapper = new BeanPropertyRowMapper<>(User.class);

    public UserDaoImpl(DataSource dataSourceUser) {
        jdbcTemplate = new JdbcTemplate(dataSourceUser);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSourceUser);
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return jdbcTemplate.query(userSelect,userMapper);
    }

    @Override
    public User getUserById(Integer userId) {
        LOGGER.debug("getUserById()");
        return jdbcTemplate.queryForObject(userSelectById, new Object[]{userId}, new UserRowMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin()");
        return jdbcTemplate.queryForObject(userSelectByLogin, new Object[]{login}, new UserRowMapper());
    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("addUser(user): login = {}", user.getLogin());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertUser, getParametersMap(user), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("deleteUser()");
        jdbcTemplate.update(deleteUser, userId);
    }

    @Override
    public Integer getCountUsers(String login) {
        LOGGER.debug("getCountUsers(): login = {}", login);
        return jdbcTemplate.queryForObject(countUser, new String[]{login}, Integer.class);
    }



    private MapSqlParameterSource getParametersMap(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", user.getUserId());
        parameterSource.addValue("login", user.getLogin());
        parameterSource.addValue("countKardOnUser", user.getCountKardOnUser());
        parameterSource.addValue("allBalance", user.getAllBalance());
        parameterSource.addValue("createdDate", user.getCreatedUserDate());
        return parameterSource;
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getInt("userId"),
                    resultSet.getString("login"),
                    resultSet.getInt("countKardOnUser"),
                    resultSet.getInt("allBalance"),
                    resultSet.getTimestamp("createdUserDate"));
        }
    }
}
