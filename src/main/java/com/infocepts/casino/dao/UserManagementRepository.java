package com.infocepts.casino.dao;

import com.infocepts.casino.model.User;
import com.infocepts.casino.responsemodels.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserManagementRepository {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public UserLoginResponse doLogin(final User user) {


        UserLoginResponse userLoginResponse = this.applicationContext.getBean(UserLoginResponse.class);

        //validate user against DB
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
                    .withProcedureName("SP_Login")
                    .withReturnValue();

            Map<String, String> inParamMap = new HashMap<String, String>();
            inParamMap.put("userName", user.getUserName());
            inParamMap.put("password", user.getPassword());
            SqlParameterSource inparams = new MapSqlParameterSource(inParamMap);

            Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inparams);

            boolean isUserAuthenticate =  (Integer.parseInt( simpleJdbcCallResult.get("RETURN_VALUE").toString()) == 1 ? true : false);


            //todo: Generate SessionID for the user, Store it in in DB and return it in response
            if(isUserAuthenticate) {
                userLoginResponse.setToken("123456789987654321");
            }

            userLoginResponse.setUserName(user.getUserName());
            userLoginResponse.setAuthorized(isUserAuthenticate);


        } catch (DataAccessException e)
        {
            userLoginResponse.setErrorMessage(e.getMessage());
        }

        return userLoginResponse;
    }

}
